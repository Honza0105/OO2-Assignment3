package view.Assets;

import app.Main;
import domain.Island;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField areaField;

    @FXML
    private ComboBox<Island.Climate> climateComboBox;

    private ObservableList<Island.Climate> climates = FXCollections.observableArrayList(Island.Climate.TROPICAL, Island.Climate.SUBTROPICAL, Island.Climate.TEMPERATE, Island.Climate.CONTINENTAL, Island.Climate.ARCTIC);

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
        climateComboBox.setItems(climates);
        climateComboBox.setValue(asset.getClimate());
    }
}
