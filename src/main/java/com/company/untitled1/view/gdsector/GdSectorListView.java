package com.company.untitled1.view.gdsector;

import com.company.untitled1.entity.GdSector;
import com.company.untitled1.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.action.list.RemoveAction;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;

import java.util.Objects;

@Route(value = "gdSectors", layout = MainView.class)
@ViewController("GdSector.list")
@ViewDescriptor("gd-sector-list-view.xml")
@LookupComponent("gdSectorsDataGrid")
@DialogMode(width = "64em")
public class GdSectorListView extends StandardListView<GdSector> {

    @ViewComponent("gdSectorsDataGrid.remove")
    private RemoveAction<GdSector> gdSectorsDataGridRemove;

    @ViewComponent
    private DataGrid<GdSector> gdSectorsDataGrid;

    @Subscribe("removeBtn")
    public void onRemoveBtnClick(final ClickEvent<JmixButton> event) {
        Objects.requireNonNull(gdSectorsDataGrid.getSingleSelectedItem()).setDeleted("Y");
    }

//    @Subscribe("gdSectorsDataGrid.remove")
//    public void onGdSectorsDataGridRemove(final ActionPerformedEvent event) {
//        Objects.requireNonNull(gdSectorsDataGrid.getSingleSelectedItem()).setDeleted("Y");
//    }



//    @Install(to = "gdSectorsDataGrid.remove", subject = "afterActionPerformedHandler")
//    private void gdSectorsDataGridRemoveAfterActionPerformedHandler(final RemoveOperation.AfterActionPerformedEvent<GdSector> afterActionPerformedEvent) {
//        Objects.requireNonNull(gdSectorsDataGrid.getSingleSelectedItem()).setDeleted("Y");
//        gdSectorsDataGridRemove.execute();
//
//    }
}