// Generated from /home/pico/Documents/ISEP/LAPR4/lei21_22_s4_2dj_3/base.core/QuestionnaireGrammar.g4 by ANTLR 4.10.1
package eapli.base.grammarutils;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QuestionnaireGrammarParser}.
 */
public interface QuestionnaireGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QuestionnaireGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(QuestionnaireGrammarParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionnaireGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(QuestionnaireGrammarParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionnaireGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(QuestionnaireGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionnaireGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(QuestionnaireGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code questionnaire}
	 * labeled alternative in {@link QuestionnaireGrammarParser#questionnaire_structure}.
	 * @param ctx the parse tree
	 */
	void enterQuestionnaire(QuestionnaireGrammarParser.QuestionnaireContext ctx);
	/**
	 * Exit a parse tree produced by the {@code questionnaire}
	 * labeled alternative in {@link QuestionnaireGrammarParser#questionnaire_structure}.
	 * @param ctx the parse tree
	 */
	void exitQuestionnaire(QuestionnaireGrammarParser.QuestionnaireContext ctx);
	/**
	 * Enter a parse tree produced by the {@code introductionMessage}
	 * labeled alternative in {@link QuestionnaireGrammarParser#questionnaire_introduction_message}.
	 * @param ctx the parse tree
	 */
	void enterIntroductionMessage(QuestionnaireGrammarParser.IntroductionMessageContext ctx);
	/**
	 * Exit a parse tree produced by the {@code introductionMessage}
	 * labeled alternative in {@link QuestionnaireGrammarParser#questionnaire_introduction_message}.
	 * @param ctx the parse tree
	 */
	void exitIntroductionMessage(QuestionnaireGrammarParser.IntroductionMessageContext ctx);
	/**
	 * Enter a parse tree produced by the {@code endMessage}
	 * labeled alternative in {@link QuestionnaireGrammarParser#questionnaire_end_message}.
	 * @param ctx the parse tree
	 */
	void enterEndMessage(QuestionnaireGrammarParser.EndMessageContext ctx);
	/**
	 * Exit a parse tree produced by the {@code endMessage}
	 * labeled alternative in {@link QuestionnaireGrammarParser#questionnaire_end_message}.
	 * @param ctx the parse tree
	 */
	void exitEndMessage(QuestionnaireGrammarParser.EndMessageContext ctx);
	/**
	 * Enter a parse tree produced by the {@code section}
	 * labeled alternative in {@link QuestionnaireGrammarParser#section_structure}.
	 * @param ctx the parse tree
	 */
	void enterSection(QuestionnaireGrammarParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code section}
	 * labeled alternative in {@link QuestionnaireGrammarParser#section_structure}.
	 * @param ctx the parse tree
	 */
	void exitSection(QuestionnaireGrammarParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sectionDescription}
	 * labeled alternative in {@link QuestionnaireGrammarParser#section_description}.
	 * @param ctx the parse tree
	 */
	void enterSectionDescription(QuestionnaireGrammarParser.SectionDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sectionDescription}
	 * labeled alternative in {@link QuestionnaireGrammarParser#section_description}.
	 * @param ctx the parse tree
	 */
	void exitSectionDescription(QuestionnaireGrammarParser.SectionDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code question}
	 * labeled alternative in {@link QuestionnaireGrammarParser#question_structure}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QuestionnaireGrammarParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code question}
	 * labeled alternative in {@link QuestionnaireGrammarParser#question_structure}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QuestionnaireGrammarParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code obligatorinessField}
	 * labeled alternative in {@link QuestionnaireGrammarParser#obligatoriness_field}.
	 * @param ctx the parse tree
	 */
	void enterObligatorinessField(QuestionnaireGrammarParser.ObligatorinessFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code obligatorinessField}
	 * labeled alternative in {@link QuestionnaireGrammarParser#obligatoriness_field}.
	 * @param ctx the parse tree
	 */
	void exitObligatorinessField(QuestionnaireGrammarParser.ObligatorinessFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionnaireGrammarParser#repeatability_field}.
	 * @param ctx the parse tree
	 */
	void enterRepeatability_field(QuestionnaireGrammarParser.Repeatability_fieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionnaireGrammarParser#repeatability_field}.
	 * @param ctx the parse tree
	 */
	void exitRepeatability_field(QuestionnaireGrammarParser.Repeatability_fieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionnaireGrammarParser#extra_info_field}.
	 * @param ctx the parse tree
	 */
	void enterExtra_info_field(QuestionnaireGrammarParser.Extra_info_fieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionnaireGrammarParser#extra_info_field}.
	 * @param ctx the parse tree
	 */
	void exitExtra_info_field(QuestionnaireGrammarParser.Extra_info_fieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instructionField}
	 * labeled alternative in {@link QuestionnaireGrammarParser#instruction_field}.
	 * @param ctx the parse tree
	 */
	void enterInstructionField(QuestionnaireGrammarParser.InstructionFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instructionField}
	 * labeled alternative in {@link QuestionnaireGrammarParser#instruction_field}.
	 * @param ctx the parse tree
	 */
	void exitInstructionField(QuestionnaireGrammarParser.InstructionFieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typeField}
	 * labeled alternative in {@link QuestionnaireGrammarParser#type_field}.
	 * @param ctx the parse tree
	 */
	void enterTypeField(QuestionnaireGrammarParser.TypeFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typeField}
	 * labeled alternative in {@link QuestionnaireGrammarParser#type_field}.
	 * @param ctx the parse tree
	 */
	void exitTypeField(QuestionnaireGrammarParser.TypeFieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectionType}
	 * labeled alternative in {@link QuestionnaireGrammarParser#selection_type}.
	 * @param ctx the parse tree
	 */
	void enterSelectionType(QuestionnaireGrammarParser.SelectionTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectionType}
	 * labeled alternative in {@link QuestionnaireGrammarParser#selection_type}.
	 * @param ctx the parse tree
	 */
	void exitSelectionType(QuestionnaireGrammarParser.SelectionTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableType}
	 * labeled alternative in {@link QuestionnaireGrammarParser#table_type}.
	 * @param ctx the parse tree
	 */
	void enterTableType(QuestionnaireGrammarParser.TableTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableType}
	 * labeled alternative in {@link QuestionnaireGrammarParser#table_type}.
	 * @param ctx the parse tree
	 */
	void exitTableType(QuestionnaireGrammarParser.TableTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code insertionType}
	 * labeled alternative in {@link QuestionnaireGrammarParser#insertion_type}.
	 * @param ctx the parse tree
	 */
	void enterInsertionType(QuestionnaireGrammarParser.InsertionTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code insertionType}
	 * labeled alternative in {@link QuestionnaireGrammarParser#insertion_type}.
	 * @param ctx the parse tree
	 */
	void exitInsertionType(QuestionnaireGrammarParser.InsertionTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code optionList}
	 * labeled alternative in {@link QuestionnaireGrammarParser#option_list}.
	 * @param ctx the parse tree
	 */
	void enterOptionList(QuestionnaireGrammarParser.OptionListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code optionList}
	 * labeled alternative in {@link QuestionnaireGrammarParser#option_list}.
	 * @param ctx the parse tree
	 */
	void exitOptionList(QuestionnaireGrammarParser.OptionListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code optionItem}
	 * labeled alternative in {@link QuestionnaireGrammarParser#option_item}.
	 * @param ctx the parse tree
	 */
	void enterOptionItem(QuestionnaireGrammarParser.OptionItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code optionItem}
	 * labeled alternative in {@link QuestionnaireGrammarParser#option_item}.
	 * @param ctx the parse tree
	 */
	void exitOptionItem(QuestionnaireGrammarParser.OptionItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableContent}
	 * labeled alternative in {@link QuestionnaireGrammarParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTableContent(QuestionnaireGrammarParser.TableContentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableContent}
	 * labeled alternative in {@link QuestionnaireGrammarParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTableContent(QuestionnaireGrammarParser.TableContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code content}
	 * labeled alternative in {@link QuestionnaireGrammarParser#table_row}.
	 * @param ctx the parse tree
	 */
	void enterContent(QuestionnaireGrammarParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code content}
	 * labeled alternative in {@link QuestionnaireGrammarParser#table_row}.
	 * @param ctx the parse tree
	 */
	void exitContent(QuestionnaireGrammarParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defaultEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#entry}.
	 * @param ctx the parse tree
	 */
	void enterDefaultEntry(QuestionnaireGrammarParser.DefaultEntryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defaultEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#entry}.
	 * @param ctx the parse tree
	 */
	void exitDefaultEntry(QuestionnaireGrammarParser.DefaultEntryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#entry}.
	 * @param ctx the parse tree
	 */
	void enterEmptyEntry(QuestionnaireGrammarParser.EmptyEntryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#entry}.
	 * @param ctx the parse tree
	 */
	void exitEmptyEntry(QuestionnaireGrammarParser.EmptyEntryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defaultEndEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#end_entry}.
	 * @param ctx the parse tree
	 */
	void enterDefaultEndEntry(QuestionnaireGrammarParser.DefaultEndEntryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defaultEndEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#end_entry}.
	 * @param ctx the parse tree
	 */
	void exitDefaultEndEntry(QuestionnaireGrammarParser.DefaultEndEntryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyEndEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#end_entry}.
	 * @param ctx the parse tree
	 */
	void enterEmptyEndEntry(QuestionnaireGrammarParser.EmptyEndEntryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyEndEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#end_entry}.
	 * @param ctx the parse tree
	 */
	void exitEmptyEndEntry(QuestionnaireGrammarParser.EmptyEndEntryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code answerContent}
	 * labeled alternative in {@link QuestionnaireGrammarParser#answer}.
	 * @param ctx the parse tree
	 */
	void enterAnswerContent(QuestionnaireGrammarParser.AnswerContentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code answerContent}
	 * labeled alternative in {@link QuestionnaireGrammarParser#answer}.
	 * @param ctx the parse tree
	 */
	void exitAnswerContent(QuestionnaireGrammarParser.AnswerContentContext ctx);
}