package com.company.untitled1.view.gdsector;

import com.company.untitled1.entity.GdSector;

import com.company.untitled1.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.data.Sequence;
import io.jmix.data.Sequences;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "gdSectors/:id", layout = MainView.class)
@ViewController("GdSector.detail")
@ViewDescriptor("gd-sector-detail-view.xml")
@EditedEntityContainer("gdSectorDc")
public class GdSectorDetailView extends StandardDetailView<GdSector> {
    private static final Logger log = LoggerFactory.getLogger(GdSector.class);
    @ViewComponent
    private TypedTextField<String> sectorCodeField;
    @ViewComponent
    private TypedTextField<String> deletedField;

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        deletedField.setValue("N");
    }
    @Autowired
    private Sequences sequences;
    @Subscribe
    public void onInitEntity(final InitEntityEvent<GdSector> event) {
        long number = sequences.createNextValue(Sequence.withName("seq_id_gdsector"));
        GdSector gdSector = event.getEntity();
        gdSector.setSectorCode(Long.toString(number));
        //thử
    }
}