package edu.buaa.park;

import java.util.HashMap;
import java.util.Iterator;

public abstract class BaseParkingBoy implements IParkingBoy, IParkPlaceCollection
{
	private int _NO;
	private String _name;
	protected HashMap<Integer,ParkArea> _park_areas = new HashMap<Integer,ParkArea>();
	private IParkingBoy _manager = null;
	
	public void setManager(IParkingBoy man)
	{
		_manager = man;
	}
	
	public int getFreeCount()
	{
		int count = 0;
		for(ParkArea p:_park_areas.values())
			count += p.getFreeCount();
		
		return count;
	}
	
	public BaseParkingBoy(int NO, String name) {
		_NO = NO;
		_name = name;
		
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
		}
	}
	
	public void removeAllParkArea()
	{
		_park_areas.clear();
	}

	protected abstract ParkArea selectParkArea();
	
	@Override
	public Ticket parkCar(Car car) {
		if(car == null)
			return null;
		
		Ticket ticket = null;
		
		ParkArea p = selectParkArea();
		if(p != null)
		{
			ticket = p.parkCar(car,this);
			if(ticket != null)
				ticket.setParkingBoyNO(_NO);
		}
		
		if(ticket != null)
			System.out.println(String.format("%s 成功将客户的车停入第%d停车场的第%d个位置",_name,ticket.getParkingAreaNO(),ticket.getPosition()));
		else
			System.out.println(String.format("由于%s管理的停车场满，所以，没有将客户的车成功停放!",_name));
		
		return ticket;
	}

	@Override
	public Car removeCar(Ticket ticket) {
		if(ticket == null)
			return null;
		
		Car res = null;
		if(ticket.getParkingBoyNO() != getNO() || !_park_areas.containsKey(ticket.getParkingAreaNO()))
		{
			//我搞不定了，给经理处理
			if(_manager != null)
				res = _manager.removeCar(ticket);
		}
		else
		{
			
			ParkArea p = _park_areas.get(ticket.getParkingAreaNO());
			if(p != null)
				res = p.removeCar(ticket);
		}
		
		if(res != null)
			System.out.println(String.format("%s 在第%d停车场的第%d个位置成功取出客户的车!",_name,ticket.getParkingAreaNO(),ticket.getPosition()));
		else
			System.out.println(String.format("%s 在第%d停车场的第%d个位置取车失败!",_name,ticket.getParkingAreaNO(),ticket.getPosition()));
		return res;
	}
	
	@Override
	public int getMaxCount() {
		int count = 0;
		for(ParkArea p:_park_areas.values())
			count += p.getMaxCount();
		
		return count;
	}
	
	public Iterator<ParkArea> getParkAreaIterator()
	{
		return _park_areas.values().iterator();
	}
	
	public void printStatus()
	{
		System.out.println(String.format("停车崽名:%s",_name));
		System.out.println("");
		for(ParkArea p:_park_areas.values())
		{
			System.out.println(String.format("停车场编号:%d", p.getNO()));
			System.out.println(String.format("    车位数:%d", p.getMaxCount()));
			System.out.println(String.format("    空位数:%d",p.getFreeCount()));
		}
		System.out.println("");
		System.out.println(String.format("Total车位数:%d",getMaxCount()));
		System.out.println(String.format("Total空位数:%d", getFreeCount()));
	}
}
