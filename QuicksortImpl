package solutions.exercise2;

import java.util.Comparator;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.Quicksort;

/**
 * This is a class to sort a given array based on a given comparator using a quicksort algorithm.
 * 
 * @author G03T03 
 */

public class QuicksortImpl<T> implements Quicksort<T>, ExerciseSubmission {

	private Comparator<T> comparator;

	/**
	 * Constructor of the class QuicksortImpl which implements the comparator. 
	 * 
	 * @param comparator compares two objects of the same type T
	 * @Exception IllegalArgumentException if the comparator is null.
	 */
	
	public QuicksortImpl(Comparator<T> comparator) throws IllegalArgumentException {
		if (comparator == null) {
			throw new IllegalArgumentException("Comparator is not allowed to be null.");
		}
		this.comparator = comparator;
	}

	/**
	 * The method partition parts the array so the pivot element stands on it's right position. The smaller elements 
	 * are left of the pivot element and the bigger elements are on the right of the pivot element.
	 * 
	 * @param arr given array of generic type T
	 * @param left index used to describe the left border
	 * @param right index used to describe the right border
	 * @Exception IllegalArgumentException if arr is null or if the chosen borders for left or right are invalid. 
	*/
	
	@Override
	public int partition(T[] arr, int left, int right) {
		if (arr == null || left < 0 || right >= arr.length || left > right) {
			throw new IllegalArgumentException("Arr is null or borders are invalid.");
		}
		
		int lp = left;
		int rp = right;
		int pivotposition = (int) (left + right) / 2;
		T pivot = arr[pivotposition];
		while (lp <= rp) {
			while (comparator.compare(arr[lp], pivot) < 0) {
				lp++;

			}
			while (comparator.compare(arr[rp], pivot) > 0) { 
				rp--;

			}
			if (lp <= rp) {
				T help = arr[lp];
				arr[lp] = arr[rp];
				arr[rp] = help;
				lp++;
				rp--;
			}
		}
		return lp;
	}

	/**
	 * Method to sort a given array with the usage of the comparator and the quicksort algorithm.
	 * 
	 * @param arr given array of generic type T
	 * @param left index used to describe the left border
	 * @param right index used to describe the right border
	 * @Exception IllegalArgumentException if arr is null. 
	 */
	
	@Override
	public void quicksort(T[] arr, int left, int right) {
		if (arr == null) {
			throw new IllegalArgumentException("Arr is not allowed to be null");
		}
		if (left < right) {
			int partitioning = partition(arr, left, right);
			quicksort(arr, left, partitioning - 1);
			quicksort(arr, partitioning, right);
		}
	}

	/**
	 * Returns Team Identifier
	 *
	 * @return String with team identifier
	 */
	@Override
	public String getTeamIdentifier() {
		return "G03T03";
	}

}
