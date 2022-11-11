package eapli.base.dashboard;

import eapli.base.agvmanagement.dto.AGVDTO;
import eapli.base.dashboard.application.DashboardController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Properties;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Dashboard extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(Dashboard.class);

    //static private SSLServerSocket sock;

    static private ServerSocket sock;
    private static DashboardController controller;
    private static final int PORT = 8080;
    private static List<AGVDTO> listagvdto;

    public Dashboard(List<AGVDTO> listagvdto) {
        this.listagvdto = listagvdto;
    }

    public Dashboard() {

    }

    public void changeController(DashboardController controller){
        this.controller = controller;
    }

    private static final String web = "www";

    public void run() {

        Socket cliSock;
        int port = PORT;
        try {

            sock = new ServerSocket(port);

        } catch (IOException ex) {

            System.out.println("Server failed to open local port " + port);

            return;
        }

        while (true) {

            try {
                cliSock = sock.accept();

            } catch (IOException e) {

                System.out.println("Server error - " + e.getMessage());

                return;
            }
                listagvdto = controller.getList();

                DashboardRequest request = new DashboardRequest(cliSock, web);

                request.start();
        }
        /*   SSLSocket cliSock = null;

        // Use this certificate and private key as server certificate

        System.setProperty("javax.net.ssl.keyStore", "SSL/dashboard.jks");

        System.setProperty("javax.net.ssl.keyStorePassword", "dashboard");

        System.setProperty("javax.net.ssl.trustStore", "SSL/dashboard.jks");

        System.setProperty("javax.net.ssl.trustStorePassword", "dashboard");

        try {
            SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sock = (SSLServerSocket) sslF.createServerSocket(PORT);
        } catch (IOException ex) {
            System.out.println("Server failed to open local port " + PORT);
            System.exit(1);
        }*/

           /* while (true) {

                try {
                    cliSock = sock.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

        //  HTTPmessage request1 = new HTTPmessage();
        //  request1.setRequestMethod("GET");
        //   request1.setURI("/status/");

       // listagvdto = controller.getList();

       // DashboardRequest request = new DashboardRequest(cliSock, web);

       // request.start();
   // }
//}
    }
    private static int y = 17;
    private static int x = 2;
    private static String ola = "ASSIGNED";
    private static int o = 0;

    public static synchronized String getStatusInHTML() {

        if (o == 0) {
            y--;
            x++;
        }
        if (y==10){
            x--;
        }
        if (y==15){
            x++;
        }
        if (y == 2) {
            ola = "FREE";
            o = 1;
        }

        if (listagvdto != null) {
            StringBuilder s = new StringBuilder();
            for (AGVDTO agv: listagvdto
                 ) {
                s.append("<tr class=\"active-row\">" +
                        "<td>" + listagvdto.indexOf(agv) + "</td>" +
                        "<td>" + agv.getIdentifier() + "</td>" +
                        "<td>" + agv.getTaskStatus() + "</td>" +
                        "<td>" + agv.getPosition().getKey() + "</td>" +
                        "<td>" + agv.getPosition().getValue() + "</td>" +
                        "</tr>");

            }
            return s.toString();

        }else{
            return "<tr class=\"active-row\">" +
                    "<td>" + "1" + "</td>" +
                    "<td>" + "AGV1" + "</td>" +
                    "<td>" + ola + "</td>" +
                    "<td>" + x + "</td>" +
                    "<td>" + y + "</td>" +
                    "</tr>" +

                    "<tr class=\"active-row\">" +
                    "<td>" + "2" + "</td>" +
                    "<td>" + "AGV2" + "</td>" +
                    "<td>" + "CHARGING" + "</td>" +
                    "<td>" + 1 + "</td>" +
                    "<td>" + 2 + "</td>" +
                    "</tr>";
        }
    }

    public static synchronized String getPlantHTML() {

        if (listagvdto != null) {

            int length = controller.getPlant().getKey();
            int width = controller.getPlant().getValue();

            StringBuilder builder1 = new StringBuilder();

            //Pair<Integer,Integer> plant = controller.getPlant();   10x10
            builder1.append("<tr>");
            builder1.append("<th> X/Y </th>");
            for (int m = 1; m <= length; m++) {

                builder1.append("<th> X=" + m + "</th>");
            }

            builder1.append("</tr>");
            for (int i = 1; i <= width; i++) {
                builder1.append("<tr> ");
                for (int j = 0; j <= length; j++) {
                    if (j == 0) {
                        builder1.append("<th> Y=" + i + "</th>");

                    }
                        builder1.append("<td>  </td>");
                    for (AGVDTO agv: listagvdto
                         ) {

                        if (agv.getPosition().getKey() == j+2 && agv.getPosition().getValue() ==i){

                            builder1.append("<td> AGVNO:" + listagvdto.indexOf(agv) +"</td>");
                        }else if(j ==1 && agv.getPosition().getKey() == j && agv.getPosition().getValue() ==i){
                            builder1.append("<td> AGVNO:" + listagvdto.indexOf(agv) +"</td>");
                        }



                    }
                    }

                }
                builder1.append("</tr>");
            return builder1.toString();


        }else{
            StringBuilder builder = new StringBuilder();

            //Pair<Integer,Integer> plant = controller.getPlant();   10x10
            builder.append("<tr>");
            builder.append("<th> X/Y </th>");
            for (int m = 1; m <= 20; m++) {

                builder.append("<th> X=" + m + "</th>");
            }

            builder.append("</tr>");
            for (int i = 1; i <= 18; i++) {
                builder.append("<tr> ");
                for (int j = 1; j <= 20; j++) {
                    if (j == 1) {
                        builder.append("<th> Y=" + i + "</th>");

                    }
                    if (i == y && j == x) {
                        builder.append("<td> 1 </td>");
                    } else if (i == 2 && j == 1) {
                        builder.append("<td> 2 </td>");
                    } else {
                        builder.append("<td>  </td>");
                    }

                }
                builder.append("</tr>");
            }

            return builder.toString();
        }
    }
}

