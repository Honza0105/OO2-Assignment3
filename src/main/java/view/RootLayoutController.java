package view;

import app.Main;
import javafx.fxml.FXML;

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
}
