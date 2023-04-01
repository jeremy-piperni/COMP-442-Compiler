import java.util.ArrayList;

public class MemAllocVisitor1 implements Visitor {
	private String tempVarValue = "t1";
	
	public void visit(Node node) {}
	public void visit(EpsilonNode node) {}
	public void visit(ArraySizeNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(EmptyArraySizeNode node) {}
	public void visit(FParamsTailNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(FParamsTailListNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(FParamsNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(MemberFuncDeclNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(MemberVarDeclNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(MemberDeclNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(MemberDeclListNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(InheritanceListTailNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(InheritanceListNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(ClassDeclNode node) {
		int scopeSize = 0;
		ArrayList<SymbolTableEntry> entries = node.getSymEntry().getSymTable().getSymEntries();
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i) instanceof SymbolClassDataEntry) {
				scopeSize = scopeSize + ((SymbolClassDataEntry)entries.get(i)).getScopeSize();
			}
		}
		node.getSymbolTable().setScopeSize(scopeSize);
		
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(FuncHeadNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(AddOpNode node) {
		String type = "";

		Node expr = node;
		ArrayList<Node> terminals = new ArrayList<>();
		findTerminals(expr,terminals);
		type = type + terminals.get(0).getExpressionType();
		if (type.equals("integer")) {
			node.setMoonSize(4);
		} else if (type.equals("float")) {
			node.setMoonSize(8);
		}
		
		Node func = node;
		while (!func.getParent().getType().equals("FUNCDEF")) {
			func = func.getParent();
		}
		func = func.getParent();
		SymbolLocalVarParamEntry tempVar = new SymbolLocalVarParamEntry("tempVar",tempVarValue,type,node.getLoc(),node.getLexeme());
		node.setMoonVarName(tempVarValue);
		String tempVarValue2 = tempVarValue.substring(tempVarValue.length()-1);
		tempVarValue = tempVarValue.substring(0,tempVarValue.length()-1) + Integer.toString(Integer.parseInt(tempVarValue2) + 1);
		func.getSymbolTable().getSymEntries().add(tempVar);
		
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(MultOpNode node) {
		String type = "";

		Node expr = node;
		ArrayList<Node> terminals = new ArrayList<>();
		findTerminals(expr,terminals);
		type = type + terminals.get(0).getExpressionType();
		if (type.equals("integer")) {
			node.setMoonSize(4);
		} else if (type.equals("float")) {
			node.setMoonSize(8);
		}
		
		Node func = node;
		while (!func.getParent().getType().equals("FUNCDEF")) {
			func = func.getParent();
		}
		func = func.getParent();
		SymbolLocalVarParamEntry tempVar = new SymbolLocalVarParamEntry("tempVar",tempVarValue,type,node.getLoc(),node.getLexeme());
		node.setMoonVarName(tempVarValue);
		String tempVarValue2 = tempVarValue.substring(tempVarValue.length()-1);
		tempVarValue = tempVarValue.substring(0,tempVarValue.length()-1) + Integer.toString(Integer.parseInt(tempVarValue2) + 1);
		func.getSymbolTable().getSymEntries().add(tempVar);
		
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(RelOpNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(NotNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(SignNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(LocalVarDeclNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		
		if (childList.get(2).getChildren().size() > 0) {
			String type = childList.get(1).getLexeme();
			int size = 1;
			ArrayList<Node> arraySizeList = childList.get(2).getChildren();
			for (int i = 0; i < arraySizeList.size(); i++) {
				size = size * Integer.parseInt(arraySizeList.get(i).getLexeme());
			}
			if (type.equals("integer")) {
				size = size * 4;
			} else if (type.equals("float")) {
				size = size * 8;
			}
			node.setMoonSize(size);
			String id = childList.get(0).getLexeme();
			Node func = node;
			while (!func.getParent().getType().equals("FUNCDEF")) {
				func = func.getParent();
			}
			func = func.getParent();
			ArrayList<SymbolLocalVarParamEntry> localVars = new ArrayList<>();
			for (int i = 0; i < func.getSymbolTable().getSymEntries().size(); i++) {
				if (func.getSymbolTable().getSymEntries().get(i) instanceof SymbolLocalVarParamEntry) {
					localVars.add(((SymbolLocalVarParamEntry)func.getSymbolTable().getSymEntries().get(i)));
				}
			}
			for (int i = 0; i < localVars.size(); i++) {
				if (localVars.get(i).getId().equals(id)) {
					localVars.get(i).setScopeSize(size);
				}
			}
		}
	}
	
	public void visit(RelExprNode node) {
		String type = "";

		Node expr = node;
		ArrayList<Node> terminals = new ArrayList<>();
		findTerminals(expr,terminals);
		type = type + terminals.get(0).getExpressionType();
		if (type.equals("integer")) {
			node.setMoonSize(4);
		} else if (type.equals("float")) {
			node.setMoonSize(8);
		}
		
		Node func = node;
		while (!func.getParent().getType().equals("FUNCDEF")) {
			func = func.getParent();
		}
		func = func.getParent();
		SymbolLocalVarParamEntry tempVar = new SymbolLocalVarParamEntry("tempVar",tempVarValue,type,node.getLoc(),node.getLexeme());
		node.setMoonVarName(tempVarValue);
		String tempVarValue2 = tempVarValue.substring(tempVarValue.length()-1);
		tempVarValue = tempVarValue.substring(0,tempVarValue.length()-1) + Integer.toString(Integer.parseInt(tempVarValue2) + 1);
		func.getSymbolTable().getSymEntries().add(tempVar);
		
		Node checkForExpr = node;
		while (!checkForExpr.getParent().getType().equals("EXPR")) {
			checkForExpr = checkForExpr.getParent();
			if (!checkForExpr.getParent().getType().equals("PROG")) {
				break;
			}
		}
		if (!checkForExpr.getParent().getType().equals("EXPR")) {
			func = node;
			while (!func.getParent().getType().equals("FUNCDEF")) {
				func = func.getParent();
			}
			func = func.getParent();
			for (int i = 0; i < terminals.size(); i++) {
				if (terminals.get(i).getType().equals("intlit")) {
					tempVar = new SymbolLocalVarParamEntry("intVal",tempVarValue,"integer",node.getLoc(),terminals.get(i).getLexeme());
					terminals.get(i).setMoonVarName(tempVarValue);
					tempVarValue2 = tempVarValue.substring(tempVarValue.length()-1);
					tempVarValue = tempVarValue.substring(0,tempVarValue.length()-1) + Integer.toString(Integer.parseInt(tempVarValue2) + 1);
					func.getSymbolTable().getSymEntries().add(tempVar);
				} else if (terminals.get(i).getType().equals("floatlit")) {
					tempVar = new SymbolLocalVarParamEntry("floatVal",tempVarValue,"float",node.getLoc(),terminals.get(i).getLexeme());
					terminals.get(i).setMoonVarName(tempVarValue);
					tempVarValue2 = tempVarValue.substring(tempVarValue.length()-1);
					tempVarValue = tempVarValue.substring(0,tempVarValue.length()-1) + Integer.toString(Integer.parseInt(tempVarValue2) + 1);
					func.getSymbolTable().getSymEntries().add(tempVar);
				}
			}
		}
		
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		
	}
	
	public void visit(ExprNode node) {
		ArrayList<Node> terminals = new ArrayList<>();
		findTerminals(node,terminals);
		Node func = node;
		while (!func.getParent().getType().equals("FUNCDEF")) {
			func = func.getParent();
		}
		func = func.getParent();
		for (int i = 0; i < terminals.size(); i++) {
			if (terminals.get(i).getType().equals("intlit")) {
				SymbolLocalVarParamEntry tempVar = new SymbolLocalVarParamEntry("intVal",tempVarValue,"integer",node.getLoc(),terminals.get(i).getLexeme());
				terminals.get(i).setMoonVarName(tempVarValue);
				String tempVarValue2 = tempVarValue.substring(tempVarValue.length()-1);
				tempVarValue = tempVarValue.substring(0,tempVarValue.length()-1) + Integer.toString(Integer.parseInt(tempVarValue2) + 1);
				func.getSymbolTable().getSymEntries().add(tempVar);
			} else if (terminals.get(i).getType().equals("floatlit")) {
				SymbolLocalVarParamEntry tempVar = new SymbolLocalVarParamEntry("floatVal",tempVarValue,"float",node.getLoc(),terminals.get(i).getLexeme());
				terminals.get(i).setMoonVarName(tempVarValue);
				String tempVarValue2 = tempVarValue.substring(tempVarValue.length()-1);
				tempVarValue = tempVarValue.substring(0,tempVarValue.length()-1) + Integer.toString(Integer.parseInt(tempVarValue2) + 1);
				func.getSymbolTable().getSymEntries().add(tempVar);
			}
		}
		
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(ExprTailListNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(AParamsNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(WriteNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(ReturnNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(StatementNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(FuncBodyNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(FuncDefNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		int scopeSize = 0;
		int tempScopeSize = 0;
		for (int i = 0; i < node.getSymbolTable().getSymEntries().size(); i++) {
			((SymbolLocalVarParamEntry)node.getSymbolTable().getSymEntries().get(i)).setScopeOffset(scopeSize);
			tempScopeSize = ((SymbolLocalVarParamEntry)node.getSymbolTable().getSymEntries().get(i)).getScopeSize();
			scopeSize = scopeSize + tempScopeSize;
		}
		if (node.getSymEntry() instanceof SymbolFreeFunctionEntry) {
			(node.getSymbolTable()).setScopeSize(scopeSize);
		}
		
	}
	
	public void visit(ProgNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(ReadNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(VariableNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(IndiceNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(IdNestNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(IdNestTempNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(AssignStatNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(FunctionCallNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(StatBlockNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(IfNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(WhileNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
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
