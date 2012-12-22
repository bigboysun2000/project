package edu.buaa.park;

import java.util.ArrayList;

import edu.buaa.park.parkstrategy.MaxParkStrategy;
import edu.buaa.park.parkstrategy.MaxRateParkStrategy;
import edu.buaa.park.parkstrategy.SimpleParkStrategy;

public class ParkingService {
	
	private ArrayList<IParkingBoy> _parking_boys = new ArrayList<IParkingBoy>();
	private ArrayList<ParkArea> _parking_areas = new ArrayList<ParkArea>();
	
	public ParkingService(int ParkingBoyCount, int ParkAreaCount)
	{
		init(ParkingBoyCount,ParkAreaCount);
	}
	
	private void init(int ParkingBoyCount, int ParkAreaCount)
	{
		int count =  ParkAreaCount /ParkingBoyCount;
		
		for(int i=0;i<ParkingBoyCount;i++)
		{
			if((i+1) % 3 == 0)
				_parking_boys.add(new ParkingBoy(i,String.format("停车崽%d",i),new SimpleParkStrategy<ParkArea>()));
			else if((i+1) % 3 == 1)
				_parking_boys.add(new ParkingBoy(i,String.format("聪明的停车崽%d",i),new MaxParkStrategy<ParkArea>()));
			else if((i+1) % 3 == 2)
				_parking_boys.add(new ParkingBoy(i,String.format("超级停车崽%d",i),new MaxRateParkStrategy<ParkArea>()));
		}
		
		for(int i=0;i<ParkAreaCount;i++)
			_parking_areas.add(new ParkArea(i,10));
		
		int c = 0;
		for(IParkingBoy p:_parking_boys)
		{
			for(int i=0;i<count;i++)
				p.addParkArea(_parking_areas.get(c++));
		}
		
		for(int i=c;i<ParkAreaCount;i++)
		{
			_parking_boys.get(ParkingBoyCount-1).addParkArea(_parking_areas.get(i));
		}
		
		ParkingManager man = new ParkingManager(ParkingBoyCount + 1,"停车场经理",new MaxParkStrategy<ParkArea>());
		
		for(IParkingBoy p:_parking_boys)
			man.addParkingBoy(p);
		
		_parking_boys.add(man);
	}
	
	public Ticket parkCar(int i, Car car)
	{
		if(i<0 || i >= _parking_boys.size())
			return null;
		
		return _parking_boys.get(i).parkCar(car);
	}
	
	public Car removeCar(Ticket ticket)
	{
		if(ticket.getParkingBoyNO() < _parking_boys.size())
			return _parking_boys.get(ticket.getParkingBoyNO()).removeCar(ticket);
		return null;
	}
	
	public void printParkAreaStatus()
	{
		int count =0;
		int free = 0;
		for(ParkArea p:_parking_areas)
		{
			
			p.printStatus();
			System.out.println("");
			
			count += p.getMaxCount();
			free += p.getFreeCount();
		}
		
		System.out.println(String.format("Totle车位数:%d",count));
		System.out.println(String.format("Totle空位数:%d",free));
		
	}
	
	public void printParkingBoyStatus()
	{
		for(IParkingBoy boy:_parking_boys)
		{
			System.out.println("");
			((BaseParkingBoy)boy).printStatus();
		}
	}
	
}
