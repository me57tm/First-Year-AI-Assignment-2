package MazeSolver;

import java.io.*;
import java.net.*;
import java.util.Map;

import lejos.hardware.Battery;

/**
 * Set up EV3 as a server to send data to the PCClient
 * @author jonasschaefer
 * 
 * Maximum LEGO EV3: Building Robots with Java Brains
 * ISBN-13: 9780986832291
 * 
 * Variant Press (C) 2014
 * Chapter 14 - Client-Server Robotics
 * Robot: EV3 Brick
 * Platform: LEGO EV3
 * @author Brian Bagnall
 * @version July 20, 2014
 */
public class EV3Server {

	/**
	 * Port of Server set to first digits of Euler's number
	 */
	public static final int PORT = 2718;
	private static ServerSocket server;
	private static Socket client;
	
	/**
	 * Initialises Connection with the PCClient
	 * @throws IOException
	 */
	public static void initializeBluetoothConnection() throws IOException {
		server = new ServerSocket(PORT);
		System.out.println("Awaiting client..");
		client = server.accept();
		System.out.println("CONNECTED");
		OutputStream out = client.getOutputStream();
		DataOutputStream dOut = new DataOutputStream(out);
		dOut.writeUTF("Battery: " + Battery.getVoltage());
		dOut.flush();
	}
	
	public static void closeBluetoothConnection() throws IOException
	{
		OutputStream out = client.getOutputStream();
		DataOutputStream dOut = new DataOutputStream(out);
		dOut.writeUTF("Closing Server...");
		dOut.flush();
		server.close();
	}
	
	// Send Map + Orientation + Voltage
}
