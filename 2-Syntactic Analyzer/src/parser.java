import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class parser {
	private lexicalanalyzer lex;
	private Stack<String> stack = new Stack<String>();
	private MyParsingTableModel parsingTable = new MyParsingTableModel();
	private ArrayList<String> terminals = new ArrayList<>();
	private FileWriter derivationWriter;
	private FileWriter errorWriter;

	public parser(lexicalanalyzer lexical) {
		lex = lexical;
		String fileName = lex.getFileName();
		String[] fileName1 = fileName.split("/");
		String[] fileName2 = fileName1[1].split("\\.");
		try {
			derivationWriter = new FileWriter("Generated Outlex Files/" + fileName2[0] + ".outderivation");
			errorWriter = new FileWriter("Generated Outlex Files/" + fileName2[0] + ".outsyntaxerrors");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean parse() {
		stack.push("$");
		stack.push("START");
		boolean error = false;
		Token token;
		
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
				token = lex.nextToken();
				
			// check if stack value is terminal 
			} else if (parsingTable.findColumn(x) != -1) {
				if (x.equals(token.getType())) {
					terminals.add(x);
					stack.pop();
					token = lex.nextToken();
				} else {
					System.out.println("Error");
					error = true;
				}
				
			// stack value is nonterminal
			} else {
				String val = (String) parsingTable.getValueAt(parsingTable.findRow(x), parsingTable.findColumn(token.getType()));
				if (val instanceof String) {
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
					System.out.println("Error");
					error = true;
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
		
		// check if error occurred
		if (!token.getType().equals("$") || error == true) {
			System.out.println("Error");
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
	
}
