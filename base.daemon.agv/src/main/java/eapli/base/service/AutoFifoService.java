package eapli.base.service;

import eapli.base.warehousemanagement.service.FifoService;

import java.net.UnknownHostException;

public class AutoFifoService {
    private final FifoService fifoService = new FifoService();

    public void runFifo(){
        while(true) {
            try {
                Thread.sleep(180000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Running FIFO");
            fifoService.fifoAlgorithm();
        }
    }
}
