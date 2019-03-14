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
 * 
 * @author jonasschaefer
 *
 */
public class Coordinator
{

	// Motors, Sensors and other mandatory variables
	public static EV3                     ev3Brick;
	public static Keys                    buttons;

	public static EV3LargeRegulatedMotor  LEFT_MOTOR;
	public static EV3LargeRegulatedMotor  RIGHT_MOTOR;
	public static EV3MediumRegulatedMotor ROTATION_MOTOR;

	public static Wheel                   wheel1;
	public static Wheel                   wheel2;
	public static Chassis                 chassis;
	public static MovePilot               pilot;

	public static EV3IRSensor             IRSensor;
	public static EV3UltrasonicSensor     USSensor;
	public static EV3ColorSensor          ColourSensor;

	public static float[]                 IR;
	public static float[]                 US;
	public static float[]                 Colour;

	public static SampleProvider          IRSampler;
	public static SampleProvider          USSampler;
	public static SampleProvider          ColourSampler;

	/* 
	 * Path-Square
	 * 
	 *   30(W)
	 *   _____
	 *  |     |
	 *  |     |  30(L)
	 *  |_____|
	 *    
	 */

	/*
	 * Wall-Element
	 *   10(W)
	 *    __
	 *   |  |
	 *   |  |  30(L)
	 *   |__|
	 *   
	 */

	/**
	 * Distance to travel from one centre of a square to the next one.
	 */
	public static final int               DISTANCE = 40;

	/**
	 * The maze representation and information.
	 */
	public static CustomOccupancyMap      map;

	/*
	 * TODO:
	 * 
	 * - Setup on 90 orientation breaks sending and map updating? 
	 * - Go to unknown as next move if possible (go to unknown) 
	 * - Implement VisitStack
	 * - Comment delay of measuring if possible
	 * 
	 * 
	 * 
	 * 
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
		while (map.getEndOfMazePosition() == null)
			mapMazeStep();
		// Find shortest path back

		// Last line of code
		EV3Server.closeBluetoothConnection();
	}

	/**
	 * Main method of mapping the maze - performs one step of mapping the maze.
	 * 
	 * @throws IOException
	 */
	public static void mapMazeStep()
		throws IOException
	{
		Action.lookForWalls(map);
		Action.moveToNextSquareDumb(map); //map
		EV3Server.sendMap();
	}
	
	/**
	 * Sets values for all motors, sensors, controls and sets up a Bluetooth connection with the Client.
	 * 
	 * @throws IOException
	 *             Default exception
	 */
	public static void setup()
		throws IOException
	{
		ev3Brick = (EV3) BrickFinder.getLocal();
		buttons = ev3Brick.getKeys();

		// Set up direction the robot is facing
		LCD.drawString("Press Button for", 0, 0);
		LCD.drawString("Direction-Setup", 0, 1);
		LCD.drawString("Hold button", 0, 3);
		LCD.drawString("until new text", 0, 4);
		LCD.drawString("appears...", 0, 5);
		while (true)
		{
			// Robot faces along the shorter side
			if (buttons.getButtons() == Keys.ID_DOWN)
			{
				map = new CustomOccupancyMap(19, 13, 0);
				break;
			}
			// Robot faces along the longer side
			if (buttons.getButtons() == Keys.ID_LEFT)
			{
				map = new CustomOccupancyMap(19, 13, 90);
				break;
			}
		}

		LCD.clear();
		LCD.drawString("Setting up", 0, 0);
		LCD.drawString("Motors", 0, 1);
		LEFT_MOTOR = new EV3LargeRegulatedMotor(MotorPort.C);
		RIGHT_MOTOR = new EV3LargeRegulatedMotor(MotorPort.A);
		ROTATION_MOTOR = new EV3MediumRegulatedMotor(MotorPort.D);

		LCD.clear();
		LCD.drawString("Setting up", 0, 0);
		LCD.drawString("Wheels,Chassis", 0, 1);
		wheel1 = WheeledChassis.modelWheel(LEFT_MOTOR, 5.5).offset(-5.15); //-5.2
		wheel2 = WheeledChassis.modelWheel(RIGHT_MOTOR, 5.5).offset(5.15); //5.2
		chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		pilot = new MovePilot(chassis);
		pilot.setAngularSpeed(50);
		pilot.setLinearSpeed(35);

		LCD.clear();
		LCD.drawString("Setting up sensors...", 0, 0);
		IRSensor = new EV3IRSensor(SensorPort.S1);
		USSensor = new EV3UltrasonicSensor(SensorPort.S4);
		ColourSensor = new EV3ColorSensor(SensorPort.S2);

		IR = new float[3]; // TODO consider multiple measures
		US = new float[1];
		Colour = new float[3];

		IRSampler = IRSensor.getDistanceMode();
		USSampler = USSensor.getDistanceMode();
		ColourSampler = ColourSensor.getRGBMode();

		// Set up Bluetooth Connection
		EV3Server.initializeBluetoothConnection();

		// Mapping start warning
		LCD.clear();
		LCD.drawString("Setup complete!", 0, 0);
		Delay.msDelay(500);
		LCD.drawString("Begin Mapping in", 0, 1);
		Delay.msDelay(1000);
		LCD.drawString("5", 7, 4);
		Delay.msDelay(1000);
		LCD.drawString("4", 7, 4);
		Delay.msDelay(1000);
		LCD.drawString("3", 7, 4);
		Delay.msDelay(1000);
		LCD.drawString("2", 7, 4);
		Delay.msDelay(1000);
		LCD.drawString("1", 7, 4);
		Delay.msDelay(1000);
		LCD.clear();
	}
}
