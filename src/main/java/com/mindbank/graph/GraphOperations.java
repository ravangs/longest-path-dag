package com.mindbank.graph;

import java.util.*;
public class GraphOperations extends GraphCore {

    // Topological sort on the graph using dfs
    // returns list of vertices which are topologically
    private List<Vertex> topologicalSort() {
        // Set to store visited vertex
        Set<Vertex> visited = new HashSet<>();

        // A stack to collect the order of vertices
        Deque<Vertex> stack = new ArrayDeque<>();

        // Iterate over all the vertices and perform DFS if not already visited
        adjList.keySet().forEach(vertex -> {
            if (!visited.contains(vertex)) {
                dfs(vertex, visited, stack);
            }
        });

        List<Vertex> sortedList = new ArrayList<>();
        while (!stack.isEmpty()) {
            sortedList.add(stack.pop());
        }
        return sortedList;
    }

    // Helper method to perform dfs starting from a vertex
    private void dfs(Vertex vertex, Set<Vertex> visited, Deque<Vertex> stack) {
        visited.add(vertex);
        getAdjVertices(vertex).forEach(v -> {
            if (!visited.contains(v)) {
                dfs(v, visited, stack);
            }
        });
        stack.push(vertex);
    }

    // calculates the longest path from the start vertex using topological sort
    public int findLongestPathFromVertex(Vertex startVertex) {
        Map<Vertex, Integer> distances = new HashMap<>();

        // Initialize distance starting with unreachability (indicated by MIN_VALUE)
        adjList.keySet().forEach(v -> distances.put(v, Integer.MIN_VALUE));
        distances.put(startVertex, 0);

        List<Vertex> sortedVertices = topologicalSort();

        // For each vertex in the sorted list, the distance of its adjacent vertices is updated
        sortedVertices.forEach(v -> {
            if (distances.get(v) != Integer.MIN_VALUE) {
                getAdjVertices(v).forEach(adj -> {
                    if (distances.get(adj) < distances.get(v) + 1) {
                        distances.put(adj, distances.get(v) + 1);
                    }
                });
            }
        });

        return distances.values().stream().max(Integer::compareTo).orElse(0);
    }

    // Checks if the graphs is a DAG
    public boolean isDAG() {
        Set<Vertex> visited = new HashSet<>();
        Set<Vertex> recursionStack = new HashSet<>();

        // Checking for cycles in each connected vertex
        for (Vertex vertex : adjList.keySet()) {
            if (!visited.contains(vertex) && isCyclic(vertex, visited, recursionStack)) {
                    return false;
            }
        }
        return true;
    }

    // Helper method to detect cycles
    private boolean isCyclic(Vertex vertex, Set<Vertex> visited, Set<Vertex> recursionStack) {
        // Check if cycle exists
        if (recursionStack.contains(vertex)) {
            return true;
        }

        // Check if vertex is already visited/processed
        if (visited.contains(vertex)) {
            return false;
        }

        visited.add(vertex);
        recursionStack.add(vertex);

        // add all connected vertices to recursion stack
        for (Vertex adj : getAdjVertices(vertex)) {
            if (isCyclic(adj, visited, recursionStack)) {
                return true;
            }
        }

        recursionStack.remove(vertex);
        return false;
    }
}

