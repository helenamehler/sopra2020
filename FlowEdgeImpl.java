package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;

public class FlowEdgeImpl<V> extends Edge<V> implements FlowEdge<V>, ExerciseSubmission {

	private int flow;

	public FlowEdgeImpl(V start, V end, int capacity) {
		flow = 0;
		this.start = start;
		this.end = end;
		this.capacity = capacity;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) throws IllegalArgumentException {
		if (flow < 0) {
			throw new IllegalArgumentException("Flow is not allowed to be smaller than zero.");
		}
		this.flow = flow;

	}
	/**
	 * Returns Team Identifier
	 * @return String with team identifier
	 */
	public String getTeamIdentifier() {
		return "G03T03";
	}
}
