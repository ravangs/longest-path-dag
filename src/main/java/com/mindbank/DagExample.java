package com.mindbank;

import com.mindbank.graph.GraphOperations;
import com.mindbank.graph.Vertex;

public class DagExample {

    private DagExample(){}

    public static void run(){
        Vertex a = new Vertex(1L);
        Vertex b = new Vertex(2L);
        Vertex c = new Vertex(3L);
        Vertex d = new Vertex(4L);
        Vertex e = new Vertex(5L);
        Vertex f = new Vertex(6L);

        GraphOperations graph = new GraphOperations();
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);

        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(a, d);
        graph.addEdge(b, d);
        graph.addEdge(c, d);
        graph.addEdge(d, e);
        graph.addEdge(e, f);

        System.out.println(graph);

        if (graph.isDAG()) {
            int longestPathLength = graph.findLongestPathFromVertex(a);
            System.out.println("Longest path length:" + longestPathLength);
        } else {
            System.out.println("Given graph is not a DAG.");
        }
    }
}
