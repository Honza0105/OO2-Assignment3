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

        int topHeirs = 9;
        BigDecimal otherHeirsValue = BigDecimal.ZERO;

        ObservableList<Heir> heirObservableList = main.getHeirObservableList();
        heirObservableList.sort((o1, o2) -> o2.totalValue().compareTo(o1.totalValue()));

        for (int i = 0; i < heirObservableList.size(); i++) {
            Heir heir = heirObservableList.get(i);

            if (i < topHeirs) {
                pieChartData.add(new PieChart.Data(heir.getName(), heir.totalValue().doubleValue()));
            } else {
                otherHeirsValue = otherHeirsValue.add(heir.totalValue());
            }
        }

        if (otherHeirsValue.compareTo(BigDecimal.ZERO) > 0) {
            pieChartData.add(new PieChart.Data("Other Heirs", otherHeirsValue.doubleValue()));
        }

        pieChart.setData(pieChartData);
    }

}
