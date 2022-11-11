package eapli.base.agvmanagement.persistence;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.TaskStatus;
import eapli.base.common.domain.model.Identifier;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface AGVRepository extends DomainRepository<Identifier, AGV> {
    List<AGV> getAvailableAGVs(String status);
    List<AGV> getAGVSByStatus(TaskStatus status);
    List<AGV> getAGVSByID(String id);
    List<AGV> getAgvByWarehouse(String warehouse);


    List<AGV> getAllAgvFromWarehouse(String warehouse);
}

