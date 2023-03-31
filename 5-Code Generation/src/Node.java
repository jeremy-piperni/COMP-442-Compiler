import java.util.ArrayList;

public class Node {
	private Token token;
	private Node parent;
	private Node leftMostSibling;
	private Node rightSibling;
	private Node leftChild;
	private ArrayList<Node> children = new ArrayList<>();
	private SymbolTable symTable;
	private SymbolTableEntry symEntry;
	private String expressionType;
	private String moonVarName;
	private int moonSize;
	
	public Node() {
		token = new Token(null,null,0);
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public void accept2(Visitor visitor) {
		visitor.visit(this);
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
	
	public String getLexeme() {
		return token.getLexeme();
	}
	
	public int getLoc() {
		return token.getLoc();
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public Node getRightSibling() {
		return rightSibling;
	}
	
	public Node getParent() {
		return parent;
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

	public SymbolTable getSymbolTable() {
		return symTable;
	}
	
	public void setSymTable(SymbolTable symTable) {
		this.symTable = symTable;
	}

	public SymbolTableEntry getSymEntry() {
		return symEntry;
	}

	public void setSymEntry(SymbolTableEntry symEntry) {
		this.symEntry = symEntry;
	}
	
	public void setExpressionType(String type) {
		expressionType = type;
	}
	
	public String getExpressionType() {
		return expressionType;
	}

	public String getMoonVarName() {
		return moonVarName;
	}

	public void setMoonVarName(String moonVarName) {
		this.moonVarName = moonVarName;
	}

	public int getMoonSize() {
		return moonSize;
	}

	public void setMoonSize(int moonSize) {
		this.moonSize = moonSize;
	}
}

class EpsilonNode extends Node {
	public EpsilonNode() {
		super();
	}
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class ArraySizeNode extends Node {
	public ArraySizeNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class EmptyArraySizeNode extends Node {
	public EmptyArraySizeNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class FParamsTailNode extends Node {
	public FParamsTailNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class FParamsTailListNode extends Node {
	public FParamsTailListNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class FParamsNode extends Node {
	public FParamsNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class MemberFuncDeclNode extends Node {
	public MemberFuncDeclNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class MemberVarDeclNode extends Node {
	public MemberVarDeclNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class MemberDeclNode extends Node {
	public MemberDeclNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class MemberDeclListNode extends Node {
	public MemberDeclListNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class InheritanceListTailNode extends Node {
	public InheritanceListTailNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class InheritanceListNode extends Node {
	public InheritanceListNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class ClassDeclNode extends Node {
	public ClassDeclNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class FuncHeadNode extends Node {
	public FuncHeadNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class AddOpNode extends Node {
	public AddOpNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class MultOpNode extends Node {
	public MultOpNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class RelOpNode extends Node {
	public RelOpNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class NotNode extends Node {
	public NotNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class SignNode extends Node {
	public SignNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class LocalVarDeclNode extends Node {
	public LocalVarDeclNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class RelExprNode extends Node {
	public RelExprNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class ExprNode extends Node {
	public ExprNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class ExprTailListNode extends Node {
	public ExprTailListNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class AParamsNode extends Node {
	public AParamsNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class WriteNode extends Node {
	public WriteNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class ReturnNode extends Node {
	public ReturnNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class StatementNode extends Node {
	public StatementNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class FuncBodyNode extends Node {
	public FuncBodyNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class FuncDefNode extends Node {
	public FuncDefNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class ProgNode extends Node {
	public ProgNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class ReadNode extends Node {
	public ReadNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class VariableNode extends Node {
	public VariableNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class IndiceNode extends Node {
	public IndiceNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class IdNestNode extends Node {
	public IdNestNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class IdNestTempNode extends Node {
	public IdNestTempNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class AssignStatNode extends Node {
	public AssignStatNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class FunctionCallNode extends Node {
	public FunctionCallNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class StatBlockNode extends Node {
	public StatBlockNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class IfNode extends Node {
	public IfNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}

class WhileNode extends Node {
	public WhileNode(Token token) {
		super(token);
	}
	public void accept(Visitor visitor) {
		ArrayList<Node> childList = this.getChildren();
		visitor.visit(this);
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept(visitor);
		}
	}
	public void accept2(Visitor visitor) {
		visitor.visit(this);
	}
}