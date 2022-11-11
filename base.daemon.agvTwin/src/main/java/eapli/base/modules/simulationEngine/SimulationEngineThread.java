package eapli.base.modules.simulationEngine;

import eapli.base.common.util.Pair;
import eapli.base.modules.communications.DigitalTwinProxy;
import eapli.base.orderServerAPI.FailedRequestException;
import eapli.base.shared.SharedMemory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SimulationEngineThread {

    /**
     * Start.
     *
     * @param sharedMemory
     * @throws IOException
     * @throws FailedRequestException
     * @throws InterruptedException
     */
    public void start(SharedMemory sharedMemory) throws IOException, FailedRequestException, InterruptedException {

        /** Initialize DigitalTwinProxy to get the position of all Agv by using a request */
        DigitalTwinProxy proxy1 = new DigitalTwinProxy();
        proxy1.connectAgvManagerServer();

        /**  get the warehouse plant using a request */
        String warehouse = proxy1.warehousePlantRequest("Armstrong Warehouse");
        proxy1.disconnectAgvManagerServer();

        String[] plant = warehouse.split(" ");

        /** length and width of warehouse */
        int x = Integer.valueOf(plant[0].replace("[", ""));
        int y = Integer.valueOf(plant[1].replace("]", ""));

        sharedMemory.updateWarehousePlant(new Pair<>(x, y));


        while (true) {
            sleep(100);
            String listPosition = "";

            DigitalTwinProxy proxy2 = new DigitalTwinProxy();
            proxy2.connectAgvManagerServer();

            try {
                listPosition = proxy2.allAgvPositionRequest("Armstrong Warehouse", sharedMemory.getId());
                //proxy2.disconnectAgvManagerServer();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FailedRequestException e) {
                e.printStackTrace();
            }

            /** get list of the position of each agv */
            listPosition=listPosition.replace("[", "");
            listPosition=listPosition.replace("]", "");

            String[] agv = listPosition.split("-");
            List<Pair<Integer, Integer>> agvPosition = new ArrayList<>();
            //for (int i=0; i<)


            List<Pair<Integer, Integer>> listPos = getOtherAgvPositions(agv);



            /** get 2D map of the Warehouse with the positions of each agv */
            //int[][] simulation = make2DMap(listPos,x,y);




            /** update the 3 lists with agv position with diferent distances */
            divideAgvByDistance(sharedMemory, listPos);

        }
    }

    /**
     * save the updates in sharedMemory
     *
     * @param sharedMemory shared memory
     * @param listPositionsIn1 list of positions within 1 block away
     * @param listPositionsIn2 list of positions within 2 block away
     * @param listRestPositions list of positions beyond 2 block away
     */
    private void save(SharedMemory sharedMemory, List<Pair<Integer, Integer>> listPositionsIn1 , List<Pair<Integer, Integer>> listPositionsIn2, List<Pair<Integer, Integer>> listRestPositions ) throws InterruptedException {

        sharedMemory.updatePositionOneDistanceList(listPositionsIn1);
        sharedMemory.updatePositionTwoDistanceList(listPositionsIn2);
        sharedMemory.updatePositionFurthestDistanceList(listRestPositions);

    }

    /**
     * make 2D map of the positions
     *
     * @param listPos list of every agv position
     * @param x warehouse length
     * @param y warehouse width
     */
    private int[][] make2DMap( List<Pair<Integer, Integer>> listPos,int x,int y){

        int[][] simulation = new int[x][y];

        for (int i =0; i<x ; i++){
            for (int j=0; j<y; j++){

                /** empty = 0 */
                simulation[x][y] = 0;

                for (Pair<Integer, Integer> pair: listPos){

                    if (pair.getKey() == i && pair.getValue() == j){

                        /** occupied = 1 */
                        simulation[i][j] = 1;
                    }
                }
            }
        }
        return simulation;
    }

    /**
     * put each agv position into diferent lists according their distance to current agv
     *
     * @param listPos list of every agv position
     */
    private void divideAgvByDistance(SharedMemory sharedMemory, List<Pair<Integer, Integer>> listPos ) throws InterruptedException {

        /** get current position of the agv */
        Pair<Integer, Integer> agvPos = sharedMemory.getPosition();

        System.out.println("OLA"+agvPos);

        int x1 = agvPos.getKey();
        int y1 = agvPos.getValue();

        for (Pair<Integer, Integer> pair : listPos) {
            int x2 = pair.getKey();
            int y2 = pair.getValue();
            int xDif = Math.abs(x2 - x1);
            int yDif = Math.abs(y2 - y1);

            /** Closest AGVs, 1 unit distance */
            if ((xDif == 1 && yDif == 1) || (xDif == 1 && yDif == 0) || (xDif == 0 && yDif == 1)) {
                List<Pair<Integer, Integer>> l1 =  sharedMemory.getPositionOneDistanceList();
                l1.add(pair);
                sharedMemory.updatePositionOneDistanceList(l1);
            }

            /** Close AGVs, 2 units distance */
            if ((xDif == 2 && yDif == 2) || (xDif == 2 && yDif == 0) || (xDif == 0 && yDif == 2)) {
                List<Pair<Integer, Integer>> l2 =  sharedMemory.getPositionTwoDistanceList();
                l2.add(pair);
                sharedMemory.updatePositionTwoDistanceList(l2);
            }

            /** Furthest AGVs, over 2 units distance */
            if ((xDif > 2 && yDif > 2) || (xDif > 2 && yDif == 0) || (xDif == 0 && yDif > 2)) {
                List<Pair<Integer, Integer>> l3 =  sharedMemory.getPositionFurthestDistanceList();
                l3.add(pair);
                sharedMemory.updatePositionFurthestDistanceList(l3);
            }
        }
    }

    /**
     * get positions of all agvs
     *
     * @param agv array of agv
     * @return list of positions of other agvs
     */
    private List<Pair<Integer, Integer>> getOtherAgvPositions(String[] agv){

        int[][] arr = new int[agv.length][2];

        for (int i = 0; i < agv.length; i++) {

            String[] position = agv[i].split(" ");
            if(position[0]!="")
                arr[i][0] = Integer.valueOf(position[0]);

            if(position.length==2 && position[1]!="")
                arr[i][1] = Integer.valueOf(position[1]);
        }

        List<Pair<Integer, Integer>> listPos = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {

            Pair<Integer, Integer> pos = new Pair<>(arr[i][0], arr[i][1]);
            listPos.add(pos);
        }
        return listPos;
    }


}
