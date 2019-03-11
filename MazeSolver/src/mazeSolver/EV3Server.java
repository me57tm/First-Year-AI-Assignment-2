package mazeSolver;

import java.io.*;
import java.net.*;

/**
 * Set up EV3 as a server to send data to the PCClient.
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
	 * Port of Server set to first digits of Euler's number.
	 */
	public static final int PORT = 2718;
	
	/**
	 * Socket of the EV3 server.
	 */
	private static ServerSocket server;
	
	/**
	 * Socket of the PC client.
	 */
	private static Socket client;
	
	/**
	 * Send PCClient current state of maze and orientation of the robot for the GUI.
	 * @param map
	 * The current map version.
	 * @param orientation
	 * The orientation the robot faces currently.
	 */
	public static void updateClient() {
		Coordinator.map.getMazeMap();
		//Coordinator.getOrientation();
		
	}
	
	
	
	/**
	 * Initialises Connection with the PCClient.
	 * @throws IOException
	 * Default exception throw.
	 */
	public static void initializeBluetoothConnection() throws IOException {
		server = new ServerSocket(PORT);
		System.out.println("Awaiting client..");
		client = server.accept();
		System.out.println("CONNECTED");
		/*
		OutputStream out = client.getOutputStream();
		DataOutputStream dOut = new DataOutputStream(out);
		dOut.flush();
		*/
	}
	
	/**
	 * Ends connection with the PCClient.
	 * @throws IOException
	 * Default exception throw.
	 */
	public static void closeBluetoothConnection() throws IOException {
		OutputStream out = client.getOutputStream();
		DataOutputStream dOut = new DataOutputStream(out);
		dOut.writeUTF("Closing Server...");
		dOut.flush();
		server.close();
	}
	
	// Send Map + Orientation + Voltage
}