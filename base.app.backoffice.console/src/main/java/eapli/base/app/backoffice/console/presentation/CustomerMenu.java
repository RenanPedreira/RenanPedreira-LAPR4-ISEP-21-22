package eapli.base.app.backoffice.console.presentation;
import eapli.base.app.backoffice.console.presentation.cart.CustomerCatalogSearchAction;
import eapli.base.app.backoffice.console.presentation.customer.AnswerQuestionnaireAction;
import eapli.base.app.backoffice.console.presentation.customer.TestOrderServerConnectionAction;
import eapli.base.app.common.console.presentation.authz.LoginUI;
import eapli.base.orderServerAPI.CsvOrderProtocolProxy;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class CustomerMenu extends Menu {
    private static final String MENU_TITLE = "Customer Menu >";
    private static final int EXIT_OPTION = 0;
    private static final int SEARCH_CATALOG_OPTION = 1;
    private static final int ANSWER_QUESTIONNAIRE = 3;
    private static final int LOGIN_OPTION = 4;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public CustomerMenu(CsvOrderProtocolProxy proxy) {
        super(MENU_TITLE);
        buildCustomerMenu(null, proxy);
    }

    public CustomerMenu(final Role onlyWithThis, CsvOrderProtocolProxy proxy) {
        super(MENU_TITLE);
        buildCustomerMenu(onlyWithThis, proxy);
    }

    private void buildCustomerMenu(final Role onlyWithThis, CsvOrderProtocolProxy proxy) {
        if (authz.hasSession()) {
            addItem(MenuItem.of(1, "View Catalog", new CustomerCatalogSearchAction(proxy)::execute));
            addItem(MenuItem.of(2, "Test Connection", new TestOrderServerConnectionAction(proxy)::execute));
            addItem(MenuItem.of(ANSWER_QUESTIONNAIRE, "AnswerQuestionnaire", new AnswerQuestionnaireAction()::execute));
        } else{
            addItem(MenuItem.of(4, "Login", new LoginUI(onlyWithThis)::show));
        }

        addItem(MenuItem.of(EXIT_OPTION, "Return", Actions.SUCCESS));
    }
}
