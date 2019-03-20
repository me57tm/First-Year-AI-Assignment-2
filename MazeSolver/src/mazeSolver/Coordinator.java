package mazeSolver;

import java.io.IOException;

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

		findEndOfMaze(map);
		
		Action.shortestPathBack(map);

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
			Action.scanSurrounding(map);
			Action.makeMoveStep(map);
			EV3Server.sendMap();
		}
		
		map.visitStack.removeAllElements();
	}
}
