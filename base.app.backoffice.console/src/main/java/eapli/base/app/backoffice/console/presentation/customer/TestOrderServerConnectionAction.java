package eapli.base.app.backoffice.console.presentation.customer;

import eapli.base.orderServerAPI.CsvOrderProtocolProxy;
import eapli.framework.actions.Action;

public class TestOrderServerConnectionAction implements Action {

    private CsvOrderProtocolProxy proxyAgv;

    public TestOrderServerConnectionAction(CsvOrderProtocolProxy proxyAgv){
        this.proxyAgv = proxyAgv;
    }

    @Override
    public boolean execute() {
        return new TestOrderServerConnectionUI().doShow(proxyAgv);
    }
}
