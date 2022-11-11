package eapli.base.service;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.TransactionalContext;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class AgvSearchService {

    private TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private AGVRepository agvRepository = PersistenceContext.repositories().agvRepository(txCtx);

    public List<AGV> allAGV(){

        return (List<AGV>) agvRepository.findAll();
    }
}
