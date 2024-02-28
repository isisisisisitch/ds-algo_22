package ca.bytetube._09_graph;

import java.util.List;

public abstract class Graph<V, E> {

    abstract int verticesSize();

    abstract int edgeSize();

    abstract void addVertex(V v);

    abstract void removeVertex(V v);

    abstract void addEdge(V fromV, V toV);

    abstract void addEdge(V fromV, V toV, E weight);

    abstract void removeEdge(V fromV, V toV);

    abstract void bfs(V begin);

    abstract void bfs(V begin, VertexVisitor<V> visitor);

    abstract void dfs(V begin);

    abstract void dfs(V begin, VertexVisitor<V> visitor);

    abstract List<V> topologicalSort(V begin);

    public static abstract class VertexVisitor<V> {

        public abstract boolean visit(V val);

    }


}
