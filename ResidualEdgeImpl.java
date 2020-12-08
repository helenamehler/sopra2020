package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.ResidualEdge;

/**
 * This class describes an edge of a residual graph
 * 
 * @author G03T03
 * 
 */
public class ResidualEdgeImpl<V> extends Edge<V> implements ResidualEdge<V>, ExerciseSubmission {

	private ResidualEdge<V> reverseEdge;

	/**
	 * Constructor of ResidualEdgeImpl which sets start + end node and capacity of the edge
	 * 
	 * @param startNode start node of the edge
	 * @param endNode   end node of the edge
	 * @param capacity  capacity of the edge
	 */
	public ResidualEdgeImpl(V startNode, V endNode, int capacity) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.capacity = capacity;
	}

	/**
	 * Adds a positive/negative flow to an edge by decreasing/increasing capacity
	 * and increasing/decreasing capacity of reverse edge
	 * 
	 * @param amount the amount of flow to be added or subtracted
	 * @throws IllegalArgumentException if absolute of to be added flow is bigger
	 *                                  than capacity
	 */
	public void addFlow(int amount) {
		if (Math.abs(amount) > capacity) {
			throw new IllegalArgumentException("Amount is not allowed to be bigger than capacity");
		}
		capacity = capacity - amount;
		reverseEdge.setCapacity(reverseEdge.getCapacity() + amount);

	}

	/**
	 * Returns reverse edge
	 * 
	 * @return reverse edge of residual edge
	 */

	public ResidualEdge<V> getReverse() {
		return reverseEdge;
	}

	/**
	 * Sets reverse edge
	 * 
	 * @param reverse the new reverse edge
	 */
	public void setReverse(ResidualEdge<V> reverse) {
		reverseEdge = reverse;

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
