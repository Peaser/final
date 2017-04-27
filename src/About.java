//import javafx.concurrent.Task;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.stage.*;

public class About {
	public static void display(){
		Stage window = new Stage(StageStyle.UNDECORATED);
		window.setTitle("About Operating System Emulator");
		Label info = new Label("Operating System Emulator\nJava implementation of a fake operating system."
				+ "\nCurrent build date: Apr. 22, 2017\nAuthors:\n\tWilliam Mullaney\n\tBhavyaa Tyagi");
		info.getStyleClass().add("aboutlabel");
		
		Button okaybtn = new Button("Okay");
		okaybtn.getStyleClass().add("aboutokaybtn");
		okaybtn.setOnAction(e->window.close());
		
		VBox lay = new VBox(10);
		lay.getStyleClass().add("aboutlayout");
		lay.getChildren().addAll(info, okaybtn);
		lay.setAlignment(Pos.CENTER);
		
		Scene main = new Scene(lay, 400, 180);
		main.getStylesheets().add("desk.css");
		
		window.setScene(main);
		window.setAlwaysOnTop(true);
		window.toFront();
		/*
		// Mover to front task.
		Task <Void> frontTask = new Task<Void>() {
			@Override
			public Void call() throws InterruptedException {
				while (true) {
					Thread.sleep(100);
				}
			}
		};
		Thread thread = new Thread(frontTask);
		thread.setDaemon(true);
		thread.start();
		*/
		
		window.initOwner(Main.window);
		window.show();
	}

}
