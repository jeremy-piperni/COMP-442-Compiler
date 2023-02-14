import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class lexicalanalyzer {

	private BufferedReader reader;
	private FileWriter tokenWriter;
	private FileWriter errorWriter;
	private MyTableModel table = new MyTableModel();
	private String fileName;
	private int line = 1;
	private int lineCounter = 0;
	private final static ArrayList<String> reservedWords = new ArrayList<String>();
	
	public lexicalanalyzer(String file) {
		try {
			reader = new BufferedReader(new FileReader(file));
			populateReservedWords();
			fileName = file;
			String[] fileName1 = file.split("/");
			String[] fileName2 = fileName1[1].split("\\.");
			try {
				tokenWriter = new FileWriter("Generated Outlex Files/" + fileName2[0] + ".outlextokens");
				errorWriter = new FileWriter("Generated Outlex Files/" + fileName2[0] + ".outlexerrors");
			} catch (IOException e) {
				e.printStackTrace();
			}
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
					// check to see if lookup is e
					} else if (isE(lookup)) { 
						columnNumber = table.findColumn("e");
					// check to see if lookup is a letter (not e)
					} else if (isLetterNotE(lookup)) {
						columnNumber = table.findColumn("L");
					// check to see if lookup is nonzero
					} else if (isNonZero(lookup)) {
						columnNumber = table.findColumn("N");
					// check to see if lookup is invalid character
					} else {
						columnNumber = table.findColumn(Character.toString(lookup));
						if (columnNumber == -1) {
							columnNumber = table.findColumn("inv");
						}
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			// find next state of dfa
			state = (int) table.getValueAt(state, columnNumber);
			
			// special case, check if value after . in float is a number
			if (state == 40 || state == 41) {
				try {
					reader.mark(2);
					char c1 = nextChar();
					char c2 = nextChar();
					if ((int) c1 == 46 && !(isNonZero(c2) || (int) c2 == 48)) {
						state = 49;
					} else {
						
					}
					reader.reset();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			// check to see if state is final state
			Object finalValue = table.getValueAt(state, table.findColumn("final"));
			if (Objects.nonNull(finalValue)) {
				
				// if at end of file, return null
				if (finalValue.toString().equals("end")) {
					try {
						tokenWriter.close();
						errorWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return new Token("$","$",line);
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
				if (tokenName.equals("id")) {
					if (reservedWords.contains(lexeme)) {
						tokenName = lexeme;
					}
				}
				token = new Token(tokenName,lexeme,line);				
				try {
					tokenWriter.write(token.toString());
					tokenWriter.write(System.getProperty( "line.separator" ));
					if (token.getType().equals("invalidchar")) {
						errorWriter.write("Lexical error: Invalid character: \"" + token.getLexeme() +"\": line " + token.getLoc() + ".");
						errorWriter.write(System.getProperty( "line.separator" ));
					} else if (token.getType().equals("untermerr")) {
						errorWriter.write("Lexical error: Unterminated comment: \"" + token.getLexeme() +"\": line " + token.getLoc() + ".");
						errorWriter.write(System.getProperty( "line.separator" ));
					} else if (token.getType().equals("invalidnum")) {
						errorWriter.write("Lexical error: Invalid number: \"" + token.getLexeme() +"\": line " + token.getLoc() + ".");
						errorWriter.write(System.getProperty( "line.separator" ));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
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
		try {
			reader.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isE(char c) {
		if ((int) c == 101) {
			return true;
		}
		return false;
	}
	
	public boolean isLetterNotE(char c) {
		if (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122)) {
			return true;
		}
		return false;
	}
	
	public boolean isNonZero(char c) {
		if ((int) c >= 49 && (int) c <= 57) {
			return true;
		}
		return false;
	}
	
	public void populateReservedWords() {
		reservedWords.add("or");
		reservedWords.add("and");
		reservedWords.add("not");
		reservedWords.add("integer");
		reservedWords.add("float");
		reservedWords.add("void");
		reservedWords.add("class");
		reservedWords.add("self");
		reservedWords.add("isa");
		reservedWords.add("while");
		reservedWords.add("if");
		reservedWords.add("then");
		reservedWords.add("else");
		reservedWords.add("read");
		reservedWords.add("write");
		reservedWords.add("return");
		reservedWords.add("localvar");
		reservedWords.add("constructor");
		reservedWords.add("attribute");
		reservedWords.add("function");
		reservedWords.add("public");
		reservedWords.add("private");
	}

	public String getFileName() {
		return fileName;
	}
	
}
