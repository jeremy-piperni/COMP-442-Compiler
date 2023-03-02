
public class ASTdriver {

	public static void main(String[] args) {
		
		String file = "Testing Source Files/example-bubblesort.src";
		lexicalanalyzer lexAnalyzer = new lexicalanalyzer(file);
		
		parser parser = new parser(lexAnalyzer);
		parser.parse();
		
		file = "Testing Source Files/example-polynomial.src";
		lexAnalyzer = new lexicalanalyzer(file);
		
		parser = new parser(lexAnalyzer);
		parser.parse();
		
		
	}

}
