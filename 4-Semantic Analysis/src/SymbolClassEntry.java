
public class SymbolClassEntry extends SymbolTableEntry {
	private String name;
	private SymbolTable symTable;
	private int line;
	
	public SymbolClassEntry(String name, int line) {
		this.name = name;
		this.line = line;
	}
	
	public void Print() {
		System.out.println("class     | " + name);
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
