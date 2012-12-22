package edu.buaa.park.parkstrategy;
import java.util.Iterator;

import edu.buaa.park.*;
public class MaxRateParkStrategy<T extends IParkPlaceCollection> implements IParkingStrategy<T> {

	@Override
	public T selectParkArea(Iterator<T> objs) {
		T res = null;
		double maxrate = 0;
		
		while(objs.hasNext())
		{
			T obj = objs.next();
			int free_count = obj.getFreeCount();
			double tmprate = (double)free_count / obj.getMaxCount();
			if((maxrate == 0 && free_count > 0) || tmprate > maxrate)
			{
				maxrate = tmprate;
				res = obj;
			}
		}
		
		return res;
	}
}
