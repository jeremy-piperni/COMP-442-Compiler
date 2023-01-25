import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class lexicalanalyzer {

	private BufferedReader reader;
	
	public lexicalanalyzer(String file) {
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Token nextToken() {
		int state = 1;
		Token token = null;
		do {
			
			try {
				reader.mark(100);
				boolean isDone = checkForEnd();
				if (isDone) {
					return token;
				}
				reader.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			char lookup = nextChar();
			token = new Token(Character.toString(lookup),Character.toString(lookup),1);
			System.out.println(token);
		} while (token != null);
			
		return token;
	}
	
	public char nextChar() {
		char c = 0;
		try {
			c = (char) reader.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public boolean checkForEnd() {	
		try {
			int c = 0;
			if ((c = reader.read()) == -1) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
