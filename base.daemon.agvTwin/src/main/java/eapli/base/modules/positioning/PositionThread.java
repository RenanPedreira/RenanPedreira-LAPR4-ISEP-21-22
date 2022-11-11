package eapli.base.modules.positioning;

import eapli.base.orderServerAPI.FailedRequestException;
import eapli.base.shared.SharedMemory;
import eapli.base.common.util.Pair;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class PositionThread {

    /**
     * Start.
     *
     * @param sharedMemory the shared memory
     * @throws InterruptedException the interrupted exception
     */
    public void start(SharedMemory sharedMemory) throws InterruptedException, IOException, FailedRequestException {

        while (true) {

            /** Every 100ms it will send the agv positions to the Sensor Module */
            sleep(1000);

            /** Get Last Position  */
            Pair<Integer, Integer> position = sharedMemory.getPosition();
            System.out.println("POSIÇÃO ATUAL "+position);
            /** Get Positions that were travelled */
            List<Pair<Integer, Integer>> travelledPositionList = sharedMemory.getUsedPositionList();


            /** Get the Route that is a List with Positions that will be crossed */

            LinkedList<Pair<Integer, Integer>> route = sharedMemory.getRoute();

            /** Get Speed */
            int speed = sharedMemory.getSpeed();

            /** Get ID */
            String id = sharedMemory.getId();

            Integer[] sensor = sharedMemory.getSensors();

            int colision = 0;

            /** Loop to remove the next Position in the Route List */
            position = getNextPosition(sharedMemory, speed, travelledPositionList, route, sensor, position, colision);

            /** Saves all the changes: Updated Route list without the
             * current position, updated Position, updated Speed and updated Travelled list*/
            save(sharedMemory, position, speed, route, travelledPositionList);
        }
    }

    /**
     * save the updates in the sharedMemory
     *
     * @param sharedMemory shared memory
     * @param position new position
     * @param speed new speed
     * @param route updated route without last position
     * @param travelledPositionList updated list with last position
     *
     */
    private void save(SharedMemory sharedMemory, Pair<Integer, Integer> position, int speed, LinkedList<Pair<Integer,Integer>> route, List<Pair<Integer,Integer>> travelledPositionList) throws InterruptedException {

        sharedMemory.updatePosition(position);
        sharedMemory.updateSpeed(speed);
        sharedMemory.updateRoute(route);
        sharedMemory.updatePositionList(travelledPositionList);

    }

    /**
     * get next position
     *
     * @param speed actual speed
     * @param travelledPositionList actual list
     * @param route actual route
     * @param sensor
     * @param position
     * @return next position
     */
    private Pair<Integer,Integer> getNextPosition(SharedMemory sharedMemory, int speed, List<Pair<Integer, Integer>> travelledPositionList, LinkedList<Pair<Integer, Integer>> route, Integer[] sensor, Pair<Integer, Integer> position, int colision){

        Pair<Integer,Integer> pos;
        if (travelledPositionList.contains(position)){
            colision = 0;
            System.out.println("JA PAREI MAS VOU EM FRENTE E DAR GHOST");
        }
        travelledPositionList.add(position);



        if(route.size()==1) {
                try {
                    sharedMemory.updateStatus("FREE");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return route.getFirst();
            }

            int dir = -1;



            int yInicial = position.getValue();
            int xInicial = position.getKey();

            int xDif = route.getFirst().getKey()-position.getKey();
            int yDif = route.getFirst().getValue()-position.getValue();

            if(xDif == 0 ){

                if (yDif>0){
                    dir = 0;
                }else{
                    dir = 1;
                }
            }
            if (yDif ==0){

                if (xDif>0){
                    dir = 3;
                }else{
                    dir = 2;
                }
            }

            if(colision == 1) {

                if (dir == 0) {
                    if (sensor[0] == 1) {
                        if (yInicial == 1) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial + 1, yInicial);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getKey() + 1);
                                if (m == route.size() - 1 && route.get(m).getKey() < pos1.getKey()) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey() - 1, route.get(m).getValue());
                                    route.add(pos2);
                                }
                                if (m == route.size() - 1 && route.get(m).getKey() > pos1.getKey()) {
                                    route.remove(m);
                                }

                            }

                            return pos1;
                        }
                    }
                    if (sensor[4] == 1) {
                        if (yInicial == 1) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial + 1, yInicial);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getKey() + 1);
                                if (m == route.size() - 1 && route.get(m).getKey() < pos1.getKey()) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey() - 1, route.get(m).getValue());
                                    route.add(pos2);
                                }
                                if (m == route.size() - 1 && route.get(m).getKey() > pos1.getKey()) {
                                    route.remove(m);
                                }
                            }
                            return pos1;
                        }
                    }
                    if (sensor[5] == 1) {
                        if (yInicial == 1) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial - 1, yInicial);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getKey() - 1);
                                if (m == route.size() - 1 && route.get(m).getKey() < pos1.getKey()) {
                                    route.remove(m);
                                }
                                if (m == route.size() - 1 && route.get(m).getKey() > pos1.getKey()) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey() + 1, route.get(m).getValue());
                                    route.add(pos2);

                                }
                            }
                            return pos1;
                        }
                    }
                }

                if (dir == 1) {
                    if (sensor[1] == 1) {
                        if (yInicial == 20) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial - 1, yInicial);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getKey() - 1);
                                if (m == route.size() - 1) {
                                    route.remove(m);
                                }
                                if (m == route.size() - 1 && route.get(m).getKey() > pos1.getKey()) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey() + 1, route.get(m).getValue());
                                    route.add(pos2);
                                }
                            }
                            return pos1;
                        }
                    }
                    if (sensor[6] == 1) {
                        if (yInicial == 20) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial + 1, yInicial);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getKey() + 1);
                                if (m == route.size() - 1) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey() - 1, route.get(m).getValue());
                                    route.add(pos2);
                                }
                                if (m == route.size() - 1 && route.get(m).getKey() > pos1.getKey()) {
                                    route.remove(m);
                                }
                            }
                            return pos1;
                        }
                    }
                    if (sensor[7] == 1) {
                        if (yInicial == 20) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial - 1, yInicial);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getKey() - 1);
                                if (m == route.size() - 1) {
                                    route.remove(m);
                                }
                                if (m == route.size() - 1 && route.get(m).getKey() > pos1.getKey()) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey() + 1, route.get(m).getValue());
                                    route.add(pos2);
                                }
                            }
                            return pos1;
                        }
                    }
                }

                if (dir == 2) {
                    if (sensor[2] == 1) {
                        if (xInicial == 20) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial - 1);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getValue() - 1);
                                if (m == route.size() - 1) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey(), route.get(m).getValue() + 1);
                                    route.add(pos2);
                                }

                            }
                            return pos1;
                        }
                    }
                    if (sensor[5] == 1) {
                        if (xInicial == 20) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial + 1);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getValue() + 1);
                                if (m == route.size() - 1) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey(), route.get(m).getValue() - 1);
                                    route.add(pos2);
                                }

                            }
                            return pos1;
                        }
                    }
                    if (sensor[7] == 1) {
                        if (xInicial == 20) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial - 1);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getValue() - 1);
                                if (m == route.size() - 1) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey() + 1, route.get(m).getValue() + 1);
                                    route.add(pos2);
                                }

                            }
                            return pos1;
                        }
                    }

                }

                if (dir == 3) {

                    if (sensor[3] == 1) {
                        if (xInicial == 1) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial + 1);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getValue() + 1);
                                if (m == route.size() - 1) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey(), route.get(m).getValue() - 1);
                                    route.add(pos2);
                                }

                            }
                            return pos1;
                        }
                    }
                    if (sensor[4] == 1) {
                        if (xInicial == 1) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial + 1);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getValue() + 1);
                                if (m == route.size() - 1) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey(), route.get(m).getValue() - 1);
                                    route.add(pos2);
                                }

                            }
                            return pos1;
                        }
                    }
                    if (sensor[6] == 1) {
                        if (xInicial == 1) {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial);
                            return pos1;
                        } else {
                            Pair<Integer, Integer> pos1 = new Pair<>(xInicial, yInicial - 1);
                            for (int m = 0; m > route.size(); m++) {
                                route.get(m).setKey(route.get(m).getValue() - 1);
                                if (m == route.size() - 1) {
                                    Pair<Integer, Integer> pos2 = new Pair<>(route.get(m).getKey(), route.get(m).getValue() + 1);
                                    route.add(pos2);
                                }

                            }
                            return pos1;
                        }
                    }
                }
            }

            if (colision ==2){

                if (dir==0 && ((sensor[0] == 1) || (sensor[4] == 1) || (sensor[5] == 1))){
                    System.out.println("AGV AVISTADO VOU PARAR");
                    return position;
                }
                if (dir == 1 && ((sensor[1] == 1) || (sensor[6] == 1) || (sensor[7] == 1))){
                    System.out.println("AGV AVISTADO VOU PARAR");
                    return position;
                }
                if (dir == 2 && ((sensor[2] == 1) || (sensor[5] == 1) || (sensor[7] == 1))){
                    System.out.println("AGV AVISTADO VOU PARAR");
                    return position;
                }
                if (dir == 3 && ((sensor[3] == 1) || (sensor[4] == 1) || (sensor[6] == 1))){
                    System.out.println("AGV AVISTADO VOU PARAR");
                    return position;
                }

                if (dir == 0 && ((sensor[0] == 2) || (sensor[4] == 2) || (sensor[5] == 2))) {
                    speed = 1;
                }

                if (dir == 1 && ((sensor[1] == 2) || (sensor[6] == 2) || (sensor[7] == 2))) {
                    speed = 1;
                }

                if (dir == 2 && ((sensor[2] == 2) || (sensor[5] == 2) || (sensor[7] == 2))) {
                    speed = 1;
                }

                if (dir == 3 && ((sensor[3] == 2) || (sensor[4] == 2) || (sensor[6] == 2))) {
                    speed = 1;
                }
            }
            position = route.getFirst();

            route.removeFirst();
            return position;

        }
}
