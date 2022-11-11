package eapli.base.warehousemanagement.repository;

import eapli.base.common.domain.model.Identifier;

import eapli.base.warehousemanagement.domain.Assignment.Assignment;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface AssignmentRepository extends DomainRepository<Identifier, Assignment> {

    List<Assignment> getAgvAssignments(String agv);

}
