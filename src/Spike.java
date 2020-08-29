import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Spike {
    public static void display(String url, String user, String password) {
        Stage window = new Stage();

        TextField beginDate = new TextField();
        TextField endDate = new TextField();
        beginDate.setPromptText("Range Start");
        endDate.setPromptText("Range end");
        Button spike = new Button("Find Spikes");
        spike.setOnAction(e -> processEntries(url, user, password, beginDate.getText(), endDate.getText()));

        HBox dates = new HBox();
        dates.getChildren().addAll(beginDate, endDate);
        VBox contents = new VBox();
        contents.getChildren().addAll(dates, spike);

        Scene layout = new Scene(contents, 300, 300);
        window.setScene(layout);
        window.show();

    }

    private static void processEntries(String url, String user, String password, String beginDate, String endDate) {
        /*
all user gives is the date range (and eventually which set of blood sugar data too e.g. morning, afternoon, evening)
select rows w/in date range for processing
calculate avg for each column of numbers, 7 in total
then find the rows where the glucose measurement exceeds the average by some amount or proportion

side note: sql avg() seems to handle NULL number fields on its own -> refactor AddRow.java at some point
 */
        try (Connection c = DriverManager.getConnection(url, user, password);
             Statement s = c.createStatement();
             ) {

            String sql = String.format("select * from sugars where date >= '%s' and date <= '%s';", beginDate, endDate);
            ResultSet rs = s.executeQuery(sql); rs.next();



        } catch (SQLException e) {
            System.err.println("Something went finding spikes.");
        }


    }
}
