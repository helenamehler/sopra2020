package solutions.exercise3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.HashMap;

/**
 * 
 * @author G03T03
 */

public class FlowGraphImpl<V> implements FlowGraph<V>, ExerciseSubmission {

	private Map<V, Map<V, FlowEdge<V>>> flowGraph;
	//private HashMap<V, FlowEdge<V>> innerFlowGraph;

	/**
	 * Constructor of FlowGraphImpl which implements outer and inner HashMap
	 */
	public FlowGraphImpl() {
		flowGraph = new HashMap<V, Map<V, FlowEdge<V>>>();
	//	innerFlowGraph = new HashMap<V, FlowEdge<V>>();
	}

	/**
	 * Adds new node to flow graph if node does not already exist. If node is added
	 * returns true, else returns false.
	 * 
	 * @param node Node to add
	 * @return true if the node was successfully added, false otherwise.
	 */
	public boolean addNode(V node) {
		if (node == null || flowGraph.containsKey(node)) { 
			return false;
		}
		HashMap<V, FlowEdge<V>> innerFlowGraph= new HashMap<V, FlowEdge<V>>();
		flowGraph.put(node, innerFlowGraph); //leere innereMap
		return true;

	}

	/**
	 * Adds a new flow edge to a start and a destination node with initial flow of
	 * 0. If either start or destination node does not exist in graph, throws an
	 * NoSuchElementException. Returns the existing edge if edge already exists.
	 * 
	 * @param start    Start node
	 * @param end      End node
	 * @param capacity Capacity of Edge
	 * @return Newly created flow edge between start and destination node
	 * @exception NoSuchElementException if either start or end node does not exist
	 *                                   in graph
	 */

	public FlowEdge<V> addEdge(V start, V end, int capacity) throws NoSuchElementException {
		if (!flowGraph.containsKey(start) || !flowGraph.containsKey(end)) {
			throw new NoSuchElementException("Start or End Node do not exist in Graph.");
		}
		FlowEdge<V> newEdge = new FlowEdgeImpl<V>(start, end, capacity);
		Map<V, FlowEdge<V>> innerFlowGraph = flowGraph.get(start);
		innerFlowGraph.put(end, newEdge);
		flowGraph.put(start, innerFlowGraph);
		return newEdge;
	}

	/**
	 * Returns true if the given node is contained in the graph. Otherwise returns
	 * false
	 * 
	 * @param node Node to be tested if contained in graph
	 * @return true if node is contained in the graph otherwise false
	 */
	public boolean containsNode(V node) {
		if (flowGraph.containsKey(node)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns all edges from given node. If node is not given in the graph, throws
	 * a NoSuchElementException.
	 * 
	 * @param node Node whose edges should be retrieved
	 * @return A Collection of all flow edges leaving the node
	 * @exception NoSuchElementException if node is null
	 */
	public Collection<FlowEdge<V>> edgesFrom(V node) throws NoSuchElementException {
		if (node == null) {
			throw new NoSuchElementException("Node is not allowed to be null.");
		}
		Map<V, FlowEdge<V>> innerMap = flowGraph.get(node);
		return innerMap.values();
	}

	/**
	 * Returns a flow edge going from start to end. Returns null if flow edge is not
	 * present or at least one parameter is null.
	 * 
	 * @param start Start of this edge
	 * @param end   End of this edge
	 * @return A flow edge
	 */
	public FlowEdge<V> getEdge(V start, V end) {
		if (start == null || end == null) {
			return null;
		}
		Map<V, FlowEdge<V>> innerMap = flowGraph.get(start);
		return innerMap.get(end);
	}

	/**
	 * Returns list of all edges from graph.
	 * 
	 * @return A List of all flow edges
	 */
	public List<FlowEdge<V>> getEdges() {
		//Collection<FlowEdge<V>> test = innerFlowGraph.values();
		List<FlowEdge<V>> list = new ArrayList<FlowEdge<V>>();
		for( V elem : flowGraph.keySet()) {
			Map<V, FlowEdge<V>> innerMap = flowGraph.get(elem);
			list.addAll(innerMap.values());
		}
		return list;
	
	}

	/**
	 * Returns a set of all nodes from graph.
	 * 
	 * @return A Set of all Nodes
	 */
	public Set<V> getNodes() {
		return flowGraph.keySet();
	}

	/**
	 * Returns Team Identifier
	 * 
	 * @return String with team identifier
	 */
	public String getTeamIdentifier() {
		return "G03T03";
	}

}
