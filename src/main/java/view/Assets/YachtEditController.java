package view.Assets;

import app.Main;
import domain.Yacht;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import util.ProperFormats;

import java.math.BigDecimal;
import java.util.Optional;

public class YachtEditController {

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

    private Main main;

    private boolean saved;

    private boolean pressedExit;

    private Yacht yacht;

    public void initialize() {
        saved = false;
    }


    public void setMain(Main main) {
        this.main = main;
    }

    public void setYacht(Yacht asset) {
        nameField.setText(asset.getName());
        descriptionField.setText(asset.getDescription());
        valueField.setText(asset.getValue().toString());
        rentPerWeekField.setText(asset.getRentPerWeek().toString());
        numberOfDecksField.setText(String.valueOf(asset.getNumberOfDecks()));
        speedField.setText(String.valueOf(asset.getSpeed()));
        hutsField.setText(String.valueOf(asset.getHuts()));
        lengthField.setText(String.valueOf(asset.getLength()));

        this.yacht = asset;
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

    @FXML
    public void saveEditDialog(){
        if (isInputValid()){
            yacht.setName(nameField.getText());
            yacht.setDescription(descriptionField.getText());
            yacht.setValue(new BigDecimal(valueField.getText()));
            yacht.setRentPerWeek(new BigDecimal(rentPerWeekField.getText()));
            yacht.setNumberOfDecks(Integer.parseInt(numberOfDecksField.getText()));
            yacht.setSpeed(Double.parseDouble(speedField.getText()));
            yacht.setHuts(Integer.parseInt(hutsField.getText()));
            yacht.setLength(Double.parseDouble(lengthField.getText()));

            saved = true;
        }
    }
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
