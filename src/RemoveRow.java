import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoveRow {
    public static void display(String url, String user, String password) {
        Stage window = new Stage();
        // blocks user interaction with other windows until this one is done
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Insert");
        window.setMinWidth(500);

        Label columnNames = new Label("Enter the YYYY-MM-DD date of the row to remove.");
        TextField userInput = new TextField();
        userInput.setPromptText("YYY-MM-DD");
        Button remove = new Button("Delete from table");
        remove.setOnAction(e -> {
            removeRow(userInput.getText(), url, user, password);
            window.close();
        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> window.close());

        HBox buttonContainer = new HBox();
        buttonContainer.getChildren().addAll(remove, cancel);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(columnNames, userInput, buttonContainer);

        Scene scene = new Scene(vbox, 300, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void removeRow(String date, String url, String user, String password){
        try (Connection c = DriverManager.getConnection(url, user, password);
             Statement s = c.createStatement();) {
            String sql = String.format("delete from sugars where date = '%s';", date);
            s.execute(sql);
        } catch (SQLException e) {
            System.err.println("Something went wrong deleting a row");
        }
    }
}
