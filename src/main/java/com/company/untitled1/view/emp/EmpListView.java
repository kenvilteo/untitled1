package com.company.untitled1.view.emp;

import com.company.untitled1.entity.Emp;

import com.company.untitled1.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "emps", layout = MainView.class)
@ViewController("Emp.list")
@ViewDescriptor("emp-list-view.xml")
@LookupComponent("empsDataGrid")
@DialogMode(width = "64em")
public class EmpListView extends StandardListView<Emp> {
}