
public class SymbolMemberFunctionDefEntry extends SymbolTableEntry {
	private String name;
	private String returnType;
	private SymbolTable symTable;
	private boolean deleted = false;
	private int line;
	
	public SymbolMemberFunctionDefEntry(String name, String returnType, int line) {
		this.name = name;
		this.returnType = returnType;
		this.line = line;
	}
	
	public void Print() {
		System.out.println("function   | " + name + "     => " + returnType);
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
	
	public int getLine() {
		return line;
	}
	
}
