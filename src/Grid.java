
public class Grid {

	public String[] hword;
	
	public Grid() {
		hword = new String[4];
		for ( int i = 0; i < hword.length; i++ )
			hword[i]="    ";
	}

	public String getHWord(int c) {
		return hword[c];
	}
	
	public String getVWord(int c) {
		String vword = "";
		for ( int i = 0; i < hword.length; i++ )
			vword = vword + hword[i].charAt(c);		
		return vword;
	}
	
	public void setWord(int row, String word) {
		hword[row] = word;
		// Pad with spaces, if necessary
		for ( int i = word.length(); i < 4; i++ )
			hword[row] = hword[row] + " ";
		for ( int i = row+1; i < hword.length; i++ )
			hword[i] = "    ";
	}
	
	public String toString() {
		String g = "";
		for ( int i = 0; i < hword.length; i++ )
			g = g + hword[i] + "\n";	
		return g;
	}
	
	/*
	 * compares horizontal and vertical words
	 */
	public boolean hasDuplicates() {
		for ( int i = 0; i < 4; i++ )
			for ( int j = 0; j < 4; j++ )
			if ( this.getHWord(i).equals(this.getVWord(j)) || (this.getHWord(i).equals(this.getVWord(j)) && i!=j) )
				return true;
		return false;		
	}
	
	
	
	/*
	 * only compares horizontal words against vertical words
	 */
	public boolean hasVerticalDuplicates() {
		for ( int i = 0; i < 4; i++ )
			for ( int j = 0; j < 4; j++ )
			if ( this.getHWord(i).equals(this.getVWord(j)) )
				return true;
		return false;		
	}
}
