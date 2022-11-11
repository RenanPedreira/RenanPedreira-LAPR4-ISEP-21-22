package eapli.base.protocol;

import eapli.base.agvmanagement.application.AGVServerController;

public abstract class FifoProtocolRequest {
    protected final AGVServerController controller;
    protected final String request;


    public FifoProtocolRequest(AGVServerController controller, String request) {
        this.controller = controller;
        this.request = request;
        }

    /**
     * Executes the requested action and builds the response to the client.
     *
     * @return the response to send back to the client
     */
    public abstract String execute();

    /**
     * Indicates the object is a goodbye message, that is, a message that will close the
     * connection to the client.
     *
     * @return {@code true} if the object is a a goodbye message.
     */
    public boolean isGoodbye(){
        return false;
    }



}
