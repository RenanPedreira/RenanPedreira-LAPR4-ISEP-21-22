package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.questionnairemanagement.dto.QuestionnaireDTO;
import eapli.base.statistics.application.ObtainStatisticalReportController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class ObtainStatisticalReportUI extends AbstractUI {
    private static final int EXIT_OPTION = 0;
    private static final int INITIAL_OPTION = 1;

    private ObtainStatisticalReportController controller;

    public ObtainStatisticalReportUI() {
        controller = new ObtainStatisticalReportController();
    }

    @Override
    protected boolean doShow() {
        List<QuestionnaireDTO> questionnaireDTOList = controller.listAllAnsweredQuestionnaires();

        int count = 1;
        int option;

        System.out.println("Please select the Questionnaire you wish to generate the relative Statistical Report: ");

        for (QuestionnaireDTO questionnaireDTO : questionnaireDTOList)
            System.out.printf("\t%d: [%s] %s\n", count++, questionnaireDTO.getIdentifier().replace("\n", ""), questionnaireDTO.getTitle());

        do {
            option = Console.readOption(INITIAL_OPTION, questionnaireDTOList.size(), EXIT_OPTION);

            if (option < EXIT_OPTION || option > questionnaireDTOList.size())
                System.out.println("Invalid Option.\nPlease select the Questionnaire you wish to generate the relative Statistical Report: ");
        } while(option < EXIT_OPTION || option > questionnaireDTOList.size());

        if (option == EXIT_OPTION)
            return true;

        controller.validateAndCompileQuestionnaire(questionnaireDTOList.get(option - 1));

        return true;
    }

    @Override
    public String headline() {
        return "Obtain Statistical Report";
    }
}
