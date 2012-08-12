import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class tree {

	static Dict dict = new Dict(4);
	static Node tn;
	static int successes = 0;
	static long count = 0;
	
	public static void main(String[] args) throws FileNotFoundException {

		//start
		long start = System.currentTimeMillis();
		
		
		String inputFile = "a-few-words.txt";
		//inputFile = "8words.txt";
		inputFile = "sorted4.dictionary";
		//inputFile = "four-letter-words.txt";
		tn = new Node("a");
		

		Scanner scanFile = new Scanner(new File(inputFile));
		while (scanFile.hasNextLine()) {
			String nextWord = scanFile.nextLine().toLowerCase();
			insert(tn, nextWord);
			for ( int i = 1; i <= nextWord.length(); i ++ ){
				//System.out.println("Adding " + nextWord.substring(0, i));
				dict.addWord(nextWord.substring(0, i));
			}
		}
		
		traverse(tn);

		
		Grid g = new Grid();
		// find grids
		// 1. Start with first word -- DFS, first child without children
		// 2. Second row -- try first node
			// works? try child for next letter
			// doesn't work? try sibling for this letter
		
		// traverse. for each word do:
			// traverse
		traverse(tn,g);
		

		
		// stop timer
		long stop = System.currentTimeMillis();		
		System.out.println((stop-start)/1000 + " seconds running time");

		System.out.println(successes + " good grids");
		System.out.println(count + " combos");
	
	}
	
	public static void traverse( Node n, Grid g ) {
		traverse(n,g,0);
	}
	
	public static void traverse(Node n, Grid g, int row) {
		
		if ( row == 4 && g.getHWord(3).trim().length() == 4 && isPrefix(g) ) {
			if ( !g.hasDuplicates() ) 
				System.out.println("Grid #" + (++successes) + "\n"  + g);
			return;
		}
		if ( n == null ) 
			return;
		g.setWord(row, n.value);
		boolean gridPass = isPrefix(g);
		if ( gridPass )
			if ( n.firstChild == null ) 
				traverse(tn,g,row+1);
			else 
				traverse(n.firstChild,g,row);

		traverse(n.nextSibling,g,row);
	}

	
	public static boolean isPrefix(Grid g) {
		//System.out.println("Now checking grid \n" + g);
		for ( int i = 0; i < g.hword[0].trim().length(); i++ ) {
			String wordToCheck = g.getVWord(i).trim();
			if ( !dict.isWord(wordToCheck) )
				return false;
		}
		return true;
	}
	
	public static void traverse(Node n) {
		System.out.println(n.value + " " + dict.isWord(n.value));
		if ( n.firstChild != null ) 
			traverse(n.firstChild);
		if ( n.nextSibling != null )
			traverse(n.nextSibling);		
	}
	
	public static void insert(Node n, String s) {
		insert(n,s,0);
	}
	
	public static void insert(Node n, String s, int d) {
		if ( d >= s.length() ) 
			return;
		if ( s.startsWith(n.value) ) {
			if ( n.firstChild == null )
				if ( d+2 > s.length() )
					n.firstChild = new Node(s);
				else 
					n.firstChild = new Node(s.substring(0,d+2));
			insert(n.firstChild,s,d+1);
		} else {
			if ( n.nextSibling == null )
				n.nextSibling = new Node(s.substring(0,d+1));
			insert(n.nextSibling,s,d);
		}
	}
	
}
