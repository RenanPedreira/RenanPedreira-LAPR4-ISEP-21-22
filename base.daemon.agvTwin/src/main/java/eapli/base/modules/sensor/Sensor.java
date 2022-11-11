package eapli.base.modules.sensor;

import eapli.base.common.util.Pair;
import eapli.base.shared.SharedMemory;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * The Sensors.
 */
public class Sensor {

    /**
     * Emits an alert with "1" if an obstacle is detected at one square distance
     */
    private static final int ONE_SQUARE_ALERT = 1;

    /**
     * Emits an alert with "2" if an obstacle is detected at two squares distance
     */
    private static final int TWO_SQUARES_ALERT = 2;

    /**
     * Emits an alert with "0" if an obstacle is detected at three or more squares distance
     */
    private static final int CLOSE_SQUARES_ALERT = 0;

    /**
     * Represents the eight sensors in an AGV
     * <p>
     * Position 0: North
     * Position 1: South
     * Position 2: East
     * Position 3: West
     * Position 4: Northwest
     * Position 5: Northeast
     * Position 6: Southwest
     * Position 7: Southeast
     */
    private Integer sensors[] = new Integer[8];

    /**
     * Starts the sensor module.
     *
     * @param sharedMemory the shared memory
     */
    public void start(SharedMemory sharedMemory) throws InterruptedException {

         while (true) {

            sleep(100);

            Pair<Integer, Integer> currentPosition = sharedMemory.getPosition();

            sensors = sharedMemory.getSensors();

            List<Pair<Integer, Integer>> oneDistanceAgvList = sharedMemory.getPositionOneDistanceList();
            emitAlert(currentPosition, oneDistanceAgvList, ONE_SQUARE_ALERT);

            List<Pair<Integer, Integer>> twoDistanceAgvList = sharedMemory.getPositionTwoDistanceList();
            emitAlert(currentPosition, twoDistanceAgvList, TWO_SQUARES_ALERT);

            List<Pair<Integer, Integer>> furtherAgvList = sharedMemory.getPositionFurthestDistanceList();
            emitAlert(currentPosition, furtherAgvList, CLOSE_SQUARES_ALERT);

            sharedMemory.updateSensors(sensors);
        }
    }

    /**
     * To delegate which sensor should emit an alert.
     *
     * @param currentPosition current position of the AGV
     * @param distanceList    list of square distances
     * @param alert           the alert to be emitted based on square distance
     */
    public void emitAlert(Pair<Integer, Integer> currentPosition, List<Pair<Integer, Integer>> distanceList, int alert) {


        for (Pair<Integer, Integer> agv : distanceList) {
            //Checks if the AGV is on North
            if (currentPosition.getKey() == agv.getKey() && currentPosition.getValue() < agv.getValue()) {
                sensors[0] = alert;
            }
            //Checks if the AGV is on South
            if (currentPosition.getKey() == agv.getKey() && currentPosition.getValue() > agv.getValue()) {
                sensors[1] = alert;
            }
            //Checks if the AGV is on East
            if (currentPosition.getKey() < agv.getKey() && currentPosition.getValue() == agv.getValue()) {
                sensors[2] = alert;
            }
            //Checks if the AGV is on West
            if (currentPosition.getKey() > agv.getKey() && currentPosition.getValue() == agv.getValue()) {
                sensors[3] = alert;
            }
            //Checks if the AGV is on Northwest
            if (currentPosition.getKey() > agv.getKey() && currentPosition.getValue() < agv.getValue()) {
                sensors[4] = alert;
            }
            //Checks if the AGV is on Northeast
            if (currentPosition.getKey() < agv.getKey() && currentPosition.getValue() < agv.getValue()) {
                sensors[5] = alert;
            }
            //Checks if the AGV is on Southwest
            if (currentPosition.getKey() > agv.getKey() && currentPosition.getValue() > agv.getValue()) {
                sensors[6] = alert;
            }
            //Checks if the AGV is on Southeast
            if (currentPosition.getKey() < agv.getKey() && currentPosition.getValue() > agv.getValue()) {
                sensors[7] = alert;
            }
        }

    }
}
