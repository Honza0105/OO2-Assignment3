package view.Heirs;

import app.Main;
import domain.Asset;
import domain.Heir;
import domain.Island;
import domain.Yacht;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.ProperFormats;

import java.math.BigDecimal;
import java.util.Optional;

public class IslandCreateController {
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

    private ObservableList<Island.Climate> climates = FXCollections.observableArrayList(Island.Climate.TROPICAL, Island.Climate.SUBTROPICAL, Island.Climate.TEMPERATE, Island.Climate.CONTINENTAL, Island.Climate.ARCTIC);

    private Main main;
    private Heir heir;

    private boolean pressedExit;


    /**
     *
     * @param heir The heir from which we've got to this window and which owns this asset
     */
    public void setIsland(Heir heir){
        this.heir = heir;
    }

    /**
     * sets booleans
     * @param main Main class
     */
    public void setMain(Main main) {
        this.main = main;
        main.setSaved(false);
        main.setEditScene(true);
    }

    @FXML
    public void exitEditDialog(){
        if (main.isSaved()){
            main.showHeirEdit(heir);
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
                main.showHeirEdit(heir);
            } else if (result.get() == buttonExitAnyways) {
                main.showHeirEdit(heir);
            }

        }
    }

    @FXML
    public void saveEditDialog(){
        if (isInputValid()) {
            Island newIsland;
            if (rentPerWeekField.getText().isEmpty()) {
                newIsland = new Island(nameField.getText(),
                        descriptionField.getText(),
                        new BigDecimal(valueField.getText()),
                        Float.parseFloat(areaField.getText()),
                        Float.parseFloat(latitudeField.getText()),
                        Float.parseFloat(longitudeField.getText()),
                        climateComboBox.getValue());
                } else {
                newIsland = new Island(nameField.getText(),
                        descriptionField.getText(),
                        new BigDecimal(valueField.getText()),
                        new BigDecimal(rentPerWeekField.getText()),
                        Float.parseFloat(areaField.getText()),
                        Float.parseFloat(latitudeField.getText()),
                        Float.parseFloat(longitudeField.getText()),
                        climateComboBox.getValue());
            }
            main.getAssetObservableList().add(newIsland);
            heir.addAsset(newIsland);
        }



        main.setSaved(true);
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
}
