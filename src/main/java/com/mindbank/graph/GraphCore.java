package com.mindbank.graph;

import java.util.*;
import java.util.stream.Collectors;

public class GraphCore {
    protected final Map<Vertex, List<Vertex>> adjList = new HashMap<>();

    public void addVertex(Vertex v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(Vertex from, Vertex to) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    protected List<Vertex> getAdjVertices(Vertex v) {
        return adjList.getOrDefault(v, new ArrayList<>());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        adjList.keySet().forEach(v -> {
            builder.append(v).append(" -> ");
            List<Vertex> edges = getAdjVertices(v);
            if (edges.isEmpty()) {
                builder.append("[]");
            } else {
                builder.append(edges.stream()
                        .map(Vertex::toString)
                        .collect(Collectors.joining(", ", "[", "]")));
            }
            builder.append(System.lineSeparator());
        });
        return builder.toString();
    }
}

