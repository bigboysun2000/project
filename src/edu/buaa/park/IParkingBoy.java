package edu.buaa.park;

/*
 * ͣ���нӿ�
 */
public interface IParkingBoy {
	
	/*
	 * ȡ��С���ѵı��
	 */
	public int getNO();
	
	/*
	 * �����С���ѼӸ�ͣ����
	 */
	public void addParkArea(ParkArea area);
	
	/*
	 * �����С������������һ��ͣ����
	 */
	public void removeParkArea(ParkArea area);
	
	/*
	 * С���ѵ�����
	 */
	public String getName();
	
	/*
	 * ͣ��
	 */
	public Ticket parkCar(Car car);
	
	/*
	 * ȡ��
	 */
	public Car removeCar(Ticket ticket);
	
	/*
	 * ��ȡ�ճ�λ����
	 */
	public int getFreeCount();
}