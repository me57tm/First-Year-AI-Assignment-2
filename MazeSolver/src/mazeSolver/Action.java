package mazeSolver;

import java.util.*;

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
		Boolean left = false;
		int[] leftPosition = new int[2];
		Boolean front = false;
		int[] frontPosition = new int[2];
		Boolean right = false;
		int[] rightPosition = new int[2];
		if (robotOrientation == 0) {
			if (Coordinator.map.getMazeMap()[robotPosition[0]-1][robotPosition[1]] == 0) {
				left = true;
				leftPosition[0] = robotPosition[0]-1;
				leftPosition[1] = robotPosition[1];
			}
			if (Coordinator.map.getMazeMap()[robotPosition[0]][robotPosition[1]+1] == 0) {
				front = true;
				frontPosition[0] = robotPosition[0];
				frontPosition[1] = robotPosition[1]+1;
			}
			if (Coordinator.map.getMazeMap()[robotPosition[0]+1][robotPosition[1]] == 0) {
				right = true;
				rightPosition[0] = robotPosition[0]+1;
				rightPosition[1] = robotPosition[1];
			}
		}
		if (robotOrientation == 90) {
			if (Coordinator.map.getMazeMap()[robotPosition[0]][robotPosition[1]+1] == 0) {
				left = true;
				leftPosition[0] = robotPosition[0];
				leftPosition[1] = robotPosition[1]+1;
			}
			if (Coordinator.map.getMazeMap()[robotPosition[0]+1][robotPosition[1]] == 0) {
				front = true;
				frontPosition[0] = robotPosition[0]+1;
				frontPosition[1] = robotPosition[1];
			}
			if (Coordinator.map.getMazeMap()[robotPosition[0]][robotPosition[1]-1] == 0) {
				right = true;
				rightPosition[0] = robotPosition[0];
				rightPosition[1] = robotPosition[1]-1;
			}
		}
		if (robotOrientation == 180) {
			if (Coordinator.map.getMazeMap()[robotPosition[0]+1][robotPosition[1]] == 0) {
				left = true;
				leftPosition[0] = robotPosition[0]+1;
				leftPosition[1] = robotPosition[1];
			}
			if (Coordinator.map.getMazeMap()[robotPosition[0]][robotPosition[1]-1] == 0) {
				front = true;
				frontPosition[0] = robotPosition[0];
				frontPosition[1] = robotPosition[1]-1;
			}
			if (Coordinator.map.getMazeMap()[robotPosition[0]-1][robotPosition[1]] == 0) {
				right = true;
				rightPosition[0] = robotPosition[0]-1;
				rightPosition[1] = robotPosition[1];
			}
		}
		if (robotOrientation == 270) {
			if (Coordinator.map.getMazeMap()[robotPosition[0]][robotPosition[1]+1] == 0) {
				left = true;
				leftPosition[0] = robotPosition[0];
				leftPosition[1] = robotPosition[1]+1;
			}
			if (Coordinator.map.getMazeMap()[robotPosition[0]-1][robotPosition[1]] == 0) {
				front = true;
				frontPosition[0] = robotPosition[0]-1;
				frontPosition[1] = robotPosition[1];
			}
			if (Coordinator.map.getMazeMap()[robotPosition[0]][robotPosition[1]-1] == 0) {
				right = true;
				rightPosition[0] = robotPosition[0];
				rightPosition[1] = robotPosition[1]-1;
			}
		}
		if (left == true) {
			
		}
		if (front == true) {
			
		}
		if (right == true) {
			
		}
	}
	
	/**
	 * Move from one Path to the next Path
	 */
	public static void moveToNextSquare() {
		
	}
	
	// Others
}
