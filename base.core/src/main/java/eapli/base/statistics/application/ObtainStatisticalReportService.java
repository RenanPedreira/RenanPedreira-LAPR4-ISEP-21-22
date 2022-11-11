package eapli.base.statistics.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.questionnairemanagement.application.QuestionnaireValidationService;
import eapli.base.questionnairemanagement.domain.Question;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.base.questionnairemanagement.domain.Section;
import eapli.base.questionnairemanagement.dto.QuestionnaireDTO;
import eapli.base.questionnairemanagement.dto.QuestionnaireMapper;
import eapli.base.questionnairemanagement.persistence.QuestionnaireRepository;
import eapli.base.statistics.domain.StatisticalData;
import eapli.base.statistics.domain.StatisticalReport;
import eapli.base.statistics.factory.StatisticalReportBuilder;
import eapli.base.statistics.persistence.StatisticalReportRepository;
import eapli.base.statistics.strategy.*;

import java.util.ArrayList;
import java.util.List;

public class ObtainStatisticalReportService {
    private QuestionnaireRepository questionnaireRepository;
    private StatisticalReportRepository statisticalReportRepository;

    public ObtainStatisticalReportService() {
        questionnaireRepository = PersistenceContext.repositories().questionnaireRepository();
        statisticalReportRepository = PersistenceContext.repositories().statisticalReportRepository();
    }

    public List<QuestionnaireDTO> listAllAnsweredQuestionnaires() {
        List<Questionnaire> answeredQuestionnaires = (List<Questionnaire>) questionnaireRepository.answeredQuestionnaires();
        List<QuestionnaireDTO> questionnaireDTOList = new ArrayList<>();

        for (Questionnaire questionnaire : answeredQuestionnaires)
            questionnaireDTOList.add(QuestionnaireMapper.toDTO(questionnaire));

        return questionnaireDTOList;
    }

    public void validateAndCompile(QuestionnaireDTO questionnaireDTO) {
        QuestionnaireValidationService questionnaireValidationService = new QuestionnaireValidationService();
        StatisticalReportBuilder statisticalReportBuilder = new StatisticalReportBuilder();
        DataContext dataContext = new DataContext();
        FileContext fileContext = new FileContext();
        List<StatisticalData> rawDataList = new ArrayList<>();
        List<StatisticalData> percentageList = new ArrayList<>();

        Questionnaire questionnaire = QuestionnaireMapper.toQuestionnaire(questionnaireDTO);

        for (int count = 0; count < questionnaire.targetAudience().respondingCustomers().size(); count++)
            questionnaireValidationService.validateQuestionnaireStringFormat(questionnaire.toString());

        for (Section section : questionnaire.sectionList()) {
            for (Question question : section.content()) {
                dataContext.setDataStrategy(new RawDataStrategy());

                List<StatisticalData> auxiliaryList = dataContext.executeStrategy(new Parameter(question));

                dataContext.setDataStrategy(new PercentageDataStrategy());
                rawDataList.addAll(auxiliaryList);
                percentageList.addAll(dataContext.executeStrategy(new DataParameter(question, auxiliaryList)));
            }
        }

        statisticalReportBuilder.setIdentifier();
        statisticalReportBuilder.setQuestionnaire(questionnaire);
        statisticalReportBuilder.setRawDataList(rawDataList);
        statisticalReportBuilder.setPercentageList(percentageList);

        StatisticalReport statisticalReport = statisticalReportBuilder.build();

        fileContext.setStrategy(new ExcelFileStrategy());
        fileContext.executeStrategy(statisticalReport);
        //questionnaireRepository.delete(questionnaire);
        statisticalReportRepository.save(statisticalReport);
    }
}
