package eapli.base.app.backoffice.console.presentation;

import eapli.base.app.backoffice.console.presentation.customer.RegisterCustomerAction;
import eapli.base.app.backoffice.console.presentation.order.CreateOrderAction;
import eapli.base.app.backoffice.console.presentation.product.CategoryRegistrationAction;
import eapli.base.app.backoffice.console.presentation.product.SpecifyProductAction;
import eapli.base.app.backoffice.console.presentation.product.ViewCatalogAction;
import eapli.base.app.backoffice.console.presentation.sales_clerk_employee_funcionalities.OrderDeliveredAction;
import eapli.base.app.common.console.presentation.authz.LoginUI;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * Data Structure which represents the Sales Clerk Menu, which is thus responsible for presenting the Sales Clerk the
 * available functionalities to him;
 *
 * @author Gonçalo Monteiro
 */
public class SalesClerkMenu extends Menu {
    private static final String MENU_TITLE = "Sales Clerk Menu >";
    private static final int EXIT_OPTION = 0;
    private static final int REGISTER_CUSTOMER_OPTION = 1;
    private static final int REGISTER_VIEW_CATALOG = 2;
    private static final int REGISTER_ORDER = 3;
    private static final int SPECIFY_PRODUCT = 4;
    private static final int DELIVERED_ORDER = 5;

    private static final int SPECIFY_CATEGORY = 6;

    private static final int LOGIN_OPTION = 7;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public SalesClerkMenu() {
        super(MENU_TITLE);
        buildSalesClerkMenu(null);
    }

    public SalesClerkMenu(final Role onlyWithThis) {
        super(MENU_TITLE);
        buildSalesClerkMenu(onlyWithThis);
    }


    private void buildSalesClerkMenu(final Role onlyWithThis) {
        if (authz.hasSession()) {
            addItem(MenuItem.of(REGISTER_CUSTOMER_OPTION, "Register Customer", new RegisterCustomerAction()::execute));
            addItem(MenuItem.of(REGISTER_VIEW_CATALOG, "View Product Catalog", new ViewCatalogAction()::execute));
            addItem(MenuItem.of(REGISTER_ORDER, "Register Client Order", new CreateOrderAction()::execute));
            addItem(MenuItem.of(SPECIFY_PRODUCT, "Specify Product", new SpecifyProductAction()::execute));
            addItem(MenuItem.of(SPECIFY_CATEGORY, "Specify Category", new CategoryRegistrationAction()::execute));
            addItem(MenuItem.of(DELIVERED_ORDER,"Delivered Orders", new OrderDeliveredAction()::execute));

        } else{
            addItem(MenuItem.of(LOGIN_OPTION, "Login", new LoginUI(onlyWithThis)::show));
        }

            addItem(MenuItem.of(EXIT_OPTION, "Return", Actions.SUCCESS));

    }
}
