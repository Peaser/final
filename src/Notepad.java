import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.geometry.*;

public class Notepad {

	public static TreeItem<FileObj> currentFile = null;// = new TreeItem<FileObj>(new Directory("ROOT"), new ImageView(new Image("fviewFolder.png")));

	public static void display() {
		Stage window = new Stage(StageStyle.UTILITY);

		window.setTitle("Notepad");

		TextArea field = new TextArea();
		field.getStyleClass().add("notepadfield");

		MenuBar menubar = new MenuBar();

		Menu filemenu = new Menu("File");
		Menu editmenu = new Menu("Edit");

		MenuItem newdoc = new MenuItem("New");
		MenuItem savedoc = new MenuItem("Save");

		MenuItem searchfor = new MenuItem("Search");

		menubar.getMenus().addAll(filemenu, editmenu);

		filemenu.getItems().addAll(newdoc, savedoc);
		editmenu.getItems().addAll(searchfor);

		newdoc.setOnAction(e->{
			currentFile = null;
			field.setText("");
		});

		savedoc.setOnAction(e->{
			if (currentFile == null) {
				currentFile = new TreeItem<FileObj>(new File(nameGetter.display(window), field.getText()),
						new ImageView(new Image("fviewFile.png")));
				FileViewer.display(window, currentFile);
			} else {
				currentFile = new TreeItem<FileObj>(new File(currentFile.getValue().name, field.getText()),
						new ImageView(new Image("fviewFile.png")));
				FileViewer.fs.addNewTreeItem(FileViewer.fs.notepadcurrentparent, currentFile);
			}
		});

		BorderPane layout = new BorderPane();
		layout.setTop(menubar);
		layout.setCenter(field);
		//layout.setAlignment(Pos.CENTER);
		layout.getStyleClass().add("notepadlayout");

		Scene scene = new Scene(layout, 600, 600);
		scene.getStylesheets().add("desk.css");

		window.setAlwaysOnTop(true);
		window.toFront();
		window.initOwner(Main.window); //This is important so it stays on top if in fullscreen
		window.setScene(scene);
		window.show();
	}

	public static void display(TreeItem<FileObj> presetfile) {
		Stage window = new Stage(StageStyle.UTILITY);
		
		currentFile = presetfile;

		window.setTitle("Notepad - "+presetfile.getValue().name);

		TextArea field = new TextArea();
		
		field.setText(((File)presetfile.getValue()).data);
		
		field.getStyleClass().add("notepadfield");

		MenuBar menubar = new MenuBar();

		Menu filemenu = new Menu("File");
		Menu editmenu = new Menu("Edit");

		MenuItem newdoc = new MenuItem("New");
		MenuItem savedoc = new MenuItem("Save");

		MenuItem searchfor = new MenuItem("Search");

		menubar.getMenus().addAll(filemenu, editmenu);

		filemenu.getItems().addAll(newdoc, savedoc);
		editmenu.getItems().addAll(searchfor);

		newdoc.setOnAction(e->{
			currentFile = null;
			field.setText("");
		});

		savedoc.setOnAction(e->{
			if (currentFile == null) {
				currentFile = new TreeItem<FileObj>(new File(nameGetter.display(window), field.getText()),
						new ImageView(new Image("fviewFile.png")));
				FileViewer.display(window, currentFile);
			} else {
				//TODO Fix duplicate file saving
				currentFile = new TreeItem<FileObj>(new File(currentFile.getValue().name, field.getText()),
						new ImageView(new Image("fviewFile.png")));
				FileViewer.fs.addNewTreeItem(FileViewer.fs.notepadcurrentparent, currentFile);
			}
		});

		BorderPane layout = new BorderPane();
		layout.setTop(menubar);
		layout.setCenter(field);
		//layout.setAlignment(Pos.CENTER);
		layout.getStyleClass().add("notepadlayout");

		Scene scene = new Scene(layout, 600, 600);
		scene.getStylesheets().add("desk.css");

		window.setAlwaysOnTop(true);
		window.toFront();
		window.initOwner(Main.window); //This is important so it stays on top if in fullscreen
		window.setScene(scene);
		window.show();
	}
	
}