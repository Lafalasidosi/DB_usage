import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.security.SecureRandom;
import java.sql.Statement;

public class App extends Application {

    Stage window;
    Scene scene;
    Button button1;
    Button button2;
    TableView<String> tableView;
    TableColumn<String, String> column1;
    TableColumn<String, String> column2;
    TableColumn<String, String> column3;
    // database connection
    final String url = "jdbc:postgresql://localhost/mydb";
    final String user = "john";
    final String password = "john";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // start window
        window = primaryStage;
        window.setTitle("lafa's list project");

        // "widgets"
        tableView = new TableView<>();
        column1 = new TableColumn<>("id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2 = new TableColumn<>("name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        column3 = new TableColumn<>("dept");
        column3.setCellValueFactory(new PropertyValueFactory<>("dept"));
        tableView.getColumns().addAll(column1, column2, column3);
        button1 = new Button("Initialize");
        button2 = new Button("Clear");
        button2.setOnAction(e -> clearDatabase());
        button1.setOnAction(e -> initializeDB());

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(tableView, button1, button2);

        // construct window
        scene = new Scene(layout, 500, 250);
        window.setScene(scene);
        window.show();
    }

    private void clearDatabase(){
        try(Connection c = DriverManager.getConnection(url, user, password);
            Statement s = c.createStatement();){
            s.executeUpdate("delete from company where id > 1000;");
        } catch(SQLException e){
            System.err.println("Couldn't clear the database");
        }
    }

    // Why does trying to have this return a connection in the heart of the app always throw an exception?
//    private Connection connectToDatabase(String url, String user, String password) {
//        try(Connection c = DriverManager.getConnection(url, user, password);) {
//            return c;
//        } catch (SQLException e) {
//            return null;
//        }
//    }

    private void initializeDB() {
        SecureRandom rand = new SecureRandom();
        String names[] = {"Alice", "Bill", "Charlie", "Denise", "Edwin", "Francine", "George"};
        String depts[] = {"HR", "IT", "Floor", "Warehouse", "Financial", "Executive", "Marketing"};
        String command;
        try(Connection c = DriverManager.getConnection(url, user, password);
            Statement s = c.createStatement();){
            for (int i = 0; i < 100; i++) {
                command = String.format("insert into company values (%d, \'%s\', \'%s\');",
                        i + 1000, names[rand.nextInt(7)], depts[rand.nextInt(7)]);
                s.executeUpdate(command);
            }
        }
        catch (SQLException e){System.err.println("Couldn't create a statement");}
    }

}