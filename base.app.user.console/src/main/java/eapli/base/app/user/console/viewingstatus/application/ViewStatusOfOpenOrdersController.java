package eapli.base.app.user.console.viewingstatus.application;

import eapli.base.app.user.console.viewingstatus.csvprotocol.client.FailedRequestException;
import eapli.base.orderServerAPI.CsvOrderProtocolProxy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.IOException;

public class ViewStatusOfOpenOrdersController {

    private final SystemUser customer = AuthzRegistry.authorizationService().session().get().authenticatedUser();
    private final CsvOrderProtocolProxy proxy = new CsvOrderProtocolProxy();

    public void connectToServer(){
        //Connect to order server
        if(proxy.connectOrderServer()){
            System.out.println("Connected to Order Server");
        }else{
            System.out.println("Failed to connect to Order Server");
        }
    }

    public String getClientOrders() throws FailedRequestException, IOException, FailedRequestException {
        String userName = customer.username().toString();
        return this.proxy.getClientOrders(userName);
    }

}
