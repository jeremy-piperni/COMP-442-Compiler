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
	public void visit(RelExprNode node) {
		// 10.1 Type error in expression for Relative op in If/While
		if (node.getParent().getType().equals("IF") || node.getParent().getType().equals("WHILE")) {
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
							errorWriter.write("ERROR 11.1:  Undeclared variable/data member at line: " + line);
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
	}
	public void visit(ExprNode node) {
		// 10.1 Type error in expression
		ArrayList<Node> terminals = new ArrayList<>();
		boolean errorFound = false;
		String exprType = "";
		findTerminals(node,terminals);
		int line = 0;
		if (terminals.size() > 0) {
			line = terminals.get(0).getLoc();
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
						errorWriter.write("ERROR 11.1:  Undeclared variable/data member at line: " + line);
						errorWriter.write(System.getProperty( "line.separator" ));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					errorWriter.write("ERROR 10.1:  Type error in expression at line: " + line);
					errorWriter.write(System.getProperty( "line.separator" ));
					errorFound = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				if (arrayTypes.size() == 0) {
					exprType = exprType + expressionTypes.get(0);
				} else if (arrayTypes.size() == 1) {
					exprType = exprType + arrayTypes.get(0);
				} else {
					boolean ifNoTypeErrorArray = arrayTypes.stream().distinct().count() <= 1;
					if (!ifNoTypeErrorArray) {
						try {
							errorWriter.write("ERROR 10.1:  Type error in expression at line: " + line);
							errorWriter.write(System.getProperty( "line.separator" ));
							errorFound = true;
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						exprType = exprType + arrayTypes.get(0);
					}
					
				}
			}
			
		}
		
		if (!errorFound) {
			// 10.2 Type error in assignment statement
			if (node.getParent().getType().equals("ASSIGNSTAT")) {
				Node variable = node.getParent().getLeftChild();
				String variableLexeme = variable.getChildren().get(1).getLexeme();
				line = variable.getChildren().get(1).getLoc();
				Node function = node;
				while (!function.getParent().getType().equals("FUNCDEF")) {
					function = function.getParent();
				}
				function = function.getParent();
				boolean found = false;
				for (int i = 0; i < function.getSymbolTable().getSymEntries().size(); i++) {
					if (((SymbolLocalVarParamEntry)function.getSymbolTable().getSymEntries().get(i)).getId().equals(variableLexeme)) {
						found = true;
						String variableType = ((SymbolLocalVarParamEntry)function.getSymbolTable().getSymEntries().get(i)).getType();
						exprType = exprType.replaceAll("\\[\\d+\\]", "");
						exprType = exprType.replaceAll("\\[", "").replaceAll("\\]", "");
						variableType = variableType.replaceAll("\\[\\d+\\]", "");
						variableType = variableType.replaceAll("\\[", "").replaceAll("\\]", "");
						if (!variableType.equals(exprType)) {
							try {
								errorWriter.write("ERROR 10.2:  Type error in assignment statement at line: " + line);
								errorWriter.write(System.getProperty( "line.separator" ));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				if (!found) {
					if (node.getParent().getLeftChild().getLeftChild().getChildren().size() == 0) {
						try {
							errorWriter.write("ERROR 11.1:  Undeclared variable/data member at line: " + line);
							errorWriter.write(System.getProperty( "line.separator" ));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (node.getParent().getLeftChild().getLeftChild().getLeftChild().getChildren().size() != 0) {
						if (!node.getParent().getLeftChild().getLeftChild().getLeftChild().getChildren().get(0).getLexeme().equals("self")) {
							try {
								errorWriter.write("ERROR 11.1:  Undeclared variable/data member at line: " + line);
								errorWriter.write(System.getProperty( "line.separator" ));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				
			// 10.3 Type error in return statement
			} else if (node.getParent().getType().equals("RETURN")) {
				Node function = node;
				String returnType = "";
				while (!function.getParent().getType().equals("FUNCDEF")) {
					function = function.getParent();
				}
				function = function.getParent();
				if (function.getSymEntry() instanceof SymbolFreeFunctionEntry) {
					returnType = ((SymbolFreeFunctionEntry)function.getSymEntry()).getReturnType();
				} else {
					returnType = ((SymbolMemberFunctionDefEntry)function.getSymEntry()).getReturnType();
				}
				if (!returnType.equals(exprType)) {
					try {
						errorWriter.write("ERROR 10.3:  Type error in return statement at line: " + line);
						errorWriter.write(System.getProperty( "line.separator" ));
					} catch (IOException e) {
						e.printStackTrace();
					}
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
	public void visit(FuncDefNode node) {
		// 11.5 Undeclared class
		ArrayList<SymbolLocalVarParamEntry> localVars = new ArrayList<>();
		for (int i = 0; i < node.getSymbolTable().getSymEntries().size(); i++) {
			if (node.getSymbolTable().getSymEntries().get(i) instanceof SymbolLocalVarParamEntry) {
				if (((SymbolLocalVarParamEntry)node.getSymbolTable().getSymEntries().get(i)).getName().equals("local")) {
					localVars.add((SymbolLocalVarParamEntry)node.getSymbolTable().getSymEntries().get(i));
				}
			}
		}
		for (int i = localVars.size() - 1; i >= 0; i--) {
			if (localVars.get(i).getType().contains("integer") || localVars.get(i).getType().contains("float")) {
				localVars.remove(i);
			}
		}
		
		Node prog = node.getParent();
		ArrayList<SymbolClassEntry> classes = new ArrayList<>();
		for (int i = 0; i < prog.getSymbolTable().getSymEntries().size(); i++) {
			if (prog.getSymbolTable().getSymEntries().get(i) instanceof SymbolClassEntry) {
				classes.add((SymbolClassEntry)prog.getSymbolTable().getSymEntries().get(i));
			}
		}
		for (int i = 0; i < localVars.size(); i++) {
			boolean classFound = false;
			for (int j = 0; j < classes.size(); j++) {
				if (localVars.get(i).getType().equals(classes.get(j).getName())) {
					classFound = true;
				}
			}
			if (!classFound) {
				try {
					errorWriter.write("ERROR 11.5:  Undeclared class at line: " + localVars.get(i).getLine());
					errorWriter.write(System.getProperty( "line.separator" ));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public void visit(ProgNode node) {}
	public void visit(ReadNode node) {}
	public void visit(VariableNode node) {}
	public void visit(IndiceNode node) {}
	public void visit(IdNestNode node) {}
	public void visit(IdNestTempNode node) {}
	public void visit(AssignStatNode node) {}
	public void visit(FunctionCallNode node) {
		String name = node.getChildren().get(1).getLexeme();
		int line = node.getChildren().get(1).getLoc();	
		
		// 11.3 Undeclared member function
		if (node.getChildren().get(0).getChildren().size() != 0) {
			int size = node.getChildren().get(0).getChildren().size();
			if (node.getChildren().get(0).getChildren().get(size - 1).getChildren().get(1).getType().equals("INDICE")) {
				String var = node.getChildren().get(0).getChildren().get(size - 1).getChildren().get(0).getLexeme();
				String className = "";
				Node funcDef = node;
				while (funcDef.getParent().getType() != "FUNCDEF") {
					funcDef = funcDef.getParent();
				}
				funcDef = funcDef.getParent();
				boolean isFound = false;
				for (int i = 0; i < funcDef.getSymbolTable().getSymEntries().size(); i++) {
					if (((SymbolLocalVarParamEntry)funcDef.getSymbolTable().getSymEntries().get(i)).getId().equals(var)) {
						isFound = true;
						className = className + ((SymbolLocalVarParamEntry)funcDef.getSymbolTable().getSymEntries().get(i)).getType();
					}
				}
				if (isFound) {
					Node prog = node;
					while (prog.getParent().getType() != "PROG") {
						prog = prog.getParent();
					}
					prog = prog.getParent();
					boolean isMemberFunc = false;
					boolean isInClass = false;
					for (int i = 0; i < prog.getSymbolTable().getSymEntries().size(); i++) {
						if (prog.getSymbolTable().getSymEntries().get(i) instanceof SymbolClassEntry) {
							if (((SymbolClassEntry)prog.getSymbolTable().getSymEntries().get(i)).getName().equals(className)) {
								isInClass = true;
								SymbolClassEntry classEntry = (SymbolClassEntry)prog.getSymbolTable().getSymEntries().get(i);
								for (int j = 0; j < classEntry.getSymTable().getSymEntries().size(); j++) {
									if (classEntry.getSymTable().getSymEntries().get(j) instanceof SymbolMemberFunctionDeclEntry) {
										if (((SymbolMemberFunctionDeclEntry)classEntry.getSymTable().getSymEntries().get(j)).getId().equals(name)) {
											isMemberFunc = true;
										}
									}
								}
							}
						}
					}
					if (!isInClass) {
						
					} else if (!isMemberFunc){
						try {
							errorWriter.write("ERROR 11.3:  Undeclared member function at line: " + line);
							errorWriter.write(System.getProperty( "line.separator" ));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					try {
						errorWriter.write("ERROR 11.1:  Undeclared variable/data member at line: " + line);
						errorWriter.write(System.getProperty( "line.separator" ));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
		
		// 11.4 Undeclared/undefined free function

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
