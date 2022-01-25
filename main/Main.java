
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import containers.Container;
import ships.Ship;
import ports.Port;
import containers.BasicContainer;
import containers.RefrigeratedContainer;
import containers.HeavyContainer;
import containers.LiquidContainer;
/**
 * This main class is created to read the input file, do the operations, and writing necessary informations on the output file.
 * The operations in the simulation are creating container, creating ship, creating port, loading, unloading, sailing, and refueling.
 * The whole simulation is triggered through main method in this class.
 * @author sagolinata111
 */
public class Main {
	
	/**
	 * Main method triggers the whole project. Since inputs are given in the format of files, it throws FileNotFoundException to guarantee that file exists.
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		int numberOfActions = in.nextInt();
		//creating arraylists
		ArrayList<Container> containerLst = new ArrayList<Container>();
		ArrayList<Ship> shipLst = new ArrayList<Ship>();
		ArrayList<Port> portLst = new ArrayList<Port>();
		//actions
		in.nextLine(); // just an empty line
		int containerId = 0;
		int shipId = 0;
		int portId = 0;
		for(int actionLines = 0; actionLines < numberOfActions; actionLines++) {
			String line = in.nextLine();
			line = line.replaceAll("( )+", " "); // removing additional spaces
			line = line.strip();
			String lineArray[];
			lineArray = line.split(" ");
			//creating Containers
			if(Integer.valueOf(lineArray[0]) == 1) {
				if(lineArray.length == 3) {
					if(Integer.valueOf(lineArray[2]) <= 3000) {
						BasicContainer basic = new BasicContainer(containerId,Integer.valueOf(lineArray[2]));
						containerLst.add(basic);
						portLst.get(Integer.valueOf(lineArray[1])).containers.add(basic);
						portLst.get(Integer.valueOf(lineArray[1])).getBasicContainers().add(basic);
						containerId += 1;
					
					}
					else {
						HeavyContainer heavy = new HeavyContainer(containerId, Integer.valueOf(lineArray[2]));
						containerLst.add(heavy);
						portLst.get(Integer.valueOf(lineArray[1])).containers.add(heavy);
						portLst.get(Integer.valueOf(lineArray[1])).getHeavyContainers().add(heavy);
						containerId +=1;
					}
				}
				if(lineArray.length == 4) {
					if(lineArray[3].charAt(0) == 'R') {
						RefrigeratedContainer refrigerated = new RefrigeratedContainer(containerId,Integer.valueOf(lineArray[2]));
						containerLst.add(refrigerated);
						portLst.get(Integer.valueOf(lineArray[1])).containers.add(refrigerated);
						portLst.get(Integer.valueOf(lineArray[1])).getRefrigeratedContainers().add(refrigerated);
						containerId += 1;
					}
					if(lineArray[3].charAt(0) == 'L') {
						LiquidContainer liquid = new LiquidContainer(containerId,Integer.valueOf(lineArray[2]));
						containerLst.add(liquid);
						portLst.get(Integer.valueOf(lineArray[1])).containers.add(liquid);
						portLst.get(Integer.valueOf(lineArray[1])).getLiquidContainers().add(liquid);
						containerId += 1;
					}
					
				}
			}
			
			//creating ships
			
			if(Integer.valueOf(lineArray[0]) == 2) {
				Ship ship = new Ship(shipId, portLst.get(Integer.valueOf(lineArray[1])), Integer.valueOf(lineArray[2]), Integer.valueOf(lineArray[3]),Integer.valueOf(lineArray[4]), Integer.valueOf(lineArray[5]), Integer.valueOf(lineArray[6]), Double.valueOf(lineArray[7]));
				shipLst.add(ship);
				portLst.get(Integer.valueOf(lineArray[1])).current.add(ship);
				shipId += 1;
				
			}
			
			//creating ports
			
			if(Integer.valueOf(lineArray[0]) == 3) {
				Port port = new Port(portId,Double.valueOf(lineArray[1]), Double.valueOf(lineArray[2]));
				portLst.add(port);
				portId += 1;
			}
			
			//loading
			
			if(Integer.valueOf(lineArray[0]) == 4 && (Integer.valueOf(lineArray[1]) < shipLst.size()) && (Integer.valueOf(lineArray[2]) < containerLst.size())) {
				shipLst.get(Integer.valueOf(lineArray[1])).loading(containerLst.get(Integer.valueOf(lineArray[2])));
			}
			
			//unloading
			
			if(Integer.valueOf(lineArray[0]) == 5 && (Integer.valueOf(lineArray[1]) < shipLst.size()) && (Integer.valueOf(lineArray[2]) < containerLst.size())) {
				shipLst.get(Integer.valueOf(lineArray[1])).unloading(containerLst.get(Integer.valueOf(lineArray[2])));
			}
			
			//sailing
			
			if(Integer.valueOf(lineArray[0]) == 6 && (Integer.valueOf(lineArray[1]) < shipLst.size()) && (Integer.valueOf(lineArray[2]) < portLst.size())) {
				shipLst.get(Integer.valueOf(lineArray[1])).travelling(portLst.get(Integer.valueOf(lineArray[2])));
			}
			
			//refuelling
			
			if(Integer.valueOf(lineArray[0]) == 7 && (Integer.valueOf(lineArray[1]) < shipLst.size())) {
				shipLst.get(Integer.valueOf(lineArray[1])).reFuel(Double.valueOf(lineArray[2]));
			}
			
		}		
		//writing output
		for(int a = 0; a < portLst.size(); a++) {
			out.print("Port" + " " + a + ":" + " " + "(" );
			out.printf("%.2f", portLst.get(a).getX());
			out.print(", ");
			out.printf("%.2f", portLst.get(a).getY());
			out.print(")");
			out.println();
			out.print(portLst.get(a).basicToString());
			out.print(portLst.get(a).heavyToString());
			out.print(portLst.get(a).refrigeratedToString());
			out.print(portLst.get(a).liquidToString());
			portLst.get(a).sortedCurrent(); // sorting the ships
			for(int x = 0; x < portLst.get(a).current.size(); x++) {
				out.print("  " + "Ship " + portLst.get(a).current.get(x).getID() + ":" + " ");
				out.printf("%.2f",portLst.get(a).current.get(x).getFuel());
				out.println();
				out.print(portLst.get(a).current.get(x).basicshipToString());
				out.print(portLst.get(a).current.get(x).heavyshipToString());
				out.print(portLst.get(a).current.get(x).refrigeratedshipToString());
				out.print(portLst.get(a).current.get(x).liquidshipToString());
				
			}
		}
		
		in.close();
		out.close();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

