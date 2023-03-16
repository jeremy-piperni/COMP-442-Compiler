import java.util.ArrayList;

public class Node {
	private Token token;
	private Node parent;
	private Node leftMostSibling;
	private Node rightSibling;
	private Node leftChild;
	private ArrayList<Node> children = new ArrayList<>();
	
	public Node() {
		token = new Token(null,null,0);
	}
	
	public void accept(Visitor visitor) {
			visitor.visit(this);
	};
	
	public Node(Token token) {
		this.token = token;
		this.leftMostSibling = this;
	}
	
	public Node makeSiblings(Node y) {
		Node xsibs = this;
		while (xsibs.getRightSibling() != null) {
			xsibs = xsibs.getRightSibling();
		}
		
		Node ysibs = y.leftMostSibling;
		xsibs.rightSibling = ysibs;
		
		ysibs.leftMostSibling = xsibs.leftMostSibling;
		ysibs.parent = xsibs.parent;
		while (ysibs.getRightSibling() != null) {
			ysibs = ysibs.getRightSibling();
			ysibs.leftMostSibling = xsibs.leftMostSibling;
			ysibs.parent = xsibs.parent;
		}
		return ysibs;
	}
	
	public Node adoptChildren(Node y) {
		if (this.getLeftChild() != null) {
			this.getLeftChild().makeSiblings(y);
		} else {
			Node ysibs;
			ysibs = y.leftMostSibling;
			this.leftChild = ysibs;
			while (ysibs != null) {
				ysibs.parent = this;
				ysibs = ysibs.getRightSibling();
			}
		}
		return this;
	}
	
	private static int depthCounter = 0;
	public static void printTree2(Node x) {
		System.out.print(x);
		if (x.getLeftChild() != null) {
			depthCounter++;
			Node now = x.getLeftChild();
			System.out.println();
			for (int i = 0; i < depthCounter; i++) {
				System.out.print("| ");
			}
			printTree2(now);
			depthCounter--;
		}
		if (x.getRightSibling() != null) {
			System.out.println();
			for (int i = 0; i < depthCounter; i++) {
				System.out.print("| ");
			}
			Node now2 = x.getRightSibling();
			printTree2(now2);
		}
	}
	
	public String toString() {
		return token.getType();
	}

	public String getType() {
		return token.getType();
	}
	
	public String getLexeme() {
		return token.getLexeme();
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public Node getRightSibling() {
		return rightSibling;
	}
	
	public void findChildren() {
		if (this.leftChild != null) {
			Node temp = this.leftChild;
			this.children.add(temp);
			while (temp.rightSibling != null) {
				temp = temp.rightSibling;
				this.children.add(temp);
			}
		}
	}
	
	public ArrayList<Node> getChildren() {
		return children;
	}
}

class EpsilonNode extends Node {
	public EpsilonNode() {
		super();
	}
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}

class ArraySizeNode extends Node {
	public ArraySizeNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class EmptyArraySizeNode extends Node {
	public EmptyArraySizeNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}

class FParamsTailNode extends Node {
	public FParamsTailNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class FParamsTailListNode extends Node {
	public FParamsTailListNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class FParamsNode extends Node {
	public FParamsNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class MemberFuncDeclNode extends Node {
	public MemberFuncDeclNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class MemberVarDeclNode extends Node {
	public MemberVarDeclNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class MemberDeclNode extends Node {
	public MemberDeclNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class MemberDeclListNode extends Node {
	public MemberDeclListNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class InheritanceListTailNode extends Node {
	public InheritanceListTailNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class InheritanceListNode extends Node {
	public InheritanceListNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class ClassDeclNode extends Node {
	public ClassDeclNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class FuncHeadNode extends Node {
	public FuncHeadNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class AddOpNode extends Node {
	public AddOpNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class MultOpNode extends Node {
	public MultOpNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class RelOpNode extends Node {
	public RelOpNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class NotNode extends Node {
	public NotNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class SignNode extends Node {
	public SignNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class LocalVarDeclNode extends Node {
	public LocalVarDeclNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class RelExprNode extends Node {
	public RelExprNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class ExprNode extends Node {
	public ExprNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class ExprTailListNode extends Node {
	public ExprTailListNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class AParamsNode extends Node {
	public AParamsNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class WriteNode extends Node {
	public WriteNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class ReturnNode extends Node {
	public ReturnNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class StatementNode extends Node {
	public StatementNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class FuncBodyNode extends Node {
	public FuncBodyNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class FuncDefNode extends Node {
	public FuncDefNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class ProgNode extends Node {
	public ProgNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class ReadNode extends Node {
	public ReadNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class VariableNode extends Node {
	public VariableNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class IndiceNode extends Node {
	public IndiceNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class IdNestNode extends Node {
	public IdNestNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class IdNestTempNode extends Node {
	public IdNestTempNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class AssignStatNode extends Node {
	public AssignStatNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class FunctionCallNode extends Node {
	public FunctionCallNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class StatBlockNode extends Node {
	public StatBlockNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class IfNode extends Node {
	public IfNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}

class WhileNode extends Node {
	public WhileNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		this.findChildren();
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
}