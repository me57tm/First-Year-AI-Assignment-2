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
import lejos.utility.Delay;

/**
 * Main method to map the maze and manoeuvre through the maze
 * @author jonasschaefer
 *
 */

public class General {
	
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
	private static int robotOrientation;
	
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
	public static void main(String[] args) throws IOException {
		setup();
		

		
		// Last line of code
		EV3Server.closeBluetoothConnection();
	}
	
	/**
	 * One step of mapping the maze
	 */
	public static void mapMazeStep() {
		
	}
	
	/**
	 * Returns current orientation
	 * @return orientation
	 */
	public int getOrientation() {
		return robotOrientation;
	}
	
	/**
	 * Updates orientation of robot relative to the Maze by 
	 * @param degrees of turn
	 */
	public void updateOrientation(int degrees) {
		// Check for invalid turns
		if (degrees != 90 && degrees != -90)
			
		robotOrientation += degrees;
		if (robotOrientation > 270)
			robotOrientation -= 360;
		if (robotOrientation < 0)
			robotOrientation += 360;
	}
	
	
	/**
	 * Sets values for all motors, sensors, controls and sets up a Bluetooth connection with the PC
	 * @throws IOException 
	 */
	public static void setup() throws IOException {
		ev3Brick = (EV3) BrickFinder.getLocal();
		buttons = ev3Brick.getKeys();
		
		// Set up direction the robot is facing
		LCD.drawString("Press Button for", 0, 0);
		LCD.drawString("Direction-Setup", 0, 1);
		buttons.waitForAnyPress();
		// Robot looks along the width side
		if (buttons.getButtons() == Keys.ID_DOWN) {
			robotOrientation = 0;
			LCD.clear();
		}
		// Robot looks along the length side
		if (buttons.getButtons() == Keys.ID_LEFT) {
			robotOrientation = 90;
			LCD.clear();
		}
		
		// Set up motors
		LCD.drawString("Setting up motors...", 0, 0);
		LEFT_MOTOR = new EV3LargeRegulatedMotor(MotorPort.C);
		RIGHT_MOTOR = new EV3LargeRegulatedMotor(MotorPort.A);
		ROTATION_MOTOR = new EV3MediumRegulatedMotor(MotorPort.D);
		
		wheel1 = WheeledChassis.modelWheel(LEFT_MOTOR,5.5).offset(-5.2);
		wheel2 = WheeledChassis.modelWheel(RIGHT_MOTOR,5.5).offset(5.2);
		chassis = new WheeledChassis(new Wheel[] {wheel1,wheel2},WheeledChassis.TYPE_DIFFERENTIAL);
		pilot = new MovePilot(chassis);
		LCD.clear();
		
		// Set up sensors
		LCD.drawString("Setting up sensors...", 0, 0);
		IRSensor = new EV3IRSensor(SensorPort.S1);
		USSensor = new EV3UltrasonicSensor(SensorPort.S4);
		ColourSensor = new EV3ColorSensor(SensorPort.S2);
		
		IR = new float[1];
		US = new float[1];
		Colour = new float[3];
		
		IRSampler = IRSensor.getDistanceMode();
		USSampler = USSensor.getDistanceMode();
		ColourSampler = ColourSensor.getRGBMode();
		
		map = new CustomOccupancyMap(18,12);
		
		// Set up Bluetooth Connection
		EV3Server.initializeBluetoothConnection();
		LCD.clear();
		LCD.drawString("Setup complete!", 0, 0);
		LCD.drawString("Wait 5 seconds", 0, 1);
		Delay.msDelay(5000);
		LCD.clear();
	}
}
