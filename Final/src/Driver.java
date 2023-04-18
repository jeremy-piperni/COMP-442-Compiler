import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter file name: ");
		String fileName = scanner.nextLine();
		
		String file = "Testing Source Files/" + fileName + ".src";
		lexicalanalyzer lexAnalyzer = new lexicalanalyzer(file);
		
		parser parser = new parser(lexAnalyzer);
		parser.parse();
		scanner.close();
		
	}

}
