package mazeSolver;

/**
 * Mostly uses Coordinator methods to implement all typical actions the robot takes while mapping and solving the maze.
 * @author jonasschafer
 *
 */
public class Action {
	
	/**
	 * Measure Surrounding for walls
	 */
	public static void lookForWalls(int robotOrientation) {
		int[] robotPosition = Coordinator.map.getRobotPosition();
		Boolean left = true;
		Boolean front = true;
		Boolean right = true;
		if (robotPosition[0] == 0) {
			up = -robotOrientation;
			if (robotOrientation == 0) {
				left = false;
			}
		}
		if (robotPosition[0] == 0) {
			
		}
		if (robotPosition[0] == 0) {
			
		}
		if (robotPosition[0] == 0) {
			
		}
	}
	
	/**
	 * Move from one Path to the next Path
	 */
	public static void moveToNextSquare() {
		
	}
	
	// Others
}
