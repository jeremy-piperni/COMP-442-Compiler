import java.util.ArrayList;

public class MemAllocVisitor1 implements Visitor {
	private String tempVarValue = "t1";
	
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
		int scopeSize = 0;
		ArrayList<SymbolTableEntry> entries = node.getSymEntry().getSymTable().getSymEntries();
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i) instanceof SymbolClassDataEntry) {
				scopeSize = scopeSize + ((SymbolClassDataEntry)entries.get(i)).getScopeSize();
			}
		}
		node.getSymbolTable().setScopeSize(scopeSize);
		
	}
	public void visit(FuncHeadNode node) {}
	public void visit(AddOpNode node) {
		String type = "";

		Node expr = node;
		ArrayList<Node> terminals = new ArrayList<>();
		findTerminals(expr,terminals);
		type = type + terminals.get(0).getExpressionType();
		
		
		Node func = node;
		while (!func.getParent().getType().equals("FUNCDEF")) {
			func = func.getParent();
		}
		func = func.getParent();
		SymbolLocalVarParamEntry tempVar = new SymbolLocalVarParamEntry("tempVar",tempVarValue,type,node.getLoc());
		String tempVarValue2 = tempVarValue.substring(tempVarValue.length()-1);
		tempVarValue = tempVarValue.substring(0,tempVarValue.length()-1) + Integer.toString(Integer.parseInt(tempVarValue2) + 1);
		func.getSymbolTable().getSymEntries().add(tempVar);
	}
	public void visit(MultOpNode node) {
		String type = "";

		Node expr = node;
		ArrayList<Node> terminals = new ArrayList<>();
		findTerminals(expr,terminals);
		type = type + terminals.get(0).getExpressionType();
		
		Node func = node;
		while (!func.getParent().getType().equals("FUNCDEF")) {
			func = func.getParent();
		}
		func = func.getParent();
		SymbolLocalVarParamEntry tempVar = new SymbolLocalVarParamEntry("tempVar",tempVarValue,type,node.getLoc());
		String tempVarValue2 = tempVarValue.substring(tempVarValue.length()-1);
		tempVarValue = tempVarValue.substring(0,tempVarValue.length()-1) + Integer.toString(Integer.parseInt(tempVarValue2) + 1);
		func.getSymbolTable().getSymEntries().add(tempVar);
		
	}
	public void visit(RelOpNode node) {}
	public void visit(NotNode node) {}
	public void visit(SignNode node) {}
	public void visit(LocalVarDeclNode node) {}
	public void visit(RelExprNode node) {}
	public void visit(ExprNode node) {
		ArrayList<Node> terminals = new ArrayList<>();
		findTerminals(node,terminals);
		System.out.println(terminals);
		Node func = node;
		while (!func.getParent().getType().equals("FUNCDEF")) {
			func = func.getParent();
		}
		func = func.getParent();
		for (int i = 0; i < terminals.size(); i++) {
			if (terminals.get(i).getType().equals("intlit")) {
				SymbolLocalVarParamEntry tempVar = new SymbolLocalVarParamEntry("intVal",tempVarValue,"integer",node.getLoc());
				String tempVarValue2 = tempVarValue.substring(tempVarValue.length()-1);
				tempVarValue = tempVarValue.substring(0,tempVarValue.length()-1) + Integer.toString(Integer.parseInt(tempVarValue2) + 1);
				func.getSymbolTable().getSymEntries().add(tempVar);
			} else if (terminals.get(i).getType().equals("floatlit")) {
				SymbolLocalVarParamEntry tempVar = new SymbolLocalVarParamEntry("floatVal",tempVarValue,"float",node.getLoc());
				String tempVarValue2 = tempVarValue.substring(tempVarValue.length()-1);
				tempVarValue = tempVarValue.substring(0,tempVarValue.length()-1) + Integer.toString(Integer.parseInt(tempVarValue2) + 1);
				func.getSymbolTable().getSymEntries().add(tempVar);
			}
		}
	}
	public void visit(ExprTailListNode node) {}
	public void visit(AParamsNode node) {}
	public void visit(WriteNode node) {}
	public void visit(ReturnNode node) {}
	public void visit(StatementNode node) {}
	public void visit(FuncBodyNode node) {}
	public void visit(FuncDefNode node) {
		int scopeSize = 0;
		if (node.getSymEntry() instanceof SymbolMemberFunctionDefEntry) {
			ArrayList<SymbolTableEntry> entries = node.getSymEntry().getSymTable().getSymEntries();
			for (int i = 0; i < entries.size(); i++) {
				if (entries.get(i) instanceof SymbolLocalVarParamEntry) {
					((SymbolLocalVarParamEntry)entries.get(i)).setScopeOffset(scopeSize);
					scopeSize = scopeSize + ((SymbolLocalVarParamEntry)entries.get(i)).getScopeSize();
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
	public void visit(FunctionCallNode node) {}
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
