import javax.swing.*;
import javax.swing.table.*;

public class Table {
	public static void main(String args[]){
	      JFrame frame = new JFrame();
	      JTable table = new JTable(new MyTableModel());
	 
	      frame.add(table);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setSize(600,600);
	      frame.setLocationRelativeTo(null);  
	      frame.setVisible(true);
	   }
	
}

class MyTableModel extends AbstractTableModel {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String[] columnNames = {"state","+","-","*","(",")","{","}","[","]",";",",",".",":","=",">","<","e","L","0","N","_","whitespace","newline","endoffile","final","backtrack"};
	 
	      Object[][] data = {
	       {"state","+","-","*","(",")","{","}","[","]",";",",",".",":","=",">","<","e","L","0","N","_","whitespace","newline","endoffile","final","backtrack"},	  
	       {1,2,3,4,5,6,7,8,9,10,11,12,13,14,17,21,24,29,29,1,1,1,1,1,28,null,null},
	       {2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"plus",null},
	       {3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"minus",null},
	       {4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"mult",null},
	       {5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"openpar",null},
	       {6,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"closepar",null},
	       {7,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"opencubr",null},
	       {8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"closecubr",null},
	       {9,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"opensqbr",null},
	       {10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"closesqbr",null},
	       {11,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"semi",null},
	       {12,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"comma",null},
	       {13,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"dot",null},
	       {14,16,16,16,16,16,16,16,16,16,16,16,16,15,16,16,16,16,16,16,16,16,16,16,16,null,null},
	       {15,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"scopeop",null},
	       {16,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"colon","yes"},
	       {17,19,19,19,19,19,19,19,19,19,19,19,19,19,18,20,19,19,19,19,19,19,19,19,19,null,null},
	       {18,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"eq",null},
	       {19,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"assign","yes"},
	       {20,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"returntype",null},
	       {21,23,23,23,23,23,23,23,23,23,23,23,23,23,22,23,23,23,23,23,23,23,23,23,23,null,null},
	       {22,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"geq",null},
	       {23,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"gt","yes"},
	       {24,26,26,26,26,26,26,26,26,26,26,26,26,26,25,27,26,26,26,26,26,26,26,26,26,null,null},
	       {25,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"leq",null},
	       {26,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"lt","yes"},
	       {27,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"noteq",null},
	       {28,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"end",null},
	       {29,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,29,29,29,29,29,30,30,30,null,null},
	       {30,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,"id","yes"},
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
	  
	   public Object getValueAt(int row, int col) {
	       return data[row][col];
	   }
	 
	}
