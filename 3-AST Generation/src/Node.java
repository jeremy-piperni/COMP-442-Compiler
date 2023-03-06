
public class Node {
	private String type;
	private String lexeme;
	private Node parent;
	private Node leftMostSibling;
	private Node rightSibling;
	private Node leftChild;
	
	public Node() {
		type = null;
		lexeme = null;
	}
	
	public Node(String type, String lexeme) {
		this.type = type;
		this.lexeme = lexeme;
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
		return type;
	}

	public String getType() {
		return type;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public Node getRightSibling() {
		return rightSibling;
	}
}

/*public class ArraySizeNode extends Node {
	public void accept(Visitor v) {
		v.arraySize();
	}
}*/
