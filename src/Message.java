import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Message {

    public static void display(String title, String message) {
        Stage window = new Stage(StageStyle.UTILITY);
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label = new Label();
        label.setText(message);
        label.getStyleClass().add("aboutlabel");
        Button closeButton = new Button("Okay");
        closeButton.setOnAction(e -> window.close());
        closeButton.getStyleClass().add("aboutokaybtn");

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("aboutlayout");

        Scene scene = new Scene(layout, 300, 150);
        scene.getStylesheets().add("desk.css");
        window.setScene(scene);
        window.showAndWait();
    }

}