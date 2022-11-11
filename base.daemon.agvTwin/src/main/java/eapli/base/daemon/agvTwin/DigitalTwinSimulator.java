package eapli.base.daemon.agvTwin;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.common.util.Pair;
import eapli.base.daemon.agvTwin.application.DigitalTwinUnit;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.orderServerAPI.FailedRequestException;
import eapli.base.twinServerAPI.TwinServerProxy;
import eapli.framework.domain.repositories.TransactionalContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Simulation engine will simulate the behavior of
 * all the agvs on the database by creating
 * a digital twin for each of them
 */
public class DigitalTwinSimulator {

    public static void main(final String[] args) throws InterruptedException, FailedRequestException, IOException {

        /** Gets all agvs on the database */
        AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();
        List<AGV> agvs = (List<AGV>) agvRepository.getAllAgvFromWarehouse("Armstrong Warehouse");

        /** For each agv a port will be created */
        int size = agvs.size();
        Integer[] twinPort = new Integer[size];

        /** Generates multiple ports, one for each digital twin server */
        for (int i=0; i<size; i++)
            twinPort[i] = 4000 + i;

        /** For each agv on the database a simulation of it is created and connected to the agv manager */
        for(int k=0; k<size; k++) {
            simulate(agvRepository, agvs.get(k), twinPort[k]);
            sleep(1000);
        }
    }

    /** Creates a thread that simulates the behavior of an agv */
    private static void simulate(AGVRepository agvRepository, AGV agv, final Integer port){
        DigitalTwinUnit digitalTwin = new DigitalTwinUnit();
        Thread twin = new Thread(() -> digitalTwin.start(agvRepository, agv, port));
        twin.start();
    }

}