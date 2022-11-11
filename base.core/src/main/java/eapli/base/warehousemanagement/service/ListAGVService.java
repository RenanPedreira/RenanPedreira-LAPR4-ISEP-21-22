package eapli.base.warehousemanagement.service;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.dto.AGVDTO;
import eapli.base.agvmanagement.dto.AGVMapper;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;

import static eapli.base.agvmanagement.domain.TaskStatus.ASSIGNED;
import static eapli.base.agvmanagement.domain.TaskStatus.FREE;

public class ListAGVService {

    private final AGVMapper agvMapper = new AGVMapper();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();

    public List<AGVDTO> listAvailableAGVS(OrderDTO orderDTO) {
        List<AGVDTO> availableAgvs = new ArrayList<>();
        List<AGVDTO> agvList = agvMapper.toDTO(agvRepository.getAGVSByStatus(FREE));

        for (AGVDTO agvdto : agvList) {
            if (agvdto.getCapacity() >= orderDTO.getTotalWeight()) {
                availableAgvs.add(agvdto);
            }
        }
        return availableAgvs;
    }

    public List<AGV> listAvailableAGVSNormal() {
        return this.agvRepository.getAGVSByStatus(FREE);

    }

    public List<AGVDTO> listAvailableAGVSDTO() {
        List<AGVDTO> availableAgvs = new ArrayList<>();
        List<AGVDTO> agvList = agvMapper.toDTO(agvRepository.getAGVSByStatus(ASSIGNED));

        for (AGVDTO agvdto : agvList) {

            availableAgvs.add(agvdto);

        }
        return availableAgvs;
    }

    public List<AGVDTO> listAGVSDTO() {
        List<AGVDTO> availableAgvs = new ArrayList<>();
        List<AGVDTO> agvList = agvMapper.toDTO(agvRepository.getAgvByWarehouse("Armstrong Warehouse"));

        for (AGVDTO agvdto : agvList) {

            availableAgvs.add(agvdto);

        }
        return availableAgvs;
    }
}
