package com.company.untitled1.view.child;

import com.company.untitled1.entity.Child;

import com.company.untitled1.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "children", layout = MainView.class)
@ViewController("Child.list")
@ViewDescriptor("child-list-view.xml")
@LookupComponent("childrenDataGrid")
@DialogMode(width = "64em")
public class ChildListView extends StandardListView<Child> {
}