package edu.buaa.park;

public class ParkException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private int _code;
	public int get_code() {
		return _code;
	}
	
	public ParkException(int code, String msg)
	{
		super(msg);
		_code = code;
	}
}
