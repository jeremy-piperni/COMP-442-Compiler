import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class lexdriver {

	public static void main(String[] args) {
		
		String file = "Testing Source Files/lexpositivegrading.src";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			//String currentLine = reader.readLine();
			//System.out.println(currentLine);
			Token token = lexicalanalyzer.nextToken(reader);
			System.out.println(token);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		

	}

}
