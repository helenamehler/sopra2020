package solutions.exercise4;

import org.junit.Before;
import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.AbstractFordFulkersonTest;
import org.sopra.api.exercises.exercise4.FordFulkerson;

import solutions.exercise3.FlowGraphImpl;
import solutions.exercise3.ResidualGraphImpl;
public class FordFulkersonTestH {
	FlowGraph<String> flow;
	ResidualGraph<String> res;
	FordFulkerson<String> ffi;
	
	@Before
	public void setup() {
		flow = new FlowGraphImpl<String>();
		flow.addNode("a");
		flow.addNode("b");
		flow.addNode("c");
		flow.addNode("d");
		flow.addNode("e");
		flow.addNode("s");
		flow.addNode("t");
	
	
		flow.addEdge("a", "b", 7);
		flow.addEdge("a", "s", 11);
		flow.addEdge("a", "c", 6);
//		flow.addEdge("a", "d", 7);
		flow.addEdge("b", "a", 7);
		flow.addEdge("b", "s", 5);
		flow.addEdge("b", "c", 4);
//		flow.addEdge("b", "e", 0);
		flow.addEdge("s", "a", 11);
		flow.addEdge("s", "b", 5);
		flow.addEdge("c", "a", 6);
		flow.addEdge("c", "b", 4);
		flow.addEdge("c", "d", 11);
		flow.addEdge("c", "e", 3);
//		flow.addEdge("d", "a", 3);
		flow.addEdge("d", "c", 11);
		flow.addEdge("d", "t", 6);
		flow.addEdge("d", "e", 8);
		flow.addEdge("t", "d", 6);
		flow.addEdge("t", "e", 7);
//		flow.addEdge("e", "b", 10);
		flow.addEdge("e", "c", 3);
		flow.addEdge("e", "d", 8);
		flow.addEdge("e", "t", 7);
		
//		flow.addNode("s");
//		flow.addNode("t");
//		flow.addNode("u");
//		flow.addNode("v");
	//	
//		flow.addEdge("s", "v", 10);
//		flow.addEdge("v", "s", 10);
//		flow.addEdge("v", "t", 10);
//		flow.addEdge("t", "v", 10);
//		flow.addEdge("s", "u", 10);
//		flow.addEdge("u", "s", 10);
//		flow.addEdge("u", "t", 10);
//		flow.addEdge("t", "u", 10);
	
	
		res = new ResidualGraphImpl<String>(flow);
		ffi = new FordFulkersonImpl<String>();
	}
	
	@Test
	public void findMaxFlowTest() {
		ffi.findMaxFlow(flow, "s", "t");
	}

}
