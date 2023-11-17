package com.company.untitled1.view.dog;

import com.company.untitled1.entity.Dog;
import com.company.untitled1.entity.DogShirt;
import com.company.untitled1.view.branch.BranchDetailView;
import com.company.untitled1.view.main.MainView;
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.metamodel.datatype.Datatype;
import io.jmix.core.metamodel.datatype.Enumeration;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.core.metamodel.model.MetaProperty;
import io.jmix.core.metamodel.model.Range;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "dogs/:id", layout = MainView.class)
@ViewController("Dog.detail")
@ViewDescriptor("dog-detail-view.xml")
@EditedEntityContainer("dogDc")
public class DogDetailView extends StandardDetailView<Dog> {

    @Subscribe("removeBtn")
    public void onRemoveBtnClick(final ClickEvent<JmixButton> event) {
        
    }
    @ViewComponent
    private TypedTextField<String> deletedField;
    @ViewComponent
    private DataGrid<DogShirt> shirtDataGrid;

    @ViewComponent
    private CollectionPropertyContainer<DogShirt> shirtDc;
    @Autowired
    private Notifications notifications;
    @ViewComponent
    private TypedTextField<Long> idField;
    @ViewComponent
    private TypedTextField<String> namePetField;
    @Autowired
    private DataManager dataManager;


    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        deletedField.setValue("N");
    }

    protected void showNotification(String message) {
        notifications.create(message)
                .withType(Notifications.Type.WARNING)
                .withCloseable(false)
                .show();
    }
    private static final Logger log = LoggerFactory.getLogger(BranchDetailView.class);

    @Subscribe("shirtDataGrid.create")
    protected void onCustomersDataGridCreate(ActionPerformedEvent event) {
        if (shirtDataGrid.getEditor().isOpen()) {
            showNotification("Close the editor before creating a new entity");
        } else {
            DogShirt newShirt = metadata.create(DogShirt.class);
            Dog dog = metadata.create(Dog.class);
            log.info(idField.getValue().replace(",", ""));
            Long res = Long.valueOf(idField.getValue().replace(",", ""));
            dog.setId(res);
            dog.setNamePet(namePetField.getValue());
            newShirt.setDog(dog);
//            newShirt.setName("test");
//            newShirt.setPrice(11111L);
            shirtDc.getMutableItems().add(newShirt);
            shirtDataGrid.select(newShirt);
            shirtDataGrid.getEditor().editItem(newShirt);
        }
        //test
        printOrderProperties();
    }

    @Subscribe("editorBufferedCheckbox")
    protected void onEditorBufferedCheckboxValueChange(ComponentValueChangeEvent<JmixCheckbox, Boolean> event) {
        if (!event.isFromClient()) {
            return;
        }

        if (shirtDataGrid.getEditor().isOpen()) {
            notifications.create("Please close Editor before changing its mode")
                    .withType(Notifications.Type.WARNING)
                    .withCloseable(false)
                    .show();

            event.getSource().setValue(event.getOldValue());
        } else {
            shirtDataGrid.getEditor().setBuffered(event.getValue());

            shirtDataGrid.getColumnByKey("bufferedEditorColumn").setVisible(event.getValue());
            shirtDataGrid.getColumnByKey("nonBufferedEditorColumn").setVisible(!event.getValue());
        }
    }

    @Subscribe("saveAndCloseBtn")
    public void onSaveAndCloseBtnClick(final ClickEvent<JmixButton> event) {
//        dataManager.save();
    }



    @Autowired
    private Metadata metadata;

    public void printOrderProperties() {
        MetaClass metaClass = metadata.getClass(Dog.class);
        for (MetaProperty metaProperty : metaClass.getProperties()) {

            String propertyName = metaProperty.getName();
            MetaProperty.Type propertyType = metaProperty.getType();
            Class<?> javaType = metaProperty.getJavaType();
            Range propertyRange = metaProperty.getRange();

            String info = "name: " + propertyName +
                    "\n type: " + propertyType +
                    "\n Java type: " + javaType +
                    "\n range: " + propertyRange;

            if (propertyRange.isClass()) {
                MetaClass refMetaClass = propertyRange.asClass();
                Range.Cardinality cardinality = propertyRange.getCardinality();
                info += "\n reference to: " + refMetaClass;
                info += "\n cardinality: " + cardinality;

            } else if (propertyRange.isEnum()) {
                Enumeration<?> enumeration = propertyRange.asEnumeration();
                info += "\n enum: " + enumeration;

            } else if (propertyRange.isDatatype()) {
                Datatype<Object> propertyDatatype = propertyRange.asDatatype();
                info += "\n data type: " + propertyDatatype;
            }

            log.info(info);
        }
    }

//    @Subscribe("testAction")
//    public void onTestAction(final ActionPerformedEvent event) {
//        notifications.show("Hello");
//    }
}