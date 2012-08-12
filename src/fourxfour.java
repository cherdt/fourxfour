import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


public class fourxfour {

	static Object[] wordArray;
	static LinkedList<String> wordList;
	static boolean[] prefixes;
	static Dict[] dict = new Dict[4];
	static boolean allowDuplicates = false;
	static Dict oneDict = new Dict(4);
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		//start
		long start = System.currentTimeMillis();
		
		// input file -- could come from arg[]http://127.0.0.1:50917/help/ntopic/org.eclipse.platform.doc.user/images/ref-spellingpref.png
		String inputFile = "four-letter-words.txt";
		inputFile = "8words.txt";
		inputFile = "a-few-words.txt";
		inputFile = "sorted4.dictionary";

		// Count tests
		long count = 0;
		// Count good grids
		int successes = 0;
		
		// Init dictionary
		for ( int i=0; i < 4; i++ )
			dict[i] = new Dict(i+1);

		
		
		wordList = new LinkedList<String>();
		
		// read file
		Scanner scan = new Scanner(System.in);
		Scanner scanFile = new Scanner(new File(inputFile));
		while (scanFile.hasNextLine()) {
			String nextWord = scanFile.nextLine().toLowerCase();
			wordList.add(nextWord);
			// add to dictionaries
			for ( int i = 1; i <= nextWord.length(); i ++ ){
				//System.out.println("Adding " + nextWord.substring(0, i));
				oneDict.addWord(nextWord.substring(0, i));
			}
		}
		
		// turn it into an array
		wordArray = wordList.toArray();

		
		// init prefixes
		prefixes = new boolean[26*26];
		for ( int p = 0; p < prefixes.length; p++ )
			prefixes[p] = false;
		checkPrefixes();
		printPrefixes();		
		
		for ( int w = 0; w < wordArray.length; w++ ) {
			count++;
			Grid g = new Grid();
			g.hword[0] = (String) wordArray[w];
			
			
			for ( int x = 0; x < wordArray.length; x++ ) {
				count++;
									
				//g.hword[1] = (String) wordArray[x];
				g.setWord(1, (String) wordArray[x]);

				
// We could potentially here perform fewer checks by skipping entire word sections
//				while ( prefixFailsAt(g) < 4 && x < wordArray.length-1) 
//					g.hword[1] = (String) wordArray[++x];
/*
* where does prefix fail? x
* while next word letter x == fail word letter x next word
* 
* 					
*/

				
				while ( prefixFailsAt(g) < 4 ) {
					int failsAt = prefixFailsAt(g);
					while ( ++x < wordArray.length && g.hword[1].charAt(failsAt) == ((String) wordArray[x]).charAt(failsAt) )
						; // do nothing
					if ( x < wordArray.length )
						g.setWord(1, (String) wordArray[x]);
						//g.hword[1] = (String) wordArray[x];
					else
						break;
				}

				
					if ( isPrefix(g) )
							
						for ( int y = 0; y < wordArray.length; y++ ) {
							count++;
							//g.hword[2] = (String) wordArray[y];
							g.setWord(2, (String) wordArray[y]);

							/* try this mess again */
							while ( prefixFailsAt(g) < 4 ) {
								int failsAt = prefixFailsAt(g);
								while ( ++y < wordArray.length && g.hword[2].charAt(failsAt) == ((String) wordArray[y]).charAt(failsAt) )
									; // do nothing
								if ( y < wordArray.length )
									g.setWord(2, (String) wordArray[y]);
									//g.hword[2] = (String) wordArray[x];
								else
									break;
							}
							
	
							if ( isPrefix(g) )
							
								for ( int z = 0; z < wordArray.length; z++ ) {
									count++;
									//g.hword[3] = (String) wordArray[z];
									g.setWord(3, (String) wordArray[z]);
		
									// Final check
									if ( isPrefix(g) && ( allowDuplicates || !g.hasDuplicates() ) ) 
										System.out.println("Grid #" + (++successes) +"\n" + g + "\n");
								}
													
						}
				
			}
			
		}
		

		if ( Collections.binarySearch(wordList,"cxsh") > 0 )
			System.out.println("it's there");
		else
			System.out.println("it's not there");
		
		System.out.println("Combination count: " + count);
		
		long stop = System.currentTimeMillis();
		
