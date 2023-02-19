import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class FirstFollowTable {
	public static void main(String args[]){
	      JFrame frame = new JFrame();
	      JTable table = new JTable(new MyFirstFollowTableModel());
	 
	      frame.add(table);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setSize(600,600);
	      frame.setLocationRelativeTo(null);  
	      frame.setVisible(true);
	   }
	
}

class MyFirstFollowTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	String[] columnNames = {"NONTERMINAL","FIRST","FOLLOW"};
	String[] rowNames = {"NONTERMINAL","START","Flsqbr","ASSIGNSTAT","CLASSDECL","EXPR2","FACTOR2","FACTOR3",
			"FACTOR4","FUNCDEF","FUNCBODY","FUNCHEAD","Ffunction","Fid2","Fsr","FFUNCid","FFUNCid2","FUNCTIONCALL",
			"Fid3","Flocalvar","Fid","Fcolon","FTYPE","LOCALVARDECL","MEMBERFUNCDECL","FPARAMS","MEMBERVARDECL",
			"OPTCLASSDECL2","ARITHEXPR","RELOP","APARAMSTAIL","REPTAPARAMS1","MEMBERDECL","REPTCLASSDECL4",
			"REPTFPARAMS3","FPARAMSTAIL","REPTFPARAMS4","REPTFPARAMSTAIL4","LOCALVARDECLORSTMT","REPTFUNCBODY1",
			"IDNEST","REPTFUNCTIONCALL0","REPTIDNEST1","REPTLOCALVARDECL4","ARRAYSIZE","REPTMEMBERVARDECL4",
			"REPTOPTCLASSDECL22","CLASSDECLORFUNCDEF","REPTSTART0","INDICE","RETURNTYPE","ADDOP","RIGHTRECARITHEXPR",
			"MULTOP","SIGN","REPTSTATBLOCK1","RELEXPR","STATBLOCK","STATEMENT2","STATEMENT3","STATEMENT4",
			"STATEMENT","ASSIGNOP","EXPR","TERM","FACTOR","RIGHTRECTERM","TYPE","FVARid","REPTVARIABLE2",
			"APARAMS","FVARid2","VARIABLE","VISIBILITY"};
	
	Object[][] data = {
			{"NONTERMINAL","FIRST","FOLLOW"},
			{"START","class function &epsilon","null"},
			{"Flsqbr","intlit rsqbr","lsqbr semi rpar comma"},
			{"ASSIGNSTAT","id","null"},
			{"CLASSDECL","class","class function"},
			{"EXPR2","eq neq lt gt leq geq &epsilon","comma rpar semi"},
			{"FACTOR2","lpar dot lsqbr &epsilon","rsqbr eq neq lt gt leq geq mult div and plus minus or comma rpar semi"},
			{"FACTOR3","dot &epsilon","rsqbr eq neq lt gt leq geq mult div and plus minus or comma rpar semi"},
			{"FACTOR4","dot &epsilon","rsqbr eq neq lt gt leq geq mult div and plus minus or comma rpar semi"},
			{"FUNCDEF","function","class function"},
			{"FUNCBODY","lcurbr","class function"},
			{"FUNCHEAD","function","lcurbr"},
			{"Ffunction","id","lcurbr"},
			{"Fid2","lpar sr","lcurbr"},
			{"Fsr","id constructor","lcurbr"},
			{"FFUNCid","lpar dot lsqbr","null"},
			{"FFUNCid2","dot &epsilon","null"},
			{"FUNCTIONCALL","id","null"},
			{"Fid3","dot lpar lsqbr","id"},
			{"Flocalvar","id","localvar if while read write return id rcurbr"},
			{"Fid","colon","localvar if while read write return id rcurbr"},
			{"Fcolon","integer float id","localvar if while read write return id rcurbr"},
			{"FTYPE","semi lpar lsqbr","localvar if while read write return id rcurbr"},
			{"LOCALVARDECL","localvar","localvar if while read write return id rcurbr"},
			{"MEMBERFUNCDECL","function constructor","public private function constructor attribute rcurbr"},
			{"FPARAMS","id &epsilon","rpar"},
			{"MEMBERVARDECL","attribute","public private function constructor attribute rcurbr"},
			{"OPTCLASSDECL2","isa &epsilon","lcurbr"},
			{"ARITHEXPR","intlit floatlit lpar not id plus minus","rsqbr eq neq lt gt leq geq comma rpar semi"},
			{"RELOP","eq neq lt gt leq geq","intlit floatlit lpar not id plus minus"},
			{"APARAMSTAIL","comma","comma rpar"},
			{"REPTAPARAMS1","comma &epsilon","rpar"},
			{"MEMBERDECL","function constructor attribute","public private function constructor attribute rcurbr"},
			{"REPTCLASSDECL4","public private function constructor attribute &epsilon","rcurbr"},
			{"REPTFPARAMS3","lsqbr &epsilon","rpar comma"},
			{"FPARAMSTAIL","comma","comma rpar"},
			{"REPTFPARAMS4","comma &epsilon","rpar"},
			{"REPTFPARAMSTAIL4","lsqbr &epsilon","comma rpar"},
			{"LOCALVARDECLORSTMT","localvar if while read write return id","localvar if while read write return id rcurbr"},
			{"REPTFUNCBODY1","localvar if while read write return id &epsilon","rcurbr"},
			{"IDNEST","id","id"},
			{"REPTFUNCTIONCALL0","id &epsilon","null"},
			{"REPTIDNEST1","lsqbr &epsilon","dot"},
			{"REPTLOCALVARDECL4","lsqbr &epsilon","semi"},
			{"ARRAYSIZE","lsqbr","lsqbr semi rpar comma"},
			{"REPTMEMBERVARDECL4","lsqbr &epsilon","semi"},
			{"REPTOPTCLASSDECL22","comma &epsilon","lcurbr"},
			{"CLASSDECLORFUNCDEF","class function","class function"},
			{"REPTSTART0","class function &epsilon","null"},
			{"INDICE","lsqbr","lsqbr dot rsqbr eq neq lt gt leq geq mult div and equal plus minus or comma rpar semi"},
			{"RETURNTYPE","void integer float id","semi lcurbr"},
			{"ADDOP","plus minus or","intlit floatlit lpar not id plus minus"},
			{"RIGHTRECARITHEXPR","plus minus or &epsilon","rsqbr eq neq lt gt leq geq comma rpar semi"},
			{"MULTOP","mult div and","intlit floatlit lpar not id plus minus"},
			{"SIGN","plus minus","intlit floatlit lpar not id plus minus"},
			{"REPTSTATBLOCK1","if while read write return id &epsilon","rcurbr"},
			{"RELEXPR","intlit floatlit lpar not id plus minus","rpar"},
			{"STATBLOCK","lcurbr if while read write return id &epsilon","else semi"},
			{"STATEMENT2","lpar dot semi lsqbr equal","else semi localvar if while read write return id rcurbr"},
			{"STATEMENT3","dot semi equal","else semi localvar if while read write return id rcurbr"},
			{"STATEMENT4","dot semi equal","else semi localvar if while read write return id rcurbr"},
			{"STATEMENT","if while read write return id","else semi localvar if while read write return id rcurbr"},
			{"ASSIGNOP","equal","intlit floatlit lpar not id plus minus"},
			{"EXPR","intlit floatlit lpar not id plus minus","comma rpar semi"},
			{"TERM","intlit floatlit lpar not id plus minus","rsqbr eq neq lt gt leq geq plus minus or comma rpar semi"},
			{"FACTOR","intlit floatlit lpar not id plus minus","rsqbr eq neq lt gt leq geq mult div and plus minus or comma rpar semi"},
			{"RIGHTRECTERM","mult div and &epsilon","rsqbr eq neq lt gt leq geq plus minus or comma rpar semi"},
			{"TYPE","integer float id","rpar lcurbr comma lpar lsqbr semi"},
			{"FVARid","lpar dot lsqbr &epsilon","equal rpar"},
			{"REPTVARIABLE2","lsqbr &epsilon","dot rsqbr eq neq lt gt leq geq mult div and equal plus minus or comma rpar semi"},
			{"APARAMS","intlit floatlit lpar not id plus minus &epsilon","rpar"},
			{"FVARid2","dot &epsilon","equal rpar"},
			{"VARIABLE","id","equal rpar"},
			{"VISIBILITY","public private &epsilon","function constructor attribute"}
	};
	
	public int getRowCount() {
    	return data.length;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int findColumn(String s) {
		for (int i=0; i < columnNames.length;i++) {
			if (s.equals(columnNames[i])) {
				return i;
		   	}
	   	}
	    return -1;
	}
	
	public int findRow(String s) {
		for (int i=0; i < rowNames.length;i++) {
			if (s.equals(rowNames[i])) {
				return i;
		   	}
	   	}
	    return -1;
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	public boolean isInFirst(Token token, String nonterminal) {
		int rowNum = findRow(nonterminal);
		boolean isInFirst = false;
		Object[] row = data[rowNum];
		String first = (String) row[1];
		String[] firstValues = first.split(" ");
		for (int i = 0; i < firstValues.length; i++) {
			if (firstValues[i].equals(token.getType())) {
				isInFirst = true;
				break;
			}
		}
		return isInFirst;
	}
	
	public boolean isInFollow(Token token, String nonterminal) {
		int rowNum = findRow(nonterminal);
		boolean isInFollow = false;
		Object[] row = data[rowNum];
		String follow = (String) row[2];
		String[] followValues = follow.split(" ");
		for (int i = 0; i < followValues.length; i++) {
			if (followValues[i].equals(token.getType())) {
				isInFollow = true;
				break;
			}
		}
		return isInFollow;
	}

}
