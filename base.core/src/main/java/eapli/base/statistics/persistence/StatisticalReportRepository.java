package eapli.base.statistics.persistence;

import eapli.base.common.domain.model.Identifier;
import eapli.base.statistics.domain.StatisticalReport;
import eapli.framework.domain.repositories.DomainRepository;

public interface StatisticalReportRepository extends DomainRepository<Identifier, StatisticalReport> {
}
