package edu.buaa.park.test;
import edu.buaa.park.*;

import junit.framework.Assert;
import org.junit.Test;

public class ParkBoyTest {

	private IParkingBoy init(IParkingBoy boy)
	{		
		boy.removeAllParkArea();
		ParkArea area1 = new ParkArea(1,10);
		ParkArea area2 = new ParkArea(2,10);
		
		boy.addParkArea(area1);
		boy.addParkArea(area2);
		
		return boy;
	}
	
	/*
	 * 测试停入一辆车
	 * 测试取出一辆车
	 * 测试用一张票据重复取出车
	 */
	public void test_park_and_take(IParkingBoy boy)
	{
		int count = boy.getFreeCount();
		Car c = new Car();
		Ticket ticket = boy.parkCar(c);
		Assert.assertNotNull(ticket);
		Assert.assertEquals(count-1, boy.getFreeCount());
		
		Car c0 = new Car();
		boy.parkCar(c0);
		
		Car c1 = boy.removeCar(ticket);
		Assert.assertNotNull(c1);
		Assert.assertEquals(c1, c);
		
		Car c2 = boy.removeCar(ticket);
		Assert.assertNull(c2);
		
	}
	
	/*
	 * 极限测试
	 * 将车位停满然后取出
	 */
	public void test_park_car_take_all_place(IParkingBoy boy)
	{
		int i = 0;
		
		Ticket t[] = new Ticket[20];
		Car c[] = new Car[20];
		
		for(;i<100;i++)
		{
			Car tmp = new Car();
			Ticket ticket = boy.parkCar(tmp);
			if(ticket == null)
				break;
			
			t[i] = ticket;
			c[i] = tmp;
		}
		
		Assert.assertEquals(20, i);
		Assert.assertEquals(0, boy.getFreeCount());
		
		for(i=0;i<20;i++)
		{
			Car cc = boy.removeCar(t[i]);
			Assert.assertEquals(cc, c[i]);
		}
		
		Assert.assertEquals(20, boy.getFreeCount());
	}
	
	@Test
	public void main_test()
	{
		//对停车崽进行测试
		IParkingBoy boy = new ParkingBoy(1, "sun");
		init(boy);
		test_park_and_take(boy);
		
		init(boy);
		test_park_car_take_all_place(boy);
		
		boy = null;
		//--对停车崽进行测试
		
		//对超级停车崽进行测试
		boy = new SuperParkingCar(2, "super_sun");
		init(boy);
		test_park_and_take(boy);
		
		init(boy);
		test_park_car_take_all_place(boy);
		//--对超级停车崽进行测试
	}
}
