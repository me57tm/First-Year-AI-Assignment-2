package mazeSolver;


/**
 * Mostly uses Coordinator methods to implement all typical actions the robot takes while mapping and solving the maze.
 * @author jakepierrepont
 *
 */
public class Action {
	
	/**
	 * Measure Surrounding for walls
	 */
	
	public static void lookForWalls(int robotOrientation) {
		int[] robotPosition = Coordinator.map.getRobotPosition();
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
			rightPosition[1]--1;
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
		if (Coordinator.map.getMazeMap()[leftPosition[0]][leftPosition[1]] == 0) {
			Coordinator.ROTATION_MOTOR.rotateTo(-90);
			Coordinator.IRSampler.fetchSample(Coordinator.IR, 0);
			if (Coordinator.IR[0] < 20) {
				Coordinator.map.updateMazeMap(leftPosition[0], leftPosition[1], -1);
			}else {
				Coordinator.map.updateMazeMap(leftPosition[0], leftPosition[1], 1);
			}
			Coordinator.ROTATION_MOTOR.rotateTo(0);
		}
		if (Coordinator.map.getMazeMap()[frontPosition[0]][frontPosition[1]] == 0) {
			Coordinator.IRSampler.fetchSample(Coordinator.IR, 0);
			if (Coordinator.IR[0] < 20) {
				Coordinator.map.updateMazeMap(frontPosition[0], frontPosition[1], -1);
			}else {
				Coordinator.map.updateMazeMap(frontPosition[0], frontPosition[1], 1);
			}
		}
		if (Coordinator.map.getMazeMap()[rightPosition[0]][rightPosition[1]] == 0) {
			Coordinator.ROTATION_MOTOR.rotateTo(90);
			Coordinator.IRSampler.fetchSample(Coordinator.IR, 0);
			if (Coordinator.IR[0] < 20) {
				Coordinator.map.updateMazeMap(rightPosition[0], rightPosition[1], -1);
			}else {
				Coordinator.map.updateMazeMap(rightPosition[0], rightPosition[1], 1);
			}
			Coordinator.ROTATION_MOTOR.rotateTo(0);
		}
	}
	
	/**
	 * Move from one Path to the next Path
	 */
	public static void moveToNextSquare(int robotOrientation) {
		int[] robotPosition = Coordinator.map.getRobotPosition();
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
		if (Coordinator.map.getMazeMap()[frontPosition[0]][frontPosition[1]] == 0) {
			//move forwards
		} else if(Coordinator.map.getMazeMap()[rightPosition[0]][rightPosition[1]] == 0) {
			
		}
		
	}
	
	// Others
}
