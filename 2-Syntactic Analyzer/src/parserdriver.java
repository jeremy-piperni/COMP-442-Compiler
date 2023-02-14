
public class parserdriver {

	public static void main(String[] args) {
		
		String file = "Testing Source Files/testing.src";
		lexicalanalyzer lexAnalyzer = new lexicalanalyzer(file);

		parser parserBubble = new parser(lexAnalyzer);
		parserBubble.parse();
		
	}

}
