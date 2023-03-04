
public class ASTdriver {

	public static void main(String[] args) {
		
		String file = "Testing Source Files/testing.src";
		lexicalanalyzer lexAnalyzer = new lexicalanalyzer(file);
		
		parser parser = new parser(lexAnalyzer);
		parser.parse();
		
		/*
		String file = "Testing Source Files/example-bubblesort.src";
		lexicalanalyzer lexAnalyzer = new lexicalanalyzer(file);
		
		parser parser = new parser(lexAnalyzer);
		parser.parse();
		
		file = "Testing Source Files/example-polynomial.src";
		lexAnalyzer = new lexicalanalyzer(file);
		
		parser = new parser(lexAnalyzer);
		parser.parse();*/
		
		/*
		Node x = new Node("id","id");
		Node y = new Node("intLit","2");
		Node z = new Node("floatlit","3.9");
		Node zz = new Node("id","yay");
		AST AST = new AST();
		AST.makeFamily(z,x,y);
		x.makeSiblings(zz);
		//Node.printSiblings(y);
		Node.printTree(z);*/
	}

}
