
public class Node {
	private Token token;
	private Node parent;
	private Node leftMostSibling;
	private Node rightSibling;
	private Node leftChild;
	
	public Node() {
		token = new Token(null,null,0);
	}
	
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

	public Node getLeftChild() {
		return leftChild;
	}

	public Node getRightSibling() {
		return rightSibling;
	}
}

class EpsilonNode extends Node {
	public EpsilonNode() {
		super();
	}
}

class ArraySizeNode extends Node {
	public ArraySizeNode(Token token) {
		super(token);
	}
}

class EmptyArraySizeNode extends Node {
	public EmptyArraySizeNode(Token token) {
		super(token);
	}
}

class FParamsTailNode extends Node {
	public FParamsTailNode(Token token) {
		super(token);
	}
}

class FParamsTailListNode extends Node {
	public FParamsTailListNode(Token token) {
		super(token);
	}
}

class FParamsNode extends Node {
	public FParamsNode(Token token) {
		super(token);
	}
}

class MemberFuncDeclNode extends Node {
	public MemberFuncDeclNode(Token token) {
		super(token);
	}
}

class MemberVarDeclNode extends Node {
	public MemberVarDeclNode(Token token) {
		super(token);
	}
}

class MemberDeclNode extends Node {
	public MemberDeclNode(Token token) {
		super(token);
	}
}

class MemberDeclListNode extends Node {
	public MemberDeclListNode(Token token) {
		super(token);
	}
}

class InheritanceListTailNode extends Node {
	public InheritanceListTailNode(Token token) {
		super(token);
	}
}

class InheritanceListNode extends Node {
	public InheritanceListNode(Token token) {
		super(token);
	}
}

class ClassDeclNode extends Node {
	public ClassDeclNode(Token token) {
		super(token);
	}
}

class FuncHeadNode extends Node {
	public FuncHeadNode(Token token) {
		super(token);
	}
}

class AddOpNode extends Node {
	public AddOpNode(Token token) {
		super(token);
	}
}

class MultOpNode extends Node {
	public MultOpNode(Token token) {
		super(token);
	}
}

class RelOpNode extends Node {
	public RelOpNode(Token token) {
		super(token);
	}
}

class NotNode extends Node {
	public NotNode(Token token) {
		super(token);
	}
}

class SignNode extends Node {
	public SignNode(Token token) {
		super(token);
	}
}

class LocalVarDeclNode extends Node {
	public LocalVarDeclNode(Token token) {
		super(token);
	}
}

class RelExprNode extends Node {
	public RelExprNode(Token token) {
		super(token);
	}
}

class ExprNode extends Node {
	public ExprNode(Token token) {
		super(token);
	}
}

class ExprTailListNode extends Node {
	public ExprTailListNode(Token token) {
		super(token);
	}
}

class AParamsNode extends Node {
	public AParamsNode(Token token) {
		super(token);
	}
}

class WriteNode extends Node {
	public WriteNode(Token token) {
		super(token);
	}
}

class ReturnNode extends Node {
	public ReturnNode(Token token) {
		super(token);
	}
}

class StatementNode extends Node {
	public StatementNode(Token token) {
		super(token);
	}
}

class FuncBodyNode extends Node {
	public FuncBodyNode(Token token) {
		super(token);
	}
}

class FuncDefNode extends Node {
	public FuncDefNode(Token token) {
		super(token);
	}
}

class ProgNode extends Node {
	public ProgNode(Token token) {
		super(token);
	}
}

class ReadNode extends Node {
	public ReadNode(Token token) {
		super(token);
	}
}

class VariableNode extends Node {
	public VariableNode(Token token) {
		super(token);
	}
}

class IndiceNode extends Node {
	public IndiceNode(Token token) {
		super(token);
	}
}

class IdNestNode extends Node {
	public IdNestNode(Token token) {
		super(token);
	}
}

class IdNestTempNode extends Node {
	public IdNestTempNode(Token token) {
		super(token);
	}
}

class AssignStatNode extends Node {
	public AssignStatNode(Token token) {
		super(token);
	}
}

class FunctionCallNode extends Node {
	public FunctionCallNode(Token token) {
		super(token);
	}
}

class StatBlockNode extends Node {
	public StatBlockNode(Token token) {
		super(token);
	}
}

class IfNode extends Node {
	public IfNode(Token token) {
		super(token);
	}
}

class WhileNode extends Node {
	public WhileNode(Token token) {
		super(token);
	}
}