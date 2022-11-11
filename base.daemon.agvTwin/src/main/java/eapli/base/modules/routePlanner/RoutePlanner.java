package eapli.base.modules.routePlanner;

import eapli.base.common.util.Pair;
import eapli.base.modules.communications.DigitalTwinProxy;
import eapli.base.orderServerAPI.FailedRequestException;
import eapli.base.shared.SharedMemory;
import java.io.IOException;
import java.util.LinkedList;

public class RoutePlanner{

    /**
     * Start.
     *
     * @param sharedMemory
     * @throws IOException
     * @throws FailedRequestException
     */
    public void start(SharedMemory sharedMemory) throws IOException, FailedRequestException, InterruptedException {

        /** initialize DigitalTwinProxy to send request to get the warehouse plant */
        DigitalTwinProxy ola = new DigitalTwinProxy();
        ola.connectAgvManagerServer();
        String pickupOrder = ola.pickupOrderRequest(sharedMemory.getId());
        ola.disconnectAgvManagerServer();

        route(sharedMemory, pickupOrder);
    }

    private void route(SharedMemory sharedMemory, String pickupOrder) throws InterruptedException {

        /** route */
        LinkedList<Pair<Integer,Integer>> route = new LinkedList<>();

        /** The products on the pickupOrder */
        pickupOrder=pickupOrder.replace("[", "");
        pickupOrder=pickupOrder.replace("]", "");
        String products[] = pickupOrder.split(" ");
        int[][] arr = new int[products.length][2];
        /** current position of the agv x and y values */
        int xInicial = sharedMemory.getPosition().getKey();
        int yInicial = sharedMemory.getPosition().getValue();
        System.out.println("\nX: "+xInicial);
        System.out.println("\nY: "+yInicial);

        int acabou = 0;

        /** algorithm where the route will be planned */
        for(int i=0; i<products.length; i++){

            String[] position = products[i].split("-");
            arr[i][0] = Integer.valueOf(position[0]);
            arr[i][1] = Integer.valueOf(position[1]);

            algorithm(xInicial,yInicial,route, arr[i][0], arr[i][1]);
            xInicial = arr[i][0];
            yInicial = arr[i][1];
        }
        for(Pair<Integer, Integer> pair : route){
            System.out.println("Rota "+pair.toString());
        }
        /**save all the changes to the sharedMemory */
        save(sharedMemory,route,acabou);
    }

    /**
     * save changes
     *
     * @param sharedMemory sharedMemory
     * @param route route list
     */
    private void save(SharedMemory sharedMemory, LinkedList<Pair<Integer,Integer>> route, int acabou) throws InterruptedException {
        acabou =1;
        sharedMemory.setAcabou(acabou);
        sharedMemory.updateRoute(route);
    }

    /**
     *algorithm that determines the path to the destination
     *
     * @param xInicial current x value
     * @param yInicial current y value
     * @param route route list
     */
    private void algorithm(int xInicial, int yInicial, LinkedList<Pair<Integer,Integer>> route, Integer xDestino, Integer yDestino){

        /** difference between current location and destination */
        int xDif = xDestino - xInicial;
        int yDif = yDestino - yInicial;

        /** if Y value dif is + then agv will move NORTH */
        if (yDif>0){
            for (int i =0; i< yDif ; i++){
                yInicial++;
                Pair<Integer,Integer> pos = new Pair<>(xInicial, yInicial);
                route.add(pos);
            }
        }
        /** if X value dif is + then agv will move EAST */
        if (xDif>0){
            for (int i =0; i< xDif ; i++){
                xInicial++;
                Pair<Integer,Integer> pos = new Pair<>(xInicial, yInicial);
                route.add(pos);
            }
        }
        /** if Y value dif is - then agv will move SOUTH */
        if (yDif<0){
            for (int i =0; i< Math.abs(yDif) ; i++){
                yInicial--;
                Pair<Integer,Integer> pos = new Pair<>(xInicial, yInicial);
                route.add(pos);
            }
        }
        /** if X value dif is - then agv will move WEST*/
        if (xDif<0){
            for (int i =0; i< Math.abs(xDif) ; i++){
                xInicial--;
                Pair<Integer,Integer> pos = new Pair<>(xInicial, yInicial);
                route.add(pos);
            }
        }
    }


}