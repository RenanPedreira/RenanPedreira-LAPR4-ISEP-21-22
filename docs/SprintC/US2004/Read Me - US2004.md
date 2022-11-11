## <b>US 2004 - Update Order Status after preparation by the AGV</b>
</br>

### <b>1. Requirements Engineering</b>
</br>

#### <b>1.A. Complete Format</b>
</br>

<b>Main Actor</b>
<p>&ensp;&ensp;&ensp;&ensp;Warehouse Employee</p>
</br>

<b>Interested Actors and their respective interests</b>
<p>&ensp;&ensp;&ensp;&ensp;<b>Warehouse Employee:</b> intends to update the status of any Order already prepared by any AGV.</p>
<p>&ensp;&ensp;&ensp;&ensp;<b>SPOMS:</b> intends to have the prepared Orders dispatched in order to be delivered to their respective Customers.</p>
<p>&ensp;&ensp;&ensp;&ensp;<b>Customer:</b> intends to receive their placed Order, thus requires that said Order be dispatched.</p>
<br>

<b>Preconditions</b>
<p>&ensp;&ensp;&ensp;&ensp;It is required that at least a single Order be placed and its Status be "Preparation".</p>
</br>

<b>Postconditions</b>
<p>&ensp;&ensp;&ensp;&ensp;The Status of the selected Orders should be update to "Dispatchment".</p>
</br>

<b>Main Scenario</b>
<ol>
    <li>The Warehouse Employee initializes the process of updating the Status;</li>
    <li>The System presents a list of Orders to the Warehouse Employee whose Status are "Preparation"</li>
    <li>The System asks the Warehouse Employee to choose Orders whose Status will be updated to "Dispatchment";</li>
    <li>The Warehouse Employee selects the Orders whose Status are to be updated;</li>
    <li>The System updates the Status of the selected Orders and informs the Warehouse Employee of the operation success;</li>
</ol>
</br>

<b>Alternative Scenarios</b>
<p>&ensp;&ensp;&ensp;&ensp;*a. The Warehouse Employee cancels the process of updating the Status of Orders whose status is "Preparation";</p>

> <p>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;The Use Case terminates;</p>
</br>

<p>&ensp;&ensp;&ensp;&ensp;4.a. The Warehouse Employee doesn't select any Order to have its Status updated;</p>

