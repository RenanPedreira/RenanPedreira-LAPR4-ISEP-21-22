// Generated from /home/pico/Documents/ISEP/LAPR4/lei21_22_s4_2dj_3/base.core/QuestionnaireGrammar.g4 by ANTLR 4.10.1
package eapli.base.grammarutils;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QuestionnaireGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QuestionnaireGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QuestionnaireGrammarParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(QuestionnaireGrammarParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionnaireGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(QuestionnaireGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code questionnaire}
	 * labeled alternative in {@link QuestionnaireGrammarParser#questionnaire_structure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionnaire(QuestionnaireGrammarParser.QuestionnaireContext ctx);
	/**
	 * Visit a parse tree produced by the {@code introductionMessage}
	 * labeled alternative in {@link QuestionnaireGrammarParser#questionnaire_introduction_message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntroductionMessage(QuestionnaireGrammarParser.IntroductionMessageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code endMessage}
	 * labeled alternative in {@link QuestionnaireGrammarParser#questionnaire_end_message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndMessage(QuestionnaireGrammarParser.EndMessageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code section}
	 * labeled alternative in {@link QuestionnaireGrammarParser#section_structure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(QuestionnaireGrammarParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sectionDescription}
	 * labeled alternative in {@link QuestionnaireGrammarParser#section_description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSectionDescription(QuestionnaireGrammarParser.SectionDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code question}
	 * labeled alternative in {@link QuestionnaireGrammarParser#question_structure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QuestionnaireGrammarParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code obligatorinessField}
	 * labeled alternative in {@link QuestionnaireGrammarParser#obligatoriness_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObligatorinessField(QuestionnaireGrammarParser.ObligatorinessFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionnaireGrammarParser#repeatability_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatability_field(QuestionnaireGrammarParser.Repeatability_fieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuestionnaireGrammarParser#extra_info_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtra_info_field(QuestionnaireGrammarParser.Extra_info_fieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instructionField}
	 * labeled alternative in {@link QuestionnaireGrammarParser#instruction_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionField(QuestionnaireGrammarParser.InstructionFieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeField}
	 * labeled alternative in {@link QuestionnaireGrammarParser#type_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeField(QuestionnaireGrammarParser.TypeFieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectionType}
	 * labeled alternative in {@link QuestionnaireGrammarParser#selection_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionType(QuestionnaireGrammarParser.SelectionTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableType}
	 * labeled alternative in {@link QuestionnaireGrammarParser#table_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableType(QuestionnaireGrammarParser.TableTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code insertionType}
	 * labeled alternative in {@link QuestionnaireGrammarParser#insertion_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertionType(QuestionnaireGrammarParser.InsertionTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code optionList}
	 * labeled alternative in {@link QuestionnaireGrammarParser#option_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionList(QuestionnaireGrammarParser.OptionListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code optionItem}
	 * labeled alternative in {@link QuestionnaireGrammarParser#option_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionItem(QuestionnaireGrammarParser.OptionItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableContent}
	 * labeled alternative in {@link QuestionnaireGrammarParser#table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableContent(QuestionnaireGrammarParser.TableContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code content}
	 * labeled alternative in {@link QuestionnaireGrammarParser#table_row}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(QuestionnaireGrammarParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defaultEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultEntry(QuestionnaireGrammarParser.DefaultEntryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyEntry(QuestionnaireGrammarParser.EmptyEntryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defaultEndEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#end_entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultEndEntry(QuestionnaireGrammarParser.DefaultEndEntryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyEndEntry}
	 * labeled alternative in {@link QuestionnaireGrammarParser#end_entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyEndEntry(QuestionnaireGrammarParser.EmptyEndEntryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code answerContent}
	 * labeled alternative in {@link QuestionnaireGrammarParser#answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerContent(QuestionnaireGrammarParser.AnswerContentContext ctx);
}