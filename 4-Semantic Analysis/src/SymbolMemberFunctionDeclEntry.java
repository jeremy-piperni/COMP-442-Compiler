import java.util.ArrayList;

public class SymbolMemberFunctionDeclEntry extends SymbolTableEntry {
	private String id;
	private ArrayList<String> parameters = new ArrayList<>();
	private String visibility;
	private String returnType;
	private SymbolTable symTable;
	
	public SymbolMemberFunctionDeclEntry(String id, String visibility, String returnType) {
		this.id = id;
		this.visibility = visibility;
		this.returnType = returnType;
	}
	
	public void Print() {
		System.out.println("function    | " + id + "          | (" + parameters + "):" + returnType + "     | " + visibility);
	}

	public void setSymTable(SymbolTable symTable) {
		this.symTable = symTable;
	}
	
	public SymbolTable getSymTable() {
		return symTable;
	}
	
}
