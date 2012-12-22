package edu.buaa.park;

import java.util.Random;

public class Place {
	private int _pos;			//����
	private String _pos_desc;	//λ������
	private Car _car;			//��ͣ����
	private Object _context;	//�ϲ��õ�
	private int _cookie;		//д��ȡ��Ʊ���ϣ�ȡ��ʱ�˶���Ч��
	
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
			throw new ParkException(0, "��λ����");
		
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
