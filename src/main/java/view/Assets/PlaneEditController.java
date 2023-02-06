package view.Assets;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PlaneEditController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label valueLabel;

    @FXML
    private Label rentPerWeekLabel;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }
}
