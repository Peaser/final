import javafx.scene.*;
import javafx.scene.control.*;
//import javafx.scene.image.*;
//import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.event.EventHandler;
//import javafx.geometry.*;
import javafx.stage.*;

public class FileViewer {

	public static FileSystem fs = new FileSystem();

	public static void display(Stage parent){
		Stage window = new Stage(StageStyle.UTILITY);

		window.setTitle("File Explorer");

		MenuItem editOpt = new MenuItem("Edit");
		MenuItem delOpt = new MenuItem("Delete");
		MenuItem newOpt = new MenuItem("New...");
		editOpt.setOnAction(e -> {
			TreeItem<FileObj> thisI = fs.tree.getSelectionModel().getSelectedItem();
			if (thisI.getValue().type.equals("file")) {
				fs.notepadcurrentparent = thisI.getParent();
				Notepad.display(thisI);
			}
		});

		delOpt.setOnAction(e-> {
			// Deletes it
			TreeItem<FileObj> c = (TreeItem<FileObj>)fs.tree.getSelectionModel().getSelectedItem();
			if (c != fs.root){
				@SuppressWarnings("unused")
				boolean remove = c.getParent().getChildren().remove(c);
			}

		});

		ContextMenu cm = new ContextMenu(editOpt, delOpt, newOpt);

		fs.tree.setContextMenu(cm);

		fs.tree.setOnMouseClicked(new EventHandler<MouseEvent>()
				//Treeview element on doubleclicked
				{
			@Override
			public void handle(MouseEvent mouseEvent)
			{            
				if(mouseEvent.getClickCount() == 2)
				{
					TreeItem<FileObj> thisI = fs.tree.getSelectionModel().getSelectedItem();
					if (thisI.getValue().type.equals("file")) {
						fs.notepadcurrentparent = thisI.getParent();
						Notepad.display(thisI);
					}

				}
			}
				});

		StackPane mainlayout = new StackPane();
		mainlayout.getStyleClass().add("treeview");
		fs.tree.getStyleClass().add("treeview");
		mainlayout.getChildren().add(fs.tree);

		Scene main = new Scene(mainlayout, 600, 600);

		main.getStylesheets().add("desk.css");
		window.initOwner(parent);
		window.setScene(main);
		window.setAlwaysOnTop(true);
		window.toFront();

		window.show();
	}

	public static void display(Stage parent, TreeItem<FileObj> itemobj){
		Stage window = new Stage(StageStyle.UTILITY);

		window.setTitle("Select directory to save...");

		MenuItem save = new MenuItem("Save Here");
		save.setOnAction(e -> {
			TreeItem<FileObj> thisI = fs.tree.getSelectionModel().getSelectedItem();
			if (thisI.getValue().type.equals("dir")) {
				fs.notepadcurrentparent = thisI;
				thisI.getChildren().add(itemobj);
				window.close();
			}
		});

		ContextMenu cm = new ContextMenu(save);


		fs.tree.setContextMenu(cm);

		fs.tree.setOnMouseClicked(new EventHandler<MouseEvent>()
				//Treeview element on doubleclicked
				{
			@Override
			public void handle(MouseEvent mouseEvent)
			{            
				if(mouseEvent.getClickCount() == 2)
				{
					TreeItem<FileObj> current = fs.tree.getSelectionModel().getSelectedItem();

					if (current.getValue().type.equals("dir")) {
						//Add thru doubleclick
						fs.notepadcurrentparent = current;
						current.getChildren().add(itemobj);

						window.close();
					}
				}
			}
				});

		StackPane mainlayout = new StackPane();
		mainlayout.getStyleClass().add("treeview");
		fs.tree.getStyleClass().add("treeview");
		mainlayout.getChildren().add(fs.tree);

		Scene main = new Scene(mainlayout, 600, 600);

		main.getStylesheets().add("desk.css");
		window.initOwner(parent);
		window.setScene(main);
		window.setAlwaysOnTop(true);
		window.toFront();

		window.show();
	}

}
