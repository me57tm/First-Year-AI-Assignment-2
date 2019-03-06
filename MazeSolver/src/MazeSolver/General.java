package MazeSolver;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.Button;
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

/**
 * Main method to map the maze and manoeuvre through the maze
 * @author jonasschaefer
 *
 */

public class General
{
	// Motors, Sensors and other mandatory variables
	public static EV3 ev3Brick;
	public static Keys buttons;
	
	public static EV3LargeRegulatedMotor LEFT_MOTOR;
	public static EV3LargeRegulatedMotor RIGHT_MOTOR;
	public static EV3MediumRegulatedMotor ROTATION_MOTOR;
	
	public static Wheel wheel1;
	public static Wheel wheel2;
	public static Chassis chassis;
	public static MovePilot pilot;
	
	public static EV3IRSensor IRSensor;
	public static EV3UltrasonicSensor USSensor;
	public static EV3ColorSensor ColourSensor;
	
	
	public static float[] IR;
	public static float[] US;
	public static float[] Colour;
	
	public static SampleProvider IRSampler;
	public static SampleProvider USSampler;
	public static SampleProvider ColourSampler;
	
	
	
	/**
	 *  Orientation the robot is facing in degrees: 
	 *  0 = Forward, 90 = Right, 180 = Backwards, 270 = Left
	 *  
	 *  Will be set in the beginning of the program
	 */
	private static int orientation;
	
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
	
	public static CustomOccupancyMap map;
	
	/**
	 * Main method the robot will execute
	 * @param args 
	 * The default parameter for main()
	 * @throws IOException 
	 * IOException
	 */
	public static void main(String[] args) throws IOException
	{
		setup();
		EV3Server.initializeBluetoothConnection();
		
		map = new CustomOccupancyMap(18,12);
		
		buttons.waitForAnyPress();
		// Robot looks along the width side
		if (buttons.getButtons() == Keys.ID_DOWN)
			orientation = 0;
		// Robot looks along the length side
		if (buttons.getButtons() == Keys.ID_LEFT)
			orientation = 90;
		
		
		
		
		
		
		
		// Last line of code
		EV3Server.closeBluetoothConnection();
	}
	/**
	 * Updates orientation of robot relative to the Maze
	 * @param degrees
	 */
	public void updateOrientation(int degrees) {
		orientation += degrees;
		if (orientation > 270)
			orientation -= 360;
		if (orientation < 0)
			orientation += 360;
	}
	/**
	 * Returns current orientation
	 * @return orientation
	 */
	public int getOrientation() {
		return orientation;
	}
	
	
	/**
	 * Sets values for all motors, sensors, controls and more 
	 */
	public static void setup() {
		ev3Brick = (EV3) BrickFinder.getLocal();
		buttons = ev3Brick.getKeys();
		
		LEFT_MOTOR = new EV3LargeRegulatedMotor(MotorPort.C);
		RIGHT_MOTOR = new EV3LargeRegulatedMotor(MotorPort.A);
		ROTATION_MOTOR = new EV3MediumRegulatedMotor(MotorPort.D);
		
		wheel1 = WheeledChassis.modelWheel(LEFT_MOTOR,5.5).offset(-5.2);
		wheel2 = WheeledChassis.modelWheel(RIGHT_MOTOR,5.5).offset(5.2);
		chassis = new WheeledChassis(new Wheel[] {wheel1,wheel2},WheeledChassis.TYPE_DIFFERENTIAL);
		pilot = new MovePilot(chassis);
		
		IRSensor = new EV3IRSensor(SensorPort.S1);
		USSensor = new EV3UltrasonicSensor(SensorPort.S4);
		ColourSensor = new EV3ColorSensor(SensorPort.S2);
		
		IR = new float[1];
		US = new float[1];
		Colour = new float[3];
		
		IRSampler = IRSensor.getDistanceMode();
		USSampler = USSensor.getDistanceMode();
		ColourSampler = ColourSensor.getRGBMode();
	}
}
