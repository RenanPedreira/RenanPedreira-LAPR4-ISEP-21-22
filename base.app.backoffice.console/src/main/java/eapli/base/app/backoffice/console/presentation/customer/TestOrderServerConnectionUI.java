package eapli.base.app.backoffice.console.presentation.customer;


import eapli.base.orderServerAPI.CsvOrderProtocolProxy;
import eapli.base.ordermanagement.application.TestOrderServerController;

public class TestOrderServerConnectionUI {

    public boolean doShow(CsvOrderProtocolProxy proxyOrder) {

        TestOrderServerController controller = new TestOrderServerController(proxyOrder);

        System.out.println("\nSending commtest request to Order server\n");
        System.out.println(controller.sendCommtest());

        System.out.println("\n\nSending disconn request to Order server\n");
        System.out.println(controller.sendDisconn());

        return true;
    }
}
