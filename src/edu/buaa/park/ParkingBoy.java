package edu.buaa.park;

public class ParkingBoy extends BaseParkingBoy{

	public ParkingBoy(int NO, String name) {
		super(NO, name);
	}

	@Override
	protected ParkArea selectParkArea() {
		for(ParkArea p:_park_areas.values())
		{
			if(p.get_free_count() > 0)
				return p;
		}
		return null;
	}



}
