package edu.buaa.park;

import java.util.Iterator;

public interface IParkingStrategy<T extends IParkPlaceCollection> {
	public T selectParkArea(Iterator<T> obj);
}