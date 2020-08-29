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
import java.security.SecureRandom;
import java.sql.Statement;

public class App extends Application {

    Stage window;
    Scene scene;
    TableView<Entry> tableView;
    TableColumn<Entry, String> columns[];
    TableColumn<Entry, String> column1;
    TableColumn<Entry, String> column2;
    TableColumn<Entry, String> column3;
    TableColumn<Entry, String> column4;
    TableColumn<Entry, String> column5;
    TableColumn<Entry, String> column6;
    TableColumn<Entry, String> column7;
    TableColumn<Entry, String> column8;
    TableColumn<Entry, String> column9;
    TableColumn<Entry, String> column10;
    TableColumn<Entry, String> column11;
    TableColumn<Entry, String> column12;
    TableColumn<Entry, String> column13;
    Button addButton;
    Button delButton;
    Button refreshButton;
    Button findSpikeButton;
    // database connection
    final String url = "jdbc:postgresql://localhost/omasdb";
    final String user = "oma";
    final String password = "oma";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // start window
        window = primaryStage;
        window.setTitle("GlucoseTracker");

        // Table and Buttons
        tableView = new TableView<>();
        column1 = new TableColumn<>("Date");
        column1.setCellValueFactory(new PropertyValueFactory<>("date"));
        column2 = new TableColumn<>("Morning BS");
        column2.setCellValueFactory(new PropertyValueFactory<>("wakeupbs"));
        column3 = new TableColumn<>("Breakfast");
        column3.setCellValueFactory(new PropertyValueFactory<>("breakfast"));
        column4 = new TableColumn<>("2hr BS");
        column4.setCellValueFactory(new PropertyValueFactory<>("twohrbs1"));
        column5 = new TableColumn<>("Pre-lunch BS");
        column5.setCellValueFactory(new PropertyValueFactory<>("prelunchbs"));
        column6 = new TableColumn<>("Lunch");
        column6.setCellValueFactory(new PropertyValueFactory<>("lunch"));
        column7 = new TableColumn<>("2hr BS");
        column7.setCellValueFactory(new PropertyValueFactory<>("twohrbs2"));
        column8 = new TableColumn<>("Pre-supper BS");
        column8.setCellValueFactory(new PropertyValueFactory<>("presupperbs"));
        column9 = new TableColumn<>("Supper");
        column9.setCellValueFactory(new PropertyValueFactory<>("supper"));
        column10 = new TableColumn<>("2hr BS");
        column10.setCellValueFactory(new PropertyValueFactory<>("twohrbs3"));
        column11 = new TableColumn<>("Pre-bed BS");
        column11.setCellValueFactory(new PropertyValueFactory<>("prebedbs"));
        column12 = new TableColumn<>("Exercise");
        column12.setCellValueFactory(new PropertyValueFactory<>("exercise"));
        column13 = new TableColumn<>("Comments");
        column13.setCellValueFactory(new PropertyValueFactory<>("comments"));
        tableView.getColumns().addAll(column1,
                column2,
                column3,
                column4,
                column5,
                column6,
                column7,
                column8,
                column9,
                column10,
                column11,
                column12,
                column13);
        addButton = new Button("Add");
        addButton.setOnAction(e -> {
            AddRow.display(url, user, password);
            populateTable();
        });
        delButton = new Button("Delete");
        delButton.setOnAction(e -> {
            RemoveRow.display(url, user, password);
            populateTable();
        });
        refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> populateTable());
        findSpikeButton = new Button("Find Spikes");
         findSpikeButton.setOnAction(e -> {
              Spike.display(url, user, password);
              // showSearchResult();
         });

        populateTable();

        // Layout
        VBox layout = new VBox(10);
        HBox buttonContainer = new HBox();
        buttonContainer.getChildren().addAll(addButton, delButton, refreshButton, findSpikeButton);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(tableView, buttonContainer);

        // construct window
        scene = new Scene(layout, 1200, 500);
        window.setScene(scene);
        window.show();
    }

    private void populateTable() {
        tableView.getItems().clear();
        try (Connection c = DriverManager.getConnection(url, user, password);
             Statement s = c.createStatement();) {
            String query = "select * from sugars;";
            ResultSet rs = s.executeQuery(query);

            while(rs.next())
            tableView.getItems().add(new Entry(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13)
            ));
        } catch (SQLException e) {
            System.err.println("Failed to populate tableView");
            e.printStackTrace();
        }
    }

    private void clearDatabase() {
        try (Connection c = DriverManager.getConnection(url, user, password);
             Statement s = c.createStatement();) {
            s.executeUpdate("truncate table;");
        } catch (SQLException e) {
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

}