> <p>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;1. The System notifies the Warehouse Employee of the previously mentioned situation;</p>
> <p>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;2. The System allows the Warehouse Employee to select Orders whose Status will be updated (step #3);</p>
><p>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;2.a. The Warehouse Employee doesn't select any Order to have its Status updated;</p>
><p>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;2.b. The Use Case terminates;</p>
</br>

<b>Special Requirements</b>
<p>&ensp;&ensp;&ensp;&ensp;N/A</p>
</br>

<b>List of Variations of Technology and Data</b>
<p>&ensp;&ensp;&ensp;&ensp;N/A</p>
</br>

<b>Frequency of Occurence</b>
<p>&ensp;&ensp;&ensp;&ensp;N/A</p>
</br>

### <b>2. Design</b>
</br>

#### <b>3.A. Rationale</b>
</br>

| Main Scenario | Question: Which class... | <div style="width:200px">Answer</div> | Pattern - Justification |
|:--------------:|:-------------------------:|:-------:|:--------------:|
| 1.&ensp;&ensp;The Warehouse Employee initializes the process of updating the Status; |  <p>A. Interacts with the Warehouse Employee?</p><p>B. Coordinates the Use Case?</p><p>C. Interacts with the Domain Layer?</p> | <p>A. OrderDispatchmentUI</p><p>B. OrderDispatchmentController</p><p>C. OrderDispatchmentService</p> | <p>A. Pure Fabrication - Class that doesn't represent a concept captured in the Domain Model but is necessary to achieve low coupling and high cohesion;</p><p>B. Controller - Class responsible for receiving or handling System events;</p><p>C. Controller-Service - Class responsible for processing business logic;</p> |
| 2.&ensp;&ensp;The System presents a list of Orders to the Warehouse Employee whose Status are "Preparation" | <p>A. Knows the Status associated to an instance of Order?</p><p>B. Retrieves the list of Orders?</p><p>C. Creates an instance of OrderRepository?</p><p>D. Manages instance of RepositoryFactory?</p><p>E. Contains data related to an instance of Order and is useable by the Application and Domain Layer?</p><p>F. Converts an instance of Order to an instance of OrderDTO?</p> | <p>A. Order</p><p>B. OrderRepository</p><p>C. RepositoryFactory</p><p>D. PersistenceContext</p><p>E. OrderDTO</p><p>F. OrderMapper</p> | <p>A. Information Expert  - Class that has the information necessary to fulfill the responsibility;</p><p>B. Repository - Abstraction of the Data Layer which centralises the handling of the Domain Objects;</p><p>C. Abstract Factory - Interface responsible for creating a Factory of related Objects without explicitly specifying the intend Class;</p><p>D. Information Expert  - Class that has the information necessary to fulfill the responsibility;</p><p>E. DTO - Class that encapsulates and aggregates for transfer;</p><p>F. DTO - Class responsible of mapping data from Domain Entities to DTOs;</p> |
| 3.&ensp;&ensp;The System asks the Warehouse Employee to choose Orders whose Status will be updated to "Dispatchment"; | | | |
| 4.&ensp;&ensp;The Warehouse Employee selects the Orders whose Status are to be updated; | | | |
| 5.&ensp;&ensp;The System updates the Status of the selected Orders; | <p>A. Updates the Status of the Order instance?</p><p>B. Updates in the database the Order instance in question?</p> | <p>A. Order</p><p>B. OrderRepository</p> | <p>A. Information Expert  - Class that has the information necessary to fulfill the responsibility;</p><p>B. Repository - Abstraction of the Data Layer which centralises the handling of the Domain Objects;</p> |
</br>

#### <b>2.B. Systematization</b>
</br>

<p>From the Rationale, the following Conceptual Classes are promoted to Software Classes:</p>

<ul>
    <li>Order;</li>
</ul>
</br>

<p>Other Software Classes identified:</p>
<ul>
    <li>OrderDispatchmentUI;</li>
    <li>OrderDispatchmentController;</li>
    <li>OrderDispatchmentService;</li>
    <li>PersistenceContext;</li>
    <li>RepositoryFactory;</li>
    <li>OrderRepository;</li>
    <li>OrderMapper;</li>
    <li>OrderDTO;</li>
</ul>
</br>

#### <b>2.C. Sequence Diagram</b>
</br>

![SD-US2004.png](SD-US2004.png)
</br>
</br>

#### <b>2.D. Test Planning</b>
</br>

<p>This section contains primary Unit Tests developped in order to affer the satisfaction of the User Stories requisites;</p>
</br>

| Tested Class | Test Objective | <div style="width:450px">Implementation</div> |
|:--------------|:-------------------------|:------------------------|
| Order | Verify that the method "changeStatusToDispatchment()" changes the current Status associated to an Order instance to the Status "Dispatchment"; | <p>@Test</br>public void ensureOrderStatusIsChangedToDispacthment() {</br>&ensp;&ensp;Status expRes = new Status("Dispatchment");</br></br>&ensp;&ensp;order.changeStatusToDispatchment();</br>&ensp;&ensp;assertEquals(expRes, order.currentStatus());</br>}</p> |
| OrderDispatchmentService | Verify that the method "listAllPreparedOrders()" returns solely Orders whose Status are "Preparation"; | <p>@Test</br>public void ensureListOnlyContainsPreparedOrders()  {</br>&ensp;&ensp;for (Order order : service.listAllPreparedOrders())</br>&ensp;&ensp;&ensp;&ensp;assertTrue(!order.currentStatus().equals(new Status("Preparation")));</br>} |
|