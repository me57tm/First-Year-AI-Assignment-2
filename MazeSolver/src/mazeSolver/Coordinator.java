package mazeSolver;

import java.io.IOException;
import java.util.Arrays;

/**
 * Main class.
 */
public class Coordinator extends Setup
{

	/**
	 * Main method the robot will execute.
	 * 
	 * @param args
	 *            The default parameter for main().
	 * @throws IOException
	 *             IOException.
	 */
	public static void main(String[] args)
		throws IOException
	{
		// Sets up map, sensors, motors, Bluetooth, etc.
		setup();
		Action.scanSurrounding(map);//TODO new
		findEndOfMaze(map);
		
		//Scan once after red square
		Action.scanSurrounding(map);
		
		moveBack(map);

		EV3Server.closeBluetoothConnection();
	}

	/**
	 * Maps the maze until it finds the end of the maze
	 * 
	 * @throws IOException
	 */
	public static void findEndOfMaze(CustomOccupancyMap map)
		throws IOException
	{
		while (map.getEndTilePosition() == null) // TODO untested
		{
			//Action.scanSurrounding(map);
			Action.makeMoveStep(map);
			Action.checkForRed(map);
		}
		
		map.visitStack.removeAllElements();
	}

	public static void moveBack(CustomOccupancyMap map) 
			throws IOException
	{
		while (!Arrays.equals(map.getRobotPosition(),new int[] {1,1})) {
			Action.shortestPathBack(map);
			EV3Server.sendMap();
		}
	}
}
