package eapli.base.daemon.agvTwin.application;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.modules.batteryManagement.BatteryManager;
import eapli.base.modules.communications.DigitalTwinServer;
import eapli.base.modules.controlUnit.DigitalTwinControlUnit;
import eapli.base.modules.positioning.PositionThread;
import eapli.base.modules.routePlanner.RoutePlanner;
import eapli.base.modules.sensor.Sensor;
import eapli.base.modules.simulationEngine.SimulationEngineThread;
import eapli.base.orderServerAPI.FailedRequestException;
import eapli.base.shared.SharedMemory;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class DigitalTwinUnit{

    /** Creates the Shared Information class */
    private SharedMemory createSharedMemory(AGV agv){
        return new SharedMemory(agv.getIdentifier().toString2(), agv.getPosition().coordinates(), 100, agv.getTaskStatus().name());
    }

    /** Starts the digital twin's module threads */
    private void StartModules(AGV agv, AGVRepository agvRepository, int port, SharedMemory sm) throws InterruptedException {
        /** Communications module */
        DigitalTwinServer communications = new DigitalTwinServer();
        Thread communicationsModule = new Thread(() -> communications.start(sm, port, true));

        /** Control Unit Module */
        DigitalTwinControlUnit controlUnit = new DigitalTwinControlUnit();
        Thread controlUnitModule = new Thread(() -> {
            try {
                controlUnit.start(sm, agvRepository, agv);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        /** Simulation Engine module */
        SimulationEngineThread simulationEngineThread = new SimulationEngineThread();
        Thread simulationEngineModule = new Thread(() -> {
            try {
                simulationEngineThread.start(sm);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FailedRequestException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /** Sensor module */
        Sensor sensor = new Sensor();
        Thread sensorModules = new Thread(() -> {
            try {
                sensor.start(sm);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /** Positioning module */
        PositionThread positionThread = new PositionThread();
        Thread positionModule = new Thread(() -> {
            try {
                positionThread.start(sm);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (FailedRequestException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        /** Route Planner module */
        RoutePlanner routePlanner = new RoutePlanner();
        Thread routePlannerModule = new Thread(() -> {
            try {
                routePlanner.start(sm);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FailedRequestException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        /** Battery Management module */
        BatteryManager batteryManager = new BatteryManager();
        Thread batteryManagementModule = new Thread(() -> {
            try {
                batteryManager.start(sm);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /** Starts the modules */
        communicationsModule.start();
        sleep(1000);

        routePlannerModule.start();
        routePlannerModule.join();

        controlUnitModule.start();
        sleep(1000);

        positionModule.start();
        sleep(1000);

        simulationEngineModule.start();
        sleep(1000);

        sensorModules.start();
        sleep(1000);

        batteryManagementModule.start();




    }

    /** Starts the module threads */
    public void start(AGVRepository agvRepository, AGV agv, Integer port){
        SharedMemory modulesMemory = createSharedMemory(agv);
        try {
            StartModules(agv, agvRepository, port, modulesMemory);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
