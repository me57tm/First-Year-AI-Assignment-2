package MazeSolver;

/**
 * Custom Map Object
 * @author jonasschaefer
 *
 */
// always bottom left, front is width
public class CustomOccupancyMap
{
	public static int[][] arrayMap; // What sizing to use?
	/*
	 * value == -1 -> Wall
	 * value == 0 -> Unknown
	 * value == 1 -> Path
	 */
	
	
	/** Creates arrayMap of size of parameters
	 * @param width
	 * @param length
	 */
	public CustomOccupancyMap(int width, int length) {
		arrayMap = new int[width][length];
	}
	
	/**
	 * Initialises arrayMap (everything unknown apart from path at [0][0])
	 */
	public void initializeArrayMap() {
		
		for (int i = 0; i < arrayMap.length; i++) 
			for (int j = 0; j < arrayMap[0].length; j++)
				arrayMap[i][j] = -1;
		
		arrayMap[0][0] = 1;
	}
}
