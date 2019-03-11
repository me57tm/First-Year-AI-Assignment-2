package mazeSolver;

import java.io.*;
import java.net.*;

/**
 * The Client that receives information from the EV3 Server.
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
public class PCClient {

	/**
	 * Connects from the client side.
	 * @param args
	 * Default arguments.
	 * @throws IOException
	 * Default exception.
	 */
	public static void main(String[] args) throws IOException {
		String ip = "10.0.1.1"; // BT
		if(args.length > 0)
			ip = args[0];
		Socket sock = new Socket(ip, EV3Server.PORT);
		System.out.println("Connected");
		InputStream in = sock.getInputStream();
		DataInputStream dIn = new DataInputStream(in);
		String str = dIn.readUTF();
		System.out.println(str);
		sock.close();
		Display display = new Display();
		
	}
}