package view.Assets;

import app.Main;
import domain.Plane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PlaneEditController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField valueField;

    @FXML
    private TextField rentPerWeekField;

    @FXML
    private TextField maxAltitudeField;

    @FXML
    private TextField numberOfPassengersField;

    @FXML
    public void initilize(){
    }

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setPlane(Plane asset) {
        nameField.setText(asset.getName());
        descriptionField.setText(asset.getDescription());
        valueField.setText(asset.getValue().toString());
        rentPerWeekField.setText(asset.getRentPerWeek().toString());
        maxAltitudeField.setText(String.valueOf(asset.getMaxAltitude()));
        numberOfPassengersField.setText(String.valueOf(asset.getNumberOfPassengers()));
    }
}
