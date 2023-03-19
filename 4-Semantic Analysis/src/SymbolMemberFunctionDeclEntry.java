import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SymbolMemberFunctionDeclEntry extends SymbolTableEntry {
	private String id;
	private ArrayList<String> parameters = new ArrayList<>();
	private String visibility;
	private String returnType;
	private SymbolTable symTable;
	private boolean matched = false;
	private int line;
	
	public SymbolMemberFunctionDeclEntry(String id, String visibility, String returnType, int line) {
		this.id = id;
		this.visibility = visibility;
		this.returnType = returnType;
		this.line = line;
	}
	
	public void Print(FileWriter symbolTableWriter) {
		try {
			symbolTableWriter.write("function    | " + id + "          | (");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.print("function    | " + id + "          | (");
		PrintParameters(symbolTableWriter);
		try {
			symbolTableWriter.write("):" + returnType + "     | " + visibility);
			symbolTableWriter.write(System.getProperty( "line.separator" ));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("):" + returnType + "     | " + visibility);
	}
	
	public void PrintParameters(FileWriter symbolTableWriter) {
		for (int i = 0; i < parameters.size(); i++) {
			try {
				symbolTableWriter.write(parameters.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.print(parameters.get(i));
			if (i != parameters.size() - 1) {
				try {
					symbolTableWriter.write(",");
				} catch (IOException e) {
					e.printStackTrace();
				}
				//System.out.print(",");
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
	
	public void match() {
		this.matched = true;
	}
	
	public boolean getMatched() {
		return matched;
	}
	
	public int getLine() {
		return line;
	}
	
}
