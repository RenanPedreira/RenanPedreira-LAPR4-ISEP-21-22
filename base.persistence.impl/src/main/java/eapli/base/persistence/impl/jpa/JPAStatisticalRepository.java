package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.common.domain.model.Identifier;
import eapli.base.statistics.domain.StatisticalReport;
import eapli.base.statistics.persistence.StatisticalReportRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JPAStatisticalRepository extends JpaAutoTxRepository<StatisticalReport, Identifier, Identifier> implements StatisticalReportRepository {
    public JPAStatisticalRepository(TransactionalContext context) {
        super(context, "Identifier");
    }

    public JPAStatisticalRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "Identifier");
    }
}
