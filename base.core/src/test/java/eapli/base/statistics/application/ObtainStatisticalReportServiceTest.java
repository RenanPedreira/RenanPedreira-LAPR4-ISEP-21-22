package eapli.base.statistics.application;

import com.github.javafaker.Faker;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.common.util.Triple;
import eapli.base.customermanagement.domain.factory.CustomerBuilder;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.customermanagement.domain.model.History;
import eapli.base.grammarutils.QuestionnaireGrammarLexer;
import eapli.base.grammarutils.QuestionnaireGrammarParser;
import eapli.base.grammarutils.Visitor;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.questionnairemanagement.domain.Answer;
import eapli.base.questionnairemanagement.domain.Question;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.base.questionnairemanagement.domain.Section;
import eapli.base.questionnairemanagement.dto.QuestionnaireDTO;
import eapli.base.questionnairemanagement.dto.QuestionnaireMapper;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.RandomRawPassword;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObtainStatisticalReportServiceTest {


    private static final String[] QUESTION_ANSWERS1 = {"Male", "Female", "Non-Binary", "Prefer not to answer", "Agender", "Intergender", "Polygender", "Transgender"};
    private static final String[] QUESTION_ANSWERS2 = {"18 - 25", "26 - 50", "51 - 69", "Over 70", "Prefer not to answer"};
    private static final String[] QUESTION_ANSWERS3 = {"Less than an hour", "Between an hour and 3 hours", "Between 4 hours and 6 hours", "More than 7 hours"};
    private static final String[] QUESTION_ANSWERS4 = {"Drama", "Horror", "Romance", "Thriller", "Science Fiction", "Fantasy", "Historical"};

    private static final String REGION_CODE = "PT";
    private static final int MAX_ITERATION1 = 10;
    private static final int MAX_ITERATION2 = 50;
    private static final int MIN_ITERATION1 = 30;
    private static final int NUMBER_DIGITS = 9;
    private static final int MAX_VALUE = 1000;

    private ObtainStatisticalReportService obtainStatisticalReportService;
    private List<QuestionnaireDTO> questionnaireDTOList;





    @BeforeEach
    public void setup (){

        List <Customer> customerList = new ArrayList<>();
        obtainStatisticalReportService = new ObtainStatisticalReportService();


        for(int count = 0; count < 30; count++ )
        {
            customerList.add(automaticCustomerGenerator(automaticSystemUserGenerator(BaseRoles.CUSTOMER_ROLE,count),new History(new IdentifierGenerator().generateIdentifier(),new ArrayList<>(),new ArrayList<>())));
        }

        List<Questionnaire> questionnaireList = new ArrayList<>();
        questionnaireList.add(generateAnsweredQuestionnaire(customerList,customerList));

        questionnaireDTOList = new ArrayList<>();

        for(Questionnaire questionnaire : questionnaireList)
        {
            questionnaireDTOList.add(QuestionnaireMapper.toDTO(questionnaire));
            PersistenceContext.repositories().questionnaireRepository().save(questionnaire);
        }

    }

    private SystemUser automaticSystemUserGenerator(Role userRole, int number) {
        Faker faker = new Faker();
        Random random = new Random();
        String[] addressTypeArray = {"Delivery Address", "Billing Address"};
        SystemUserBuilder userBuilder = UserBuilderHelper.builder();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(faker.funnyName().name().replace(" ", "")).append(number);

        userBuilder.withUsername(stringBuilder.toString())
                .withPassword(new RandomRawPassword().toString())
                .withName(faker.name().firstName(), faker.name().lastName())
                .withEmail(faker.internet().emailAddress())
                .withRoles(userRole);

        return userBuilder.build();
    }

    private Customer automaticCustomerGenerator(SystemUser systemUser, History history) {
        Faker faker = new Faker();
        Random random = new Random();
        String[] addressTypeArray = {"Delivery Address", "Billing Address"};

        Map<Triple<String, String, Integer>, String> addressList = new HashMap<>();

        int num = 0;

        for (int cnt = 0; cnt < random.nextInt(MAX_ITERATION1); cnt++)
            addressList.put(new Triple<>(faker.address().streetName(), faker.address().cityName(), Integer.valueOf(faker.address().streetAddressNumber())),
                    addressTypeArray[random.nextBoolean() ? 0 : 1]);

        CustomerBuilder customerBuilder = new CustomerBuilder();
        customerBuilder.setSystemUser(systemUser);
        customerBuilder.setIdentifier();
        customerBuilder.setPhoneNumber(String.valueOf(PhoneNumberUtil.getInstance().getExampleNumber(REGION_CODE).getNationalNumber()));
        customerBuilder.setIdVAT(faker.number().digits(NUMBER_DIGITS));
        customerBuilder.setBirthdate(LocalDate.EPOCH);
        customerBuilder.setGender(faker.demographic().sex());
        customerBuilder.setAddressList(addressList);
        customerBuilder.setHistory(history);


        return customerBuilder.build();
    }


    private Questionnaire generateAnsweredQuestionnaire(List<Customer> respondingCustomers, List<Customer> targetCustomers) {
        Random random = new Random();
        List<Answer> answerList1 = new ArrayList<>();
        List<Answer> answerList2 = new ArrayList<>();
        List<Answer> answerList3 = new ArrayList<>();
        List<Answer> answerList4 = new ArrayList<>();

        QuestionnaireGrammarLexer lexer = null;

        try {
            lexer = new QuestionnaireGrammarLexer(CharStreams.fromFileName("./docs/SprintD/US3002/Questionnaire3.txt"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        CommonTokenStream token = new CommonTokenStream(lexer);
        QuestionnaireGrammarParser parser = new QuestionnaireGrammarParser(token);
        Visitor visitor = new Visitor();
        ParseTree tree = parser.prog();

        visitor.visit(tree);
        Questionnaire questionnaire = QuestionnaireMapper.toQuestionnaire(visitor.getQuestionnaireDTO());

        int iteration1 = respondingCustomers.size();
        int value;

        for (int count = 0; count < iteration1; count++) {
            value = random.nextInt(MAX_VALUE);

            if (value <= 400)
                answerList1.add(new Answer(QUESTION_ANSWERS1[0]));
            if (value > 400 && value <= 800)
                answerList1.add(new Answer(QUESTION_ANSWERS1[1]));
            if (value > 800 && value <= 900)
                answerList1.add(new Answer(QUESTION_ANSWERS1[2]));
            if (value > 900 && value <= 920)
                answerList1.add(new Answer(QUESTION_ANSWERS1[3]));
            if (value > 920 && value <= 940)
                answerList1.add(new Answer(QUESTION_ANSWERS1[4]));
            if (value > 940 && value <= 960)
                answerList1.add(new Answer(QUESTION_ANSWERS1[5]));
            if (value > 960 && value <= 980)
                answerList1.add(new Answer(QUESTION_ANSWERS1[6]));
            if (value > 980 && value <= 1000)
                answerList1.add(new Answer(QUESTION_ANSWERS1[7]));
        }

        for (int count = 0; count < iteration1; count++) {
            value = random.nextInt(MAX_VALUE);

            if (value <= 400)
                answerList2.add(new Answer(QUESTION_ANSWERS2[0]));
            if (value > 400 && value <= 800)
                answerList2.add(new Answer(QUESTION_ANSWERS2[1]));
            if (value > 800 && value <= 900)
                answerList2.add(new Answer(QUESTION_ANSWERS2[2]));
            if (value > 900 && value <= 950)
                answerList2.add(new Answer(QUESTION_ANSWERS2[3]));
            if (value > 950 && value <= 1000)
                answerList2.add(new Answer(QUESTION_ANSWERS2[1]));
        }

        for (int count = 0; count < iteration1; count++) {
            value = random.nextInt(MAX_VALUE);

            if (value <= 400)
                answerList3.add(new Answer(QUESTION_ANSWERS3[0]));
            if (value > 400 && value <= 800)
                answerList3.add(new Answer(QUESTION_ANSWERS3[1]));
            if (value > 800 && value <= 900)
                answerList3.add(new Answer(QUESTION_ANSWERS3[2]));
            if (value > 900 && value <= 1000)
                answerList3.add(new Answer(QUESTION_ANSWERS3[3]));
        }

        for (int count = 0; count < iteration1 + MAX_ITERATION1; count++) {
            value = random.nextInt(MAX_VALUE);

            if (value <= 200)
                answerList4.add(new Answer(QUESTION_ANSWERS4[0]));
            if (value > 200 && value <= 400)
                answerList4.add(new Answer(QUESTION_ANSWERS4[1]));
            if (value > 400 && value <= 600)
                answerList4.add(new Answer(QUESTION_ANSWERS4[2]));
            if (value > 600 && value <= 800)
                answerList4.add(new Answer(QUESTION_ANSWERS4[3]));
            if (value > 800 && value <= 900)
                answerList4.add(new Answer(QUESTION_ANSWERS4[4]));
            if (value > 900 && value <= 950)
                answerList4.add(new Answer(QUESTION_ANSWERS4[5]));
            if (value > 950 && value <= 1000)
                answerList4.add(new Answer(QUESTION_ANSWERS4[6]));
        }

        for (Customer customer : targetCustomers)
            questionnaire.targetAudience().addCustomerToTargetAudience(customer);

        for (Customer customer : respondingCustomers) {
            questionnaire.targetAudience().newCustomerResponse(customer);
        }

        int count = 0;

        for (Section section : questionnaire.sectionList()) {
            for (Question question : section.content()) {
                question.clearAnswers();

                if (count == 0) {
                    for (Answer answer : answerList1)
                        question.addAnswer(answer);
                }
                if (count == 1) {
                    for (Answer answer : answerList2)
                        question.addAnswer(answer);
                }
                if (count == 2) {
                    for (Answer answer : answerList3)
                        question.addAnswer(answer);
                }
                if (count == 3) {
                    for (Answer answer : answerList4)
                        question.addAnswer(answer);
                }

                count++;
            }
        }

        return questionnaire;
    }


    @Test
    public void ensureRetrievalOfAnsweredQuestionnaires(){

        List <QuestionnaireDTO> expectedResult = questionnaireDTOList ;

        assertEquals (expectedResult,obtainStatisticalReportService.listAllAnsweredQuestionnaires());

    }

    @Test
    public void listAllAnsweredQuestionnaire(){

        List <Questionnaire> expectedResult = new ArrayList<>();
        assertEquals(expectedResult,obtainStatisticalReportService.listAllAnsweredQuestionnaires());

    }




}