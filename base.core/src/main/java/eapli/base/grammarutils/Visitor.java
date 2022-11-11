package eapli.base.grammarutils;

import eapli.base.common.util.Pair;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.questionnairemanagement.dto.*;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Visitor extends QuestionnaireGrammarBaseVisitor<String> {
    private static final String END_PUNCTUATION_CHARACTER1 = ";";
    private static final String SPLIT_REGEX1 = "[<>;]";
    private static final String SPLIT_REGEX2 = "[();]";
    private static final String SPLIT_REGEX3 = "[\n]";
    private static final String SPLIT_REGEX4 = "[||]";

    private static final int TYPE_INDEX = 0;
    private static final int CONDITION_INDEX = 1;
    private static final int MIN_LENGTH = 1;

    private QuestionnaireDTO questionnaireDTO;
    private SectionDTO sectionDTO;
    private QuestionDTO questionDTO;

    @Override
    public String visitQuestionnaire(QuestionnaireGrammarParser.QuestionnaireContext ctx) {
        String identifier = ctx.QUESTIONNAIRE_ID().getText();
        String title = ctx.TITLE().getText();
        String introductionMessage = visit(ctx.questionnaire_introduction_message());
        List<SectionDTO> sectionList = new ArrayList<>();

        for (QuestionnaireGrammarParser.Section_structureContext context : ctx.section_structure()) {
            visit(context);
            sectionList.add(sectionDTO);
        }

        String endMessage = visit(ctx.questionnaire_end_message());

        questionnaireDTO = QuestionnaireMapper.toDTO(identifier,
                title,
                introductionMessage,
                endMessage,
                sectionList,
                TargetAudienceMapper.toDTO(new IdentifierGenerator().generateIdentifier().getIdentifier(), new ArrayList<>(), new ArrayList<>()));

        return null;
    }

    @Override
    public String visitIntroductionMessage(QuestionnaireGrammarParser.IntroductionMessageContext ctx) {
        StringBuilder stringBuilder = new StringBuilder();

        for (TerminalNode node : ctx.SENTENCE())
            stringBuilder.append(node.getText());

        return stringBuilder.toString();
    }

    @Override
    public String visitEndMessage(QuestionnaireGrammarParser.EndMessageContext ctx) {
        StringBuilder stringBuilder = new StringBuilder();

        for (TerminalNode node : ctx.SENTENCE())
            stringBuilder.append(node.getText());

        return stringBuilder.toString();
    }

    @Override
    public String visitSection(QuestionnaireGrammarParser.SectionContext ctx) {
        Integer number = Integer.valueOf(ctx.NUMBER().getText());
        String title = ctx.TITLE().getText();
        String description = visit(ctx.section_description());
        List<QuestionDTO> questionList = new ArrayList<>();

        for (QuestionnaireGrammarParser.Question_structureContext context : ctx.question_structure()) {
            visit(context);
            questionList.add(questionDTO);
        }

        sectionDTO = SectionMapper.toDTO(new IdentifierGenerator().generateIdentifier().getIdentifier(),
                number,
                title,
                description,
                tokenToPair(visit(ctx.obligatoriness_field())),
                tokenToPair(visit(ctx.repeatability_field())),
                questionList);

        return null;
    }

    @Override
    public String visitSectionDescription(QuestionnaireGrammarParser.SectionDescriptionContext ctx) {
        StringBuilder stringBuilder = new StringBuilder();

        for (TerminalNode node : ctx.SENTENCE())
            stringBuilder.append(node).append("\n");

        return stringBuilder.toString();
    }

    @Override
    public String visitQuestion(QuestionnaireGrammarParser.QuestionContext ctx) {
        Integer number = Integer.valueOf(ctx.NUMBER().getText());
        String interrogation = ctx.SENTENCE().getText();
        String instruction = visit(ctx.instruction_field());
        String extraInformation = visit(ctx.extra_info_field());
        String[] auxArray1 = visit(ctx.type_field()).split(SPLIT_REGEX3);
        String auxString = visit(ctx.answer());

        List<String> optionList = null;
        List<String> answers = new ArrayList<>();

        if (auxArray1.length > MIN_LENGTH) {
            optionList = new ArrayList<>();

            for (int count = 1; count < auxArray1.length; count++)
                optionList.add(auxArray1[count]);
        }

        String[] auxArray2 = auxString.split(SPLIT_REGEX4);

        for (String answer : auxArray2) {
            if (!answer.isEmpty())
                answers.add(answer.replace(END_PUNCTUATION_CHARACTER1, "").trim());
        }

        questionDTO = QuestionMapper.toDTO(new IdentifierGenerator().generateIdentifier().getIdentifier(),
                number,
                interrogation,
                instruction,
                auxArray1[TYPE_INDEX],
                optionList,
                tokenToPair(visit(ctx.obligatoriness_field())),
                extraInformation,
                answers);

        return null;
    }

    @Override
    public String visitObligatorinessField(QuestionnaireGrammarParser.ObligatorinessFieldContext ctx) {
        String[] auxArray = ctx.OBLIGATORINESS_OPTIONS().getText().split(SPLIT_REGEX2);
        String obligatorinessType = auxArray[TYPE_INDEX].trim();
        String obligatorinessCondition = null;

        if (auxArray.length > MIN_LENGTH)
            obligatorinessCondition = auxArray[CONDITION_INDEX];

        Pair<String, String> pair = new Pair<>(obligatorinessType, obligatorinessCondition);

        return pair.toString();
    }

    @Override
    public String visitInstructionField(QuestionnaireGrammarParser.InstructionFieldContext ctx) {
        StringBuilder stringBuilder = new StringBuilder();

        for (TerminalNode node : ctx.SENTENCE())
            stringBuilder.append(node.getText()).append("\n");

        return stringBuilder.toString();
    }

    @Override
    public String visitTypeField(QuestionnaireGrammarParser.TypeFieldContext ctx) {
        if (ctx.selectionType != null)
            return visit(ctx.selection_type());
        else if (ctx.insertionType != null)
            return visit(ctx.insertion_type());
        else
            return visit(ctx.table_type());
    }

    @Override
    public String visitSelectionType(QuestionnaireGrammarParser.SelectionTypeContext ctx) {
        StringBuilder builder = new StringBuilder();

        builder.append(ctx.SELECTION_TYPE_TITLE()).append("\n");
        builder.append(visit(ctx.option_list()));

        return builder.toString();
    }

    @Override
    public String visitTableType(QuestionnaireGrammarParser.TableTypeContext ctx) {
        StringBuilder builder = new StringBuilder();

        builder.append(ctx.TABLE_TYPE_TITLE()).append("\n");
        builder.append(visit(ctx.table()));

        return builder.toString();
    }

    @Override public String visitInsertionType(QuestionnaireGrammarParser.InsertionTypeContext ctx) {
        return ctx.INSERTION_TYPE_TITLE().getText().replace(END_PUNCTUATION_CHARACTER1, "");
    }

    @Override public String visitOptionList(QuestionnaireGrammarParser.OptionListContext ctx) {
        StringBuilder builder = new StringBuilder();

        for (QuestionnaireGrammarParser.Option_itemContext context : ctx.option_item())
            builder.append(visit(context)).append("\n");

        return builder.toString();
    }

    @Override
    public String visitOptionItem(QuestionnaireGrammarParser.OptionItemContext ctx) { return ctx.SENTENCE().getText(); }

    @Override
    public String visitAnswerContent(QuestionnaireGrammarParser.AnswerContentContext ctx) {
        StringBuilder stringBuilder = new StringBuilder();

        for (TerminalNode node : ctx.SENTENCE())
            stringBuilder.append(node.getText()).append("\n");

        return stringBuilder.toString();
    }

    private Pair<String, String> tokenToPair(String token) {
        if (token != null) {
            List<String> auxList = new ArrayList<>();

            for (String splitString : token.split(SPLIT_REGEX1)) {
                if (!splitString.isEmpty())
                    auxList.add(splitString);
            }

            if (auxList.size() > MIN_LENGTH)
                return new Pair<>(auxList.get(TYPE_INDEX), auxList.get(CONDITION_INDEX));
            else
                return new Pair<>(auxList.get(TYPE_INDEX), null);
        }

        return null;
    }

    public QuestionnaireDTO getQuestionnaireDTO() {
        return questionnaireDTO;
    }
}
