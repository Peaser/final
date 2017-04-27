import java.util.ArrayList;

public class File extends FileObj {
	public String data = "";
	public ArrayList<FileObj> children = new ArrayList<FileObj>(0); // No children possible
	
	public File(String name) {
		super(name);
		this.type = "file";
	}
	
	public File(String name, String data) {
		super(name);
		this.type = "file";
		this.data = data;
	}
	
	public File(String name, boolean del) {
		super(name);
		this.type = "file";
		this.deletable = del;
	}
	
	public File(String name, String data, boolean del) {
		super(name);
		this.type = "file";
		this.data = data;
		this.deletable = del;
	}
	
	public String toString() {
		return this.name;
	}

}
