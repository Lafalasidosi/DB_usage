import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddRow {
    public static void display(String url, String user, String password) {
        Stage window = new Stage();
        // blocks user interaction with other windows until this one is done
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Insert");
        window.setMinWidth(500);

        Label columnNames = new Label("date, wakeupbs, breakfast, twohrbs1, prelunchbs, lunch, twohrbs2, presupperbs, supper, twohrbs2, prebedbs, exercise, comments");
        TextArea userInput = new TextArea();
        userInput.setPromptText("Comma-separated values for the different fields");
        Button add = new Button("Append to table");
        add.setOnAction(e -> {
            addRow(userInput.getText(), url, user, password);
            window.close();
        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> window.close());

        HBox buttonContainer = new HBox();
        buttonContainer.getChildren().addAll(add, cancel);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(columnNames, userInput, buttonContainer);

        Scene scene = new Scene(vbox, 900, 450);
        window.setScene(scene);
        window.showAndWait();

    }

    private static void addRow(String input, String url, String user, String password){
        try (Connection c = DriverManager.getConnection(url, user, password);
             Statement s = c.createStatement();) {

            String inputs[] = input.split(",");
            String sql = String.format("insert into sugars " +
                    "(date, wakeupbs, breakfast, twohrbs1, prelunchbs, " +
                    "lunch, twohrbs2, presupperbs, supper, twohrbs3, " +
                    "prebedbs, exercise, comments) values " +
                    "('%s',%.1f,'%s',%.1f,%.1f,'%s',%.1f,%.1f,'%s',%.1f,%.1f,'%s','%s');",
                    inputs[0],
                    Float.valueOf(inputs[1]),
                    inputs[2],
                    Float.valueOf(inputs[3]),
                    Float.valueOf(inputs[4]),
                    inputs[5],
                    Float.valueOf(inputs[6]),
                    Float.valueOf(inputs[7]),
                    inputs[8],
                    Float.valueOf(inputs[9]),
                    Float.valueOf(inputs[10]),
                    inputs[11],
                    inputs[12]);
            s.execute(sql);

        } catch(SQLException e) {
            System.err.println("Something went wrong in AddRow.addrow()");
            e.printStackTrace();
        }
    }
}
