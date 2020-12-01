package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.CapacityEdge;

abstract class Edge<V> implements CapacityEdge<V>, ExerciseSubmission {

	protected int capacity;
	protected V start;
	protected V end;

	public V getEnd() {
		return end;
	}

	public V getStart() {
		return start;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) throws IllegalArgumentException {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity is not allowed to be smaller than zero.");
		}
		this.capacity = capacity;
	}



	/**
	 * Returns Team Identifier
	 * @return String with team identifier
	 */

	public String getTeamIdentifier() {
		return "G03T03";
	}
}
