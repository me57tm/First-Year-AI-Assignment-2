package mazeSolver;

import java.io.*;
import java.net.*;

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
				display.update(map);
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
