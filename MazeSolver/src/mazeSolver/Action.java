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
		for (int i = 0; i < 360; i += 90) {
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
	public static void moveToNextSquare(int robotOrientation) {
		int[] robotPosition = Coordinator.map.getRobotPosition();
		// TODO Don't you point the head of the array to robotPosition with all 3 arrays instead of copying values? Alternative below
		int[] leftPosition = robotPosition;
		int[] frontPosition = robotPosition;
		int[] rightPosition = robotPosition;
		
		if (robotOrientation == 0) {
			leftPosition[0]--;
			frontPosition[1]++;
			rightPosition[0]++;
		}
		if (robotOrientation == 90) {
			leftPosition[1]++;
			frontPosition[0]++;
			rightPosition[1]--;
		}
		if (robotOrientation == 180) {
			leftPosition[0]++;
			frontPosition[1]--;
			rightPosition[0]--;
		}
		if (robotOrientation == 270) {
			leftPosition[1]--;
			frontPosition[0]--;
			rightPosition[1]++;
		}
		int[][] mazeMap = Coordinator.map.getMazeMap();
		// TODO Can you give travel a final int?
		if (mazeMap[frontPosition[0]][frontPosition[1]] == 0)
			Coordinator.pilot.travel(Coordinator.DISTANCE);
		else if (mazeMap[rightPosition[0]][rightPosition[1]] == 0) {
			Coordinator.pilot.rotate(-90);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
		}
		else if (mazeMap[leftPosition[0]][leftPosition[1]] == 0) {
			Coordinator.pilot.rotate(90);
			Coordinator.pilot.travel(Coordinator.DISTANCE);
		}
		else
			Coordinator.pilot.travel(Coordinator.DISTANCE * -1);
	}
	
	// Others
}
