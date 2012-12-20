package edu.buaa.park.test;
import edu.buaa.park.*;

import junit.framework.Assert;
import org.junit.Test;

public class ParkBoyTest {

	private IParkingBoy init()
	{
		IParkingBoy boy = new ParkingBoy(1,"sun");
		
		ParkArea area1 = new ParkArea(1,10);
		ParkArea area2 = new ParkArea(2,10);
		
		boy.addParkArea(area1);
		boy.addParkArea(area2);
		
		return boy;
	}
	
	@Test
	public void test_park_and_take()
	{
		IParkingBoy boy = init();
		
		int count = boy.getFreeCount();
		Car c = new Car();
		Ticket ticket = boy.parkCar(c);
		Assert.assertNotNull(ticket);
		Assert.assertEquals(count-1, boy.getFreeCount());
		
		Car c1 = boy.removeCar(ticket);
		Assert.assertNotNull(c1);
		Assert.assertEquals(c1, c);
		
	}
}
