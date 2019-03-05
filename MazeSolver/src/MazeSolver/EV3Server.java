package MazeSolver;

import java.io.*;
import java.net.*;
import lejos.hardware.Battery;

/**
 * Set up EV3 as a server to send data to the PCClient
 * @author jonasschaefer
 * 
 * Maximum LEGO EV3: Building Robots with Java Brains
 * ISBN-13: 9780986832291
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
	
	/**
	 * Initialises Connection with the PCClient
	 * @throws IOException
	 */
	public static void InitializeBluetoothConnection() throws IOException {
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("Awaiting client..");
		Socket client = server.accept();
		System.out.println("CONNECTED");
		OutputStream out = client.getOutputStream();
		DataOutputStream dOut = new DataOutputStream(out);
		dOut.writeUTF("Battery: " + Battery.getVoltage());
		dOut.flush();
		// Don't close for initialisation
		server.close();
	}
	
	// Send Map + Orientation + Voltage
}
