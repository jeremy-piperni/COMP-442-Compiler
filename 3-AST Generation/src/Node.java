
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
		while (xsibs.rightSibling != null) {
			xsibs = xsibs.rightSibling;
		}
		
		Node ysibs = y.leftMostSibling;
		xsibs.rightSibling = ysibs;
		
		ysibs.leftMostSibling = xsibs.leftMostSibling;
		ysibs.parent = xsibs.parent;
		while (ysibs.rightSibling != null) {
			ysibs = ysibs.rightSibling;
			ysibs.leftMostSibling = xsibs.leftMostSibling;
			ysibs.parent = xsibs.parent;
		}
		return ysibs;
	}
	
	public Node adoptChildren(Node y) {
		if (this.leftChild != null) {
			this.leftChild.makeSiblings(y);
		} else {
			Node ysibs;
			ysibs = y.leftMostSibling;
			this.leftChild = ysibs;
			while (ysibs != null) {
				ysibs.parent = this;
				ysibs = ysibs.rightSibling;
			}
		}
		return this;
	}
	
	public static void printSiblings(Node x) {
		Node now = x.leftMostSibling;
		System.out.print(now.lexeme + " ");
		while (now.rightSibling != null) {
			now = now.rightSibling;
			System.out.print(now.lexeme + " ");
		}
	}
	
	public static void printTree(Node x) {
		System.out.print(x);
		if (x.leftChild != null) {
			Node now = x.leftChild;
			System.out.println();
			System.out.print("|" + " ");
			while (now.rightSibling != null) {
				now = now.rightSibling;
				System.out.print("|" + " ");
			}
			System.out.println("");
			now = now.leftMostSibling;
			while (now != null) {
				printTree(now);
				System.out.print(" ");
				now = now.rightSibling;
			}
		}
	}
	
	private static int depthCounter = 0;
	public static void printTree2(Node x) {
		System.out.print(x);
		if (x.leftChild != null) {
			depthCounter++;
			Node now = x.leftChild;
			System.out.println();
			for (int i = 0; i < depthCounter; i++) {
				System.out.print("| ");
			}
			printTree2(now);
			depthCounter--;
		}
		if (x.rightSibling != null) {
			System.out.println();
			for (int i = 0; i < depthCounter; i++) {
				System.out.print("| ");
			}
			Node now2 = x.rightSibling;
			printTree2(now2);
		}
	}
	
	public String toString() {
		return lexeme;
	}

	public String getType() {
		return type;
	}
}
