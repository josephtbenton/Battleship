//package gui;
//
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import net.Server;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.concurrent.ArrayBlockingQueue;
//
//public class Controller {
//
//    @FXML
//    TextArea outgoing;
//
//    @FXML
//    TextArea incoming;
//
//    @FXML
//    Button send;
//
//    @FXML
//    TextField host;
//
//    @FXML
//    TextField port;
//
//    ArrayBlockingQueue<String> messages = new ArrayBlockingQueue<>(20);
//
//    // TODO you can actually move this receiver into net package.
//    @FXML
//    void initialize() {
//        incoming.setEditable(false);
//        send.setOnAction(event -> send());
//        new Thread(() -> {
//            for (;;) {
//                try {
//                    String msg = messages.take();
//                    Platform.runLater(() -> {incoming.setText(incoming.getText() + '\n' + msg);});
//                } catch (Exception e) {
//                    badNews(e.getMessage());
//                }
//
//            }
//        }).start();
//
//    }
//
//    void badNews(String what) {
//        Alert badNum = new Alert(Alert.AlertType.ERROR);
//        badNum.setContentText(what);
//        badNum.show();
//    }
//
//    void send() {
//        new Thread(() -> {
//            try {
//                Server s = new Server(Integer.parseInt(port.getText()));
//                s.listen();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        ).start();
//        try {
//            sendTo(host.getText(), Integer.parseInt(this.port.getText()), outgoing.getText());
//        } catch (NumberFormatException nfe) {
//            badNews(String.format("\"%s\" is not an integer", this.port.getText()));
//        }
//    }
//
//    void sendTo(String host, int port, String message) {
//        new Thread(() -> {
//            try {
//                Socket target = new Socket(host, port);
//                send(target, message);
//                receive(target);
//                target.close();
//            } catch (Exception e) {
//                Platform.runLater(() -> badNews(e.getMessage()));
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//    void send(Socket target, String message) throws IOException {
//        PrintWriter sockout = new PrintWriter(target.getOutputStream());
//        sockout.println(message);
//        sockout.flush();
//    }
//
//    void receive(Socket target) throws IOException {
//        BufferedReader sockin = new BufferedReader(new InputStreamReader(target.getInputStream()));
//        while (!sockin.ready()) {}
//        while (sockin.ready()) {
//            try {
//                String msg = sockin.readLine();
//                messages.put(msg);
//            } catch (Exception e) {
//                Platform.runLater(() -> badNews(e.getMessage()));
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
