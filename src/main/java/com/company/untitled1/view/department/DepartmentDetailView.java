package com.company.untitled1.view.department;

import com.company.untitled1.entity.Department;

import com.company.untitled1.view.main.MainView;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.view.*;

@Route(value = "departments/:id", layout = MainView.class)
@ViewController("Department.detail")
@ViewDescriptor("department-detail-view.xml")
@EditedEntityContainer("departmentDc")
public class DepartmentDetailView extends StandardDetailView<Department> {
    @ViewComponent
    private TypedTextField<String> nameField;
    @ViewComponent
    private HorizontalLayout detailActions;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<Department> event) {
//        nameField.setReadOnly(true);
         nameField.setValue(detailActions.getElement().getText());
//         nameField.setVisible(false);
    }
}