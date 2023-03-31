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
	private FileWriter ASTWriter;
	private FileWriter semanticErrorWriter;
	private FileWriter symbolTableWriter;
	private FileWriter moonCode;
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
			ASTWriter = new FileWriter("Generated Output Files/" + fileName2[0] + ".outast");
			semanticErrorWriter = new FileWriter("Generated Output Files/" + fileName2[0] + ".outsemanticerrors");
			symbolTableWriter = new FileWriter("Generated Output Files/" + fileName2[0] + ".outsymboltables");
			moonCode = new FileWriter("moon/source/" + fileName2[0] + ".moon");
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
			
			// check if semantic rule, fire correct semantic function
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
				case "J1":J1(); break;
				case "J2":J2(); break;
				case "K":K(); break;
				case "L":L(); break;
				case "M":M(); break;
				case "N":N(); break;
				case "O1":O1(); break;
				case "O2":O2(); break;
				case "O3":O3(); break;
				case "P":P(); break;
				case "Q":Q(); break;
				case "R":R(); break;
				case "S":S(); break;
				case "T":T(); break;
				case "U":U(); break;
				case "V":V(); break;
				case "W":W(); break;
				case "X":X(); break;
				case "Y":Y(); break;
				case "Z":Z(); break;
				case "AA":AA(); break;
				case "AB":AB(); break;
				case "AC":AC(); break;
				case "AD":AD(); break;
				case "AE":AE(); break;
				case "AF":AF(); break;
				case "AG":AG(); break;
				case "AH1":AH1(); break;
				case "AH2":AH2(); break;
				case "AI":AI(); break;
				case "AJ":AJ(); break;
				case "AK":AK(); break;
				case "AL":AL(); break;
				case "AM":AM(); break;
				case "AN":AN(); break;
				case "AO":AO(); break;
				case "AQ":AQ(); break;
				case "AR":AR(); break;
				case "AS":AS(); break;
				case "AT":AT(); break;
				case "AC2":AC2(); break;
				case "AU":AU(); break;
				case "AV":AV(); break;
				case "AW":AW(); break;
				case "AX":AX(); break;
				case "AY":AY(); break;
				case "AZ":AZ(); break;
				case "BA":BA(); break;
				}
				stack.pop();
				
			// check if stack value is terminal 
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
		
		// print semantic stack, call visitors
		if (semStack.size() == 1) {
			printTree(semStack.peek());
			try {
				ASTWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			SymbolTableCreationVisitor Visitor1 = new SymbolTableCreationVisitor();
			PopulateFParamsVisitor Visitor2 = new PopulateFParamsVisitor();
			MemberFuncVisitor Visitor3 = new MemberFuncVisitor(semanticErrorWriter);
			SemanticErrorVisitor1 Visitor4 = new SemanticErrorVisitor1(semanticErrorWriter);
			SemanticErrorVisitor2 Visitor5 = new SemanticErrorVisitor2(semanticErrorWriter);
			MemAllocVisitor1 Visitor6 = new MemAllocVisitor1();
			semStack.peek().accept(Visitor1);
			semStack.peek().accept(Visitor2);
			semStack.peek().accept(Visitor3);
			semStack.peek().accept(Visitor4);
			semStack.peek().accept(Visitor5);
			
			semStack.peek().accept2(Visitor6);
			CodeGenerationVisitor Visitor7 = new CodeGenerationVisitor();
			Visitor7.populateRegisterPool();
			semStack.peek().accept2(Visitor7);
			try {
				moonCode.write(Visitor7.getMoonExecCode());
				moonCode.write(Visitor7.getMoonDataCode());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			semStack.peek().getSymbolTable().Print(symbolTableWriter);
			
		}
		
		
		// close writers
		try {
			derivationWriter.close();
			errorWriter.close();
			ASTWriter.close();
			semanticErrorWriter.close();
			symbolTableWriter.close();
			moonCode.close();
		} catch (IOException e) {
			e.printStackTrace();
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
	
	private int depthCounter = 0;
	public void printTree(Node x) {
		try {
			ASTWriter.write(x.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		x.findChildren();
		if (x.getLeftChild() != null) {
			depthCounter++;
			Node now = x.getLeftChild();
			try {
				ASTWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < depthCounter; i++) {
				try {
					ASTWriter.write("| ");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			printTree(now);
			depthCounter--;
		}
		if (x.getRightSibling() != null) {
			try {
				ASTWriter.write(System.getProperty( "line.separator" ));
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < depthCounter; i++) {
				try {
					ASTWriter.write("| ");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Node now2 = x.getRightSibling();
			printTree(now2);
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
	
	public void J1() {
		Node memberDecl = AST.makeNodeMemberDecl();
		Node kid1,kid2;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		semStack.push(AST.makeFamilyMemberDecl1(memberDecl,kid1,kid2));
	}
	
	public void J2() {
		Node memberDecl = AST.makeNodeMemberDecl();
		Node kid1;
		kid1 = semStack.pop();
		semStack.push(AST.makeFamilyMemberDecl2(memberDecl,kid1));
	}
	
	public void K() {
		Node memberDeclList = AST.makeNodeMemberDeclList();
		ArrayList<Node> children = new ArrayList<>();
		while (semStack.peek().getType() != null) {
			children.add(semStack.pop());
		}
		semStack.pop();
		semStack.push(AST.makeFamilyMemberDeclList(memberDeclList,children));
	}
	
	public void L() {
		Node inheritanceListTail = AST.makeNodeInheritanceListTail();
		ArrayList<Node> children = new ArrayList<>();
		while (semStack.peek().getType() != null) {
			children.add(semStack.pop());
		}
		semStack.pop();
		semStack.push(AST.makeFamilyInheritanceListTail(inheritanceListTail,children));
	}
	
	public void M() {
		Node inheritanceList = AST.makeNodeInheritanceList();
		ArrayList<Node> children = new ArrayList<>();
		while (semStack.peek().getType() != null) {
			children.add(semStack.pop());
		}
		semStack.pop();
		semStack.push(AST.makeFamilyInheritanceList(inheritanceList,children));
	}
	
	public void N() {
		Node classDecl = AST.makeNodeClassDecl();
		Node kid1,kid2,kid3;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		semStack.push(AST.makeFamilyClassDecl(classDecl,kid1,kid2,kid3));
	}
	
	public void O1() {
		Node funcHead = AST.makeNodeFuncHead();
		Node kid1,kid2,kid3;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		semStack.push(AST.makeFamilyFuncHead1(funcHead,kid1,kid2,kid3));
	}
	
	public void O2() {
		Node funcHead = AST.makeNodeFuncHead();
		Node kid1,kid2,kid3,kid4;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		kid4 = semStack.pop();
		semStack.push(AST.makeFamilyFuncHead2(funcHead,kid1,kid2,kid3,kid4));
	}
	
	public void O3() {
		Node funcHead = AST.makeNodeFuncHead();
		Node kid1,kid2;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		semStack.push(AST.makeFamilyFuncHead3(funcHead,kid1,kid2));
	}
	
	public void P() {
		semStack.push(AST.makeAddOpNode(prevToken));
	}
	
	public void Q() {
		semStack.push(AST.makeMultOpNode(prevToken));
	}
	
	public void R() {
		semStack.push(AST.makeRelOpNode(prevToken));
	}
	
	public void S() {
		semStack.push(AST.makeNotNode(prevToken));
	}
	
	public void T() {
		semStack.push(AST.makeSignNode(prevToken));
	}
	
	public void U() {
		Node localVarDecl = AST.makeNodeLocalVarDecl();
		Node kid1,kid2,kid3;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		semStack.push(AST.makeFamilyLocalVarDecl(localVarDecl,kid1,kid2,kid3));
	}
	
	public void V() {
		Node kid1,parent;
		kid1 = semStack.pop();
		parent = semStack.pop();
		semStack.push(AST.makeFamilyNot(parent,kid1));
	}
	
	public void W() {
		Node kid1,parent;
		kid1 = semStack.pop();
		parent = semStack.pop();
		semStack.push(AST.makeFamilySign(parent,kid1));
	}
	
	public void X() {
		Node kid1,kid2,parent;
		kid1 = semStack.pop();
		parent = semStack.pop();
		kid2 = semStack.pop();
		semStack.push(AST.makeFamilyMultOp(parent,kid1,kid2));
	}
	
	public void Y() {
		Node kid1,kid2,parent;
		kid1 = semStack.pop();
		parent = semStack.pop();
		kid2 = semStack.pop();
		semStack.push(AST.makeFamilyAddOp(parent,kid1,kid2));
	}
	
	public void Z() {
		Node relExpr = AST.makeNodeRelExpr();
		Node kid1,kid2,kid3;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		semStack.push(AST.makeFamilyRelExpr(relExpr,kid1,kid2,kid3));
	}
	
	public void AA() {
		Node expr = AST.makeNodeExpr();
		Node kid1;
		kid1 = semStack.pop();
		semStack.push(AST.makeFamilyExpr(expr,kid1));
	}
	
	public void AB() {
		Node exprTailList = AST.makeNodeExprTailList();
		ArrayList<Node> children = new ArrayList<>();
		while (semStack.peek().getType() != null) {
			children.add(semStack.pop());
		}
		semStack.pop();
		semStack.push(AST.makeFamilyExprTailList(exprTailList,children));
	}
	
	public void AC() {
		Node aParams = AST.makeNodeAParams();
		Node kid1,kid2;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		semStack.push(AST.makeFamilyAParams(aParams,kid1,kid2));
	}
	
	public void AD() {
		semStack.push(AST.makeWriteNode(prevToken));
	}
	
	public void AE() {
		semStack.push(AST.makeReturnNode(prevToken));
	}
	
	public void AF() {
		Node kid1,parent;
		kid1 = semStack.pop();
		parent = semStack.pop();
		semStack.push(AST.makeFamilyWrite(parent,kid1));
	}
	
	public void AG() {
		Node kid1,parent;
		kid1 = semStack.pop();
		parent = semStack.pop();
		semStack.push(AST.makeFamilyReturn(parent,kid1));
	}
	
	public void AH1() {
		Node statement = AST.makeNodeStatement();
		Node kid1;
		kid1 = semStack.pop();
		semStack.push(AST.makeFamilyStatement(statement,kid1));
	}
	
	public void AI() {
		Node funcBody = AST.makeNodeFuncBody();
		ArrayList<Node> children = new ArrayList<>();
		while (semStack.peek().getType() != null) {
			children.add(semStack.pop());
		}
		semStack.pop();
		semStack.push(AST.makeFamilyFuncBody(funcBody,children));
	}
	
	public void AJ() {
		Node funcDef = AST.makeNodeFuncDef();
		Node kid1,kid2;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		semStack.push(AST.makeFamilyFuncDef(funcDef,kid1,kid2));
	}
	
	public void AK() {
		Node prog = AST.makeNodeProg();
		ArrayList<Node> children = new ArrayList<>();
		while (semStack.peek().getType() != null) {
			children.add(semStack.pop());
		}
		semStack.pop();
		semStack.push(AST.makeFamilyProg(prog,children));
	}
	
	public void AL() {
		semStack.push(AST.makeReadNode(prevToken));
	}
	
	public void AM() {
		Node idNest = AST.makeNodeIdNest();
		Node kid1,kid2;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		ArrayList<Node> children = new ArrayList<>();
		while (semStack.peek().getType() != null) {
			children.add(semStack.pop());
		}
		semStack.pop();
		semStack.push(AST.makeFamilyIdNest(idNest,children));
		semStack.push(kid2);
		semStack.push(kid1);
	}
	
	public void AN() {
		Node kid1,parent;
		kid1 = semStack.pop();
		parent = semStack.pop();
		semStack.push(AST.makeFamilyRead(parent,kid1));
	}
	
	public void AO() {
		Node indice = AST.makeNodeIndice();
		ArrayList<Node> children = new ArrayList<>();
		while (semStack.peek().getType() != null) {
			children.add(semStack.pop());
		}
		semStack.pop();
		semStack.push(AST.makeFamilyIndice(indice,children));
	}

	public void AQ() {
		Node idNestTemp = AST.makeNodeIdNestTemp();
		Node kid1,kid2;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		semStack.push(AST.makeFamilyIdNestTemp(idNestTemp,kid1,kid2));
	}
	
	public void AR() {
		Node variable = AST.makeNodeVariable();
		Node kid1,kid2,kid3;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		semStack.push(AST.makeFamilyVariable(variable,kid1,kid2,kid3));
	}
	
	public void AS() {
		Node assignStat = AST.makeNodeAssignStat();
		Node kid1,kid2;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		semStack.push(AST.makeFamilyAssignStat(assignStat,kid1,kid2));
	}
	
	public void AT() {
		Node functionCall = AST.makeNodeFunctionCall();
		Node kid1,kid2,kid3;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		semStack.push(AST.makeFamilyFunctionCall(functionCall,kid1,kid2,kid3));
	}
	
	public void AC2() {
		semStack.push(AST.makeNodeAParams());
	}
	
	public void AU() {
		Node statBlock = AST.makeNodeStatBlock();
		ArrayList<Node> children = new ArrayList<>();
		while (semStack.peek().getType() != null) {
			children.add(semStack.pop());
		}
		semStack.pop();
		semStack.push(AST.makeFamilyStatBlock(statBlock,children));
	}
	
	public void AV() {
		semStack.push(AST.makeWhileNode(prevToken));
	}
	
	public void AH2() {
		semStack.push(AST.makeNodeStatement());
	}
	
	public void AW() {
		Node kid1,kid2,parent;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		parent = semStack.pop();
		semStack.push(AST.makeFamilyWhile(parent,kid1,kid2));
	}
	
	public void AX() {
		semStack.push(AST.makeIfNode(prevToken));
	}
	
	public void AY() {
		Node kid1,kid2,kid3,parent;
		kid1 = semStack.pop();
		kid2 = semStack.pop();
		kid3 = semStack.pop();
		parent = semStack.pop();
		semStack.push(AST.makeFamilyIf(parent,kid1,kid2,kid3));
	}
	
	public void AZ() {
		semStack.push(AST.makeNodeFParams());
	}
	
	public void BA() {
		semStack.push(AST.makeNodeEmptyArraySize());
	}
	
}
