
public class SymbolMemberFunctionDefEntry extends SymbolTableEntry {
	private String name;
	private String returnType;
	private SymbolTable symTable;
	private boolean deleted = false;
	
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
	
	public String getName() {
		return name;
	}
	
	public void delete() {
		deleted = true;
	}
	
	public boolean getDeleted() {
		return deleted;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
}
