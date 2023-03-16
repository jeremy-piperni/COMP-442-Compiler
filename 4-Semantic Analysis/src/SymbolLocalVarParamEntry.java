
public class SymbolLocalVarParamEntry extends SymbolTableEntry {
	private String name;
	private String id;
	private String type;
	
	public SymbolLocalVarParamEntry(String name, String id, String type) {
		this.name = name;
		this.id = id;
		this.type = type;
	}
	
	public void Print() {
		System.out.println(name + "     | " + id + "     | " + type);
	}
	
	public SymbolTable getSymTable() {
		return null;
	}
	
	
}
