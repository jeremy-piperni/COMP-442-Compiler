import javax.swing.*;
import javax.swing.table.*;

public class Table {
	public static void main(String args[]){
	      JFrame frame = new JFrame();
	      JTable table = new JTable(new MyTableModel());
	 
	      frame.add(table);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setSize(400,400);
	      frame.setLocationRelativeTo(null);  
	      frame.setVisible(true);
	   }
	
}

class MyTableModel extends AbstractTableModel {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String[] columnNames = {"state","+","-","*","(",")","{","}","[","]",";",",",".","whitespace","final","backtrack"};
	 
	      Object[][] data = {
	       {"state","+","-","*","(",")","{","}","[","]",";",",",".","whitespace","final","backtrack"},	  
	       {1,2,3,4,5,6,7,8,9,10,11,12,13,1,null,null},
	       {2,1,1,1,1,1,1,1,1,1,1,1,1,1,"plus",null},
	       {3,1,1,1,1,1,1,1,1,1,1,1,1,1,"minus",null},
	       {4,1,1,1,1,1,1,1,1,1,1,1,1,1,"mult",null},
	       {5,1,1,1,1,1,1,1,1,1,1,1,1,1,"openpar",null},
	       {6,1,1,1,1,1,1,1,1,1,1,1,1,1,"closepar",null},
	       {7,1,1,1,1,1,1,1,1,1,1,1,1,1,"opencubr",null},
	       {8,1,1,1,1,1,1,1,1,1,1,1,1,1,"closecubr",null},
	       {9,1,1,1,1,1,1,1,1,1,1,1,1,1,"opensqbr",null},
	       {10,1,1,1,1,1,1,1,1,1,1,1,1,1,"closesqbr",null},
	       {11,1,1,1,1,1,1,1,1,1,1,1,1,1,"semi",null},
	       {12,1,1,1,1,1,1,1,1,1,1,1,1,1,"comma",null},
	       {13,1,1,1,1,1,1,1,1,1,1,1,1,1,"dot",null},
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
