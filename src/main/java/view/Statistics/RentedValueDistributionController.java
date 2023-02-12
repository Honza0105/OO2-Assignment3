package view.Statistics;

import domain.Rent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class RentedValueDistributionController {

    @FXML
    private ScatterChart<String, Number> scatterChart;

    @FXML
    public void initialize() {
//        XYChart.Series<String, Number> series = new XYChart.Series<>();
//        series.setName("Distribution of Rented Value");
//
//        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
//        data.add(new XYChart.Data<>("Day 1", 500));
//        data.add(new XYChart.Data<>("Day 2", 400));
//        data.add(new XYChart.Data<>("Day 3", 600));
//        data.add(new XYChart.Data<>("Day 4", 450));
//        data.add(new XYChart.Data<>("Day 5", 700));
//        series.setData(data);
//
//        scatterChart.getData().add(series);
    }

    public void setRentList(ObservableList<Rent> rentList) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Distribution of Rented Value");

        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        for (Rent rent : rentList) {
            long totalDays = ChronoUnit.DAYS.between(rent.getStart(), rent.getEnd());
            data.add(new XYChart.Data<>(String.valueOf(totalDays), rent.getRent()));
        }
        data.sort(Comparator.comparing(d -> new BigDecimal(d.getXValue().toString())));
        series.setData(data);

        scatterChart.getData().add(series);
    }


}
