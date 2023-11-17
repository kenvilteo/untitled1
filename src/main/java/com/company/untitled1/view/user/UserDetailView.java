package com.company.untitled1.view.user;

import com.company.untitled1.entity.OnboardingStatus;
import com.company.untitled1.entity.Step;
import com.company.untitled1.entity.User;
import com.company.untitled1.entity.UserStep;
import com.company.untitled1.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.EntityStates;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;



@Route(value = "users/:id", layout = MainView.class)
@ViewController("User.detail")
@ViewDescriptor("user-detail-view.xml")
@EditedEntityContainer("userDc")
public class UserDetailView extends StandardDetailView<User> {

    @ViewComponent
    private TypedTextField<String> usernameField;
    @ViewComponent
    private PasswordField passwordField;
    @ViewComponent
    private PasswordField confirmPasswordField;
    @ViewComponent
    private ComboBox<String> timeZoneField;

    @Autowired
    private EntityStates entityStates;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @ViewComponent
    private CollectionPropertyContainer<UserStep> stepsDc;
    @ViewComponent
    private DataContext dataContext;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;
    @Autowired
    private UiComponents uiComponents;
    @ViewComponent
    private DataGrid<UserStep> stepsDataGrid;

    @Subscribe
    public void onInit(final InitEvent event) {
        timeZoneField.setItems(List.of(TimeZone.getAvailableIDs()));

        Grid.Column<UserStep> completedColumn = stepsDataGrid.addComponentColumn(userStep -> {
            JmixCheckbox checkbox = uiComponents.create(JmixCheckbox.class);
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
        stepsDataGrid.setColumnPosition(completedColumn, 0);
    }

    @Subscribe
    public void onInitEntity(final InitEntityEvent<User> event) {
        usernameField.setReadOnly(false);
        passwordField.setVisible(true);
        confirmPasswordField.setVisible(true);
        User user = event.getEntity();
        user.setOnboardingStatus(OnboardingStatus.NOT_STARTED);
    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            usernameField.focus();
        }
    }

    @Subscribe
    public void onValidation(final ValidationEvent event) {
        if (entityStates.isNew(getEditedEntity())
                && !Objects.equals(passwordField.getValue(), confirmPasswordField.getValue())) {
            event.getErrors().add(messageBundle.getMessage("passwordsDoNotMatch"));
        }
    }

    @Subscribe
    protected void onBeforeSave(final BeforeSaveEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            getEditedEntity().setPassword(passwordEncoder.encode(passwordField.getValue()));
        }
    }

    @Subscribe("generateButton")
    public void onGenerateButtonClick(final ClickEvent<JmixButton> event) {
        User user = getEditedEntity();

        if (user.getJoiningDate() == null) {
            notifications.show("Cannot generate steps for user without 'Joining date'");
            return;
        }

        List<Step> steps = dataManager.load(Step.class)
                .query("select s from Step s order by s.sortValue asc")
                .list();

        for (Step step : steps) {
            if (stepsDc.getItems().stream().noneMatch(userStep ->
                    userStep.getStep().equals(step))) {
                UserStep userStep = dataContext.create(UserStep.class);
                userStep.setUser(user);
                userStep.setStep(step);
                userStep.setDueDate(user.getJoiningDate().plusDays(step.getDuration()));
                userStep.setSortValue(step.getSortValue());
                stepsDc.getMutableItems().add(userStep);
            }
        }
    }

//    @Install(to = "stepsDataGrid.completed", subject = "columnGenerator")
//    private Component stepsDataGridCompletedColumnGenerator(UserStep userStep) {
//        CheckBox checkBox = uiComponents.create(CheckBox.class);
//        checkBox.setValue(userStep.getCompletedDate() != null);
//        checkBox.addValueChangeListener(e -> {
//            if (userStep.getCompletedDate() == null) {
//                userStep.setCompletedDate(LocalDate.now());
//            } else {
//                userStep.setCompletedDate(null);
//            }
//        });
//        return checkBox;
//    }
}