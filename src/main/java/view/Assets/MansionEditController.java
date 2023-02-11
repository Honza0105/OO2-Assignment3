package view.Assets;

import app.Main;
import domain.Heir;
import domain.Island;
import domain.Mansion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.ProperFormats;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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

    @FXML
    private Label savedLabel;

    @FXML
    private Label rentedLabel;

    @FXML
    private ComboBox<Island> islandComboBox;

    @FXML
    private CheckBox removeIslandCheckBox;

    @FXML
    private DatePicker dateFromDatePicker;

    @FXML
    private DatePicker dateTillDatePicker;

    @FXML
    private ComboBox<Heir> heirComboBox;

    private Main main;


    private boolean pressedExit;

    private Mansion mansion;

    public void setMain(Main main) {
        this.main = main;
        main.setSaved(false);
        main.setEditScene(true);
    }
    public void initialize() {

    }

    public void setMansion(Mansion asset) {
        nameField.setText(asset.getName());
        descriptionField.setText(asset.getDescription());
        valueField.setText(asset.getValue().toString());
        rentPerWeekField.setText(String.valueOf(asset.getRentPerWeek()));
        addressField.setText(asset.getAddress());
        islandComboBox.setItems(main.getIslandObservableList());
        islandComboBox.setValue(asset.getHomeIsland());
        heirComboBox.setItems(main.getHeirObservableListWithoutOwner(main.getAssetHeirHashMap().get(asset)));


        this.mansion = asset;
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

    @FXML
    public void saveEditDialog(){
        if (isInputValid()){
            mansion.setName(nameField.getText());
            mansion.setDescription(descriptionField.getText());
            mansion.setValue(new BigDecimal(valueField.getText()));
            mansion.setRentPerWeek(new BigDecimal(rentPerWeekField.getText()));
            mansion.setAddress(addressField.getText());

            savedLabel.setVisible(true);

            if (removeIslandCheckBox.isSelected()){
                removeFromIsland();
            }
            else{
                mansion.setHomeIsland(islandComboBox.getValue());
            }


            main.setSaved(true);
        }
    }

    private boolean isInputValid() {
        String alertMessage = ProperFormats.isSharedInputValid(nameField,descriptionField,valueField,rentPerWeekField);


        if (addressField.getText() == null || addressField.getText().length() == 0) {
            alertMessage += "No valid address!";
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
    public void removeFromIsland(){
        mansion.setHomeIsland(null);
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
            main.startRent(mansion,dateFromDatePicker.getValue(),dateTillDatePicker.getValue(),heirComboBox.getValue());
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
