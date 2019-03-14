package mazeSolver;

import java.io.Serializable;

/**
 * Custom Map Object to represent a to-be-explored Maze.
 * 
 * @author jonasschaefer
 *
 */
public class CustomOccupancyMap implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1710743261578049661L;

	private int               robotOrientation;

	/**
	 * Representation of the maze: every entry has value -1 for an obstacle, 0
	 * for unknown and 1 for a path surface.
	 */
	private int[][]           mazeMap;

	/**
	 * Current position of the robot in the arrayMap (array of length 2 with
	 * value 0 = width and value 1 = height).
	 */
	private int[]             robotPosition;

	/**
	 * Current number of walls.
	 */
	private int               numberOfWalls;

	/**
	 * Current number of unknowns.
	 */
	private int               numberOfUnknowns;

	/**
	 * Current number of paths.
	 */
	private int               numberOfPaths;

	/**
	 * Creates arrayMap of size of parameters.
	 * 
	 * @param width
	 *            The total number of sections being either walls or paths.
	 * @param height
	 *            The total number of sections being either walls or paths.
	 */
	public CustomOccupancyMap(int width, int height, int orientation)
	{
		mazeMap = new int[width][height];

		// Set map to unknown apart from outer walls
		for (int i = 0; i < mazeMap.length; i++)
			for (int j = 0; j < mazeMap[0].length; j++)
			{
				if (i == 0 || j == 0 || i == mazeMap.length - 1 || j == mazeMap[0].length - 1)
					mazeMap[i][j] = -1;
				else
					mazeMap[i][j] = 0;
			}
		robotPosition = new int[] { 1, 1 };
		// Set origin to a Path
		mazeMap[1][1] = 1;

		robotOrientation = orientation;

		numberOfWalls = 2 * (width - 1) + 2 * (height - 1);
		numberOfPaths = 1;
		numberOfUnknowns = width * height - numberOfWalls - numberOfPaths;
	}

	/**
	 * 
	 * @return number of measured squares
	 */
	public int getCompletion()
	{
		return numberOfWalls + numberOfPaths;
	}

	/**
	 * Gets mazeMap array.
	 * 
	 * @return mazeMap mazeMap.
	 */
	public int[][] getMazeMap()
	{
		return mazeMap;
	}

	/**
	 * Updates maze map at position [width][length] to the new value.
	 * 
	 * @param width
	 *            The width-position.
	 * @param length
	 *            The length-position.
	 * @param value
	 *            The new value assigned.
	 */
	public void updateMazeMap(int width, int height, int value)
	{
		mazeMap[width][height] = value;
	}

	/**
	 * Returns position of the Robot in the maze.
	 * 
	 * @return robotPosition Position of the robot.
	 */
	public int[] getRobotPosition()
	{
		return robotPosition;
	}

	/**
	 * Updates robotPosition, execute when robot moves.
	 * 
	 * @param orientation
	 *            The orientation the robot faces for the movement.
	 */
	public void updateRobotPosition()
	{
		if (robotOrientation == 0)
			robotPosition[1] += 2;
		if (robotOrientation == 90)
			robotPosition[0] += 2;
		if (robotOrientation == 180)
			robotPosition[1] -= 2;
		if (robotOrientation == 270)
			robotPosition[0] -= 2;
	}

	/**
	 * Updates orientation of robot relative to the Maze by the number of
	 * degrees the robot is instructed to turn.
	 * 
	 * @param degrees
	 *            Degrees of turning.
	 */
	public void updateOrientation(int degrees)
	{
		robotOrientation += degrees;
		if (robotOrientation > 270)
			robotOrientation -= 360;
		if (robotOrientation < 0)
			robotOrientation += 360;
	}

	/**
	 * Returns current orientation.
	 * 
	 * @return orientation orientation
	 */
	public int getOrientation()
	{
		return robotOrientation;
	}

	/**
	 * Getter to get the width of the mazeMap.
	 * 
	 * @return mazeMap.length The width of the mazeMap.
	 */
	public int getMapWidth()
	{
		return mazeMap.length;
	}

	/**
	 * Getter to get the length of the mazeMap.
	 * 
	 * @return mazeMap[0].length The length of the mazeMap.
	 */
	public int getMapLength()
	{
		return mazeMap[0].length;
	}

	/**
	 * Function to return positions of a square adjacent to the robot
	 * 
	 * @param direction
	 *            to face to check for square (out of the view of the robot): 0
	 *            = front, 90/-270 = right, 180/-180 = behind, 270/-90 = left.
	 *            Can take negatives too.
	 * @return int[2] Square Coordinates
	 */
	public int[] getSquare(int direction)
	{
		direction += robotOrientation;

		if (direction >= 360)
			direction -= 360;
		if (direction < 0)
			direction += 360;

		if (direction == 0)
			return new int[] { robotPosition[0], robotPosition[1] + 1 };
		if (direction == 90)
			return new int[] { robotPosition[0] + 1, robotPosition[1] };
		if (direction == 180)
			return new int[] { robotPosition[0], robotPosition[1] - 1 };
		if (direction == 270 || direction == -90)
			return new int[] { robotPosition[0] - 1, robotPosition[1] };
		// Wrong input
		return null;
	}

	// TODO How does this work? What does it do? 
	/**
	 * It takes a set of coordinates
	 * @param coords
	 * coords to get to
	 * @return
	 * The angle the robot needs to turn (by?to?)
	 */
	public int getAngle(int[] coords)
	{
		int[] diff = new int[] { coords[0] - robotPosition[0], coords[1] - robotPosition[1] };
		// Check for invalid passed squares
		int sumOfDistances = 0;
		for (int i = 0; i < 2; i++) 
			sumOfDistances += diff[i];
		
		boolean validCoords = (sumOfDistances == 2 && (diff[0] == 0 || diff[1] == 0));
		
		int direction = -1;
		
		if (validCoords)
		{	
			if (diff[0] > 0)
				direction = 90;
			if (diff[0] < 0)
				direction = 270;
			if (diff[1] > 0)
				direction = 0;
			if (diff[1] < 0)
				direction = 180;
			if (direction > 360)
				direction -= 360;
		}
		return direction;
	}
}
