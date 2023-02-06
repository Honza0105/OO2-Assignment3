package view.Assets;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class YachtEditController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label valueLabel;

    @FXML
    private Label rentPerWeekLabel;

    @FXML
    private Label numberOfDecksLabel;

    @FXML
    private Label speedLabel;

    @FXML
    private Label hutsLabel;

    @FXML
    private Label lengthLabel;

    private Main main;

    @FXML
    public void initilize(){
    }


    public void setMain(Main main) {
        this.main = main;
    }
}
