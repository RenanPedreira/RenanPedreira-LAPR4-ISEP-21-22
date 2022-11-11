package eapli.base.agvmanagement.application;

import eapli.base.agvmanagement.dto.AGVDTO;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDock;
import eapli.base.warehousemanagement.dto.DockDTO;
import eapli.base.warehousemanagement.repository.AgvDockRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ConfigureAGVController {
    ConfigureAGVService service;

    public ConfigureAGVController() {
        service = new ConfigureAGVService();
    }

    public List<DockDTO> retrieveAllAvailableDocks() {
        return service.retrieveAllAvailableDocks();
    }

    public AGVDTO configureAGV(String model, String description, String taskStatus, Duration batteryAutonomy, String dockIdentifier, double capacity) {
        return service.configureAGV(model, description, taskStatus, batteryAutonomy, dockIdentifier, capacity);
    }

    public void persistAGV(AGVDTO agvDTO) {
        service.persistAGV(agvDTO);
    }
}
