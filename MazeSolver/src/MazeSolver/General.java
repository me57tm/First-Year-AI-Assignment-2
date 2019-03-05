package MazeSolver;

import java.io.IOException;

import lejos.utility.Delay;
import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Button;
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
	 * The default parameter for main()
	 * @throws IOException 
	 * IOException
	 */
	public static void main(String[] args) throws IOException
	{
		initialize();
		EV3Server.initializeBluetoothConnection();
		CustomOccupancyMap map = new CustomOccupancyMap(12,18);
		map.initializeArrayMap();
		// Last line of code
		EV3Server.closeBluetoothConnection();
	}
	
	public static void initialize() {
		EV3LargeRegulatedMotor LEFT_MOTOR = new EV3LargeRegulatedMotor(MotorPort.C);
		EV3LargeRegulatedMotor RIGHT_MOTOR = new EV3LargeRegulatedMotor(MotorPort.A);
		EV3MediumRegulatedMotor ROTATION_MOTOR = new EV3MediumRegulatedMotor(MotorPort.D);
		EV3 ev3Brick = (EV3) BrickFinder.getLocal();
		Keys buttons = ev3Brick.getKeys();
		EV3IRSensor IRSensor = new EV3IRSensor(SensorPort.S1);
		EV3UltrasonicSensor USSensor = new EV3UltrasonicSensor(SensorPort.S4);
		EV3ColorSensor ColourSensor = new EV3ColorSensor(SensorPort.S2);
		
		Wheel wheel1 = WheeledChassis.modelWheel(LEFT_MOTOR,5.5).offset(-5.2);
		Wheel wheel2 = WheeledChassis.modelWheel(RIGHT_MOTOR,5.5).offset(5.2);
		Chassis chassis = new WheeledChassis(new Wheel[] {wheel1,wheel2},WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot pilot = new MovePilot(chassis);
		
		float[] IR = new float[1];
		float[] US = new float[1];
		float[] Colour = new float[3];
		SampleProvider IRSampler = IRSensor.getDistanceMode();
		SampleProvider USSampler = USSensor.getDistanceMode();
		SampleProvider ColourSampler = ColourSensor.getRGBMode();
	}
}
