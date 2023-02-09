package view.Assets;

import app.Main;
import domain.Island;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import util.ProperFormats;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Pattern;

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

    private boolean saved;

    private boolean pressedExit;

    private Island island;

    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize() {
        saved = false;
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
        this.island = asset;
    }

    @FXML
    public void saveEditDialog(){
        if (isInputValid()){
            island.setName(nameField.getText());
            island.setDescription(descriptionField.getText());
            island.setValue(new BigDecimal(valueField.getText()));
            island.setRentPerWeek(new BigDecimal(rentPerWeekField.getText()));
            island.setLongitude(Float.parseFloat(longitudeField.getText()));
            island.setLatitude(Float.parseFloat(latitudeField.getText()));
            island.setArea(Float.parseFloat(areaField.getText()));
            island.setClimate(climateComboBox.getValue());



            saved = true;
        }
    }

    private boolean isInputValid() {
        String alertMessage = ProperFormats.isSharedInputValid(nameField,descriptionField,valueField,rentPerWeekField);

        if (!ProperFormats.positiveDecimalFormat(longitudeField.getText())){
            alertMessage += "Longitude can only contain positive decimals!\n";
        }
        if (!ProperFormats.positiveDecimalFormat(latitudeField.getText())){
            alertMessage += "Latitude can only contain positive decimals!\n";
        }
        if (!ProperFormats.positiveDecimalFormat(areaField.getText())){
            alertMessage += "Area can only contain positive decimals!\n";
        }

        if (alertMessage.length()==0 || pressedExit){
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(main.getStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(alertMessage);
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void exitEditDialog(){
        if (saved){
            main.showAssetOverview();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("File not saved");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("If not saved, all changes will be lost.");
            ButtonType buttonSaveBeforeExit = new ButtonType("Save before exit");
            ButtonType buttonExitAnyways = new ButtonType("Exit anyways");

            alert.getButtonTypes().setAll(buttonSaveBeforeExit, buttonExitAnyways);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonSaveBeforeExit) {
                pressedExit = true;
                saveEditDialog();
                main.showAssetOverview();
            } else if (result.get() == buttonExitAnyways) {
                main.showAssetOverview();
            }

        }
    }
}
