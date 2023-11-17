package com.company.untitled1.view.child;

import com.company.untitled1.entity.Child;

import com.company.untitled1.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "children/:id", layout = MainView.class)
@ViewController("Child.detail")
@ViewDescriptor("child-detail-view.xml")
@EditedEntityContainer("childDc")
public class ChildDetailView extends StandardDetailView<Child> {
}