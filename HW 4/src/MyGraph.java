import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {
    // you will need some private fields to represent the graph
    // you are also likely to want some private helper methods

    // YOUR CODE HERE
	// A map of the vertices and their adjacent vertices in the graph.
	private HashMap<Vertex, List<Vertex>> hashGraph;
	// A Set of all the edges in the graph.
	private Set<Edge> graphEdges;
	
    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {

		// YOUR CODE HERE
    	
    	hashGraph = new HashMap();
    	graphEdges = (HashSet<Edge>) e;
    	
    	// Tests if there is a duplicate directed edge with a different weight.
    	Map<String, Integer> duplicateEdgeTester = new HashMap<String, Integer>();
    	for(Edge edgeElement : e){
    		String key = edgeElement.getSource() + " " + edgeElement.getDestination();
    		int value = edgeElement.getWeight();
    		
    		// Adds to the hash map each unique directed edge.
    		if(!duplicateEdgeTester.keySet().contains(key)){
    			duplicateEdgeTester.put(key, value);
    		}else{
    			// if the weight of the stored directed edge is different from the weight
    			// of the duplicate directed edge, throw an illegal argument exception.
    			if(duplicateEdgeTester.get(key) != value){
    				throw new IllegalArgumentException("Edge from " + edgeElement.getSource() + 
    						" to " + edgeElement.getDestination() + 
    						" is the same as another directed edge but has a different weight");
    			}
    		}
    	}
    	
    	// Adds in all the vertices along with an empty array list for the adjacent vertices.
    	for(Vertex vertexElement : v){
    		if(!hashGraph.containsKey(vertexElement)){
        		hashGraph.put(vertexElement, new ArrayList<Vertex>());
    		}
    	}
    	
    	for(Edge edgeElement : graphEdges){
    		Vertex source = edgeElement.getSource();
    		Vertex destination = edgeElement.getDestination();
    		if(edgeElement.getWeight() < 0){
    			// If the weight of any edge is negative, throw an illegal argument exception.
    			throw new IllegalArgumentException("Edge from " + source + 
					    " to " + destination + " has a negative weight");
    		}else if(!hashGraph.containsKey(source)){
    			// If the source vertex does not exist, throw an illegal argument exception.
    			throw new IllegalArgumentException("Vertex " + source + " doesn't exist");
    		}else if(!hashGraph.containsKey(destination)){
    			// If the detination vertex does not exist, throw an illegal argument exception.
    			throw new IllegalArgumentException("Vertex " + destination + " doesn't exist");
    		}else{
    			// Adds all destinations from a source vertex to the source's array list
    			// in the hash map.
    			List<Vertex> adjacentList = hashGraph.get(source);
    			adjacentList.add(destination);
    			hashGraph.put(source,adjacentList);
    		}
    	}
    	
    }

    /** 
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {

	// YOUR CODE HERE
    	return hashGraph.keySet();
    }

    /** 
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {

	// YOUR CODE HERE
    	return graphEdges;
    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {

	// YOUR CODE HERE
    	if(!hashGraph.containsKey(v)){
    		// If the vertex being searched for doesn't exist, throw an illegal 
    		// argument exception.
    		throw new IllegalArgumentException("Vertex " + v + " does not exist");
    	}else if(hashGraph.get(v) != null){
    		// Returns the adjacent vertices for a given vertex.
    		return hashGraph.get(v);
    	}
    	return new ArrayList<Vertex>();
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph, 
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int edgeCost(Vertex a, Vertex b) {

	// YOUR CODE HERE
    	if(!hashGraph.containsKey(a)){
    		// If the given source vertex does not exist, throw an illegal argument exception.
    		throw new IllegalArgumentException("Vertex " + a + " does not exist");
    	}else if(!hashGraph.containsKey(b)){
    		// If the given destination vertex does not exist, throw an illegal argument exception.
    		throw new IllegalArgumentException("Vertex " + b + " does not exist");
    	}
    	for(Edge edgeEle : graphEdges){
    		Vertex source = edgeEle.getSource();
    		Vertex destination = edgeEle.getDestination();
    		if(source.getLabel().equals(a.getLabel()) && 
    				destination.getLabel().equals(b.getLabel())){
    			// If the source and destination vertices of the edge are equal to
    			// what is being searched for return the edge's weight.
    			return edgeEle.getWeight();
    		}
    	}
    	
		return -1;
    }

}
