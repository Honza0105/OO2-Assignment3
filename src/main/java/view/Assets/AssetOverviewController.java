package view.Assets;

import app.Main;
import domain.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import util.ProperFormats;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AssetOverviewController {

    @FXML
    private TableView<Asset> assetTable;

    @FXML
    private TableColumn<Asset,String> nameColumn;

    @FXML
    private TableColumn<Asset,String> typeColumn;

    @FXML
    private Label nameLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label valueLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label rentPerWeekLabel;

    private Main main;


    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));

        showAssetDetails(null);

        assetTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> showAssetDetails(newValue));
        assetTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                //brings up a detail screen with all properties. The user can edit these properties and save them here.
                Asset selectedAsset = assetTable.getSelectionModel().getSelectedItem();
                System.out.println("Selected Asset: " + selectedAsset.getName());
                main.showAssetEdit(selectedAsset);
                System.out.println("huh?");
            }
        });
    }




    private void showAssetDetails(Asset asset){
        if (asset != null){
            nameLabel.setText(asset.getName());
            descriptionLabel.setText(asset.getDescription());
            BigDecimal value = asset.getValue();
            String valueFormatted = ProperFormats.bigDecimalProperFormat(value);
            valueLabel.setText(valueFormatted);
            typeLabel.setText(asset.getClass().getSimpleName());
            BigDecimal rentPerWeek = asset.getRentPerWeek();
            String rentFormatted = ProperFormats.bigDecimalProperFormat(rentPerWeek);
            rentPerWeekLabel.setText(rentFormatted);
        }
        else {
            nameLabel.setText("");
            descriptionLabel.setText("");
            valueLabel.setText("");
            typeLabel.setText("");
            rentPerWeekLabel.setText("");
        }
    }

    public void setMain(Main main) {
        this.main = main;

        assetTable.setItems(main.getAssetObservableList());
    }
}
