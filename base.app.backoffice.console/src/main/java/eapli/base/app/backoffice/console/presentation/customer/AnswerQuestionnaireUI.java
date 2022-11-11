package eapli.base.app.backoffice.console.presentation.customer;

import eapli.base.customermanagement.application.AnswerQuestionnaireController;
import eapli.base.questionnairemanagement.dto.QuestionDTO;
import eapli.base.questionnairemanagement.dto.QuestionnaireDTO;
import eapli.base.questionnairemanagement.dto.SectionDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

public class AnswerQuestionnaireUI extends AbstractUI {
    private static final int EXIT_OPTION = 0;
    private static final int INITIAL_OPTION = 1;
    private static final String QUIT_OPTION = "QUIT";
    private static final String QUESTION_TYPE1 = "Multiple-Choice;";
    private static final String QUESTION_TYPE2 = "Multiple-Choice with Input;";

    private AnswerQuestionnaireController controller;

    public AnswerQuestionnaireUI() {
        controller = new AnswerQuestionnaireController();
    }

    @Override
    protected boolean doShow() {
        List<QuestionnaireDTO> questionnaireDTOList = controller.listAssignedUnansweredQuestionnaire();
        Map<QuestionDTO, LinkedList<String>> answerPerQuestion = new HashMap<>();


        int count = 1;
        int option;

        System.out.println("Please select the Questionnaire you wish to generate the relative Statistical Report: ");

        for (QuestionnaireDTO questionnaireDTO : questionnaireDTOList)
            System.out.printf("\t%d: [%s] %s\n", count++, questionnaireDTO.getIdentifier().replace("\n", ""), questionnaireDTO.getTitle());

        do {
            option = Console.readOption(INITIAL_OPTION, questionnaireDTOList.size(), EXIT_OPTION);

            if (option < EXIT_OPTION || option > questionnaireDTOList.size())
                System.out.println("Invalid Option.\nPlease select the Questionnaire you wish to generate the relative Statistical Report: ");
        } while (option < EXIT_OPTION || option > questionnaireDTOList.size());

        if (option == EXIT_OPTION)
            return true;

        QuestionnaireDTO questionnaireDTO = questionnaireDTOList.get(option - 1);

        System.out.printf("%s\n\t%s\n%s\n", questionnaireDTO.getIdentifier(), questionnaireDTO.getTitle(), questionnaireDTO.getIntroductionMessage());

        for (SectionDTO sectionDTO : questionnaireDTO.getSectionList()) {
            System.out.printf("\n\t%d - %s\n%s\nObligatoriness: %s (%s);\nRepeatability: %s (%s);\nOption List: ",
                    sectionDTO.getNumber(),
                    sectionDTO.getTitle(),
                    sectionDTO.getDescription(),
                    sectionDTO.getObligatoriness().getKey(),
                    sectionDTO.getObligatoriness().getValue(),
                    sectionDTO.getRepeatability() != null ? sectionDTO.getRepeatability().getKey() : null,
                    sectionDTO.getRepeatability() != null ? sectionDTO.getRepeatability().getValue() : null);

            for (QuestionDTO questionDTO : sectionDTO.getContent()) {
                System.out.printf("{%d} %s\n\nInstructions: %sObligatoriness: %s (%s);\nType: %s\n",
                        questionDTO.getNumber(),
                        questionDTO.getInterrogation(),
                        questionDTO.getInstruction(),
                        questionDTO.getObligatoriness().getKey(),
                        questionDTO.getObligatoriness().getValue(),
                        questionDTO.getType());

                for (int count1 = 1; count1 <= questionDTO.getOptionList().size(); count1++)
                    System.out.printf("\t%s\n", questionDTO.getOptionList().get(count1 - 1));

                    LinkedList<String> answers = new LinkedList<>();
                String answer;

                if (questionDTO.getType().equals(QUESTION_TYPE1) || questionDTO.getType().equals(QUESTION_TYPE2)) {
                    System.out.printf("Type \"QUIT\" in the Terminal when you're satisfied with answer.");

                    do {
                        answer = Console.readLine("Answer: ");

                        if (!answer.equals(QUIT_OPTION))
                            answers.add(answer);
                    } while (!answer.equals(QUIT_OPTION));
                } else
                    answers.add(Console.readLine("Answer: "));

                answerPerQuestion.put(questionDTO, answers);
            }
        }

        System.out.printf("\n\tENDING OF SURVEY\n%s\n", questionnaireDTO.getFinalMessage());

        controller.validateAndPersistAnswers(questionnaireDTO, answerPerQuestion);

        return true;
    }

    @Override
    public String headline() {
        return "Answer Questionnaire";
    }
}
