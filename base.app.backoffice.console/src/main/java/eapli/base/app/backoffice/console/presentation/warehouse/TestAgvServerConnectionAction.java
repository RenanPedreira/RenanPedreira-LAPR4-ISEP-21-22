package eapli.base.app.backoffice.console.presentation.warehouse;

import eapli.base.agvManagerAPI.CsvAgvManagerBackofficeProtocolProxy;
import eapli.framework.actions.Action;

public class TestAgvServerConnectionAction implements Action {

    private CsvAgvManagerBackofficeProtocolProxy proxyAgv;

    public TestAgvServerConnectionAction(CsvAgvManagerBackofficeProtocolProxy proxyAgv){
        this.proxyAgv=proxyAgv;
    }
    public boolean execute(){
        return new TestAgvServerConnectionUI().doShow(proxyAgv);
    }
}
