import java.io.FileWriter;
import java.io.IOException;

public class SymbolClassDataEntry extends SymbolTableEntry {
	private String name;
	private String id;
	private String type;
	private String visibility;
	private int line;
	
	public SymbolClassDataEntry(String name, String id, int line) {
		this.name = name;
		this.id = id;
		this.line = line;
	}
	
	public SymbolClassDataEntry(String name, String id, String type, String visibility, int line) {
		this.name = name;
		this.id = id;
		this.type = type;
		this.visibility = visibility;
		this.line = line;
	}
	
	public void Print(FileWriter symbolTableWriter) {
		if (type == null) {
			try {
				symbolTableWriter.write(name + "   | " + id);
				symbolTableWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println(name + "   | " + id);
		} else {
			try {
				symbolTableWriter.write(name + "   | " + id + "     | " + type + "          | " + visibility);
				symbolTableWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println(name + "   | " + id + "     | " + type + "          | " + visibility);
		}
	}
	
	public SymbolTable getSymTable() {
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public int getLine() {
		return line;
	}
	
	public String getType() {
		return type;
	}

}
