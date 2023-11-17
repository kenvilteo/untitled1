package com.company.untitled1.view.step;

import com.company.untitled1.entity.Step;

import com.company.untitled1.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "steps", layout = MainView.class)
@ViewController("Step.list")
@ViewDescriptor("step-list-view.xml")
@LookupComponent("stepsDataGrid")
@DialogMode(width = "64em")
public class StepListView extends StandardListView<Step> {
}