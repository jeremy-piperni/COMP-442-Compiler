import java.io.BufferedReader;
import java.io.IOException;

public class lexicalanalyzer {

	public static Token nextToken(BufferedReader reader) {
		int state = 1;
		Token token = null;
		//do {
			try {
				char c = (char) reader.read();
				token = new Token(Character.toString(c),Character.toString(c),1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//} while(true);
		return token;
	}
	
}
