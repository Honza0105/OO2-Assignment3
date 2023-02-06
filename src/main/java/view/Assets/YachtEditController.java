package view.Assets;

import app.Main;
import domain.Yacht;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class YachtEditController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField valueField;

    @FXML
    private TextField rentPerWeekField;

    @FXML
    private TextField numberOfDecksField;

    @FXML
    private TextField speedField;

    @FXML
    private TextField hutsField;

    @FXML
    private TextField lengthField;

    private Main main;

    @FXML
    public void initilize(){
    }


    public void setMain(Main main) {
        this.main = main;
    }

    public void setYacht(Yacht asset) {
        nameField.setText(asset.getName());
        descriptionField.setText(asset.getDescription());
        valueField.setText(asset.getValue().toString());
        rentPerWeekField.setText(asset.getRentPerWeek().toString());
        numberOfDecksField.setText(String.valueOf(asset.getNumberOfDecks()));
        speedField.setText(String.valueOf(asset.getSpeed()));
        hutsField.setText(String.valueOf(asset.getHuts()));
        lengthField.setText(String.valueOf(asset.getLength()));
    }
}