		System.out.println((stop-start)/1000 + " seconds running time");
		
	}


	
	public static boolean isPrefix(Grid g) {
		for ( int i = 0; i < 4; i++ ) {
			String wordToCheck = g.getVWord(i).trim();
			if ( !oneDict.isWord(wordToCheck) )
				return false;
//			if ( !dict[wordToCheck.length()-1].isWord(wordToCheck) ) 
//				return false;
		}
		return true;
	}

	
	public static int prefixFailsAt(Grid g) {
		for ( int i = 0; i < 4; i++ ) {
			String wordToCheck = g.getVWord(i).trim();
			if ( !oneDict.isWord(wordToCheck) )
				return i;
//			if ( !dict[wordToCheck.length()-1].isWord(wordToCheck) ) 
//				return i;
		}
		return 4;
	}
	
	
	public static int prefixFailsAtSearch(Grid g) {
		for ( int i = 0; i < 4; i++ ) {
			boolean isWordPrefix = true;
			int loc = Math.abs( Collections.binarySearch(wordList, g.getVWord(i).trim()) );
			if ( loc < wordArray.length ) {
				String nextWord = (String) wordArray[ loc ];
				boolean allLettersMatch = true;
				for ( int j = 0; j < g.getVWord(i).trim().length(); j++ ) 
					allLettersMatch = allLettersMatch && (nextWord.charAt(j) == g.getVWord(i).charAt(j));
				isWordPrefix = allLettersMatch;
					
			} else {
				isWordPrefix = false;
			}
			if ( !isWordPrefix )
				return i;
		}
		
		return 4;
	}
	
	public static boolean isPrefixSearch(Grid g) {
		boolean areAllWordsPrefixes = true;
		for ( int i = 0; i < 4; i++ ) {
			boolean isWordPrefix = true;
			int loc = Math.abs( Collections.binarySearch(wordList, g.getVWord(i).trim()) );
			if ( loc < wordArray.length ) {
				String nextWord = (String) wordArray[ loc ];
				boolean allLettersMatch = true;
				for ( int j = 0; j < g.getVWord(i).trim().length(); j++ ) 
					allLettersMatch = allLettersMatch && (nextWord.charAt(j) == g.getVWord(i).charAt(j));
				isWordPrefix = allLettersMatch;
//				if ( g.getVWord(i).trim().length() == 2 && isWordPrefix ) {
//					// update prefixes array
//					int index = ((int) nextWord.charAt(0)-97)*26 + ((int) nextWord.charAt(1)-97);
//					prefixes[index] = true;
//				}
					
			} else {
				isWordPrefix = false;
			}
			areAllWordsPrefixes = areAllWordsPrefixes && isWordPrefix;
		}
		
		return areAllWordsPrefixes;
	}


	/*
	 * This does not work!!
	 * It is not finding valid prefixes!
	 */
	public static void checkPrefixes() {
		for ( int a = 0; a < 26; a++ )
			for ( int b = 0; b < 26; b++ ) {
				int p = a*26+b;
				String s = "" + ((char) (a+97)) + ((char) (b+97));
				//System.out.println(s);
				int loc = Math.abs( Collections.binarySearch(wordList, s.trim() ) );
				if ( loc < wordArray.length ) {
					// why loc-1?!
					String nextWord = (String) wordArray[ loc-1 ];
					//System.out.println( loc + ":" +  nextWord + ":" + s + "?");
					if ( nextWord.charAt(0) == s.charAt(0) && nextWord.charAt(1) == s.charAt(1) )
						prefixes[p] = true;
					else
						prefixes[p] = false;
				} else {
					prefixes[p] = false;
				}
			}
		
	}
	
	public static int prefixIndex(String s) {
		int a = s.charAt(0) - 97;
		int b = s.charAt(1) - 97;
		return a*26+b;
	}
	
	public static void printPrefixes() {	
		int count = 0;
		System.out.println("Prefixes array:");
		for ( int p=0; p<prefixes.length; p++) {
			System.out.println( (" " + ((char) (p/26+97))) + "" + ((char) (p%26+97)) + ": " + prefixes[p]);
			if ( prefixes[p] )
				count++;
		}
		//System.out.println(((char) (0/26+97)));
		//System.out.println( (char) 97 + "" + (char) 101);
		System.out.println(count + " valid 2-letter prefixes");
	}

	
	public static boolean search(String word) {
		
		return false;
	}
	
}
