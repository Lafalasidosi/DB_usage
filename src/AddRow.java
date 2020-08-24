import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Modality;

public class AddRow {
    /* window is a text area and confirm button
    user input looks like entering each field in a comma-separated list
    return array of String inputs or null if user presses cancel
     */

    public static void display() {
        Stage window = new Stage();
        // blocks user interaction with other windows until this one is done
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Insert");
        window.setMinWidth(500);

        Label columnNames = new Label("date, wakeupbs, breakfast, twohrbs1, prelunchbs, lunch, twohrbs2, presupperbs, supper, twohrbs2, prebedbs, exercise, comments");
        TextArea userInput = new TextArea();
        userInput.setPromptText("Comma-separated values for the different fields");
        Button add = new Button("Append to table");
        Button cancel = new Button("Cancel");

        HBox buttonContainer = new HBox();
        buttonContainer.getChildren().addAll(add, cancel);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(columnNames, userInput, buttonContainer);

        Scene scene = new Scene(vbox, 900, 450);
        window.setScene(scene);
        window.showAndWait();


    }
}
