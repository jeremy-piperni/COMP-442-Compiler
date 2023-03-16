
public class SymbolClassEntry extends SymbolTableEntry {
	private String name;
	private SymbolTable symTable;
	
	public SymbolClassEntry(String name) {
		this.name = name;
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

}
