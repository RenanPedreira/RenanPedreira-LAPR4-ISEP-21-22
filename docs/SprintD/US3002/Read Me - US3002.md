## <b>US 3002 - Obtain Statistical Report regarding a Questionnaire</b>
</br>

### <b>1. Requirements Engineering</b>
</br>

#### <b>1.A. Complete Format</b>
</br>

<b>Main Actor</b>
<p>&ensp;&ensp;&ensp;&ensp;Sales Manager</p>
</br>

<b>Interested Actors and their respective interests</b>
<p>&ensp;&ensp;&ensp;&ensp;<b>Sales Manager:</b> intends to obtain Statistical Data regarding a given Questionnaire.</p>
<p>&ensp;&ensp;&ensp;&ensp;<b>SPOMS:</b> intends to have statiscal data regarding a given Questionnaire in order to analyze and obtain a greater perception of consumption trends.</p>
<br>

<b>Preconditions</b>
<p>&ensp;&ensp;&ensp;&ensp;It is required that at least a single Questionnaire be avalaible in the System and answered by a minimum of 30 Customers.</p>
</br>

<b>Postconditions</b>
<p>&ensp;&ensp;&ensp;&ensp;The Statistical Data regarding a given Questionnaire is compiled and stored in generated Excel file.</p>
</br>

<b>Main Scenario</b>
<ol>
    <li>The Sales Manager initializes the process of obtaining Statistical Data regarding a given Questionnaire answered by a minimum of 30 Customers;</li>
    <li>The System presents a list of Questionnaires that have been answered by a minimun of 30 Customers and asks the Sales Manager to select one of the Questionnaires at hand;</li>
    <li>The Sales Manager selects the Questionnaire whose answers are to be compiled and converted to a Statistical Report;</li>
    <li>The System validates all the answers given by the Customers related to the selected Questionnaire;</li>
    <li>The System compiles all the validated answers into Statistical Data and converts said Data into a Statistical Report;</li>
    <li>The System informs the Sales Manager of the operation success;</li>
</ol>
</br>

<b>Alternative Scenarios</b>
<p>&ensp;&ensp;&ensp;&ensp;*a. The Sales Manager cancels the process of compiling a Statistical Report for a given Questionnaire;</p>

> <p>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;The Use Case terminates;</p>
</br>

<p>&ensp;&ensp;&ensp;&ensp;2.a. The System doesn't recover any Questionnaire that has been answered by at minumum of 30 Customers;</p>

> <p>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;The Use Case terminates;</p>
</br>

<p>&ensp;&ensp;&ensp;&ensp;3.a. The Sales Manager doesn't select any of the previously presented Questionnaires;</p>

> <p>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;The Use Case terminates;</p>
</br>

<p>&ensp;&ensp;&ensp;&ensp;4.a. The System detects that a given Answer for a given Question is invalid;</p>

> <p>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;1. The System considers the Answer as an Invalid Answer for Statistical purpose;</p>
> <p>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;2. The System continues the validation of the remaining Answers related to the selected Questionnaire;</p>
</br>

<b>Special Requirements</b>
<p>&ensp;&ensp;&ensp;&ensp;Statistical Report is required to be presented in a Excel file;</p>
</br>

<b>List of Variations of Technology and Data</b>
<p>&ensp;&ensp;&ensp;&ensp;1. Apache POI - Required to generate Statistical Report Excel file;</p>
<p>&ensp;&ensp;&ensp;&ensp;2. ANTLR - Used for the generation of the required Parser;</p>
</br>

<b>Frequency of Occurence</b>
<p>&ensp;&ensp;&ensp;&ensp;N/A</p>
</br>

### <b>2. Design</b>
</br>

#### <b>2.A. Rationale</b>
</br>

