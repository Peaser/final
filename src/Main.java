import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.stage.Stage;

public class Main extends Application {


	boolean startActive = false;//start menu toggleable
	boolean full = false; //fullscreen toggleable

	static Stage window;
	//Static for access by other windows

	public static void main(String[] args) {
		launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage1) throws Exception {

		window = stage1;
		stage1.setTitle("OpSystemulator");

		//Label l1 = new Label("test label click that");
		Button startbtn = new Button("Start");
		//startbtn.setAlignment(Pos.BASELINE_LEFT);
		startbtn.getStyleClass().add("startbtn");

		Button fullscreenbtn = new Button();
		fullscreenbtn.getStyleClass().add("fsb");
		fullscreenbtn.setOnAction(e -> {
			window.setFullScreen(!full);
			full = !full;
		});

		Label clock = new Label("00:00");
		//clock.setAlignment(Pos.Ba);
		clock.getStyleClass().add("clock");
		Task <Void> clockTask = new Task<Void>() {
			@Override
			public Void call() throws InterruptedException {
				while (true) {
					DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					Date dateobj = new Date();
					updateMessage(df.format(dateobj).toString());
					Thread.sleep(1000); //Update clock every second
				}
			}
		};
		
		clock.textProperty().bind(clockTask.messageProperty());
		Thread thread = new Thread(clockTask);
		thread.setDaemon(true);
		thread.start();

		VBox startmenumain = new VBox(20);
		startmenumain.getStyleClass().add("startmenu");
		startmenumain.setAlignment(Pos.BOTTOM_LEFT);

		Label userlb = new Label(" The User");
		Image usrimage = new Image(getClass().getResourceAsStream("user.png"));
		userlb.setGraphic(new ImageView(usrimage));
		userlb.getStyleClass().add("ulbl");

		Button aboutbtn = new Button("About");
		aboutbtn.getStyleClass().add("about");

		Button notepadLauncher = new Button("Note pad");
		notepadLauncher.getStyleClass().add("npst");

		Button fileexpLauncher = new Button("File Explorer");
		fileexpLauncher.getStyleClass().add("filest");

		Button solitaireLauncher = new Button("Solitaire");
		solitaireLauncher.getStyleClass().add("solst");

		aboutbtn.setMinWidth(150);
		aboutbtn.setAlignment(Pos.BASELINE_LEFT);
		aboutbtn.setOnAction(e->{
			About.display();
		});
		notepadLauncher.setMinWidth(150);
		notepadLauncher.setAlignment(Pos.BASELINE_LEFT);
		notepadLauncher.setOnAction(e->{
			Notepad.display();
		});
		fileexpLauncher.setMinWidth(150);
		fileexpLauncher.setAlignment(Pos.BASELINE_LEFT);
		fileexpLauncher.setOnAction(e->{
			FileViewer.display(window);
		});
		solitaireLauncher.setMinWidth(150);
		solitaireLauncher.setAlignment(Pos.BASELINE_LEFT);

		startmenumain.getChildren().addAll(userlb, aboutbtn, notepadLauncher, fileexpLauncher, solitaireLauncher);
		startmenumain.setMaxHeight(400);
		startmenumain.setVisible(false);

		startbtn.setOnAction(e -> {
			startmenumain.setVisible(!startActive);
			startActive = !startActive;
		});


		BorderPane barLayout = new BorderPane();
		barLayout.getStyleClass().add("background");
		BorderPane.setAlignment(startmenumain, Pos.BOTTOM_LEFT);

		HBox taskbar = new HBox(20);
		taskbar.getStyleClass().add("taskbar");
		taskbar.setAlignment(Pos.BASELINE_LEFT);

		HBox infobar = new HBox(20);
		infobar.getStyleClass().add("infobar");
		infobar.setAlignment(Pos.BASELINE_RIGHT);

		barLayout.setBottom(taskbar);
		barLayout.setTop(infobar);
		barLayout.setLeft(startmenumain);
		taskbar.getChildren().addAll(startbtn);
		infobar.getChildren().addAll(fullscreenbtn, clock);

		Scene desktop = new Scene(barLayout, 1200,900);
		desktop.getStylesheets().add("desk.css");

		TextField usernm = new TextField();
		PasswordField passwd = new PasswordField();
		Button logmein = new Button("Log in");
		logmein.setMaxWidth(100);
		usernm.setMaxWidth(250);
		usernm.setPromptText("User name");
		passwd.setMaxWidth(250);
		passwd.setPromptText("Password");
		logmein.setOnAction(e-> {
			// Log in button
			if ((usernm.getText().equals("the user")) && (passwd.getText().equals("default"))) {
				window.setScene(desktop);
			} else {
				Message.display("Invalid credentials", "Invalid username or password.");
			}
		});

		passwd.setOnKeyTyped(e -> {
			// or press enter while the password field is selected
			if (e.getCharacter().equals("\r"/*Carriage return which is also known as Enter*/)) {
				if ((usernm.getText().equals("the user")) && (passwd.getText().equals("default"))) {
					window.setScene(desktop);
				} else {
					Message.display("Invalid credentials", "Invalid username or password.");
				};
			}
		});

		VBox loginlayout = new VBox(50);
		loginlayout.getStyleClass().add("background");
		loginlayout.setAlignment(Pos.CENTER);
		loginlayout.getChildren().addAll(usernm, passwd, logmein);

		Scene login = new Scene(loginlayout, 1200,900);
		login.getStylesheets().add("desk.css");

		//window.setScene(login);
		window.setScene(desktop);

		Task <Void> btmtask = new Task<Void>() {
			@Override
			public Void call() throws InterruptedException {
				while (true) {
					window.toBack();
					Thread.sleep(100);
				}
			}
		};
		Thread low = new Thread(btmtask);
		low.setDaemon(true);
		low.start();

		window.setFullScreen(false);
		window.show();

	}
}
