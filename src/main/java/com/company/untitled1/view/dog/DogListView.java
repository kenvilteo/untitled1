package com.company.untitled1.view.dog;

import com.company.untitled1.entity.Dog;
import com.company.untitled1.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.action.list.CreateAction;
import io.jmix.flowui.action.list.EditAction;
import io.jmix.flowui.action.list.RemoveAction;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route(value = "dogs", layout = MainView.class)
@ViewController("Dog.list")
@ViewDescriptor("dog-list-view.xml")
@LookupComponent("dogsDataGrid")
@DialogMode(width = "64em")
public class DogListView extends StandardListView<Dog> {

    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private CollectionContainer<Dog> dogsDc;
    @ViewComponent
    private CollectionLoader<Dog> dogsDl;
    @ViewComponent
    private DataGrid<Dog> dogsDataGrid;
    @ViewComponent("dogsDataGrid.create")
    private CreateAction<Dog> dogsDataGridCreate;
    @ViewComponent("dogsDataGrid.remove")
    private RemoveAction<Dog> dogsDataGridRemove;
    @ViewComponent("dogsDataGrid.edit")
    private EditAction<Dog> dogsDataGridEdit;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        Logger logz = LoggerFactory.getLogger(BeforeShowEvent.class);
        logz.info(event.toString());
//        dogsDataGridEdit.

    }

}