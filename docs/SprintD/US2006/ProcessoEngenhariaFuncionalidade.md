# US2006: As Project Manager, I want the communications made through the SPOMS2022 protocol to be secured/protected.
=======================================

# 1. Requirements

**US2006**: As Project Manager, I want the communications made through the SPOMS2022 protocol to be secured/protected.
		
*R1: The Dashboard will be presented like a web page. 
	
*R2: The web page will automaticaly update without refreshing the page.

*R3: Will only be in localhost mode. 

### Client clarifications 

* Q1: Regarding this US it is said that "It must be used the provided application protocol (SPOMS2022). The dashboard is intended to be displayed on a web page provided by an existing HTTP server in the ""BackOfficeApp"" application (...)".

Our question is, between what applications should the SPOMS protocol be implemented? Should the HTTP server be part of the "BackOfficeApp" and communicate with the AGV Manager using the REQUESTS_API? Or should the HTTP server be its own application and communicate only with the "BackOfficeApp", which on the other hand communicates directly with the database?
  
* A1: As it is being said the "HTTP Server" is part of the "Backoffice Application" in order to provide a local web page only. As so, the "HTTP Server" is a component of the "Backoffice Application".

However, a question stands out: where the data to be presented by the "HTTP server" comes from?

The "backoffice Application" (or one of its components) must, somehow, get the data from the "AGV Manager" (and/or the system database).

In addition, you should notice that the SPOMS2002 protocol relies on the TCP and not on HTTP.

* Q2: Question 2:Despite in the provided sprint user stories asking for the digital twin in a web dashboard along with its status and position, in user stories of the next sprint it is said that the development of the movement of the AGV is needed which causes a minor confusion. My question is in this sprint is it required to create the movement of the AGV?

* A2: Further, on sprint D, when simulating the AGV movement the AGV position will change and, therefore, we will be able to see the AGVs position changing accordingly on the web dashboard. 

Notice that, this is an integrative project developed following an iterative and incremental process. So, at the end, the pieces need to fit well together.

* Q3: How would you like the dashboard to look? A simple list of the AGVS along with its position and status?

* A3: No! Preferably, the dashboard should be an approximation to what is depicted on Figure 8 of the specifications document.


# 2. Analysis 

# 1.Actor #
* Project Manager/Warehouse Employee

# 2.Acceptance Criteria #
* It should be adopted SSL/TLS with mutual authentication based on public key certificates.
* It complements the US2005.
* It needs to be updated automaticaly
* It needs to have a map of Agv Positions 

# 3.Relevant business aspects #
* Position of AGV needs to be updated automatically


# 4.Funcionalidade #
* Create a dashboard with information of an AGV that can be updated automatically and gets its info from database.


 

