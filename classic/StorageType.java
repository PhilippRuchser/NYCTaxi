package classic;

public class StorageType implements Comparable{
	private String pickup;
	private double fare;
	
	@Override
	public String toString() {
		return "StorageType [pickup=" + pickup + ", fare=" + fare + "]";
	}
	public StorageType(String pickup, double fare) {
		super();
		this.pickup = pickup;
		this.fare = fare;
	}
	public String getPickup() {
		return pickup;
	}
	public void setPickup(String pickup) {
		this.pickup = pickup;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public boolean add(StorageType addMe)
	{
		if (!this.pickup.equals(addMe.getPickup())) return false;
		this.fare += addMe.getFare();
		return true;
	}
	
	
	// Compare only according to pickup location
	@Override
	public int hashCode() 
	{ 
		return pickup.hashCode();
	}

	@Override
	public boolean equals(Object o) 
	{
		if (o == null) return false;
	    if (!(o instanceof StorageType)) return false;
	    StorageType st = (StorageType) o;
	    return this.pickup.equals(st.getPickup());
	 }
	
	@Override
	public int compareTo(Object o) {
		if (o == null) return 0;
	    if (!(o instanceof StorageType)) return 0;
		StorageType st = (StorageType) o;
		
		return this.pickup.compareTo(st.getPickup());

	}
	
}
