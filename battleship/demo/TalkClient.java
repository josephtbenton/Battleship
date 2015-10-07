package demo;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TalkClient extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			BorderPane root = (BorderPane) loader.load(getClass().getResource("TalkClientGUI.fxml").openStream());
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			primaryStage.setOnCloseRequest(event -> System.exit(0));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}