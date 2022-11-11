package eapli.base.warehousemanagement.domain.Assignment;

import eapli.base.agvmanagement.domain.*;
import eapli.base.common.domain.model.Description;
import eapli.base.common.domain.model.Identifier;
import eapli.base.ordermanagement.domain.Ordre;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDock;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDockId;
import eapli.base.warehousemanagement.domain.Aisle.Accessibility;
import eapli.base.warehousemanagement.domain.Aisle.Square;
import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.domain.Warehouse.WarehouseName;
import eapli.base.warehousemanagement.domain.Warehouse.WarehousePlant;

import org.junit.Test;


import java.time.Duration;

import static org.junit.Assert.assertNotNull;


public class AssignmentTest {
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
    public void ensureValidAssignment(){
        Assignment assignment = new Assignment(id, agv, status, order);
        assertNotNull(assignment);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveOrder() {
        new Assignment(id,agv,status,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveAGV() {
        new Assignment(id,null,status,order);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveStatus() {
        new Assignment(id,agv,null,order);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveId() {
        new Assignment(null,agv,status,order);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void ensureIdNotExceedingLength() {
        Identifier id2 = new Identifier("CU-1234567890");
        new Assignment(id2,agv,status,order);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureIdNotBelowLength() {
        Identifier id2 = new Identifier("CU-123456789");
        new Assignment(id2,agv,status,order);
    }*/

}