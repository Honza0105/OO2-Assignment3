package view.Heirs;

import app.Main;
import domain.Asset;
import domain.Heir;
import domain.Island;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class IslandCreateController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField valueField;

    @FXML
    private TextField rentPerWeekField;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField areaField;

    @FXML
    private ComboBox<Island.Climate> climateComboBox;

    @FXML
    private ListView<Asset> assetsListView;

    @FXML
    private Label savedLabel;

    private ObservableList<Island.Climate> climates = FXCollections.observableArrayList(Island.Climate.TROPICAL, Island.Climate.SUBTROPICAL, Island.Climate.TEMPERATE, Island.Climate.CONTINENTAL, Island.Climate.ARCTIC);

    private Main main;
    private Heir heir;


    public void setIsland(Heir heir){
        this.heir = heir;
    }
}
