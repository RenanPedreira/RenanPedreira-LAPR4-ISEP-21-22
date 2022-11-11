package eapli.base.app.backoffice.console.presentation.warehouse;

import eapli.base.agvManagerAPI.CsvAgvManagerBackofficeProtocolProxy;
import eapli.base.agvmanagement.application.TestAgvServerController;

public class TestAgvServerConnectionUI {
    public boolean doShow(CsvAgvManagerBackofficeProtocolProxy proxyAgv) {
        TestAgvServerController controller = new TestAgvServerController(proxyAgv);

        System.out.println("Sending commtest request to Agv Manager server\n");
        System.out.println(controller.sendCommtestRequest());

        System.out.println("\nSending disconn request to Agv Manager server\n");
        System.out.println(controller.sendDisconnRequest());

        return true;
    }
}
