package edu.buaa.park.test;
import edu.buaa.park.*;

import junit.framework.Assert;

import org.junit.Test;

public class ParkAreaTest {
	
	/*
	 * 停一辆车
	 */
	@Test
	public void park_a_car_should_be_success()
	{
		ParkArea park_area = new ParkArea(1,10);
		
		int pre_count = park_area.getFreeCount();
		
		Car c = new Car();
		Ticket ticket = park_area.parkCar(c, null);
		
		Assert.assertEquals(pre_count - 1,park_area.getFreeCount());
		Assert.assertNotNull(ticket);
	}
	
	/*
	 * 测试将停车场停满
	 */
	@Test
	public void park_full_should_return_null()
	{
		ParkArea park_area = new ParkArea(1,10);
		
		int i =0;
		for(;i<100;i++)
		{
			Car c = new Car();
			Ticket t = park_area.parkCar(c,null);
			if(t == null)
				break;
		}
		
		Assert.assertEquals(i,10);
	}
	
	/*
	 * 停车测试
	 * 票据测试
	 * 取车测试
	 */
	@Test 
	public void park_and_remove_should_work()
	{
		ParkArea park_area = new ParkArea(2,10);
		
		int pre_free_count = park_area.getFreeCount();
		Car c = new Car();
		Ticket ticket = park_area.parkCar(c,null);
		//存入应该返回成功，ticket不能为空
		Assert.assertNotNull(ticket);
		//停入一辆车后，空车位减1
		Assert.assertEquals(pre_free_count - 1, park_area.getFreeCount());
		//停入一辆车后，已经使用的车位加1
		Assert.assertEquals(10 - pre_free_count + 1, park_area.getMaxCount() - park_area.getFreeCount());
		
		
		Car c1 = new Car();
		park_area.parkCar(c1,null);
		
		
		Car r = park_area.removeCar(ticket);
		//取出的车应该是放入的车
		Assert.assertTrue(c == r);
		
		Car r1 = park_area.removeCar(ticket);
		//多次使用一个票据取车，应该返回空
		Assert.assertTrue(r1 == null);
	}
	
	
}
