import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class lexicalanalyzer {

	private BufferedReader reader;
	private MyTableModel table = new MyTableModel();
	private static int line = 1;
	
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
		String lexeme = "";
		do {
			
			// Check for end of file and new line
			try {
				reader.mark(1);
				boolean isDone = checkForEnd();
				if (isDone) {
					return token;
				}
				reader.reset();
				
				boolean isNewLine = checkForNewLine();
				if (isNewLine) {
					line = line + 1;
				} else {
					reader.reset();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			char lookup = nextChar();
			int columnNumber = 0;
			
			if (Character.isWhitespace(lookup)) {
				columnNumber = table.findColumn("whitespace");
				} else {
				columnNumber = table.findColumn(Character.toString(lookup));
				lexeme = lexeme + lookup;
				if (columnNumber == -1) {
					token = new Token("Error","Error",line);
					System.out.println(token);
					return token;
				}
			}
			
			state = (int) table.getValueAt(state, columnNumber);
			
			Object finalValue = table.getValueAt(state, table.findColumn("final"));
			if (Objects.nonNull(finalValue)) {
				String tokenName = (String) finalValue;
				token = new Token(tokenName,lexeme,line);
				System.out.println(token);
			}
		} while (token == null);
			
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
	
	public boolean checkForNewLine() {
		try {
			int c = 0;
			if ((c = reader.read()) == 10) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
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
