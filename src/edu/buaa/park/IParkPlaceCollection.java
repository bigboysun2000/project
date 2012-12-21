package edu.buaa.park;

import java.util.Iterator;

public interface IParkPlaceCollection {
	int getFreeCount();
	int getMaxCount();
	public Iterator<Place> enumPlaceUsed();
}
