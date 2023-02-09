package view;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class RootLayoutController {

	private Main main;

	public void setMain(Main main) {
		this.main = main;

	}

	@FXML
	public void initialize() {

	}
	
	@FXML
	public void onClose() {
		System.exit(0);
	}
	
	@FXML
	public void onAbout() {
		
	}

	@FXML
	public void showAssetsMenuButton(){

		if (main.isSaved()){
			main.showAssetOverview();
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(main.getStage());
			alert.setTitle("File not saved");
			alert.setHeaderText("Are you sure?");
			alert.setContentText("If not saved, all changes will be lost.");
			ButtonType buttonSaveBeforeExit = new ButtonType("I will save my data");
			ButtonType buttonExitAnyways = new ButtonType("Exit anyways");

			alert.getButtonTypes().setAll(buttonSaveBeforeExit, buttonExitAnyways);

			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == buttonExitAnyways) {
				main.showAssetOverview();
			}

		}
	}

	@FXML
	public void onHelp(){
		//To be implemented
	}
}
