package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * 
 * @author Nathan Assefa
 *
 */
public class TownGraphManager implements TownGraphManagerInterface{
	
	TownGraphManager manager;
	Road edge;
	Graph graph = new Graph();
	ArrayList<Town> vertices = new ArrayList<>();
	ArrayList<Road> roads = new ArrayList<>();
	ArrayList<Integer> weight = new ArrayList<>();

	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		roads.add(graph.addEdge(new Town(town1), new Town(town2), weight, roadName));
		return true;
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	public String getRoad(String town1, String town2) {
		for(int i=0; i < roads.size(); i++) {
		if(roads.get(i).source.name.equals(town1) && roads.get(i).destination.name.equals(town2)) {
			return roads.get(i).name;
		}
	}
		return null;
	}

	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v) {
		graph.addVertex(new Town(v));
		return true;
	}

	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	public Town getTown(String name) {
		for(int i=0; i < graph.vertices.size(); i++) {
			if(graph.vertices.get(i).name.equals(name)) {
				return graph.vertices.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads() {
		ArrayList<String> roadsNames = new ArrayList<>();
		for(int i=0; i<graph.edge.size(); i++) {
			roadsNames.add(graph.edge.get(i).name);
		}
		Collections.sort(roadsNames);
		
		return roadsNames;
	}

	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if(graph.removeEdge(new Town(town1), new Town(town2), 0, road) != null) {
			return true;
		}
		else
			return false;
		
	}

	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	public ArrayList<String> allTowns() {
		ArrayList<String> townNames = new ArrayList<>();
		ArrayList<String> townNames2 = new ArrayList<>();

		for(int i=0; i<graph.vertices.size(); i++) {
			townNames.add(graph.vertices.get(i).name);
		}
		Collections.sort(townNames);
		townNames2 = removeDuplicates(townNames);
		
		return townNames2;
	}
	
	public ArrayList<String> removeDuplicates(ArrayList<String> list){
		ArrayList<String> duplicateFree = new ArrayList<String>();
		
		for(String element : list) {
			if(!duplicateFree.contains(element)) {
				duplicateFree.add(element);
			}
		}
		
		return duplicateFree;
	}
	
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException {
		Scanner data = new Scanner(selectedFile);
		Scanner space;
		Scanner slash;
		int count=0;
		String morseCode = "", temp2, temp;
		if(!selectedFile.canExecute()) {
			throw new FileNotFoundException();
		}
		while(data.hasNextLine()) {
			slash = new Scanner(data.nextLine());
			slash.useDelimiter(";");
			
			while(slash.hasNext()) {

				temp = slash.next();
				if(count%3 != 0) {
					graph.addVertex(new Town(temp));
				}
			
				count++;
			}
		}
		data.close();		
	}

}
