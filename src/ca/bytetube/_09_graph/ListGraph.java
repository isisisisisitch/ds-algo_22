package ca.bytetube._09_graph;

import java.util.*;

public class ListGraph<V, E> extends Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();

    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value) &&
                    Objects.equals(inEdges, vertex.inEdges) &&
                    Objects.equals(outEdges, vertex.outEdges);
        }

        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }
    }


    private static class Edge<V, E> {
        E weight;
        Vertex<V, E> from;
        Vertex<V, E> to;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(weight, edge.weight) &&
                    Objects.equals(from, edge.from) &&
                    Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight, from, to);
        }
    }


    @Override
    int verticesSize() {
        return vertices.size();
    }

    @Override
    int edgeSize() {
        return edges.size();
    }

    @Override
    void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<V, E>(v));

    }


    @Override
    void addEdge(V fromV, V toV) {
        addEdge(fromV, toV, null);
    }

    @Override
    void addEdge(V fromV, V toV, E weight) {
        //1.先判断fromV，toV 是否存在
        Vertex<V, E> fromVertex = vertices.get(fromV);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(fromV);
            vertices.put(fromV, fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(toV);
        if (toVertex == null) {
            toVertex = new Vertex<>(toV);
            vertices.put(toV, toVertex);
        }

        //代码能够来到这，说明一定有起点和终点
        //接下来还需要情定起点和终点之间之前是否有边，不管之前是否存在边，都尝试删除这条边，再重新建一条新边
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);

    }

    @Override
    void removeVertex(V v) {
        //删除点时，可能会导致与其相关的边也需要跟着一起被删除


    }

    @Override
    void removeEdge(V fromV, V toV) {
        //1.先判断from到to的这条边是否存在
        Vertex<V, E> fromVertex = vertices.get(fromV);
        if (fromVertex == null) return;
        Vertex<V, E> toVertex = vertices.get(toV);
        if (toVertex == null) return;


        //代码能来到这，说明这条边的起点终点一定存在,但是两点之间不一定有边
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }
}
