package MazeSolver;

/**
 * Custom Map Object
 * @author jonasschaefer
 *
 */
// always bottom left, front is width
public class CustomOccupancyMap
{
	/**
	 * Representation of the maze: every entry has value -1 for an obstacle, 0 for unknown and 1 for a path surface
	 */
	public static int[][] arrayMap; 
	
	/**
	 * Current number of walls. Must remain updated!
	 */
	public static int numberOfWalls;
	
	/**
	 * Current number of unknowns. Must remain updated!
	 */
	public static int numberOfUnknowns;
	
	/**
	 * Current number of paths. Must remain updated!
	 */
	public static int numberOfPaths;
	
	/** 
	 * Creates arrayMap of size of parameters
	 * @param width
	 * The width of the maze including both walls and paths
	 * @param length
	 * The length of the maze including both walls and paths
	 */
	public CustomOccupancyMap(int width, int length) {
		arrayMap = new int[width][length];
		
		// Set map to unknown
		for (int i = 0; i < arrayMap.length; i++) 
			for (int j = 0; j < arrayMap[0].length; j++)
				arrayMap[i][j] = 0;
		
		// Set origin to a Path
		arrayMap[0][0] = 1;
		
		numberOfWalls = 0;
		numberOfUnknowns = width*length - 1;
		numberOfPaths = 1;
	}
	
	/**
	 * Initialises arrayMap (everything unknown apart from origin)
	 */
	public void initializeArrayMap() {
		
		for (int i = 0; i < arrayMap.length; i++) 
			for (int j = 0; j < arrayMap[0].length; j++)
				arrayMap[i][j] = 0;
		
	}
	
	public boolean hasMappedWholeMaze() {
		
		for (int i = 0; i < arrayMap.length; i++) 
			for (int j = 0; j < arrayMap[0].length; j++)
				if (arrayMap[i][j] == 0)
					return false;
		
		return true;
	}
}
