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

        @Override
        public String toString() {
            return "vertex = " + value;
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
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;
//        for (Edge<V, E> edge : vertex.outEdges) {
//            removeEdge(edge.from.value,edge.to.value);
//            edges.remove(edge);
//        }
//
//        for (Edge<V, E> edge : vertex.inEdges) {
//            removeEdge(edge.to.value,edge.from.value);
//            edges.remove(edge);
//        }
        for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

        for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            edge.from.outEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

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

    @Override
    void bfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
            System.out.println(poll);
            for (Edge<V, E> edge : poll.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            }
        }


    }

    @Override
    void bfs(V begin, VertexVisitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
            if (visitor.visit(poll.value)) return;
            for (Edge<V, E> edge : poll.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            }
        }
    }


    @Override
    void dfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        dfs(beginVertex, new HashSet<>());
    }

    /**
     * 思路：
     * 1.先将起点放入栈中并打印，将起点存入set中，再从栈中弹出
     * 2.选择被弹出顶点的一条outEdge，将outEdge的from和to顺序地放回栈中
     * 3.访问栈顶元素，最后存入set中
     */
    @Override
    void dfs(V begin, VertexVisitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        stack.push(beginVertex);
        // System.out.print(beginVertex.value + " ");
        if (visitor.visit(beginVertex.value)) return;
        visitedVertices.add(beginVertex);

        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();//1
            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);

                //System.out.print(edge.to.value + " ");
                if (visitor.visit(edge.to.value)) return;
                visitedVertices.add(edge.to);
                //为什么要加break？因为不能去访问vertex.outEdges的其他边，而应该去访问这条边上的更深的点
                break;

            }
        }


    }

    @Override
    List<V> topologicalSort(V begin) {
        return null;
    }



    private void dfs(Vertex<V, E> beginVertex, Set<Vertex<V, E>> visitedVertices) {

        System.out.print(beginVertex.value + " ");
        visitedVertices.add(beginVertex);
        for (Edge<V, E> edge : beginVertex.outEdges) {
            if (visitedVertices.contains(edge.to)) continue;
            dfs(edge.to, visitedVertices);
        }


    }
}
