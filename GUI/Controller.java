package GUI;

import javafx.scene.control.*;
import labs.DfList;
import labs.DataFrame;
import javafx.stage.FileChooser;
import labs.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

public class Controller {

    public ListView listView;

    private DataFrame df = null;
    private DataFrame chartDf;
    private String[] groupby;

    public void onMaxClick() {
        listView.getItems().clear();
        if (df != null && groupby != null) {
            if (groupby.length == 1) {
                String result = df.groupby(groupby[0]).max().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            } else {
                DfList ldf = df.groupby(groupby);
                //ldf.max().print();
                String result = ldf.max().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            }
        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }
    public void onMinClick() {
        listView.getItems().clear();
        if (df != null && groupby.length > 0) {
            if (groupby.length == 1) {
                String result = df.groupby(groupby[0]).min().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            } else {
                DfList ldf = df.groupby(groupby);
                //ldf.max().print();
                String result = ldf.min().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            }
        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }
    public void onSumClick() {
        listView.getItems().clear();
        if (df != null && groupby.length > 0) {
            if (groupby.length == 1) {
                String result = df.groupby(groupby[0]).sum().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            } else {
                DfList ldf = df.groupby(groupby);
                //ldf.max().print();
                String result = ldf.sum().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            }
        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }
    public void onVarClick() {
        listView.getItems().clear();
        if (df != null && groupby.length > 0) {
            if (groupby.length == 1) {
                String result = df.groupby(groupby[0]).var().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            } else {
                DfList ldf = df.groupby(groupby);
                //ldf.max().print();
                String result = ldf.var().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            }
        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }
    public void onStdClick() {
        listView.getItems().clear();
        if (df != null && groupby.length > 0) {
            if (groupby.length == 1) {
                String result = df.groupby(groupby[0]).std().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            } else {
                DfList ldf = df.groupby(groupby);
                //ldf.max().print();
                String result = ldf.std().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            }
        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }
    public void onMeanClick() {
        listView.getItems().clear();
        if (df != null && groupby.length > 0) {
            if (groupby.length == 1) {
                String result = df.groupby(groupby[0]).mean().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            } else {
                DfList ldf = df.groupby(groupby);
                //ldf.max().print();
                String result = ldf.mean().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            }
        } else if (df == null)  {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }
    public void onMedClick() {
        listView.getItems().clear();
        if (df != null && groupby.length > 0) {
            if (groupby.length == 1) {
                String result = df.groupby(groupby[0]).mediana().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            } else {
                DfList ldf = df.groupby(groupby);
                //ldf.max().print();
                String result = ldf.mediana().printGUI();
                Label label = new Label(result);
                listView.getItems().addAll(label);
            }
        } else if (df == null) {
            listView.getItems().addAll(new Label("You have to choose a file to create DataFrame first."));
        } else {
            listView.getItems().addAll(new Label("You have to define columns you want to groupby on"));
        }
    }
    public void onFileClick() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File", "*.csv"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            FileAlertBox fileAlertBox = new FileAlertBox();
            ArrayList<Class<? extends Value>> types = fileAlertBox.display(selectedFile.getAbsolutePath());
            boolean header = fileAlertBox.header;
            System.out.println(header);
            if (header) {
                df = new DataFrame(selectedFile.getAbsolutePath(), types, header);
                chartDf= new DataFrame(selectedFile.getAbsolutePath(), types, header);
            }
//            else {
//                df = new DataFrame(selectedFile.getAbsolutePath(), types, header, fileAlertBox.names);
//                chartDf = new DataFrame(selectedFile.getAbsolutePath(), types, header, fileAlertBox.names);
//            }


        } else {
            listView.getItems().addAll(new Label("You have to choose a file to proceed"));
        }

    }
    public void onGroupbyClick() {
        listView.getItems().clear();
        if (df != null) {
            groupby = new GroupbyAlertBox().display(df);
        } else {
            listView.getItems().addAll(new Label("You have to create DataFrame first"));
        }
    }
    public void onChartClick() {
        if (chartDf != null) {
            if (chartDf.columns.size() >= 2) {
                listView.getItems().clear();
                String[] names = new ChartAlertBox().display(chartDf);
                listView.getItems().addAll(new DrawChart().display(names, chartDf));
            } else {
                listView.getItems().addAll(new Label("DataFrame too small"));
            }
        } else {
            listView.getItems().addAll(new Label("You have to generate DataFrame first"));
        }
    }

}
