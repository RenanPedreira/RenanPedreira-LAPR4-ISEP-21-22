package eapli.base.warehousemanagement.application;

import eapli.base.agvmanagement.dto.AGVDTO;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.warehousemanagement.service.AssignmentService;
import eapli.base.warehousemanagement.service.ListAGVService;
import eapli.base.warehousemanagement.service.ListOrderToBePreparedService;

import java.util.List;

public class PrepareOrderController {

    private ListOrderToBePreparedService listOrderToBePreparedService = new ListOrderToBePreparedService();
    private ListAGVService listAGVService = new ListAGVService();
    private AssignmentService assignmentService = new AssignmentService();

    public List<OrderDTO> listOrdersToBePrepared(){
        return this.listOrderToBePreparedService.listOrdersToBePrepared();
    }

    public List<AGVDTO> listAvailableAGVS(OrderDTO orderDto){
        return this.listAGVService.listAvailableAGVS(orderDto);
    }

    public boolean assignment(OrderDTO orderDto, AGVDTO agvDto){
        return this.assignmentService.assignOrderToAGV(orderDto, agvDto);
    }
}
