import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SemanticErrorVisitor2 implements Visitor {
	FileWriter errorWriter;
	public SemanticErrorVisitor2(FileWriter error) {
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
	public void visit(ClassDeclNode node) {}
	public void visit(FuncHeadNode node) {}
	public void visit(AddOpNode node) {}
	public void visit(MultOpNode node) {}
	public void visit(RelOpNode node) {}
	public void visit(NotNode node) {}
	public void visit(SignNode node) {}
	public void visit(LocalVarDeclNode node) {}
	public void visit(RelExprNode node) {}
	public void visit(ExprNode node) {
		// 10.1 Type error in expression
		ArrayList<Node> terminals = new ArrayList<>();
		findTerminals(node,terminals);
		if (terminals.size() > 1) {
			int line = terminals.get(0).getLoc();
			for (int i = 0; i < terminals.size(); i++) {
				if (terminals.get(i).getType().equals("id")) {
					String lexeme = terminals.get(i).getLexeme();
					Node function = node;
					while (!function.getParent().getType().equals("FUNCDEF")) {
						function = function.getParent();
					}
					function = function.getParent();
					if (function.getSymEntry() instanceof SymbolMemberFunctionDefEntry) {
						for (int j = 0; j < function.getSymbolTable().getSymEntries().size(); j++) {
							if (((SymbolLocalVarParamEntry)function.getSymbolTable().getSymEntries().get(j)).getId().equals(lexeme)) {
								terminals.get(i).setExpressionType(((SymbolLocalVarParamEntry)function.getSymbolTable().getSymEntries().get(j)).getType());
							}
						}
						String functionName = ((SymbolMemberFunctionDefEntry)function.getSymEntry()).getName();
						ArrayList<SymbolClassEntry> classes = new ArrayList<>();
						Node prog = function.getParent();
						for (int j = 0; j < prog.getSymbolTable().getSymEntries().size(); j++) {
							if (prog.getSymbolTable().getSymEntries().get(j) instanceof SymbolClassEntry) {
								classes.add((SymbolClassEntry)prog.getSymbolTable().getSymEntries().get(j));
							}
						}
						for (int j = 0; j < classes.size(); j++) {
							if (functionName.equals(classes.get(j).getName())) {
								for (int k = 0; k < classes.get(j).getSymTable().getSymEntries().size(); k++) {
									if (classes.get(j).getSymTable().getSymEntries().get(k) instanceof SymbolClassDataEntry) {
										if (((SymbolClassDataEntry)classes.get(j).getSymTable().getSymEntries().get(k)).getId().equals(lexeme)) {
											terminals.get(i).setExpressionType(((SymbolClassDataEntry)classes.get(j).getSymTable().getSymEntries().get(k)).getType());
										}
									}
								}
							}
						}
					} else {
						for (int j = 0; j < function.getSymbolTable().getSymEntries().size(); j++) {
							if (((SymbolLocalVarParamEntry)function.getSymbolTable().getSymEntries().get(j)).getId().equals(lexeme)) {
								terminals.get(i).setExpressionType(((SymbolLocalVarParamEntry)function.getSymbolTable().getSymEntries().get(j)).getType());
							}
						}
					}
				} else if (terminals.get(i).getType().equals("intlit")) {
					terminals.get(i).setExpressionType("integer");
				} else {
					terminals.get(i).setExpressionType("float");
				}
			}

			ArrayList<String> expressionTypes = new ArrayList<>();
			for (int i = 0; i < terminals.size(); i++) {
				expressionTypes.add(terminals.get(i).getExpressionType());
			}
			
			ArrayList<String> arrayTypes = new ArrayList<>();
			for (int i = expressionTypes.size() - 1; i >= 0; i--) {
				if (!expressionTypes.contains(null)) {
					if (expressionTypes.get(i).contains("[")) {
						//expressionTypes.set(i, expressionTypes.get(i).replace("[", "").replace("]", ""));
						arrayTypes.add(expressionTypes.get(i));
						expressionTypes.remove(i);
					}
				}
			}
			
			boolean ifNoTypeError = expressionTypes.stream().distinct().count() <= 1;
			if (!ifNoTypeError) {
				if (expressionTypes.contains(null)) {
					try {
						errorWriter.write("ERROR 11.2:  Undeclared variable/data member at line: " + line);
						errorWriter.write(System.getProperty( "line.separator" ));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					errorWriter.write("ERROR 10.1:  Type error in expression at line: " + line);
					errorWriter.write(System.getProperty( "line.separator" ));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		terminals.clear();
	}
	public void visit(ExprTailListNode node) {}
	public void visit(AParamsNode node) {}
	public void visit(WriteNode node) {}
	public void visit(ReturnNode node) {}
	public void visit(StatementNode node) {}
	public void visit(FuncBodyNode node) {}
	public void visit(FuncDefNode node) {}
	public void visit(ProgNode node) {}
	public void visit(ReadNode node) {}
	public void visit(VariableNode node) {}
	public void visit(IndiceNode node) {}
	public void visit(IdNestNode node) {}
	public void visit(IdNestTempNode node) {}
	public void visit(AssignStatNode node) {}
	public void visit(FunctionCallNode node) {
		// 11.4 Undeclared/undefined free function
		String name = node.getChildren().get(1).getLexeme();
		int line = node.getChildren().get(1).getLoc();	
		Node prog = node;
		while (prog.getParent().getType() != "PROG") {
			prog = prog.getParent();
		}
		prog = prog.getParent();
		ArrayList<SymbolFreeFunctionEntry> functions = new ArrayList<>();
		for (int i = 0; i < prog.getSymbolTable().getSymEntries().size(); i++) {
			if (prog.getSymbolTable().getSymEntries().get(i) instanceof SymbolFreeFunctionEntry) {
				functions.add((SymbolFreeFunctionEntry) prog.getSymbolTable().getSymEntries().get(i));
			}
		}
		boolean isDefined = false;
		for (int i = 0; i < functions.size(); i++) {
			if (functions.get(i).getName().equals(name)) {
				isDefined = true;
			}
		}
		if (!isDefined) {
			try {
				errorWriter.write("ERROR 11.4:  Undeclared/Undefined free function at line: " + line);
				errorWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void visit(StatBlockNode node) {}
	public void visit(IfNode node) {}
	public void visit(WhileNode node) {}
	
	public void findTerminals(Node node, ArrayList<Node> terminals) {
		if (node.getChildren().size() != 0) {
			for (int i = 0; i < node.getChildren().size(); i++) {
				if (!(node.getChildren().get(i).getType().equals("FUNCTIONCALL"))) {
					findTerminals(node.getChildren().get(i), terminals);
				}
			}
		} else {
			if (node.getType().equals("intlit") || node.getType().equals("floatlit") || node.getType().equals("id")) {
				terminals.add(node);
			}
		}
	}
}
