package edu.buaa.park;

import java.util.Iterator;

public class ParkingBoy extends BaseParkingBoy{

	IParkingStrategy<ParkArea> _parking_strategy;
	
	public ParkingBoy(int NO, String name, IParkingStrategy<ParkArea> park_strategy) {
		super(NO, name);
		_parking_strategy = park_strategy;
	}

	@Override
	protected ParkArea selectParkArea() {
		return _parking_strategy.selectParkArea(_park_areas.values().iterator());
	}
}
