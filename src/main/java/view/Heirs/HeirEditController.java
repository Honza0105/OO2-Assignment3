package view.Heirs;

import app.Main;
import domain.Asset;
import domain.Heir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.DateUtil;
import util.ProperFormats;

import java.math.BigDecimal;
import java.util.Optional;

public class HeirEditController {
    @FXML
    private TextField nameField;

    @FXML
    private DatePicker dateOfBirthDatePicker;

    @FXML
    private TextField netWorthField;

    @FXML
    private ListView<Asset> assetListView;


    @FXML
    private TextField addressField;

    @FXML
    private ComboBox<Heir.Gender> genderComboBox;

    @FXML
    private TextField incomeField;

    @FXML
    private Label savedLabel;

    @FXML
    private CheckBox removeSelectedAssetCheckBox;

    @FXML
    private ComboBox<String> assetsComboBox;

    private ObservableList<Heir.Gender> genders = FXCollections.observableArrayList(Heir.Gender.MALE, Heir.Gender.FEMALE, Heir.Gender.OTHER);

    private ObservableList<String> assetsObservableList = FXCollections.observableArrayList("Yacht","Plane","Mansion","Island");

    private Main main;

    private boolean pressedExit;

    private Heir heir;


    public void setMain(Main main) {
        this.main = main;
        main.setSaved(false);
        main.setEditScene(true);
    }

    public void setHeir(Heir heir) {
        nameField.setText(heir.getName());
        dateOfBirthDatePicker.setValue(heir.getDateOfBirth());
        netWorthField.setText(heir.getNetWorth().toString());
        addressField.setText(heir.getAddress());
        incomeField.setText(heir.getIncome().toString());
        genderComboBox.setItems(genders);
        genderComboBox.setValue(heir.getGender());
        ObservableList<Asset> assets = FXCollections.observableArrayList(heir.getAssets());
        assetListView.setItems(assets);
        this.heir = heir;
        assetsComboBox.setItems(assetsObservableList);
    }

    private boolean isInputValid() {
        String alertMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            alertMessage += "Not a valid name!\n";
        }
        if (addressField.getText() == null || addressField.getText().length() == 0) {
            alertMessage += "Not a valid address!\n";
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

    @FXML
    public void saveEditDialog(){
        if (isInputValid()){
            heir.setName(nameField.getText());
            heir.setAddress(addressField.getText());
            heir.setDateOfBirth(dateOfBirthDatePicker.getValue());
            heir.setGender(genderComboBox.getValue());
            heir.setNetWorth(new BigDecimal(netWorthField.getText()));
            heir.setIncome(new BigDecimal(incomeField.getText()));
            if (removeSelectedAssetCheckBox.isSelected()){
                Asset selectedAsset = assetListView.getSelectionModel().getSelectedItem();
                heir.removeAsset(selectedAsset);
            }




            savedLabel.setVisible(true);


            main.setSaved(true);
        }
    }

    @FXML
    public void exitEditDialog(){
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
                saveEditDialog();
                main.showHeirOverview();
            } else if (result.get() == buttonExitAnyways) {
                main.showHeirOverview();
            }

        }
    }

    public void handleCreateNewAsset(){
        System.out.println(assetsComboBox.getValue());
        main.showAssetCreate(assetsComboBox.getValue(),heir);


    }




}
