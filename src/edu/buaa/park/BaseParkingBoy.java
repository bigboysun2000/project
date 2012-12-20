package edu.buaa.park;

import java.util.HashMap;

public abstract class BaseParkingBoy implements IParkingBoy
{
	private int _NO;
	private String _name;
	protected HashMap<Integer,ParkArea> _park_areas = new HashMap<Integer,ParkArea>();
	private int _free_count;
	
	public int getFreeCount()
	{
		if(_free_count >= 0)
			return _free_count;
		
		_free_count = 0;
		for(ParkArea p:_park_areas.values())
			_free_count += p.get_free_count();
		
		return _free_count;
	}
	
	public BaseParkingBoy(int NO, String name) {
		_NO = NO;
		_name = name;
		_free_count = -1;
	}
	
	public int getNO()
	{
		return _NO;
	}
	public String getName()
	{
		return _name;
	}
	
	public void addParkArea(ParkArea area)
	{
		assert(area != null);
		if(!_park_areas.containsKey(area))
		{
			_park_areas.put(area.getNO(), area);
			_free_count = -1;
		}
	}
	
	public void removeParkArea(ParkArea area)
	{
		assert(area != null);
		if(!_park_areas.containsKey(area))
		{
			_park_areas.remove(area.getNO());
			_free_count = -1;
		}
	}
}
