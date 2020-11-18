package solutions.exercise2;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.AbstractTestRunTimeSorting;
import com.google.common.base.Stopwatch;

public class TestRunTimeSorting extends AbstractTestRunTimeSorting implements ExerciseSubmission{

	private Integer[] sortedArray;
	private Integer[] shuffledArray;
	
	private Simplesort<Integer> ssi;
	private Quicksort<Integer> qsi;
	private Stopwatch stopwatch;
	
	@Before
	public void setup() {
		sortedArray = createOrderedIntArray(5);
		shuffledArray = createShuffledIntArray(5);
		ssi = new SimplesortImpl<Integer>(comparator);
		qsi = new QuicksortImpl<Integer>(comparator);
		stopwatch = Stopwatch.createUnstarted();
	}
	
	@Test
	public void quicksortRunTimeTest() {
		stopwatch.start();
		qsi.quicksort(shuffledArray, 0, shuffledArray.length -1);
		stopwatch.stop();
		long timeElapsed = stopwatch.elapsed(TimeUnit.MICROSECONDS);
		System.out.println("Time elapsed for sorting shuffled array with Quicksort = " + timeElapsed + " microseconds.");
		
		assertArrayEquals(sortedArray, shuffledArray);
	}



	@Test
	public void simplesortRunTimeTest() {
		stopwatch.start();
		ssi.simplesort(shuffledArray, 0, shuffledArray.length -1);
		stopwatch.stop();
		long timeElapsed = stopwatch.elapsed(TimeUnit.MICROSECONDS);
		System.out.println("Time elapsed for sorting shuffled array with Simplesort = " + timeElapsed + " microseconds.");
		
		assertArrayEquals(sortedArray, shuffledArray);
		
	}

	public String getTeamIdentifier() {
		return "G03T03";
	}

}
