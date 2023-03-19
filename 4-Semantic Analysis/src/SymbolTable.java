import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SymbolTable {
	private String name;
	private ArrayList<SymbolTableEntry> symEntries = new ArrayList<>();
	private int scope = 0;
	
	public SymbolTable(String name, int scope) {
		this.name = name;
		this.scope = scope;
	}
	
	public void Print(FileWriter symbolTableWriter) {
		if (scope == 0) {
			try {
				symbolTableWriter.write("==========================================================================================");
				symbolTableWriter.write(System.getProperty( "line.separator" ));
				symbolTableWriter.write("| table: " + name);
				symbolTableWriter.write(System.getProperty( "line.separator" ));
				symbolTableWriter.write("==========================================================================================");
				symbolTableWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println("======================================================================");
			//System.out.println("| table: " + name);
			//System.out.println("======================================================================");
			for (int i = 0; i < symEntries.size(); i++) {
				try {
					symbolTableWriter.write("| ");
				} catch (IOException e) {
					e.printStackTrace();
				}
				//System.out.print("| ");
				symEntries.get(i).Print(symbolTableWriter);
				if (symEntries.get(i).getSymTable() != null) {
					symEntries.get(i).getSymTable().Print(symbolTableWriter);
				}	
			}
			try {
				symbolTableWriter.write("==========================================================================================");
				symbolTableWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println("======================================================================");
		} else if (scope == 1) {
			try {
				symbolTableWriter.write("|    =====================================================================================");
				symbolTableWriter.write(System.getProperty( "line.separator" ));
				symbolTableWriter.write("|    | table: " + name);
				symbolTableWriter.write(System.getProperty( "line.separator" ));
				symbolTableWriter.write("|    =====================================================================================");
				symbolTableWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println("|    =================================================================");
			//System.out.println("|    | table: " + name);
			//System.out.println("|    =================================================================");
			for (int i = 0; i < symEntries.size(); i++) {
				try {
					symbolTableWriter.write("|    | ");
				} catch (IOException e) {
					e.printStackTrace();
				}
				//System.out.print("|    | ");
				symEntries.get(i).Print(symbolTableWriter);
				if (symEntries.get(i).getSymTable() != null) {
					symEntries.get(i).getSymTable().Print(symbolTableWriter);
				}	
			}
			try {
				symbolTableWriter.write("|    =====================================================================================");
				symbolTableWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println("|    =================================================================");
		} else {
			try {
				symbolTableWriter.write("|    |    ================================================================================");
				symbolTableWriter.write(System.getProperty( "line.separator" ));
				symbolTableWriter.write("|    |    | table: " + name);
				symbolTableWriter.write(System.getProperty( "line.separator" ));
				symbolTableWriter.write("|    |    ================================================================================");
				symbolTableWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println("|    |    ===========================================================");
			//System.out.println("|    |    | table: " + name);
			//System.out.println("|    |    ===========================================================");
			for (int i = 0; i < symEntries.size(); i++) {
				try {
					symbolTableWriter.write("|    |    | ");
				} catch (IOException e) {
					e.printStackTrace();
				}
				//System.out.print("|    |    | ");
				symEntries.get(i).Print(symbolTableWriter);
				if (symEntries.get(i).getSymTable() != null) {
					symEntries.get(i).getSymTable().Print(symbolTableWriter);
				}	
			}
			try {
				symbolTableWriter.write("|    |    ================================================================================");
				symbolTableWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println("|    |    ===========================================================");
		}
		
	}
	
	public ArrayList<SymbolTableEntry> getSymEntries() {
		return symEntries;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
