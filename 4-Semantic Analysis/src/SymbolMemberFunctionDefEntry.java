
public class SymbolMemberFunctionDefEntry extends SymbolTableEntry {
	private String name;
	private String returnType;
	private SymbolTable symTable;
	
	public SymbolMemberFunctionDefEntry(String name, String returnType) {
		this.name = name;
		this.returnType = returnType;
	}
	
	public void Print() {
		System.out.println("member function   | " + name + "     => " + returnType);
	}

	public void setSymTable(SymbolTable symTable) {
		this.symTable = symTable;
	}
	
	public SymbolTable getSymTable() {
		return symTable;
	}
}
