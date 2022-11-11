package eapli.base.protocol;


import eapli.base.agvmanagement.application.AGVServerController;
import eapli.base.agvmanagement.application.AGVServerControllerImpl;
import eapli.base.warehousemanagement.service.FifoService;

import java.net.UnknownHostException;

public class FifoRequest extends AGVProtocolRequest{

    private final FifoService fifoService = new FifoService();

    public FifoRequest(String inputLine, String[] tokens) throws UnknownHostException {
        super(inputLine);
    }

    @Override
    public String execute() {
        return "Automatic Fifo running!";
    }

    public void runFifo(){
        fifoService.fifoAlgorithm();
    }
}
