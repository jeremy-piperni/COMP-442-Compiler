import java.util.ArrayList;

public class AST {

	public AST() {
		
	}

	public Node makeNode() {
		return new EpsilonNode();
	}
	
	public Node makeNode(Token token) {
		return new Node(token);
	}
	
	public Node makeTypeNode(Token token) {
		return new Node(new Token("type", token.getLexeme(), token.getLoc()));
	}
	
	public Node makeNodeArraySize() {
		return new ArraySizeNode(new Token("ARRAYSIZE","ArraySize",0));
	}
	
	public Node makeNodeEmptyArraySize() {
		return new EmptyArraySizeNode(new Token("EMPTYARRAYSIZE","EmptyArraySize",0));
	}
	
	public Node makeNodeFParamsTail() {
		return new FParamsTailNode(new Token("FPARAMSTAIL","FParamsTail",0));
	}
	
	public Node makeNodeFParamsTailList() {
		return new FParamsTailListNode(new Token("FPARAMSTAILLIST","FParamsTailList",0));
	}
	
	public Node makeNodeFParams() {
		return new FParamsNode(new Token("FPARAMS","FParams",0));
	}
	
	public Node makeNodeMemberFuncDecl() {
		return new MemberFuncDeclNode(new Token("MEMBERFUNCDECL","MemberFuncDecl",0));
	}
	
	public Node makeNodeMemberVarDecl() {
		return new MemberVarDeclNode(new Token("MEMBERVARDECL","MemberVarDecl",0));
	}
	
	public Node makeNodeMemberDecl() {
		return new MemberDeclNode(new Token("MEMBERDECL","MemberDecl",0));
	}
	
	public Node makeNodeMemberDeclList() {
		return new MemberDeclListNode(new Token("MEMBERDECLLIST","MemberDeclList",0));
	}
	
	public Node makeNodeInheritanceListTail() {
		return new InheritanceListTailNode(new Token("INHERITANCELISTTAIL","InheritanceListTail",0));
	}
	
	public Node makeNodeInheritanceList() {
		return new InheritanceListNode(new Token("INHERITANCELIST","InheritanceList",0));
	}
	
	public Node makeNodeClassDecl() {
		return new ClassDeclNode(new Token("CLASSDECL","ClassDecl",0));
	}
	
	public Node makeNodeFuncHead() {
		return new FuncHeadNode(new Token("FUNCHEAD","FuncHead",0));
	}
	
	public Node makeAddOpNode(Token token) {
		return new AddOpNode(new Token("ADDOP",token.getLexeme(),token.getLoc()));
	}
	
	public Node makeMultOpNode(Token token) {
		return new MultOpNode(new Token("MULTOP",token.getLexeme(),token.getLoc()));
	}
	
	public Node makeRelOpNode(Token token) {
		return new RelOpNode(new Token("RELOP",token.getLexeme(),token.getLoc()));
	}
	
	public Node makeNotNode(Token token) {
		return new NotNode(new Token("NOT",token.getLexeme(),token.getLoc()));
	}
	
	public Node makeSignNode(Token token) {
		return new SignNode(new Token("SIGN",token.getLexeme(),token.getLoc()));
	}
	
	public Node makeNodeLocalVarDecl() {
		return new LocalVarDeclNode(new Token("LOCALVARDECL","LocalVarDecl",0));
	}
	
	public Node makeNodeRelExpr() {
		return new RelExprNode(new Token("RELEXPR","RelExpr",0));
	}
	
	public Node makeNodeExpr() {
		return new ExprNode(new Token("EXPR","Expr",0));
	}
	
	public Node makeNodeExprTailList() {
		return new ExprTailListNode(new Token("EXPRTAILLIST","ExprTailList",0));
	}
	
	public Node makeNodeAParams() {
		return new AParamsNode(new Token("APARAMS","AParams",0));
	}
	
	public Node makeWriteNode(Token token) {
		return new WriteNode(new Token("WRITE",token.getLexeme(),token.getLoc()));
	}
	
	public Node makeReturnNode(Token token) {
		return new ReturnNode(new Token("RETURN",token.getLexeme(),token.getLoc()));
	}
	
	public Node makeNodeStatement() {
		return new StatementNode(new Token("STATEMENT","Statement",0));
	}
	
	public Node makeNodeFuncBody() {
		return new FuncBodyNode(new Token("FUNCBODY","FuncBody",0));
	}
	
	public Node makeNodeFuncDef() {
		return new FuncDefNode(new Token("FUNCDEF","FuncDef",0));
	}
	
	public Node makeNodeProg() {
		return new ProgNode(new Token("PROG","Prog",0));
	}
	
	public Node makeReadNode(Token token) {
		return new ReadNode(new Token("READ",token.getLexeme(),token.getLoc()));
	}
	
	public Node makeNodeVariable() {
		return new VariableNode(new Token("VARIABLE","Variable",0));
	}
	
	public Node makeNodeIndice() {
		return new IndiceNode(new Token("INDICE","Indice",0));
	}
	
	public Node makeNodeIdNest() {
		return new IdNestNode(new Token("IDNEST","IdNest",0));
	}
	
	public Node makeNodeIdNestTemp() {
		return new IdNestTempNode(new Token("IDNESTTEMP","IdNestTemp",0));
	}
	
	public Node makeNodeAssignStat() {
		return new AssignStatNode(new Token("ASSIGNSTAT","AssignStat",0));
	}
	
	public Node makeNodeFunctionCall() {
		return new FunctionCallNode(new Token("FUNCTIONCALL","FunctionCall",0));
	}
	
	public Node makeNodeStatBlock() {
		return new StatBlockNode(new Token("STATBLOCK","StatBlock",0));
	}
	
	public Node makeIfNode(Token token) {
		return new IfNode(new Token("IF",token.getLexeme(),token.getLoc()));
	}
	
	public Node makeWhileNode(Token token) {
		return new WhileNode(new Token("WHILE",token.getLexeme(),token.getLoc()));
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
