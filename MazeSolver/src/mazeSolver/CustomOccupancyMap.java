package mazeSolver;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Stack;

import lejos.hardware.lcd.LCD;

/**
 * Custom Map Object to represent a to-be-explored Maze.
 */
public class CustomOccupancyMap implements Serializable
{
	/**
	 * To be able to send and receive Objects via Bluetooth
	 */
	private static final long serialVersionUID = -1710743261578049661L;

	/**
	 * Stack that pushes the square that the robot is on every time the robot
	 * moves to the next square. Use pop to get the square that the robot came
	 * from
	 */
	public Stack<int[]>       visitStack;

	/**
	 * Representation of the maze: every entry represents a
	 * "square"/"tile"/"place"/... in the maze that has a value -1 for an
	 * obstacle, 0 for unknown and 1 for a pathable surface.
	 */
	private int[][]           mazeMap;

	/**
	 * Orientation the robot is facing (0 = front, 90 = right, 180 = back, 270 =
	 * left) - when bottom-left is seen as start
	 */
	private int               robotOrientation;

	/**
	 * Current position of the robot in the arrayMap (array of length 2 with
	 * value 0 = width and value 1 = height).
	 */
	private int[]             robotPosition;

	/**
	 * The end of the maze
	 */
	private int[]             endTilePosition;

	/**
	 * Creates arrayMap of size of parameters.
	 * 
	 * @param width
	 *            The total number of sections being either walls or paths in
	 *            width.
	 * @param height
	 *            The total number of sections being either walls or paths in
	 *            height.
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
		
		// Origin is a path
		mazeMap[1][1] = 1;

		robotPosition = new int[] { 1, 1 };
		robotOrientation = orientation;

		visitStack = new Stack<int[]>();
	}

	/**
	 * Returns angle to turn to the square
	 * 
	 * @param square
	 *            coordinates to turn to
	 * @return angle
	 */
	public int turnToSquare(int[] square)
	{
		LCD.clear();
		LCD.drawString(String.valueOf(square[0] + " " + String.valueOf(square[1])), 0, 2);
		Coordinator.buttons.waitForAnyPress();
		LCD.clear();
		int[] diff = new int[] { square[0] - robotPosition[0], square[1] - robotPosition[1] };
		// Check for invalid passed squares
		int sumOfDistances = 0;
		for (int i = 0; i < 2; i++)
			sumOfDistances += diff[i];
		// Valid if it is a square to move on and in a line for the robot
		boolean invalid = !((sumOfDistances % 2 == 0 && (diff[0] == 0 || diff[1] == 0))) || Arrays.equals(square, robotPosition);

		// End program if invalid
		if (invalid)
			System.exit(1);
		
		//if in direction 0
		if (diff[1] > 0)
		{
			int turnby = -180;
			while ((robotOrientation + turnby) % 360 != 0)
				turnby += 90;
			return turnby;
		}
		
		//if in direction 90
		if (diff[0] > 0)
		{
			int turnby = -180;
			while ((robotOrientation + turnby) % 360 != 90)
				turnby += 90;
			return turnby;
		}
			
		//if in direction 270
		if (diff[0] < 0)
		{
			int turnby = -180;
			while ((robotOrientation + turnby) % 360 != 270)
				turnby += 90;
			return turnby;
		}
		
		//if in direction 180
		if (diff[1] < 0)
		{
			int turnby = -180;
			while ((robotOrientation + turnby) % 360 != 180)
				turnby += 90;
			return turnby;
		}
		
		// Illegal state
		LCD.clear();
		LCD.drawString("Illegal state", 0, 0);
		Coordinator.buttons.waitForAnyPress();
		LCD.clear();
		return 0;
	}
	

	/**
	 * Gets mazeMap array.
	 * 
	 * @return mazeMap.
	 */
	public int[][] getMazeMap()
	{
		return mazeMap;
	}

	/**
	 * Updates the current tile to the new value.
	 * 
	 * @param width
	 *            The width-position of the tile.
	 * @param length
	 *            The length-position of the tile.
	 * @param value
	 *            The new value assigned, -1 for wall, 0 for unknown (should not
	 *            be used) and 1 for path.
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
	 * Getter for the position of the end tile
	 * 
	 * @return end tile position
	 */
	public int[] getEndTilePosition()
	{
		if (endTilePosition == null)
			return null;
		return endTilePosition;
	}

