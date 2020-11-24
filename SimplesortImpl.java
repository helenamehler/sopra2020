package solutions.exercise2;

import java.util.Comparator;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.Simplesort;


/*
 * This is a class to sort a given array based on a given comparator using a simplesort algorithm.
 * 
 * @author G03T03 
 * @version 1.0
 */
public class SimplesortImpl<T> implements Simplesort<T>, ExerciseSubmission {
	
	private Comparator<T> comparator;
	
	/*
	 * Constructor of the class SimplesortImpl which implements the comparator. 
	 * 
	 * @param comparator compares two objects of the same type T
	 * @Exception IllegalArgumentException if the comparator is null.
	 */
	public SimplesortImpl(Comparator<T> comparator) {
		if (comparator == null) {
			throw new IllegalArgumentException("Comparator is not allowed to be null.");
		} else {
		this.comparator = comparator;
		}
	}

	
	/*
	 * Method to sort a given array with the usage of the comparator and the simplesort algorithm.
	 * 
	 * @param arr given array of generic type T
	 * @param left index used to describe the left border
	 * @param right index used to describe the right border
	 * @Exception IllegalArgumentException if arr is null or if the chosen borders for left or right are invalid. 
	 */
	public void simplesort(T[] arr, int left, int right) {
		if (arr == null || left < 0 || right >= arr.length || right < left) {
			throw new IllegalArgumentException("Either arr is null or borders are invalid.");
		} else {
			for (int i = left; i <= right; i++) {
				for (int j = i + 1; j <= right; j++) {
					if (comparator.compare(arr[j], arr[i]) < 0) {
						T sorted = arr[i];
						arr[i] = arr[j];
						arr[j] = sorted;
					}
				}
		    } 
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


