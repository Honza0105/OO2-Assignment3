package view.Statistics;

import app.Main;
import domain.Heir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.math.BigDecimal;

public class AssetDistributionController {


    @FXML
    private PieChart pieChart;

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    private Main main;

    public void setMain(Main main) {
        this.main = main;

        ObservableList<Heir> heirObservableList = main.getHeirObservableList();

        int n = heirObservableList.size();
        int k = 9;
        if (n > k) {
            BigDecimal otherHeirsValue = new BigDecimal(0);
            for (int i = k; i < n; i++) {
                Heir heir = heirObservableList.get(i);
                otherHeirsValue = otherHeirsValue.add(heir.totalValue());
            }
            for (int i = 0; i < k; i++) {
                Heir heir = heirObservableList.get(i);
                pieChartData.add(new PieChart.Data(heir.getName(), heir.totalValue().doubleValue()));
            }
            pieChartData.add(new PieChart.Data("Other Heirs", otherHeirsValue.doubleValue()));
        } else {
            for (Heir heir : heirObservableList) {
                pieChartData.add(new PieChart.Data(heir.getName(), heir.totalValue().doubleValue()));
            }
        }

        pieChart.setData(pieChartData);
    }
}