	/**
	 * Setter for the end tile
	 * 
	 * @param endTilePosition
	 *            Position of the maze end in the 2D array
	 */
	public void setEndTilePosition(int[] endOfMazePosition)
	{
		this.endTilePosition = endOfMazePosition;
	}

	/**
	 * Returns current orientation.
	 * 
	 * @return orientation
	 */
	public int getRobotOrientation()
	{
		return robotOrientation;
	}

	/**
	 * Updates orientation of robot relative to the Maze by the number of
	 * degrees the robot is instructed to turn.
	 * 
	 * @param degrees
	 *            Degrees of turning.
	 */
	public void updateRobotOrientation(int degrees)
	{
		robotOrientation += degrees;
		if (robotOrientation >= 360)
			robotOrientation -= 360;
		if (robotOrientation < 0)
			robotOrientation += 360;
	}

	/**
	 * Function to return positions of a square adjacent to the robot
	 * 
	 * @param direction
	 *            to face to check for square (out of the view of the robot): 0
	 *            = front, 90/-270 = right, 180/-180 = behind, 270/-90 = left.
	 *            Can take negatives too.
	 * @return Square Coordinates as int[2]
	 */
	public int[] getSquareInDirection(int direction)
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

	/**
	 * Returns neighbour in defined direction of a given square
	 * 
	 * @param square
	 *            given square
	 * @param direction
	 *            direction to look to from given square
	 * @return square in direction from the given square
	 */
	public int[] getSquareInDirection(int[] square, int direction)
	{
		direction += robotOrientation;

		if (direction >= 360)
			direction -= 360;
		if (direction < 0)
			direction += 360;
		
		if (direction == 0)
			return new int[] { square[0], square[1] + 1 };
		if (direction == 90)
			return new int[] { square[0] + 1, square[1] };
		if (direction == 180)
			return new int[] { square[0], square[1] - 1 };
		if (direction == 270 || direction == -90)
			return new int[] { square[0] - 1, square[1] };
		//Wrong input
		return null;
	}

	/**
	 * Returns true if a square is a outer wall
	 * 
	 * @param square
	 *            the square to test
	 * @return
	 */
	public boolean isOuterWall(int[] square)
	{
		if (square[0] == 0 || square[0] == getMazeMap().length || square[1] == 0 || square[1] == getMazeMap()[0].length)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @return number of Walls
	 */
	public int getNumberOfWalls()
	{
		int numberOfWalls = 0;
		for (int i = 0; i < mazeMap.length; i++)
			for (int j = 0; j < mazeMap[0].length; j++)
			{
				if (mazeMap[i][j] == -1)
					numberOfWalls++;
			}
		return numberOfWalls;
	}

	/**
	 * 
	 * @return number of Paths
	 */
	public int getNumberOfPaths()
	{
		int numberOfPaths = 0;
		for (int i = 0; i < mazeMap.length; i++)
			for (int j = 0; j < mazeMap[0].length; j++)
			{
				if (mazeMap[i][j] == 1)
					numberOfPaths++;
			}
		return numberOfPaths;
	}

	/**
	 * 
	 * @return number of Unknowns
	 */
	public int getNumberOfUnknowns()
	{
		int numberOfUnknowns = 0;
		for (int i = 0; i < mazeMap.length; i++)
			for (int j = 0; j < mazeMap[0].length; j++)
			{
				if (mazeMap[i][j] == 0)
					numberOfUnknowns++;
			}
		return numberOfUnknowns;
	}

	/**
	 * Returns the number of explored squares to determine the progress of the
	 * mapping process
	 * 
	 * @return number of measured squares
	 */
	public int getCompletion()
	{
		return getNumberOfPaths() + getNumberOfWalls();
	}

	/**
	 * Getter to get the width of the mazeMap.
	 * 
	 * @return mazeMap.length - The width of the mazeMap.
	 */
	public int getMapWidth()
	{
		return mazeMap.length;
	}

	/**
	 * Getter to get the length of the mazeMap.
	 * 
	 * @return mazeMap[0].length - The length of the mazeMap.
	 */
	public int getMapLength()
	{
		return mazeMap[0].length;
	}

}
