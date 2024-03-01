package ca.bytetube._09_graph;

import java.util.List;
import java.util.Set;

public abstract class Graph<V, E> {

    protected WeightManager<E> weightManager;

    public Graph() {
    }

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

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

    abstract Set<EdgeInfo<V, E>> mst();

    public static abstract class VertexVisitor<V> {

        public abstract boolean visit(V val);

    }

    public static class EdgeInfo<V, E> {
        E weight;
        V from;
        V to;

        public EdgeInfo(E weight, V from, V to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }


        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        @Override
        public String toString() {
            return
                    "{from=" + from + ", to=" + to +
                            ",weight=" + weight + "}";
        }
    }

    public interface WeightManager<E> {
        public int compare(E w1, E w2);
//        public int add(E w1, E w2);
//        public int minus(E w1, E w2);
    }


}