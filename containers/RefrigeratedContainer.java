
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * This class extends HeavyContainer class. Refrigerated Container objects are created through this class.
 * This class is a final class because in the simulation there are no other type of container that can extend this class.
 * @author sagolinata111
 *
 */
public final class RefrigeratedContainer extends HeavyContainer{
	/**
	 * Constructs the Liquid Containers according to the given parameters.
	 * Also, initializes fuel consumption per weight of the Liquid Container as 5.00
	 * @param ID ID of the refrigerated container.
	 * @param weight weight of the refrigerated container.
	 */
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
		super.fuelConsumptionPerWeight = 5.00;
	}
	

}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

