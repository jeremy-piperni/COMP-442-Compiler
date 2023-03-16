import java.util.ArrayList;

public class SymbolTable {
	private String name;
	private ArrayList<SymbolTableEntry> symEntries = new ArrayList<>();
	private int scope = 0;
	
	public SymbolTable(String name, int scope) {
		this.name = name;
		this.scope = scope;
	}
	
	public void Print() {
		if (scope == 0) {
			System.out.println("======================================================================");
			System.out.println("| table: " + name);
			System.out.println("======================================================================");
			for (int i = 0; i < symEntries.size(); i++) {
				System.out.print("| ");
				symEntries.get(i).Print();
				if (symEntries.get(i).getSymTable() != null) {
					symEntries.get(i).getSymTable().Print();
				}	
			}
			System.out.println("======================================================================");
		} else if (scope == 1) {
			System.out.println("|    =================================================================");
			System.out.println("|    | table: " + name);
			System.out.println("|    =================================================================");
			for (int i = 0; i < symEntries.size(); i++) {
				System.out.print("|    | ");
				symEntries.get(i).Print();
				if (symEntries.get(i).getSymTable() != null) {
					symEntries.get(i).getSymTable().Print();
				}	
			}
			System.out.println("|    =================================================================");
		} else {
			System.out.println("|    |    ===========================================================");
			System.out.println("|    |    | table: " + name);
			System.out.println("|    |    ===========================================================");
			for (int i = 0; i < symEntries.size(); i++) {
				System.out.print("|    |    | ");
				symEntries.get(i).Print();
				if (symEntries.get(i).getSymTable() != null) {
					symEntries.get(i).getSymTable().Print();
				}	
			}
			System.out.println("|    |    ===========================================================");
		}
		
	}
	
	public ArrayList<SymbolTableEntry> getSymEntries() {
		return symEntries;
	}
	
}
