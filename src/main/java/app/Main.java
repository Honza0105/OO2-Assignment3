package app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
	private static Stage primaryStage;
	private BorderPane rootLayout;
	
	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}

	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
        primaryStage.setTitle("Heir BNB");
        initRootLayout();
		primaryStage.show();
	}
	
	private void initRootLayout() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
	        rootLayout = (BorderPane) fxmlLoader.load();
	        Scene scene = new Scene(rootLayout, 1250, 600);
			primaryStage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
