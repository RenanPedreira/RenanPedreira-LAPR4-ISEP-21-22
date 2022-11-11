# US5100: As Project Manager, I want that the team to develop and integrate the others components/parts of the AGV digital twin (e.g.: movement, obstacle sensors, control unit).
=======================================

# 1. Requirements

**US5100**: As Project Manager, I want that the team to develop and integrate the others components/parts of the AGV digital twin (e.g.: movement, obstacle sensors, control unit).

### Client clarifications 
* Q1: Referring to the documentation, it is mentioned that the Route Planner module of the AGV Digital twin is responsible for "... (re)computing routes based on a source and target location on thewarehouse considering the warehouse plant only. It is worth notifying that AGV can only movehorizontally or vertically".

What do you mean by source and target location of agv ? We can set agvdock as a starting point but what would be the end point?

* A1: When assigning a task to an AGV, the AGV knows which products to collect, right?

So, the source location (starting point) is the position where the AGV is at that moment (as you said, it might be the AGV dock)

The target location (end point) might be the location of a product.

However,  there are other possibilities.

For instance, consider the scenario where the AGV has to collect 2 products (say A and B).

At least three routes have to be computed:

1. From AGV Dock location to the location of product A.

2. From location of product A to the location of product B.

3. From location of product B to the AGV dock location.

* Q2: How should we measure the charging of an AGV should it have a certain percentage per minute or per hour?

* A2: You should adopt "seconds" as time unit.

* Q3: Could you specify at what percentage the discharging of the AGV happens and if it is affected by other factors other than being turned on?

* A3: As stated on the specifications' document: "when the AGV is moving battery consumption might be computed based on the travelled distance, but when the AGV is waiting/stopped on its dock battery consumption might be computed based on time.". Other factors might be considered, but at this stage, I recommend you to not apply a complex algorithm.

* Q4: What is the difference between having one or two sensors, in each corner? How do we differentiate between them? Are they in the same position?

* A4: Each sensor is a source of information to signal (or not) the presence of an obstacle (e.g.: another AGV, an aisle, etc.). In each corner there is a sensor to evaluate obstacles in the front and another sensor  to evaluate obstacles on the side.

* Q5: Would all these AGV Digital Twin instances share information about the different modules, to be aware of the presence of other moving agvs? 

* A5: It is NOT foreseen that AGVs communicate between them. However, each AGV can by its own initiative communicate with the AGV Manager. Notice that in your picture this possibility is not depicted. However, this possibility is depicted on Figure 2 of the specifications' document.

* Q6: At the level of representing the movement of agvs on the dashboard, developed in Sprint 3, how would the information about the movement of an agv be sent to the dashboard, for the dashboard to be able to demonstrate?

* A6: Your question is related to data/information flow. Such flow must be in accordance with Figure 2 of the specifications' document. Notice that, the dashboard shows the current position of each AGV. If the AGVs position is changing then by refreshing the dashboard is enough to show AGV movement. 

* Adicional Considerations P1: You have to notice that the overall idea is to simulate a real AGV. As so, you might start by applying basic algorithms for every AGV functions (e.g.: moving, charging/discharging battery). Probably, you might need some input information to apply such algorithms (e.g.: min, max and average speed). Such information might vary from one AGV to another, for instance, based on the AGV model.

* Adicional Considerations P2: - Choose a speed that allows to see the movement of the AGV on the screen;
- You should define your charging and discharging model. A simplified linear model is perfectly adequate for the problem.
- Define your charging and discharging parameters according to your model.


# 2. Analysis 

# 1.Actor #
* System

# 2.Acceptance Criteria #
* In conformity with SCOMP guidelines.

# 3.Relevant business aspects #
* 

# 4.Funcionalidade #
* Create several modules for the "AGV TWIN" 
* Communications module
* Control Unit module
* Sensor module
* Positioning module
* Route Planner module
* Battery Management module
* Simulation engine
