import java.util.ArrayList;
public abstract class FileObj {
	public ArrayList<FileObj> children;
	public String type;
	public String name;
	public boolean deletable;

	public FileObj(String name) {
		this.name = name;
	}

}