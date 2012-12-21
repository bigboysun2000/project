package edu.buaa.park;

import java.util.Random;

public class Place {
	private int _pos;			//索引
	private String _pos_desc;	//位置描述
	private Car _car;			//所停车辆
	private Object _context;	//上层用到
	private int _cookie;		//写在取车票据上，取车时核对有效性
	
	public Place(int pos, String desc)
	{
		_pos = pos;
		_pos_desc = desc;
		_car = null;
	}
	
	public Car get_car() {
		return _car;
	}

	public int get_pos() {
		return _pos;
	}

	public boolean is_free()
	{
		return _car == null;
	}
	
	public String get_pos_desc() {
		return _pos_desc;
	}
	
	public int parkCar(Car car, Object context)
	{
		if(_car != null)
			throw new ParkException(0, "车位不空");
		
		_car = car;
		_context = context;
		
		Random rnd = new Random();
		_cookie = rnd.nextInt();
		
		return _cookie;
	}
	
	public Car removeCar(int cookie)
	{
		Car res = null;
		if(_car != null && _cookie == cookie)
		{
			res = _car;
			_car = null;
			_context = null;
		}
		
		return res;
	}
	
	public Object getContext()
	{
		return _context;
	}
}
