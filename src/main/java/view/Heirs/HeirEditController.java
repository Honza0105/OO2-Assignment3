package view.Heirs;

import app.Main;
import domain.Asset;
import domain.Heir;
import domain.Island;
import domain.Yacht;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;
import util.DateUtil;

public class HeirEditController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField dateOfBirthField;

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

    private ObservableList<Heir.Gender> genders = FXCollections.observableArrayList(Heir.Gender.MALE, Heir.Gender.FEMALE, Heir.Gender.OTHER);


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
        dateOfBirthField.setText(DateUtil.format(heir.getDateOfBirth()));
        netWorthField.setText(heir.getNetWorth().toString());
        addressField.setText(heir.getAddress());
        incomeField.setText(heir.getIncome().toString());
        genderComboBox.setItems(genders);
        genderComboBox.setValue(heir.getGender());
        ObservableList<Asset> assets = FXCollections.observableArrayList(heir.getAssets());
        assetListView.setItems(assets);
        this.heir = heir;
    }



}
