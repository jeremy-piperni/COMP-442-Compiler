import java.util.ArrayList;

public class lexdriver {
	
	public static void main(String[] args) {
		
		String file = "Testing Source Files/testing.src";
		lexicalanalyzer lexAnalyzer = new lexicalanalyzer(file);
		ArrayList<Token> tokenList = new ArrayList<Token>();
		
		Token token;
		do {
			token = lexAnalyzer.nextToken();
			tokenList.add(token);
		} while (token != null);
	}

}
