package solutions.exercise2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.AbstractQuicksortTest;

public class QuicksortTest extends AbstractQuicksortTest implements ExerciseSubmission {

	Integer[] arrayEmpty	= {};
	Integer[] arrayOne 	 	= {1};
	Integer[] arrayTwo   	= {2,4,3,5,1};
	Integer[] partitionOne	= {2,1,3,5,4};
	Integer[] arrayTwoSol	= {1,2,3,4,5};
	Integer[] arraySmallSol	= {3,4,5};
	Integer[] testArray;
	
	@Override
	public String getTeamIdentifier() {
		return "G03T03";
	}

	@Override
	@Test
	public void testPartition() {
		testArray = arrayOne;
		//basic test if it doesn't have to change anything
		sut.partition(testArray, 0, 0);
		assertArrayEquals(testArray, arrayOne);
		
		//tests if it sorts like it should and if the methods finds the right pivot index if left + right is even
		testArray = arrayTwo;
		assertEquals(sut.partition(testArray, 0, 4), 3);
		assertArrayEquals(testArray, partitionOne);
		
		//tests if partition finds the right pivot index if left + right is an uneven number
		testArray = Arrays.copyOfRange(arrayTwo, 0, 4);
		assertEquals(sut.partition(testArray, 0, 3), 1);
		
		//tests if it finds the right pivot element, of a part of the array
		assertEquals(sut.partition(testArray, 2, 3), 3); //why is (int) 2.5 = 2 ?
	}

	@Override
	@Test
	public void testPartition_Parameters() {
		try {
			sut.partition(null, 0, 0);
			fail();
		} catch(IllegalArgumentException e) {
			//expected
		}
		
		try {
			sut.partition(arrayEmpty, 0, 0);
			fail();
		} catch(IllegalArgumentException e) {
			//expected
		}
		
		try {
			sut.partition(arrayOne, -1, 0);
			fail();
		} catch(IllegalArgumentException e) {
			//expected
		}
		
		try {
			sut.partition(arrayOne, 0, -1);
			fail();
		} catch(IllegalArgumentException e) {
			//expected
		}
		
		try {
			sut.partition(arrayOne, 0, 1);
			fail();
		} catch(IllegalArgumentException e) {
			//expected
		}
		
		try {
			sut.partition(arrayOne, 1, 0);
			fail();
		} catch(IllegalArgumentException e) {
			//expected
		}
		
		try {
			sut.partition(arrayTwo, 2, 1);
			fail();
		} catch(IllegalArgumentException e) {
			//expected
		}
	}

	@Override
	@Test
	public void testQuicksort() {
		//basic Test
		testArray = arrayOne;
		sut.quicksort(testArray, 0, 0);
		assertArrayEquals(testArray, arrayOne);
		
		//small test
		testArray = Arrays.copyOfRange(arrayTwo, 1, 4);
		sut.quicksort(testArray, 0, 2);
		assertArrayEquals(testArray, arraySmallSol);
		
		//normal test
		sut.quicksort(arrayTwo, 0, 4);
		assertArrayEquals(arrayTwo, arrayTwoSol);
		
	}

	@Override
	@Test
	public void testQuicksort_Parameters() {
		try {
			sut.quicksort(null, 0, 0);
			fail();
		} catch(IllegalArgumentException e) {
			//expected
		}
		try {
			sut.quicksort(arrayTwo, 2, 1);
			fail();
		} catch(IllegalArgumentException e) {
			//expected
		}
	}

}
