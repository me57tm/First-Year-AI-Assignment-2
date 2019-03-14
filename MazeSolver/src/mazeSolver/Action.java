package mazeSolver;

import lejos.utility.Delay;

/**
 * Mostly uses Coordinator methods to implement all typical actions the robot
 * takes while mapping and solving the maze.
 */
public class Action
{

	/**
	 * Measuring fields around the robot if they have not already been measured
	 * before. Fetching each one sample. Avoids re-measuring already measured
	 * squares as measurements are reliable
	 */
	public static void lookForWalls(CustomOccupancyMap map)
	{
		// For
		for (int i = -90; i < 180; i += 90)
		{
			int[] tile = map.getSquareInDirection(i);
			if (map.getMazeMap()[tile[0]][tile[1]] == 0)
			{
				Coordinator.ROTATION_MOTOR.rotateTo(i);
				Coordinator.IRSampler.fetchSample(Coordinator.IR, 0);
				// TODO Delay necessary? Try to do without delay
				Delay.msDelay(50);

				if (Coordinator.IR[0] < 25)
					// Set to wall
					map.updateMazeMap(tile[0], tile[1], -1);
				else
					// Set to path
					map.updateMazeMap(tile[0], tile[1], 1);
			}
		}
		Coordinator.ROTATION_MOTOR.rotateTo(0);
	}

	/**
	 * Move from one Path to the next Path. Tries to drive front, then right,
	 * then left, then back
	 * 
	 * @param map
	 */
	public static void moveToNextSquareDumb(CustomOccupancyMap map)
	{
		int[][] mazeMap = map.getMazeMap();

		int[] leftPosition = map.getSquareInDirection(-90);
		int[] frontPosition = map.getSquareInDirection(0);
		int[] rightPosition = map.getSquareInDirection(90);

		if (mazeMap[frontPosition[0]][frontPosition[1]] == 1)
		{
			Coordinator.pilot.travel(Coordinator.DISTANCE);
			map.updateRobotPosition();
		}
		else if (mazeMap[rightPosition[0]][rightPosition[1]] == 1)
		{
			Coordinator.pilot.rotate(90);
			map.updateOrientation(90);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
			map.updateRobotPosition();

		}
		else if (mazeMap[leftPosition[0]][leftPosition[1]] == 1)
		{
			Coordinator.pilot.rotate(-90);
			map.updateOrientation(-90);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
			map.updateRobotPosition();
		}
		else
		{
			Coordinator.pilot.rotate(180);
			map.updateOrientation(180);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
			map.updateRobotPosition();
		}
		int[] robotPosition = map.getRobotPosition();
		map.updateMazeMap(robotPosition[0], robotPosition[1], 1);
	}

	/**
	 * Intelligent version: Make next move in exploring the maze. Try to move to an unexplored
	 * square, otherwise backtrack with help of the visitStack
	 * 
	 * @param map
	 */
	public static void makeNextMove(CustomOccupancyMap map)
	{
		int[][] mazeMap = map.getMazeMap();

		// squares out of the view of the robot on the left, front and right
		int[] left = map.getSquareInDirection(-90);
		int[] front = map.getSquareInDirection(0);
		int[] right = map.getSquareInDirection(90);

		boolean goLeft = false;
		boolean goFront = false;
		boolean goRight = false;

		// If this is an outer wall (otherwise out of bounds with following getSquare-method)
		if (!map.isOuterWall(left, map))
		{
			// Get square after this one
			int[] left2 = map.getSquareInDirection(left, -90);
			// If it's unexplored, set move to go there
			if (mazeMap[left2[0]][left2[1]] == 0)
				goLeft = true;
		}
		if (!map.isOuterWall(front, map))
		{
			int[] front2 = map.getSquareInDirection(front, 0);
			if (mazeMap[front2[0]][front2[1]] == 0)
				goFront = true;
		}
		if (!map.isOuterWall(right, map))
		{
			int[] right2 = map.getSquareInDirection(left, 90);
			if (mazeMap[right2[0]][right2[1]] == 0)
				goRight = true;
		}
		
		
		

		// Execute movements
		if (goLeft)
		{
			Coordinator.pilot.travel(Coordinator.DISTANCE);
			map.updateRobotPosition();
			map.visitStack.push(left);
		}
		else if (goFront)
		{
			Coordinator.pilot.rotate(90);
			map.updateOrientation(90);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
			map.updateRobotPosition();
			map.visitStack.push(front);

		}
		else if (goRight)
		{
			Coordinator.pilot.rotate(-90);
			map.updateOrientation(-90);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
			map.updateRobotPosition();
			map.visitStack.push(right);
		}
		// Otherwise backtrack to the previous square
		else
		{
			if (!map.visitStack.isEmpty())
			{
				int[] backtrackSquare = map.visitStack.pop();
				int angle = map.getAngle(backtrackSquare);
				Coordinator.pilot.rotate(angle);
				map.updateOrientation(angle);
				Coordinator.pilot.travel(Coordinator.DISTANCE);
				map.updateRobotPosition();
			}
			// Illegal maze since no path has led to the end of the maze that would terminate this movement call - see mazeStep
			else
				System.exit(1);
		}
		int[] robotPosition = map.getRobotPosition();
		// Set square the robot moved to to a path
		map.updateMazeMap(robotPosition[0], robotPosition[1], 1);
	}
}
