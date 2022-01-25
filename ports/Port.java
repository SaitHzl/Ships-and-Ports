
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE


package ports;
import interfaces.IPort;
import java.util.ArrayList;
import java.util.Collections;
import ships.Ship;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.BasicContainer;
import containers.RefrigeratedContainer;
/**
 * Represents the ports in the simulation. According to the simulation ports are created through this class. 
 * Port objects that are created from this class has some spesific fields to accomplish the given task.
 * This class implements IPort interface.
 * Basicly, ships are coming to ports and do the given tasks such as loading or unloading if possible.
 * We can think of ports in the 2-D coordinate system.
 * @author sagolinata111
 */
public final class Port implements IPort{
	
	/**
	 *  ID of the port. Every ports in the simulation must have unique Id's. Once the port is created, its ID cannot be changed.
	 */
	private final int ID;
	
	/**
	 *  X coordinate of the port. This field shows where the port is located in terms of X axes of the coordinate system. Once the port is created, its location caanot be changed.
	 */
	private final double X;
	
	/**
	 * Y coordinate of the port. This field shows where the port is located in terms of Y axes of the coordinate system. Once the port is created, its location caanot be changed.
	 */
	private final double Y;
	
	/**
	 * Represents the containers that are in the port.
	 */
	public ArrayList<Container> containers;
	
	/**
	 * Represents the ships that has visited, and not in the port currently.
	 */
	public ArrayList<Ship> history;
	
	/**
	 * Represents the ships that are currenly in the port.
	 */
	public ArrayList<Ship> current;
	
	/**
	 * Keeps the Basic Containers that are currently in the port.
	 */
	private ArrayList<BasicContainer> basicContainers;
	
	/**
	 * Keeps Heavy Containers that are currently in the port. 
	 * The spesific types of Heavy container that are Liquid and Heavy containers are not kept in this ArrayList.
	 */
	private ArrayList<HeavyContainer> heavyContainers;
	
	/**
	 * Keeps the Liquid Containers that are currently in the port.
	 */
	private ArrayList<LiquidContainer> liquidContainers;
	
	/**
	 * Keeps the Refrigerated Containers that are currently in the port.
	 */
	private ArrayList<RefrigeratedContainer> refrigeratedContainers;
	
