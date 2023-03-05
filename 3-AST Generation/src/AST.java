import java.util.ArrayList;

public class AST {

	public AST() {
		
	}

	public Node makeNode() {
		return new Node();
	}
	
	public Node makeNode(Token token) {
		return new Node(token.getType(),token.getLexeme());
	}
	
	public Node makeTypeNode(Token token) {
		return new Node("type",token.getLexeme());
	}
	
	public Node makeNodeArraySize() {
		return new Node("ARRAYSIZE","ArraySize");
	}
	
	public Node makeNodeFParamsTail() {
		return new Node("FPARAMSTAIL","FParamsTail");
	}
	
	public Node makeNodeFParamsTailList() {
		return new Node("FPARAMSTAILLIST","FParamsTailList");
	}
	
	public Node makeNodeFParams() {
		return new Node("FPARAMS","FParams");
	}
	
	public Node makeNodeMemberFuncDecl() {
		return new Node("MEMBERFUNCDECL","MemberFuncDecl");
	}
	
	public Node makeNodeMemberVarDecl() {
		return new Node("MEMBERVARDECL","MemberVarDecl");
	}
	
	public Node makeNodeMemberDecl() {
		return new Node("MEMBERDECL","MemberDecl");
	}
	
	public Node makeNodeMemberDeclList() {
		return new Node("MEMBERDECLLIST","MemberDeclList");
	}
	
	public Node makeNodeInheritanceListTail() {
		return new Node("INHERITANCELISTTAIL","InheritanceListTail");
	}
	
	public Node makeNodeInheritanceList() {
		return new Node("INHERITANCELIST","InheritanceList");
	}
	
	public Node makeNodeClassDecl() {
		return new Node("CLASSDECL","ClassDecl");
	}
	
	public Node makeNodeFuncHead() {
		return new Node("FUNCHEAD","FuncHead");
	}
	
	public Node makeAddOpNode(Token token) {
		return new Node("ADDOP",token.getLexeme());
	}
	
	public Node makeMultOpNode(Token token) {
		return new Node("MULTOP",token.getLexeme());
	}
	
	public Node makeRelOpNode(Token token) {
		return new Node("RELOP",token.getLexeme());
	}
	
	public Node makeNotNode(Token token) {
		return new Node("NOT",token.getLexeme());
	}
	
	public Node makeSignNode(Token token) {
		return new Node("SIGN",token.getLexeme());
	}
	
	public Node makeNodeLocalVarDecl() {
		return new Node("LOCALVARDECL","LocalVarDecl");
	}
	
	public Node makeNodeRelExpr() {
		return new Node("RELEXPR","RelExpr");
	}
	
	public Node makeNodeExpr() {
		return new Node("EXPR","Expr");
	}
	
	public Node makeNodeExprTailList() {
		return new Node("EXPRTAILLIST","ExprTailList");
	}
	
	public Node makeNodeAParams() {
		return new Node("APARAMS","AParams");
	}
	
	public Node makeWriteNode(Token token) {
		return new Node("WRITE",token.getLexeme());
	}
	
	public Node makeReturnNode(Token token) {
		return new Node("RETURN",token.getLexeme());
	}
	
	public Node makeNodeStatement() {
		return new Node("STATEMENT","Statement");
	}
	
	public Node makeNodeFuncBody() {
		return new Node("FUNCBODY","FuncBody");
	}
	
	public Node makeNodeFuncDef() {
		return new Node("FUNCDEF","FuncDef");
	}
	
	public Node makeNodeProg() {
		return new Node("PROG","Prog");
	}
	
	public Node makeReadNode(Token token) {
		return new Node("READ",token.getLexeme());
	}
	
	public Node makeNodeVariable() {
		return new Node("VARIABLE","Variable");
	}
	
	public Node makeNodeIndice() {
		return new Node("INDICE","Indice");
	}
	
	public Node makeNodeAExpr() {
		return new Node("AEXPR","AExpr");
	}
	
	public Node makeNodeIdNest() {
		return new Node("IDNEST","IdNest");
	}
	
	public Node makeNodeIdNestTemp() {
		return new Node("IDNESTTEMP","IdNestTemp");
	}
	
	public Node makeNodeAssignStat() {
		return new Node("ASSIGNSTAT","AssignStat");
	}
	
