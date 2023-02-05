package view;

import app.Main;
import domain.Asset;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import util.ProperFormats;

import java.math.BigDecimal;
import java.text.Format;

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

    }

    private void showAssetDetails(Asset asset){
        if (asset != null){
            nameLabel.setText(asset.getName());
            descriptionLabel.setText(asset.getDescription());
            BigDecimal value = asset.getValue();
            String valueFormatted = ProperFormats.bigDecimalProperFormat(value);
            valueLabel.setText(valueFormatted);
            typeLabel.setText(asset.getClass().getSimpleName());
            rentPerWeekLabel.setText(asset.getRentPerWeek().toString()+ "x");
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
