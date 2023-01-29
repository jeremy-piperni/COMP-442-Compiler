
public class lexdriver {
	
	public static void main(String[] args) {
		
		String file = "Testing Source Files/lexpositivegrading.src";
		lexicalanalyzer lexAnalyzer = new lexicalanalyzer(file);
		//ArrayList<Token> tokenList = new ArrayList<Token>();
		
		// run lexer on lexpositivegrading
		Token token;
		do {
			token = lexAnalyzer.nextToken();
			//tokenList.add(token);
		} while (token != null);
		
		// run lexer on lexnegativegrading
		file = "Testing Source Files/lexnegativegrading.src";
		lexAnalyzer = new lexicalanalyzer(file);
		do {
			token = lexAnalyzer.nextToken();
		} while (token != null);
		
		// run lexer on example-bubblesort
		file = "Testing Source Files/example-bubblesort.src";
		lexAnalyzer = new lexicalanalyzer(file);
		do {
			token = lexAnalyzer.nextToken();
		} while (token != null);
		
		// run lexer on example-polynomial
		file = "Testing Source Files/example-polynomial.src";
		lexAnalyzer = new lexicalanalyzer(file);
		do {
			token = lexAnalyzer.nextToken();
		} while (token != null);
		
		// run lexer on testing
		file = "Testing Source Files/testing.src";
		lexAnalyzer = new lexicalanalyzer(file);
		do {
			token = lexAnalyzer.nextToken();
		} while (token != null);
		
	}

}
