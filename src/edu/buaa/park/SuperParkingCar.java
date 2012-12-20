package edu.buaa.park;

public class SuperParkingCar extends BaseParkingBoy {
	
	public SuperParkingCar(int NO, String name)
	{
		super(NO,name);
	}

	@Override
	protected ParkArea selectParkArea() {

		ParkArea res = null;
		int max_count = 0;
		
		for(ParkArea tmp:_park_areas.values())
		{
			int tmpcount = tmp.get_free_count();
			if(max_count < tmpcount)
				res = tmp;
		}
		return res;
	}

}
