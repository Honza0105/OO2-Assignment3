package view.Assets;

import app.Main;
import domain.Asset;
import domain.Heir;
import domain.Island;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.ProperFormats;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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

    @FXML
    private ListView<Asset> assetsListView;

    @FXML
    private Label savedLabel;

    @FXML
    private Label rentedLabel;

    @FXML
    private DatePicker dateFromDatePicker;

    @FXML
    private DatePicker dateTillDatePicker;

    @FXML
    private ComboBox<Heir> heirComboBox;

    private ObservableList<Island.Climate> climates = FXCollections.observableArrayList(Island.Climate.TROPICAL, Island.Climate.SUBTROPICAL, Island.Climate.TEMPERATE, Island.Climate.CONTINENTAL, Island.Climate.ARCTIC);

    private Main main;


    private boolean pressedExit;

    private Island island;

    public void setMain(Main main) {
        this.main = main;
        main.setSaved(false);
        main.setEditScene(true);
    }

    public void initialize() {
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
        ObservableList<Asset> assets = FXCollections.observableArrayList(asset.getAssets());
        assetsListView.setItems(assets);
        heirComboBox.setItems(main.getHeirObservableList());


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

            savedLabel.setVisible(true);


            main.setSaved(true);
        }
    }

    private boolean isInputValid() {
        String alertMessage = ProperFormats.isSharedInputValid(nameField,descriptionField,valueField,rentPerWeekField);

        if (!ProperFormats.decimalLongitudeFormat(longitudeField.getText())){
            alertMessage += "Longitude can only contain decimals between -180 and 180!\n";
        }
        if (!ProperFormats.decimalLatitudeFormat(latitudeField.getText())){
            alertMessage += "Latitude can only contain decimals between -90 and 90!\n";
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
        if (main.isSaved()){
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

    public void startRent() {
        String alertMessage = "";
        if (dateFromDatePicker.getValue().isAfter(dateTillDatePicker.getValue())){
            alertMessage +=  "Start date must be before end date.\n";
        }
        if (dateFromDatePicker.getValue().isBefore(LocalDate.now())){
            alertMessage += "Start date must not be before today.\n";
        }
        if (heirComboBox.getValue()==null) {
            alertMessage +="Please select the heir.\n";
        }
        if (alertMessage.length()==0 || pressedExit){
            rentedLabel.setVisible(true);
            main.startRent(island,dateFromDatePicker.getValue(),dateTillDatePicker.getValue(),heirComboBox.getValue());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(main.getStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(alertMessage);
            alert.showAndWait();
        }


    }
}
