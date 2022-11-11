package eapli.base.app.backoffice.console.presentation.cart;

import eapli.base.orderServerAPI.CsvOrderProtocolProxy;

import java.io.IOException;

public class CustomerCatalogSearchAction {

    CsvOrderProtocolProxy proxy;

    public CustomerCatalogSearchAction(CsvOrderProtocolProxy proxy){
        this.proxy = proxy;
    }
    public boolean execute() {
        try {
            return new CustomerCatalogSearchUI().doShow(proxy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
