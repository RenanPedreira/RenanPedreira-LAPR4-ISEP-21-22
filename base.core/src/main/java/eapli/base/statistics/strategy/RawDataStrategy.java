package eapli.base.statistics.strategy;

import eapli.base.questionnairemanagement.domain.Answer;
import eapli.base.statistics.domain.StatisticalData;

import java.util.*;

public class RawDataStrategy implements DataStrategy {
    private static final String OTHER_OPTION = "Other (Please specify)";
    private static final String[] NUMERIC_OPTIONS = {"0","1","2","3","4","5"};
    private static final String QUESTION_TYPE = "Numeric";

    @Override
    public List<StatisticalData> execute(Parameter parameter) {
        List<StatisticalData> rawData = new ArrayList<>();

        if (parameter.getQuestionParameter().getOptionList() != null && !parameter.getQuestionParameter().getOptionList().isEmpty()) {
            String[] optionArray = new String[parameter.getQuestionParameter().getOptionList().size()];

            for (int count = 0; count < parameter.getQuestionParameter().getOptionList().size(); count++)
                optionArray[count] = parameter.getQuestionParameter().getOptionList().get(count).getMessage();

            rawData = generateRawData(optionArray, parameter.getQuestionParameter().answers());
        } else if (parameter.getQuestionParameter().getType().getType().replace(";", "").equals(QUESTION_TYPE))
            rawData = generateRawData(NUMERIC_OPTIONS, parameter.getQuestionParameter().answers());

        return rawData;
    }

    private List<StatisticalData> generateRawData(String[] optionArray, List<Answer> answers) {
        Map<String, Double> dataPerAnswer = new HashMap<>();
        List<StatisticalData> values = new ArrayList<>();

        for (String option : optionArray)
            dataPerAnswer.put(option.replace(";", ""),
                    Double.valueOf(0));

        for (Answer answer : answers) {
            if (dataPerAnswer.containsKey(answer.getContent()))
                dataPerAnswer.put(answer.getContent(),
                        dataPerAnswer.get(answer.getContent()).doubleValue() + 1);
            else
                dataPerAnswer.put(OTHER_OPTION, dataPerAnswer.get(OTHER_OPTION).doubleValue() + 1);
        }

        for (String option : optionArray)
            values.add(new StatisticalData(dataPerAnswer.get(option.replace(";", ""))));

        return values;
    }
}
