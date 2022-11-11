package eapli.base.modules.controlUnit;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.common.util.Pair;
import eapli.base.modules.communications.DigitalTwinServer;
import eapli.base.protocol.AgvTwinProtocolRequest;
import eapli.base.shared.SharedMemory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class DigitalTwinControlUnit {

    private static final Logger LOGGER = LogManager.getLogger(DigitalTwinServer.class);

    public static class AgvTwinHandler {

        private Socket agvTwinSocket;

        public AgvTwinHandler(final Socket socket) {
            this.agvTwinSocket = socket;
        }

        public void start(SharedMemory sharedMemory) {
            final var clientIP = agvTwinSocket.getInetAddress();
            final int port = agvTwinSocket.getPort();

            LOGGER.debug("Accepted connection from {}:{}", clientIP.getHostAddress(), port);

            try (var out = new PrintWriter(agvTwinSocket.getOutputStream(), true);
                 var in = new BufferedReader(new InputStreamReader(agvTwinSocket.getInputStream()))) {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    LOGGER.debug("Received message:----\n{}\n----", inputLine);

                    DigitalTwinParser parser = new DigitalTwinParser(sharedMemory);
                    final AgvTwinProtocolRequest request = DigitalTwinParser.parse(inputLine);
                    final String response = request.execute(); //IMPORTANT
                    out.println(response);
                    LOGGER.debug("Sent message:----\n{}\n----", response);

                    if (request.isGoodbye()) {
                        break;
                    }
                }
            } catch (final IOException e) {
                LOGGER.error(e);
            } finally {
                try {
                    agvTwinSocket.close();
                    LOGGER.debug("Closing agv twin socket {}:{}", clientIP.getHostAddress(), port);
                } catch (final IOException e) {
                    LOGGER.error("While closing the agv twin socket {}:{}", clientIP.getHostAddress(),
                            agvTwinSocket.getPort(), e);
                }
                // null the reference to ensure it will be caught by the garbage collector
                agvTwinSocket = null;

                // helper debug - SHOULD NOT BE USED IN PRODUCTION CODE!!!
                if (LOGGER.isDebugEnabled()) {
                    final int finalThreadCount = Thread.activeCount();
                    LOGGER.debug("Ending agv twin thread - final thread count: {}", finalThreadCount);
                    final Thread[] t = new Thread[finalThreadCount];
                    final int n = Thread.enumerate(t);
                    for (var i = 0; i < n; i++) {
                        LOGGER.debug("T {}: {}", t[i].getId(), t[i].getName());
                    }
                }
            }
        }
    }

    /** Runs when the Control Unit Thread starts */
    public void start(SharedMemory sharedMemory, AGVRepository agvRepository, AGV agv) throws InterruptedException {


        /** The AGV's global status is shown on the terminal every 1 second */
        while(true){
            sleep(1000);
            System.out.println("\n\n\n------------------------------------------");
            System.out.println("\nControl Unit: "+sharedMemory.getId());
            System.out.println("Current Position: "+sharedMemory.getPosition());
            System.out.println("Current Status: "+sharedMemory.getStatus());
            System.out.println("Current Battery: "+sharedMemory.getBattery());
            for(int i=0; i<8; i++)
                System.out.println("Sensor "+i+1+": "+sharedMemory.getSensors()[i]);
            System.out.println("Route to travel:");
            for(Pair<Integer, Integer> pos : sharedMemory.getRoute())
                System.out.println("-> "+pos);
            System.out.println("\n\n\n------------------------------------------");

            /** Updates the position on the AGV */
            agv.changePosition(sharedMemory.getPosition());

            /** Updates the status on the AGV */
            agv.changeStatus2(sharedMemory.getStatus());

            /** Persists the changes on teh database */
            agvRepository.save(agv);
        }
    }
}
