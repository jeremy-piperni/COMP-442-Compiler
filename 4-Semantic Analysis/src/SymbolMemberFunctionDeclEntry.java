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
		System.out.print("function    | " + id + "          | (");
		PrintParameters();
		System.out.println("):" + returnType + "     | " + visibility);
	}
	
	public void PrintParameters() {
		for (int i = 0; i < parameters.size(); i++) {
			System.out.print(parameters.get(i));
			if (i != parameters.size() - 1) {
				System.out.print(",");
			}
		}
	}

	public void setSymTable(SymbolTable symTable) {
		this.symTable = symTable;
	}
	
	public SymbolTable getSymTable() {
		return symTable;
	}
	
	public void addParameter(String parameter) {
		parameters.add(parameter);
	}
	
	public ArrayList<String> getParameters() {
		return parameters;
	}
	
	public String getId() {
		return id;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
}
