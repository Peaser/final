import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class nameGetter {
	
	static String name = "";

    public static String display(Stage parent) {
        Stage window = new Stage(StageStyle.UTILITY);
        
        //window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Please enter a filename");

        Label label = new Label();
        label.setText("Please enter a file name.");
        label.getStyleClass().add("aboutlabel");
        
        TextField fn = new TextField();
        fn.setMaxWidth(250);
        fn.setPromptText("Filename");
        
        Button closeButton = new Button("Okay");
        closeButton.setOnAction(e ->  {
        	name = fn.getText();
        	if (name.length() > 0)
        		window.close();
        });
        
        fn.setOnKeyTyped(e -> {
			// or press enter while the password field is selected
			if (e.getCharacter().equals("\r"/*Carriage return which is also known as Enter*/)) {
	        	name = fn.getText();
	        	if (name.length() > 0)
	        		window.close();
				}
		});
        
        closeButton.getStyleClass().add("aboutokaybtn");
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, fn, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("aboutlayout");

        Scene scene = new Scene(layout, 300, 150);
        scene.getStylesheets().add("desk.css");
        window.initOwner(parent);
        window.setScene(scene);
        window.showAndWait();
        
        return name;

    }

}