package solutions.exercise2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.AbstractQuicksortTest;

public class QuicksortTest extends AbstractQuicksortTest implements ExerciseSubmission {

	public String getTeamIdentifier() {
		return "G03T03";
	}

	Integer[] test1 = { 7, 6, 5, 4, 3, 2, 1 };
	Integer[] sort1 = { 1, 2, 3, 4, 5, 6, 7 };

	Integer[] test2 = { 5, 6, 3, 1, 9, 3, 12 };
	Integer[] part2 = { 5, 1, 3, 6, 9, 3, 12 };
	Integer[] sort2 = { 1, 3, 5, 6, 9, 3, 12 };

	Integer[] test3 = { 2, 101, 7, 25, 16, 33, 1 };
	Integer[] part3 = { 2, 7, 101, 25, 16, 33, 1 };
	Integer[] sort3 = { 2, 7, 25, 101, 16, 33, 1 };

	@Test
	public void testPartition() {
		assertEquals(sut.partition(test1, 0, 6), 4);
		assertArrayEquals(test1, sort1);

		assertEquals(sut.partition(test2, 0, 3), 3);
		assertArrayEquals(test2, part2);

		assertEquals(sut.partition(test3, 1, 3), 2);
		assertArrayEquals(test3, part3);

	}

	@Test
	public void testPartition_Parameters() {
		try {
			sut.quicksort(null, 0, 2);
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			sut.quicksort(test1, 1, 0);
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			sut.quicksort(test1, 1, 7);
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			sut.quicksort(test1, -1, 3);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testQuicksort() {

		sut.quicksort(test1, 0, 6);
		assertArrayEquals(test1, sort1);

		sut.quicksort(test2, 0, 3);
		assertArrayEquals(test2, sort2);

		sut.quicksort(test3, 1, 3);
		assertArrayEquals(test3, sort3);

	}

	@Test
	public void testQuicksort_Parameters() {
		try {
			sut.quicksort(null, 0, 2);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
}
