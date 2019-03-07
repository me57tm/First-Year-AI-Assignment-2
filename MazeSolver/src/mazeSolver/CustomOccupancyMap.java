package mazeSolver;

/**
 * Custom Map Object to represent a to-be-explored Maze.
 * @author jonasschaefer
 *
 */
public class CustomOccupancyMap
{
	/**
	 * Representation of the maze: every entry has value -1 for an obstacle, 0 for unknown and 1 for a path surface.
	 */
	private static int[][] mazeMap; 
	
	/**
	 * Current position of the robot in the arrayMap (array of length 2 with value 0 = width and value 1 = length).
	 */
	private static int[] robotPosition;
	
	/**
	 * Current number of walls.
	 */
	private static int numberOfWalls;
	
	/**
	 * Current number of unknowns.
	 */
	private static int numberOfUnknowns;
	
	/**
	 * Current number of paths.
	 */
	private static int numberOfPaths;
	
	/** 
	 * Creates arrayMap of size of parameters.
	 * @param width
	 * Width - The total number of sections being either walls or paths.
	 * @param length
	 * Length - The total number of sections being either walls or paths.
	 */
	public CustomOccupancyMap(int width, int length) {
		mazeMap = new int[width][length];
		
		// Set map to unknown
		for (int i = 0; i < mazeMap.length; i++) 
			for (int j = 0; j < mazeMap[0].length; j++)
				mazeMap[i][j] = 0;
		
		// Set origin to a Path
		robotPosition = new int[] {0,0};
		mazeMap[0][0] = 1;
		
		numberOfWalls = 0;
		numberOfUnknowns = width*length - 1;
		numberOfPaths = 1;
	}
	
	/**
	 * Returns whether or not the maze is fully mapped (only works if maze is fully accessible).
	 * @return boolean
	 * Whether the maze is fully mapped or not (i.e. has unknown spots).
	 */
	public boolean hasMappedWholeMaze() {
		if (numberOfUnknowns == 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Gets mazeMap array.
	 * @return mazeMap
	 * mazeMap.
	 */
	public int[][] getMazeMap() {
		return mazeMap;
	}
	
	/**
	 * Updates maze map at position [width][length] to the new value.
	 * @param width
	 * The width-position.
	 * @param length
	 * The length-position.
	 * @param value
	 * The new value assigned.
	 */
	public void updateMazeMap(int width, int length, int value) {
		// if (value > 1 || value < -1)
		//		illegal value - error message
		mazeMap[width][length] = value;
	}
	
	/**
	 * Returns position of the Robot in the maze.
	 * @return robotPosition
	 * Position of the robot.
	 */
	public int[] getRobotPosition() {
		return robotPosition;
	}
	
	/**
	 * Updates robotPosition, execute when robot moves. 
	 * @param orientation
	 * The orientation the robot faces for the movement.
	 */
	public void updateRobotPosition(int orientation) {
		if (orientation == 0)
			robotPosition[1]++;
		if (orientation == 90)
			robotPosition[0]++;
		if (orientation == 180)
			robotPosition[1]--;
		if (orientation == 270)
			robotPosition[0]--;
	}
	
	/**
	 * Getter to get the width of the mazeMap.
	 * @return mazeMap.length
	 * The width of the mazeMap.
	 */
	public int getMapWidth() {
		return mazeMap.length;
	}
	
	/**
	 * Getter to get the length of the mazeMap.
	 * @return mazeMap[0].length
	 * The length of the mazeMap.
	 */
	public int getMapLength() {
		return mazeMap[0].length;
	}
}
