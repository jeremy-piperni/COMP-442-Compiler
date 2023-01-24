
public class Token {
	private String type;
	private String lexeme;
	private int loc;

	public Token(String type, String lexeme, int loc) {
		this.setType(type);
		this.setLexeme(lexeme);
		this.setLoc(loc);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLexeme() {
		return lexeme;
	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}
	
	public String toString() {
		return "[" + getType() + ", " + getLexeme() + ", " + getLoc() + "]";
	}
}
