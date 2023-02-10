package view;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

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
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Are you sure?");
		dialog.setContentText("Be aware that all unsaved activity will be lost!");
		ButtonType yesButton = new ButtonType("Yes I am sure", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("No! I have changed my mind", ButtonBar.ButtonData.NO);

		dialog.getDialogPane().getButtonTypes().setAll(yesButton, noButton);

		Optional<ButtonType> result = dialog.showAndWait();

		if (result.isPresent() && result.get() == yesButton) {
			System.exit(0);
		}
	}
	
	@FXML
	public void onAbout() {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("About");
		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		dialog.setContentText("""
								  Welcome to HeirBnB™ !

				We provide the most luxurious service to the richest among us.
				Add your valuable assets and rent them through our service.
				
				In case of any technical difficulties contact us 24/7 on:
				Tel: +31 06 29 29 29 29
				Email: heirbnbsupport@heir.com""");
		dialog.getDialogPane().getButtonTypes().add(type);
		dialog.showAndWait();

	}

	@FXML
	public void showAssetsMenuButton(){

		if (main.isSaved() || !main.isEditScene()){
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
	public void showHeirsMenuButton(){
		if (main.isSaved() || !main.isEditScene()){
			main.showHeirOverview();
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
				main.showHeirOverview();
			}

		}
	}

	@FXML
	public void onHelp(){
		//To be implemented
	}
}
