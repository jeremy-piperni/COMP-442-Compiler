import java.util.ArrayList;

public class SymbolFreeFunctionEntry extends SymbolTableEntry {
	private String name;
	private ArrayList<String> parameters = new ArrayList<>();
	private String returnType;
	private SymbolTable symTable;
	
	public SymbolFreeFunctionEntry(String name, String type) {
		this.name = name;
		this.returnType = type;
	}
	
	public SymbolFreeFunctionEntry(String name, ArrayList<String> parameters, String type) {
		this.name = name;
		this.parameters = parameters;
		this.returnType = type;
	}
	
	public void Print() {
		System.out.println("function    | " + name + "          | (" + parameters + "):" + returnType);
	}

	public void setSymTable(SymbolTable symTable) {
		this.symTable = symTable;
	}
	
	public SymbolTable getSymTable() {
		return symTable;
	}
}
