import java.util.ArrayList;
import java.util.Arrays;

public class MemberFuncVisitor implements Visitor {
	public void visit(Node node) {}
	public void visit(EpsilonNode node) {}
	public void visit(ArraySizeNode node) {}
	public void visit(EmptyArraySizeNode node) {}
	public void visit(FParamsTailNode node) {}
	public void visit(FParamsTailListNode node) {}
	public void visit(FParamsNode node) {}
	public void visit(MemberFuncDeclNode node) {}
	public void visit(MemberVarDeclNode node) {}
	public void visit(MemberDeclNode node) {
		String className = ((SymbolClassEntry)node.getParent().getParent().getSymEntry()).getName();
		if (node.getSymEntry() instanceof SymbolMemberFunctionDeclEntry) {
			if (((SymbolMemberFunctionDeclEntry)node.getSymEntry()).getReturnType().equals("")) {
				((SymbolMemberFunctionDeclEntry)node.getSymEntry()).setReturnType(className);
			}
		}
	}
	public void visit(MemberDeclListNode node) {}
	public void visit(InheritanceListTailNode node) {}
	public void visit(InheritanceListNode node) {}
	public void visit(ClassDeclNode node) {}
	public void visit(FuncHeadNode node) {}
	public void visit(AddOpNode node) {}
	public void visit(MultOpNode node) {}
	public void visit(RelOpNode node) {}
	public void visit(NotNode node) {}
	public void visit(SignNode node) {}
	public void visit(LocalVarDeclNode node) {}
	public void visit(RelExprNode node) {}
	public void visit(ExprNode node) {}
	public void visit(ExprTailListNode node) {}
	public void visit(AParamsNode node) {}
	public void visit(WriteNode node) {}
	public void visit(ReturnNode node) {}
	public void visit(StatementNode node) {}
	public void visit(FuncBodyNode node) {}
	public void visit(FuncDefNode node) {}
	public void visit(ProgNode node) {
		ArrayList<Node> children = node.getChildren();
		ArrayList<Node> classes = new ArrayList<>();
		ArrayList<Node> functions = new ArrayList<>();
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).getType().equals("CLASSDECL")) {
				classes.add(children.get(i));
			} else {
				functions.add(children.get(i));
			}
		}
		int membFuncDeclCount = 0;
		for (int i = 0; i < classes.size(); i++) {
			SymbolTable table =	classes.get(i).getSymbolTable();
			ArrayList<SymbolTableEntry> entries = table.getSymEntries();
			for (int j = 0; j < entries.size(); j++) {
				if (entries.get(j) instanceof SymbolMemberFunctionDeclEntry) {
					membFuncDeclCount++;
				}
			}
		}
		ArrayList<SymbolTableEntry> membFuncDef = new ArrayList<>();
		for (int i = 0; i < functions.size(); i++) {
			if (functions.get(i).getChildren().get(0).getChildren().size() != 3) {
				membFuncDef.add(functions.get(i).getSymEntry());
			}
		}
		for (int i = 0; i < membFuncDef.size(); i++) {
			for (int j = 0; j < classes.size(); j++) {
				SymbolTable table = classes.get(j).getSymbolTable();
				String className = classes.get(j).getSymbolTable().getName();
				ArrayList<SymbolTableEntry> entries = table.getSymEntries();
				for (int k = 0; k < entries.size(); k++) {
					if (entries.get(k) instanceof SymbolMemberFunctionDeclEntry) {
						SymbolMemberFunctionDeclEntry membFuncDecl = (SymbolMemberFunctionDeclEntry) entries.get(k);
						SymbolMemberFunctionDefEntry membFuncDefTemp = (SymbolMemberFunctionDefEntry) membFuncDef.get(i);
						if (membFuncDefTemp.getName().equals(className)) {
							String[] parts = membFuncDefTemp.getSymTable().getName().split("::");
							if(parts[1].equals(membFuncDecl.getId()) && membFuncDecl.getReturnType().equals(membFuncDefTemp.getReturnType())) {
								int size = 0;
								ArrayList<SymbolTableEntry> membDeclParams = membFuncDefTemp.getSymTable().getSymEntries();
								for (int m = 0; m < membDeclParams.size(); m++) {
									if (((SymbolLocalVarParamEntry)membDeclParams.get(m)).getName().equals("param")) {
										size++;
									}
								}
								if (size == membFuncDecl.getParameters().size()) {
									boolean[] paramTypeMatch = new boolean[size];
									for (int m = 0; m < size; m++) {
										if (membFuncDecl.getParameters().get(m).equals(((SymbolLocalVarParamEntry)membDeclParams.get(m)).getType())) {
											paramTypeMatch[m] = true;
										} else {
											paramTypeMatch[m] = false;
										}
									}
									boolean allTrue=true;
									for(int m = 0; m < paramTypeMatch.length; m++)
									{
									   if(paramTypeMatch[m] == false) {
									       allTrue=false;
									   }
									}
									if (allTrue) {
										ArrayList<SymbolTableEntry> defEntries = membFuncDefTemp.getSymTable().getSymEntries();
										for (int m = 0; m < defEntries.size(); m++) {
											membFuncDecl.getSymTable().getSymEntries().add(defEntries.get(m));
											membFuncDecl.getSymTable().setName(className + "::" + membFuncDecl.getId());				
										}
										membFuncDeclCount--;
										membFuncDefTemp.delete();
									}
								}
							}
						}
					}
				}
			}
		}
		SymbolTable progTable = node.getSymbolTable();
		int size = progTable.getSymEntries().size();
		for (int i = size - 1; i > 0; i--) {
			if (progTable.getSymEntries().get(i) instanceof SymbolMemberFunctionDefEntry) {
				SymbolMemberFunctionDefEntry memberDef = (SymbolMemberFunctionDefEntry) progTable.getSymEntries().get(i);
				if (memberDef.getDeleted()) {
					progTable.getSymEntries().remove(i);
				}
			}
		}
		System.out.println(membFuncDeclCount);
	}
	public void visit(ReadNode node) {}
	public void visit(VariableNode node) {}
	public void visit(IndiceNode node) {}
	public void visit(IdNestNode node) {}
	public void visit(IdNestTempNode node) {}
	public void visit(AssignStatNode node) {}
	public void visit(FunctionCallNode node) {}
	public void visit(StatBlockNode node) {}
	public void visit(IfNode node) {}
	public void visit(WhileNode node) {}
}
