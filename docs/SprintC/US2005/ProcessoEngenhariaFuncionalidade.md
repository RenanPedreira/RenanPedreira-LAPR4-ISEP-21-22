# US2005: As Warehouse Employee, I want to open a web dashboard presenting the current status of the AGVs as well as their position in the warehouse layout and keeps updated automatically (e.g.: at each minute).
=======================================

# 1. Requirements

**US2005**: As Warehouse Employee, I want to open a web dashboard presenting the current status of the AGVs as well as their position.

### Client clarifications 
* Q1: Regarding this US it is said that "It must be used the provided application protocol (SPOMS2022). The dashboard is intended to be displayed on a web page provided by an existing HTTP server in the ""BackOfficeApp"" application (...)".

Our question is, between what applications should the SPOMS protocol be implemented? Should the HTTP server be part of the "BackOfficeApp" and communicate with the AGV Manager using the REQUESTS_API? Or should the HTTP server be its own application and communicate only with the "BackOfficeApp", which on the other hand communicates directly with the database?
  
* A1: As it is being said the "HTTP Server" is part of the "Backoffice Application" in order to provide a local web page only. As so, the "HTTP Server" is a component of the "Backoffice Application".

However, a question stands out: where the data to be presented by the "HTTP server" comes from?

The "backoffice Application" (or one of its components) must, somehow, get the data from the "AGV Manager" (and/or the system database).

In addition, you should notice that the SPOMS2002 protocol relies on the TCP and not on HTTP.

There is nothing wrong here.

# 2. Analysis 

# 1.Actor #
* Warehouse Employee

# 2.Acceptance Criteria #
* It must be used the provided application protocol (SPOMS2022).
The dashboard is intended to be displayed on a web page provided by an existing HTTP server in the "BackOfficeApp" application and only available to localhost.
The dashboard web page is kept updated without reloading.

# 3.Relevant business aspects #
* Position of AGV needs to be updated automatically

# 4.Funcionalidade #
* Create a dashboard with information of an AGV that can be updated automatically.