| Main Scenario | <div style="width:150px">Question: Which class...</div> | <div style="width:250px">Answer</div> | <div style="width:500px">Pattern - Justification</div> |
|:--------------:|:-------------------------:|:-------:|:--------------:|
| 1.&ensp;&ensp;The Sales Manager initializes the process of obtaining Statistical Data regarding a given Questionnaire answered by a minimum of 30 Customers; | <p>A. Interacts with the Sales Manager?</p><p>B. Coordinates the Use Case?</p><p>C. Interacts with the Domain Layer?</p> | <p>A. ObtainStatisticalReportUI</p><p>B. ObtainStatisticalReportController</p><p></p><p>C. ObtainStatisticalReportService</p> | <p>A. Pure Fabrication - Class that doesn't represent a concept captured in the Domain Model but is necessary to achieve low coupling and high cohesion;</p><p>B. Controller - Class responsible for receiving or handling System events;</p><p>C. Controller-Service - Class responsible for processing business logic;</p> |
| 2.&ensp;&ensp;The System presents a list of Questionnaires that have been answered by a minimun of 30 Customers and asks the Sales Manager to select one of the Questionnaires at hand; | <p>A. Knows how many Customers answered a given Questionnaire?</p><p>B. Retrieves Questionnaire instances that have been answered by at least 30 Customers?</p><p>C. Creates an instance of QuestionnaireRepository?</p><p>D. Manages instance of RepositoryFactory?</p><p>E. Contains data related to an instance of Questionnaire and is useable by the Application and Domain Layer?</p><p>F. Converts an instance of Questionnaire to an instance of QuestionnaireDTO?</p> | <p>A. TargetAudience</p><p>B. QuestionnaireRepository</p><p>C. RepositoryFactory</p><p>D. PersistenceContext</p><p>E. QuestionnaireDTO</p><p>F. QuestionnaireMapper</p> | <p>A. Information Expert  - Class that has the information necessary to fulfill the responsibility;</p><p>B. Repository - Abstraction of the Data Layer which centralises the handling of the Domain Objects;</p><p>C. Abstract Factory - Interface responsible for creating a Factory of related Objects without explicitly specifying the intend Class;</p><p>D. Information Expert  - Class that has the information necessary to fulfill the responsibility;</p><p>E. DTO - Class that encapsulates and aggregates for transfer;</p><p>F. DTO - Class responsible of mapping data from Domain Entities to DTOs;</p> |
| 3.&ensp;&ensp;The Sales Manager selects the Questionnaire whose answers are to be compiled and converted to a Statistical Report; | | | |
| 4.&ensp;&ensp;The System validates all the Answers given by the Customers related to the selected Questionnaire; | <p>A. Knows the Answers to each Question associated to the Questionnaire at hand?</p><p>B. Knows which Section a given Question belongs to?</p><p>C. Knows which Sections a given Questionnaire is organized by?</p><p>D. Valides each Answer relative to a given Question?</p> | <p>A. Question</p><p>B. Section</p><p>C. Questionnaire</p><p>D. QuestionnaireValidationService</p> | <p>A. Information Expert  - Class that has the information necessary to fulfill the responsibility;</p><p>B. Information Expert  - Class that has the information necessary to fulfill the responsibility;</p><p>C. Information Expert  - Class that has the information necessary to fulfill the responsibility;</p><p>D. Controller-Service - Class responsible for processing business logic;</p> |
| 5.&ensp;&ensp;The System compiles all the validated answers into Statistical Data and converts said Data into a Statistical Report; | <p>A. Compiles each validated Answer relative go a given Question, into Data?</p><p>B. Compiles all the Raw Data associated to a given Question into Statistical Data?</p><p>C. Compiles all Percentages associated to a given Question into Statistical Data?</p><p>D. Knows the Parameters required by the previous stated Strategies</p><p>E. Knows the Statistical Data relative to the previously selected Questionnaire?<p>F. Creates instances of StatisticalReport?</p><p>G. Converts instance of Statistical Report into a File?</p><p>H. Converts instance of Statistical Report into a Excel File?</p> | <p>A. DataStrategy</p><p>B. RawDataStrategy</p><p>C. PercentageDataStrategy</p><p>D. Parameter</p><p>E. StatisticalReport</p><p>F. StatiscalReportBuilder</p><p>G. FileStrategy</p><p>H. ExcelFileStrategy</p> | <p>A. Strategy - Behavioral Software Design Pattern that enables the selection of an Algorithm at Runtime. During Runtime the Process receives instructions as to which specific implementation of the Algorithm to use;</p><p>B. Strategy - Behavioral Software Design Pattern that enables the selection of an Algorithm at Runtime. During Runtime the Process receives instructions as to which specific implementation of the Algorithm to use; </p><p>C. Strategy - Behavioral Software Design Pattern that enables the selection of an Algorithm at Runtime. During Runtime the Process receives instructions as to which specific implementation of the Algorithm to use;</p><p>D. Strategy - Behavioral Software Design Pattern that enables the selection of an Algorithm at Runtime. During Runtime the Process receives instructions as to which specific implementation of the Algorithm to use;</p><p>E. Information Expert - Class that has the information necessary to fulfill the responsibility;</p><p>F. Builder - Creational Design Pattern which allows the construction of an Instance of a given complex Object iteratively;</p><p>G. Strategy - Behavioral Software Design Pattern that enables the selection of an Algorithm at Runtime. During Runtime the Process receives instructions as to which specific implementation of the Algorithm to use;</p><p>H. Strategy - Behavioral Software Design Pattern that enables the selection of an Algorithm at Runtime. During Runtime the Process receives instructions as to which specific implementation of the Algorithm to use;</p> |
</br>

#### <b>2.B. Systematization</b>
</br>

<p>From the Rationale, the following Conceptual Classes are promoted to Software Classes:</p>

<ul>
    <li>TargetAudience;</li>
    <li>Question;</li>
    <li>Section;</li>
    <li>Questionnaire;</li>
    <li>StatisticalReport;</li>
</ul>
</br>

<p>Other Software Classes identified:</p>

