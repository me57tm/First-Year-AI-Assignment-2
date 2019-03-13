package mazeSolver;

import java.io.*;
import java.net.*;

import lejos.utility.Delay;

/**
 * The Client that receives information from the EV3 Server.
 * 
 * @author jonasschaefer
 * 
 *         Maximum LEGO EV3: Building Robots with Java Brains ISBN-13:
 *         9780986832291 Variant Press (C) 2014 Chapter 14 - Client-Server
 *         Robotics Robot: EV3 Brick Platform: LEGO EV3
 * @author Brian Bagnall
 * @version July 20, 2014
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
	 */
	public static void main(String[] args)
		throws IOException
	{
		// Setup
		setup(args);

		while (true)
		{
			InputStream in = sock.getInputStream();
			ObjectInputStream oIn = new ObjectInputStream(in);
			CustomOccupancyMap map;
			try
			{
				map = (CustomOccupancyMap) oIn.readObject();
				display.update(map);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			oIn.close();
		}
	}

	public static void setup(String[] args)
		throws IOException
	{
		ip = "10.0.1.1"; // BT
		System.out.println("Trying to connect...");
		if (args.length > 0)
			ip = args[0];
		sock = new Socket(ip, EV3Server.PORT);
		System.out.println("CONNECTED");
		InputStream in = sock.getInputStream();
		DataInputStream dIn = new DataInputStream(in);
		float batteryV = dIn.readFloat();
		System.out.println(batteryV);
		sock.close();
		display = new Display();
	}
}
