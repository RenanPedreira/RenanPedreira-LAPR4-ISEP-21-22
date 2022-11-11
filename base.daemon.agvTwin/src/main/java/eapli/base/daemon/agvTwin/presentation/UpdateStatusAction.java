package eapli.base.daemon.agvTwin.presentation;

import eapli.base.modules.communications.DigitalTwinProxy;
import eapli.framework.actions.Action;

public class UpdateStatusAction implements Action {

    DigitalTwinProxy proxyAgv;
    public UpdateStatusAction(DigitalTwinProxy proxyAgv){
        this.proxyAgv = proxyAgv;
    }
    public boolean execute(){
        return new UpdateStatusUI().doShow(proxyAgv);
    }
}
