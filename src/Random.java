
public class Random {

	private static java.util.Random rand = null;
	
	public static java.util.Random get() {
		
		if(rand == null)
			rand = new java.util.Random();
		
		return rand;
	}
	
	public static int fromRange(int from, int to) {
		return (int) (Math.random() * (to - from + 1)) + from;
	}
}
