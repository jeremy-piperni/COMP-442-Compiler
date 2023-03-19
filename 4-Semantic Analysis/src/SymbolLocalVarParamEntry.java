import java.io.FileWriter;
import java.io.IOException;

public class SymbolLocalVarParamEntry extends SymbolTableEntry {
	private String name;
	private String id;
	private String type;
	private int line;
	
	public SymbolLocalVarParamEntry(String name, String id, String type, int line) {
		this.name = name;
		this.id = id;
		this.type = type;
		this.line = line;
	}
	
	public void Print(FileWriter symbolTableWriter) {
		try {
			symbolTableWriter.write(name + "     | " + id + "     | " + type);
			symbolTableWriter.write(System.getProperty( "line.separator" ));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(name + "     | " + id + "     | " + type);
	}
	
	public SymbolTable getSymTable() {
		return null;
	}
	
	public String getType() {
		return type;
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
	
}
