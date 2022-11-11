package eapli.base.dashboard;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.Socket;

public class DashboardRequest extends Thread {

    private String web;
    DataInputStream input;
    DataOutputStream output;
    //private SSLSocket socket;
    private Socket socket;

    public DashboardRequest(Socket socket, String web)  {

        this.web = web;
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Thread error on data streams creation");
        }
        try {
            HTTPmessage request = new HTTPmessage(input);
            HTTPmessage response = new HTTPmessage();

            if (request.getMethod().equals("GET")) {
                if (request.getURI().equals("/status")) {
                    String content = Dashboard.getStatusInHTML();
                    if (content != null) {
                        response.setContentFromString(content, "text/html");
                        response.setResponseStatus("200 Ok");


                    } else {
                        response.setContentFromString(
                                "<span id=\"loading\">Error loading content, trying again...</span>", "text/html");
                        response.setResponseStatus("404 Not Found");
                    }

                }else if(request.getURI().equals("/position")){
                    String content = Dashboard.getPlantHTML();
                    if (content != null) {
                        response.setContentFromString(content, "text/html");
                        response.setResponseStatus("200 Ok");


                    } else {
                        response.setContentFromString(
                                "<span id=\"loading\">Error loading content, trying again...</span>", "text/html");
                        response.setResponseStatus("404 Not Found");
                    }

                } else {
                    String fullname = web/* + "/"*/;
                    if (request.getURI().equals("/")) fullname += "/index.html";
                    else fullname += request.getURI();
                    //System.out.println(fullname);

                    if (response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    } else {
                        response.setContentFromString(
                                "<html><body><h1>404 File not found</h1></body></html>", "text/html");
                        response.setResponseStatus("404 Not Found");
                    }
                }
                response.send(output);
            }/*else if(request.getMethod().equals("PUT")){
                if (request.getURI().equals("/updateAgv")){
                    response.setResponseStatus("200 OK");
                }else {
                    response.setResponseStatus("404 Not Found");
                }*/

         //   }
        } catch (IOException ex) {
            System.out.println("Thread error when reading request");
            //ex.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Thread error when closing socket");
        }
    }

}
