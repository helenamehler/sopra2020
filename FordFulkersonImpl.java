package solutions.exercise4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;

import solutions.exercise3.ResidualGraphImpl;

public class FordFulkersonImpl<V> implements FordFulkerson<V>, ExerciseSubmission {
	Deque<V> waitingLine = new ArrayDeque<V>();
	Map<V, V> relation = new HashMap<V, V>(); // Map with key = child, value = parent
	Deque<V> finished = new ArrayDeque<V>();
	boolean found = false;

	public void augmentPath(Deque<ResidualEdge<V>> path) throws IllegalArgumentException {
		if (path == null) {
			throw new IllegalArgumentException("Path is null.");
		}
		int minimumCapacity = path.getFirst().getCapacity();
		for (ResidualEdge<V> elem : path) {
			if (elem.getCapacity() < minimumCapacity) {
				minimumCapacity = elem.getCapacity();
			}
		}
		for (ResidualEdge<V> elem : path) {
			elem.addFlow(minimumCapacity);
		}
	}

	public Deque<ResidualEdge<V>> findPath(V start, V end, ResidualGraph<V> graph) throws IllegalArgumentException {
		Deque<ResidualEdge<V>> way = new ArrayDeque<ResidualEdge<V>>();

		if (start == null || end == null || graph == null) {
			throw new IllegalArgumentException("Either start or end node or graph is null.");
		}
		waitingLine.add(start); //kann das auch einfach mit pop() gleich entfernen - Felix
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
			waitingLine.remove();//node wurde schon aus der waiting line rausgeschmissen obwohl die node dort noch für die Überprüfung in search() gebraucht wurde, somit wurden a,a ... relations geschaffen, die eine loop erzeugt haben
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

	public void search(ResidualGraph<V> graph, V node, V end) {

		List<ResidualEdge<V>> children = graph.edgesFrom(node);
		for (ResidualEdge<V> edge : children) {
			if (!finished.contains(edge.getEnd()) && !waitingLine.contains(edge.getEnd()) && edge.getCapacity() != 0) {
				waitingLine.add(edge.getEnd());

				relation.put(edge.getEnd(), node);
				if (edge.getEnd() == end) {
					found = true;
					return;
				}
			}
		}
		finished.add(node);

	}

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
		int flow = 3;
		int help = 0;
		while(way != null) {
			
			augmentPath(way);
			way = findPath(start, target, res);
	}
		

	}

	public String getTeamIdentifier() {
		return "G03T03";
	}

}
