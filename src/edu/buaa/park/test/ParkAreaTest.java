package edu.buaa.park.test;
import edu.buaa.park.*;

import junit.framework.Assert;

import org.junit.Test;

public class ParkAreaTest {
	
	/*
	 * ͣһ����
	 */
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
	
	/*
	 * ���Խ�ͣ����ͣ��
	 */
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
	
	/*
	 * ͣ������
	 * Ʊ�ݲ���
	 * ȡ������
	 */
	@Test 
	public void park_and_remove_should_work()
	{
		ParkArea park_area = new ParkArea(10);
		
		int pre_free_count = park_area.get_free_count();
		Car c = new Car();
		Ticket ticket = park_area.parkCar(c);
		//����Ӧ�÷��سɹ���ticket����Ϊ��
		Assert.assertNotNull(ticket);
		//ͣ��һ�����󣬿ճ�λ��1
		Assert.assertEquals(pre_free_count - 1, park_area.get_free_count());
		//ͣ��һ�������Ѿ�ʹ�õĳ�λ��1
		Assert.assertEquals(10 - pre_free_count + 1, park_area.get_used_count());
		
		
		Car c1 = new Car();
		park_area.parkCar(c1);
		
		
		Car r = park_area.removeCar(ticket);
		//ȡ���ĳ�Ӧ���Ƿ���ĳ�
		Assert.assertTrue(c == r);
		
		Car r1 = park_area.removeCar(ticket);
		//���ʹ��һ��Ʊ��ȡ����Ӧ�÷��ؿ�
		Assert.assertTrue(r1 == null);
	}
	
	
}
