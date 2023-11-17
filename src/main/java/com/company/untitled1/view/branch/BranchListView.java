package com.company.untitled1.view.branch;

import com.company.untitled1.entity.Branch;
import com.company.untitled1.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "branches", layout = MainView.class)
@ViewController("Branch.list")
@ViewDescriptor("branch-list-view.xml")
@LookupComponent("branchesDataGrid")
@DialogMode(width = "64em")
public class BranchListView extends StandardListView<Branch> {
    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private DataGrid<Branch> branchesDataGrid;
    @Autowired
    private ViewNavigators viewNavigators;
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private CollectionLoader<Branch> branchesDl;
    @Autowired
    private Notifications notifications;

    @Subscribe("removeBtn")
    public void onRemoveBtnClick(final ClickEvent<JmixButton> event) {
        Branch branch = branchesDataGrid.getSingleSelectedItem();
        assert branch != null;
        branch.setDeleted("Y");
    }

    @Subscribe("branchesDataGrid.someAction")
    protected void onSomeActionButtonClick(ActionPerformedEvent event) {
        if (!branchesDataGrid.getSelectedItems().isEmpty()){
            Branch branch = branchesDataGrid.getSingleSelectedItem();
            assert branch != null;
//            viewNavigators.detailView(Branch.class)
//                    .editEntity(branch)
//                    .navigate();
            branch.setDeleted("Y");
            dataManager.remove(branch);
            branchesDl.load();
        }else{
            notifications.show("Chưa chọn bản ghi cần xóa");
        }
    }

}