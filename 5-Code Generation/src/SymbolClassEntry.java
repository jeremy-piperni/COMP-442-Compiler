import java.io.FileWriter;
import java.io.IOException;

public class SymbolClassEntry extends SymbolTableEntry {
	private String name;
	private SymbolTable symTable;
	private int line;
	
	public SymbolClassEntry(String name, int line) {
		this.name = name;
		this.line = line;
	}
	
	public void Print(FileWriter symbolTableWriter) {
		try {
			symbolTableWriter.write("class     | " + name);
			symbolTableWriter.write(System.getProperty( "line.separator" ));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("class     | " + name);
	}

	public void setSymTable(SymbolTable symTable) {
		this.symTable = symTable;
	}
	
	public SymbolTable getSymTable() {
		return symTable;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLine() {
		return line;
	}

}
