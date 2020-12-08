package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;

/**
 * This class describes an edge of a flow graph
 * 
 * @author G03T03
 *
 */

public class FlowEdgeImpl<V> extends Edge<V> implements FlowEdge<V>, ExerciseSubmission {

	private int flow;

	/**
	 * Constructor which sets start + end node, capacity of the edge and sets flow to zero
	 * 
	 * @param startNode start node of the edge
	 * @param endNode end node of the edge
	 * @param capacity capacity of the edge
	 */
	public FlowEdgeImpl(V startNode, V endNode, int capacity) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.capacity = capacity;
		flow = 0;
	}
	/**
	 * returns flow 
	 * @return flow of the edge
	 */

	public int getFlow() {
		return flow;
	}
	/**
	 * sets new flow value
	 * @param flow the value of the new flow
	 * @throws IllegalArgumentException if flow is smaller than zero
	 */

	public void setFlow(int flow) throws IllegalArgumentException {
		if (flow < 0) {
			throw new IllegalArgumentException("Flow is not allowed to be smaller than zero.");
		}
		this.flow = flow;

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
