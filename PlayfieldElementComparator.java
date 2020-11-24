package solutions.exercise2;

import java.util.Comparator;
import org.sopra.api.Scenario;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.producer.ProducerType;
import org.sopra.api.ConstructionCostCalculatorImpl;
import org.sopra.api.exercises.ExerciseSubmission;


/*
 * This is a class to compare different PlayfieldElements based on the ConstructionCosts for a specific ProducerType.
 * @author G03T03 
 * @version 1.0
 * 
 */
public class PlayfieldElementComparator implements Comparator<PlayfieldElement>, ExerciseSubmission {
	
	private ConstructionCostCalculatorImpl constrCostCalci;
	private ProducerType producerType;
	
	/*
	 * This is the constructor of a PlayFieldComparator which creates a new instance of the class ConstructionCostCalculatorImpl.
	 * Object variables producerType and constructionCostCalculatorImpl are instantiated.
	 * 
	 * @param producertype type of Producer which will be compared
	 * @param scenario data type the game is played on
	 * @exception NullPointerException if either the ProducerType or the Scenario is null.
	 */
	public PlayfieldElementComparator(ProducerType producertype, Scenario scenario) {
		if (producertype == null || scenario == null) {
			throw new NullPointerException("ProducerType or Scenario is not allowed to be null.");
			
		} else {
		ConstructionCostCalculatorImpl constrCostCalci = new ConstructionCostCalculatorImpl(scenario);
		
		this.producerType = producertype;
		this.constrCostCalci = constrCostCalci;
	    }
	}
	
	
	/*
	 * Method to compare two PlayfieldElements with each other to determine which of them is the better option for building.
	 * 
	 * @param e1 first PlayfieldElement to be compared
	 * @param e2 second PlayfieldElement to be compared
	 * @return A negative integer value if the costs of PlayfieldElement e2 are less then the costs of PlayfieldElement e1,
	 *         zero if the costs of PlayfieldElement e2 and e1 are even
	 *         and a positive integer value if the costs of PlayfieldElement e2 are more then the costs of PlayfieldElement e1.
	 * 
	 * @exception NullPointerException if one of the PlayfieldElements is null.
	 */
	public int compare(PlayfieldElement e1, PlayfieldElement e2) {
		if (e1 == null || e2 == null) {
			throw new NullPointerException("PlayfieldElement e1 or e2 are not allowed to be null.");
		} else {
		return ((int) constrCostCalci.calculateCost(e2, producerType) - (int) constrCostCalci.calculateCost(e1, producerType));
		}
    }

	
	/*
	 * Method to get the TeamIdentifier.
	 * 
	 * @return String with team identification
	 */
	public String getTeamIdentifier() {
		return "G03T03";
	}

}
