// a dictionary of n-letter words.
// this is much larger than it needs to be--words are rare
// but memory is cheap
			// a=97. a-97=0. 0*26=0, so a, aa, aaa, aaaa are all zero?
			// should we use 96 instead and pad it (leave zero position blank)?
	// just thinking about using one dictionary to rule them all
	// a dictionary this size could store 1,2,3,and 4 letter words without collision
public class Dict {
	private boolean[] d;
	private int l;

	public Dict(int wordLength) {
		this.l = wordLength;
		this.d = new boolean[(int) Math.pow(27, wordLength)];
		// initialize all to false
		for ( int i = 0; i < this.d.length; i++ )
			this.d[i] = false;
	}

	public int getHash(String w) {
		//System.out.println("getting hash for " + w);
		int hashVal = 0;
//		for ( int i = 0; i < this.l; i++ )
		for ( int i = 0; i < w.length(); i++ )
			hashVal += ((int) w.charAt(i) -96) * (Math.pow(26,i));
		return hashVal;	
	}
	
	public void addWord(String w) {
		//if (getHash(w)>this.d.length) System.out.println(w + " evaluates to " + getHash(w));
		this.d[getHash(w)] = true;
	}
	
	public boolean isWord(String w) {
		//System.out.println("isString(" + w + ") : " + this.d[getHash(w)]);
		return this.d[getHash(w)];
	}
}
