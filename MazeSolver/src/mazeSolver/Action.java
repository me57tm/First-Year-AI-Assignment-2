package mazeSolver;

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
	 * Intelligent version: Make next move in exploring the maze. Try to move to
	 * an unexplored path, otherwise backtrack to the previous grid with help of
	 * the visitStack. move right > move forward > move left > backtrack
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
				/*LCD.clear();
				LCD.drawString("Move forwards", 0, 0);
				Coordinator.buttons.waitForAnyPress();
				LCD.clear();*/
				moveCarefully(map, 0);
				return;
			}
		}
		if (mazeMap[right[0]][right[1]] != -1)
		{
			int[] right2 = map.getSquareInDirection(right, 90);
			
			if (mazeMap[right2[0]][right2[1]] == 0)
			{
				// move right
				/*LCD.clear();
				LCD.drawString("Move right", 0, 0);
				Coordinator.buttons.waitForAnyPress();
				LCD.clear();*/
				moveCarefully(map, 90);
				return;
			}
		}
		if (mazeMap[left[0]][left[1]] != -1)
		{
			int[] left2 = map.getSquareInDirection(left, -90);
			
			if (mazeMap[left2[0]][left2[1]] == 0)
			{
				// Move left
				/*LCD.clear();
				LCD.drawString("Move left", 0, 0);
				Coordinator.buttons.waitForAnyPress();
				LCD.clear();*/
				moveCarefully(map, -90);
				return;
			}
		}
		/*LCD.clear();
		LCD.drawString("Tries to backtrack", 0, 0);
		LCD.drawString(String.valueOf(map.visitStack.size()), 0, 1);
		Coordinator.buttons.waitForAnyPress();
		LCD.clear();*/
		// Otherwise backtrack to the previous square
		boolean backtrack = performBacktrack(map, map.visitStack);
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
	 * Performs a backtrack move using a map and a Stack
	 * 
	 * @param map
	 * @param stack
	 * @return true if performed as expected, false if stack is empty
	 */
	public static boolean performBacktrack(CustomOccupancyMap map, Stack<int[]> stack)
	{
		if (stack.isEmpty())
			return false;

		int[] backtrackSquare = stack.pop();
		int angle = map.turnToSquare(backtrackSquare);
		Coordinator.pilot.rotate(angle);
		map.updateRobotOrientation(angle);
		Coordinator.pilot.travel(Coordinator.DISTANCE);
		map.updateRobotPosition();
		return true;
	}

	/**
	 * Moves and simultaneously checks for coloured tiles. Handles all situations of coloured tiles internally
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
			/*
			Coordinator.ColourSampler.fetchSample(RGB, 0);
			Delay.msDelay(30);

			String detectedColour = determineColour(RGB);
			
			if (detectedColour == "GREEN")
			{
				Coordinator.pilot.stop();
				map.visitStack.pop();
				
				LCD.clear();
				LCD.drawString("Detected green", 0, 0);
				Coordinator.buttons.waitForAnyPress();
				LCD.clear();
				
				// Travel back
				Coordinator.pilot.travel(-1 * (Coordinator.DISTANCE - 18));
				int[] front = map.getSquareInDirection(0);
				int[] greenTile = map.getSquareInDirection(front, 0);
				// Set all surrounding the path to walls
				for (int i = 0; i < 360; i += 90)
				{
					int[] tile = map.getSquareInDirection(greenTile, i);
					map.updateMazeMap(tile[0], tile[1], -1);
				}
				map.updateMazeMap(greenTile[0], greenTile[1], -1);
				
				// Turn back
				Coordinator.pilot.rotate(direction * -1);
				map.updateRobotOrientation(direction * -1);
				return;
			}
			if (detectedColour == "RED")
			{
				Coordinator.pilot.stop();
				LCD.clear();
				LCD.drawString("Detected green", 0, 0);
				Coordinator.buttons.waitForAnyPress();
				LCD.clear();
				// Travel to the middle of the tile
				Coordinator.pilot.travel(18);
				map.updateRobotPosition();
				map.setEndTilePosition(map.getRobotPosition());
				int[] robotPosition = map.getRobotPosition();
				map.updateMazeMap(robotPosition[0], robotPosition[1], 1);
				return;
			}
			*/
		}
		// If no special colours
		map.updateRobotPosition();
		int[] robotPosition = map.getRobotPosition();
		map.updateMazeMap(robotPosition[0], robotPosition[1], 1);
	}
	
	/**
	 * Detects significant offset of the robot and performs a appropiate correction turn
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
	 * @param RGB
	 * @return colour name "WHITE", "GREEN" or "RED"
	 */
	public static String determineColour(float[] RGB)
	{
		float average = (RGB[0] + RGB[1] + RGB[2]) / 3.0f;
		if (RGB[0] > 1.3 * average)
			return "RED";
		if (RGB[0] < 0.7 * average && average > 0.2) // TODO average > 0.2 new
			return "GREEN";
		return "WHITE";
	}

	/**
	 * Old movement, remove if new one is functional. Move from one Path to the
	 * next Path. Tries to drive front, then right, then left, then back
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
			map.updateRobotOrientation(90);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
			map.updateRobotPosition();
	
		}
		else if (mazeMap[leftPosition[0]][leftPosition[1]] == 1)
		{
			Coordinator.pilot.rotate(-90);
			map.updateRobotOrientation(-90);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
			map.updateRobotPosition();
		}
		else
		{
			Coordinator.pilot.rotate(180);
			map.updateRobotOrientation(180);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
			map.updateRobotPosition();
		}
		int[] robotPosition = map.getRobotPosition();
		map.updateMazeMap(robotPosition[0], robotPosition[1], 1);
	}
	
}
