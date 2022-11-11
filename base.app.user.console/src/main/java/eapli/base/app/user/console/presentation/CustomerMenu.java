package eapli.base.app.user.console.presentation;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class CustomerMenu  extends Menu {

    private static final String MENU_TITLE = "Customer Menu >";
    private static final int EXIT_OPTION = 0;
    private static final int CHECK_ORDER_STATUS = 1;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public CustomerMenu() {
        super(MENU_TITLE);
        buildCustomerMenu(null);
    }

    public CustomerMenu(final Role onlyWithThis) {
        super(MENU_TITLE);
        buildCustomerMenu(onlyWithThis);
    }

    private void buildCustomerMenu(final Role onlyWithThis) {
        addItem(MenuItem.of(CHECK_ORDER_STATUS, "Check status of open orders", new ViewStatusOfOpenOrdersAction()::execute));
        addItem(MenuItem.of(EXIT_OPTION, "Return", Actions.SUCCESS));
    }
}
