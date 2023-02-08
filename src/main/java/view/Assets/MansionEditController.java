package view.Assets;

import app.Main;
import domain.Mansion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MansionEditController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField valueField;

    @FXML
    private TextField rentPerWeekField;

    @FXML
    private TextField addressField;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMansion(Mansion asset) {
        nameField.setText(asset.getName());
        descriptionField.setText(asset.getDescription());
        valueField.setText(asset.getValue().toString());
        rentPerWeekField.setText(String.valueOf(asset.getRentPerWeek()));
        addressField.setText(asset.getAddress());
    }
}
