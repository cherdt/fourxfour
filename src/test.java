import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class test {

	public static Node wordStructure = null;
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		// input file -- could come from arg[]
		String inputFile = "a-few-words.txt";

		String testString = "";
		
		// read file
		Scanner scan = new Scanner(System.in);
		Scanner scanFile = new Scanner(new File(inputFile));
		while (scanFile.hasNextLine()) {
			String nextWord = scanFile.nextLine();
			testString = testTheString(testString,nextWord.charAt(0));
			insertWord(wordStructure, nextWord);
			//System.out.println(wordStructure.value);
			System.out.println(testString);
		}
		
		printNode(wordStructure);
		
	}

	
	public static String testTheString(String s, char a) {
		return  s + " " + a;		
	}

	public static void printNode(Node n) {
		if (n!= null) {
			System.out.println(n.value);
			while ( n.firstChild != null )
				printNode(n.firstChild);
			while ( n.nextSibling != null )
				printNode(n.nextSibling);
		} else {
			System.out.println(" you are trying to print null!");
		}
	}
	
	public static void insertWord(Node n, String w) {
		System.out.println("trying to insert " + w);
		insertFirstLetter(n,w);
	}

	public static void insertFirstLetter(Node n, String w) {

		// If this is a zero-length word, return
		if ( w.length() == 0 )
			return;
		
		// There are letters (nodes)
		if ( n != null) {
		
			// loop through letters
			while ( !n.value.equals(w.charAt(0))  && n.nextSibling != null )
				n = n.nextSibling;
		
			// This is the letter
			if ( n.value.equals(w.charAt(0)) )
				// select node
				// n = n;
				;
			// Letter not found
			else
				// Insert letter
				n.nextSibling = new Node( w.charAt(0)+"" );
		} else {
			// There are no letters
			// Insert letter
			System.out.println("Trying to insert " + w.charAt(0));
			n = new Node(w.charAt(0)+"");
			System.out.println("Node value " + n.value);
			// Do I not understand pass-by-reference?!
			//System.out.println("wordStructure " + wordStructure.value);
			
		}

		// insert subword at node
		insertFirstLetter(n.firstChild, w.substring(1));		
		
	}
	
}
