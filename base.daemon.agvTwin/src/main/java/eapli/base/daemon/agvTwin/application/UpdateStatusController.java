package eapli.base.daemon.agvTwin.application;

import eapli.base.modules.communications.DigitalTwinProxy;
import eapli.base.orderServerAPI.FailedRequestException;

import java.io.IOException;

public class UpdateStatusController {

    DigitalTwinProxy proxy;

    public UpdateStatusController(DigitalTwinProxy proxyAgv){
        proxy=proxyAgv;
    }

    public String sendCommtestRequest(){
        String result;
        try {
            result=proxy.commtestRequest();
        } catch (IOException | FailedRequestException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String sendDisconnRequest(){
        String result;
        try {
            result=proxy.disconnRequest();
        } catch (IOException | FailedRequestException e) {
            result = "Connection already closed";
        }
        return result;
    }

    public String sendCommtestTwinRequest(){
        String result;
        try {
            result=proxy.commtestTwin();
        } catch (IOException | FailedRequestException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String sendDisconnTwinRequest(){
        String result;
        try {
            result=proxy.disconnTwin();
        } catch (IOException | FailedRequestException e) {
            result = "Connection already closed";
        }
        return result;
    }

}
