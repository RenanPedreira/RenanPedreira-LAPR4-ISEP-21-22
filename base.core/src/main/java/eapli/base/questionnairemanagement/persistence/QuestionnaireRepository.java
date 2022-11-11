package eapli.base.questionnairemanagement.persistence;

import eapli.base.common.domain.model.Identifier;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface QuestionnaireRepository extends DomainRepository<Identifier,Questionnaire> {
    Iterable<Questionnaire> answeredQuestionnaires();
}
