
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * This class is an abstract class, and parent of BasicContainer and HeavyContainer classes.
 * Basicly, containers are loading to ships, unloading from ships, and travelling between ports with ships.
 * This class implements Comparable interface to do sorting operations.
 * @author sagolinata111
 *
 */
public abstract class Container implements Comparable<Container>{
	/**
	 * ID of the container. The ID of the container cannot be changed once it is crated.
	 */
	protected final int ID;
	
	/**
	 * Weight of the container.
	 */
	protected int weight;
	
	/**
	 * Fuel consumption per weight of the container. Spesific type of containers have different fuel consumption per weight.
	 */
	protected double fuelConsumptionPerWeight;
	
	/**
	 * This construction takes parameter ID and weight to initialize the fields.
	 * @param ID The ID of the container.
	 * @param weight The weight of the container.
	 */
	public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	
	/**
	 * Calculates the consumption of container when travelling
	 * @return the value of weight multiplied by fuel consumption per weight of the container.
	 */
	public double consumption() {
		return weight * fuelConsumptionPerWeight;
	}
	
	/**
	 * Checks if the enclosed object is the same object with parameter other.
	 * @param other Another container that we want to compare with.
	 * @return true, if parameter other and enclosed object have same ID, same weight, and are objects of the same class.
	 */
	public boolean equals(Container other) {
		if(other.getClass() == this.getClass()) {
			if(this.ID == other.getID() && this.weight == other.getWeight()) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * getter method for ID.
	 * @return ID of the container.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * getter method for weight.
	 * @return weight of the container.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * compareTo method of the comparable interface. This method is implemented to do sort operations of containers according to their ID's.
	 * @return 0 if the parameter container and the enclosed object are same. Return 1 if the parameter container's ID is bigger than the enclosed object's ID. Return -1 if the parameter container's ID is smaller than the enclosed object's ID.
	 */
	public int compareTo(Container container) {
		if(this.equals(container)) {
			return 0;
		}
		if(this.ID > container.getID()) {
			return 1;
		}
		if(this.ID < container.getID()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

