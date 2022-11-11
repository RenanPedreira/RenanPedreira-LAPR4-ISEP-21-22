package eapli.base.warehousemanagement.service;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.TaskStatus;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.Ordre;
import eapli.base.ordermanagement.persistence.OrderRepository;
import eapli.base.warehousemanagement.domain.Assignment.AssignmentStatus;
import eapli.base.warehousemanagement.factory.AssignmentFactory;
import eapli.base.warehousemanagement.repository.AssignmentRepository;

import java.util.List;

public class FifoService {
    private final ListOrderToBePreparedService orderToBePreparedService = new ListOrderToBePreparedService();
    private final ListAGVService listAGVService = new ListAGVService();
    private final AssignmentRepository assignmentRepository = PersistenceContext.repositories().assignments();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();
    private Ordre ordre;
    private AGV agv;


    public String fifoAlgorithm(){
        int dif = 0;
        List<Ordre> listOrder = this.orderToBePreparedService.listOrdersToBePreparedNormal();
        List<AGV> listAGV = this.listAGVService.listAvailableAGVSNormal();

        int sizeBanana = listOrder.size();
        int bananaSize = listAGV.size();

        dif = sizeBanana-bananaSize;

        if(sizeBanana == 0 || bananaSize ==0){
            //System.out.println("no order or agv");
            return "no order or agv";
        }
        for (int i = 0; i<dif ; i++) {
            this.ordre = listOrder.get(i);
            this.agv = listAGV.get(i);
            if (agv.getCapacity().getWeight() < ordre.getTotalWeight().getWeight()) {
                double weight = listOrder.get(i).getTotalWeight().getWeight();
                for (int j=0; j<listAGV.size();j++){
                    this.agv = listAGV.get(j);
                    if (weight<=agv.getCapacity().getWeight()){
                        agv.changeStatus(TaskStatus.ASSIGNED);
                        ordre.changeStatus(OrderStatus.DISPATCHED);
                        Identifier is = new IdentifierGenerator().generateIdentifier();
                        final var ass = new AssignmentFactory().
                                idy(is).agvy(agv).ordrey(ordre).statusy(AssignmentStatus.DOING).build();

                        assignmentRepository.save(ass);
                        orderRepository.save(ordre);
                        agvRepository.save(agv);
                        listAGV.remove(j);
                        listOrder.remove(i);
                        break;
                    }
                }
            }
            agv.changeStatus(TaskStatus.ASSIGNED);
            ordre.changeStatus(OrderStatus.DISPATCHED);
            Identifier is = new IdentifierGenerator().generateIdentifier();
            final var ass = new AssignmentFactory().
                    idy(is).agvy(agv).ordrey(ordre).statusy(AssignmentStatus.DOING).build();

            assignmentRepository.save(ass);
            orderRepository.save(ordre);
            agvRepository.save(agv);
            listAGV.remove(i);
            listOrder.remove(i);
        }

        return "Order is being assigned";
    }
}
