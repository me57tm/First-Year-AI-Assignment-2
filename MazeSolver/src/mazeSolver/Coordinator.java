package mazeSolver;

import java.io.IOException;
import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.*;
import lejos.utility.Delay;

/**
 * Main class to map the maze.
 */
public class Coordinator extends Setup
{

	/*
	 * TODO:
	 * 
	 * - Setup on 90 orientation breaks sending and map updating?????
	 * - DONE Go to unknown as next move if possible (go to unknown) 
	 * - DONE Implement VisitStack
	 * - DONE Comment delay of measuring if possible, UNDO if it doesn't work
	 * 
	 * 
	 */

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
		setup();
		//while (map.getEndTilePosition() == null)
		while (true)
			mapMazeStep();
		
		// Find shortest path back

		// Last line of code
		//EV3Server.closeBluetoothConnection();
	}

	/**
	 * Main method of mapping the maze - performs one step of mapping the maze.
	 * 
	 * @throws IOException
	 */
	public static void mapMazeStep()
		throws IOException
	{
		Action.scanSurrounding(map);
		Action.makeMoveStep(map);
		EV3Server.sendMap();
	}
}
