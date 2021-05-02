package application;

public class Road implements Comparable<Road> {
	
	Town destination, source;
	String name;
	int degrees;
	Road road;
	/**
	 * @param source - One town on the road
	 * 	@param destination - Another town on the road
	 *	@param degrees - Weight of the edge, i.e., distance from one town to the other
	 *	@param name - Name of the road
	 * 
	 */
	Road(Town source, Town destination, int degrees, String name){
		this.source = source;
		this.destination = destination;
		this.degrees = degrees;
		this.name = name;
	}
	
	

	/**
	 * @param source - One town on the road
	 * 	@param destination - Another town on the road
	 *	@param name - Name of the road
	 * 
	 */
	Road(Town source, Town destination, String name){
		this.source = source;
		this.destination = destination;
		this.degrees = 1;
		this.name = name;
	}
	
	/**
	 * 
	 * @param town - a vertex of the graph
	 * @return true only if the edge is connected to the given vertex
	 */
	
	public boolean contains(Town town) {
		if(road.source.name.equals(town.name) || road.destination.name.equals(town.name)) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * @return a string
	 */
	@Override
	public String toString() {
		return null;
	}
	
	/**
	 * 
	 * @return the road name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return thesecond town on the road
	 */
	public Town getDestination() {
		return destination;
	}
	
	/**
	 * 
	 * @return the first town on the road
	 */
	public Town getSource() {
		return source;
	}
	
	/**
	 * 
	 * @param o
	 * @return 0 if the road names are the same, 
	 * a positive or negative number if the road names are not the same
	 */

	public int compareTo(Road o) {
		if(degrees == o.degrees) {
			return 0;
		}
		if(degrees < o.degrees) {
			return -1;
		}
		else
			return 1;
	}
	
	/**
	 * 
	 * @return the distance of the road
	 */
	public int getWeight() {
		return degrees;
	}
	
	/**
	 * @param r - road object to compare it to
	 * Returns true if each of the ends of the road r is the same as the ends of this road. Remember that a road 
	 * that goes from point A to point B is the same as a road that goes from point B to point A.
	 */
	
	public boolean equals(Road r) {
		if(road.source.equals(r.source) || road.destination.equals(r.destination)) {
			return true;
		}
		else
			return false;
	}



}
