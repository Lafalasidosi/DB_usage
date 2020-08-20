import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
// import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.collections.ObservableList;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.security.SecureRandom;

public class App extends Application {

    Stage window;
    Scene scene;
    Button button;
    ListView<String> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /// database connection
        final String url = "jdbc:postgresql://localhost/mydb";
        final String user = "john";
        final String password = "h74AX9ws";


        try(Connection c = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = c.prepareStatement("Select * from company");
            ResultSet rs = stmt.executeQuery()) {


            while(rs.next()) {
                for(int i = 1; i < 6; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

        window = primaryStage;
        window.setTitle("lafa's list project");
        button = new Button("Submit");

        listView = new ListView<>();
        listView.getItems().addAll("Titanic", "Chicken Run", "Ender's Game", "Pulp Fiction", "Kill Bill",
                "The Emperor's New Groove", "Melancholia");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        button.setOnAction(e -> buttonClicked());

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(listView, button);

        // construct window
        scene = new Scene(layout, 1500, 1250);
        window.setScene(scene);
        window.show();

    }

    private void buttonClicked() {
        listView.getSelectionModel().getSelectedItems().stream().forEach(s -> System.out.println(s));
        System.out.println();
    }

}