grammar QuestionnaireGrammar;

prog: start;

start: questionnaire_structure;

questionnaire_structure: QUESTIONNAIRE_ID TITLE questionnaire_introduction_message section_structure+ questionnaire_end_message <EOF> #questionnaire;
questionnaire_introduction_message: SENTENCE+ #introductionMessage;
questionnaire_end_message: TITLE SENTENCE+ #endMessage;

section_structure: NUMBER SECTION_DELIMITER TITLE section_description obligatoriness_field repeatability_field question_structure+ #section;
section_description: (SENTENCE+)? #sectionDescription;

question_structure: QUETION_DELIMITER1 NUMBER QUETION_DELIMITER2 SENTENCE instruction_field obligatoriness_field extra_info_field type_field answer? #question;

obligatoriness_field: OBLIGATORINESS_TITLE OBLIGATORINESS_OPTIONS #obligatorinessField;
repeatability_field:;
extra_info_field:;
instruction_field: (INSTRUCTION_TITLE SENTENCE+)? #instructionField;
type_field: TYPE_TITLE (selectionType = selection_type | tableType = table_type | insertionType = insertion_type) #typeField;

selection_type: SELECTION_TYPE_TITLE option_list #selectionType;
table_type: TABLE_TYPE_TITLE table #tableType;
insertion_type: INSERTION_TYPE_TITLE #insertionType;

option_list: TITLE option_item+ #optionList;
option_item: NUMBER SENTENCE #optionItem;

table: table_row+ #tableContent;
table_row: entry+ end_entry #content;

entry: TABLE_DELIMITER TITLE #defaultEntry
| TABLE_DELIMITER #emptyEntry;

end_entry: TABLE_DELIMITER TITLE END_TABLE_DELIMITER #defaultEndEntry
| TABLE_DELIMITER END_TABLE_DELIMITER #emptyEndEntry;

answer: ANSWER_TITLE (SENTENCE+)? #answerContent;

QUESTIONNAIRE_ID: WORD NUMBER SPECIAL_CHARACTERS NUMBER;

SECTION_DELIMITER: [\-];

OBLIGATORINESS_TITLE: 'Obligatoriness';
OBLIGATORINESS_OPTIONS: ('Mandatory' END_SENTENCE_PUNCTUATION | 'Optional' END_SENTENCE_PUNCTUATION | 'Condition Dependent' SPACE SENTENCE);

QUETION_DELIMITER1: '{';
QUETION_DELIMITER2: '}';
INSTRUCTION_TITLE: 'Instructions';
TYPE_TITLE: 'Type';
SELECTION_TYPE_TITLE: ('Single-Choice'|'Single-Choice with Input'|'Multiple-Choice'|'Multiple-Choice with Input') END_SENTENCE_PUNCTUATION;
TABLE_TYPE_TITLE: ('Scaling Option'|'Sorting Options') END_SENTENCE_PUNCTUATION;
TABLE_DELIMITER: '[|'|'|';
END_TABLE_DELIMITER: '|]';
INSERTION_TYPE_TITLE: ('Free-Text'|'Numeric') END_SENTENCE_PUNCTUATION;
ANSWER_TITLE: 'Answer';

TITLE: WORD | WORD ((SPACE|SPECIAL_CHARACTERS) WORD SPECIAL_CHARACTERS?)*;
WORD: [A-Za-z]+;
NUMBER: [0-9]+;
SPACE: [ \t] -> skip;
END_SENTENCE_PUNCTUATION: [.?!;];
PUNCTUATION: [,];
DELIMITERS: [:] -> skip;
SPECIAL_CHARACTERS: [()/\-'#"] -> skip;
NEWLINE: [\n\r] -> skip;
SENTENCE: SPECIAL_CHARACTERS? (WORD|NUMBER) (WORD|NUMBER|SPACE|SPECIAL_CHARACTERS|PUNCTUATION|TABLE_DELIMITER)* SPECIAL_CHARACTERS? END_SENTENCE_PUNCTUATION;
