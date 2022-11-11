package eapli.base.protocol;

import eapli.base.shared.SharedMemory;

import java.net.UnknownHostException;

public class CurrentStatusRequest extends AgvTwinProtocolRequest{

    public CurrentStatusRequest(String inputLine, SharedMemory sharedMemory) throws UnknownHostException {
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

        result.append(this.sharedMemory.getStatus());
        result.append("\n");

        return result.toString();
    }

}
