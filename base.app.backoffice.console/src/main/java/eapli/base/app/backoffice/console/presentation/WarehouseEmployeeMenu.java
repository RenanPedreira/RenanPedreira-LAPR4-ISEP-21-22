package eapli.base.app.backoffice.console.presentation;

import eapli.base.agvManagerAPI.CsvAgvManagerBackofficeProtocolProxy;
import eapli.base.app.backoffice.console.presentation.warehouse.PrepareOrderAction;
import eapli.base.app.backoffice.console.presentation.warehouse.TestAgvServerConnectionAction;
import eapli.base.app.backoffice.console.presentation.warehouse.SetWarehousePlantAction;
import eapli.base.app.backoffice.console.presentation.warehouse_employee_functionalities.ConfigureAGVAction;
import eapli.base.app.backoffice.console.presentation.warehouse_employee_functionalities.DashboardAction;
import eapli.base.app.backoffice.console.presentation.warehouse_employee_functionalities.OrderDispatchmentAction;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class WarehouseEmployeeMenu extends Menu {

    private static final String MENU_TITLE = "Warehouse Employee Menu >";
    private static final int EXIT_OPTION = 0;
    private static final int IMPORT_WAREHOUSE_PLANT = 1;
    private static final int CONFIGURE_AGV = 2;
    private static final int DISPATCH_ORDER = 3;
    private static final int PREPARE_ORDER = 4;
    private static final int DASHBOARD = 5;

    private static final int ALL_AGV = 6;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public WarehouseEmployeeMenu(CsvAgvManagerBackofficeProtocolProxy proxyAgv) {
        super(MENU_TITLE);
        buildWarehouseEmployeeMenu(null, proxyAgv);
    }

    public WarehouseEmployeeMenu(final Role onlyWithThis, CsvAgvManagerBackofficeProtocolProxy proxyAgv) {
        super(MENU_TITLE);
        buildWarehouseEmployeeMenu(onlyWithThis, proxyAgv);
    }

    private void buildWarehouseEmployeeMenu(final Role onlyWithThis, CsvAgvManagerBackofficeProtocolProxy proxyAgv) {
        addItem(MenuItem.of(IMPORT_WAREHOUSE_PLANT, "Import Warehouse Plant", new SetWarehousePlantAction()::execute));
        addItem(MenuItem.of(CONFIGURE_AGV, "Configure AGV", new ConfigureAGVAction()::execute));
        addItem(MenuItem.of(DISPATCH_ORDER, "Dispatch Orders", new OrderDispatchmentAction()::execute));
        addItem(MenuItem.of(PREPARE_ORDER, "Prepare Orders", new PrepareOrderAction()::execute));
        addItem(MenuItem.of(DASHBOARD,"Dashboard",new DashboardAction()::execute));
        addItem(MenuItem.of(ALL_AGV,"Test Server Connection",new TestAgvServerConnectionAction(proxyAgv)::execute));
        addItem(MenuItem.of(EXIT_OPTION, "Return", Actions.SUCCESS));
    }
}
