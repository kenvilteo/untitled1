package com.company.untitled1.view.emp;

import com.company.untitled1.entity.Child;
import com.company.untitled1.entity.Emp;
import com.company.untitled1.entity.GdSector;
import com.company.untitled1.view.branch.BranchDetailView;
import com.company.untitled1.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "emps/:id", layout = MainView.class)
@ViewController("Emp.detail")
@ViewDescriptor("emp-detail-view.xml")
@EditedEntityContainer("empDc")
public class EmpDetailView extends StandardDetailView<Emp> {







    @Autowired
    private Notifications notifications;
    @ViewComponent
    private DataGrid<Child> childrenDataGrid;
    @Autowired
    private Metadata metadata;
    @ViewComponent
    private TypedTextField<Long> idField;
    @ViewComponent
    private TypedTextField<String> nameField;
    @ViewComponent
    private CollectionContainer<Child> childrenDc;
    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private CollectionLoader<Child> childrenDl;
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private DataGrid<GdSector> sectorDataGrid;
    @ViewComponent
    private CollectionContainer<GdSector> gdSectorsDc;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        childrenDl.setParameter("idParent", Long.valueOf(idField.getValue()));
        childrenDl.load();
    }


    protected void showNotification(String message) {
        notifications.create(message)
                .withType(Notifications.Type.WARNING)
                .withCloseable(false)
                .show();
    }
    private static final Logger log = LoggerFactory.getLogger(BranchDetailView.class);

    @Subscribe("childrenDataGrid.create")
    protected void onChildDataGridCreate(ActionPerformedEvent event) {
        if (childrenDataGrid.getEditor().isOpen()) {
            showNotification("Close the editor before creating a new entity");
        } else {
            Child newChild = metadata.create(Child.class);

//            Long res = Long.valueOf(idField.getValue().replace(",", ""));
//            newChild.setParent(res);
            childrenDc.getMutableItems().add(newChild);
            childrenDataGrid.select(newChild);
            childrenDataGrid.getEditor().editItem(newChild);
        }
        //test
//        printOrderProperties();
    }

    @Subscribe("sectorDataGrid.create")
    protected void onSectorDataGridCreate(ActionPerformedEvent event) {
        if (sectorDataGrid.getEditor().isOpen()) {
            showNotification("Close the editor before creating a new entity");
        } else {
            GdSector newObj = metadata.create(GdSector.class);

//            Long res = Long.valueOf(idField.getValue().replace(",", ""));

//            newChild.setParent(res);
            gdSectorsDc.getMutableItems().add(newObj);
            sectorDataGrid.select(newObj);
            sectorDataGrid.getEditor().editItem(newObj);
        }
        //test
//        printOrderProperties();
    }


    @Subscribe("editorBufferedCheckbox")
    protected void onEditorBufferedCheckboxValueChange(AbstractField.ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        if (!event.isFromClient()) {
            return;
        }

        if (childrenDataGrid.getEditor().isOpen()) {
            notifications.create("Please close Editor before changing its mode")
                    .withType(Notifications.Type.WARNING)
                    .withCloseable(false)
                    .show();

            event.getSource().setValue(event.getOldValue());
        } else {
            childrenDataGrid.getEditor().setBuffered(event.getValue());

            childrenDataGrid.getColumnByKey("bufferedEditorColumn").setVisible(event.getValue());
            childrenDataGrid.getColumnByKey("nonBufferedEditorColumn").setVisible(!event.getValue());
        }
    }

    @Subscribe("saveAndCloseBtn")
    public void onSaveAndCloseBtnClick(final ClickEvent<JmixButton> event) {
        log.info("onSaveAndCloseBtnClick");
    }

    @Subscribe
    public void onAfterSave(final AfterSaveEvent event) {
            for (Child child : childrenDc.getItems()){
                //set parent id ở đây hoặc lúc sinh bản ghi mới là đã map
                child.setParent(Long.valueOf(idField.getValue().replace(",", "")));
                dataManager.save(child);
            }
    }

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        log.info("onBeforeSave");
    }
}