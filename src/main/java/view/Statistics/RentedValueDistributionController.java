package view.Statistics;

import domain.Rent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RentedValueDistributionController {
    @FXML
    private ScatterChart<Number, Number> scatterChart;

    private XYChart.Series<Number, Number> scatterChartData = new XYChart.Series<>();

//    private NumberAxis xAxis;
//
//    private NumberAxis yAxis;
    public void setRentList(List<Rent> rentList) {
        // X-axis: Rent duration in days
        NumberAxis xaxis = new NumberAxis();
        xaxis.setLabel("Duration (days)");

        // Y-axis: Rent amount in euros
        NumberAxis yaxis = new NumberAxis();
        yaxis.setLabel("Rent Amount (€)");

        // Configure ScatterChart
        scatterChart = new ScatterChart<>(xaxis, yaxis);
        scatterChart.setTitle("Rent Duration vs Rent Amount");

        // Configure Series and adding data to the series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (Rent rent : rentList) {
            long duration = ChronoUnit.DAYS.between(rent.getStart(), rent.getEnd());
            series.getData().add(new XYChart.Data<>(duration, rent.getRent().doubleValue()));
            System.out.println(series.getData());
        }

        // Adding series to the ScatterChart
        scatterChart.getData().add(series);
        System.out.println(rentList);
        System.out.println(series);
    }

}


