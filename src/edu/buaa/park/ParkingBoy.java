package edu.buaa.park;

public class ParkingBoy extends BaseParkingBoy{

	public ParkingBoy(int NO, String name) {
		super(NO, name);
	}

	@Override
	public Ticket parkCar(Car car) {
		for(ParkArea p:_park_areas.values())
		{
			Ticket ticket = null;
			if(p.get_free_count() > 0)
				ticket = p.parkCar(car);
			
			if(ticket != null)
				ticket.set_park_boy_NO(getNO());
		}

		return null;
	}

	@Override
	public Car removeCar(Ticket ticket) {
		if(ticket.get_park_boy_NO() != getNO())
			return null;
		
		for(ParkArea p:_park_areas.values())
		{
			if(p.getNO() == ticket.get_park_area_NO())
				return p.removeCar(ticket);
		}
		
		return null;
	}

}
