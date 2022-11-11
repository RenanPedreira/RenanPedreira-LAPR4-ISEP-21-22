package eapli.base.agvmanagement.application;

import eapli.base.agvManagerAPI.CsvAgvManagerBackofficeProtocolProxy;
import eapli.base.orderServerAPI.FailedRequestException;

import java.io.IOException;

public class TestAgvServerController {
    private CsvAgvManagerBackofficeProtocolProxy proxy;

    public TestAgvServerController(CsvAgvManagerBackofficeProtocolProxy proxy){
        this.proxy=proxy;
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
}