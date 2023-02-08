package view.Assets;

import app.Main;
import domain.Island;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.CookieHandler;

public class IslandEditController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField valueField;

    @FXML
    private TextField rentPerWeekField;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField areaField;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }


    public void setIsland(Island asset) {
        //develop
        nameField.setText(asset.getName());
        descriptionField.setText(asset.getDescription());
        valueField.setText(asset.getValue().toString());
        rentPerWeekField.setText(asset.getRentPerWeek().toString());
        longitudeField.setText(String.valueOf(asset.getLongitude()));
        latitudeField.setText(String.valueOf(asset.getLatitude()));
        areaField.setText(String.valueOf(asset.getArea()));
    }
}
