
public class AST {

	public AST() {
		
	}
	
	public Node makeNode(Token token) {
		return new Node(token.getType(),token.getLexeme());
	}
	
	public Node makeNode() {
		return new Node();
	}
	
	public Node makeFamily(Node op,Node kid1,Node kid2) {
		return (op.adoptChildren(kid1.makeSiblings(kid2)));
	}
	
}
