package edu.buaa.park;

public class Ticket {

	//停车崽编号
	private int _park_boy_NO;
	//停车场编号
	private int _park_area_NO;
	//停车场位置
	private int _position;
		
	public Ticket(int park_area_NO, int position)
	{
		_position = position;
		_park_area_NO = park_area_NO;
		_park_boy_NO = 0;
		
	}
	
	public void set_park_boy_NO(int park_boy_NO)
	{
		_park_boy_NO = park_boy_NO;
	}

	public int get_position() {
		return _position;
	}
	
	public int get_park_area_NO() {
		return _park_area_NO;
	}

	public int get_park_boy_NO() {
		return _park_boy_NO;
	}
}
