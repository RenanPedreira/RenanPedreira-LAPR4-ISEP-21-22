package eapli.base.statistics.strategy;

import eapli.base.questionnairemanagement.domain.Question;

public class Parameter {
    private Question questionParameter;

    public Parameter(Question questionParameter) {
        this.questionParameter = questionParameter;
    }

    public Question getQuestionParameter() {
        return questionParameter;
    }
}
