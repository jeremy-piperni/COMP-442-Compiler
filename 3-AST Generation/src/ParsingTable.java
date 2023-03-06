import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ParsingTable {
	public static void main(String args[]){
	      JFrame frame = new JFrame();
	      JTable table = new JTable(new MyParsingTableModel());
	 
	      frame.add(table);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setSize(600,600);
	      frame.setLocationRelativeTo(null);  
	      frame.setVisible(true);
	   }
	
}
	
class MyParsingTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	String[] columnNames = {"name","$","private","public","dot","rpar","lpar","id","float","integer","semi","return","write","read","while","else","then","if","rcurbr","lcurbr","minus","plus","void","comma","geq","leq","gt","lt","neq","eq","isa","and","div","mult","colon","attribute","constructor","arrow","function","localvar","rsqbr","lsqbr","sr","not","floatlit","intlit","class","equal","or"};
	String[] rowNames = {"name","START","ADDOP","APARAMS","APARAMSTAIL","ARITHEXPR","ARRAYSIZE","Flsqbr","ASSIGNOP","ASSIGNSTAT","CLASSDECL","CLASSDECLORFUNCDEF","EXPR","EXPR2","FACTOR","FACTOR1","FACTOR2","FACTOR3","FACTOR4","FPARAMS","FPARAMSTAIL","FUNCBODY","FUNCDEF","FUNCHEAD","Ffunction","Fid2","Fsr","FUNCTIONCALL","FFUNCid","FFUNCid2","IDNEST","Fid3","INDICE","LOCALVARDECL","Flocalvar","Fid","Fcolon","FTYPE","LOCALVARDECLORSTMT","MEMBERDECL","MEMBERFUNCDECL","MEMBERVARDECL","MULTOP","OPTCLASSDECL2","RELEXPR","RELOP","REPTAPARAMS1","REPTCLASSDECL4","REPTFPARAMS3","REPTFPARAMS4","REPTFPARAMSTAIL4","REPTFUNCBODY1","REPTFUNCTIONCALL0","REPTIDNEST1","REPTLOCALVARDECL4","REPTMEMBERVARDECL4","REPTOPTCLASSDECL22","REPTSTART0","REPTSTATBLOCK1","REPTVARIABLE2","RETURNTYPE","RIGHTRECARITHEXPR","RIGHTRECTERM","SIGN","STATBLOCK","STATEMENT","STATEMENT1","STATEMENT2","STATEMENT3","STATEMENT4","TERM","TYPE","VARIABLE","FVARid","FVARid2","VISIBILITY"};
	
	Object[][] data = {
			{"name","$","private","public","dot","rpar","lpar","id","float","integer","semi","return","write","read","while","else","then","if","rcurbr","lcurbr","minus","plus","void","comma","geq","leq","gt","lt","neq","eq","isa","and","div","mult","colon","attribute","constructor","arrow","function","localvar","rsqbr","lsqbr","sr","not","floatlit","intlit","class","equal","or"},
			{"START","#B REPTSTART0 #AK",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"#B REPTSTART0 #AK",0,0,0,0,0,0,0,"#B REPTSTART0 #AK",0,0},
			{"ADDOP",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"minus #P","plus #P",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"or #P"},
			{"APARAMS",0,0,0,0,"#AC2 &epsilon","EXPR #AA #B REPTAPARAMS1 #AB #AC","EXPR #AA #B REPTAPARAMS1 #AB #AC",0,0,0,0,0,0,0,0,0,0,0,0,"EXPR #AA #B REPTAPARAMS1 #AB #AC","EXPR #AA #B REPTAPARAMS1 #AB #AC",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"EXPR #AA #B REPTAPARAMS1 #AB #AC","EXPR #AA #B REPTAPARAMS1 #AB #AC","EXPR #AA #B REPTAPARAMS1 #AB #AC",0,0,0},
			{"APARAMSTAIL",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"comma EXPR #AA",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"ARITHEXPR",0,0,0,0,0,"TERM RIGHTRECARITHEXPR","TERM RIGHTRECARITHEXPR",0,0,0,0,0,0,0,0,0,0,0,0,"TERM RIGHTRECARITHEXPR","TERM RIGHTRECARITHEXPR",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"TERM RIGHTRECARITHEXPR","TERM RIGHTRECARITHEXPR","TERM RIGHTRECARITHEXPR",0,0,0},
			{"ARRAYSIZE",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"lsqbr Flsqbr",0,0,0,0,0,0,0},
			{"Flsqbr",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"rsqbr #BA",0,0,0,0,"intlit #A rsqbr",0,0,0},
			{"ASSIGNOP",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"equal",0},
			{"ASSIGNSTAT",0,0,0,0,0,0,"VARIABLE ASSIGNOP EXPR",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"CLASSDECL",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"class id #A #B OPTCLASSDECL2 #M lcurbr #B REPTCLASSDECL4 #K rcurbr semi #N",0,0},
			{"CLASSDECLORFUNCDEF",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"FUNCDEF",0,0,0,0,0,0,0,"CLASSDECL",0,0},
			{"EXPR",0,0,0,0,0,"FACTOR RIGHTRECTERM RIGHTRECARITHEXPR EXPR2","FACTOR RIGHTRECTERM RIGHTRECARITHEXPR EXPR2",0,0,0,0,0,0,0,0,0,0,0,0,"FACTOR RIGHTRECTERM RIGHTRECARITHEXPR EXPR2","FACTOR RIGHTRECTERM RIGHTRECARITHEXPR EXPR2",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"FACTOR RIGHTRECTERM RIGHTRECARITHEXPR EXPR2","FACTOR RIGHTRECTERM RIGHTRECARITHEXPR EXPR2","FACTOR RIGHTRECTERM RIGHTRECARITHEXPR EXPR2",0,0,0},
			{"EXPR2",0,0,0,0,"&epsilon",0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,"&epsilon","RELOP ARITHEXPR #Z","RELOP ARITHEXPR #Z","RELOP ARITHEXPR #Z","RELOP ARITHEXPR #Z","RELOP ARITHEXPR #Z","RELOP ARITHEXPR #Z",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"FACTOR",0,0,0,0,0,"lpar ARITHEXPR rpar","#B FACTOR1",0,0,0,0,0,0,0,0,0,0,0,0,"SIGN FACTOR #W","SIGN FACTOR #W",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"not #S FACTOR #V","floatlit #A","intlit #A",0,0,0},
			{"FACTOR1",0,0,0,0,0,0,"id #A FACTOR2",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"FACTOR2",0,0,0,"#B REPTVARIABLE2 #AO FACTOR3","#B REPTVARIABLE2 #AO FACTOR3","lpar APARAMS rpar FACTOR4",0,0,0,"#B REPTVARIABLE2 #AO FACTOR3",0,0,0,0,0,0,0,0,0,"#B REPTVARIABLE2 #AO FACTOR3","#B REPTVARIABLE2 #AO FACTOR3",0,"#B REPTVARIABLE2 #AO FACTOR3","#B REPTVARIABLE2 #AO FACTOR3","#B REPTVARIABLE2 #AO FACTOR3","#B REPTVARIABLE2 #AO FACTOR3","#B REPTVARIABLE2 #AO FACTOR3","#B REPTVARIABLE2 #AO FACTOR3","#B REPTVARIABLE2 #AO FACTOR3",0,"#B REPTVARIABLE2 #AO FACTOR3","#B REPTVARIABLE2 #AO FACTOR3","#B REPTVARIABLE2 #AO FACTOR3",0,0,0,0,0,0,"#B REPTVARIABLE2 #AO FACTOR3","#B REPTVARIABLE2 #AO FACTOR3",0,0,0,0,0,0,"#B REPTVARIABLE2 #AO FACTOR3"},
			{"FACTOR3",0,0,0,"dot #AQ FACTOR1","#AM #AR &epsilon",0,0,0,0,"#AM #AR &epsilon",0,0,0,0,0,0,0,0,0,"#AM #AR &epsilon","#AM #AR &epsilon",0,"#AM #AR &epsilon","#AM #AR &epsilon","#AM #AR &epsilon","#AM #AR &epsilon","#AM #AR &epsilon","#AM #AR &epsilon","#AM #AR &epsilon",0,"#AM #AR &epsilon","#AM #AR &epsilon","#AM #AR &epsilon",0,0,0,0,0,0,"#AM #AR &epsilon",0,0,0,0,0,0,0,"#AM #AR &epsilon"},
			{"FACTOR4",0,0,0,"dot #AQ FACTOR1","#AM #AT &epsilon",0,0,0,0,"#AM #AT &epsilon",0,0,0,0,0,0,0,0,0,"#AM #AT &epsilon","#AM #AT &epsilon",0,"#AM #AT &epsilon","#AM #AT &epsilon","#AM #AT &epsilon","#AM #AT &epsilon","#AM #AT &epsilon","#AM #AT &epsilon","#AM #AT &epsilon",0,"#AM #AT &epsilon","#AM #AT &epsilon","#AM #AT &epsilon",0,0,0,0,0,0,"#AM #AT &epsilon",0,0,0,0,0,0,0,"#AM #AT &epsilon"},
			{"FPARAMS",0,0,0,0,"#AZ &epsilon",0,"id #A colon TYPE #G #B REPTFPARAMS3 #C #B REPTFPARAMS4 #E #F",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"FPARAMSTAIL",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"comma id #A colon TYPE #G #B REPTFPARAMSTAIL4 #C #D",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"FUNCBODY",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"lcurbr #B REPTFUNCBODY1 #AI rcurbr",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"FUNCDEF",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"FUNCHEAD FUNCBODY #AJ",0,0,0,0,0,0,0,0,0,0},
			{"FUNCHEAD",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"function Ffunction",0,0,0,0,0,0,0,0,0,0},
			{"Ffunction",0,0,0,0,0,0,"id #A Fid2",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"Fid2",0,0,0,0,0,"lpar FPARAMS rpar arrow RETURNTYPE #G #O1",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"sr Fsr",0,0,0,0,0,0},
			{"Fsr",0,0,0,0,0,0,"id #A lpar FPARAMS rpar arrow RETURNTYPE #G #O2",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"constructor lpar FPARAMS rpar #O3",0,0,0,0,0,0,0,0,0,0,0,0},
			{"FUNCTIONCALL",0,0,0,0,0,0,"id FFUNCid",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"FFUNCid",0,0,0,"REPTIDNEST1 dot FUNCTIONCALL",0,"lpar APARAMS rpar FFUNCid2",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"REPTIDNEST1 dot FUNCTIONCALL",0,0,0,0,0,0,0},
			{"FFUNCid2",0,0,0,"dot FUNCTIONCALL",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"IDNEST",0,0,0,0,0,0,"id Fid3",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"Fid3",0,0,0,"REPTIDNEST1 dot",0,"lpar APARAMS rpar dot",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"REPTIDNEST1 dot",0,0,0,0,0,0,0},
			{"INDICE",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"lsqbr ARITHEXPR rsqbr",0,0,0,0,0,0,0},
			{"LOCALVARDECL",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"localvar Flocalvar",0,0,0,0,0,0,0,0,0},
			{"Flocalvar",0,0,0,0,0,0,"id #A Fid",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"Fid",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"colon Fcolon",0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"Fcolon",0,0,0,0,0,0,"TYPE #G FTYPE","TYPE #G FTYPE","TYPE #G FTYPE",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"FTYPE",0,0,0,0,0,"lpar APARAMS rpar semi #U",0,0,0,"#B REPTLOCALVARDECL4 #C semi #U",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"#B REPTLOCALVARDECL4 #C semi #U",0,0,0,0,0,0,0},
			{"LOCALVARDECLORSTMT",0,0,0,0,0,0,"STATEMENT",0,0,0,"STATEMENT","STATEMENT","STATEMENT","STATEMENT",0,0,"STATEMENT",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"LOCALVARDECL",0,0,0,0,0,0,0,0,0},
			{"MEMBERDECL",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"MEMBERVARDECL","MEMBERFUNCDECL",0,"MEMBERFUNCDECL",0,0,0,0,0,0,0,0,0,0},
			{"MEMBERFUNCDECL",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"constructor colon lpar FPARAMS rpar semi #H2",0,"function id #A colon lpar FPARAMS rpar arrow RETURNTYPE #G semi #H1",0,0,0,0,0,0,0,0,0,0},
			{"MEMBERVARDECL",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"attribute id #A colon TYPE #G #B REPTMEMBERVARDECL4 #C semi #I",0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"MULTOP",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"and #Q","div #Q","mult #Q",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"OPTCLASSDECL2",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,"isa id #A #B REPTOPTCLASSDECL22 #L",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"RELEXPR",0,0,0,0,0,"ARITHEXPR RELOP ARITHEXPR #Z","ARITHEXPR RELOP ARITHEXPR #Z",0,0,0,0,0,0,0,0,0,0,0,0,"ARITHEXPR RELOP ARITHEXPR #Z","ARITHEXPR RELOP ARITHEXPR #Z",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"ARITHEXPR RELOP ARITHEXPR #Z","ARITHEXPR RELOP ARITHEXPR #Z","ARITHEXPR RELOP ARITHEXPR #Z",0,0,0},
			{"RELOP",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"geq #R","leq #R","gt #R","lt #R","neq #R","eq #R",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"REPTAPARAMS1",0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"APARAMSTAIL REPTAPARAMS1",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"REPTCLASSDECL4",0,"VISIBILITY MEMBERDECL #J1 REPTCLASSDECL4","VISIBILITY MEMBERDECL #J1 REPTCLASSDECL4",0,0,0,0,0,0,0,0,0,0,0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"VISIBILITY MEMBERDECL #J2 REPTCLASSDECL4","VISIBILITY MEMBERDECL #J2 REPTCLASSDECL4",0,"VISIBILITY MEMBERDECL #J2 REPTCLASSDECL4",0,0,0,0,0,0,0,0,0,0},
			{"REPTFPARAMS3",0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"ARRAYSIZE REPTFPARAMS3",0,0,0,0,0,0,0},
			{"REPTFPARAMS4",0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"FPARAMSTAIL REPTFPARAMS4",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"REPTFPARAMSTAIL4",0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"ARRAYSIZE REPTFPARAMSTAIL4",0,0,0,0,0,0,0},
			{"REPTFUNCBODY1",0,0,0,0,0,0,"LOCALVARDECLORSTMT REPTFUNCBODY1",0,0,0,"LOCALVARDECLORSTMT REPTFUNCBODY1","LOCALVARDECLORSTMT REPTFUNCBODY1","LOCALVARDECLORSTMT REPTFUNCBODY1","LOCALVARDECLORSTMT REPTFUNCBODY1",0,0,"LOCALVARDECLORSTMT REPTFUNCBODY1","&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"LOCALVARDECLORSTMT REPTFUNCBODY1",0,0,0,0,0,0,0,0,0},
			{"REPTFUNCTIONCALL0",0,0,0,0,0,0,"IDNEST REPTFUNCTIONCALL0",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"REPTIDNEST1",0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"INDICE REPTIDNEST1",0,0,0,0,0,0,0},
			{"REPTLOCALVARDECL4",0,0,0,0,0,0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"ARRAYSIZE REPTLOCALVARDECL4",0,0,0,0,0,0,0},
			{"REPTMEMBERVARDECL4",0,0,0,0,0,0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"ARRAYSIZE REPTMEMBERVARDECL4",0,0,0,0,0,0,0},
			{"REPTOPTCLASSDECL22",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"&epsilon",0,0,0,"comma id #A REPTOPTCLASSDECL22",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"REPTSTART0","&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"CLASSDECLORFUNCDEF REPTSTART0",0,0,0,0,0,0,0,"CLASSDECLORFUNCDEF REPTSTART0",0,0},
			{"REPTSTATBLOCK1",0,0,0,0,0,0,"STATEMENT REPTSTATBLOCK1",0,0,0,"STATEMENT REPTSTATBLOCK1","STATEMENT REPTSTATBLOCK1","STATEMENT REPTSTATBLOCK1","STATEMENT REPTSTATBLOCK1",0,0,"STATEMENT REPTSTATBLOCK1","&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"REPTVARIABLE2",0,0,0,"&epsilon","&epsilon",0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,"&epsilon","&epsilon",0,"&epsilon","&epsilon","&epsilon","&epsilon","&epsilon","&epsilon","&epsilon",0,"&epsilon","&epsilon","&epsilon",0,0,0,0,0,0,"&epsilon","INDICE REPTVARIABLE2",0,0,0,0,0,"&epsilon","&epsilon"},
			{"RETURNTYPE",0,0,0,0,0,0,"TYPE","TYPE","TYPE",0,0,0,0,0,0,0,0,0,0,0,0,"void",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"RIGHTRECARITHEXPR",0,0,0,0,"&epsilon",0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,"ADDOP TERM #Y RIGHTRECARITHEXPR","ADDOP TERM #Y RIGHTRECARITHEXPR",0,"&epsilon","&epsilon","&epsilon","&epsilon","&epsilon","&epsilon","&epsilon",0,0,0,0,0,0,0,0,0,0,"&epsilon",0,0,0,0,0,0,0,"ADDOP TERM #Y RIGHTRECARITHEXPR"},
			{"RIGHTRECTERM",0,0,0,0,"&epsilon",0,0,0,0,"&epsilon",0,0,0,0,0,0,0,0,0,"&epsilon","&epsilon",0,"&epsilon","&epsilon","&epsilon","&epsilon","&epsilon","&epsilon","&epsilon",0,"MULTOP FACTOR #X RIGHTRECTERM","MULTOP FACTOR #X RIGHTRECTERM","MULTOP FACTOR #X RIGHTRECTERM",0,0,0,0,0,0,"&epsilon",0,0,0,0,0,0,0,"&epsilon"},
			{"SIGN",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"minus #T","plus #T",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"STATBLOCK",0,0,0,0,0,0,"STATEMENT",0,0,"#AH2 &epsilon","STATEMENT","STATEMENT","STATEMENT","STATEMENT","#AH2 &epsilon",0,"STATEMENT",0,"lcurbr #B REPTSTATBLOCK1 #AU rcurbr",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"STATEMENT",0,0,0,0,0,0,"#B STATEMENT1",0,0,0,"return #AE lpar EXPR #AA rpar semi #AG #AH1","write #AD lpar EXPR #AA rpar semi #AF #AH1","read #AL lpar #B VARIABLE #AM #AR rpar semi #AN #AH1","while #AV lpar RELEXPR rpar STATBLOCK semi #AW #AH1",0,0,"if #AX lpar RELEXPR rpar then STATBLOCK else STATBLOCK semi #AY #AH1",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"STATEMENT1",0,0,0,0,0,0,"id #A STATEMENT2",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"STATEMENT2",0,0,0,"#B REPTVARIABLE2 #AO STATEMENT3",0,"lpar APARAMS rpar STATEMENT4",0,0,0,"#B REPTVARIABLE2 #AO STATEMENT3",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"#B REPTVARIABLE2 #AO STATEMENT3",0,0,0,0,0,"#B REPTVARIABLE2 #AO STATEMENT3",0},
			{"STATEMENT3",0,0,0,"dot #AQ STATEMENT1",0,0,0,0,0,"semi",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"#AM #AR ASSIGNOP EXPR #AA semi #AS #AH1",0},
			{"STATEMENT4",0,0,0,"dot #AQ STATEMENT1",0,0,0,0,0,"#AM semi #AT #AH1",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"ASSIGNOP EXPR semi",0},
			{"TERM",0,0,0,0,0,"FACTOR RIGHTRECTERM","FACTOR RIGHTRECTERM",0,0,0,0,0,0,0,0,0,0,0,0,"FACTOR RIGHTRECTERM","FACTOR RIGHTRECTERM",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"FACTOR RIGHTRECTERM","FACTOR RIGHTRECTERM","FACTOR RIGHTRECTERM",0,0,0},
			{"TYPE",0,0,0,0,0,0,"id","float","integer",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"VARIABLE",0,0,0,0,0,0,"id #A FVARid",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{"FVARid",0,0,0,"#B REPTVARIABLE2 #AO FVARid2","#B REPTVARIABLE2 #AO FVARid2","lpar APARAMS rpar dot #AQ VARIABLE",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"#B REPTVARIABLE2 #AO FVARid2",0,0,0,0,0,"#B REPTVARIABLE2 #AO FVARid2",0},
			{"FVARid2",0,0,0,"dot #AQ VARIABLE","&epsilon",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"&epsilon",0},
			{"VISIBILITY",0,"private #A","public #A",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"&epsilon","&epsilon",0,"&epsilon",0,0,0,0,0,0,0,0,0,0},
		
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
}

