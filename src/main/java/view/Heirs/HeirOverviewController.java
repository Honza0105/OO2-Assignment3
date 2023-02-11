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

public class HeirOverviewController {

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
        heirTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                //brings up a detail screen with all properties. The user can edit these properties and save them here.
                Heir selectedHeir = heirTableView.getSelectionModel().getSelectedItem();
//                System.out.println("Selected Heir: " + selectedHeir.getName());
                main.showHeirEdit(selectedHeir);

            }
        });
    }
    private void showHeirDetails(Heir heir){
        if (heir != null){
            nameLabel.setText(heir.getName());
            addressLabel.setText(heir.getAddress());
            dateOfBirthLabel.setText(DateUtil.format(heir.getDateOfBirth()));
            genderLabel.setText(heir.getGender().toString());
            BigDecimal netWorth = heir.getNetWorth();
            String netWorthFormatted = ProperFormats.bigDecimalProperFormat(netWorth);
            netWorthLabel.setText(netWorthFormatted);
            BigDecimal income = heir.getIncome();
            String incomeFormatted = ProperFormats.bigDecimalProperFormat(income);
            incomeLabel.setText(incomeFormatted);
            ObservableList<Asset> assets = FXCollections.observableArrayList(heir.getAssets());
            assetsListView.setItems(assets);

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

    @FXML
    public void deleteHeir(){
        if (heirTableView.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setHeaderText("No heir selected");
            alert.setContentText("Please select an heir you want to delete.");
            alert.showAndWait();
        }
        else {
            for (Asset asset : heirTableView.getSelectionModel().getSelectedItem().getAssets()
            ) {
                asset.removeFromHomeIsland();
                main.getAssetObservableList().remove(asset);
            }
            main.getHeirObservableList().remove(heirTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void createHeir(){
        main.createHeir();
    }

    public void setMain(Main main) {
        this.main = main;


        heirTableView.setItems(main.getHeirObservableList());
    }
}
