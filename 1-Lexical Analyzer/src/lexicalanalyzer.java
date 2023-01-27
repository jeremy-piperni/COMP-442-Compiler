import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class lexicalanalyzer {

	private BufferedReader reader;
	private MyTableModel table = new MyTableModel();
	private static int line = 1;
	private static int lineCounter = 0;
	
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
			int columnNumber = 0;
			char lookup = 0;
			try {
				// check for end of file
				reader.mark(2);
				if (checkForEnd()) {
					columnNumber = table.findColumn("endoffile");
					reader.reset();
					
				// check for new line
				} else if (checkForNewLine()) {
					columnNumber = table.findColumn("newline");
					lineCounter = 1;
					reader.mark(1);
					lookup = ' ';
				} else {
					reader.mark(1);
					lookup = nextChar();
					
					// check for whitespace
					if (Character.isWhitespace(lookup)) {
						columnNumber = table.findColumn("whitespace");
						} else {
						columnNumber = table.findColumn(Character.toString(lookup));
						if (columnNumber == -1) {
							token = new Token("Error","Error",line);
							System.out.println(token);
							return token;
						}
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			// find next state of dfa
			state = (int) table.getValueAt(state, columnNumber);

			// check to see if state is final state
			Object finalValue = table.getValueAt(state, table.findColumn("final"));
			if (Objects.nonNull(finalValue)) {
				
				// if at end of file, return null
				if (finalValue.toString().equals("end")) {
					return null;
				}
				
				// update lexeme if not white space
				if (!Character.isWhitespace(lookup)) {
					lexeme = lexeme + lookup;
				}
				
				// check to see if we must backtrack, delete last character of lexeme
				Object backtrack = table.getValueAt(state, table.findColumn("backtrack"));
				if (Objects.nonNull(backtrack)) {
					if (!Character.isWhitespace(lookup)) {
						lexeme = (lexeme.substring(0, lexeme.length() - 1));
					}
					try {
						reader.reset();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				// returns token
				String tokenName = (String) finalValue;
				token = new Token(tokenName,lexeme,line);				
				System.out.println(token);
			} else {
				
				// update lexeme when not in final state
				if (!Character.isWhitespace(lookup)) {
					lexeme = lexeme + lookup;
				}
			}
			line = line + lineCounter;
			lineCounter = 0;
			
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
		try {
			reader.reset();
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
