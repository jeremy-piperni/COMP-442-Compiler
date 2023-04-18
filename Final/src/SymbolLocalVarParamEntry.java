import java.io.FileWriter;
import java.io.IOException;

public class SymbolLocalVarParamEntry extends SymbolTableEntry {
	private String name;
	private String id;
	private String type;
	private int line;
	private int scopeSize;
	private int scopeOffset;
	private String value;
	
	public SymbolLocalVarParamEntry(String name, String id, String type, int line, String value) {
		this.name = name;
		this.id = id;
		this.type = type;
		this.line = line;
		if (type.equals("integer")) {
			scopeSize = 4;
		} else if (type.equals("float")) {
			scopeSize = 8;
		}
		this.value = value;
	}
	
	public SymbolLocalVarParamEntry(String name, String id, String type, int line) {
		this.name = name;
		this.id = id;
		this.type = type;
		this.line = line;
		if (type.equals("integer")) {
			scopeSize = 4;
		} else if (type.equals("float")) {
			scopeSize = 8;
		}
	}
	
	public void Print(FileWriter symbolTableWriter) {
		try {
			symbolTableWriter.write(name + "     | " + id + "     | " + type + "     | " + scopeSize + "     | " + scopeOffset);
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
	
	public int getScopeSize() {
		return scopeSize;
	}
	
	public void setScopeSize(int scopeSize) {
		this.scopeSize = scopeSize;
	}
	
	public int getScopeOffset() {
		return scopeOffset;
	}
	
	public void setScopeOffset(int scopeOffset) {
		this.scopeOffset = scopeOffset;
	}

	public String getValue() {
		return value;
	}
}
