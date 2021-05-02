package application;

public class Town {

	String name;
	Town town;
	
	/**
	 * 
	 * @param name - town's name
	 */
	public Town(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @param templateTown - an instance of Town
	 */
	public Town(Town templateTown) {
		town = templateTown;
	}
	
	public Town deepCopy() {
		Town clone = new Town(name);
		return clone;
	}
	
	/**
	 * 
	 * @return town's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param o
	 * @return  0 if names are equal, 
	 * a positive or negative number if the names are not equal
	 */
	public int compareTo(Town o) {
		int compare = town.compareTo(o);
		if(compare == 0) {
			return 0;
		}
		if(compare < 0) {
			return -1;
		}
		else
			return 1;
		
	}
	
	/** 
	 * @return the town name
	 */
	
	public String toString() {
		return "Town: " + town.name;
	}
	
	/**
	 * @return the hashcode for the name of the town
	 */
	
	public int hashCode() {
		int hash = 0;
		String code = String.valueOf(getName());
		int length = code.length();
		for(int i=0; i<length; i++) {
			hash += 31*hash + code.charAt(i);
		}
		hash = code.hashCode() % length;
		return hash;
	}
	
	/**
	 * @return true if the town names are equal, false if not
	 */
	
	public boolean equals(Town obj) {
		if(town.equals(obj)) { 
			return true;
		}
		else
			return false;
	}
}
