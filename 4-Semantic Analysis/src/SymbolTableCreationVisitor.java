
public class SymbolTableCreationVisitor implements Visitor {
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
		String name = node.getChildren().get(0).getLexeme();
		SymbolTable table = new SymbolTable(name,1);
		node.setSymEntry(new SymbolClassEntry(name));
		node.setSymTable(table);
		node.getSymEntry().setSymTable(table);
		Node parent = node.getParent();
		parent.getSymbolTable().getSymEntries().add(node.getSymEntry());
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
		String name = node.getChildren().get(0).getChildren().get(0).getLexeme();
		SymbolTable table = new SymbolTable("::" + name,1);
		node.setSymEntry(new SymbolFreeFunctionEntry(name));
		node.setSymTable(table);
		node.getSymEntry().setSymTable(table);
		Node parent = node.getParent();
		parent.getSymbolTable().getSymEntries().add(node.getSymEntry());
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
