package edu.buaa.park.test;
import edu.buaa.park.*;

import junit.framework.Assert;

import org.junit.Test;

public class ParkAreaTest {
	
	@Test
	public void park_a_car_should_be_success()
	{
		ParkArea park_area = new ParkArea(10);
		
		int pre_count = park_area.get_free_count();
		
		Car c = new Car();
		Ticket ticket = park_area.parkCar(c);
		
		Assert.assertEquals(pre_count - 1,park_area.get_free_count());
		Assert.assertNotNull(ticket);
	}
	
	@Test
	public void park_full_should_return_null()
	{
		ParkArea park_area = new ParkArea(10);
		
		int i =0;
		for(;i<100;i++)
		{
			Car c = new Car();
			Ticket t = park_area.parkCar(c);
			if(t == null)
				break;
		}
		
		Assert.assertEquals(i,10);
	}
	
	
}
