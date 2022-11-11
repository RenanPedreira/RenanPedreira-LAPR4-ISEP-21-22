package eapli.base.protocol;

import eapli.base.shared.SharedMemory;

import java.net.UnknownHostException;

public class CurrentPositionRequest extends AgvTwinProtocolRequest{

    public CurrentPositionRequest(String inputLine, SharedMemory sharedMemory) throws UnknownHostException {
        super(inputLine, sharedMemory);
    }

    @Override
    public String execute(){
        try {
            return buildResponse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String buildResponse() throws InterruptedException {
        StringBuilder result = new StringBuilder();

        result.append(super.sharedMemory.getPositionAlter());
        result.append("\n");

        return result.toString();
    }

}
