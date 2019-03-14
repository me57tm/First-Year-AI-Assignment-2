package mazeSolver;

import lejos.utility.Delay;

/**
 * Mostly uses Coordinator methods to implement all typical actions the robot
 * takes while mapping and solving the maze.
 * 
 * @author jakepierrepont
 *
 */
public class Action
{

	/**
	 * Measuring fields around the robot if they have not already been measured before. 
	 * Fetching each one sample. Avoids re-measuring already measured squares as measurements are reliable
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
	 * @param map
	 */
	public static void moveToNextSquareDumb(CustomOccupancyMap map)
	{
		int[] leftPosition = map.getSquareInDirection(-90);
		int[] frontPosition = map.getSquareInDirection(0);
		int[] rightPosition = map.getSquareInDirection(90);

		int[][] mazeMap = map.getMazeMap(); 
		
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
	 * Make next move in exploring the maze
	 * @param map
	 */
	public static void makeNextMove(CustomOccupancyMap map)
	{
		
	}

	// Others
}
