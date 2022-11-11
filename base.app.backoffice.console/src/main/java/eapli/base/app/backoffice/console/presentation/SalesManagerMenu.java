package eapli.base.app.backoffice.console.presentation;

import eapli.base.app.backoffice.console.presentation.questionnaire.ObtainStatisticalReportAction;
import eapli.base.app.backoffice.console.presentation.questionnaire.QuestionnaireValidationUI;
import eapli.base.app.common.console.presentation.authz.LoginUI;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;


public class SalesManagerMenu extends Menu {
    private static final String MENU_TITLE = "Sales Manager Menu >";
    private static final int EXIT_OPTION = 0;
    private static final int VALIDATE_QUESTIONNAIRE = 1;
    private static final int OBTAIN_REPORT = 2;



    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public SalesManagerMenu() {
        super(MENU_TITLE);
        buildSalesManagerMenu(null);
    }

    public SalesManagerMenu(final Role onlyWithThis){
        super(MENU_TITLE);
        buildSalesManagerMenu(onlyWithThis);
    }

    private void buildSalesManagerMenu(final Role onlyWithThis) {
        if(authz.hasSession()) {
            addItem(MenuItem.of(VALIDATE_QUESTIONNAIRE,"Validate Questionnaire", new QuestionnaireValidationUI()::show));
            addItem(MenuItem.of(OBTAIN_REPORT, "Obtain Statistical Report", new ObtainStatisticalReportAction()::execute));
        }
        addItem(MenuItem.of(EXIT_OPTION,"Return", Actions.SUCCESS));
    }
}
