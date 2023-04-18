import java.util.ArrayList;
import java.util.Stack;

public class CodeGenerationVisitor implements Visitor {
	private Stack<String> registerPool = new Stack<>();
	private ArrayList<String> ifElseValueList = new ArrayList<>();
	private Stack<String> ifElseValueStack = new Stack<>();
	private ArrayList<String> ifEndValueList = new ArrayList<>();
	private Stack<String> ifEndValueStack = new Stack<>();
	private ArrayList<String> whileGoValueList = new ArrayList<>();
	private Stack<String> whileGoValueStack = new Stack<>();
	private ArrayList<String> whileEndValueList = new ArrayList<>();
	private Stack<String> whileEndValueStack = new Stack<>();
	private String moonExecCode = "";
	private String moonDataCode = "";
	
	public void visit(Node node) {
		if (node.getType().equals("intlit") && !node.getParent().getParent().getType().equals("LOCALVARDECL")) {
			moonDataCode = moonDataCode + node.getMoonVarName() + "   res 4\n";
			String localRegister = registerPool.pop();
			
			moonExecCode = moonExecCode + "     % store value of int literals\n";
			moonExecCode = moonExecCode + "     addi " + localRegister + ",r0," + node.getLexeme() +"\n";
			moonExecCode = moonExecCode + "     sw "+ node.getMoonVarName() + "(r0)," + localRegister + "\n";
			
			
			registerPool.push(localRegister);
		}
	}
	
	public void visit(EpsilonNode node) {}
	
