package view.Statistics;

import app.Main;
import domain.Heir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class AssetDistributionController {

    @FXML
    private PieChart pieChart;

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    private Main main;

    public void setMain(Main main) {
        this.main = main;

        for (Heir heir : main.getHeirObservableList()) {
            pieChartData.add(new PieChart.Data(heir.getName(), heir.totalValue().doubleValue()));
        }

        pieChart.setData(pieChartData);

    }
}
