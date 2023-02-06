package app;
	
import domain.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.Assets.*;
import view.RootLayoutController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main extends Application {
	private static Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Heir> heirObservableList = FXCollections.observableArrayList();

	private ObservableList<Asset> assetObservableList = FXCollections.observableArrayList();

	private Map<Class<? extends Asset>, String> fxmlFileMap = new HashMap<>();



	public Main() {
		fxmlFileMap.put(Yacht.class,"/view/Assets/YachtEdit.fxml");
		fxmlFileMap.put(Island.class,"/view/Assets/IslandEdit.fxml");
		fxmlFileMap.put(Plane.class,"/view/Assets/PlaneEdit.fxml");
		fxmlFileMap.put(Mansion.class,"/view/Assets/MansionEdit.fxml");
		//sample data
		Mansion mansion1 = new Mansion("Villa Grande", "Cool villa for Nuniks","Italy, Rome 21", new BigDecimal("20000000"));
		Plane plane1 = new Plane("Boeing737","No space for legs",new BigDecimal("80000000"),10000,150);
		assetObservableList.add(mansion1);
		assetObservableList.add(plane1);
		SortedSet<Asset> island1Assets = new TreeSet<>();
		island1Assets.add(plane1);
		island1Assets.add(mansion1);

		assetObservableList.add(new Island("Isla Grande", "Cool island tbh", new BigDecimal("2000000000"),new BigDecimal(1000000),99.9f,52.3269544f,4.9621217f, Island.Climate.TEMPERATE,island1Assets));
		Yacht yacht1 = new Yacht("Jan Zizka","Rest in peace",new BigDecimal("1000000"),new BigDecimal("20000000"),45.0d,3,80d,10);
		assetObservableList.add(yacht1);
	}

	public ObservableList<Heir> getHeirObservableList() {
		return heirObservableList;
	}

	public ObservableList<Asset> getAssetObservableList() {
		return assetObservableList;
	}

	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}


	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
        primaryStage.setTitle("Heir BNB");
        initRootLayout();
		primaryStage.show();

		showAssetOverview();
	}
	
	private void initRootLayout() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
	        rootLayout = (BorderPane) fxmlLoader.load();
	        Scene scene = new Scene(rootLayout, 1250, 600);
			primaryStage.setScene(scene);

			RootLayoutController controller = fxmlLoader.getController();
			controller.setMain(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void showAssetOverview() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("/view/Assets/AssetOverview.fxml"));
			System.out.println(fxmlLoader.getLocation());
			AnchorPane assetOverview = fxmlLoader.load();

			rootLayout.setCenter(assetOverview);
			AssetOverviewController controller = fxmlLoader.getController();
			controller.setMain(this);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void showAssetEdit(Asset asset) {
		String fxmlFile = fxmlFileMap.get(asset.getClass());
		System.out.println(fxmlFile);
		try {
			if (fxmlFile != null) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Main.class.getResource(fxmlFile));
				System.out.println(fxmlLoader.getLocation());
				AnchorPane assetOverview = fxmlLoader.load();

				rootLayout.setCenter(assetOverview);

				Object controller = fxmlLoader.getController();

				if (controller instanceof YachtEditController) {
					((YachtEditController) controller).setMain(this);
				} else if (controller instanceof IslandEditController) {
					((IslandEditController) controller).setMain(this);
				} else if (controller instanceof PlaneEditController) {
					((PlaneEditController) controller).setMain(this);
				} else if (controller instanceof MansionEditController) {
					((MansionEditController) controller).setMain(this);
				}

			}


		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
