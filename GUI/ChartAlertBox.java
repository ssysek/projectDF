package GUI;


import labs.DataFrame;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChartAlertBox {

    public String[] display(DataFrame df) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        Button applyButton = new Button("Apply changes");
        applyButton.setOnAction(e -> window.close());

        String[] names = new String[df.columns.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = df.columns.get(i).columnName;
        }


        VBox layout = new VBox(10);


        ChoiceBox<String> choiceBox1 = new ChoiceBox<>();
        choiceBox1.getItems().addAll(names);
        choiceBox1.setValue(names[0]);

        ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
        choiceBox2.getItems().addAll(names);
        choiceBox2.setValue(names[0]);



        layout.getChildren().addAll(new Label("Choose 2 columns which will generate chart"), choiceBox1, choiceBox2, applyButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();



        String[] result = new String[2];
        result[0] = choiceBox1.getValue();
        result[1] = choiceBox2.getValue();

        return result;

    }
}
