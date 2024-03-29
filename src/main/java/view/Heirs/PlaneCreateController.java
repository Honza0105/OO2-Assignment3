package view.Heirs;

import app.Main;
import domain.Heir;
import domain.Island;
import domain.Plane;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.ProperFormats;

import java.math.BigDecimal;
import java.util.Optional;

public class PlaneCreateController {
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
    private Label savedLabel;

    @FXML
    private ComboBox<Island> islandComboBox;

    @FXML
    private CheckBox removeIslandCheckBox;

    private boolean pressedExit;




    private Main main;
    private Heir heir;


    /**
     *
     * @param heir The heir from which we've got to this window and which owns this asset
     */
    public void setPlane(Heir heir){
        islandComboBox.setItems(main.getIslandObservableList());
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
            ButtonType buttonCancel = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(buttonSaveBeforeExit, buttonExitAnyways, buttonCancel);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonSaveBeforeExit) {
                pressedExit = true;
                saveCreateDialog();
                main.showHeirEdit(heir);
            } else if (result.get() == buttonExitAnyways) {
                main.showHeirEdit(heir);
            } else if (result.get() == buttonCancel) {
                //do nothing
            }

        }
    }

    @FXML
    public void saveCreateDialog(){
        if (isInputValid()){
            Plane newPlane;
            if (rentPerWeekField.getText().isEmpty()) {
                newPlane = new Plane(nameField.getText(),
                        descriptionField.getText(),
                        new BigDecimal(valueField.getText()),
                        Integer.parseInt(maxAltitudeField.getText()),
                        Integer.parseInt(numberOfPassengersField.getText()));
            } else {
                newPlane = new Plane(nameField.getText(),
                        descriptionField.getText(),
                        new BigDecimal(valueField.getText()),
                        new BigDecimal(rentPerWeekField.getText()),
                        Integer.parseInt(maxAltitudeField.getText()),
                        Integer.parseInt(numberOfPassengersField.getText()));
            }
            main.getAssetObservableList().add(newPlane);
            heir.addAsset(newPlane);
            main.getAssetHeirHashMap().put(newPlane,heir);
            if (islandComboBox.getValue() != null) {
                newPlane.setHomeIsland(islandComboBox.getValue());
            }
            savedLabel.setVisible(true);
            main.setSaved(true);

        }
    }

    private boolean isInputValid() {
        String alertMessage = ProperFormats.isSharedInputValid(nameField,descriptionField,valueField,rentPerWeekField);

        if (!ProperFormats.positiveIntegerFormat(maxAltitudeField.getText())) {
            alertMessage += "Maximal altitude can only be positive integer!\n";
        }

        if (!ProperFormats.positiveIntegerFormat(numberOfPassengersField.getText())){
            alertMessage += "Number of passengers can only be positive integer!";
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
