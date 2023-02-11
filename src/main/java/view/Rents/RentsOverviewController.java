package view.Rents;

import app.Main;
import domain.Rent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RentsOverviewController {
    @FXML
    private TableView<Rent> rentTableView;

    @FXML
    private TableColumn<Rent,String> nameColumn;

    @FXML
    private TableColumn<Rent,String> assetColumn;

    @FXML
    private Label occupantLabel;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    @FXML
    private Label rentLabel;

    @FXML
    private Label paymentDateLabel;

    @FXML
    private Label assetLabel;

    private Main main;

    public void setMain(Main main) {
        this.main = main;

        rentTableView.setItems(main.getRentObservableList());
    }

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getOccupant().nameProperty());
        assetColumn.setCellValueFactory(cellData -> cellData.getValue().getAsset().nameProperty());


        showRentDetails(null);

        rentTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> showRentDetails(newValue));
        rentTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                Rent selectedRent = rentTableView.getSelectionModel().getSelectedItem();
                System.out.println(selectedRent);
                main.showRentEdit(selectedRent);

            }
        });
    }

    public void showRentDetails(Rent rent){
        if (rent != null){
            occupantLabel.setText(rent.getOccupant().getName());
            startLabel.setText(rent.getStart().toString());
            endLabel.setText(rent.getEnd().toString());
            rentLabel.setText(rent.getRent().toString());
            paymentDateLabel.setText(rent.getDateOfPayment().toString());
            assetLabel.setText(rent.getAsset().toString());

        }
        else {
            occupantLabel.setText("");
            startLabel.setText("");
            endLabel.setText("");
            rentLabel.setText("");
            paymentDateLabel.setText("");
            assetLabel.setText("");
        }
    }

    public void onDelete(){
        if (rentTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setHeaderText("No rent selected");
            alert.setContentText("Please select rent you want to delete.");
            alert.showAndWait();
        }
        else{
            main.getRentObservableList().remove(rentTableView.getSelectionModel().getSelectedItem());
        }

    }


}
