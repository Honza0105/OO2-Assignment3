package view.Assets;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MansionEditController {
    @FXML
    private Label nameField;

    @FXML
    private Label descriptionField;

    @FXML
    private Label valueField;

    @FXML
    private Label rentPerWeekField;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }
}