	public Node makeNodeFunctionCall() {
		return new Node("FUNCTIONCALL","FunctionCall");
	}
	
	public Node makeNodeStatBlock() {
		return new Node("STATBLOCK","StatBlock");
	}
	
	public Node makeIfNode(Token token) {
		return new Node("IF",token.getLexeme());
	}
	
	public Node makeWhileNode(Token token) {
		return new Node("WHILE",token.getLexeme());
	}
	
	public Node makeFamilyArraySize(Node op, ArrayList<Node> children) {
		while (!children.isEmpty()) {
			op.adoptChildren(children.get(children.size() - 1));
			children.remove(children.size() - 1);
		}
		return op;
	}
	
	public Node makeFamilyFParamsTail(Node op, Node kid1, Node kid2, Node kid3) {
		return (op.adoptChildren(kid3.makeSiblings(kid2).makeSiblings(kid1)));
	}
	
	public Node makeFamilyFParamsTailList(Node op, ArrayList<Node> children) {
		while (!children.isEmpty()) {
			op.adoptChildren(children.get(children.size() - 1));
			children.remove(children.size() - 1);
		}
		return op;
	}
	
	public Node makeFamilyFParams(Node op, Node kid1, Node kid2, Node kid3, Node kid4) {
		return (op.adoptChildren(kid4.makeSiblings(kid3).makeSiblings(kid2).makeSiblings(kid1)));
	}
	
	public Node makeFamilyMemberFuncDecl1(Node op, Node kid1, Node kid2, Node kid3) {
		return (op.adoptChildren(kid3.makeSiblings(kid2).makeSiblings(kid1)));
	}
	
	public Node makeFamilyMemberFuncDecl2(Node op, Node kid1) {
		return (op.adoptChildren(kid1));
	}
	
	public Node makeFamilyMemberVarDecl(Node op, Node kid1, Node kid2, Node kid3) {
		return (op.adoptChildren(kid3.makeSiblings(kid2).makeSiblings(kid1)));
	}
	
	public Node makeFamilyMemberDecl1(Node op, Node kid1, Node kid2) {
		return (op.adoptChildren(kid2.makeSiblings(kid1)));
	}
	
	public Node makeFamilyMemberDecl2(Node op, Node kid1) {
		return (op.adoptChildren(kid1));
	}
	
	public Node makeFamilyMemberDeclList(Node op, ArrayList<Node> children) {
		while (!children.isEmpty()) {
			op.adoptChildren(children.get(children.size() - 1));
			children.remove(children.size() - 1);
		}
		return op;
	}
	
	public Node makeFamilyInheritanceListTail(Node op, ArrayList<Node> children) {
		while (!children.isEmpty()) {
			op.adoptChildren(children.get(children.size() - 1));
			children.remove(children.size() - 1);
		}
		return op;
	}
	
	public Node makeFamilyInheritanceList(Node op, ArrayList<Node> children) {
		while (!children.isEmpty()) {
			op.adoptChildren(children.get(children.size() - 1));
			children.remove(children.size() - 1);
		}
		return op;
	}
	
	public Node makeFamilyClassDecl(Node op, Node kid1, Node kid2, Node kid3) {
		return (op.adoptChildren(kid3.makeSiblings(kid2).makeSiblings(kid1)));
	}
	
	public Node makeFamilyFuncHead1(Node op, Node kid1, Node kid2, Node kid3) {
		return (op.adoptChildren(kid3.makeSiblings(kid2).makeSiblings(kid1)));
	}
	
	public Node makeFamilyFuncHead2(Node op, Node kid1, Node kid2, Node kid3, Node kid4) {
		return (op.adoptChildren(kid4.makeSiblings(kid3).makeSiblings(kid2).makeSiblings(kid1)));
	}
	
	public Node makeFamilyFuncHead3(Node op, Node kid1, Node kid2) {
		return (op.adoptChildren(kid2.makeSiblings(kid1)));
	}
	
	public Node makeFamilyLocalVarDecl(Node op, Node kid1, Node kid2, Node kid3) {
		return (op.adoptChildren(kid3.makeSiblings(kid2).makeSiblings(kid1)));
	}
	
	public Node makeFamilyNot(Node op, Node kid1) {
		return (op.adoptChildren(kid1));
	}
	
