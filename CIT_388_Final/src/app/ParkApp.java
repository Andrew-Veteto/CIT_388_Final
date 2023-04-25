package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ParkApp extends Application{

		@Override
		public void start(Stage primaryStage) {
			try {
				BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("display.fxml"));
				Scene scene = new Scene(root, 900, 600);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setTitle("Park App Project");
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public static void main(String[] args) {
		
		launch(args);

	}
	
}
