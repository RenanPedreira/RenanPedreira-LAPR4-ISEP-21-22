package eapli.base.app.backoffice.console.presentation.warehouse_employee_functionalities;


import eapli.base.dashboard.application.DashboardController;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;


public class DashboardUI extends AbstractUI {

    private static DashboardController controller = new DashboardController();
    @Override
    protected boolean doShow() {

            controller.showDashboard();

            return true;
    }

    @Override
    public String headline() {
        return "Dashboard";
    }
}
