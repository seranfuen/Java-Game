package es.sergioangelverbo.test;

import static org.junit.Assert.*;

import org.junit.Test;

import es.sergioangelverbo.model.base.Acceleration;
import es.sergioangelverbo.model.base.Speed;

public class SpeedTests {

	@Test
	public void Add_ZeroSpeeds_StayZero() {
		Speed speed1 = new Speed(0,0);
		Speed speed2 = new Speed(0,0);
		Speed result = speed1.add(speed2);
		assertEquals(0d, result.getHorizontal(), 0);
	}
	
	@Test
	public void Add_ZeroSpeedToNonZero_StaySame() {
		Speed speed1 = new Speed(0.53d, 1.23d);
		Speed speed2 = new Speed(0,0);
		Speed result = speed1.add(speed2);
		assertEquals(result, speed1);
	}

	@Test
	public void Add_TwoSpeeds_AddedProperly() {
		Speed speed1 = new Speed(1.19993d, 1.322d);
		Speed speed2 = new Speed(0, 0.00033d);
		Speed result = speed1.add(speed2);
		assertEquals(1.19993d, result.getHorizontal(), 0);
		assertEquals(1.322d + 0.00033, result.getVertical(), 0);
	}
	
	@Test
	public void updateSpeedByAcceleration_ZeroAcceleration_Unchanged() {
		Speed speed = new Speed(25d, 10.12d);
		Acceleration acc = new Acceleration(0,0);
		Speed result = speed.updateSpeedByAcceleration(acc, 500);
		assertEquals(speed, result);
	}
	
	@Test
	public void updateSpeedByAcceleration_NormalAcceleration_UpdatedProperly() {
		Speed speed = new Speed(25d, 10.12d);
		Acceleration acc = new Acceleration(2.5d ,1.2d);
		Speed result = speed.updateSpeedByAcceleration(acc, 500);
		double expectedXSpeed = speed.getHorizontal() + acc.getHorizontal() * (500d / 1000d);
		double expectedYSpeed = speed.getVertical() + acc.getVertical() * (500d / 1000d);
		
		assertEquals(expectedXSpeed, result.getHorizontal(), 0);
		assertEquals(expectedYSpeed, result.getVertical(), 0);
	}
}
