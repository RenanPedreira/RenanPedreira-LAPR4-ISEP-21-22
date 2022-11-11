package eapli.base.warehousemanagement.factory;

import eapli.base.agvmanagement.domain.*;
import eapli.base.common.domain.model.Description;
import eapli.base.common.domain.model.Identifier;
import eapli.base.ordermanagement.domain.Ordre;

import eapli.base.warehousemanagement.domain.AgvDock.AgvDock;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDockId;
import eapli.base.warehousemanagement.domain.Aisle.Accessibility;
import eapli.base.warehousemanagement.domain.Aisle.Square;
import eapli.base.warehousemanagement.domain.Assignment.Assignment;
import eapli.base.warehousemanagement.domain.Assignment.AssignmentStatus;
import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.domain.Warehouse.WarehouseName;
import eapli.base.warehousemanagement.domain.Warehouse.WarehousePlant;
import org.junit.Test;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentFactoryTest {
/*
    private static final Identifier id = new Identifier("CU-123456789");
    private static final Identifier id1 = new Identifier("AGV-123456789");
    private static final AssignmentStatus status = AssignmentStatus.DOING;
    private static final Ordre order = new Ordre(null, null, null, null, null, null, null);
    private static final AutonomyStatus aStatus = new AutonomyStatus(Duration.ZERO);
    private static final Model model = new Model("A69");
    private static final TaskStatus tStatus = TaskStatus.ASSIGNED;
    private static final Weight weight = new Weight(1000);
    private static final Description technicalDescription1 = new Description("SHORT_DESCRIPTION","ola");
    private static final AgvDockId agvDockId = new AgvDockId("123");
    private static final Square begin = new Square(5, 1);
    private static final Square end = new Square(2, 3);
    private static final Square depth = new Square(3, 8);
    private static final Accessibility accessibility = new Accessibility("w+");
    private static final WarehouseName warehouseName = new WarehouseName("Warehouse 1");
    private static final WarehousePlant warehousePlant = new WarehousePlant("warehouse example 1", 12, 32, 1, "cm");
    private static final Warehouse warehouse = new Warehouse(warehouseName, warehousePlant);
    private static final AgvDock agvDock = new AgvDock(agvDockId, begin, end, depth, accessibility, warehouse);
    private static final AGV agv = new AGV(id1,model,technicalDescription1,tStatus,aStatus,agvDock,weight);

    @Test
    public void ensureBuildAss() {
        Assignment ass = new AssignmentFactory().agvy(agv).idy(id).ordrey(order).statusy(status).build();
        assertNotNull(ass);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveOrder()  {

        new AssignmentFactory().agvy(agv).idy(id).ordrey(null).statusy(status).build();

    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveAgv()  {

        new AssignmentFactory().agvy(null).idy(id).ordrey(order).statusy(status).build();

    }
    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveId()  {

        new AssignmentFactory().agvy(agv).idy(null).ordrey(order).statusy(status).build();

    }
    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveStatus()  {

        new AssignmentFactory().agvy(agv).idy(id).ordrey(order).statusy(null).build();

    }
*/

}