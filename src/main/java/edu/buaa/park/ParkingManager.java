package edu.buaa.park;

import java.util.HashMap;
import java.util.Iterator;

public class ParkingManager extends ParkingBoy implements IParkingManagerBoy {

	HashMap<Integer,IParkingBoy> _parkingboys = new HashMap<Integer,IParkingBoy>();
	public ParkingManager(int NO, String name, IParkingStrategy<ParkArea> parkstrategy) {
		super(NO, name,parkstrategy);
	}
	
	public void addParkingBoy(IParkingBoy boy)
	{
		if(!_parkingboys.containsKey(boy.getNO()))
		{
			_parkingboys.put(boy.getNO(), boy);
			
			boy.setManager(this);
			Iterator<ParkArea> it = boy.getParkAreaIterator();
			while(it.hasNext())
			{
				ParkArea p = it.next();
				_park_areas.put(p.getNO(), p);
			}
		}
	}
	
	public void removeAllParkingBoy()
	{
		for(IParkingBoy p:_parkingboys.values())
			p.setManager(null);
		
		_parkingboys.clear();
		_park_areas.clear();
	}

	@Override
	public Ticket parkCarBySubordinate(Car car) {
		if(car == null)
			return null;
		
		for(IParkingBoy p:_parkingboys.values())
		{
			if(p.getFreeCount() > 0)
			{
				Ticket t = p.parkCar(car);
				if(t != null)
					return t;
			}
		}
		
		return null;
	}
}