	public Node makeFamilySign(Node op, Node kid1) {
		return (op.adoptChildren(kid1));
	}
	
	public Node makeFamilyMultOp(Node op, Node kid1, Node kid2) {
		return (op.adoptChildren(kid2.makeSiblings(kid1)));
	}
	
	public Node makeFamilyAddOp(Node op, Node kid1, Node kid2) {
		return (op.adoptChildren(kid2.makeSiblings(kid1)));
	}
	
	public Node makeFamilyRelExpr(Node op, Node kid1, Node kid2, Node kid3) {
		return (op.adoptChildren(kid3.makeSiblings(kid2).makeSiblings(kid1)));
	}
	
	public Node makeFamilyExpr(Node op, Node kid1) {
		return (op.adoptChildren(kid1));
	}
	
	public Node makeFamilyExprTailList(Node op, ArrayList<Node> children) {
		while (!children.isEmpty()) {
			op.adoptChildren(children.get(children.size() - 1));
			children.remove(children.size() - 1);
		}
		return op;
	}
	
	public Node makeFamilyAParams(Node op, Node kid1, Node kid2) {
		return (op.adoptChildren(kid2.makeSiblings(kid1)));
	}
	
	public Node makeFamilyWrite(Node op, Node kid1) {
		return (op.adoptChildren(kid1));
	}
	
	public Node makeFamilyReturn(Node op, Node kid1) {
		return (op.adoptChildren(kid1));
	}
	
	public Node makeFamilyStatement(Node op, Node kid1) {
		return (op.adoptChildren(kid1));
	}
	
	public Node makeFamilyFuncBody(Node op, ArrayList<Node> children) {
		while (!children.isEmpty()) {
			op.adoptChildren(children.get(children.size() - 1));
			children.remove(children.size() - 1);
		}
		return op;
	}
	
	public Node makeFamilyFuncDef(Node op, Node kid1, Node kid2) {
		return (op.adoptChildren(kid2.makeSiblings(kid1)));
	}
	
	public Node makeFamilyProg(Node op, ArrayList<Node> children) {
		while (!children.isEmpty()) {
			op.adoptChildren(children.get(children.size() - 1));
			children.remove(children.size() - 1);
		}
		return op;
	}
	
	public Node makeFamilyIdNest(Node op, ArrayList<Node> children) {
		while (!children.isEmpty()) {
			op.adoptChildren(children.get(children.size() - 1));
			children.remove(children.size() - 1);
		}
		return op;
	}
	
	public Node makeFamilyRead(Node op, Node kid1) {
		return (op.adoptChildren(kid1));
	}
	
	public Node makeFamilyIndice(Node op, ArrayList<Node> children) {
		while (!children.isEmpty()) {
			op.adoptChildren(children.get(children.size() - 1));
			children.remove(children.size() - 1);
		}
		return op;
	}
	
	public Node makeFamilyAExpr(Node op, Node kid1) {
		return (op.adoptChildren(kid1));
	}
	
	public Node makeFamilyIdNestTemp(Node op, Node kid1, Node kid2) {
		return (op.adoptChildren(kid2.makeSiblings(kid1)));
	}
	
	public Node makeFamilyVariable(Node op, Node kid1, Node kid2, Node kid3) {
		return (op.adoptChildren(kid3.makeSiblings(kid2).makeSiblings(kid1)));
	}
	
	public Node makeFamilyAssignStat(Node op, Node kid1, Node kid2) {
		return (op.adoptChildren(kid2.makeSiblings(kid1)));
	}
	
	public Node makeFamilyFunctionCall(Node op, Node kid1, Node kid2, Node kid3) {
		return (op.adoptChildren(kid3.makeSiblings(kid2).makeSiblings(kid1)));
	}
	
	public Node makeFamilyStatBlock(Node op, ArrayList<Node> children) {
		while (!children.isEmpty()) {
			op.adoptChildren(children.get(children.size() - 1));
			children.remove(children.size() - 1);
		}
		return op;
	}
	
	public Node makeFamilyWhile(Node op, Node kid1, Node kid2) {
		return (op.adoptChildren(kid2.makeSiblings(kid1)));
	}
	
	public Node makeFamilyIf(Node op, Node kid1, Node kid2, Node kid3) {
		return (op.adoptChildren(kid3.makeSiblings(kid2).makeSiblings(kid1)));
	}
}
