
public class parser {
	private lexicalanalyzer lex;

	public parser(lexicalanalyzer lex) {
		this.lex = lex;
		
	}
	
	public boolean parse() {
		Token token;
		do {
			token = lex.nextToken();
		} while (token != null);
		return true;
	}
	
}
