package solutions.exercise4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.AbstractFordFulkersonTest;

import solutions.exercise3.ResidualGraphImpl;

public class FordFulkersonTest extends AbstractFordFulkersonTest implements ExerciseSubmission {
	
	
	@Test
	public void test_findMaxFlow_ParameterStartIsNull() {
		try {
			sut.findMaxFlow(flowGraph1, null, "t");
			fail("Expected an exception of type IllegalArgumentException.");
		} catch (IllegalArgumentException e) {

		} catch (NoSuchElementException e) {
			fail("Expected an exception of type IllegalArgumentException not NoSuchElementException.");
		}

	}
	
	
	@Test
	public void test_augmentPath1() {
		ResidualGraph<String> residualGraph1 = new ResidualGraphImpl<String>(flowGraph1);
		Deque<ResidualEdge<String>> path = new ArrayDeque<ResidualEdge<String>>();
		path.add(residualGraph1.getEdge("s", "b"));
		path.addFirst(residualGraph1.getEdge("b", "a"));
		path.addFirst(residualGraph1.getEdge("a", "d"));
		path.addFirst(residualGraph1.getEdge("d", "t"));

		sut.augmentPath(path);
		assertEquals(path.remove().getCapacity(), 3);
		assertEquals(path.remove().getCapacity(), 5);
		assertEquals(path.remove().getCapacity(), 0);
		assertEquals(path.remove().getCapacity(), 4);

	}

	@Test
	public void test_augmentPath2() {
		ResidualGraph<String> residualGraph1 = new ResidualGraphImpl<String>(flowGraph2);
		Deque<ResidualEdge<String>> path = new ArrayDeque<ResidualEdge<String>>();
		path.add(residualGraph1.getEdge("s", "a"));
		path.addFirst(residualGraph1.getEdge("a", "c"));
		path.addFirst(residualGraph1.getEdge("c", "b"));
		path.addFirst(residualGraph1.getEdge("b", "e"));
		path.addFirst(residualGraph1.getEdge("e", "t"));

		sut.augmentPath(path);
		assertEquals(path.remove().getCapacity(), 2);
		assertEquals(path.remove().getCapacity(), 4);
		assertEquals(path.remove().getCapacity(), 0);
		assertEquals(path.remove().getCapacity(), 2);
		assertEquals(path.remove().getCapacity(), 7);

	}

	@Test
	public void test_augmentPath_ParameterNull() {
		try {
			sut.augmentPath(null);
			fail("Expected an exception of type IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// Test successful
		}
	}

	@Test
	public void test_findMaxFlow_ParameterGraphIsNull() {
		try {
			sut.findMaxFlow(null, "s", "t");
			fail("Expected an exception of type IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// Test successful
		}
	}



	@Test
	public void test_findMaxFlow_ParameterTargetIsNull() {
		try {
			sut.findMaxFlow(flowGraph1, "s", null);
			fail("Expected an exception of type IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// Test successful
		} catch (NoSuchElementException e) {
			fail("Expected an exception of type IllegalArgumentException not NoSuchElementException.");
		}
	}

	@Test
	public void test_findMaxFlow_ParameterTargetNotInGraph() {
		try {
			sut.findMaxFlow(flowGraph1, "s", "x");
			fail("Expected an exception of type NoSuchElementException.");
		} catch (NoSuchElementException e) {
			// Test successful
		}
		catch (IllegalArgumentException e) {
			// Test successful
		}
	}

	@Test
	public void test_findMaxFlow_flowGraphA() {
		sut.findMaxFlow(flowGraphA, "s", "t");
		assertEquals(flowGraphA.getEdge("e", "t").getFlow() + flowGraphA.getEdge("d", "t").getFlow(), 17);
		assertEquals(flowGraphA.getEdge("s", "a").getFlow() + flowGraphA.getEdge("s", "b").getFlow(), 17);

	}

	@Test
	public void test_findMaxFlow_flowGraphB() {
		sut.findMaxFlow(flowGraphB, "s", "t");
		assertEquals(flowGraphB.getEdge("e", "t").getFlow() + flowGraphB.getEdge("d", "t").getFlow(), 24);
		assertEquals(flowGraphB.getEdge("s", "a").getFlow() + flowGraphB.getEdge("s", "b").getFlow(), 24);

	}

	@Test
	public void test_findMaxFlow_flowGraphC() {

		sut.findMaxFlow(flowGraphC, "s", "t");
		assertEquals(flowGraphC.getEdge("e", "t").getFlow() + flowGraphC.getEdge("d", "t").getFlow(), 10);
		assertEquals(flowGraphC.getEdge("s", "a").getFlow() + flowGraphC.getEdge("s", "b").getFlow(), 10);

	}

	@Test
	public void test_findPath1() {
		Deque<ResidualEdge<String>> path = sut.findPath("s", "t", new ResidualGraphImpl<String>(flowGraph1));
		assertEquals(path.size(), 3);
		assertEquals(path.getFirst().getStart(), "d");
		assertEquals(path.getFirst().getEnd(), "t");
		path.removeFirst();
		assertEquals(path.getFirst().getStart(), "a");
		assertEquals(path.getFirst().getEnd(), "d");
		path.removeFirst();
		assertEquals(path.getFirst().getStart(), "s");
		assertEquals(path.getFirst().getEnd(), "a");

	}

	@Test
	public void test_findPath2() {
		Deque<ResidualEdge<String>> path = sut.findPath("s", "t", new ResidualGraphImpl<String>(flowGraph2));
		assertEquals(path.size(), 3);
		assertEquals(path.getFirst().getStart(), "e");
		assertEquals(path.getFirst().getEnd(), "t");
		path.removeFirst();
		assertEquals(path.getFirst().getStart(), "b");
		assertEquals(path.getFirst().getEnd(), "e");
		path.removeFirst();
		assertEquals(path.getFirst().getStart(), "s");
		assertEquals(path.getFirst().getEnd(), "b");

	}

	@Test
	public void test_findPath_IsNull() {

		assertNull(sut.findPath("s", "t", new ResidualGraphImpl<String>(flowGraph3)));
	}

	@Test
	public void test_findPath_ParameterGraphIsNull() {
		try {
			sut.findPath("s", "t", null);
			fail("Expected an exception of type IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// Test successful
		}
	}

	@Test
	public void test_findPath_ParameterStartIsNull() {
		try {
			sut.findPath(null, "t", new ResidualGraphImpl<String>(flowGraph1));
			fail("Expected an exception of type IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// Test successful
		}
	}

	@Test
	public void test_findPath_ParameterTargetIsNull() {
		try {
			sut.findPath("s", null, new ResidualGraphImpl<String>(flowGraph1));
			fail("Expected an exception of type IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// Test successful
		}
	}

	public String getTeamIdentifier() {
		return "G03T03";
	}
}
