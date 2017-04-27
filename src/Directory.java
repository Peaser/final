import java.util.ArrayList;

public class Directory extends FileObj{
	public ArrayList<FileObj> children = new ArrayList<FileObj>();

	public Directory(String name) {
		super(name);
		this.type = "dir";
	}
	public Directory(String name, boolean delable) {
		super(name);
		this.type = "dir";
		this.deletable = delable;
	}
	
	public String toString() {
		return this.name;
	}
	
	public void addChild(FileObj obj) {
		this.children.add(obj);
	}
}
