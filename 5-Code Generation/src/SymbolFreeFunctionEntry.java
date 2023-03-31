import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SymbolFreeFunctionEntry extends SymbolTableEntry {
	private String name;
	private ArrayList<String> parameters = new ArrayList<>();
	private String returnType;
	private SymbolTable symTable;
	private int line;
	
	public SymbolFreeFunctionEntry(String name, String type, int line) {
		this.name = name;
		this.returnType = type;
		this.line = line;
	}
	
	public SymbolFreeFunctionEntry(String name, ArrayList<String> parameters, String type) {
		this.name = name;
		this.parameters = parameters;
		this.returnType = type;
	}
	
	public void Print(FileWriter symbolTableWriter) {
		try {
			symbolTableWriter.write("function    | " + name + "          | (");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.print("function    | " + name + "          | (");
		PrintParameters(symbolTableWriter);
		try {
			symbolTableWriter.write("):" + returnType);
			symbolTableWriter.write(System.getProperty( "line.separator" ));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("):" + returnType);
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
	
	public String getName() {
		return name;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public void addParameter(String parameter) {
		parameters.add(parameter);
	}
	
	public ArrayList<String> getParameters() {
		return parameters;
	}
	
	public int getLine() {
		return line;
	}

}
