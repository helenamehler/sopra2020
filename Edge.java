package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.CapacityEdge;

/**
 * This is an abstract class to describe an edge of a weighted graph
 * 
 * @author G03T03
 *
 */
abstract class Edge<V> implements CapacityEdge<V>, ExerciseSubmission {

	protected int capacity;
	protected V startNode;
	protected V endNode;

	/**
	 * Returns end node
	 * 
	 * @return end node of edge
	 */
	public V getEnd() {
		return endNode;
	}

	/**
	 * Returns start node
	 * 
	 * @return start node of edge
	 */

	public V getStart() {
		return startNode;
	}

	/**
	 * Returns capacity
	 * 
	 * @return capacity of edge
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * Sets capacity
	 * @param capacity the integer value of the new capacity
	 * @throws IllegalArgumentException if capacity is smaller than zero
	 */

	public void setCapacity(int capacity) throws IllegalArgumentException {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity is not allowed to be smaller than zero.");
		}
		this.capacity = capacity;
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
