
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;
import java.util.ArrayList;
import java.util.Collections;
import containers.Container;
import interfaces.IShip;
import ports.Port;
import containers.BasicContainer;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
/**
 * Represents the ships in the simulation. According to the given input ships are created through this class.
 * Ships have some spesific fields and methods to do the given task such as loading, unloading or sailing to another port.
 * This class implements IShip interface. Also Comparable interface is implemented to sorting (ascending order) the ID's of the ships in a port.
 * Basicly, ships are travelling between ports and carries the containers to the ports.
 * @author sagolinata111
 *
 */
public final class Ship implements IShip, Comparable<Ship> {
	
	/**
	 * ID of the ship. ID's are different in every ships. Once the ship is created, the ID of the ship cannot be changed.
	 */
	private final int ID;
	
	/**
	 * Fuel in the ship.
	 */
	private double fuel;
	
	/**
	 * Fuel consumption that a ship consumes in 1 km.
	 */
	private double fuelConsumptionPerKM;
	
	/**
	 * The port that ship is currently at.
	 */
	private Port currentPort;
	
	/**
	 * The capacity of weight that ship is able to carry. The total weight capacity of the ship cannot be changed.
	 */
	private final int totalWeightCapacity;
	
	/**
	 * The number of all containers that ship can carry.
	 */
	private final int maxNumberOfAllContainers;
	
	/**
	 * The number of Heavy Containers that ship can carry.
	 */
	private final int maxNumberOfHeavyContainers;
	
	/**
	 * The number of Refrigerated Containers that ship can carry.
	 */
	private final int maxNumberOfRefrigeratedContainers;
	
	/**
	 * The number of LiquidContainers that ship can carry.
	 */
	private final int maxNumberOfLiquidContainers;
	
	/**
	 * Arraylist of all containers in ship.
	 */
	private ArrayList<Container> containersInShip;
	
	/**
	 * Arraylist of Heavy Containers in ship. Spesific types of heavy containers (liquid and refrigerated) are not kept in this arraylist.
	 */
	private ArrayList<HeavyContainer> heavyContainersInShip;
	
	/**
	 * ArrayList of Liquid Containers in ship.
	 */
	private ArrayList<LiquidContainer> liquidContainersInShip;
	
	/**
	 * ArrayList of Refrigerated Containers in ship.
	 */
	private ArrayList<RefrigeratedContainer> refrigeratedContainersInShip;
	
	/**
	 * Arraylist of Basic Containers in ship.
	 */
	private ArrayList<BasicContainer> basicContainersInShip;
	
