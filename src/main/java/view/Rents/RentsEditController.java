package view.Rents;

import app.Main;
import domain.Asset;
import domain.Rent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class RentsEditController {
    @FXML
    private Label occupantLabel;

    @FXML
    private Label assetLabel;

    @FXML
    private DatePicker startDateDatePicker;

    @FXML
    private DatePicker endDateDatePicker;

    @FXML
    private TextField rentField;

    @FXML
    private DatePicker paymentDateDatePicker;

    @FXML
    private Label savedLabel;


    private Main main;

    private boolean pressedExit;

    private Rent rent;

    public void setMain(Main main) {
        this.main = main;
        main.setSaved(false);
        main.setEditScene(true);
    }

    public void setRent(Rent rent) {
        occupantLabel.setText(rent.getOccupant().getName());
        assetLabel.setText(rent.getAsset().getName());
        startDateDatePicker.setValue(rent.getStart());
        endDateDatePicker.setValue(rent.getEnd());
        rentField.setText(rent.getRent().toString());
        paymentDateDatePicker.setValue(rent.getDateOfPayment());

        this.rent = rent;
    }

    @FXML
    public void saveEditDialog(){
        if (isInputValid()){
            rent.setStart(startDateDatePicker.getValue());
            rent.setEnd(endDateDatePicker.getValue());
            rent.setRent(new BigDecimal(rentField.getText()));
            rent.setDateOfPayment(paymentDateDatePicker.getValue());




            savedLabel.setVisible(true);


            main.setSaved(true);
        }
    }

    @FXML
    public void exitEditDialog(){
        if (main.isSaved()){
            main.showRentOverview();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("File not saved");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("If not saved, all changes will be lost.");
            ButtonType buttonSaveBeforeExit = new ButtonType("Save before exit");
            ButtonType buttonExitAnyways = new ButtonType("Exit anyways");
            ButtonType buttonCancel = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(buttonSaveBeforeExit, buttonExitAnyways, buttonCancel);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonSaveBeforeExit) {
                pressedExit = true;
                saveEditDialog();
                main.showRentOverview();
            } else if (result.get() == buttonExitAnyways) {
                main.showRentOverview();
            } else if (result.get() == buttonCancel) {
                //do nothing
            }

        }
    }

    private boolean isInputValid() {
        String alertMessage = "";
        if (startDateDatePicker.getValue().isAfter(endDateDatePicker.getValue())){
            alertMessage +=  "Start date must be before end date.\n";
        }
        if (startDateDatePicker.getValue().isBefore(LocalDate.now())){
            alertMessage += "Start date must not be before today.\n";}
        if (paymentDateDatePicker.getValue().isBefore(LocalDate.now())){
            alertMessage += "Payment date cannot be in the past.\n";}

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

}
