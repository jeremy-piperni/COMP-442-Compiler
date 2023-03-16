
public class SymbolTableCreationVisitor implements Visitor {
	public void visit(Node node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(EpsilonNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(ArraySizeNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(EmptyArraySizeNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(FParamsTailNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(FParamsTailListNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(FParamsNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(MemberFuncDeclNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(MemberVarDeclNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(MemberDeclNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(MemberDeclListNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(InheritanceListTailNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(InheritanceListNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(ClassDeclNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(FuncHeadNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(AddOpNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(MultOpNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(RelOpNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(NotNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(SignNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(LocalVarDeclNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(RelExprNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(ExprNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(ExprTailListNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(AParamsNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(WriteNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(ReturnNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(StatementNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(FuncBodyNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(FuncDefNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(ProgNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(ReadNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(VariableNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(IndiceNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(IdNestNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(IdNestTempNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(AssignStatNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(FunctionCallNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(StatBlockNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(IfNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
	public void visit(WhileNode node) {
		System.out.println(node.getLexeme() + " visited");
	}
}
