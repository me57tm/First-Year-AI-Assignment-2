package mazeSolver;

import lejos.utility.Delay;

/**
 * Mostly uses Coordinator methods to implement all typical actions the robot takes while mapping and solving the maze.
 * @author jakepierrepont
 *
 */
public class Action {
	
	/**
	 * Measuring all 4 fields around the robot if they have not already been measured before. Fetching each one sample.
	 * Consider the following: Increment and decrement for wall/path and if value >2 or <2 stop re-measuring to allow for one more sample taking
	 * before assuming 100% correct measurement
	 */
	public static void lookForWalls(CustomOccupancyMap map) {
		for (int i = -90; i < 180; i += 90) {
			int[] tile = map.getSquare(i);
			if (map.getMazeMap()[tile[0]][tile[1]] == 0) {
				Coordinator.ROTATION_MOTOR.rotateTo(i);
				Coordinator.IRSampler.fetchSample(Coordinator.IR, 0);
				// TODO Delay necessary?
				Delay.msDelay(50);
				
				if (Coordinator.IR[0] < 25)
					map.updateMazeMap(tile[0], tile[1], -1);
				else 
					map.updateMazeMap(tile[0], tile[1], 1);
				//Coordinator.ROTATION_MOTOR.rotateTo(0);
			}
		}
		// Reset by looking forward
		Coordinator.ROTATION_MOTOR.rotateTo(0);
	}
	
	/**
	 * Move from one Path to the next Path. Tries to drive front, then right, then left, then back
	 */
	public static void moveToNextSquare(CustomOccupancyMap map) {
		int[] leftPosition = map.getSquare(-90);
		int[] frontPosition = map.getSquare(0);
		int[] rightPosition = map.getSquare(90);
		
		int[][] mazeMap = map.getMazeMap();
		// TODO Can you give travel a final int?
		if (mazeMap[frontPosition[0]][frontPosition[1]] == 1)
			Coordinator.pilot.travel(Coordinator.DISTANCE);
		else if (mazeMap[rightPosition[0]][rightPosition[1]] == 1) {
			Coordinator.pilot.rotate(-90);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
		}
		else if (mazeMap[leftPosition[0]][leftPosition[1]] == 1) {
			Coordinator.pilot.rotate(90);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
		}
		else
			Coordinator.pilot.travel(Coordinator.DISTANCE * -1);
	}
	
	// Others
}
