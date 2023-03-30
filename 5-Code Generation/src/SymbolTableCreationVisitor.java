import java.util.ArrayList;

public class SymbolTableCreationVisitor implements Visitor {
	public void visit(Node node) {}
	public void visit(EpsilonNode node) {}
	public void visit(ArraySizeNode node) {}
	public void visit(EmptyArraySizeNode node) {}
	public void visit(FParamsTailNode node) {
		String name = "param";
		String id = node.getChildren().get(0).getLexeme();
		String type = node.getChildren().get(1).getLexeme();
		int line = node.getChildren().get(0).getLoc();
		int size;
		if (node.getChildren().get(2).getChildren() != null) {
			size = node.getChildren().get(2).getChildren().size();
			for (int i = 0; i < size; i++) {
				String value = "";
				if (node.getChildren().get(2).getChildren().get(i).getType() != "EMPTYARRAYSIZE") {
					value = node.getChildren().get(2).getChildren().get(i).getLexeme();
				}
				type = type + "[" + value + "]";
			}
		}
		Node parent = node.getParent().getParent().getParent().getParent();
		node.setSymEntry(new SymbolLocalVarParamEntry(name,id,type,line));
		parent.getSymbolTable().getSymEntries().add(node.getSymEntry());

	}
	public void visit(FParamsTailListNode node) {}
	public void visit(FParamsNode node) {
		if (node.getChildren().size() != 0) {
			String name = "param";
			String id = node.getChildren().get(0).getLexeme();
			String type = node.getChildren().get(1).getLexeme();
			int line = node.getChildren().get(0).getLoc();
			int size;
			if (node.getChildren().get(2).getChildren() != null) {
				size = node.getChildren().get(2).getChildren().size();
				for (int i = 0; i < size; i++) {
					String value = "";
					if (node.getChildren().get(2).getChildren().get(i).getType() != "EMPTYARRAYSIZE") {
						value = node.getChildren().get(2).getChildren().get(i).getLexeme();
					}
					type = type + "[" + value + "]";
				}
			}
			Node parent = node.getParent().getParent();
			node.setSymEntry(new SymbolLocalVarParamEntry(name,id,type,line));
			parent.getSymbolTable().getSymEntries().add(node.getSymEntry());
		}
	}
	public void visit(MemberFuncDeclNode node) {}
	public void visit(MemberVarDeclNode node) {}
	public void visit(MemberDeclNode node) {
		String visibility = node.getChildren().get(0).getLexeme();
		String id = "";
		String type = "";
		String name = "";
		if (node.getChildren().get(1).getType() == "MEMBERVARDECL") {
			int line = node.getChildren().get(0).getLoc();
			Node memberVarDecl = node.getChildren().get(1);
			name = "data";
			id = memberVarDecl.getChildren().get(0).getLexeme();
			type = memberVarDecl.getChildren().get(1).getLexeme();
			int size;
			if (memberVarDecl.getChildren().get(2).getChildren() != null && memberVarDecl.getChildren().get(2).getType() == "ARRAYSIZE") {
				size = memberVarDecl.getChildren().get(2).getChildren().size();
				for (int i = 0; i < size; i++) {
					String value = "";
					if (memberVarDecl.getChildren().get(2).getChildren().get(i).getType() != "EMPTYARRAYSIZE") {
						value = memberVarDecl.getChildren().get(2).getChildren().get(i).getLexeme();
					}
					type = type + "[" + value + "]";
				}
			}
			Node parent = node.getParent().getParent();
			node.setSymEntry(new SymbolClassDataEntry(name,id,type,visibility,line));
			parent.getSymbolTable().getSymEntries().add(node.getSymEntry());
		} else {
			Node memberFuncDecl = node.getChildren().get(1);
			if (memberFuncDecl.getChildren().size() == 1) {
				id = "constructor";
				int line = memberFuncDecl.getChildren().get(0).getChildren().get(0).getLoc();
				SymbolTable table = new SymbolTable(id,2);
				node.setSymEntry(new SymbolMemberFunctionDeclEntry(id,visibility,type,line));
				node.setSymTable(table);
				node.getSymEntry().setSymTable(table);
				Node parent = node.getParent().getParent();
				parent.getSymbolTable().getSymEntries().add(node.getSymEntry());
			} else {
				id = memberFuncDecl.getChildren().get(0).getLexeme();
				type = memberFuncDecl.getChildren().get(2).getLexeme();
				int line = memberFuncDecl.getChildren().get(2).getLoc();
				SymbolTable table = new SymbolTable(id,2);
				node.setSymEntry(new SymbolMemberFunctionDeclEntry(id,visibility,type,line));
				node.setSymTable(table);
				node.getSymEntry().setSymTable(table);
				Node parent = node.getParent().getParent();
				parent.getSymbolTable().getSymEntries().add(node.getSymEntry());
			}
		}
	}
	public void visit(MemberDeclListNode node) {}
	public void visit(InheritanceListTailNode node) {}
	public void visit(InheritanceListNode node) {}
	public void visit(ClassDeclNode node) {
		String name = node.getChildren().get(0).getLexeme();
		int line = node.getChildren().get(0).getLoc();
		SymbolTable table = new SymbolTable(name,1);
		node.setSymEntry(new SymbolClassEntry(name,line));
		node.setSymTable(table);
		node.getSymEntry().setSymTable(table);
		Node parent = node.getParent();
		parent.getSymbolTable().getSymEntries().add(node.getSymEntry());
		if (node.getChildren().get(1).getChildren().size() != 0) {
			ArrayList<Node> children = node.getChildren().get(1).getChildren();
			node.getSymbolTable().getSymEntries().add(new SymbolClassDataEntry("inherit",children.get(0).getLexeme(),line));
			if (children.get(1).getChildren().size() != 0) {
				children = children.get(1).getChildren();
				for (int i = 0; i < children.size(); i++) {
					node.getSymbolTable().getSymEntries().add(new SymbolClassDataEntry("inherit",children.get(i).getLexeme(),line));
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
	public void visit(LocalVarDeclNode node) {
		String name = "local";
		String id = node.getChildren().get(0).getLexeme();
		String type = node.getChildren().get(1).getLexeme();
		int line = node.getChildren().get(0).getLoc();
		int size;
		if (node.getChildren().get(2).getChildren() != null && node.getChildren().get(2).getType() == "ARRAYSIZE") {
			size = node.getChildren().get(2).getChildren().size();
			for (int i = 0; i < size; i++) {
				String value = "";
				if (node.getChildren().get(2).getChildren().get(i).getType() != "EMPTYARRAYSIZE") {
					value = node.getChildren().get(2).getChildren().get(i).getLexeme();
				}
				type = type + "[" + value + "]";
			}
		}
		node.setSymEntry(new SymbolLocalVarParamEntry(name,id,type,line));
		Node parent = node.getParent().getParent();
		parent.getSymbolTable().getSymEntries().add(node.getSymEntry());
	}
	public void visit(RelExprNode node) {}
	public void visit(ExprNode node) {}
	public void visit(ExprTailListNode node) {}
	public void visit(AParamsNode node) {}
	public void visit(WriteNode node) {}
	public void visit(ReturnNode node) {}
	public void visit(StatementNode node) {}
	public void visit(FuncBodyNode node) {}
	public void visit(FuncDefNode node) {
		Node funcHead = node.getChildren().get(0);
		String name = funcHead.getChildren().get(0).getLexeme();
		if (funcHead.getChildren().size() == 3) {
			String type = funcHead.getChildren().get(2).getLexeme();
			int line = funcHead.getChildren().get(2).getLoc();
			SymbolTable table = new SymbolTable("::" + name,1);
			node.setSymEntry(new SymbolFreeFunctionEntry(name,type,line));
			node.setSymTable(table);
			node.getSymEntry().setSymTable(table);
			Node parent = node.getParent();
			parent.getSymbolTable().getSymEntries().add(node.getSymEntry());
		} else if (funcHead.getChildren().size() == 4) {
			String type = funcHead.getChildren().get(3).getLexeme();
			String name2 = funcHead.getChildren().get(1).getLexeme();
			int line = funcHead.getChildren().get(0).getLoc();
			SymbolTable table = new SymbolTable(name + "::" + name2,1);
			node.setSymEntry(new SymbolMemberFunctionDefEntry(name,type,line));
			node.setSymTable(table);
			node.getSymEntry().setSymTable(table);
			Node parent = node.getParent();
			parent.getSymbolTable().getSymEntries().add(node.getSymEntry());
		} else {
			String name2 = "constructor";
			SymbolTable table = new SymbolTable(name + "::" + name2,1);
			int line = funcHead.getChildren().get(0).getLoc();
			node.setSymEntry(new SymbolMemberFunctionDefEntry(name,"",line));
			node.setSymTable(table);
			node.getSymEntry().setSymTable(table);
			Node parent = node.getParent();
			parent.getSymbolTable().getSymEntries().add(node.getSymEntry());
		}
	}
	public void visit(ProgNode node) {
		node.setSymTable(new SymbolTable("global",0));
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
