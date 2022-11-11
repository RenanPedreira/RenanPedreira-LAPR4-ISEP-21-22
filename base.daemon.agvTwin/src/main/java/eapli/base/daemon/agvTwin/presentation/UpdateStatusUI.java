package eapli.base.daemon.agvTwin.presentation;

import eapli.base.modules.communications.DigitalTwinProxy;
import eapli.base.daemon.agvTwin.application.UpdateStatusController;

public class UpdateStatusUI  {
    public boolean doShow(DigitalTwinProxy proxyAgv) {
        UpdateStatusController controller = new UpdateStatusController(proxyAgv);

        System.out.println("\nSending commtest request to Agv Manager server\n");
        controller.sendCommtestRequest();

        System.out.println("\n\nSending commtest request to Digital Twin server\n");
        controller.sendCommtestTwinRequest();

        System.out.println("\n\nSending disconn request to Digital Twin server\n");
        controller.sendDisconnTwinRequest();

        System.out.println("\n\nSending disconn request to Agv Manager server\n");
        controller.sendDisconnRequest();

        return true;
    }

}
