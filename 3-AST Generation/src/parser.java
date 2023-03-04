import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class parser {
	private lexicalanalyzer lex;
	private Stack<String> stack = new Stack<String>();
	private MyParsingTableModel parsingTable = new MyParsingTableModel();
	private MyFirstFollowTableModel firstFollowTable = new MyFirstFollowTableModel();
	private ArrayList<String> terminals = new ArrayList<>();
	private FileWriter derivationWriter;
	private FileWriter errorWriter;
	private Token token;
	private Token prevToken;
	private Stack<Node> semStack = new Stack<Node>();
	private AST AST = new AST();

	public parser(lexicalanalyzer lexical) {
		lex = lexical;
		String fileName = lex.getFileName();
		String[] fileName1 = fileName.split("/");
		String[] fileName2 = fileName1[1].split("\\.");
		try {
			derivationWriter = new FileWriter("Generated Output Files/" + fileName2[0] + ".outderivation");
			errorWriter = new FileWriter("Generated Output Files/" + fileName2[0] + ".outsyntaxerrors");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean parse() {
		stack.push("$");
		stack.push("START");
		boolean error = false;
		
		token = lex.nextToken();
		while (!stack.peek().equals("$")) {
			String x = stack.peek();
			
			// check if epsilon is read from stack, pop if yes
			if (x.equals("&epsilon")) {
				stack.pop();
				
				// print derivation
				for (int i = 0; i < terminals.size(); i++) {
					try {
						derivationWriter.write(terminals.get(i) + " ");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				PrintStack(stack);
				try {
					derivationWriter.write(System.getProperty( "line.separator" ));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			// check if token is error or comment, skip token if yes
			} else if (token.getType().equals("invalidchar") || token.getType().equals("inlinecmt") || token.getType().equals("blockcmt") || token.getType().equals("untermerr") || token.getType().equals("invalidnum")) {
				prevToken = token;
				token = lex.nextToken();
				
			// check if stack value is terminal 
			} else if (x.charAt(0) == '#') {
				String str = x.substring(1);
				switch(str) {
				case "A":A(); break;
				case "B":B(); break;
				case "C":C(); break;
				case "D":D(); break;
				case "E":E(); break;
				case "F":F(); break;
				case "G":G(); break;
				case "H1":H1(); break;
				case "H2":H2(); break;
				case "I":I(); break;
				case "J":J(); break;
				}
				stack.pop();
			} else if (parsingTable.findColumn(x) != -1) {
				if (x.equals(token.getType())) {
					terminals.add(x);
					stack.pop();
					prevToken = token;
					token = lex.nextToken();
				} else {
					skipErrors();
					error = true;
				}
			// stack value is nonterminal
			} else {
				Object val = parsingTable.getValueAt(parsingTable.findRow(x), parsingTable.findColumn(token.getType()));
				if (val instanceof String) {
					val = (String) val;
					stack.pop();
					String newPush = (String) parsingTable.getValueAt(parsingTable.findRow(x), parsingTable.findColumn(token.getType()));
					inverseRHSMultiplePush(newPush);
					
					// print derivation
					for (int i = 0; i < terminals.size(); i++) {
						try {
							derivationWriter.write(terminals.get(i) + " ");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					PrintStack(stack);
					try {
						derivationWriter.write(System.getProperty( "line.separator" ));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} else {
					
					error = true;
					skipErrors();
				}
			}
			
		}
		
		// close writers
		try {
			derivationWriter.close();
			errorWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// TEMPORARY PRINT SEMANTIC STACK
		while (!semStack.empty()) {
			Node.printTree2(semStack.peek());
			System.out.println();
			semStack.pop();
		}
		
		// check if error occurred
		if (!token.getType().equals("$") || error == true) {
			return false;
		} else {
			return true;
		}


	}
	
	public void inverseRHSMultiplePush(String s) {
		String[] newPushSplit = s.split("\\s+");
		ArrayList<String> newPushSplitList = new ArrayList<String>();
		for (int i = 0; i < newPushSplit.length; i++) {
			newPushSplitList.add(newPushSplit[i]);
		}
		Collections.reverse(newPushSplitList);
		for (int i = 0; i < newPushSplit.length; i++) {
			stack.push(newPushSplitList.get(i));
		}
	}
	
	public void PrintStack(Stack<String> s) 
	{ 
	    if (s.empty()) 
	        return; 
	    
	    String x = s.peek(); 
	    
	    s.pop(); 
	    
	    try {
			derivationWriter.write(x + " ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    PrintStack(s); 
	    
	    s.push(x);
	} 
	
	public void skipErrors() {
		try {
			errorWriter.write("Syntax Error for token type: " + token.getType() + ", lexeme: '" + token.getLexeme() + "', at line: " + token.getLoc());
			errorWriter.write(System.getProperty( "line.separator" ));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (firstFollowTable.findRow(stack.peek()) == -1 && !token.getType().equals("$")) {
			prevToken = token;
			token = lex.nextToken();
		} else if (token.getType().equals("$") || firstFollowTable.isInFollow(token,stack.peek())) {
			stack.pop();
		} else {
			while ((!firstFollowTable.isInFirst(token, stack.peek()) &&
					!firstFollowTable.isInFollow(token, stack.peek())) &&
					!token.getType().equals("$")) {
				prevToken = token;
				token = lex.nextToken();	
			}
		}
	}
	
	// START OF SEMANTIC FUNCTIONS
	public void A() {
		semStack.push(AST.makeNode(prevToken));
	}
	
	public void B() {
		semStack.push(AST.makeNode());
	}
	
	public void C() {
		Node arraySize = AST.makeNodeArraySize();
		ArrayList<Node> children = new ArrayList<>();
		while (semStack.peek().getType() != null) {
			children.add(semStack.pop());
		}
		semStack.pop();
		semStack.push(AST.makeFamilyArraySize(arraySize,children));
	}
	
	public void D() {
		Node fParamsTail = AST.makeNodeFParamsTail();
		Node kid1,kid2,kid3;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		semStack.push(AST.makeFamilyFParamsTail(fParamsTail,kid1,kid2,kid3));
	}
	
	public void E() {
		Node fParamsTailList = AST.makeNodeFParamsTailList();
		ArrayList<Node> children = new ArrayList<>();
		while (semStack.peek().getType() != null) {
			children.add(semStack.pop());
		}
		semStack.pop();
		semStack.push(AST.makeFamilyFParamsTailList(fParamsTailList,children));
	}
	
	public void F() {
		Node fParams = AST.makeNodeFParams();
		Node kid1,kid2,kid3,kid4;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		kid4 = semStack.pop();
		semStack.push(AST.makeFamilyFParams(fParams,kid1,kid2,kid3,kid4));
	}
	
	public void G() {
		semStack.push(AST.makeTypeNode(prevToken));
	}
	
	public void H1() {
		Node memberFuncDecl = AST.makeNodeMemberFuncDecl();
		Node kid1,kid2,kid3;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		semStack.push(AST.makeFamilyMemberFuncDecl1(memberFuncDecl,kid1,kid2,kid3));
	}
	
	public void H2() {
		Node memberFuncDecl = AST.makeNodeMemberFuncDecl();
		Node kid1;
		kid1 = semStack.pop();
		semStack.push(AST.makeFamilyMemberFuncDecl2(memberFuncDecl,kid1));
	}
	
	public void I() {
		Node memberVarDecl = AST.makeNodeMemberVarDecl();
		Node kid1,kid2,kid3;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		semStack.push(AST.makeFamilyMemberVarDecl(memberVarDecl,kid1,kid2,kid3));
	}
	
	public void J() {
		
	}
	
}
