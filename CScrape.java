import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class CScrape {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		int lineNumber = 1;
		
		System.out.print("Enter input file: ");
		String inFile = "C:\\Users\\bryce\\OneDrive\\CSC110\\CSC110\\src\\" + keyboard.next( );
		String outFile = inFile.substring(0, inFile.indexOf(".")) + ".txt";
		//System.out.println(outFile); ~ CScrape.txt
		File outputFile = new File(outFile);
		try {
			
			outputFile.createNewFile();
		}catch (IOException e) {
			System.out.println("An error has occured: " + e.getMessage());
		}
		
		
		try {
			File reader = new File(inFile);
			Scanner in = new Scanner(reader);
			PrintWriter out = new PrintWriter(outputFile);
			out.println("	Comment Scrape - Bryce Perez");
			out.println();
			while (in.hasNextLine()) {
				String line = in.nextLine();
				//have the lines, now need to filter them by // and /* characters
				for(int i = 0; i < line.length(); i++) {
					if( line.charAt(i) == '/') {
						if(i+1 <= line.length() - 1 && line.charAt(i+1) == '/') {
							//get substring to include whole comment
							String comment = line.substring(line.indexOf(line.charAt(i)));
							//then out.println(lineNumber + ": " + substring);
							out.println(lineNumber + ": " + comment);
							
							
						}else if (i+1 <= line.length() - 1 && line.charAt(i+1) == '*') {
							String comment = line.substring(line.indexOf('/'),line.lastIndexOf('/') + 1);
							out.println(lineNumber + ": " + comment);
						}
					}
					
				}
				lineNumber++;
			}
			/* close scanners */
			in.close();
			out.close();
			
		} catch(IOException exception) {
			System.out.println("Error processing the file: " + exception.getMessage());
		}
		/* comment test */
		System.out.println("Comments from " + inFile + " have been written to " + outFile + ".");
		keyboard.close();

	}

}
