package edu.buaa.park;

public class Place {
	private int _pos;			//����
	private String _pos_desc;	//λ������
	private Car _car;			//��ͣ����
	
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
	
	public void parkCar(Car car)
	{
		if(_car != null)
			throw new ParkException(0, "��λ����");
		
		_car = car;
	}
	
	public Car removeCar()
	{
		if(_car == null)
			throw new ParkException(0, "�޳���ȡ");
		
		Car tmp = _car;
		_car = null;
		
		return tmp;
	}
}
