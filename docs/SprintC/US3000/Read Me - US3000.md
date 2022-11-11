## <b>US 3000 - Specify Grammar for Questionnaire validation</b>
</br>

### <b>1. Requirements Engineering</b>
</br>

#### <b>1.A. Complete Format</b>
</br>

<b>Main Actor</b>
<p>&ensp;&ensp;&ensp;&ensp;N/A</p>
</br>

<b>Interested Actors and their respective interests</b>
<p>&ensp;&ensp;&ensp;&ensp;<b>Sales Manager:</b> intends to have surveys validated by a Parser in order for them to be answered by Customers.</p>
<p>&ensp;&ensp;&ensp;&ensp;<b>SPOMS:</b> intends to have surveys validated by a Parser in order for them to be answered by Customers.</p>
<p>&ensp;&ensp;&ensp;&ensp;<b>Customer:</b> intends to have surveys validated by a Parser in order for them to be answered.</p>
<br>

<b>Preconditions</b>
<p>&ensp;&ensp;&ensp;&ensp;It is required that at least a single Questionnaire be developed in order for it to be validated by the Parser.</p>
</br>

<b>Postconditions</b>
<p>&ensp;&ensp;&ensp;&ensp;The Parser indicates whether or not the developed Questionnaires are valid.</p>
</br>

<b>Main Scenario</b>
<ol>
    <li>The Sales Manager initializes the process of validation of the Questionnaires;</li>
    <li>The System analyses and validates each Questionnaire;</li>
</ol>
</br>

<b>Alternative Scenarios</b>
<p>&ensp;&ensp;&ensp;&ensp;1.a. The System analyses and detects that a given Questionnaire isn't valid;</p>

> <p>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;The System doesn't validate the Questionnaire and informs the Sales Manager of such;</p>
</br>

<b>Special Requirements</b>
<p>&ensp;&ensp;&ensp;&ensp;N/A</p>
</br>

<b>List of Variations of Technology and Data</b>
<p>&ensp;&ensp;&ensp;&ensp;1. ANTLR - Used for the generation of the required Parser;</p>
</br>

<b>Frequency of Occurence</b>
<p>&ensp;&ensp;&ensp;&ensp;N/A</p>
</br>