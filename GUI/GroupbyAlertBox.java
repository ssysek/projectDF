package GUI;

import labs.DataFrame;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GroupbyAlertBox {

    public String[] display(DataFrame df) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        Button applyButton = new Button("Apply changes");
        applyButton.setOnAction(e -> window.close());


        VBox layout = new VBox(10);

        String[] names = new String[df.columns.size()];

        ArrayList<ChoiceBox<String>> choiceBoxes = new ArrayList<>();
        for (int i = 0; i < df.columns.size(); i++) {
            names[i] = df.columns.get(i).columnName;
        }
        for (int i = 0; i < df.columns.size(); i++) {
            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().addAll(names);
            choiceBoxes.add(choiceBox);
        }

        HBox hBox = new HBox();
        for (ChoiceBox<String> i : choiceBoxes) {
            hBox.getChildren().addAll(i);
        }

        hBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(new Label("Choose on what columns you want to groupby"));
        layout.getChildren().addAll(hBox, applyButton);


        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        ArrayList<Integer> rem = new ArrayList<Integer>();

        for (int i = 0; i < choiceBoxes.size(); i++) {
            if (choiceBoxes.get(i).getValue() == null) {
                rem.add(i);
            } else {
                for (int j = 0; j < i; j++) {
                    if (choiceBoxes.get(i).getValue().equals(choiceBoxes.get(j).getValue())) {
                        rem.add(i);
                        break;
                    }
                }
            }
        }
        int c = 0, size = choiceBoxes.size();
        for (int i = 0; i < size; i++) {
            if (i == rem.get(c)) {
                choiceBoxes.remove(i - c); //idk why it doesnt work
                c++;
                if (c == rem.size()) {
                    break;
                }
            }
        }
        String[] result = new String[choiceBoxes.size()];
        for (int i = 0; i < choiceBoxes.size(); i++) {
            result[i] = choiceBoxes.get(i).getValue();
        }

        if (result.length > 0) {
            return result;
        } else {
            return new String[] {};
        }
    }
}
