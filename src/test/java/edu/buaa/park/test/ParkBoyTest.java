package edu.buaa.park.test;
import edu.buaa.park.*;
import edu.buaa.park.parkstrategy.MaxParkStrategy;
import edu.buaa.park.parkstrategy.MaxRateParkStrategy;
import edu.buaa.park.parkstrategy.SimpleParkStrategy;

import junit.framework.Assert;
import org.junit.Test;

public class ParkBoyTest {

	private IParkingBoy init(IParkingBoy boy)
	{		
		boy.removeAllParkArea();
		
		ParkArea area1 = new ParkArea(100+boy.getNO(),10);
		ParkArea area2 = new ParkArea(200+boy.getNO(),10);
		
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
		int max = boy.getMaxCount();
		
		Ticket t[] = new Ticket[max];
		Car c[] = new Car[max];
		
		for(;i<10000;i++)
		{
			Car tmp = new Car();
			Ticket ticket = boy.parkCar(tmp);
			if(ticket == null)
				break;
			
			t[i] = ticket;
			c[i] = tmp;
		}
		
		Assert.assertEquals(max, i);
		Assert.assertEquals(0, boy.getFreeCount());
		
		for(i=0;i<max;i++)
		{
			Car cc = boy.removeCar(t[i]);
			Assert.assertEquals(cc, c[i]);
		}
		
		Assert.assertEquals(max, boy.getFreeCount());
	}
	
	@Test
	public void main_test()
	{
		//对停车崽进行测试
		IParkingBoy boy = new ParkingBoy(1, "sun", new SimpleParkStrategy<ParkArea>());
		init(boy);
		test_park_and_take(boy);
		
		init(boy);
		test_park_car_take_all_place(boy);
		
		boy = null;
		//--对停车崽进行测试
		
		//对聪明停车崽进行测试
		boy = new ParkingBoy(2, "smart_sun", new MaxParkStrategy<ParkArea>());
		init(boy);
		test_park_and_take(boy);
		
		init(boy);
		test_park_car_take_all_place(boy);
		//--对聪明的停车崽进行测试
		
		//对超级停车崽进行测试
		boy = new ParkingBoy(3, "super_sun", new MaxRateParkStrategy<ParkArea>());
		init(boy);
		test_park_and_take(boy);
		
		init(boy);
		test_park_car_take_all_place(boy);
		//--对超级停车崽进行测试
		
		//对经理进行测试
		IParkingBoy boy1 = new ParkingBoy(1, "sun", new SimpleParkStrategy<ParkArea>());
		IParkingBoy boy2 = new ParkingBoy(2, "super_sun", new MaxParkStrategy<ParkArea>());
		IParkingBoy boy3 = new ParkingBoy(3, "super_sun", new MaxRateParkStrategy<ParkArea>());
		
		init(boy1);
		init(boy2);
		init(boy3);		
		
		ParkingManager man = new ParkingManager(9, "Manager", new MaxParkStrategy<ParkArea>());
		man.addParkingBoy(boy1);
		man.addParkingBoy(boy2);
		man.addParkingBoy(boy3);
		
		test_park_and_take(man);
		
		init(boy1);
		init(boy2);
		init(boy3);	
		
		man.removeAllParkingBoy();
		man.addParkingBoy(boy3);
		man.addParkingBoy(boy2);
		man.addParkingBoy(boy1);
		
		test_park_car_take_all_place(man);
		//--对经理进行测试
	}
}
