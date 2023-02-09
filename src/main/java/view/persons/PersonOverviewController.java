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


//        showAssetDetails(null);

//        assetTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> showAssetDetails(newValue));
//        assetTable.setOnMouseClicked(event -> {
//            if (event.getClickCount() == 2){
//                brings up a detail screen with all properties. The user can edit these properties and save them here.
//                Asset selectedAsset = assetTable.getSelectionModel().getSelectedItem();
//                System.out.println("Selected Asset: " + selectedAsset.getName());
//                main.showAssetEdit(selectedAsset);
//                System.out.println("huh?");
//            }
//        });
    }

    public void setMain(Main main) {
        this.main = main;


        heirTableView.setItems(main.getHeirObservableList());
    }
}
