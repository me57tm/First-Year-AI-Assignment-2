package mazeSolver;

import java.io.IOException;
import java.util.Stack;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

/**
 * Implement all actions the robot takes while mapping and solving the maze.
 */
public class Action
{

	/**
	 * Measuring fields around the robot if they have not already been measured
	 * before. Fetching each one sample. Avoids re-measuring already measured
	 * grids as measurements are reliable
	 */
	public static void scanSurrounding(CustomOccupancyMap map)
	{
		for (int i = -90; i < 180; i += 90)
		{
			int[] tile = map.getSquareInDirection(i);
			if (map.getMazeMap()[tile[0]][tile[1]] == 0)
			{
				Coordinator.ROTATION_MOTOR.rotateTo(i);
				float[] IR = new float[1];
				Coordinator.IRSampler.fetchSample(IR, 0);
				// TODO Delay necessary? Try to do without delay
				Delay.msDelay(50);

				if (IR[0] < 25)
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
	 * Make next move in exploring the maze. Try to move to an unexplored path,
	 * otherwise backtrack to the previous tile with help of the visitStack.
	 * move forward > move right > move left > backtrack
	 * 
	 * @param map
	 */
	public static void makeMoveStep(CustomOccupancyMap map)
	{
		int[][] mazeMap = map.getMazeMap();

		// squares out of the view of the robot on the left, front and right
		int[] left = map.getSquareInDirection(-90);
		int[] front = map.getSquareInDirection(0);
		int[] right = map.getSquareInDirection(90);

		// Determines where to drive
		// Calls movements for movements - front > right > left

		// If this is not a wall
		if (mazeMap[front[0]][front[1]] != -1)
		{
			// Set to following path
			int[] front2 = map.getSquareInDirection(front, 0);
			// If unexplored
			if (mazeMap[front2[0]][front2[1]] == 0)
			{
				// move forwards
				moveCarefully(map, 0);
				return;
			}
		}
		if (mazeMap[right[0]][right[1]] != -1)
		{
			int[] right2 = map.getSquareInDirection(right, 90);
			if (mazeMap[right2[0]][right2[1]] == 0)
			{
				moveCarefully(map, 90);
				return;
			}
		}
		if (mazeMap[left[0]][left[1]] != -1)
		{
			int[] left2 = map.getSquareInDirection(left, -90);
			if (mazeMap[left2[0]][left2[1]] == 0)
			{
				moveCarefully(map, -90);
				return;
			}
		}

		// Otherwise backtrack to the previous square
		boolean backtrack = moveToTileFromStack(map, map.visitStack);
		// Invalid maze
		if (!backtrack)
		{
			LCD.clear();
			LCD.drawString("Backtrack stack empty", 0, 0);
			Coordinator.buttons.waitForAnyPress();
			LCD.clear();
		}
	}

	/**
	 * Moves to the next tile and simultaneously checks for coloured tiles.
	 * Handles all situations of coloured tiles internally
	 * 
	 * @param map
	 * @param direction
	 */
	public static void moveCarefully(CustomOccupancyMap map, int direction)
	{
		//recalibrateOrientation();
		Coordinator.pilot.rotate(direction);
		map.updateRobotOrientation(direction);

		map.visitStack.push(map.getRobotPosition().clone());

		float[] RGB = new float[3];

		// True means it returns right away and allows for measurements while moving
		Coordinator.pilot.travel(Coordinator.DISTANCE, true);

		while (Coordinator.pilot.isMoving())
		{

			Coordinator.ColourSampler.fetchSample(RGB, 0);
			Delay.msDelay(30);

			String detectedColour = determineColour(RGB);

			if (detectedColour == "GREEN")
			{
				double drivenDistance = (double) Coordinator.pilot.getMovement().getDistanceTraveled();
				Coordinator.pilot.stop(); //necessary?
				Delay.msDelay(250);

				map.visitStack.pop();

				// Travel back
				Coordinator.pilot.travel(-drivenDistance);
				int[] front = map.getSquareInDirection(0);
				int[] greenTile = map.getSquareInDirection(front, 0);
				// Set everything surrounding the green tile to walls
				for (int i = 0; i < 360; i += 90)
				{
					int[] tile = map.getSquareInDirection(greenTile, i);
					map.updateMazeMap(tile[0], tile[1], -1);
				}
				map.updateMazeMap(greenTile[0], greenTile[1], -1);

				// Turn back
				Coordinator.pilot.rotate(-direction);
				map.updateRobotOrientation(-direction);
				return;
			}

			if (detectedColour == "RED")
			{
				double drivenDistance = (double) Coordinator.pilot.getMovement().getDistanceTraveled();
				Coordinator.pilot.stop(); //necessary?
				Delay.msDelay(250);
				// Travel to the middle of the tile
				Coordinator.pilot.travel(Coordinator.DISTANCE - drivenDistance);
				map.updateRobotPosition();
				map.setEndTilePosition(map.getRobotPosition().clone());
				int[] robotPosition = map.getRobotPosition().clone();
				map.updateMazeMap(robotPosition[0], robotPosition[1], 1);
				return;
			}
		}
		// If no special colours
		map.updateRobotPosition();
		int[] robotPosition = map.getRobotPosition();
		map.updateMazeMap(robotPosition[0], robotPosition[1], 1);
	}

	/**
	 * Calculates possible shortest paths based on the known and unknown state
	 * of the map until the shortest path is found (explored as path). Then it
	 * travels back to the end of the maze and follows the shortest path back to
	 * the start of the maze.
	 * 
	 * @param map
	 */
	public static void shortestPathBack(CustomOccupancyMap map)
	{
		PathFinder pathFinder = new PathFinder(map.getMazeMap());
		while (true)
		{
			Stack<int[]> pathWithUnknowns = new Stack<>();
			pathWithUnknowns = pathFinder.getPath(map.getEndTilePosition(), new int[] { 1, 1 }, true);
			Stack<int[]> pathWithoutUnknowns = new Stack<>();
			pathWithoutUnknowns = pathFinder.getPath(map.getEndTilePosition(), new int[] { 1, 1 }, false);

			// if there exists a route without unknowns so with only observed paths, that is the shortest possible path
			if (pathWithUnknowns.size() == pathWithoutUnknowns.size())
			{
				Stack<int[]> pathToEndTile = new Stack<>();
				pathToEndTile = pathFinder.getPath(map.getRobotPosition(), map.getEndTilePosition(), false);
				// Go to end of maze
				while (!pathToEndTile.isEmpty())
					Action.moveToTileFromStack(map, pathToEndTile);
				// Go from end back to the start
				while (!pathWithoutUnknowns.isEmpty())
					Action.moveToTileFromStack(map, pathWithoutUnknowns);
				return;
			}

			// explore and stuff
		}
	}

	/**
	 * Moves to the top of the stack
	 * 
	 * @param map
	 * @param stack
	 * @return true if performed as expected, false if stack is empty
	 */
	public static boolean moveToTileFromStack(CustomOccupancyMap map, Stack<int[]> stack)
	{
		if (stack.isEmpty())
			return false;

		int[] backtrackSquare = stack.pop();
		int angle = map.getAngleToSquare(backtrackSquare);
		Coordinator.pilot.rotate(angle);
		map.updateRobotOrientation(angle);
		Coordinator.pilot.travel(Coordinator.DISTANCE);
		map.updateRobotPosition();
		return true;
	}

	/**
	 * NOT functional, detects significant offset of the robot and performs a
	 * appropriate correction turn
	 */
	public static void recalibrateOrientation()
	{
		// Measure current orientation
		float[] Gyro = new float[1];
		Coordinator.GyroSampler.fetchSample(Gyro, 0);
		Delay.msDelay(30);

		int robotOrientation = Coordinator.map.getRobotOrientation();
		int offsetInDegrees = (int) (Math.abs(robotOrientation) - Math.abs(Gyro[0])); // TODO Care, cast float to int

		// If significant offset in degrees
		if (offsetInDegrees > Coordinator.RECALIBRATION_THRESHHOLD && robotOrientation > Gyro[0])
			Coordinator.pilot.rotate(offsetInDegrees);
		if (offsetInDegrees < Coordinator.RECALIBRATION_THRESHHOLD && robotOrientation < Gyro[0])
			Coordinator.pilot.rotate(-1 * offsetInDegrees);
	}

	/**
	 * Returns colour name
	 * 
	 * @param RGB
	 * @return colour name "WHITE", "GREEN" or "RED"
	 */
	public static String determineColour(float[] RGB)
	{
		float average = (RGB[0] + RGB[1] + RGB[2]) / 3.0f;
		if (RGB[0] > 1.3 * average)
			return "RED";
		if (RGB[0] < 0.7 * average && RGB[1] > 1.1 * average && average > 0.2)
			return "GREEN";
		return "WHITE";
	}

}
