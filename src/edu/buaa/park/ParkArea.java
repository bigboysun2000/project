package edu.buaa.park;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

//ͣ������
public class ParkArea  implements IParkPlaceCollection{

	private int _max_place;
	private int _NO;
	private ArrayList<Place> _all_places = new ArrayList<Place>();
	private LinkedList<Place> _free_place = new LinkedList<Place>();
	private LinkedList<Place> _used_place = new LinkedList<Place>();
	
	public ParkArea(int NO, int max_place)
	{
		if(max_place <= 0)
			throw new ParkException(0,"ͣ��λ��������");
		
		_NO = NO;
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
	public int getFreeCount()
	{
		return _free_place.size();
	}
	
	/*
	 * ռ�ó�λ��
	 */
	public int getMaxCount()
	{
		return _max_place;
	}
	
	/*
	 * ͣ����Ʊ
	 */
	public Ticket parkCar(Car car, Object context)
	{
		if(_free_place.isEmpty())
			return null;
		
		Place p = _free_place.removeFirst();
		int cookie = p.parkCar(car,context);
		_used_place.addLast(p);
		
		Ticket res = new Ticket(_NO,p.get_pos(),cookie);
		return res;
	}
	
	public int getNO() {
		return _NO;
	}

	/*
	 * ����Ʊ��ȡ��
	 */
	public Car removeCar(Ticket ticket)
	{
		int pos = ticket.getPosition();
		Place p = _all_places.get(pos);
		
		if(p.is_free())
			return null;
		
		Car c = p.removeCar(ticket.getCookie());
		if(c != null)
		{
			_used_place.remove(p);		
			_free_place.addFirst(p);
		}
		return c;
	}
	
	public Iterator<Place> enumPlaceUsed()
	{
		return _used_place.iterator();
	}
}
