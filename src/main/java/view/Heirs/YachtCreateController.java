package view.Heirs;

import app.Main;
import domain.Heir;
import domain.Island;
import domain.Yacht;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.ProperFormats;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * This class handles the FXML window in which Yachts can be created
 */
public class YachtCreateController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField valueField;

    @FXML
    private TextField rentPerWeekField;

    @FXML
    private TextField numberOfDecksField;

    @FXML
    private TextField speedField;

    @FXML
    private TextField hutsField;

    @FXML
    private TextField lengthField;

    @FXML
    private Label savedLabel;

    @FXML
    private ComboBox<Island> islandComboBox;

    private Main main;

    private boolean saved;

    private boolean pressedExit;

    private Yacht yacht;

    private Heir heir;

    public void initialize() {
    }

    /**
     *
     * @param heir The heir from which we've got to this window and which owns this asset
     */
    public void setYacht(Heir heir){
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
                main.showHeirEdit(heir);
            } else if (result.get() == buttonExitAnyways) {
                main.showHeirEdit(heir);
            }

        }
    }

    @FXML
    public void saveEditDialog(){
        if (isInputValid()) {
            Yacht newYacht;
            if (rentPerWeekField.getText().isEmpty()) {
                newYacht = new Yacht(nameField.getText(),
                        descriptionField.getText(),
                        new BigDecimal(valueField.getText()),
                        Double.parseDouble(lengthField.getText()),
                        Integer.parseInt(numberOfDecksField.getText()),
                        Double.parseDouble(speedField.getText()),
                        Integer.parseInt(hutsField.getText()));
            } else {
                newYacht = new Yacht(nameField.getText(),
                        descriptionField.getText(),
                        new BigDecimal(valueField.getText()),
                        new BigDecimal(rentPerWeekField.getText()),
                        Double.parseDouble(lengthField.getText()),
                        Integer.parseInt(numberOfDecksField.getText()),
                        Double.parseDouble(speedField.getText()),
                        Integer.parseInt(hutsField.getText()));
            }
            main.getAssetObservableList().add(newYacht);
            heir.addAsset(newYacht);
            newYacht.setHomeIsland(islandComboBox.getValue());
        }



            main.setSaved(true);
        }

    /**
     * Checks input
     * @return True if input is valid
     */
    private boolean isInputValid() {
        String alertMessage = ProperFormats.isSharedInputValid(nameField,descriptionField,valueField,rentPerWeekField);

        if (!ProperFormats.positiveIntegerFormat(numberOfDecksField.getText())) {
            alertMessage += "Number of Decks can only be positive integer!\n";
        }

        if (!ProperFormats.positiveDoubleFormat(speedField.getText())){
            alertMessage += "Speed can only be positive integer!";
        }

        if (!ProperFormats.positiveIntegerFormat(hutsField.getText())){
            alertMessage += "Huts can only be positive integer!";
        }
        if (!ProperFormats.positiveDoubleFormat(lengthField.getText())){
            alertMessage += "Length can only be positive integer!";
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
