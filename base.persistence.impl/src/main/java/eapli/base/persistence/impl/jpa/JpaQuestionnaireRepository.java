package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.common.domain.model.Identifier;
import eapli.base.questionnairemanagement.persistence.QuestionnaireRepository;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import java.util.List;

public class JpaQuestionnaireRepository extends JpaAutoTxRepository<Questionnaire, Identifier, Identifier> implements QuestionnaireRepository {
    public JpaQuestionnaireRepository(TransactionalContext context) {
        super(context, "Identifier");
    }

    public JpaQuestionnaireRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "Identifier");
    }

    @Override
    public Iterable<Questionnaire> answeredQuestionnaires() {
        //Query query = entityManager().createQuery("SELECT questionnaire FROM Questionnaire questionnaire WHERE SIZE(questionnaire.targetAudience.respondingCustomers) >= 30");
        List<Questionnaire> questionnaireList = (List<Questionnaire>) findAll();

        for (Questionnaire questionnaire : questionnaireList) {
            if (questionnaire.targetAudience().respondingCustomers().size() < 30)
                questionnaireList.remove(questionnaire);
        }

        return questionnaireList;
    }
}
