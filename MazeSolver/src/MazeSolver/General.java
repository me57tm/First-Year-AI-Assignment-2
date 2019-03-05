package MazeSolver;

import java.io.IOException;
import lejos.hardware.motor.*;
import lejos.robotics.chassis.*;

/**
 * Main method to map the maze and manoeuvre through the maze
 * @author jonasschaefer
 *
 */

public class General
{
	/**
	 *  Orientation the robot is facing in degrees
	 */
	public static int orientation = 0;
	
	/**
	 * Width of one Path square
	 */
	public static final int PATH_WIDTH = 30;
	
	/**
	 * Length of one Path square
	 */
	public static final int PATH_LENGTH = 30;
	
	/* 
	 *   30(W)
	 *   _____
	 *  |     |
	 *  |     |  30(L)
	 *  |_____|
	 *    
	 */ 

	/**
	 * Width of a Wall element
	 */
	public static final int WALL_WIDTH = 10;
	
	/**
	 * Length of a Wall element
	 */
	public static final int WALL_LENGTH = 30;
	
	/*
	 *   10(W)
	 *    __
	 *   |  |
	 *   |  |  30(L)
	 *   |__|
	 *   
	 */
	
	/**
	 * Distance to travel from one centre of the square to the next one
	 */
	public static final int DISTANCE_SQUARETOSQUARE = PATH_WIDTH + WALL_WIDTH;
	
	/**
	 * Main method to execute by the robot
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		EV3Server.InitializeBluetoothConnection();
		CustomOccupancyMap map = new CustomOccupancyMap(12,18);
		map.initializeArrayMap();
		
		
		
	}
}
