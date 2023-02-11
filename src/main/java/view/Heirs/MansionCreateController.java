package view.Heirs;

import app.Main;
import domain.Heir;
import domain.Island;
import domain.Mansion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.ProperFormats;

import java.math.BigDecimal;
import java.util.Optional;

public class MansionCreateController {
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
    private ComboBox<Island> islandComboBox;

    @FXML
    private CheckBox removeIslandCheckBox;

    private Main main;


    private boolean pressedExit;


    private Heir heir;

    /**
     *
     * @param heir The heir from which we've got to this window and which owns this asset
     */
    public void setMansion(Heir heir){
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
    public void exitCreateDialog(){
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
                saveEditDialog();
                main.showHeirEdit(heir);
            } else if (result.get() == buttonExitAnyways) {
                main.showHeirEdit(heir);
            } else if (result.get() == buttonCancel) {
                //do nothing
            }

        }
    }

    @FXML
    public void saveEditDialog(){
        if (isInputValid()){
            Mansion newMansion;
            if (rentPerWeekField.getText().isEmpty()) {
                newMansion = new Mansion(nameField.getText(),
                        descriptionField.getText(),
                        addressField.getText(),
                        new BigDecimal(valueField.getText()));

            } else {
                newMansion = new Mansion(nameField.getText(),
                        descriptionField.getText(),
                        addressField.getText(),
                        new BigDecimal(valueField.getText()));
                        new BigDecimal(rentPerWeekField.getText());
            }
            main.getAssetObservableList().add(newMansion);
            heir.addAsset(newMansion);
            main.getAssetHeirHashMap().put(newMansion,heir);
            if (islandComboBox.getValue() != null) {
                newMansion.setHomeIsland(islandComboBox.getValue());
            }
            savedLabel.setVisible(true);
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

}
