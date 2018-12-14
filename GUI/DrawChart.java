package GUI;

import labs.Column;
import labs.DataFrame;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;

public class DrawChart {
    public ScatterChart<String,String> display(String[] names, DataFrame df) {

        final CategoryAxis xAxis = new CategoryAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final ScatterChart<String,String> sc = new ScatterChart<String,String>(xAxis,yAxis);
        xAxis.setLabel(names[1] + " value");
        yAxis.setLabel(names[0] + " value");
        sc.setTitle("Chart for columns " + names[0] + " and " + names[1]);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("The chart");
        for (Column i : df.columns) {
            if (i.columnName.equals(names[0])) {
                for (Column j : df.columns) {
                    if (j.columnName.equals(names[1])) {
                        for (int k = 0; k < j.col.size(); k += 1001) {
                            series1.getData().add(new XYChart.Data(j.col.get(k).toString(), i.col.get(k).toString()));
                        }
                        break;
                    }
                }
                break;
            }
        }



        sc.getData().addAll(series1);
        return sc;
    }
}