<ul>
    <li>ObtainStatisticalReportUI;</li>
    <li>ObtainStatisticalReportController;</li>
    <li>ObtainStatisticalReportService;</li>
    <li>QuestionnaireValidationService;</li>
    <li>PersistenceContext;</li>
    <li>RepositoryFactory;</li>
    <li>QuestionnaireRepository;</li>
    <li>StatisticalReportRepository;</li>
    <li>QuestionnaireDTO;</li>
    <li>QuestionnaireMapper;</li>
    <li>DataContext;</li>
    <li>RawDataStrategy;</li>
    <li>PercentageDataStrategy;</li>
    <li>Parameter;</li>
    <li>StatisticalDataBuilder;</li>
    <li>StatisticalReportBuilder;</li>
    <li>FileContext;</li>
    <li>ExcelFileStrategy;</li>
</ul>
</br>

#### <b>2.C. Sequence Diagram</b>
</br>

![US3002-SD.svg](US3002-SD.svg)
</br>
</br>

#### <b>2.D. Test Planning</b>
</br>

<p>This section contains primary Unit Tests developped in order to affer the satisfaction of the User Stories requisites;</p>
</br>

| Tested Class | Test Objective | <div style="width:650px">Implementation</div>                                                                                                                                                                                                             |
|:--------------|:-------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| QuestionnaireRepository | Verify that the method "getAnsweredQuestionnaireIdentifiers()" retrieves the full list of of appropriate Questionnaire Identifiers that have been answered by at least 30 Customers; | <p>@Test</br>public void ensureRetrievalOfAnsweredQuestionnaires() {</br>&ensp;&ensp;List\<Questionnaires> expectedResult = questionnaireList;</br></br>&ensp;&ensp;assertEquals(expectedResult, questionnaireRepository.answeredQuestionnaires());</br>} |
| QuestionnaireRepository  | Verify that the method "getAnsweredQuestionnaireIdentifiers()" returns an empty list of Identifiers if no Questionnaire have been answered by at least 30 Customer; | <p>@Test</br>public void ensureRetrievalOfEmptyList() {</br>&ensp;&ensp;List\<Questionnaire> expectedResult = new ArrayList<>();</br></br>&ensp;&ensp;assertEquals(expectedResult, questionnaireRepository.answeredQuestionnaires());</br>}               |
| ObtainStatisticalReportService | Verify that the method "listAllAnsweredQuestionnaire()" returns the full list of appropriate QuestionnaireDTOs who have been answered by at least 30 Customers; | <p>@Test</br>public void ensureRetrievalOfAnsweredQuestionnaires() {</br>&ensp;&ensp;List\<QuestionnaireDTO> expectedResult = questionnaireDTOList;</br></br>&ensp;&ensp;assertEquals(expectedResult, service.listAllAnsweredQuestionnaire());</br>}      |
| ObtainStatisticalReportService | Verify that the method "listAllAnsweredQuestionnaire()" returns an empty list of Identifiers if no Questionnaire have been answered by at least 30 Customer; | <p>@Test</br>public void ensureRetrievalOfEmptyList() {</br>&ensp;&ensp;List\<Questionnaire> expectedResult = new ArrayList<>();</br></br>&ensp;&ensp;assertEquals(expectedResult, service.listAllAnsweredQuestionnaire());</br>}                         |
| RawDataStrategy | Verify that the method "execute(Parameter parameter)" generates mathematically correct data and returns List of Instances of StatisticalData containning said data; | <p>@Test</br>public void ensureValidInstantiationOfStatisticalData() {</br>&ensp;&ensp;List<StatisticalData> expectedResult = targetStatisticalData;</br></br>&ensp;&ensp;assertEquals(expectedResult, rawDataStrategy.execute(parameter);</br>}          |
| ExcelFileStrategy | Verify that the method "execute(StatisticalReport statisticalReport)" generates an Excel file containning all Statistical Data present in the Instance of StatisticalReport passed as Parameter; | <p>@Test</br>public void ensureRetrievalOfAnsweredQuestionnaires() {</br>&ensp;&ensp;excelFileStrategy.execute(statisticalReport);</br></br>&ensp;&ensp;assertTrue(Files.exists(targetPath));</br>}                                                       |
| ExcelFileStrategy | Verify that the method "generateExcelFile(StatisticalReport statisticalReport)" throws an Exception when an invalid Instante of StatisticalReport is passed as Parameter; | <p>@Test</br>public void ensureRetrievalOfAnsweredQuestionnaires() {</br>&ensp;&ensp;assertThrows(Exception.class, () -> excelFileStrategy.execute(invalidStatisticalReport));</br>}                                                                      |
| ObtainStatisticalReportService | Verify that the method "validateAndCompileQuestionnaire(QuestionnaireDTO questionnaireDTO)" compiles, generates an Excel File containning the Statistical Data compiled and persists newly created Instance of StatisticalReport; | <p>@Test</br>public void ensureStatisticalReportPersistence() {</br>}                                                                                                                                                                                     |