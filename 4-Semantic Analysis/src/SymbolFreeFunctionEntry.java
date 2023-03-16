
public class SymbolFreeFunctionEntry extends SymbolTableEntry {
	private String name;
	private SymbolTable symTable;
	
	public SymbolFreeFunctionEntry(String name) {
		this.name = name;
	}
	
	public void Print() {
		System.out.println("function    | " + name);
	}

	public void setSymTable(SymbolTable symTable) {
		this.symTable = symTable;
	}
	
	public SymbolTable getSymTable() {
		return symTable;
	}
}
