package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NamesInputAlertBox {

    public String[] display(int n) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        Button applyButton = new Button("Apply changes");
        applyButton.setOnAction(e -> window.close());


        VBox layout = new VBox(10);

        String[] names = new String[n];
        ArrayList<TextField> textFields = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Label label1 = new Label("Column " + (i + 1) + " name:");
            TextField textField = new TextField();
            HBox hb = new HBox();
            hb.getChildren().addAll(label1, textField);
            hb.setSpacing(10);
            layout.getChildren().addAll(hb);
            textFields.add(textField);
        }


        layout.getChildren().addAll(applyButton);


        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        for (int i = 0; i < names.length; i++) {
            names[i] = textFields.get(i).getText();
        }
        return names;
    }

}
