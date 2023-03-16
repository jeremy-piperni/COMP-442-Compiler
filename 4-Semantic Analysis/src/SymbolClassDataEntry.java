
public class SymbolClassDataEntry extends SymbolTableEntry {
	private String name;
	private String id;
	private String type;
	private String visibility;
	
	public SymbolClassDataEntry(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public SymbolClassDataEntry(String name, String id, String type, String visibility) {
		this.name = name;
		this.id = id;
		this.type = type;
		this.visibility = visibility;
	}
	
	public void Print() {
		if (type == null) {
			System.out.println(name + "   | " + id);
		} else {
			System.out.println(name + "   | " + id + "     | " + type + "          | " + visibility);
		}
	}
	
	public SymbolTable getSymTable() {
		return null;
	}

}
