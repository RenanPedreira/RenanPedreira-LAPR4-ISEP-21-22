package eapli.base.ordermanagement.application;

import eapli.base.orderServerAPI.CsvOrderProtocolProxy;
import eapli.base.orderServerAPI.FailedRequestException;

import java.io.IOException;

public class TestOrderServerController {

    private CsvOrderProtocolProxy proxyOrder;
    public TestOrderServerController(CsvOrderProtocolProxy proxyOrder){
        this.proxyOrder=proxyOrder;
    }

    public String sendCommtest(){
        String result;
        try {
            result=proxyOrder.commtest();
        } catch (IOException | FailedRequestException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String sendDisconn(){
        String result;
        try {
            result=proxyOrder.disconn();
        } catch (IOException | FailedRequestException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
