package com.company.untitled1.view.user;

import com.company.untitled1.entity.User;
import com.company.untitled1.view.main.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "users", layout = MainView.class)
@ViewController("User.list")
@ViewDescriptor("user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {
    @ViewComponent
    private DataGrid<User> usersDataGrid;
    @Autowired
    private UiComponents uiComponents;

    @Subscribe
    public void onInit(final InitEvent event) {
        Grid.Column<User> pictureColumn = usersDataGrid.addComponentColumn(user -> {
            FileRef fileRef = user.getPicture();
            if (fileRef != null) {
                Image image = uiComponents.create(Image.class);
                image.setWidth("30px");
                image.setHeight("30px");
                StreamResource streamResource = new StreamResource(
                        fileRef.getFileName(),
                        () -> fileStorage.openStream(fileRef));
                image.setSrc(streamResource);
                image.setClassName("user-picture");

                return image;
            } else {
                return new Span();
            }
        });
        pictureColumn.setFlexGrow(0);
        pictureColumn.setWidth("40px");
        usersDataGrid.setColumnPosition(pictureColumn, 0);
    }
    @Autowired
    private FileStorage fileStorage;
}