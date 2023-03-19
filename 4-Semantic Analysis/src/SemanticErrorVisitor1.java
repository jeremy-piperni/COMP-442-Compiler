import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SemanticErrorVisitor1 implements Visitor {
	FileWriter errorWriter;
	public SemanticErrorVisitor1(FileWriter error) {
		this.errorWriter = error;
	}
	
	public void visit(Node node) {}
	public void visit(EpsilonNode node) {}
	public void visit(ArraySizeNode node) {}
	public void visit(EmptyArraySizeNode node) {}
	public void visit(FParamsTailNode node) {}
	public void visit(FParamsTailListNode node) {}
	public void visit(FParamsNode node) {}
	public void visit(MemberFuncDeclNode node) {}
	public void visit(MemberVarDeclNode node) {}
	public void visit(MemberDeclNode node) {}
	public void visit(MemberDeclListNode node) {}
	public void visit(InheritanceListTailNode node) {}
	public void visit(InheritanceListNode node) {}
	public void visit(ClassDeclNode node) {
		// 8.3 Multiply declared data member in class
		ArrayList<SymbolClassDataEntry> data = new ArrayList<>();
		for (int i = 0; i < node.getSymbolTable().getSymEntries().size(); i++) {
			if (node.getSymbolTable().getSymEntries().get(i) instanceof SymbolClassDataEntry) {
				if (((SymbolClassDataEntry)node.getSymbolTable().getSymEntries().get(i)).getName().equals("data")) {
					data.add((SymbolClassDataEntry) node.getSymbolTable().getSymEntries().get(i));
				}
			}
		}
		for (int i = 0; i < data.size(); i++) {
			for (int j = i + 1; j < data.size(); j++) {
				if (data.get(i).getId().equals(data.get(j).getId())) {
					try {
						errorWriter.write("ERROR 8.3:  Multiply declared data member in class at line: " + data.get(j).getLine());
						errorWriter.write(System.getProperty( "line.separator" ));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	public void visit(FuncHeadNode node) {}
	public void visit(AddOpNode node) {}
	public void visit(MultOpNode node) {}
	public void visit(RelOpNode node) {}
	public void visit(NotNode node) {}
	public void visit(SignNode node) {}
	public void visit(LocalVarDeclNode node) {}
	public void visit(RelExprNode node) {}
	public void visit(ExprNode node) {}
	public void visit(ExprTailListNode node) {}
	public void visit(AParamsNode node) {}
	public void visit(WriteNode node) {}
	public void visit(ReturnNode node) {}
	public void visit(StatementNode node) {}
	public void visit(FuncBodyNode node) {}
	public void visit(FuncDefNode node) {
		
	}
	public void visit(ProgNode node) {
		ArrayList<SymbolClassEntry> classes = new ArrayList<>();
		ArrayList<SymbolFreeFunctionEntry> functions = new ArrayList<>();
		for (int i = 0; i < node.getSymbolTable().getSymEntries().size(); i++) {
			if (node.getSymbolTable().getSymEntries().get(i) instanceof SymbolClassEntry) {
				classes.add((SymbolClassEntry) node.getSymbolTable().getSymEntries().get(i));
			} else {
				functions.add((SymbolFreeFunctionEntry) node.getSymbolTable().getSymEntries().get(i));
			}
		}
		
		// 8.1 Multiply declared class
		for (int i = 0; i < classes.size(); i++) {
			for (int j = i + 1; j < classes.size(); j++ ) {
				if (classes.get(i).getName().equals(classes.get(j).getName())) {
					try {
						errorWriter.write("ERROR 8.1:  Multiply declared class at line: " + classes.get(j).getLine());
						errorWriter.write(System.getProperty( "line.separator" ));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		// 8.2 Multiply declared free function, 9.1 Overloaded free function
		for (int i = 0; i < functions.size(); i++) {
			for (int j = i + 1; j < functions.size(); j++) {
				if (functions.get(i).getName().equals(functions.get(j).getName()) && functions.get(i).getReturnType().equals(functions.get(j).getReturnType())) {
					ArrayList<String> parameters1 = functions.get(i).getParameters();
					ArrayList<String> parameters2 = functions.get(j).getParameters();
					if (parameters1.size() != parameters2.size()) {
						try {
							errorWriter.write("WARNING 9.1:  Overloaded free function at line: " + functions.get(j).getLine());
							errorWriter.write(System.getProperty( "line.separator" ));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						boolean[] paramMatch = new boolean[parameters1.size()];
						for (int k = 0; k < paramMatch.length; k++) {
							if (parameters1.get(k).equals(parameters2.get(k))) {
								paramMatch[k] = true;
							}
						}
						boolean allTrue=true;
						for(int m = 0; m < paramMatch.length; m++)
						{
						   if(paramMatch[m] == false) {
						       allTrue=false;
						   }
						}
						if (allTrue) {
							try {
								errorWriter.write("ERROR 8.2:  Multiply declared free function at line: " + functions.get(j).getLine());
								errorWriter.write(System.getProperty( "line.separator" ));
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							try {
								errorWriter.write("WARNING 9.1:  Overloaded free function at line: " + functions.get(j).getLine());
								errorWriter.write(System.getProperty( "line.separator" ));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	public void visit(ReadNode node) {}
	public void visit(VariableNode node) {}
	public void visit(IndiceNode node) {}
	public void visit(IdNestNode node) {}
	public void visit(IdNestTempNode node) {}
	public void visit(AssignStatNode node) {}
	public void visit(FunctionCallNode node) {}
	public void visit(StatBlockNode node) {}
	public void visit(IfNode node) {}
	public void visit(WhileNode node) {}
}
