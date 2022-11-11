# US4002: "AGVManager" component is enhanced with a basic FIFO algorithm to automatically assign tasks to AGVs
=======================================

# 1. Requirements

**US4002**: As Project Manager, I want that the "AGVManager" component is enhanced with a basic FIFO algorithm to automatically assign tasks to AGVs.

### Client clarifications 
* Q1: Will the FIFO algorithm be used to control the tasks/orders that are waiting for an available AGV? 
  
* A1: The general idea is that product orders reaching a certain state whose meaning is of "need to be prepared by an AGV" are added to a queue. Then, following the FIFO algorithm orders are removed from the queue and assigned to available AGVs capable of performing the task that such order implies.

* Q2: Talking about being automatic, the System executes this functionality after some other functionality, or executes it periodically? If it is periodically, how often?

* A2: Teams are free to propose a solution for that problem/issue. Notice that all team decisions must be well supported in light of business need and technical constraints.

* Q3: In US4002 it is required that the AGV Manager should support automatic assignment of orders to AGVs. In US2003 the Warehouse Employee will be able to assign any order to an AGV available. If the orders are being automatically assigned to an AGV (US4002) how can the Warehouse Employee assign a specific order to an AGV? 

* A3: Usually, and by default, one intends that system automatically assigns orders to AGVs (US 4002). However, if such option is not available (e.g.: turned off) or by some reason an order needs to be prepared faster than it would normally be, the warehouse employee has the ability to assign tasks manually (US 2003). Notice that, orders that can be prepared by AGVs are being added to a queue following a FIFO algorithm (part of the US 4002). In the scope of US 2003 the FIFO algorithm does not apply... the employee might choose the order (s)he wants.
 

# 2. Analysis 

# 1.Actor #
* System

# 2.Acceptance Criteria #
* No acceptance Criteria

# 3.Relevant business aspects #
* The system will execute this functionality periodically.
* Must use FIFO algorithm.
* The system will execute this functionality automatically.

# 4.Funcionalidade #
* Esta US não deve ser vista como caso de uso onde há um ator e uma UI para realizar a mesma, assim, a realização da US será excutada pelo sistema em background(Daemon Thread).

# 5.Testing Planning #

*@Test
    public void ensureCompleteAss() {
        new Assignment(id,agv,status,order);
    }

*@Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveOrder() {
        new Assignment(id,agv,status,null);
    }

* @Test(expected = IllegalArgumentException.class)
    public void ensureIdNotBeIncorrectLetters() {
        Identifier id2 = new Identifier("CUI-123456789");
        new Assignment(id2,agv,status,order);
    }

* @Test
    public void ensureBuildAss() {
        Assignment ass = new AssignmentFactory().agvy(agv).idy(id).ordrey(order).statusy(status).build();
        assertNotNull(ass);
    }

*@Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveOrder()  {

        new AssignmentFactory().agvy(agv).idy(id).ordrey(null).statusy(status).build();

    }
