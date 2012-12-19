package edu.buaa.park;

import java.util.ArrayList;
import java.util.LinkedList;

//停车场类
public class ParkArea {

	private int _max_place;
	private ArrayList<Place> _all_places = new ArrayList<Place>();
	private LinkedList<Place> _free_place = new LinkedList<Place>();
	
	public ParkArea(int max_place)
	{
		if(max_place <= 0)
			throw new ParkException(0,"停车位个数错误");
		
		init(max_place);
	}
	
	private void init(int max_place)
	{
		_max_place = max_place;
		for(int i=0;i<max_place;i++)
		{
			Place p = new Place(i,String.format("一层第%d个车位",i));
			_all_places.add(p);
			_free_place.add(p);
		}
	}
	
	/*
	 * 空闲车位数
	 */
	public int get_free_count()
	{
		return _free_place.size();
	}
	
	/*
	 * 占用车位数
	 */
	public int get_used_count()
	{
		return _max_place - _free_place.size();
	}
	
	/*
	 * 停车拿票
	 */
	public Ticket parkCar(Car car)
	{
		if(_free_place.isEmpty())
			return null;
		
		Place p = _free_place.removeFirst();
		p.parkCar(car);
		
		Ticket res = new Ticket(p.get_pos());
		return res;
	}
	
	/*
	 * 根据票据取车
	 */
	public Car removeCar(Ticket ticket)
	{
		int pos = ticket.get_position();
		Place p = _all_places.get(pos);
		
		if(p.is_free())
			return null;
		
		Car c = p.removeCar();
		
		_free_place.addFirst(p);
		return c;
	}
	
}
