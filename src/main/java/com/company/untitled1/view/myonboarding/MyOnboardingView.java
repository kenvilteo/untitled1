package com.company.untitled1.view.myonboarding;


import com.company.untitled1.entity.User;
import com.company.untitled1.entity.UserStep;
import com.company.untitled1.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Route(value = "MyOnboardingView", layout = MainView.class)
@ViewController("MyOnboardingView")
@ViewDescriptor("my-onboarding-view.xml")
public class MyOnboardingView extends StandardView {
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @ViewComponent
    private CollectionLoader<UserStep> userStepsDl;
    @Autowired
    private UiComponents uiComponents;
    @ViewComponent
    private DataGrid<UserStep> userStepsDataGrid;
    @ViewComponent
    private CollectionContainer<UserStep> userStepsDc;
    @ViewComponent
    private Span completedStepsLabel;
    @ViewComponent
    private Span overdueStepsLabel;
    @ViewComponent
    private Span totalStepsLabel;
    @ViewComponent
    private DataContext dataContext;

    private void updateLabels() {
        totalStepsLabel.setText("Total steps: " + userStepsDc.getItems().size());

        long completedCount = userStepsDc.getItems().stream()
                .filter(us -> us.getCompletedDate() != null)
                .count();
        completedStepsLabel.setText("Completed steps: " + completedCount);

        long overdueCount = userStepsDc.getItems().stream()
                .filter(us -> isOverdue(us))
                .count();
        overdueStepsLabel.setText("Overdue steps: " + overdueCount);
    }

    @Subscribe(id = "userStepsDc", target = Target.DATA_CONTAINER)
    public void onUserStepsDcItemPropertyChange(final InstanceContainer.ItemPropertyChangeEvent<UserStep> event) {
        updateLabels();
    }

    private boolean isOverdue(UserStep us) {
        return us.getCompletedDate() == null
                && us.getDueDate() != null
                && us.getDueDate().isBefore(LocalDate.now());
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        final User user = (User) currentAuthentication.getUser();
        userStepsDl.setParameter("user", user);
        userStepsDl.load();
        updateLabels();
    }

    @Subscribe
    public void onInit(final InitEvent event) {
        Grid.Column<UserStep> completedColumn = userStepsDataGrid.addComponentColumn(userStep -> {
            Checkbox checkbox = uiComponents.create(Checkbox.class);
            checkbox.setValue(userStep.getCompletedDate() != null);
            checkbox.addValueChangeListener(e -> {
                if (userStep.getCompletedDate() == null) {
                    userStep.setCompletedDate(LocalDate.now());
                } else {
                    userStep.setCompletedDate(null);
                }
            });
            return checkbox;
        });
        completedColumn.setFlexGrow(0);
        completedColumn.setWidth("4em");
        userStepsDataGrid.setColumnPosition(completedColumn, 0);

        //lấy đối tượng ngày đến hạn dueDate
        Grid.Column<UserStep> dueDate = userStepsDataGrid.getColumnByKey("dueDate");
        //set css cho dueDate = "overdue-step"(màu đỏ) nếu đối tượng userStep này quá hạn, nếu ko thì set = null
        dueDate.setPartNameGenerator(userStep ->
                isOverdue(userStep) ? "overdue-step" : null);
//        Logger logz = LoggerFactory.getLogger(CustomUserSynchronizationStrategy.class);
//        logz.info(dueDate.getId().get());
    }

    @Subscribe("saveButton")
    public void onSaveButtonClick(final ClickEvent<JmixButton> event) {
        dataContext.save();
        close(StandardOutcome.SAVE);
    }

    @Subscribe("discardButton")
    public void onDiscardButtonClick(final ClickEvent<JmixButton> event) {
        close(StandardOutcome.DISCARD);
    }


}