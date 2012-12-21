package edu.buaa.park;

public class Ticket {

	//停车崽编号
	private int _park_boy_NO;
	//停车场编号
	private int _park_area_NO;
	//停车场位置
	private int _position;
	//停车校验数据
	private int _cookie;
		
	public Ticket(int park_area_NO, int position, int cookie)
	{
		_position = position;
		_park_area_NO = park_area_NO;
		_park_boy_NO = 0;
		_cookie = cookie;
	}
	
	public void setParkingBoyNO(int park_boy_NO)
	{
		_park_boy_NO = park_boy_NO;
	}

	public int getPosition() {
		return _position;
	}
	
	public int getParkingAreaNO() {
		return _park_area_NO;
	}

	public int getParkingBoyNO() {
		return _park_boy_NO;
	}
	
	public int getCookie()
	{
		return _cookie;
	}
}
