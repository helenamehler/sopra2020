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
 * This class describes a flow graph through a hashmap. The keys of the hashmap
 * are start nodes and the values are new hashmaps. The keys of the inner
 * hashmap are the end nodes, the values are the capacity of the edges
 * 
 * @author G03T03
 */

public class FlowGraphImpl<V> implements FlowGraph<V>, ExerciseSubmission {

	private Map<V, Map<V, FlowEdge<V>>> flowGraph;

	/**
	 * Constructor of FlowGraphImpl which instantiate outer HashMap
	 */
	public FlowGraphImpl() {
		flowGraph = new HashMap<V, Map<V, FlowEdge<V>>>();
	}

	/**
	 * Adds new node to flow graph, returns true if successful. If node already
	 * exists or if it is null, returns false
	 * 
	 * @param node Node to add
	 * @return true if the node was successfully added, false otherwise.
	 */
	public boolean addNode(V node) {
		if (node == null || flowGraph.containsKey(node)) {
			return false;
		}
		HashMap<V, FlowEdge<V>> innerFlowGraph = new HashMap<V, FlowEdge<V>>();
		flowGraph.put(node, innerFlowGraph);
		return true;

	}

	/**
	 * Adds new flow edge with flow = 0. If start or destination node does not exist
	 * in graph, throws a NoSuchElementException. Returns the existing edge if edge
	 * already exists.
	 * 
	 * @param start    start node of edge
	 * @param end      end node of edge
	 * @param capacity capacity of edge
	 * @return created flow edge between start and destination node or existing edge
	 * @exception NoSuchElementException if start or end node does not exist in
	 *                                   graph
	 */

	public FlowEdge<V> addEdge(V start, V end, int capacity) throws NoSuchElementException {
		if (!flowGraph.containsKey(start) || !flowGraph.containsKey(end)) {
			throw new NoSuchElementException("Start or End Node do not exist in Graph.");
		}
		if (getEdge(start, end) != null) {
			return getEdge(start, end);
		}
		FlowEdge<V> newEdge = new FlowEdgeImpl<V>(start, end, capacity);
		Map<V, FlowEdge<V>> innerFlowGraph = flowGraph.get(start);
		innerFlowGraph.put(end, newEdge);
		flowGraph.put(start, innerFlowGraph);
		return newEdge;
	}

	/**
	 * Returns true if the given node is contained in graph. Else returns false
	 * 
	 * @param node node to be tested if contained in graph
	 * @return true if node exists in the graph otherwise false
	 */
	public boolean containsNode(V node) {
		if (flowGraph.containsKey(node)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns every edge from given node. If node is not in the graph, throws a
	 * NoSuchElementException.
	 * 
	 * @param node node whose edges should be retrieved
	 * @return A collection of all flow edges from the node
	 * @exception NoSuchElementException if node is null or not in the graph
	 */
	public Collection<FlowEdge<V>> edgesFrom(V node) throws NoSuchElementException {
		if (node == null) {
			throw new NoSuchElementException("Node is not allowed to be null.");
		}
		if (!containsNode(node)) {
			throw new NoSuchElementException("Node is not in the graph.");
		}
		Map<V, FlowEdge<V>> innerMap = flowGraph.get(node);
		return innerMap.values();
	}

	/**
	 * Returns a flow edge which goes from a given start to a given end. Returns
	 * null if a flow edge between those given nodes does not exist or if at least
	 * one parameter is null
	 * 
	 * @param start start of this edge
	 * @param end   end of this edge
	 * @return the flow edge between those nodes
	 */
	public FlowEdge<V> getEdge(V start, V end) {
		if (start == null || end == null) {
			return null;
		}
		if(!flowGraph.containsKey(start)) {
			return null;
		}
		Map<V, FlowEdge<V>> innerMap = flowGraph.get(start);
		if(!innerMap.containsKey(end)) {
			return null;
		}
		return innerMap.get(end);
	}

	/**
	 * Returns list of all edges from graph.
	 * 
	 * @return a list of all flow edges
	 */
	public List<FlowEdge<V>> getEdges() {
		List<FlowEdge<V>> list = new ArrayList<FlowEdge<V>>();
		for (V elem : flowGraph.keySet()) {
			Map<V, FlowEdge<V>> innerMap = flowGraph.get(elem);
			list.addAll(innerMap.values());
		}
		return list;

	}

	/**
	 * Returns a set of all nodes from graph.
	 * 
	 * @return a set of all nodes
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
