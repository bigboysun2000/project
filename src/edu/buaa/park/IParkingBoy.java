package edu.buaa.park;

/*
 * 停车仔接口
 */
public interface IParkingBoy {
	
	/*
	 * 取得小盆友的编号
	 */
	public int getNO();
	
	/*
	 * 给这个小盆友加个停车场
	 */
	public void addParkArea(ParkArea area);
	
	/*
	 * 收回小盆友管理的所有停车场
	 */
	public void removeAllParkArea();
	
	/*
	 * 小朋友的名字
	 */
	public String getName();
	
	/*
	 * 停车
	 */
	public Ticket parkCar(Car car);
	
	/*
	 * 取车
	 */
	public Car removeCar(Ticket ticket);
	
	/*
	 * 获取空车位个数
	 */
	public int getFreeCount();
}