package application;
/**
 * 
 * @author Nathan Assefa
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Graph implements GraphInterface<Town, Road>{
	Road road;
	ArrayList<Town> vertices = new ArrayList<>();
	ArrayList<Road> edge = new ArrayList<>();
	ArrayList<Integer> weight = new ArrayList<>();
	int[][] adjMatrix = new int[100][100];
	int numOfVertices;
	
	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if(sourceVertex == null || destinationVertex == null)
			return null;
		for(int i=0; i < edge.size(); i++) {
			if(edge.get(i).source.name == sourceVertex.name || edge.get(i).source.name == destinationVertex.name && 
				edge.get(i).destination.name == destinationVertex.name || edge.get(i).destination.name == sourceVertex.name) {
				return edge.get(i);

			}
		}
			return null;
	}

	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(sourceVertex == null || destinationVertex == null) {
			return null;
		}
		road = new Road(sourceVertex, destinationVertex, weight, description);
		edge.add(road);
		this.weight.add(weight);
		adjMatrix[Math.abs(sourceVertex.name.hashCode()%100)][Math.abs(destinationVertex.name.hashCode()%100)] = weight;
		
		return road;
	}

	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	public boolean addVertex(Town v) {
		vertices.add(v);
		numOfVertices++;
		return true;
	}

	 /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for(int i=0; i<edge.size(); i++) {
			if(edge.get(i) != null && edge.get(i).source.name.equals(sourceVertex.name) && edge.get(i).destination.name.equals(destinationVertex.name)) {
				return true;
			}
		}
		return false;
	}

	 /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	public boolean containsVertex(Town v) {
		for(int i=0; i<vertices.size(); i++) {
			if(vertices.get(i) != null && vertices.get(i).name.equals(v.name)) {
				return true;
			}
		}
		return false;
	}

	 /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	public Set<Road> edgeSet() {
		Set<Road> edgesCopy = new HashSet<>();
		for(int i=0; i<edge.size(); i++) {
			edgesCopy.add(edge.get(i));
		}
		return edgesCopy;
	}

	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> edgesCopy = new HashSet<>();
		
		for(int i=0; i<edge.size(); i++){
			if(edge.get(i).source== vertex || edge.get(i).destination == vertex) {
				edgesCopy.add(edge.get(i));
			}
		}
		return edgesCopy;
	}

	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		for(int i=0; i < edge.size(); i++) {
			if(edge.get(i).source.name.equals(sourceVertex.name) && edge.get(i).destination.name.equals(destinationVertex.name) && edge.get(i).name.equals(description)) {
				this.weight.remove(i);
				return edge.remove(i);
			}
		}
		return null;
	}

	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	
	public boolean removeVertex(Town v) {
		for(int i=0; i < vertices.size(); i++) {
			if(vertices.get(i).name != null && vertices.get(i).name.equals(v.name)) {
				vertices.remove(i);
			}
		}
		if(Collections.frequency(vertices, v) == 0) {
			return true;
		}
		else 
			return false;
	}

	 /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	public Set<Town> vertexSet() {
		Set<Town> verticesCopy = new HashSet<>();
		ArrayList<Town> townNames2 = new ArrayList<>();
		townNames2 = removeDuplicates(vertices);

		for(int i=0; i<vertices.size(); i++) {
			if(vertices.get(i) != null) {
			verticesCopy.add(townNames2.get(i));
			}
		}

		return verticesCopy;
	}

	public ArrayList<Town> removeDuplicates(ArrayList<Town> list){
		ArrayList<Town> duplicateFree = new ArrayList<Town>();
		
		for(Town element : list) {
			if(!duplicateFree.contains(element)) {
				duplicateFree.add(element);
				continue;
			}
		}
		
		return duplicateFree;
	}
	/**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */  
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		Set<Road> copy = edgesOf(sourceVertex);
		ArrayList<Road> roadCopy = new ArrayList<>();
		Iterator<Road> itr = copy.iterator();
		for(int i=0; i<copy.size(); i++) {
			roadCopy.add(itr.next());
		}
		int count = 0;
		ArrayList<String> shortPath = new ArrayList<>();
		boolean sorted = false;
	    Road temp;
	    

	    
	    for(int i=0; i<roadCopy.size(); i++) {
		 Collections.sort(roadCopy);
		 shortPath.add(roadCopy.get(i).source.name + " via " + roadCopy.get(i).name + " to " + roadCopy.get(i).destination.name + " " + roadCopy.get(i).degrees + " mi");
		 if(roadCopy.get(i).destination.name == destinationVertex.name) {
			 break;
		 }

		 
	     }
		
		return shortPath;
	}

	public String getIndex(String index) {
		for(int i=0; i<edge.size(); i++) {
			if(edge.get(i).degrees == Integer.valueOf(index.trim())) {
				return String.valueOf(i);
			}
		}
		return String.valueOf(-1);
	}
	 /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	public void dijkstraShortestPath(Town sourceVertex) {
		// TODO Auto-generated method stub
		
	}

	


}
