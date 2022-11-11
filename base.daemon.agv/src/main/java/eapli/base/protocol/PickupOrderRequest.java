package eapli.base.protocol;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.warehousemanagement.domain.Assignment.Assignment;
import eapli.base.warehousemanagement.repository.AssignmentRepository;

import java.net.UnknownHostException;
import java.util.List;

public class PickupOrderRequest  extends AGVProtocolRequest{

    public PickupOrderRequest(String inputLine) throws UnknownHostException{
        super(inputLine);
    }

    private AssignmentRepository assigmentRepository = PersistenceContext.repositories().assignments();

    public String execute(){
        return getPickupOrderRequest();
    }

    public String getPickupOrderRequest(){
        StringBuilder result = new StringBuilder();

        List<Assignment> orders = assigmentRepository.getAgvAssignments(super.tokens[4]);
        Assignment lastOrder = orders.get(orders.size()-1);

        for(OrderItem p : lastOrder.getOrder().itemList()){
            result.append(p.getProduct().getProductPosition());
            result.append(" ");
        }
        result.append("\n");

        return result.toString();
    }


}
