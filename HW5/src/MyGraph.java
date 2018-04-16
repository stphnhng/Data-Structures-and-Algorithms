import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

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
	private Collection<Edge> graphEdges;
	
    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {

		// YOUR CODE HERE
    	
    	hashGraph = new HashMap();
    	graphEdges = new ArrayList<Edge>();
    	
    	for(Edge edEle : e){
    		if(!graphEdges.contains(edEle)){
    			graphEdges.add(edEle);
    		}
    	}
    	
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
    			// If the destination vertex does not exist, throw an illegal argument exception.
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

    /**
     * Returns the shortest path from a to b in the graph, or null if there is
     * no such path.  Assumes all edge weights are nonnegative.
     * Uses Dijkstra's algorithm.
     * @param a the starting vertex
     * @param b the destination vertex
     * @return a Path where the vertices indicate the path from a to b in order
     *   and contains a (first) and b (last) and the cost is the cost of 
     *   the path. Returns null if b is not reachable from a.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    public Path shortestPath(Vertex a, Vertex b) {
    	Path returnPath = null;
	// YOUR CODE HERE 
    	if(!hashGraph.containsKey(a)){
    		// If the given source vertex does not exist, throw an illegal argument exception.
    		throw new IllegalArgumentException("Vertex " + a + " does not exist");
    	}else if(!hashGraph.containsKey(b)){
    		// If the given destination vertex does not exist, throw an illegal argument exception.
    		throw new IllegalArgumentException("Vertex " + b + " does not exist");
    	}
    	if(a.getLabel().equals(b.getLabel())){
    		// If the start and end vertexes are equal to each other, 
    		// return one vertex and a cost of 0.
    		List<Vertex> returnList = new ArrayList<Vertex>();
    		returnList.add(a);
    		return new Path(returnList,0);
    	}
		// Sets up the table so that all vertices have infinite cost 
		// except for the source, no paths, and are all unknown.
		Map<Vertex, Integer> vertexCost = new HashMap<Vertex, Integer>();
		Map<Vertex, Boolean> vertexKnown = new HashMap<Vertex, Boolean>();
		Map<Vertex, Vertex> vertexPath = new HashMap<Vertex, Vertex>();
		Queue<Integer> minCost = new PriorityQueue<Integer>();
		for(Vertex vEle : hashGraph.keySet()){
			vertexKnown.put(vEle,false);
			vertexPath.put(vEle, null);
			// All nodes are not known and their paths are null.
			if(a.equals(vEle)){
				// If this is the source node, set cost to 0.
				vertexCost.put(vEle, 0);
			}else{
				// If this is any other node but source, the cost is 
				// close to infinity.
				vertexCost.put(vEle, Integer.MAX_VALUE);
			}
		}
		
		
		while(vertexKnown.containsValue(false)){
			// Gets all known + cost + path values in dijkstra's algorithm
			minCost = new PriorityQueue<Integer>();
			for(Vertex vCostEle : vertexCost.keySet()){
				// if the vertex is unknown, add all min values to min heap
				// this is to update the min heap every time dijkstra runs.
				if(!vertexKnown.get(vCostEle)){
    				minCost.add(vertexCost.get(vCostEle));
				}
			}
			int lowestCost = minCost.remove();
			// current lowest cost edge
			
			Vertex minVertex = null;
			for(Vertex ele : vertexCost.keySet()){
				// Finds the vertex associated with the minimum cost.
				if(vertexCost.get(ele) == lowestCost && !vertexKnown.get(ele)){
					minVertex = ele;
				}
			}
			
			vertexKnown.put(minVertex, true);
			// Make the current lowest cost vertex known
			Collection<Vertex> adjVertices = adjacentVertices(minVertex);
			for(Vertex verEle : adjVertices){
				if(vertexKnown.get(verEle) == false){
					// Checks the adjacent vertices that are unknown.
    				int sourceCost = vertexCost.get(minVertex) + 
    						edgeCost(minVertex,verEle);
    				// The total cost from source to adjacent vertex.
    				if(vertexCost.get(minVertex) == Integer.MAX_VALUE){
    					// adding edgeCost to Integer.MAX_VALUE overflows
    					// and sets it to negative infinity, thus reset to
    					// Integer.MAX_VALUE
    					sourceCost = Integer.MAX_VALUE;
    				}
    				int destCost = vertexCost.get(verEle);
    				if(sourceCost < destCost){
    					// If the cost from the source to the adjacent vertex
    					// is less than the current cost of the adjacent vertex,
    					// set adjacent vertex's cost to the source cost
    					// and the path to the source vertex.
    					vertexCost.put(verEle, sourceCost);
    					vertexPath.put(verEle, minVertex);
    				}
				}
			}
		}
		Vertex verIter = b;
		// Vertex to iterate through the path backwards from 
		// destination to source by following the path value.
		Stack<Vertex> shortPath = new Stack<Vertex>();
		while(!verIter.equals(a) && verIter != null &&
				vertexPath.get(verIter) != null){
			// While the iterator hasn't reached the source
			// and the current and next vertex aren't null.
			shortPath.add(verIter);
			// Adds the vertices of the path to a stack
			// since this is traversing backwards.
			verIter = vertexPath.get(verIter);
		}
		if(verIter.equals(a)){
			// When the source vertex has been found.
			shortPath.add(a);
			List<Vertex> shortPathOrder = new ArrayList<Vertex>();
			while(!shortPath.isEmpty()){
				// Pop all vertices from the stack to an array list.
				Vertex vEle = shortPath.pop();
				shortPathOrder.add(vEle);
			}
			if(shortPathOrder.size() != 0){
				// If there are no vertices in the path, only include
				// the source + destination.
	    		returnPath = new Path(shortPathOrder, vertexCost.get(b));
			}
		}else{
			// Returns null when the source + destination could not be linked.
			return null;
		}
    	return returnPath;
    }
    
    
}
