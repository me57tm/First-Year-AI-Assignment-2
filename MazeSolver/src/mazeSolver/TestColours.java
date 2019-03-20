package mazeSolver;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class TestColours
{

	public static void main(String[] args) throws IOException
	{
		EV3 ev3Brick = (EV3) BrickFinder.getLocal();;
		Keys buttons = ev3Brick.getKeys();
		EV3ColorSensor ColourSensor = new EV3ColorSensor(SensorPort.S2);
		SampleProvider ColourSampler = ColourSensor.getRGBMode();
		
		while (true)
		{
			float[] colour = new float[3];
			ColourSampler.fetchSample(colour, 0);
			Delay.msDelay(50);
			LCD.clear();
			LCD.drawString("R: " + String.valueOf(colour[0]), 0, 0);
			LCD.drawString("G: " + String.valueOf(colour[1]), 0, 1);
			LCD.drawString("B: " + String.valueOf(colour[1]), 0, 2);
			LCD.drawString("AVG: " + String.valueOf(average(colour)), 0, 4);
			LCD.drawString("R in %AVG: " + String.valueOf(percentageOf(colour[0], colour)), 0, 5);
			LCD.drawString("G in %AVG: " + String.valueOf(percentageOf(colour[1], colour)), 0, 6);
			LCD.drawString("B in %AVG: " + String.valueOf(percentageOf(colour[2], colour)), 0, 7);
			Delay.msDelay(100);
		}
	}
	public static float average(float[] rgb)
	{
		return ((rgb[0] + rgb[1] + rgb[2])/3);
	}
	
	public static float percentageOf(float specRGB, float[] rgb)
	{
		return 100*(specRGB / average(rgb));
	}
}
