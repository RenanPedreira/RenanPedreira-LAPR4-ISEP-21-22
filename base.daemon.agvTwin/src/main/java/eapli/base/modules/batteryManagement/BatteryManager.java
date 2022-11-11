package eapli.base.modules.batteryManagement;

import eapli.base.common.util.Pair;
import eapli.base.shared.SharedMemory;

import static java.lang.Thread.sleep;

/**
 * The Battery manager.
 */
public class BatteryManager {

    /**
     * Number that the AGV battery decreases while moving
     */
    private static final int DECREASE_MOVING = 5;

    /**
     * Number that the AGV battery decreases while not moving
     */
    private static final int DECREASE_STOPPED = 1;

    /**
     * Starts the battery manager.
     *
     * @param sharedMemory the shared memory
     */
    public void start(SharedMemory sharedMemory) throws InterruptedException {

        while (true) {

            sleep(1000);

            Integer battery = sharedMemory.getBattery();

            //Checks if the AGV moved
            if (sharedMemory.getStatus().equals("FREE")) {
                sharedMemory.updateBattery(battery - DECREASE_STOPPED);
            } else {
                sharedMemory.updateBattery(battery - DECREASE_MOVING);
            }

            //Simulates the AGV charging
            if (sharedMemory.getBattery() == 0) {
                sharedMemory.updateBattery(100);
            }
        }
    }
}
