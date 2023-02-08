package view.Assets;

import app.Main;
import domain.Island;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class IslandEditController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField valueField;

    @FXML
    private TextField rentPerWeekField;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }


    public void setIsland(Island asset) {
        //develop
        System.out.println("To be developed");
    }
}
