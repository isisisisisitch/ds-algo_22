package ca.bytetube._09_graph;

public abstract class Graph<V, E> {

    abstract int verticesSize();

    abstract int edgeSize();

    abstract void addVertex(V v);

    abstract void removeVertex(V v);

    abstract void addEdge(V fromV, V toV);

    abstract void addEdge(V fromV, V toV, E weight);

    abstract void removeEdge(V fromV, V toV);


}
