package eapli.base.shared;

import eapli.base.agvmanagement.domain.Position;
import eapli.base.agvmanagement.domain.Speed;

import eapli.base.common.util.Pair;
import eapli.base.warehousemanagement.domain.Warehouse.WarehousePlant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SharedMemory {

    /** Shared attributes known by all modules */

    /**Sync Lock*/
    private boolean lock = true;

    /** Agv Identification */
    private volatile String id;

    private  volatile  int acabou=0;
    /** Position */
    private volatile Pair<Integer, Integer> position;

    /** Battery */
    private volatile Integer battery;

    /** Status */
    private volatile String status;

    /** Route */
    private volatile LinkedList<Pair<Integer, Integer>> route = new LinkedList<>();

    private volatile List<Pair<Integer,Integer>> usedPositionList = new ArrayList<>();

    /** List of positions that are at one square distance */
   private volatile List<Pair<Integer,Integer>> positionOneDistanceList = new ArrayList<>();

    /** List of positions that are at two squares distance */
   private volatile List<Pair<Integer,Integer>> positionTwoDistanceList = new ArrayList<>();

    /** List of positions that are at a further square distance
     *
     * (three or more squares distance) */
    private volatile List<Pair<Integer,Integer>> positionFurthestDistanceList = new ArrayList<>();

    /** Speed */
    private volatile int speed;

    /** Represents the eight sensors in an AGV */
    private Integer sensors[] = new Integer[8];

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private List<Position> positionList;

    private List<Speed> speedList;

    private List<LinkedList<Position>> routeList; //route planer list each agv will have one route

    private Pair<Integer, Integer> plant;

    private List<String> idList;

    private int[][] agvPositionOnMap = new int[20][20];
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /** Class constructor */
    public SharedMemory(String agv, Pair<Integer, Integer> position, Integer battery, String status){
        this.id=agv;
        this.position=position;
        this.battery=battery;
        this.status=status;
    }

    /** Methods concerning Synchronization -------------------------------------------------------------------------- */

    /** Checks if the memory access is locked and if it is waits for it to be unlocked */
    public synchronized void lockState() throws InterruptedException {
        if(!this.lock)
            wait();
    }



    /** Locks access to the Shared Memory */
    public synchronized void lock() throws InterruptedException {
        this.lock=false;
    }

    /** Unlocks access to the Shared Memory */
    public synchronized void unlock(){
        this.lock=true;
        notifyAll();
    }

    /** ------------------------------------------------------------------------------------------------------------- */
    public int getAcabou() {
        return acabou;
    }

    public void setAcabou(int acabou) {
        this.acabou = acabou;
    }

    /** Gets the Agv's identification  */
    public String getId() throws InterruptedException {

        lockState();

        lock();
        String agvId = this.id;
        unlock();

        return agvId;
    }

    /** Gets the Agv's position  */
    public Pair<Integer, Integer> getPosition() throws InterruptedException {

        lockState();

        lock();
        Pair<Integer, Integer> agvPosition = this.position;
        unlock();

        return agvPosition;
    }

    /** Gets the list of used position */
    public List<Pair<Integer, Integer>> getUsedPositionList() throws InterruptedException {

        lockState();

        lock();
        List<Pair<Integer, Integer>> list = this.usedPositionList;
        unlock();

        return list;
    }

    /** Gets the position of agvs that are one unit of distance from the twin */
    public List<Pair<Integer, Integer>> getPositionOneDistanceList() throws InterruptedException {

        lockState();

        lock();
        List<Pair<Integer, Integer>> list = this.positionOneDistanceList;
        unlock();

        return list;
    }

    /** Gets the position of agvs that are two units of distance from the twin */
    public List<Pair<Integer, Integer>> getPositionTwoDistanceList() throws InterruptedException {

        lockState();

        lock();
        List<Pair<Integer, Integer>> list = this.positionTwoDistanceList;;
        unlock();

        return list;
    }

    /** Gets the position of agvs that are over two units of distance from the twin */
    public List<Pair<Integer, Integer>> getPositionFurthestDistanceList() throws InterruptedException {

        lockState();

        lock();
        List<Pair<Integer, Integer>> list = this.positionFurthestDistanceList;;
        unlock();

        return list;
    }

    /** Gets the Agv's position  */
    public String getPositionAlter() throws InterruptedException {

        lockState();

        lock();
        Integer positionX =  this.position.getKey();
        Integer positionY =  this.position.getValue();
        unlock();

        return String.format("%d %d", positionX, positionY);
    }

    /** Gets the Agv's battery  */
    public Integer getBattery() throws InterruptedException {

        lockState();

        lock();
        Integer agvBattery = this.battery;
        unlock();

        return agvBattery;
    }

    /** Gets the Agv's status  */
    public String getStatus() throws InterruptedException {

        lockState();

        lock();
        String agvStatus = this.status;
        unlock();

        return agvStatus;
    }

    /** Gets the Route */
    public LinkedList<Pair<Integer, Integer>> getRoute() throws InterruptedException {

        lockState();

        lock();
        LinkedList<Pair<Integer, Integer>> pickUpRoute = this.route;
        unlock();

        return pickUpRoute;
    }

    /** Updates the Agv's position  */
    public void updatePosition(Pair<Integer, Integer> position) throws InterruptedException {

        lockState();

        lock();
        this.position=position;
        unlock();
    }

    /** Updates the Agv's battery  */
    public void updateBattery(Integer battery) throws InterruptedException {

        lockState();

        lock();
        this.battery=battery;
        unlock();
    }

    /** Updates the Agv's status  */
    public void updateStatus(String status) throws InterruptedException {

        lockState();

        lock();
        this.status=status;
        unlock();
    }

    /** Updates the Route */
    public void updateRoute(LinkedList<Pair<Integer, Integer> >route) throws InterruptedException {
        lockState();

        lock();
        this.route=route;
        unlock();
    }

    /** Updates the list of positions that are at one square distance */
    public void updatePositionOneDistanceList(List<Pair<Integer, Integer>> listPos1) throws InterruptedException {
        lockState();

        lock();
        this.positionOneDistanceList = listPos1;
        unlock();
    }

    /** Updates the list of positions that are at two squares distance */
    public void updatePositionTwoDistanceList(List<Pair<Integer, Integer>> listPos2) throws InterruptedException {
        lockState();

        lock();
        this.positionTwoDistanceList = listPos2;
        unlock();
    }

    /** Updates the list of positions that are at three or more squares distance */
    public void updatePositionFurthestDistanceList(List<Pair<Integer, Integer>> listPosRest) throws InterruptedException {
        lockState();

        lock();
        this.positionFurthestDistanceList = listPosRest;
        unlock();
    }

    public SharedMemory(){
    }


    /** Gets the position on the warehouse */
    public int[][] getAgvPositionOnMap() throws InterruptedException {
        lockState();

        lock();
        int position[][] = agvPositionOnMap;
        unlock();
        return position;
    }

    /** Gets the list of sensors */
    public Integer[] getSensors() throws InterruptedException {
        lockState();

        lock();
        Integer[] sensor = this.sensors;
        unlock();

        return sensor;
    }

    /** Updates list of used positions */
    public void updatePositionList(List<Pair<Integer, Integer>> usedPositionList) throws InterruptedException {
        lockState();

        lock();
        this.usedPositionList = usedPositionList;
        unlock();
    }

    /** Updates the sensors */
    public void updateSensors(Integer[] sensors) throws InterruptedException {
        lockState();

        lock();
        this.sensors = sensors;
        unlock();
    }

    public int getSpeed() throws InterruptedException {
        lockState();

        lock();
        int speed = this.speed;
        unlock();
        return  speed;
    }

    public void updateSpeed(int speed) throws InterruptedException {
        lockState();

        lock();
        this.speed = speed;
        unlock();
    }

    public void updateWarehousePlant(Pair<Integer, Integer> plant) throws InterruptedException {
        lockState();

        lock();
        this.plant=plant;
        unlock();
    }

    public Pair<Integer, Integer> getWarehousePlant() throws InterruptedException {
        lockState();

        lock();
        Pair<Integer, Integer> plant = this.plant;
        unlock();

        return plant;
    }

}