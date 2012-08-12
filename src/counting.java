
public class counting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//start
		long start = System.currentTimeMillis();
		
		System.out.println( Math.pow(26, 16));
		
		long count = 0;
		
		//for ( int i = 1; i <= Math.pow(26, 8); i++ )
		//	System.out.println(++count + " bottles of beer on the wall...");
		
		// stop timer
		long stop = System.currentTimeMillis();		
		System.out.println((stop-start)/1000 + " seconds running time");
		
	}

}
