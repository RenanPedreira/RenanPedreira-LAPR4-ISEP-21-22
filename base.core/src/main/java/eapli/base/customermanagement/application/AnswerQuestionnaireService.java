package eapli.base.customermanagement.application;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.questionnairemanagement.application.QuestionnaireValidationService;
import eapli.base.questionnairemanagement.domain.Answer;
import eapli.base.questionnairemanagement.domain.Question;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.base.questionnairemanagement.domain.Section;
import eapli.base.questionnairemanagement.dto.QuestionDTO;
import eapli.base.questionnairemanagement.dto.QuestionMapper;
import eapli.base.questionnairemanagement.dto.QuestionnaireDTO;
import eapli.base.questionnairemanagement.dto.QuestionnaireMapper;
import eapli.base.questionnairemanagement.persistence.QuestionnaireRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.*;

public class AnswerQuestionnaireService {
    private CustomerRepository customerRepository;
    private AuthorizationService authorizationService;

    public AnswerQuestionnaireService() {
        customerRepository = PersistenceContext.repositories().customerRepository();
        authorizationService = AuthzRegistry.authorizationService();
    }

    public List<QuestionnaireDTO> listAssignedUnansweredQuestionnaire() {
        Customer customer = getCustomerBySystemUser();
        List<QuestionnaireDTO> questionnaireDTOList = new ArrayList<>();

        for (Questionnaire questionnaire : customer.history().unansweredQuestionnaires())
            questionnaireDTOList.add(QuestionnaireMapper.toDTO(questionnaire));

        return questionnaireDTOList;
    }

    public void validateAndPersistAnswers(QuestionnaireDTO questionnaireDTO,
                                          Map<QuestionDTO, LinkedList<String>> answersPerQuestionDTO) {
        Customer customer = getCustomerBySystemUser();
        Questionnaire questionnaire = QuestionnaireMapper.toQuestionnaire(questionnaireDTO);

        QuestionnaireValidationService validationService = new QuestionnaireValidationService();
        Map<Question, LinkedList<Answer>> answersPerQuestion = new HashMap<>();

        for (QuestionDTO questionDTO : answersPerQuestionDTO.keySet()) {
            for (String answer : answersPerQuestionDTO.get(questionDTO)) {
                Question question;

                if (answersPerQuestion.containsKey((question = QuestionMapper.toQuestion(questionDTO)))) {
                    LinkedList<Answer> answerList = answersPerQuestion.get(question);

                    answerList.add(new Answer(answer));
                    answersPerQuestion.put(question, answerList);
                } else {
                    LinkedList<Answer> answerList = new LinkedList<>();

                    answerList.add(new Answer(answer));
                    answersPerQuestion.put(question, answerList);
                }
            }
        }

        int answers = 0;

        for (LinkedList<Answer> answerList : answersPerQuestion.values()) {
            for (Answer answer : answerList)
                answers++;
        }

        while (answers != 0) {
            for (Section section : questionnaire.sectionList()) {
                for (Question question : section.content()) {
                    if (!answersPerQuestion.get(question).isEmpty()) {
                        LinkedList<Answer> answerList = answersPerQuestion.get(question);

                        question.addAnswer(answerList.getFirst());
                        answerList.remove(answerList.getFirst());
                        answersPerQuestion.put(question, answerList);
                        answers--;
                    }
                }
            }

            validationService.validateQuestionnaireStringFormat(questionnaire.toString());
        }

        questionnaire.targetAudience().respondingCustomers().add(customer);
        customer.history().answeredQuestionnaires().add(questionnaire);
        customer.history().unansweredQuestionnaires().remove(questionnaire);

        customerRepository.save(customer);
    }

    private Customer getCustomerBySystemUser() {
        SystemUser systemUser = authorizationService.session().get().authenticatedUser();
        return customerRepository.findByEmail(systemUser.email().toString()).get(0);
    }
}
