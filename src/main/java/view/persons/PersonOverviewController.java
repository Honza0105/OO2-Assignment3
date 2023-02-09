package view.persons;

import app.Main;
import domain.Asset;
import domain.Heir;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import util.DateUtil;
import util.ProperFormats;

import java.math.BigDecimal;

public class PersonOverviewController {

    @FXML
    private TableView<Heir> heirTableView;

    @FXML
    private TableColumn<Heir,String> nameColumn;

    @FXML
    private Label nameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label dateOfBirthLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label netWorthLabel;

    @FXML
    private Label incomeLabel;

    @FXML
    private ListView<Asset> assetsListView;

    private Main main;

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());


        showHeirDetails(null);

        heirTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> showHeirDetails(newValue));
//        heirTableView.setOnMouseClicked(event -> {
//            if (event.getClickCount() == 2){
//                brings up a detail screen with all properties. The user can edit these properties and save them here.
//                Heir selectedHeir = heirTableView.getSelectionModel().getSelectedItem();
//                System.out.println("Selected Asset: " + selectedAsset.getName());
//                main.showAssetEdit(selectedAsset);
//                System.out.println("huh?");
//            }
//        });
    }
    private void showHeirDetails(Heir heir){
        if (heir != null){
            nameLabel.setText(heir.getName());
            addressLabel.setText(heir.getAddress());
            dateOfBirthLabel.setText(DateUtil.format(heir.getDateOfBirth()));
            genderLabel.setText(heir.getGender().toString());
            netWorthLabel.setText(heir.getNetWorth().toString());
            incomeLabel.setText(heir.getIncome().toString());
        }
        else {
            nameLabel.setText("");
            addressLabel.setText("");
            dateOfBirthLabel.setText("");
            genderLabel.setText("");
            netWorthLabel.setText("");
            incomeLabel.setText("");
        }
    }

    public void setMain(Main main) {
        this.main = main;


        heirTableView.setItems(main.getHeirObservableList());
    }
}
