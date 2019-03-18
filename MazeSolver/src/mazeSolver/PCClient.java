package mazeSolver;

import java.io.*;
import java.net.*;

import lejos.utility.Delay;

/**
 * The Client that receives information from the EV3 Server.
 */
public class PCClient
{
	public static String  ip;

	public static Socket  sock;

	public static Display display;

	/**
	 * Connects from the client side.
	 * 
	 * @param args
	 *            Default arguments.
	 * @throws IOException
	 *             Default exception.
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args)
		throws IOException,
			ClassNotFoundException
	{
		// Setup
		setup(args);
		
		while (true)
		{
			try
			{
				System.out.println("Sending update...");
				CustomOccupancyMap map;
				InputStream in = sock.getInputStream();
				ObjectInputStream oIn = new ObjectInputStream(in);
				map = (CustomOccupancyMap) oIn.readObject();
				Delay.msDelay(100);
				display.update(map);
				for (int i = 0; !map.visitStack.isEmpty(); i++)
				{
					System.out.println("Stack" + i + "=" + map.visitStack.peek()[0]);
					System.out.println("Stack " + i + "=" + map.visitStack.pop()[1]);
				}
				System.out.println("RobotPosition = " + String.valueOf(map.getRobotPosition()[0]) + String.valueOf(map.getRobotPosition()[1]));
				System.out.println("Robot Orientation = " + map.getRobotOrientation());
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		//sock.close();
		//break;
	}

	public static void setup(String[] args)
		throws IOException,
			ClassNotFoundException
	{
		display = new Display();
		ip = "10.0.1.1"; // BT
		System.out.println("Trying to connect...");
		if (args.length > 0)
			ip = args[0];
		sock = new Socket(ip, EV3Server.PORT);
		System.out.println("CONNECTED");
		InputStream in = sock.getInputStream();
		ObjectInputStream oIn = new ObjectInputStream(in);
		CustomOccupancyMap map;
		map = (CustomOccupancyMap) oIn.readObject();
		display.update(map);
	}
}
