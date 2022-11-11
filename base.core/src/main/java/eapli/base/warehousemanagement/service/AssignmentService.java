package eapli.base.warehousemanagement.service;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.dto.AGVDTO;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderNumericIdentifier;
import eapli.base.ordermanagement.domain.Ordre;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.ordermanagement.persistence.OrderRepository;
import eapli.base.warehousemanagement.domain.Assignment.Assignment;
import eapli.base.warehousemanagement.repository.AssignmentRepository;

import static eapli.base.agvmanagement.domain.TaskStatus.ASSIGNED;
import static eapli.base.ordermanagement.domain.OrderStatus.PREPARED;
import static eapli.base.warehousemanagement.domain.Assignment.AssignmentStatus.DOING;

public class AssignmentService {

    private final AssignmentRepository assignmentRepository = PersistenceContext.repositories().assignments();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();

    public boolean assignOrderToAGV(OrderDTO orderDto, AGVDTO agvDto){
        try{
            OrderNumericIdentifier orderId = new OrderNumericIdentifier(orderDto.getNumericIdentifier());
            Ordre order = orderRepository.ofIdentity(orderId).get();
            order.changeStatus(PREPARED);
            orderRepository.save(order);

            Identifier agvId = new Identifier(agvDto.getIdentifier());
            AGV agv = agvRepository.ofIdentity(agvId).get();
            agv.changeStatus(ASSIGNED);
            agvRepository.save(agv);

            Identifier assignmentId = new IdentifierGenerator().generateIdentifier();
            Assignment assignment = new Assignment(assignmentId, agv, DOING, order);
            assignmentRepository.save(assignment);

            return true;
        }catch (Exception e){
            return false;
        }
    }
}
