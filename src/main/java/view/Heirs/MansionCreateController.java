package view.Heirs;

import app.Main;
import domain.Heir;
import domain.Island;
import domain.Mansion;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MansionCreateController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField valueField;

    @FXML
    private TextField rentPerWeekField;

    @FXML
    private TextField addressField;

    @FXML
    private Label savedLabel;

    @FXML
    private ComboBox<Island> islandComboBox;

    @FXML
    private CheckBox removeIslandCheckBox;

    private Main main;


    private boolean pressedExit;


    private Heir heir;

    /**
     *
     * @param heir The heir from which we've got to this window and which owns this asset
     */
    public void setMansion(Heir heir){
        islandComboBox.setItems(main.getIslandObservableList());
        this.heir = heir;
    }

    /**
     * sets booleans
     * @param main Main class
     */
    public void setMain(Main main) {
        this.main = main;
        main.setSaved(false);
        main.setEditScene(true);
    }

}