package eapli.base.agvmanagement.application;

import eapli.base.agvmanagement.domain.*;
import eapli.base.agvmanagement.dto.AGVDTO;
import eapli.base.agvmanagement.factory.AGVBuilder;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.common.domain.model.Description;
import eapli.base.common.domain.model.Identifier;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDock;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDockId;
import eapli.base.warehousemanagement.dto.DockDTO;
import eapli.base.warehousemanagement.repository.AgvDockRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ConfigureAGVService {
    private static final String DESCRIPTION_TYPE = "TECHNICAL_DESCRIPTION";
    AgvDockRepository repository = PersistenceContext.repositories().agvDocks();
    public List<DockDTO> retrieveAllAvailableDocks() {


        List<AgvDock> dockList = (List<AgvDock>) repository.findAll();
        List<DockDTO> dockDTOList = new ArrayList<>();

        for (AgvDock dock : dockList)
            dockDTOList.add(dock.toDTO());

        return dockDTOList;
    }

    public AGVDTO configureAGV(String model, String description, String taskStatus, Duration batteryAutonomy, String dockIdentifier, double capacity) {
        //AgvDockRepository repository = PersistenceContext.repositories().agvDocks();
        List<AgvDock> dockList = (List<AgvDock>) repository.findAll();
        AgvDock dock = null;

        for (AgvDock dockAux : dockList) {
            if (dockAux.hasIdentity(new AgvDockId(dockIdentifier)))
                dock = dockAux;
        }

        AGVBuilder builder = new AGVBuilder();

        builder.setIdentifier();
        builder.setModel(model);
        builder.setTechnicalDescription(description);
        builder.setTaskStatus(taskStatus);
        builder.setAutonomyStatus(batteryAutonomy);
        builder.setBaseLocation(dock);
        builder.setCapacity(capacity);

        return builder.build().toDTO();
    }

    public void persistAGV(AGVDTO agvDTO) {
        AGVRepository repository1 = PersistenceContext.repositories().agvRepository();
        List<AgvDock> dockList = (List<AgvDock>) repository.findAll();
        AgvDock dock = null;

        for (AgvDock dockAux : dockList) {
            if (dockAux.hasIdentity(new AgvDockId(agvDTO.getBaseLocation())))
                dock = dockAux;
        }

        AGV agv = new AGV(new Identifier(agvDTO.getIdentifier()),
                new Model(agvDTO.getModel()),
                new Description(DESCRIPTION_TYPE, agvDTO.getTechnicalDescription()),
                TaskStatus.valueOf(agvDTO.getTaskStatus()),
                new AutonomyStatus(agvDTO.getBatteryAutonomy()),
                dock,
                new Weight(agvDTO.getCapacity()));

        repository1.save(agv);
    }
}
