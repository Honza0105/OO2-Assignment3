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
import util.DateUtil;
import view.Assets.*;
import view.Heirs.*;
import view.Rents.RentsEditController;
import view.Rents.RentsOverviewController;
import view.RootLayoutController;
import view.Statistics.AssetDistributionController;
import view.Statistics.RentedValueDistributionController;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main extends Application {
	private static Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Heir> heirObservableList = FXCollections.observableArrayList();

	private ObservableList<Asset> assetObservableList = FXCollections.observableArrayList();

	private ObservableList<Island> islandObservableList = FXCollections.observableArrayList();

	private HashMap<Asset, Heir> assetHeirHashMap = new HashMap<>();


	private ObservableList<Rent> rentObservableList = FXCollections.observableArrayList();

	private Map<Class<? extends Asset>, String> fxmlFileMap = new HashMap<>();

	private boolean saved = false;

	private boolean editScene = false;



	public Main() {
		fxmlFileMap.put(Yacht.class,"/view/Assets/YachtEdit.fxml");
		fxmlFileMap.put(Island.class,"/view/Assets/IslandEdit.fxml");
		fxmlFileMap.put(Plane.class,"/view/Assets/PlaneEdit.fxml");
		fxmlFileMap.put(Mansion.class,"/view/Assets/MansionEdit.fxml");
		//sample Assets data
		Mansion mansion1 = new Mansion("Villa Grande", "Cool villa for Nuniks","Italy, Rome 21", new BigDecimal("20000000"));
		Plane plane1 = new Plane("Boeing737","No space for legs",new BigDecimal("80000000"),10000,150);
		assetObservableList.add(mansion1);
		assetObservableList.add(plane1);
		SortedSet<Asset> island1Assets = new TreeSet<>();
		island1Assets.add(plane1);
		island1Assets.add(mansion1);
		Island island1 = new Island("Isla Grande", "Cool island tbh", new BigDecimal("200000000"),new BigDecimal(1000000),99.9f,52.3269544f,4.9621217f, Island.Climate.TEMPERATE,island1Assets);
		mansion1.setHomeIsland(island1);
		islandObservableList.add(island1);
		assetObservableList.add(island1);
		Yacht yacht1 = new Yacht("Jan Žižka","Rest in peace",new BigDecimal("1000000"),new BigDecimal("20000000"),45.0d,3,80d,10);
		assetObservableList.add(yacht1);

		//sample Heir data
		Heir john = new Heir("John Johnson", "Amsterdam Noord 12", LocalDate.of(1970,11,11), Heir.Gender.MALE,new BigDecimal("10000000"),new BigDecimal("150000"));
		heirObservableList.add(john);
		Heir sara = new Heir("Sara Zlota", "Poland Danzig 1", LocalDate.of(1999,1,3), Heir.Gender.FEMALE, new BigDecimal("200000000"), new BigDecimal("10000"));
		heirObservableList.add(sara);

		//more heirs:
		Heir mike = new Heir("Mike Smith", "London Bridge 1", LocalDate.of(1980,4,12), Heir.Gender.MALE,new BigDecimal("3000000"),new BigDecimal("100000"));
		heirObservableList.add(mike);
		Heir anna = new Heir("Anna Brown", "Paris Eiffel 1", LocalDate.of(1992,5,6), Heir.Gender.FEMALE,new BigDecimal("50000000"),new BigDecimal("80000"));
		heirObservableList.add(anna);
		Heir jim = new Heir("Jim Star", "New York Central Park 1", LocalDate.of(1986,9,15), Heir.Gender.MALE,new BigDecimal("20000000"),new BigDecimal("120000"));
		heirObservableList.add(jim);
		Heir jessica = new Heir("Jessica Alba", "Los Angeles Sunset Boulevard 1", LocalDate.of(1985,2,25), Heir.Gender.FEMALE,new BigDecimal("70000000"),new BigDecimal("70000"));
		heirObservableList.add(jessica);
		Heir peter = new Heir("Peter Parker", "New York Broadway 1", LocalDate.of(1978,7,20), Heir.Gender.MALE,new BigDecimal("15000000"),new BigDecimal("90000"));
		heirObservableList.add(peter);
		Heir jane = new Heir("Jane Doe", "London Oxford Street 1", LocalDate.of(1984,11,5), Heir.Gender.FEMALE,new BigDecimal("40000000"),new BigDecimal("110000"));
		heirObservableList.add(jane);
		Heir tom = new Heir("Tom Cruise", "Los Angeles Hollywood 1", LocalDate.of(1962,7,3), Heir.Gender.MALE,new BigDecimal("25000000"),new BigDecimal("140000"));
		heirObservableList.add(tom);




		sara.addAsset(mansion1);
		assetHeirHashMap.put(mansion1,sara);

		john.addAsset(plane1);
		assetHeirHashMap.put(plane1,john);

		john.addAsset(assetObservableList.get(2));
		assetHeirHashMap.put(assetObservableList.get(2),john);
		john.addAsset(yacht1);
		assetHeirHashMap.put(yacht1,john);


		Rent sampleRent = new Rent(yacht1,sara,LocalDate.now(),LocalDate.of(2023,3,30),new BigDecimal("300000"),LocalDate.of(2023,3,10));
		rentObservableList.add(sampleRent);

		System.out.println(island1.removeAsset(plane1));
		System.out.println(island1.getAssets());

		Rent sampleRent2 = new Rent(mansion1,john,LocalDate.now(),LocalDate.of(2023,2,28),new BigDecimal("30000"),LocalDate.of(2023,3,10));
		rentObservableList.add(sampleRent2);





	}

	public ObservableList<Heir> getHeirObservableList() {
		return heirObservableList;
	}

	public ObservableList<Heir> getHeirObservableListWithoutOwner(Heir owner) {
		ObservableList<Heir> result = FXCollections.observableArrayList(heirObservableList);
		result.remove(owner);
		return result;
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

	public Stage getStage(){
		return primaryStage;
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
		setEditScene(false);
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

	public void showHeirOverview() {
		setEditScene(false);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("/view/Heirs/HeirOverview.fxml"));
			System.out.println(fxmlLoader.getLocation());
			AnchorPane heirOverview = fxmlLoader.load();

			rootLayout.setCenter(heirOverview);
			HeirOverviewController controller = fxmlLoader.getController();
			controller.setMain(this);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}


	public void showHeirEdit(Heir heir) {
		setEditScene(true);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("/view/Heirs/HeirEdit.fxml"));
			System.out.println(fxmlLoader.getLocation());
			AnchorPane heirEdit = fxmlLoader.load();

			rootLayout.setCenter(heirEdit);
			HeirEditController controller = fxmlLoader.getController();
			controller.setMain(this);
			controller.setHeir(heir);
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
					((YachtEditController) controller).setYacht((Yacht) asset);
				} else if (controller instanceof IslandEditController) {
					((IslandEditController) controller).setMain(this);
					((IslandEditController) controller).setIsland((Island) asset);
				} else if (controller instanceof PlaneEditController) {
					((PlaneEditController) controller).setMain(this);
					((PlaneEditController) controller).setPlane((Plane) asset);
				} else if (controller instanceof MansionEditController) {
					((MansionEditController) controller).setMain(this);
					((MansionEditController) controller).setMansion((Mansion) asset);
				}

			}


		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public void showAssetCreate(String type, Heir heir) {
		setEditScene(true);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			if (type.equals("Yacht")){
				fxmlLoader.setLocation(Main.class.getResource("/view/Heirs/YachtCreate.fxml"));
				AnchorPane assetCreate = fxmlLoader.load();
				rootLayout.setCenter(assetCreate);
				YachtCreateController controller = fxmlLoader.getController();
				controller.setMain(this);
				controller.setYacht(heir);
			}
			if (type.equals("Plane")){
				fxmlLoader.setLocation(Main.class.getResource("/view/Heirs/PlaneCreate.fxml"));
				AnchorPane assetCreate = fxmlLoader.load();
				rootLayout.setCenter(assetCreate);
				PlaneCreateController controller = fxmlLoader.getController();
				controller.setMain(this);
				controller.setPlane(heir);
			}
			if (type.equals("Mansion")){
				fxmlLoader.setLocation(Main.class.getResource("/view/Heirs/MansionCreate.fxml"));
				AnchorPane assetCreate = fxmlLoader.load();
				rootLayout.setCenter(assetCreate);
				MansionCreateController controller = fxmlLoader.getController();
				controller.setMain(this);
				controller.setMansion(heir);
			}
			if (type.equals("Island")){
				fxmlLoader.setLocation(Main.class.getResource("/view/Heirs/IslandCreate.fxml"));
				AnchorPane assetCreate = fxmlLoader.load();
				rootLayout.setCenter(assetCreate);
				IslandCreateController controller = fxmlLoader.getController();
				controller.setMain(this);
				controller.setIsland(heir);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}




	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public boolean isEditScene() {
		return editScene;
	}

	public void setEditScene(boolean editScene) {
		this.editScene = editScene;
	}

	public ObservableList<Island> getIslandObservableList() {
		return islandObservableList;
	}

	public void createHeir() {
		setEditScene(false);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("/view/Heirs/HeirCreate.fxml"));
			System.out.println(fxmlLoader.getLocation());
			AnchorPane createHeir = fxmlLoader.load();

			rootLayout.setCenter(createHeir);
			HeirCreateController controller = fxmlLoader.getController();
			controller.setMain(this);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void startRent(Asset asset, LocalDate fromDate, LocalDate tillDate, Heir occupant){
		Rent newRent = new Rent(asset,occupant,fromDate,tillDate,asset.getRent(fromDate,tillDate), DateUtil.getPaymentDay());
		rentObservableList.add(newRent);
		System.out.println(asset.getRent(fromDate,tillDate));
		System.out.println(rentObservableList);
	}

	public void showRentOverview(){
		setEditScene(false);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("/view/Rents/RentsOverview.fxml"));
			System.out.println(fxmlLoader.getLocation());
			AnchorPane rentsOverview = fxmlLoader.load();

			rootLayout.setCenter(rentsOverview);
			RentsOverviewController controller = fxmlLoader.getController();
			controller.setMain(this);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void showRentEdit(Rent rent) {
		setEditScene(true);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("/view/Rents/RentsEdit.fxml"));
			System.out.println(fxmlLoader.getLocation());
			AnchorPane rentEdit = fxmlLoader.load();

			rootLayout.setCenter(rentEdit);
			RentsEditController controller = fxmlLoader.getController();
			controller.setMain(this);
			controller.setRent(rent);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void showAssetDistribution() {
		setEditScene(false);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("/view/Statistics/AssetDistribution.fxml"));
			System.out.println(fxmlLoader.getLocation());
			AnchorPane assetDistribution = fxmlLoader.load();

			rootLayout.setCenter(assetDistribution);
			AssetDistributionController controller = fxmlLoader.getController();
			controller.setMain(this);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public void showValueOverTimeDistribution() {
		setEditScene(false);
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("/view/Statistics/RentedValueDistribution.fxml"));
			System.out.println(fxmlLoader.getLocation());
			AnchorPane valueDistribution = fxmlLoader.load();

			rootLayout.setCenter(valueDistribution);
			RentedValueDistributionController controller = fxmlLoader.getController();
			controller.setRentList(getRentObservableList());
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public ObservableList<Rent> getRentObservableList() {
		return rentObservableList;
	}

	public HashMap<Asset, Heir> getAssetHeirHashMap() {
		return assetHeirHashMap;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
