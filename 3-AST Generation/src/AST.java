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
	
	public Node makeFamily(Node op,Node kid1,Node kid2) {
		return (op.adoptChildren(kid1.makeSiblings(kid2)));
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
	
	
}
