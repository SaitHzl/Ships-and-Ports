
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * This class extends Container class. Heavy Container objects are created through this class.
 * @author sagolinata111
 *
 */
public class HeavyContainer extends Container {
	
	/**
	 * Constructs the Heavy Containers according to given parameters.
	 * Also, initializes fuel consumption per weight of the Heavy Container as 3.00
	 * @param ID ID of the Heavy Container.	
	 * @param weight Weight of the Heavy Container.
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
		super.fuelConsumptionPerWeight = 3.00;
	}
	
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

