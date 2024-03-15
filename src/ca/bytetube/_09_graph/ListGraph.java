package ca.bytetube._09_graph;

import java.util.*;

public class ListGraph<V, E> extends Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();

    private Comparator<Edge<V, E>> edgeComparator = (o1, o2) -> weightManager.compare(o1.weight, o2.weight);

    public ListGraph() {
    }

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

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

        public EdgeInfo<V, E> info() {
            return new EdgeInfo<V, E>(weight, from.value, to.value);
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

    /**
     * 1.需要准备一个map（用来存图的inDegree信息），一个queue（缓冲区），一个list（存排序结果）
     * 2.将graph中inDegree=0的顶点放入queue，inDegree !=0 放入map中
     * 3.出队头元素，放入list中，并且更新map中的inDegree信息
     * 4.从map中找到inDegree=0的顶点，并放入queue中
     * 5.不断重复3，4的过程，直到queue为空
     */
    @Override
    List<V> topologicalSort(V begin) {
        //1. 需要准备一个map（用来存图的inDegree信息），一个queue（缓冲区），一个list（存排序结果）
        Map<Vertex<V, E>, Integer> map = new HashMap<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        List<V> list = new LinkedList<>();
        //2.将graph中inDegree=0的顶点放入queue，inDegree !=0 放入map中
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int size = vertex.inEdges.size();
            if (size == 0) queue.offer(vertex);
            else map.put(vertex, size);
        });

        //5.不断重复3，4的过程，直到queue为空
        while (!queue.isEmpty()) {
            //3.出队头元素，放入list中，并且更新map中的inDegree信息
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> edge : vertex.outEdges) {
                int toIn = map.get(edge.to) - 1;
                //  4.从map中找到inDegree=0的顶点，并放入queue中
                if (toIn == 0) queue.offer(edge.to);
                else map.put(edge.to, toIn);
            }
        }

        return list;
    }

    @Override
    Set<EdgeInfo<V, E>> mst() {

        return kruskal();
    }

    @Override
    Map<V, E> shortestPathWithoutPathInfo(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        /**
         *   paths.put("B",10);
         *   paths.put("C",50);
         *   paths.put("D",30);
         *   paths.put("E",60);
         *
         *   从paths中找到第一个起飞点：找到从A到B,D,E的最短路径：("B",10);
         *   由于B点还在map中，所以下次选最短路径时，还是会被重复选中
         *   因此一个map不够，需要再做map，用来存储已经原点到达其他点的最短路径
         *   Map<V,E> selectedPaths = new HashMap<>();
         *   selectedPaths.put("B",10);
         *   paths.remove("B");
         *   对B点的所有outEdges做一次松弛操作（更新A点到达其他顶点的最短路径）
         */

        Map<Vertex<V, E>, E> paths = new HashMap<>();//红色容器
        Map<V, E> selectedPaths = new HashMap<>();//绿色容器

        //1.初始化paths：将B,D,E放入paths中
        for (Edge<V, E> edge : beginVertex.outEdges) {
            paths.put(edge.to, edge.weight);
        }
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, E> minEntry = getMinPathWithoutPathInfo(paths);
            Vertex<V, E> minVertex = minEntry.getKey();
            E minWeight = minEntry.getValue();
            selectedPaths.put(minVertex.value, minWeight);
            paths.remove(minVertex);
            //Relaxation：Update the shortest path between 2 vertices
            //对起飞顶点的所有的outEdges更新weight
            for (Edge<V, E> edge : minVertex.outEdges) {
                //计算newWeight
                E newWeight = weightManager.add(minEntry.getValue(), edge.weight);

                //计算oldWeight
                E oldWeight = paths.get(edge.to);
                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
                    paths.put(edge.to, newWeight);
                }
            }
        }

        return selectedPaths;
    }

    @Override
    Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return bellmanFord(begin);
    }

    @Override
    public Map<V, Map<V, PathInfo<V, E>>> shortestPath() {
        Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();

        //paths 初始化 默认图中点与点之间直接的连线是最短路径
        for (Edge<V, E> edge : edges) {
            //终点                                   //起点
            Map<V, PathInfo<V, E>> map = paths.get(edge.from.value);
            if (map == null) {
                map = new HashMap<>();
                paths.put(edge.from.value, map);//(A,map(null))
            }

            PathInfo<V, E> pathInfo = new PathInfo<>(edge.weight);
            pathInfo.edgeInfos.add(edge.info());
            map.put(edge.to.value, pathInfo);
            //AMap: key:A value:pathInfo(AB,10)
            //  key:A value:pathInfo(AE,8)
            // paths.put(edge.from.value,map);

        }

        vertices.forEach((V v2, Vertex<V, E> vertex2) -> {

            vertices.forEach((V v1, Vertex<V, E> vertex1) -> {

                vertices.forEach((V v3, Vertex<V, E> vertex3) -> {
                    //v1--->v2  //v2--->v3  //v1--->v3

                    //v1--->v2
                    PathInfo<V, E> path12 = getPathInfo(v1, v2, paths);

                    if (path12 == null) return;
                    //v2--->v3
                    PathInfo<V, E> path23 = getPathInfo(v2, v3, paths);
                    if (path23 == null) return;

                    E newWeight = weightManager.add(path12.weight, path23.weight);

                    //v1--->v3
                    PathInfo<V, E> path13 = getPathInfo(v1, v3, paths);

                    if (path13 != null && weightManager.compare(newWeight, path13.weight) >= 0) return;
                    if (path13 == null) {
                        path13 = new PathInfo<>();
                        paths.get(v1).put(v3, path13);

                    } else path13.edgeInfos.clear();

                    path13.weight = newWeight;

                    path13.edgeInfos.addAll(path12.edgeInfos);

                    path13.edgeInfos.addAll(path23.edgeInfos);

                });
            });
        });

        return paths;
    }

    private PathInfo<V, E> getPathInfo(V v1, V v2, Map<V, Map<V, PathInfo<V, E>>> paths) {
        Map<V, PathInfo<V, E>> infoMap = paths.get(v1);
        if (infoMap == null) {
            return null;
        }
        return infoMap.get(v2);
    }

    private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();

        selectedPaths.put(beginVertex.value, new PathInfo<>(weightManager.zero()));
        for (int i = 0; i < vertices.size() - 1; i++) {
            for (Edge<V, E> edge : edges) {//edge = DC
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);//AD
                if (fromPath == null) continue;
                relaxationBellmanFord(edge, selectedPaths, fromPath);
            }
        }
        for (Edge<V, E> edge : edges) {//edge = DC
            PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);//AD
            if (fromPath == null) continue;

            if (relaxationBellmanFord(edge, selectedPaths, fromPath)) {
                throw new RuntimeException("there is a negative cycle !");
            }
        }

        selectedPaths.remove(beginVertex.value);
        return selectedPaths;
    }


    private boolean relaxationBellmanFord(Edge<V, E> edge, Map<V, PathInfo<V, E>> paths, PathInfo<V, E> fromPath) {
        //1.计算newWeight  AC = AD+DC
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        //计算oldWeight
        PathInfo<V, E> oldPath = paths.get(edge.to.value);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return false;

        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to.value, oldPath);
        } else oldPath.edgeInfos.clear();

        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);// A-D
        oldPath.edgeInfos.add(edge.info());//DC
        return true;
    }


    private Map<V, PathInfo<V, E>> dijkstra(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;

        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();//红色容器
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();//绿色容器

        //1.初始化paths：将B,D,E放入paths中
        for (Edge<V, E> edge : beginVertex.outEdges) {
            PathInfo<V, E> path = new PathInfo<>();
            path.weight = edge.weight;
            path.edgeInfos.add(edge.info());
            paths.put(edge.to, path);
        }

        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);
            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPath = minEntry.getValue();
            selectedPaths.put(minVertex.value, minPath);
            paths.remove(minVertex);


            //Relaxation：Update the shortest path between 2 vertices
            //对起飞顶点的所有的outEdges更新weight
            for (Edge<V, E> edge : minVertex.outEdges) {
                if (selectedPaths.containsKey(edge.to.value)) continue;
                relaxationDijkstra(edge, paths, minPath);

            }
        }

        return selectedPaths;
    }

    private void relaxationDijkstra(Edge<V, E> edge, Map<Vertex<V, E>, PathInfo<V, E>> paths, PathInfo<V, E> minPath) {
        //1.计算newWeight
        E newWeight = weightManager.add(minPath.weight, edge.weight);
        //计算oldWeight
        PathInfo<V, E> oldPath = paths.get(edge.to);

        if (oldPath == null || weightManager.compare(newWeight, oldPath.weight) < 0) {
            PathInfo<V, E> path = new PathInfo<>();
            path.weight = newWeight;
            //A->E  A-D-C + C-E
            path.edgeInfos.addAll(minPath.edgeInfos);// A-D-C
            path.edgeInfos.add(edge.info());//C-E
            paths.put(edge.to, path);
        }

    }

    //minHeap 优化找最小路径
    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> nextEntry = iterator.next();
            if (weightManager.compare(nextEntry.getValue().weight, minEntry.getValue().weight) < 0) {
                minEntry = nextEntry;
            }
        }
        return minEntry;
    }


    //minHeap 优化找最小路径
    private Map.Entry<Vertex<V, E>, E> getMinPathWithoutPathInfo(Map<Vertex<V, E>, E> paths) {
        Iterator<Map.Entry<Vertex<V, E>, E>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, E> minEntry = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, E> nextEntry = iterator.next();
            if (weightManager.compare(nextEntry.getValue(), minEntry.getValue()) < 0) {
                minEntry = nextEntry;
            }
        }
        return minEntry;
    }

    private Set<EdgeInfo<V, E>> prim() {
        //A mst边集
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        //S mst点集
        Set<Vertex<V, E>> addedVertices = new HashSet<>();
        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        Vertex<V, E> vertex = iterator.next();//A点
        addedVertices.add(vertex);
        MinHeap<Edge<V, E>> minHeap = new MinHeap<>(vertex.outEdges, edgeComparator);

        while (!minHeap.isEmpty() && addedVertices.size() < vertices.size()) {
            Edge<V, E> edge = minHeap.remove();
            if (addedVertices.contains(edge.to)) continue;
            //AB--->A mst边集
            edgeInfos.add(edge.info());
            addedVertices.add(edge.to);

            //将B点所有的outEdges放到heap中，继续寻找A mst边集中的最小的crossing edge
            minHeap.addAll(edge.to.outEdges);
        }

        return edgeInfos;
    }

    private Set<EdgeInfo<V, E>> kruskal() {
        int edgeSize = edges.size() - 1;
        if (edgeSize == -1) return null;
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        MinHeap<Edge<V, E>> minHeap = new MinHeap<>(edges, edgeComparator);
        UnionFind<Vertex<V, E>> uf = new UnionFind<>();
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            uf.makeSet(vertex);
        });

        while (!minHeap.isEmpty() && edgeInfos.size() < vertices.size() - 1) {
            Edge<V, E> edge = minHeap.remove();
            if (uf.isSame(edge.to, edge.from)) continue;
            edgeInfos.add(edge.info());
            uf.union(edge.from, edge.to);
        }

        return edgeInfos;
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
