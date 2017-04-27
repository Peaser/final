import javafx.scene.control.*;
import javafx.scene.image.*;

public class FileSystem {
	
	public TreeItem<FileObj> root = new TreeItem<FileObj>(new Directory("ROOT"), new ImageView(new Image("fviewFolder.png")));
	public TreeView<FileObj> tree = new TreeView<FileObj>(root);
	
	public TreeItem<FileObj> notepadcurrentparent = null;
	
	public void addNewTreeItem(TreeItem<FileObj> rootItem, TreeItem<FileObj> newTreeItem) {
		rootItem.getChildren().add(newTreeItem);
	}
	
	// 4 File constructors
	public void addNewFile(TreeItem<FileObj> rootItem, String name) {
		rootItem.getChildren().add(new TreeItem<FileObj>(new File(name), new ImageView(new Image("fviewFile.png"))));
	}
	public void addNewFile(TreeItem<FileObj> rootItem, String name, String data) {
		rootItem.getChildren().add(new TreeItem<FileObj>(new File(name, data), new ImageView(new Image("fviewFile.png"))));
	}
	public void addNewFile(TreeItem<FileObj> rootItem, String name, boolean del) {
		rootItem.getChildren().add(new TreeItem<FileObj>(new File(name, del), new ImageView(new Image("fviewFile.png"))));
	}
	public void addNewFile(TreeItem<FileObj> rootItem, String name, String data, boolean del) {
		rootItem.getChildren().add(new TreeItem<FileObj>(new File(name, data, del), new ImageView(new Image("fviewFile.png"))));
	}
	
	
	public void addNewDirectory(TreeItem<FileObj> rootItem, String name) {
		rootItem.getChildren().add(new TreeItem<FileObj>(new Directory(name), new ImageView(new Image("fviewFolder.png"))));
	}
	
	public FileSystem() {
		//this.root.getChildren().add(new TreeItem<FileObj>(new File("ReadMe.txt"), new ImageView(new Image("fviewFile.png"))));
		//this.root.getChildren().add(new TreeItem<FileObj>(new Directory("B"), new ImageView(new Image("fviewFolder.png"))));
		//this.root.getChildren().add(new TreeItem<FileObj>(new Directory("A"), new ImageView(new Image("fviewFolder.png"))));
		
		addNewFile(this.root, "ReadMe.txt", "This is a built in readme file. It works!");
		addNewDirectory(this.root, "A:/");
		addNewDirectory(this.root, "B:/");
		addNewDirectory(this.root, "C:/");
		
		addNewFile(this.root.getChildren().get(1), "inDirA.txt", "This file was put in directory A:/ from within FileSystem.java.");
		
		addNewDirectory(this.root.getChildren().get(3), "Desktop");
		addNewDirectory(this.root.getChildren().get(3).getChildren().get(0), "Recycle Bin");
		
	}

}
