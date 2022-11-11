package eapli.base.daemon.agvTwin;

import eapli.base.modules.communications.DigitalTwinProxy;
import eapli.base.daemon.agvTwin.presentation.UpdateStatusUI;

public final class AgvTwinClient {

    public static void main(final String[] args) {

        DigitalTwinProxy proxyAgv = new DigitalTwinProxy();
        proxyAgv.connectAgvManagerServer();

        UpdateStatusUI updateStatusUI = new UpdateStatusUI();
        updateStatusUI.doShow(proxyAgv);

        proxyAgv.disconnectAgvManagerServer();
    }
}
