package edu.buaa.park;

import java.util.ArrayList;
import java.util.LinkedList;

//ͣ������
public class ParkArea {

	private int _max_place;
	private ArrayList<Place> _all_places = new ArrayList<Place>();
	private LinkedList<Place> _free_place = new LinkedList<Place>();
	
	public ParkArea(int max_place)
	{
		if(max_place <= 0)
			throw new ParkException(0,"ͣ��λ��������");
		
		init(max_place);
	}
	
	private void init(int max_place)
	{
		_max_place = max_place;
		for(int i=0;i<max_place;i++)
		{
			Place p = new Place(i,String.format("һ���%d����λ",i));
			_all_places.add(p);
			_free_place.add(p);
		}
	}
	
	/*
	 * ���г�λ��
	 */
	public int get_free_count()
	{
		return _free_place.size();
	}
	
	/*
	 * ռ�ó�λ��
	 */
	public int get_used_count()
	{
		return _max_place - _free_place.size();
	}
	
	/*
	 * ͣ����Ʊ
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
	 * ����Ʊ��ȡ��
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
