/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.infrastructure.bootstrapers;

import com.github.javafaker.Faker;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import eapli.base.agvmanagement.domain.Weight;
import eapli.base.agvmanagement.factory.AGVBuilder;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.cartmanagement.domain.Cart;
import eapli.base.cartmanagement.persistence.CartRepository;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.common.domain.model.Address;
import eapli.base.common.util.Triple;
import eapli.base.customermanagement.domain.factory.CustomerBuilder;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.customermanagement.domain.model.History;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.grammarutils.QuestionnaireGrammarLexer;
import eapli.base.grammarutils.QuestionnaireGrammarParser;
import eapli.base.grammarutils.Visitor;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.factory.OrderFactory;
import eapli.base.ordermanagement.persistence.OrderRepository;
import eapli.base.productmanagement.domain.Categorya.Alpha;
import eapli.base.productmanagement.domain.Categorya.Category;
import eapli.base.productmanagement.domain.Categorya.CategoryFactory;
import eapli.base.productmanagement.domain.Categorya.Description;
import eapli.base.productmanagement.domain.Producto.*;
import eapli.base.productmanagement.persistence.CategoryRepository;
import eapli.base.productmanagement.persistence.ProductRepository;
import eapli.base.questionnairemanagement.domain.Answer;
import eapli.base.questionnairemanagement.domain.Question;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.base.questionnairemanagement.domain.Section;
import eapli.base.questionnairemanagement.dto.QuestionnaireMapper;
import eapli.base.questionnairemanagement.factory.QuestionBuilder;
import eapli.base.questionnairemanagement.persistence.QuestionnaireRepository;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDock;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDockId;
import eapli.base.warehousemanagement.domain.Aisle.Accessibility;
import eapli.base.warehousemanagement.domain.Aisle.Square;
import eapli.base.warehousemanagement.domain.Assignment.Assignment;
import eapli.base.warehousemanagement.domain.Assignment.AssignmentStatus;
import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.domain.Warehouse.WarehouseName;
import eapli.base.warehousemanagement.domain.Warehouse.WarehousePlant;
import eapli.base.warehousemanagement.repository.AgvDockRepository;
import eapli.base.warehousemanagement.repository.AssignmentRepository;
import eapli.base.warehousemanagement.repository.WarehouseRepository;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Base Bootstrapping data app
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings("squid:S106")
public class BaseBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseBootstrapper.class);

    private static final String POWERUSER_A1 = "poweruserA1";
    private static final String POWERUSER = "poweruser";

    private static final String SALESCLERK_A1 = "S123456";
    private static final String SALESCLERK = "s";

    private static final String WAREHOUSEEMPLOYEE_A1 = "wemployeeA1";
    private static final String WAREHOUSEEMPLOYEE = "wemployee";

    private static final String PROJECTMANAGER = "projectmanager";
    private static final String PROJECTMANAGER_A1 = "projectmanagerA1";
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

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private final CategoryRepository categoryRepository = PersistenceContext.repositories().categories();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    private final WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouses();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customerRepository();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();
    private final AgvDockRepository agvDockRepository = PersistenceContext.repositories().agvDocks();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final CartRepository cartRepository = PersistenceContext.repositories().cartRepository();
    private final QuestionnaireRepository questionnaireRepository = PersistenceContext.repositories().questionnaireRepository();
    private final AssignmentRepository assignmentRepository = PersistenceContext.repositories().assignments();



    @Override
    public boolean execute() {
        // declare bootstrap actions
        final Action[] actions = {new MasterUsersBootstrapper(),};

        Random random = new Random();

        registerPowerUser();
        registerSalesClerk();
        registerWarehouseEmployee();
        registerSalesManager();

        registerSystemUser(BaseRoles.CUSTOMER_ROLE, 0);
        //Customer customer = registerCustomer(systemUser);
        Customer customer = registerCustomer();
        Customer customer2 = registerCustomer2();

        registerOrdersToBePrepared(customer, customer2);
        registerOrders(customer2);

        registerCategory1();
        registerCategory2();
        registerCategory3();

        registerProduct1();
        registerProduct2();
        registerProduct3();
        registerProduct4();
        registerProduct5();

        registerWarehouse1();
        registerWarehouse2();

        registerAGV1();
        registerAGV2();


        /** Digital Twin */
        registerWarehouse();
        registerDigitalTwin1();
        registerDigitalTwin2();
        //registerDigitalTwin3();
        ordersToAgvs();


        for (int cnt = 0; cnt < MAX_ITERATION1; cnt++)
            registerOrder(customer, null);

        List<Customer> customerList = new ArrayList<>();
        List<Questionnaire> unansweredQuestionnaires = new ArrayList<>();

        for (int count = 0; count < random.nextInt(MAX_ITERATION2 - MIN_ITERATION1) + MIN_ITERATION1; count++)
            customerList.add(automaticCustomerGenerator(automaticSystemUserGenerator(BaseRoles.CUSTOMER_ROLE, count), new History(new IdentifierGenerator().generateIdentifier(), new ArrayList<>(), new ArrayList<>())));

        Questionnaire questionnaire = registerQuestionnaire(customerList, customerList);

        unansweredQuestionnaires.add(questionnaire);
        automaticCustomerGenerator(automaticSystemUserGenerator(BaseRoles.CUSTOMER_ROLE, 0), new History(new IdentifierGenerator().generateIdentifier(), new ArrayList<>(), unansweredQuestionnaires));

        authenticateForBootstrapping();

        // execute all bootstrapping
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    private void ordersToAgvs(){
        List<Product> products = (List<Product>)productRepository.findAll();
        List<Customer> customers = (List<Customer>)customerRepository.findAll();

        OrderNumericIdentifier id1 = new OrderNumericIdentifier(9111);
        OrderNumericIdentifier id2 = new OrderNumericIdentifier(9112);
        OrderNumericIdentifier id3 = new OrderNumericIdentifier(9113);
        Address address1 = new Address("Billing Address", "Jetstream Street", "Ratanaba", 986);
        Address address2 = new Address("Delivery Address", "Jetstream Street", "Ratanaba", 987);
        PaymentMethod paymentMethod = new PaymentMethod("Paypal");
        Price price = new Price(100.00);
        Weight weight = new Weight(100.00);

        Ordre order1 = new Ordre(id1, customers.get(0), address1, address2, paymentMethod, price, weight);
        Ordre order2 = new Ordre(id2, customers.get(1), address1, address2, paymentMethod, price, weight);
        Ordre order3 = new Ordre(id3, customers.get(1), address1, address2, paymentMethod, price, weight);


        order1.addOrderItem(new OrderItem(products.get(0), new Quantity(1)));
        order1.addOrderItem(new OrderItem(products.get(1), new Quantity(2)));
        //order1.addOrderItem(new OrderItem(products.get(2), new Quantity(3)));
        orderRepository.save(order1);

        order2.addOrderItem(new OrderItem(products.get(3), new Quantity(1)));
        order2.addOrderItem(new OrderItem(products.get(4), new Quantity(2)));
        //order2.addOrderItem(new OrderItem(products.get(0), new Quantity(3)));
        orderRepository.save(order2);

        order3.addOrderItem(new OrderItem(products.get(1), new Quantity(1)));
        order3.addOrderItem(new OrderItem(products.get(2), new Quantity(2)));
        //order3.addOrderItem(new OrderItem(products.get(3), new Quantity(3)));
        orderRepository.save(order3);

        Assignment assignment1 = new Assignment(new Identifier("CU-111111111"), agvRepository.getAgvByWarehouse("Armstrong Warehouse").get(0), AssignmentStatus.DOING, ((List<Ordre>)orderRepository.findAll()).get(0));
        Assignment assignment2 = new Assignment(new Identifier("CU-211111111"), agvRepository.getAgvByWarehouse("Armstrong Warehouse").get(1), AssignmentStatus.DOING, ((List<Ordre>)orderRepository.findAll()).get(1));
        //Assignment assignment3 = new Assignment(new Identifier("CU-311111111"), agvRepository.getAgvByWarehouse("Armstrong Warehouse").get(2), AssignmentStatus.DOING, ((List<Ordre>)orderRepository.findAll()).get(2));
        assignmentRepository.save(assignment1);
        assignmentRepository.save(assignment2);
        //assignmentRepository.save(assignment3);
    }


    /** Digital twin 1 */
    private void registerDigitalTwin1() {
        AGVBuilder builder = new AGVBuilder();

        builder.setIdentifier();
        builder.setModel("Sundowner");
        builder.setTechnicalDescription("They harden in response to physical trauma.");
        builder.setAutonomyStatus(Duration.ofHours(4));
        builder.setTaskStatus("ASSIGNED");

        AgvDock dock = new AgvDock(new AgvDockId("421"),
                new Square(1, 1),
                new Square(2, 1),
                new Square(3, 1),
                new Accessibility("Senator Armstrong"),
                warehouseRepository.getWarehouseByName2("Armstrong Warehouse"));

        builder.setCapacity(900);

        agvDockRepository.save(dock);
        builder.setBaseLocation(dock);

        agvRepository.save(builder.build());
    }

    /** Digital twin 2 */
    private void registerDigitalTwin2() {
        AGVBuilder builder = new AGVBuilder();

        builder.setIdentifier();
        builder.setModel("Mistral");
        builder.setTechnicalDescription("They harden in response to physical trauma.");
        builder.setAutonomyStatus(Duration.ofHours(4));
        builder.setTaskStatus("ASSIGNED");

        AgvDock dock = new AgvDock(new AgvDockId("422"),
                new Square(4, 1),
                new Square(5, 1),
                new Square(6, 1),
                new Accessibility("Senator Armstrong"),
                warehouseRepository.getWarehouseByName2("Armstrong Warehouse"));

        builder.setCapacity(900);

        agvDockRepository.save(dock);
        builder.setBaseLocation(dock);

        agvRepository.save(builder.build());
    }

    /** Digital twin 3 */
    private void registerDigitalTwin3() {
        AGVBuilder builder = new AGVBuilder();

        builder.setIdentifier();
        builder.setModel("Monsoon");
        builder.setTechnicalDescription("They harden in response to physical trauma.");
        builder.setAutonomyStatus(Duration.ofHours(4));
        builder.setTaskStatus("ASSIGNED");

        AgvDock dock = new AgvDock(new AgvDockId("423"),
                new Square(7, 1),
                new Square(8, 1),
                new Square(9, 1),
                new Accessibility("Senator Armstrong"),
                warehouseRepository.getWarehouseByName2("Armstrong Warehouse"));

        builder.setCapacity(900);

        agvDockRepository.save(dock);
        builder.setBaseLocation(dock);

        agvRepository.save(builder.build());
    }

    private void registerAGV1() {
        AGVBuilder builder = new AGVBuilder();

        builder.setIdentifier();
        builder.setModel("Nano");
        builder.setTechnicalDescription("They harden in response to physical trauma.");
        builder.setAutonomyStatus(Duration.ofHours(4));
        builder.setTaskStatus("FREE");

        AgvDock dock = new AgvDock(new AgvDockId("420"),
                new Square(4, 12),
                new Square(23, 11),
                new Square(16, 8),
                new Accessibility("Senator Armstrong"),
                new Warehouse(new WarehouseName("Oporto Central Warehouse")));

        builder.setCapacity(900);

        agvDockRepository.save(dock);
        builder.setBaseLocation(dock);

        agvRepository.save(builder.build());
    }

    private void registerAGV2() {
        AGVBuilder builder = new AGVBuilder();

        builder.setIdentifier();
        builder.setModel("Big");
        builder.setTechnicalDescription("Very high quality");
        builder.setAutonomyStatus(Duration.ofHours(2));
        builder.setTaskStatus("FREE");

        AgvDock dock = new AgvDock(new AgvDockId("21"),
                new Square(7, 17),
                new Square(83, 13),
                new Square(26, 18),
                new Accessibility("w+"),
                new Warehouse(new WarehouseName("Busan Central Warehouse")));

        builder.setCapacity(990);

        agvDockRepository.save(dock);
        builder.setBaseLocation(dock);

        agvRepository.save(builder.build());
    }


    private void registerOrder(Customer customer, List<OrderItem> itemList) {
        Ordre order = automaticOrderGenerator(customer, itemList);

        orderRepository.save(order);
    }

    /**
     * register a power user directly in the persistence layer as we need to
     * circumvent authorisations in the Application Layer
     */
    private boolean registerPowerUser() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(POWERUSER).withPassword(POWERUSER_A1).withName("joe", "power")
                .withEmail("joe@email.org").withRoles(BaseRoles.POWER_USER);
        final SystemUser newUser = userBuilder.build();

        SystemUser poweruser;
        try {
            poweruser = userRepository.save(newUser);
            assert poweruser != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerSalesClerk() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(SALESCLERK).withPassword(SALESCLERK_A1).withName("Senator", "Armstrong")
                .withEmail("armstrong@email.org").withRoles(BaseRoles.SALES_CLERK_ROLE);
        final SystemUser newUser = userBuilder.build();

        SystemUser salesClerk;
        try {
            salesClerk = userRepository.save(newUser);
            assert salesClerk != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerSalesManager() {
        Faker faker = new Faker();
        SystemUserBuilder systemUserBuilder = UserBuilderHelper.builder();

        systemUserBuilder.withUsername(faker.name().username())
                .withPassword(new RandomRawPassword().toString())
                .withName(faker.name().firstName(), faker.name().lastName())
                .withEmail(faker.internet().emailAddress())
                .withRoles(BaseRoles.SALES_MANAGER);

        SystemUser systemUser = systemUserBuilder.build();
        SystemUser salesManager;

        try {
            salesManager = userRepository.save(systemUser);
            assert salesManager != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", systemUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }

    }

    private boolean registerWarehouseEmployee() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(WAREHOUSEEMPLOYEE).withPassword(WAREHOUSEEMPLOYEE_A1).withName("Joanita", "Vaz")
                .withEmail("despacito@email.org").withRoles(BaseRoles.WAREHOUSE_EMPLOYEE);
        final SystemUser newUser = userBuilder.build();

        SystemUser salesClerk;
        try {
            salesClerk = userRepository.save(newUser);
            assert salesClerk != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerProjectManager() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(PROJECTMANAGER).withPassword(PROJECTMANAGER_A1).withName("Mikael", "Chen")
                .withEmail("mikael@email.org").withRoles(BaseRoles.PROJECT_MANAGER);
        final SystemUser newUser = userBuilder.build();

        SystemUser projectManager;
        try {
            projectManager = userRepository.save(newUser);
            assert projectManager != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private SystemUser registerSystemUser(Role role, int number) {
        SystemUser systemUser = automaticSystemUserGenerator(role, number);

        return userRepository.save(systemUser);
    }

    //private Customer registerCustomer(SystemUser systemUser) {
    private Customer registerCustomer() {
        //Customer customer = automaticCustomerGenerator(systemUser);

        SystemUserBuilder builder = UserBuilderHelper.builder();

        SystemUser systemUser = builder.withUsername("Eri")
                .withPassword("E123456")
                .withName("Eri", "Lops")
                .withEmail("eri@america.com")
                .withRoles(BaseRoles.CUSTOMER_ROLE).build();

        Customer customer;
        try {
            systemUser = userRepository.save(systemUser);

            Map<Triple<String, String, Integer>, String> addressList = new HashMap<>();

            addressList.put(new Triple<>("Senator Armstrong Avenue", "Nano Machines", 1), "Delivery Address");
            addressList.put(new Triple<>("Raiden Street", "Nano Machines", 999), "Billing Address");

            CustomerBuilder builder1 = new CustomerBuilder();
            builder1.setSystemUser(systemUser);

            builder1.setIdentifier("CU-132465798");
            builder1.setPhoneNumber("+351 213456785");
            builder1.setIdVAT("456785542");
            builder1.setBirthdate(LocalDate.EPOCH);
            builder1.setGender("FEMALE");
            builder1.setAddressList(addressList);

            customer = customerRepository.save(builder1.build());
            Cart cart = new Cart(customer);
            cartRepository.save(cart);

            assert customer != null;
            return customer;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            return null;
        }
        /*
        customer = customerRepository.save(customer);
        Cart cart = new Cart(customer);
        cartRepository.save(cart);
        return customer;
        */
    }

    private Customer registerCustomer2() {
        SystemUserBuilder builder = UserBuilderHelper.builder();

        SystemUser systemUser = builder.withUsername("Faker")
                .withPassword("F223456")
                .withName("Lee", "Sanghyeok")
                .withEmail("faker@gmail.com")
                .withRoles(BaseRoles.CUSTOMER_ROLE).build();

        Customer customer;
        try {
            systemUser = userRepository.save(systemUser);

            Map<Triple<String, String, Integer>, String> addressList = new HashMap<>();

            addressList.put(new Triple<>("Gwanghwamun Avenue", "Busan", 3), "Delivery Address");
            addressList.put(new Triple<>("Hongdae Street", "Seoul", 99), "Billing Address");

            CustomerBuilder builder1 = new CustomerBuilder();
            builder1.setSystemUser(systemUser);

            builder1.setIdentifier("CU-132433798");
            builder1.setPhoneNumber("+351 213489785");
            builder1.setIdVAT("456785576");
            builder1.setBirthdate(LocalDate.EPOCH);
            builder1.setGender("MALE");
            builder1.setAddressList(addressList);
            builder1.setHistory(new History(new IdentifierGenerator().generateIdentifier(), new ArrayList<>(), new ArrayList<>()));

            customer = customerRepository.save(builder1.build());
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            return null;
        }
        return customer;

    }

    private boolean registerCategory1() {
        final CategoryFactory pf = new CategoryFactory();
        pf.description(new Description("Perfume"));
        pf.alpha(new Alpha("PRF01"));
        categoryRepository.save(pf.build());
        return true;
    }

    private boolean registerCategory2() {
        final CategoryFactory pf = new CategoryFactory();
        pf.description(new Description("Book"));
        pf.alpha(new Alpha("BOK02"));
        categoryRepository.save(pf.build());
        return true;
    }

    private boolean registerCategory3() {
        final CategoryFactory pf = new CategoryFactory();
        pf.description(new Description("Car"));
        pf.alpha(new Alpha("CAR03"));
        categoryRepository.save(pf.build());
        return true;
    }

    private boolean registerProduct1() {
        List<Category> list = (List<Category>) categoryRepository.findAll();
        Category cat = list.get(0);
        /*List<Row> list1 = (List<Row>) rowRepository.getAllRow();
        Row row = list1.get(0);*/


        final ProductFactory pf = new ProductFactory();
        pf.internal(new InternalCode("11111111111111111111111"));
        pf.name(new ProductName("L'Stranger"));
        pf.shortD(new ShortDescription("A perfume"));
        pf.longD(new LongDescription("A French perfume ---------------"));
        pf.tecD(new TechnicalDescription("A French sweet perfume"));
        pf.brand(new Brand("Mistral"));
        pf.location(new Location("COCO"));
        pf.barCode(new Barcode("1234512345123"));
        pf.prodCode(new ProductionCode("MST-111"));
        pf.reference(new Reference("Mistral-Stranger"));
        pf.price(new Price(35.00));
        pf.category(cat);
        pf.photo(null);
        productRepository.save(pf.build());
        return true;
    }

    private boolean registerProduct2() {
        List<Category> list = (List<Category>) categoryRepository.findAll();
        Category cat = list.get(1);
       /* List<Row> list1 = (List<Row>) rowRepository.getAllRow();
        Row row = list1.get(1);*/


        final ProductFactory pf = new ProductFactory();
        pf.internal(new InternalCode("21111111111111111111111"));
        pf.name(new ProductName("Stains of Time"));
        pf.shortD(new ShortDescription("A book"));
        pf.barCode(new Barcode("2234512345123"));
        pf.longD(new LongDescription("An English book---------------"));
        pf.tecD(new TechnicalDescription("A English suspense book"));
        pf.brand(new Brand("Monsoon"));
        pf.location(new Location("COCO"));
        pf.prodCode(new ProductionCode("MON-222"));
        pf.reference(new Reference("Monsoon-Stains"));
        pf.price(new Price(25.00));
        pf.category(cat);
        pf.photo(null);
        productRepository.save(pf.build());
        return true;
    }

    private boolean registerProduct3() {
        List<Category> list = (List<Category>) categoryRepository.findAll();
        Category cat = list.get(2);
        //  List<Row> list1 = (List<Row>) rowRepository.getAllRow();
        //  Row row = list1.get(2);

        final ProductFactory pf = new ProductFactory();
        pf.internal(new InternalCode("31111111111111111111111"));
        pf.name(new ProductName("Red Sun"));
        pf.barCode(new Barcode("3234512345123"));
        pf.shortD(new ShortDescription("A car"));
        pf.longD(new LongDescription("An American car---------------"));
        pf.tecD(new TechnicalDescription("An American invincible car"));
        pf.brand(new Brand("Sundowner"));
        pf.location(new Location("COCO"));
        pf.prodCode(new ProductionCode("SUN-333"));
        pf.reference(new Reference("Sundowner-RedSun"));
        pf.price(new Price(500.00));
        pf.category(cat);
        pf.photo(null);
        productRepository.save(pf.build());
        return true;
    }

    private boolean registerProduct4() {
        List<Category> list = (List<Category>) categoryRepository.findAll();
        Category cat = list.get(1);
        //  List<Row> list1 = (List<Row>) rowRepository.getAllRow();
        //  Row row = list1.get(3);

        final ProductFactory pf = new ProductFactory();
        pf.internal(new InternalCode("41111111111111111111111"));
        pf.name(new ProductName("The Only Thing I Know for Real"));
        pf.shortD(new ShortDescription("A book"));
        pf.barCode(new Barcode("4234512345123"));
        pf.longD(new LongDescription("A Brazilian book---------------"));
        pf.tecD(new TechnicalDescription("A Brazilian romance book"));
        pf.brand(new Brand("Jetstream Sam"));
        pf.location(new Location("COCO"));
        pf.prodCode(new ProductionCode("JET-444"));
        pf.reference(new Reference("Jetstream-Thing"));
        pf.price(new Price(30.00));
        pf.category(cat);
        pf.photo(null);
        productRepository.save(pf.build());
        return true;
    }

    private boolean registerProduct5() {
        List<Category> list = (List<Category>) categoryRepository.findAll();
        Category cat = list.get(2);
        //   List<Row> list1 = (List<Row>) rowRepository.getAllRow();
        //  Row row = list1.get(4);

        final ProductFactory pf = new ProductFactory();
        pf.internal(new InternalCode("51111111111111111111111"));
        pf.name(new ProductName("Bladewolf"));
        pf.barCode(new Barcode("5234512345123"));
        pf.shortD(new ShortDescription("A car"));
        pf.longD(new LongDescription("A Brazilian car---------------"));
        pf.tecD(new TechnicalDescription("A Brazilian racing car"));
        pf.brand(new Brand("Jetstream Sam"));
        pf.location(new Location("COCO"));
        pf.prodCode(new ProductionCode("JET-555"));
        pf.reference(new Reference("Jetstream-BladeWolf"));
        pf.price(new Price(300.00));
        pf.category(cat);
        pf.photo(null);
        productRepository.save(pf.build());
        return true;
    }


    private boolean registerWarehouse1() {
        WarehouseName n = new WarehouseName("Algarve West Warehouse");
        Warehouse w = new Warehouse(n);

        warehouseRepository.save(w);
        return true;
    }

    private boolean registerWarehouse2() {
        WarehouseName n = new WarehouseName("Oporto Central Warehouse");
        Warehouse w = new Warehouse(n);

        warehouseRepository.save(w);
        return true;
    }

    private boolean registerWarehouse() {
        WarehouseName n = new WarehouseName("Armstrong Warehouse");
        Warehouse w = new Warehouse(n);

        w.setPlant(new WarehousePlant("The largest warehouse of America", 50, 50, 10, "meters"));
        warehouseRepository.save(w);
        return true;
    }


    /**
     * authenticate a super user to be able to register new users
     */
    protected void authenticateForBootstrapping() {
        authenticationService.authenticate(POWERUSER, POWERUSER_A1);
        Invariants.ensure(authz.hasSession());
    }

    private String nameOfEntity(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
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

        customerRepository.save(customerBuilder.build());

        return customerBuilder.build();
    }

    private Ordre automaticOrderGenerator(Customer customer, List<OrderItem> itemList) {
        Faker faker = new Faker();
        OrderFactory orderBuilder = new OrderFactory();
        Random random = new Random();
        int min = 100;
        int maxPrice = 200000;
        int maxWeight = 100000;

        orderBuilder.setIdentifier(Integer.valueOf(faker.number().digits(NUMBER_DIGITS)));
        orderBuilder.setCustomer(customer);
        orderBuilder.setBillingAddress(faker.address().streetName(),
                faker.address().cityName(),
                Integer.valueOf(faker.address().streetAddressNumber()));
        orderBuilder.setDeliveryAddress(faker.address().streetName(),
                faker.address().cityName(),
                Integer.valueOf(faker.address().streetAddressNumber()));
        orderBuilder.setPaymentMethod(faker.company().name());

        orderBuilder.setStatus(OrderStatus.values()[random.nextInt(OrderStatus.values().length - 1)].name());
        orderBuilder.setItemList(itemList);
        orderBuilder.setTotalPrice(new Price(ThreadLocalRandom.current().nextInt(min, maxPrice) / 100d));
        orderBuilder.setTotalWeight(new Weight(ThreadLocalRandom.current().nextInt(min, maxWeight) / 100d));

        return orderBuilder.build();
    }

    private void registerOrdersToBePrepared(Customer customer1, Customer customer2) {
        Faker faker = new Faker();
        OrderFactory orderBuilder = new OrderFactory();
        Random random = new Random();
        int min = 100;
        int maxPrice = 200000;
        int maxWeight = 100000;

        for (int i = 0; i < 2; i++) {
            //if (i == 0) {
                orderBuilder.setCustomer(customer1);
           // } else {
             //   orderBuilder.setCustomer(customer2);
            //}
            orderBuilder.setIdentifier(Integer.valueOf(faker.number().digits(NUMBER_DIGITS)));
            orderBuilder.setBillingAddress(faker.address().streetName(),
                    faker.address().cityName(),
                    Integer.valueOf(faker.address().streetAddressNumber()));
            orderBuilder.setDeliveryAddress(faker.address().streetName(),
                    faker.address().cityName(),
                    Integer.valueOf(faker.address().streetAddressNumber()));
            orderBuilder.setPaymentMethod(faker.company().name());

            orderBuilder.setStatus(OrderStatus.TOBEPREPARED.name());
            orderBuilder.setTotalPrice(new Price(ThreadLocalRandom.current().nextInt(min, maxPrice) / 100d));
            orderBuilder.setTotalWeight(new Weight(ThreadLocalRandom.current().nextInt(min, maxWeight) / 100d));

            Ordre order = orderBuilder.build();
            orderRepository.save(order);
        }
    }

    private Questionnaire registerQuestionnaire(List<Customer> targetCustomers, List<Customer> respondingCustomers) {
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

        questionnaireRepository.save(questionnaire);

        return questionnaire;
    }

    private void registerOrders(Customer customer) {
        Faker faker = new Faker();
        OrderFactory orderBuilder = new OrderFactory();
        Random random = new Random();
        int min = 100;
        int maxPrice = 20000;
        int maxWeight = 10000;

        for (int i = 0; i < 2; i++) {

            orderBuilder.setCustomer(customer);
            orderBuilder.setIdentifier(Integer.valueOf(faker.number().digits(NUMBER_DIGITS)));
            orderBuilder.setBillingAddress("Samseong Road", "Gangnam", 120);
            orderBuilder.setDeliveryAddress("Samseong Road", "Gangnam", 120);
            orderBuilder.setPaymentMethod("Bitcoin");

            orderBuilder.setStatus(OrderStatus.values()[random.nextInt(OrderStatus.values().length - 1)].name());
            orderBuilder.setTotalPrice(new Price(ThreadLocalRandom.current().nextInt(min, maxPrice) / 100d));
            orderBuilder.setTotalWeight(new Weight(ThreadLocalRandom.current().nextInt(min, maxWeight) / 100d));

            Ordre order = orderBuilder.build();
            orderRepository.save(order);
        }
    }
}
