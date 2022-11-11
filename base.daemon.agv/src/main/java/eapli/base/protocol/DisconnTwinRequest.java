package eapli.base.protocol;

import eapli.base.orderServerAPI.FailedRequestException;
import eapli.base.twinServerAPI.TwinServerProxy;

import java.io.IOException;
import java.net.UnknownHostException;

public class DisconnTwinRequest extends AGVProtocolRequest{

    public DisconnTwinRequest(String inputLine) throws UnknownHostException {
        super(inputLine);
    }

    @Override
    public String execute(){
        return buildResponse();
    }

    public String buildResponse(){

        StringBuilder result=new StringBuilder();

        TwinServerProxy proxy = new TwinServerProxy();
        proxy.connectDigitalTwinServer();
        try {
            result.append(proxy.disconnRequest());
        } catch (IOException | FailedRequestException e) {
            result.append("Resquest failed");
        }
        result.append("\n");

        return "Digital Twin Response: , \"" + result + "\"\n";
    }
}
