package net;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Eric on 10/3/2015.
 */
public class Network {
    String ipAddress;
    ArrayBlockingQueue<String> messages = new ArrayBlockingQueue<>(20);
    int port;

    public Network() {
        port = 8000;
        new Thread(() -> {
            try {
                Server s = new Server(port);
                s.listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ).start();
    }

    public void Connect(String ip, int port){
        ipAddress = ip;
        this.port = port;
        try{
            sendTo(ip,port,"TConnection Successful");
        } catch(Exception connectionfail){
            connectionfail.printStackTrace();
        }
    }


    public void send(String text) {
        try {
            sendTo(ipAddress, port, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void badNews(String what) {
        // TODO: THROW ALL THE ERRORS
    }

    void sendTo(String host, int port, String message) {
        new Thread(() -> {
            try {
                Socket target = new Socket(host, port);
                send(target, message);
                receive(target);
                target.close();
            } catch (Exception e) {
                Platform.runLater(() -> badNews(e.getMessage()));
                e.printStackTrace();
            }
        }).start();
    }

    void send(Socket target, String message) throws IOException {
        PrintWriter sockout = new PrintWriter(target.getOutputStream());
        sockout.println(message);
        sockout.flush();
    }

    void receive(Socket target) throws IOException {
        BufferedReader sockin = new BufferedReader(new InputStreamReader(target.getInputStream()));
        while (!sockin.ready()) {}
        while (sockin.ready()) {
            try {
                String msg = sockin.readLine();
                messages.put(msg);
            } catch (Exception e) {
                Platform.runLater(() -> badNews(e.getMessage()));
                e.printStackTrace();
            }
        }
    }



}
