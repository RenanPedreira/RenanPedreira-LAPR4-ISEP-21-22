package eapli.base.statistics.strategy;

import eapli.base.questionnairemanagement.domain.Answer;
import eapli.base.questionnairemanagement.domain.Message;
import eapli.base.questionnairemanagement.domain.Question;
import eapli.base.questionnairemanagement.domain.Type;
import eapli.base.statistics.domain.StatisticalData;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RawDataStrategyTest {

    private static final String[] answerArray = { "Nokia", "Apple", "Google" };
    private static List <StatisticalData> statisticalDataList;
    private static DataContext dataContext;
    private static Parameter parameter;






    @BeforeAll
    public static void  setup() {

        dataContext = new DataContext();
        statisticalDataList = new ArrayList<>();
        LinkedList <Answer> answerList = new LinkedList<>();
        Random random = new Random();
        List<Message> optionList = new ArrayList<>();

        for(String option : answerArray){
            optionList.add(new Message(option));
        }


        answerList.add(new Answer("Nokia"));
        answerList.add(new Answer("Nokia"));
        answerList.add(new Answer("Nokia"));
        answerList.add(new Answer("Nokia"));
        answerList.add(new Answer("Google"));
        answerList.add(new Answer("Apple"));
        answerList.add(new Answer("Google"));
        answerList.add(new Answer("Apple"));



        statisticalDataList.add(new StatisticalData(4));
        statisticalDataList.add(new StatisticalData(2));
        statisticalDataList.add(new StatisticalData(2));



        Question question = new Question(null, null,null,null,new Type("Single-Choice"),optionList,null,null,answerList);
        parameter = new Parameter(question);

        dataContext.setDataStrategy(new RawDataStrategy());

    }



    @Test
    public void ensureValidInstantiationOfStatisticalData(){

        List<StatisticalData> expectedResult = statisticalDataList;
        assertEquals(expectedResult,dataContext.executeStrategy(parameter));






    }
}