	/**
	 * Constructs the ship according to given input.
	 * The ID of the ship, where the ship is created, total capacity of weight, the number of all containers, liquid containers, heavy containers, basic containers that ship can carry most, and the fuel consumption that ship consumes in 1 km are given as parameters.
	 * When a ship created, fuel is initialized as 0.
	 * Also, the arraylists of containersInShip, heavyContainersInShip, liquidContainersInShip, RefrigeratedContainersInShip, BasicContainersInShip are initiliazed.
	 * @param ID ID of the ship.
	 * @param p Port where the ship currently is.
	 * @param totalWeightCapacity maximum weight that ship can carry.
	 * @param maxNumberOfAllContainers maximum number of all types of containers that ship can carry.
	 * @param maxNumberOfHeavyContainers maximum number of heavy containers including heavy, liquid, refrigerated that ship can carry.
	 * @param maxNumberOfRefrigeratedContainers maximum number of refrigerated containers that ship can carry.
	 * @param maxNumberOfLiquidContainers maximum number off liquid containers that ship can carry.
	 * @param fuelConsumptionPerKM Fuel consumption that a ship consumes in 1 km.
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM){
		this.ID = ID;
		fuel = 0;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		currentPort = p;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.containersInShip = new ArrayList<Container>();
		this.heavyContainersInShip = new ArrayList<HeavyContainer>();
		this.liquidContainersInShip = new ArrayList<LiquidContainer>();
		this.refrigeratedContainersInShip = new ArrayList<RefrigeratedContainer>();
		this.basicContainersInShip = new ArrayList<BasicContainer>();
	}
	/**
	 * Checks the travelling possibilities. If the ship has enough fuel to sail the another port p, fuel is decreasing. Otherwise, nothing happens.
	 * @param p the other port that ship is trying to go to.
	 * @return true, if the ship has enough fuel. False, if the ship do not have enough fuel.
	 */
	public boolean sailTo(Port p) {
		if((this.currentPort.getDistance(p) *  fuelConsumptionPerKM ) + totalFuelConsumptionWhenTravelling() * this.currentPort.getDistance(p)  <= fuel) {
			fuel = fuel - ((this.currentPort.getDistance(p) *  fuelConsumptionPerKM ) + totalFuelConsumptionWhenTravelling() * this.currentPort.getDistance(p));
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * takes parameter newFuel and adds it to fuel of the ship.
	 * @param newFuel amount of new fuel that added to the ship's fuel.
	 */
	public void reFuel(double newFuel) {
		fuel += newFuel;
	}
	
	/**
	 * Checks if the parameter cont is currently in the port. Otherwise, nothing happens.
	 * If container currently in the currentPort, first adds it to containersInShip that keeps all types of containers in the ship.
	 * Then, according to the type of the container, adds it to spesific arraylist in the ship.
	 * @return true if parameter cont is currently in the currentPort, false if parameter cont is not currently in the currentPort.
	 * @param cont Container that is being tried to load to the ship.
	 * 
	 */
	public boolean load(Container cont) {
		if(currentPort.containers.contains(cont)) {
			if(cont instanceof BasicContainer && (this.containersInShip.size() + 1 <= maxNumberOfAllContainers) && (cont.getWeight() + getTotalWeightInShip() <= totalWeightCapacity)) {
				return true;
			}
			
			else if(cont instanceof HeavyContainer && !(cont instanceof RefrigeratedContainer) && !(cont instanceof LiquidContainer) && (this.containersInShip.size() + 1 <= maxNumberOfAllContainers ) && (heavyContainersInShip.size() + liquidContainersInShip.size() + refrigeratedContainersInShip.size() + 1 <= maxNumberOfHeavyContainers) && (cont.getWeight() + getTotalWeightInShip() <= totalWeightCapacity)) {
				return true;
			}
			
			else if(cont instanceof LiquidContainer && (this.containersInShip.size() + 1 <= maxNumberOfAllContainers) && (heavyContainersInShip.size()+ liquidContainersInShip.size()+ refrigeratedContainersInShip.size()+ 1 <= maxNumberOfHeavyContainers) && (liquidContainersInShip.size()+ 1 <= maxNumberOfLiquidContainers) && (cont.getWeight() + getTotalWeightInShip() <= totalWeightCapacity)) {
				return true;
			}
			
			else if(cont instanceof RefrigeratedContainer && (this.containersInShip.size() + 1 <= maxNumberOfAllContainers) && (heavyContainersInShip.size()+ liquidContainersInShip.size() + refrigeratedContainersInShip.size()+ 1 <= maxNumberOfHeavyContainers) && (refrigeratedContainersInShip.size() + 1 <= maxNumberOfRefrigeratedContainers) && (cont.getWeight() + getTotalWeightInShip() <= totalWeightCapacity)) {
				return true;
			}
			else {
				return false;
			}	
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks whether ship is able to unload the parameter cont.
	 * @return true if parameter cont is currently in the ship, false if parameter cont is not in the ship.
	 * @param cont Container that is being tried to unload from ship.
	 */
	public boolean unLoad(Container cont) {
		if(containersInShip.contains(cont)) {
			return true;	
		}
		else {
			return false;
		}
	}
	
	/**
	 * Calculates the consumption of containers in the ship when ship travels.
	 * Every types of ship has spesific consumption constant. Total consumption is calculated by multiplying weight with the consumption constant and adding them up.
	 * @return total consumption of containers.
	 */
	private double totalFuelConsumptionWhenTravelling() {
		double totalConsumption = 0;
		for(int index = 0; index < this.containersInShip.size(); index++) {
			totalConsumption += containersInShip.get(index).consumption();
			
		}
		return totalConsumption;
		
	}
	
	/**
	 * Calculates the weight of all containers in the ship.
	 * @return total weight in the ship.
	 */
	private int getTotalWeightInShip() {
		int totalWeightInShip = 0;
		for(int index = 0; index < this.containersInShip.size(); index++) {
			totalWeightInShip += containersInShip.get(index).getWeight();
		}
		return totalWeightInShip;
		
	}
	
	/**
	 * Basicly, this method is used to do loading operations in the simulation according to the given input. 
	 * Takes parameter container and checks if loading is possible. If loading is possible, adds it to containersInShip arraylist and removes from currentPort.
	 * Also, if loading is possible, parameter container is added to arraylist of spesific container type. For instance, if container is a type of liquid container, and loading is possible, container is added to both containersInShip and liquidContainersInShip arraylists.
	 * If loading is not possible, nothing happens.
	 * @param container Container that added to the ship if possible.
	 */
	public void loading(Container container) {
		if(load(container)) {
			containersInShip.add(container);
			currentPort.containers.remove(container);
			if(container instanceof LiquidContainer) {
				this.liquidContainersInShip.add((LiquidContainer) container);
				this.currentPort.getLiquidContainers().remove((LiquidContainer) container);
			}
			else if(container instanceof RefrigeratedContainer) {
				this.refrigeratedContainersInShip.add((RefrigeratedContainer) container);
				this.currentPort.getRefrigeratedContainers().remove((RefrigeratedContainer) container);
			}
			else if(container instanceof HeavyContainer) {
				this.heavyContainersInShip.add((HeavyContainer) container);
				this.currentPort.getHeavyContainers().remove((HeavyContainer) container);
			}
			else if(container instanceof BasicContainer) {
				this.basicContainersInShip.add((BasicContainer) container);
				this.currentPort.getBasicContainers().remove((BasicContainer) container);
			}
		}
	}
	
	/**
	 * This method does unloading operations in the simulation according to the given input.
	 * Takes parameter container and checks if unloading is possible. If unloading is possible, parameter container is removed from containersInShip arraylist and added containers arraylist of currentPort.
	 * If unloading is not possible, nothing happens.
	 * @param container Container that unloaded from the ship if possible.
	 */
	public void unloading(Container container) {
		if(unLoad(container)) {
			containersInShip.remove(container);
			currentPort.containers.add(container);
			if(container instanceof LiquidContainer) {
				this.liquidContainersInShip.remove((LiquidContainer) container);
				this.currentPort.getLiquidContainers().add((LiquidContainer) container);
			}
			else if(container instanceof RefrigeratedContainer) {
				this.refrigeratedContainersInShip.remove((RefrigeratedContainer) container);
				this.currentPort.getRefrigeratedContainers().add((RefrigeratedContainer) container);
			}
			else if(container instanceof HeavyContainer) {
				this.heavyContainersInShip.remove((HeavyContainer) container);
				this.currentPort.getHeavyContainers().add((HeavyContainer) container);
			}
			else if(container instanceof BasicContainer) {
				this.basicContainersInShip.remove((BasicContainer) container);
				this.currentPort.getBasicContainers().add((BasicContainer) container);
			}
		}
	}
	
	/**
	 * This method does travelling operations in the simulation. If travveling is possible (if ship has enough fuel), ship leaves the currentPort and arrives to paramether other port.
	 * @param other Another port that ship is trying to go to.
	 */
	public void travelling(Port other) {
		if(sailTo(other)) {
			currentPort.outgoingShip(this);
			currentPort = other;
			other.incomingShip(this);
		}
	}
	
	/**
	 * Sorts the objects in the containersInShip arraylist according to their IDs in ascending order.
	 * @return sorted containersInShip arraylist.
	 */
	public ArrayList<Container> getCurrentContainers() {
		Collections.sort(containersInShip);
		return containersInShip;
	}
	
	/**
	 * getter method for ID.
	 * @return ID of the ship.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * getter method for fuel
	 * @return fuel of the ship.
	 */
	public double getFuel() {
		return fuel;
	}
	
	/**
	 * sets the field fuel to parameter fuel.
	 * @param fuel 
	 */
	public void setFuel(double fuel) {
		this.fuel = fuel;
	}
	
	/**
	 * getter method for currentPort.
	 * @return Port where ship currently at.
	 */
	public Port getCurrentPort() {
		return currentPort;
	}
	
	/**
	 * sets the field currentPort to the parameter currentPort.
	 * @param currentPort
	 */
	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}
	
	/**
	 * This method first sorts the objects of basicContainersInShip arraylist according to their ID's in ascending order.
	 * This method is used to print sorted ID's of the basic containers in the ship.
	 * @return empty string if there is no basic container in the ship. If there is basic container in the ship, returns the string of sorted ID's of basic containers whitespace between them.
	 */
	public String basicshipToString() {
		Collections.sort(basicContainersInShip);
		String string = "";
		if(basicContainersInShip.size() > 0){
			string += "    " + "BasicContainer:";
			for(int index = 0; index < basicContainersInShip.size(); index++) {
				string += " " + String.valueOf(basicContainersInShip.get(index).getID());
			}
			string += "\n";
		}
		return string;
		
	}
	
	/**
	 * This method first sorts the objects of heavyContainersInShip arraylist according to their ID's in ascending order.
	 * This method is used to print sorted ID's of the heavy containers in the ship.
	 * @return empty string if there is no heavy container in the ship. If there is heavy container in the ship, returns the string of sorted ID's of heavy containers whitespace between them.
	 */
	public String heavyshipToString() {
		Collections.sort(heavyContainersInShip);
		String string = "";
		if(heavyContainersInShip.size() > 0){
			string += "    " + "HeavyContainer:";
			for(int index = 0; index < heavyContainersInShip.size(); index++) {
				string += " " + String.valueOf(heavyContainersInShip.get(index).getID());
			}
			string += "\n";
		}
		return string;
		
	}
	
	/**
	 * This method first sorts the objects of liquidContainersInShip arraylist according to their ID's in ascending order.
	 * This method is used to print sorted ID's of the liquid containers in the ship.
	 * @return empty string if there is no liquid container in the ship. If there is liquid container in the ship, returns the string of sorted ID's of liquid containers whitespace between them.
	 */
	public String liquidshipToString() {
		Collections.sort(liquidContainersInShip);
		String string = "";
		if(liquidContainersInShip.size() > 0){
			string +="    " + "LiquidContainer:";
			for(int index = 0; index < liquidContainersInShip.size(); index++) {
				string += " " + String.valueOf(liquidContainersInShip.get(index).getID());
			}
			string += "\n";
		}
		return string;
	}
	
	/**
	 * This method first sorts the objects of refrigeratedContainersInShip arraylist according to their ID's in ascending order.
	 * This method is used to print sorted ID's of the refrigerated containers in the ship.
	 * @return empty string if there is no refrigerated container in the ship. If there is refrigerated container in the ship, returns the string of sorted ID's of refrigerated containers whitespace between them.
	 */
	public String refrigeratedshipToString() {
		Collections.sort(refrigeratedContainersInShip);
		String string = "";
		if(refrigeratedContainersInShip.size() > 0){
			string += "    " + "RefrigeratedContainer:";
			for(int index = 0; index < refrigeratedContainersInShip.size(); index++) {
				string += " " + String.valueOf(refrigeratedContainersInShip.get(index).getID());
			}
			string += "\n";
		}
		return string;
		
	}
	
	/**
	 * compareTo method of the comparable interface. This method is implemented to do sort operations of containers according to their ID's.
	 * @return 0 if the parameter ship and the enclosed object have same ID, 1 if the parameter ship's ID is bigger than the enclosed object's ID, -1 if the parameter ship's ID is smaller than the enclosed object's ID.
	 */
	public int compareTo(Ship ship) {
		if(this.ID == ship.getID()) {
			return 0;
		}
		if(this.ID > ship.getID()) {
			return 1;
		}
		if(this.ID < ship.getID()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

