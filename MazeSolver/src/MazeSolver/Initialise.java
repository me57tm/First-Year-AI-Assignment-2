package MazeSolver;

import java.util.Random;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
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

public class Initialise
{

	public static void main(String[] args)
	{
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
