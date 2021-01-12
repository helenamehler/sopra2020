package solutions.exercise4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;

import solutions.exercise3.ResidualGraphImpl;

/**
 * This class implements the Ford-Fulkerson-Algorithm with a generic type V
 * 
 * @author G03T03
 */
public class FordFulkersonImpl<V> implements FordFulkerson<V>, ExerciseSubmission {
	Deque<V> waitingLine = new ArrayDeque<V>();
	Map<V, V> relation = new HashMap<V, V>(); // Map with key = child, value = parent
	Deque<V> finished = new ArrayDeque<V>();
	boolean found = false;
	int minimumCapacity;

	/**
	 * Finds the minimum capacity of the given residual path. Adds this minimum
	 * capacity to flow of each residual edges of the path.
	 * 
	 * @param path a double ended queue with residual edges. The found minimum
	 *             capacity is added to these edges as flow
	 * @throws IllegalArgumentException if path is null
	 *
	 */
	public void augmentPath(Deque<ResidualEdge<V>> path) throws IllegalArgumentException {
		if (path == null) {
			throw new IllegalArgumentException("Path is null.");
		}
		minimumCapacity = path.getFirst().getCapacity();
		for (ResidualEdge<V> elem : path) {
			if (elem.getCapacity() < minimumCapacity) {
				minimumCapacity = elem.getCapacity();
			}
		}
		for (ResidualEdge<V> elem : path) {
			elem.addFlow(minimumCapacity);
		}
	}

	/**
	 * finds the shortest path with remaining capacity using Breadth-First-Search
	 * (BFS) on a residual graph. this method uses search(...) to use BFS. the
	 * accessibility tree it represented by a hashmap. the values are the parent
	 * nodes and the keys the children.
	 * 
	 * @param start start node of the wanted path
	 * @param end   end node of the wanted path
	 * @param graph residual graph to search in
	 * @return path (represented by a double ended queue) from end to start or null
	 *         if there is no path with remaining capacity
	 * @throws IllegalArgumentException if any parameter is null
	 */
	public Deque<ResidualEdge<V>> findPath(V start, V end, ResidualGraph<V> graph) throws IllegalArgumentException {
		Deque<ResidualEdge<V>> way = new ArrayDeque<ResidualEdge<V>>();

		if (start == null || end == null || graph == null) {
			throw new IllegalArgumentException("Either start or end node or graph is null.");
		}
		waitingLine.add(start);
		relation.put(start, null);

		List<ResidualEdge<V>> children = graph.edgesFrom(start);

		for (ResidualEdge<V> edge : children) {
			if (!finished.contains(edge.getEnd()) && !waitingLine.contains(edge.getEnd()) && edge.getCapacity() != 0) {
				waitingLine.add(edge.getEnd());
				relation.put(edge.getEnd(), start);

			}
		}
		finished.add(start);
		waitingLine.remove();
		while (!waitingLine.isEmpty() && !found) {
			search(graph, waitingLine.getFirst(), end);
			waitingLine.remove();
		}

		V value = relation.get(end);
		if (value != null) {
			way.add(graph.getEdge(value, end));
		} else {
			return null;
		}
		while (relation.get(value) != null) {
			way.add(graph.getEdge(relation.get(value), value));
			value = relation.get(value);
		}
		for (ResidualEdge<V> elem : way) {
			if (elem.getCapacity() == 0) {
				return null;
			}
		}

		if (way.getFirst().getEnd() == end && way.getLast().getStart() == start) {
			waitingLine.clear();
			finished.clear();
			relation.clear();
			found = false;
			return way;

		}
		return null;
	}

	/**
	 * implements the BFS by retrieving all edges from the given node and their end
	 * nodes. It adds the end nodes, if not already used, to the waiting line and as
	 * a key in the hashmap with the node as parent
	 * 
	 * @param graph residual graph to search in
	 * @param node  node which edges will be retrieved
	 * @param end   end node of the wanted path. if node has an edge with the target
	 *              node, search will be stopped
	 */
	public void search(ResidualGraph<V> graph, V node, V target) {

		List<ResidualEdge<V>> children = graph.edgesFrom(node);
		for (ResidualEdge<V> edge : children) {
			if (!finished.contains(edge.getEnd()) && !waitingLine.contains(edge.getEnd()) && edge.getCapacity() != 0) {
				waitingLine.add(edge.getEnd());

				relation.put(edge.getEnd(), node);
				if (edge.getEnd() == target) {
					found = true;
					return;
				}
			}
		}
		finished.add(node);

	}

	/**
	 * Given a flow network, a start node and a target node, the maximum flow is
	 * calculated from start to target in this given network.
	 * 
	 * @param graph  a flow graph. the flows along the edges will be set to max flow
	 * @param start  start node
	 * @param target end node
	 * @throws IllegalArgumentException if any parameter is null
	 * @throws NoSuchElementException   if start or target are not in the graph
	 */
	public void findMaxFlow(FlowGraph<V> graph, V start, V target)
			throws IllegalArgumentException, NoSuchElementException {
		if (start == null || target == null || graph == null) {
			throw new IllegalArgumentException("Either start or target node or graph is null.");
		}
		if (graph.containsNode(start) == false || graph.containsNode(target) == false) {
			throw new NoSuchElementException("Start or Target not are not in Graph.");
		}
		ResidualGraph<V> res = new ResidualGraphImpl<V>(graph);
		Deque<ResidualEdge<V>> way = findPath(start, target, res);
		while (way != null) {

			augmentPath(way);
			for (ResidualEdge<V> edge : way) {
				FlowEdge<V> flowEdge = graph.getEdge(edge.getStart(), edge.getEnd());
				if (flowEdge != null) {
					flowEdge.setFlow(minimumCapacity + flowEdge.getFlow());
				}
			}
			way = findPath(start, target, res);
		}

	}
	/**
	 * Returns Team Identifier
	 * 
	 * @return String with team identification
	 */
	public String getTeamIdentifier() {
		return "G03T03";
	}

}
