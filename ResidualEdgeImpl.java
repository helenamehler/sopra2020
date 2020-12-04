package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.ResidualEdge;

public class ResidualEdgeImpl<V> extends Edge<V> implements ResidualEdge<V>, ExerciseSubmission{

	private ResidualEdge<V> reverseEdge;
	
	public ResidualEdgeImpl(V startNode, V endNode, int capacity) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.capacity = capacity;
	}
	public void addFlow(int amount) {
		if(amount > capacity || amount < -capacity) {
			throw new IllegalArgumentException("Amount is not allowed to be bigger than capacity");
		}
		capacity = capacity - amount;
		reverseEdge.setCapacity(capacity + 2*amount); //Wieso???
		
	}

	public ResidualEdge<V> getReverse() {
		return reverseEdge;
	}

	public void setReverse(ResidualEdge<V> reverse) {
		reverseEdge = reverse;	
		
	}
	
	/**
	 * Returns Team Identifier
	 * @return String with team identifier
	 */
	public String getTeamIdentifier() {
		return "G03T03";
	}

}
