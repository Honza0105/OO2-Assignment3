package view.Heirs;

import app.Main;
import domain.Heir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.DateUtil;
import util.ProperFormats;

import java.math.BigDecimal;
import java.util.Optional;

public class HeirCreateController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField dateOfBirthField;

    @FXML
    private ComboBox<Heir.Gender> genderComboBox;

    @FXML
    private TextField netWorthField;

    @FXML
    private TextField incomeField;

    @FXML
    private Label savedLabel;

    private boolean pressedExit;

    private final ObservableList<Heir.Gender> genders = FXCollections.observableArrayList(Heir.Gender.MALE, Heir.Gender.FEMALE, Heir.Gender.OTHER);


    Main main;

    public void setMain(Main main){
        this.main = main;
        genderComboBox.setItems(genders);
    }

    @FXML
    public void exitCreateDialog(){
        if (main.isSaved()){
            main.showHeirOverview();
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
                saveCreateDialog();
                main.showHeirOverview();
            } else if (result.get() == buttonExitAnyways) {
                main.showHeirOverview();
            }
        }
    }

    @FXML
    public void saveCreateDialog(){
        if (isInputValid()){
            Heir newHeir = new Heir(
                    nameField.getText(),
                    addressField.getText(),
                    DateUtil.parse(dateOfBirthField.getText()),
                    genderComboBox.getValue(),
                    new BigDecimal(netWorthField.getText()),
                    new BigDecimal(incomeField.getText()));
            main.getHeirObservableList().add(newHeir);
            savedLabel.setVisible(true);
            main.setSaved(true);
        }
    }

    private boolean isInputValid() {
        String alertMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            alertMessage += "Not a valid name!\n";
        }
        if (addressField.getText() == null || addressField.getText().length() == 0) {
            alertMessage += "Not a valid address!\n";
        }
        if (!DateUtil.validDate(dateOfBirthField.getText())){
            alertMessage += "Not a valid date!\n" +
                    "It is seen as " + dateOfBirthField.getText();
        }

        if (!ProperFormats.positiveDecimalFormat(netWorthField.getText())){
            alertMessage += "Net worth can only contain positive decimals!\n";
        }
        if (!ProperFormats.positiveDecimalFormat(incomeField.getText())){
            alertMessage += "Income can only contain positive decimals!\n";
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
