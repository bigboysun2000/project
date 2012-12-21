package edu.buaa.park;

import java.util.Random;

public class ParkSystem {

	public static void main(String[] param)
	{
		int boycount = -1;
		int parkcount = -1;
		Random ran = new Random();
		while(boycount <= 0 || parkcount <= 0 || parkcount < boycount)
		{
			boycount = ran.nextInt(20);
			parkcount = ran.nextInt(20);
		}
		
		ParkingService service = new ParkingService(boycount,parkcount);
		
		Car [] cars = new Car[100];
		Ticket[] tickets = new Ticket[100];
		
		for(int i=0;i<100;i++)
		{
			int x = ran.nextInt(100);
			if(tickets[x] != null)
			{
				Car c = service.removeCar(tickets[x]);
				tickets[x] = null;
			}
			else
			{
				Car c = new Car();
				int b = ran.nextInt(boycount);
				tickets[x] = service.parkCar(b, c);
			}
		}
		
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println(String.format("停车场数:%d,停车崽数:%d",parkcount,boycount ));
		System.out.println("");
		System.out.println("");
		
		service.printParkAreaStatus();
		
		service.printParkingBoyStatus();
	}
}