	/**
	 * Constructs the port according the given input. The ID, X coordinate, and Y coordinate of the port are given as parameters.
	 * When a port created, the ArrayLists of Current, History, containers, basicContainers, heavyContainers, liquidContainers, refrigeratedContainers are initialized.
	 * 
	 * @param ID ID of the port.
	 * @param X X coordinate of the port.
	 * @param Y Y coordinate of the port.
	 */
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
		this.containers = new ArrayList<Container>();
		this.history = new ArrayList<Ship>();
		this.current = new ArrayList<Ship>();
		this.basicContainers = new ArrayList<BasicContainer>();
		this.heavyContainers = new ArrayList<HeavyContainer>();
		this.liquidContainers = new ArrayList<LiquidContainer>();
		this.refrigeratedContainers = new ArrayList<RefrigeratedContainer>();
	}
	
	/**
	 * This method is an interface method. Takes the parameter s that is an object of ship, and adds it to current ArrayList.
	 * @param s Ship that is coming to the port.
	 */
	public void incomingShip(Ship s) {
		current.add(s);
	}
	/**
	 * This method is an interface method. Takes the parameter s that is an object of ship, and removes from current Arraylist.
	 * If ship s is not in the history list, If ship s is coming this port first time, this method adds ship s to history ArrayList.
	 * @param s Ship that is lefting the port.
	 */
	public void outgoingShip(Ship s) {
		current.remove(s);
		if(!this.history.contains(s)) {
			history.add(s);
		}
		
	}
	/**
	 * This method calculates and returns the distances between Parametre other and this enclosed object of port.
	 * @param other Another port.
	 * @return the distance between ports.
	 */
	public double getDistance(Port other) {
		double distance = Math.sqrt(Math.pow((this.X-other.getX()),2) + Math.pow((this.Y-other.getY()),2));
		return distance;
	}
	
	/**
	 * getter method for ID.
	 * @return the ID of the port.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * getter method for X coordinate of the port.
	 * @return the X coordinate of the port.
	 */
	public double getX() {
		return X;
	}
	
	/**
	 * getter method for Y coordinate of the port.
	 * @return the Y coordinate of the port.
	 */
	public double getY() {
		return Y;
	}
	
	/**
	 * This method first sorts the objects in the  ArrayList of basicContainers according to their IDs. 
	 * @return the string that is the Ids of the containers in the ports in ascending order with a whitespace between them. 
	 */
	public String basicToString() {
		Collections.sort(basicContainers);
		String string = "";
		if(basicContainers.size() > 0){
			string += "  " + "BasicContainer:";
			for(int index = 0; index < basicContainers.size(); index++) {
				string += " " + String.valueOf(basicContainers.get(index).getID());
			}
			string += "\n";
		}
		return string;
		
	}
	
	/**
	 * This method first sorts the objects in the  ArrayList of heavyContainers according to their IDs.
	 * @return  the string that is the Ids of the containers in the ports in ascending order with a whitespace between them. 
	 */
	public String heavyToString() {
		Collections.sort(heavyContainers);
		String string = "";
		if(heavyContainers.size() > 0){
			string += "  " + "HeavyContainer:";
			for(int index = 0; index < heavyContainers.size(); index++) {
				string += " " + String.valueOf(heavyContainers.get(index).getID());
			}
			string += "\n";
		}
		return string;
		
	}
	
	/**
	 * This method first sorts the objects in the  ArrayList of liquidContainers according to their IDs.
	 * @return  the string that is the Ids of the containers in the ports in ascending order with a whitespace between them. 
	 */
	public String liquidToString() {
		Collections.sort(liquidContainers);
		String string = "";
		if(liquidContainers.size() > 0){
			string +="  " + "LiquidContainer:";
			for(int index = 0; index < liquidContainers.size(); index++) {
				string += " " + String.valueOf(liquidContainers.get(index).getID());
			}
			string += "\n";
		}
		return string;
		
	}
	
	/**
	 * This method first sorts the objects in the  ArrayList of refrigeratedContainers according to their IDs.
	 * @return the string that is the Ids of the containers in the ports in ascending order with a whitespace between them. 
	 */
	public String refrigeratedToString() {
		Collections.sort(refrigeratedContainers);
		String string = "";
		if(refrigeratedContainers.size() > 0){
			string += "  " + "RefrigeratedContainer:";
			for(int index = 0; index < refrigeratedContainers.size(); index++) {
				string += " " + String.valueOf(refrigeratedContainers.get(index).getID());
			}
			string += "\n";
		}
		return string;
		
	}
	
	/**
	 * sorts the current ships that is spesific to a object with respect to ship's Ids in ascending order.
	 * @return the sorted current arraylist.
	 */
	public ArrayList<Ship> sortedCurrent(){
		Collections.sort(current);
		return current;
	}
	
	/**
	 * getter method for Basic Containers in the port.
	 * @return Arraylist of basicContainers in the port.
	 */
	public ArrayList<BasicContainer> getBasicContainers() {
		return basicContainers;
	}
	
	/**
	 * getter method for Liquid Containers in the port.
	 * @return ArrayList of liquidContainers in the port.
	 */
	public ArrayList<LiquidContainer> getLiquidContainers() {
		return liquidContainers;
	}
	
	/**
	 * getter method for Heavy Containers in the port.
	 * @return ArrayList of heavyContainers in the port.
	 */
	public ArrayList<HeavyContainer> getHeavyContainers() {
		return heavyContainers;
	}
	
	/**
	 * getter method for Refrigerated Containers in the port.
	 * @return ArrayList of refrigeratedContainers in the port.
	 */
	public ArrayList<RefrigeratedContainer> getRefrigeratedContainers() {
		return refrigeratedContainers;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

