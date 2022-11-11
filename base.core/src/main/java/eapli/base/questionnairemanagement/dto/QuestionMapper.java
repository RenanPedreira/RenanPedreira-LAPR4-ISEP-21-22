package eapli.base.questionnairemanagement.dto;

import eapli.base.common.domain.model.Identifier;
import eapli.base.common.util.Pair;
import eapli.base.questionnairemanagement.domain.*;
import eapli.base.questionnairemanagement.domain.Number;
import eapli.framework.math.domain.model.Numeral;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QuestionMapper {
    public static QuestionDTO toDTO(Question question) {
        String identifier = question.identifier().getIdentifier();
        Integer number = question.number().getNumber();
        String interrogation = question.interrogation().getInterrogation();
        String instruction = question.instruction().getInstruction();
        String type = question.getType().getType();
        Pair<String, String> obligatoriness = new Pair<>(question.obligatoriness().getType(), question.obligatoriness().getCondition());
        String extraInformation = null;

        if (question.extraInformation() != null)
            extraInformation = question.extraInformation().getMessage();

        List<String> optionList = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        for (Message option : question.getOptionList())
            optionList.add(option.getMessage());

        for (Answer answer : question.answers())
            answers.add(answer.getContent());

        return new QuestionDTO(identifier, number, interrogation, instruction, type, optionList, obligatoriness, extraInformation, answers);
    }

    public static QuestionDTO toDTO(String identifier,
                                    Integer number,
                                    String interrogation,
                                    String instruction,
                                    String type,
                                    List<String> optionList,
                                    Pair<String, String> obligatoriness,
                                    String extraInformation,
                                    List<String> answers) {
        return new QuestionDTO(identifier, number, interrogation, instruction, type, optionList, obligatoriness, extraInformation, answers);
    }

    public static Question toQuestion(QuestionDTO questionDTO) {
        List<Message> optionList = new ArrayList<>();
        LinkedList<Answer> answers = new LinkedList<>();

        if (questionDTO.getOptionList() != null) {
            for (String option : questionDTO.getOptionList())
                optionList.add(new Message(option));
        }

        for (String answer : questionDTO.getAnswers())
            answers.add(new Answer(answer));

        return new Question(new Identifier(questionDTO.getIdentifier()),
                new Number(questionDTO.getNumber()),
                new Interrogation(questionDTO.getInterrogation()),
                new Instruction(questionDTO.getInstruction()),
                new Type(questionDTO.getType()),
                optionList,
                new Obligatoriness(questionDTO.getObligatoriness().getKey(), questionDTO.getObligatoriness().getValue()),
                new Message(questionDTO.getExtraInformation()),
                answers);
    }
}
