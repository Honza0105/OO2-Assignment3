package view.persons;

import domain.Asset;
import domain.Heir;
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
}
