package solutions.exercise3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.AbstractFlowGraphTest;
import org.sopra.api.exercises.exercise3.FlowEdge;

public class FlowGraphTest extends AbstractFlowGraphTest implements ExerciseSubmission {

	public String getTeamIdentifier() {
		return "G03T03";
	}

	/**
	 * to compare two flowEdges. If the capacity, the start and end node are the
	 * same, they are considered equal
	 * 
	 * @param edge1 first FlowEdge to be compared
	 * @param edge2 second FlowEdge to be compared
	 * @return true if equal, false if not or if one of them is null
	 */
	public static boolean compare(FlowEdge<String> edge1, FlowEdge<String> edge2) {
		if (edge1 == null || edge2 == null) {
			return false;
		}
		if (edge1.getCapacity() == edge2.getCapacity() && edge1.getStart() == edge2.getStart()
				&& edge1.getEnd() == edge2.getEnd()) {
			return true;
		}
		return false;
	}

	/**
	 * adds all nodes
	 */
	public void nodesAdd() {
		sut.addNode("s");
		sut.addNode("u");
		sut.addNode("v");
		sut.addNode("t");
	}

	/**
	 * adds all edges
	 */
	public void EdgesAdd() {
		for (FlowEdge<String> edge : edges) {
			sut.addEdge(edge.getStart(), edge.getEnd(), edge.getCapacity());
		}
	}

	@Test
	public void test_getEdge() {
		nodesAdd();
		EdgesAdd();

		assertEquals(sut.getEdge(null, "s"), null);
		assertEquals(sut.getEdge("s", null), null);
		assertEquals(sut.getEdge("a", "b"), null);

		FlowEdge<String> test = sut.getEdge("u", "s");
		List<FlowEdge<String>> allEdges = sut.getEdges();
		assertEquals(compare(test, edges[1]), true);
		assertEquals(allEdges.contains(test), true);

	}

	@Test
	public void test_addEdge() {
		nodesAdd();

		FlowEdge<String> test = sut.addEdge("t", "u", 4);
		assertEquals(compare(edges[6], test), true);
		assertEquals(compare(sut.getEdge("t", "u"), test), true);
		assertEquals(sut.addEdge("t", "u", 4), test);
		assertEquals(test.getFlow(), 0);

		try {
			sut.addEdge("s", "x", 2);
			fail();
		} catch (NoSuchElementException e) {
		}
		try {
			sut.addEdge("x", "u", 2);
			fail();
		} catch (NoSuchElementException e) {
		}

	}

	@Test
	public void test_addNode() {
		assertEquals(sut.addNode("s"), true);
		assertEquals(sut.getNodes().contains(nodes[0]), true);
		assertEquals(sut.addNode("s"), false);
		assertEquals(sut.addNode(null), false);
	}

	@Test
	public void test_containsNode() {
		nodesAdd();
		EdgesAdd();

		assertEquals(sut.containsNode("a"), false);
		assertEquals(sut.containsNode("s"), true);
		assertEquals(sut.containsNode("t"), true);
		assertEquals(sut.containsNode("u"), true);
		assertEquals(sut.containsNode("v"), true);

	}

	@Test
	public void test_getEdges() {

		nodesAdd();

		List<FlowEdge<String>> list1 = sut.getEdges();
		if (list1 == null) {
			fail();
		}
		assertEquals(list1.isEmpty(), true);
		FlowEdge<String> edge1 = sut.addEdge("s", "u", 5);
		FlowEdge<String> edge2 = sut.addEdge("u", "s", 5);
		FlowEdge<String> edge3 = sut.addEdge("v", "s", 7);
		sut.addEdge("s", "v", 7);
		sut.addEdge("u", "v", 9);
		sut.addEdge("v", "u", 9);
		sut.addEdge("v", "t", 6);
		sut.addEdge("t", "v", 6);
		sut.addEdge("u", "t", 4);
		sut.addEdge("t", "u", 4);

		List<FlowEdge<String>> list2 = sut.getEdges();
		assertEquals(edges.length, list2.size());
		assertEquals(list2.contains(edge1), true);
		assertEquals(list2.contains(edge2), true);
		assertEquals(list2.contains(edge3), true);

	}

	@Test
	public void test_getNodes() {
		Set<String> list1 = sut.getNodes();
		if (list1 == null) {
			fail();
		}
		assertEquals(list1.isEmpty(), true);

		nodesAdd();

		Set<String> test = sut.getNodes();
		assertEquals(test.size(), nodes.length);
		for (int i = 0; i < nodes.length; i++) {
			assertEquals(test.contains(nodes[i]), true);
		}
	}

	@Test
	public void test_edgesFrom() {
		nodesAdd();

		FlowEdge<String> edge1 = sut.addEdge("s", "u", 5);
		FlowEdge<String> edge2 = sut.addEdge("u", "s", 5);
		sut.addEdge("v", "s", 7);
		FlowEdge<String> edge3 = sut.addEdge("s", "v", 7);
		FlowEdge<String> edge4 = sut.addEdge("u", "v", 9);
		sut.addEdge("v", "u", 9);
		sut.addEdge("v", "t", 6);
		sut.addEdge("t", "v", 6);
		FlowEdge<String> edge5 = sut.addEdge("u", "t", 4);
		sut.addEdge("t", "u", 4);

		Collection<FlowEdge<String>> test = sut.edgesFrom("u");

		if (test == null) {
			fail();
		}
		assertEquals(test.contains(edge2), true);
		assertEquals(test.contains(edge4), true);
		assertEquals(test.contains(edge5), true);
		assertEquals(test.size(), 3);

		// here i make another test because there was an error in the framework which
		// said,
		// that random nodes are being removed. I'm not sure how the second test helps
		// but the error did not show after.
		// especially because tests on the nodes specifically (like are 4 nodes are in
		// the graph?) does not work with the error
		Collection<FlowEdge<String>> test2 = sut.edgesFrom("s");

		if (test2 == null) {
			fail();
		}
		assertEquals(test2.contains(edge1), true);
		assertEquals(test2.contains(edge3), true);
		assertEquals(test2.size(), 2);

		try {
			sut.edgesFrom(null);
			fail();
		} catch (NoSuchElementException e) {
		}
		try {
			sut.edgesFrom("a");
			fail();
		} catch (Exception e) {
		}

	}

}