	public void visit(ArraySizeNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(EmptyArraySizeNode node) {}
	
	public void visit(FParamsTailNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(FParamsTailListNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(FParamsNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(MemberFuncDeclNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(MemberVarDeclNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(MemberDeclNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(MemberDeclListNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(InheritanceListTailNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(InheritanceListNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(ClassDeclNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(FuncHeadNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(AddOpNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		
		String localReg1 = registerPool.pop();
		String localReg2 = registerPool.pop();
		String localReg3 = registerPool.pop();
		String leftName;
		String rightName;
		
		if (node.getChildren().get(0).getType().equals("VARIABLE")) {
			leftName = node.getChildren().get(0).getChildren().get(1).getLexeme();
		} else {
			leftName = node.getChildren().get(0).getMoonVarName();
		}
		
		if (node.getChildren().get(1).getType().equals("VARIABLE")) {
			rightName = node.getChildren().get(1).getChildren().get(1).getLexeme();
		} else {
			rightName = node.getChildren().get(1).getMoonVarName();
		}
		
		moonDataCode = moonDataCode + node.getMoonVarName() + "   res " + node.getMoonSize() + "\n";
		moonExecCode = moonExecCode + "     % compute the addition of two values\n";
		moonExecCode = moonExecCode + "     lw " + localReg1 + "," + leftName + "(r0)\n";
		moonExecCode = moonExecCode + "     lw " + localReg2 + "," + rightName + "(r0)\n";
		if (node.getLexeme().equals("+")) {
			moonExecCode = moonExecCode + "     add " + localReg3 + "," + localReg1 + "," + localReg2 + "\n";
		} else {
			moonExecCode = moonExecCode + "     sub " + localReg3 + "," + localReg1 + "," + localReg2 + "\n";
		}
		moonExecCode = moonExecCode + "     sw " + node.getMoonVarName() + "(r0)," + localReg3 + "\n";
		
		registerPool.push(localReg3);
		registerPool.push(localReg2);
		registerPool.push(localReg1);
	}
	
	public void visit(MultOpNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		
		String localReg1 = registerPool.pop();
		String localReg2 = registerPool.pop();
		String localReg3 = registerPool.pop();
		String leftName;
		String rightName;
		
		if (node.getChildren().get(0).getType().equals("VARIABLE")) {
			leftName = node.getChildren().get(0).getChildren().get(1).getLexeme();
		} else {
			leftName = node.getChildren().get(0).getMoonVarName();
		}
		
		if (node.getChildren().get(1).getType().equals("VARIABLE")) {
			rightName = node.getChildren().get(1).getChildren().get(1).getLexeme();
		} else {
			rightName = node.getChildren().get(1).getMoonVarName();
		}
		
		moonDataCode = moonDataCode + node.getMoonVarName() + "   res " + node.getMoonSize() + "\n";
		moonExecCode = moonExecCode + "     % compute the multiplication of two values\n";
		moonExecCode = moonExecCode + "     lw " + localReg1 + "," + leftName + "(r0)\n";
		moonExecCode = moonExecCode + "     lw " + localReg2 + "," + rightName + "(r0)\n";
		if (node.getLexeme().equals("*")) {
			moonExecCode = moonExecCode + "     mul " + localReg3 + "," + localReg1 + "," + localReg2 + "\n";
		} else {
			moonExecCode = moonExecCode + "     div " + localReg3 + "," + localReg1 + "," + localReg2 + "\n";
		}
		moonExecCode = moonExecCode + "     sw " + node.getMoonVarName() + "(r0)," + localReg3 + "\n";
		
		registerPool.push(localReg3);
		registerPool.push(localReg2);
		registerPool.push(localReg1);
	}
	
	public void visit(RelOpNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(NotNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(SignNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(LocalVarDeclNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		
		String id = node.getChildren().get(0).getLexeme();
		String type = node.getChildren().get(1).getLexeme();
		if (node.getChildren().get(2).getChildren().size() == 0) {
			if (type.equals("integer")) {
				moonDataCode = moonDataCode + id + "    res 4\n"; 
			} else if(type.equals("float")) {
				moonDataCode = moonDataCode + id + "    res 8\n"; 
			}
		} else {
			if (type.equals("integer") || type.equals("float")) {
				moonDataCode = moonDataCode + id + "  res " + node.getMoonSize() + "\n";
			}
		}
		
		
	}
	
	public void visit(RelExprNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		
		String localReg1 = registerPool.pop();
		String localReg2 = registerPool.pop();
		String localReg3 = registerPool.pop();
		String leftName;
		String rightName;
		String operation = node.getChildren().get(1).getLexeme();
		
		if (node.getChildren().get(0).getType().equals("VARIABLE")) {
			leftName = node.getChildren().get(0).getChildren().get(1).getLexeme();
		} else {
			leftName = node.getChildren().get(0).getMoonVarName();
		}
		
		if (node.getChildren().get(2).getType().equals("VARIABLE")) {
			rightName = node.getChildren().get(2).getChildren().get(1).getLexeme();
		} else {
			rightName = node.getChildren().get(2).getMoonVarName();
		}
		
		moonDataCode = moonDataCode + node.getMoonVarName() + "   res " + node.getMoonSize() + "\n";
		moonExecCode = moonExecCode + "     % computes the relop of two values\n";
		moonExecCode = moonExecCode + "     lw " + localReg1 + "," + leftName + "(r0)\n";
		moonExecCode = moonExecCode + "     lw " + localReg2 + "," + rightName + "(r0)\n";
		if (operation.equals("<")) {
			moonExecCode = moonExecCode + "     clt " + localReg3 + "," + localReg1 + "," + localReg2 + "\n";
		} else if (operation.equals(">")) {
			moonExecCode = moonExecCode + "     cgt " + localReg3 + "," + localReg1 + "," + localReg2 + "\n";
		} else if (operation.equals("<=")) {
			moonExecCode = moonExecCode + "     cle " + localReg3 + "," + localReg1 + "," + localReg2 + "\n";
		} else if (operation.equals(">=")) {
			moonExecCode = moonExecCode + "     cge " + localReg3 + "," + localReg1 + "," + localReg2 + "\n";
		} else if (operation.equals("==")) {
			moonExecCode = moonExecCode + "     ceq " + localReg3 + "," + localReg1 + "," + localReg2 + "\n";
		} else if (operation.equals("<>")) {
			moonExecCode = moonExecCode + "     cne " + localReg3 + "," + localReg1 + "," + localReg2 + "\n";
		}
		moonExecCode = moonExecCode + "     sw " + node.getMoonVarName() + "(r0)," + localReg3 + "\n";
		
		registerPool.push(localReg3);
		registerPool.push(localReg2);
		registerPool.push(localReg1);
	}
	
	public void visit(ExprNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		
	}
	
	public void visit(ExprTailListNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(AParamsNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(WriteNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		
		String localRegister = registerPool.pop();
		String localRegister2 = registerPool.pop();
		if (node.getChildren().get(0).getChildren().get(0).getType().equals("VARIABLE")) {
			if (node.getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().size() > 0) {
				int offset = Integer.parseInt(node.getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(0).getLexeme());
				int typeOffset;
				if (node.getChildren().get(0).getChildren().get(0).getChildren().get(2).getChildren().get(0).getType().equals("intlit")) {
					typeOffset = 4;
				} else {
					typeOffset = 8;
				}
				moonExecCode = moonExecCode + "     % loads word for array";
				moonExecCode = moonExecCode + "     addi " + localRegister2 + ",r0," + (offset * typeOffset) +"\n";
				moonExecCode = moonExecCode + "     lw " + localRegister + "," + node.getChildren().get(0).getChildren().get(0).getChildren().get(1).getLexeme() + "(" + localRegister2 + ")\n";
			} else {
				moonExecCode = moonExecCode + "     % loads word\n";
				moonExecCode = moonExecCode + "     lw " + localRegister + "," + node.getChildren().get(0).getChildren().get(0).getChildren().get(1).getLexeme() + "(r0)\n";
			}
		} else {
			moonExecCode = moonExecCode + "     lw " + localRegister + "," + node.getChildren().get(0).getChildren().get(0).getMoonVarName() + "(r0)\n";
		}
		moonExecCode = moonExecCode + "     % put value on stack\n";
		moonExecCode = moonExecCode + "     sw -8(r14)," + localRegister + "\n";
		moonExecCode = moonExecCode + "     % link buffer to stack\n";
		moonExecCode = moonExecCode + "     addi " + localRegister + ",r0, buf\n";
		moonExecCode = moonExecCode + "     sw -12(r14)," + localRegister + "\n";
		moonExecCode = moonExecCode + "     % convert int to string for output\n";
		moonExecCode = moonExecCode + "     jl r15, intstr\n";	
		moonExecCode = moonExecCode + "     sw -8(r14),r13\n";
		moonExecCode = moonExecCode + "     % output to console\n";
		moonExecCode = moonExecCode + "     jl r15, putstr\n";
		moonExecCode = moonExecCode + "     % skips line\n";
		moonExecCode = moonExecCode + "     addi r13,r0,cr\n";	
		moonExecCode = moonExecCode + "     sw -8(r14),r13\n";
		moonExecCode = moonExecCode + "     jl r15, putstr\n";
		
		registerPool.push(localRegister);
		registerPool.push(localRegister2);
	}
	
	public void visit(ReturnNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(StatementNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(FuncBodyNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(FuncDefNode node) {
		if (node.getSymbolTable().getName().equals("::main")) {
			moonExecCode = moonExecCode + "     % main function starts\n";
			moonExecCode = moonExecCode + "     entry\n";
			moonExecCode = moonExecCode + "     addi r14,r0,topaddr\n";
			ArrayList<Node> childList = node.getChildren();
			for (int i = 0; i < childList.size(); i++) {
				childList.get(i).accept2(this);
			}
			moonExecCode = moonExecCode + "     %required to end program\n";
			moonExecCode = moonExecCode + "     getc r0\n";
			moonExecCode = moonExecCode + "hlt\n\n";
		} else {
			moonExecCode = moonExecCode + node.getChildren().get(0).getChildren().get(0).getLexeme() + "\n";
			ArrayList<Node> childList = node.getChildren();
			for (int i = 0; i < childList.size(); i++) {
				childList.get(i).accept2(this);
			}
			moonExecCode = moonExecCode + "     % jump back to calling function\n";
			moonExecCode = moonExecCode + "     jr r15\n\n";
		}
		
	}
	
	public void visit(ProgNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		moonDataCode = moonDataCode + "buf  res 20\n";
		moonDataCode = moonDataCode + "cr   db 10\n";
	}
	
	public void visit(ReadNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		
		String localRegister = registerPool.pop();
		String name = node.getChildren().get(0).getChildren().get(1).getLexeme();
		
		moonExecCode = moonExecCode + "     % reads word from input\n";
		moonExecCode = moonExecCode + "     addi " + localRegister + ",r0,buf\n";
		moonExecCode = moonExecCode + "     sw -8(r14)," + localRegister + "\n";
		moonExecCode = moonExecCode + "     jl r15,getstr\n";
		moonExecCode = moonExecCode + "     jl r15,strint\n";
		moonExecCode = moonExecCode + "     sw " + name + "(r0),r13\n";
		
		registerPool.push(localRegister);
	}
	
	public void visit(VariableNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(IndiceNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(IdNestNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(IdNestTempNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(AssignStatNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		
		String localRegister = registerPool.pop();
		
		if (node.getChildren().get(0).getChildren().get(2).getChildren().size() > 0) {
			String localRegister2 = registerPool.pop();
			int offset = Integer.parseInt(node.getChildren().get(0).getChildren().get(2).getChildren().get(0).getLexeme());
			int typeOffset;
			if (node.getChildren().get(0).getChildren().get(2).getChildren().get(0).getType().equals("intlit")) {
				typeOffset = 4;
			} else {
				typeOffset = 8;
			}
			moonExecCode = moonExecCode + "     % assigns statement for array\n";
			moonExecCode = moonExecCode + "     addi " + localRegister2 + ",r0," + (offset * typeOffset) +"\n";
			moonExecCode = moonExecCode + "     lw " + localRegister + "," + node.getChildren().get(1).getChildren().get(0).getMoonVarName() + "(r0)\n";
			moonExecCode = moonExecCode + "     sw " + node.getChildren().get(0).getChildren().get(1).getLexeme() + "(" + localRegister2 + ")," + localRegister + "\n";
			registerPool.push(localRegister2);
			registerPool.push(localRegister);
		} else {
			moonExecCode = moonExecCode + "     % assigns statement\n";
			moonExecCode = moonExecCode + "     lw " + localRegister + "," + node.getChildren().get(1).getChildren().get(0).getMoonVarName() + "(r0)\n";
			moonExecCode = moonExecCode + "     sw " + node.getChildren().get(0).getChildren().get(1).getLexeme() + "(r0)," + localRegister + "\n";
			registerPool.push(localRegister);
		}
		
	}
	
	public void visit(FunctionCallNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
		moonExecCode = moonExecCode + "     % jump to function\n";
		moonExecCode = moonExecCode + "     jl r15," + node.getChildren().get(1).getLexeme() + "\n";
	}
	
	public void visit(StatBlockNode node) {
		ArrayList<Node> childList = node.getChildren();
		for (int i = 0; i < childList.size(); i++) {
			childList.get(i).accept2(this);
		}
	}
	
	public void visit(IfNode node) {
		ArrayList<Node> childList = node.getChildren();
		childList.get(0).accept2(this);
		
		String localRegister = registerPool.pop();
		
		int ifElseValueSize = ifElseValueList.size();
		ifElseValueList.add("else" + (ifElseValueSize + 1));
		ifElseValueStack.push(ifElseValueList.get(ifElseValueSize));
		ifEndValueList.add("endif" + (ifElseValueSize + 1));
		ifEndValueStack.push(ifEndValueList.get(ifElseValueSize));
		moonExecCode = moonExecCode + "     % checks value of relative expression for if statement\n";
		moonExecCode = moonExecCode + "     lw " + localRegister + "," + node.getChildren().get(0).getMoonVarName() + "(r0)\n";
		moonExecCode = moonExecCode + "     % jumps to else if needed\n";
		moonExecCode = moonExecCode + "     bz " + localRegister + "," + ifElseValueList.get(ifElseValueSize) + "\n";
		childList.get(1).accept2(this);
		moonExecCode = moonExecCode + "     % jumps to end of if statement\n";
		moonExecCode = moonExecCode + "     j " + ifEndValueList.get(ifElseValueSize) + "\n";
		moonExecCode = moonExecCode + ifElseValueStack.pop() + "\n";
		childList.get(2).accept2(this);
		moonExecCode = moonExecCode + ifEndValueStack.pop() + "\n";
		
		registerPool.push(localRegister);
		
	}
	
	public void visit(WhileNode node) {
		ArrayList<Node> childList = node.getChildren();
		
		String localRegister = registerPool.pop();
		
		int whileGoValueSize = whileGoValueList.size();
		whileGoValueList.add("gowhile" + (whileGoValueSize) + 1);
		whileGoValueStack.push(whileGoValueList.get(whileGoValueSize));
		whileEndValueList.add("endwhile" + (whileGoValueSize) + 1);
		whileEndValueStack.push(whileEndValueList.get(whileGoValueSize));
		moonExecCode = moonExecCode + whileGoValueList.get(whileGoValueSize) + "\n";
		childList.get(0).accept2(this);
		moonExecCode = moonExecCode + "     % computes while loop\n";
		moonExecCode = moonExecCode + "     lw " + localRegister + "," + node.getChildren().get(0).getMoonVarName() + "(r0)\n";
		moonExecCode = moonExecCode + "     % while loop terminates if needed\n";
		moonExecCode = moonExecCode + "     bz " + localRegister + "," + whileEndValueList.get(whileGoValueSize) + "\n";
		childList.get(1).accept2(this);
		moonExecCode = moonExecCode + "     % while loop continues\n";
		moonExecCode = moonExecCode + "     j " + whileGoValueStack.pop() + "\n";
		moonExecCode = moonExecCode + whileEndValueStack.pop() + "\n";
		
		registerPool.push(localRegister);
	}

	public void populateRegisterPool() {
		registerPool.add("r12");
		registerPool.add("r11");
		registerPool.add("r10");
		registerPool.add("r9");
		registerPool.add("r9");
		registerPool.add("r7");
		registerPool.add("r6");
		registerPool.add("r5");
		registerPool.add("r4");
		registerPool.add("r3");
		registerPool.add("r2");
		registerPool.add("r1");
	}
	
	public String getMoonExecCode() {
		return moonExecCode;
	}
	
	public String getMoonDataCode() {
		return moonDataCode;
	}
	
}
