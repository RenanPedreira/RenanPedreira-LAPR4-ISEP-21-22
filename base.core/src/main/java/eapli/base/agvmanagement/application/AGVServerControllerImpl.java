package eapli.base.agvmanagement.application;

import eapli.base.warehousemanagement.service.FifoService;

import java.net.*;


public class AGVServerControllerImpl implements AGVServerController{

    /*
    private String serverAddress = "192.168.56.1";
    private Integer serverPort = 8898;

    Socket socket = new Socket(serverAddress, serverPort);*/
    private final FifoService service = new FifoService();

    public AGVServerControllerImpl() throws UnknownHostException {
    }

    @Override
    public String doFifo() {
        return this.service.fifoAlgorithm();
    }
}
