
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * This class extends the Container class. Basic container objects which are spesific type of containers are created through this class.
 * This class is a final class because no other type of container can extend BasicContainer class.
 * @author sagolinata111
 *
 */
public final class BasicContainer extends Container {
	
	/**
	 * Constructs the Basic Container according to given parameters.
	 * Also, initilazes the fuel consumption per weight of the basic container as 2.50
	 * @param ID ID of the Basic Container.
	 * @param weight Weight of the Basic Container.
	 */
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
		super.fuelConsumptionPerWeight= 2.50;
	}

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

