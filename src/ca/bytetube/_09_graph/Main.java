package ca.bytetube._09_graph;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

    }

    @Test
    public void testSP() {
        Graph<Object, Double> directedGraph = directedGraph(Data.SP);
        Map<Object, Double> map = directedGraph.shortestPath("A");
        System.out.println(map);

    }

    @Test
    public void testMST() {
        Graph<Object, Double> unDirectedGraph = unDirectedGraph(Data.MST_02);
        Set<Graph.EdgeInfo<Object, Double>> mst = unDirectedGraph.mst();
        System.out.println(mst);
    }


    @Test
    public void testTOPO() {
        Graph<Object, Double> directedGraph = directedGraph(Data.TOPO);
        List<Object> list = directedGraph.topologicalSort(0);
        System.out.println(list);
    }


    @Test
    public void testDFS() {
        Graph<Object, Double> directedGraph = directedGraph(Data.DFS_02);
        directedGraph.dfs("a", new Graph.VertexVisitor<Object>() {
            @Override
            public boolean visit(Object val) {
                System.out.print(val + " ");
                return val.equals("f");
            }
        });

    }


    @Test
    public void testBFS() {
        Graph<Object, Double> directedGraph = directedGraph(Data.BFS_02);
        directedGraph.bfs(0, new Graph.VertexVisitor<Object>() {
            @Override
            public boolean visit(Object val) {
                System.out.println(val);
                return val.equals(7);
            }
        });

    }

    @Test
    public void testRemove() {
        Graph<Object, Double> directedGraph = directedGraph(Data.BFS_02);
        directedGraph.removeVertex(0);
        System.out.println(directedGraph);
    }

    @Test
    public void testAdd() {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("V0", "V4", 6);
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);

        System.out.println(graph);


    }

    static Graph.WeightManager<Double> weightManager = new Graph.WeightManager<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            return w1.compareTo(w2);
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }
    };

    public static Graph<Object, Double> unDirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }

        return graph;
    }


    public static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }

        return graph;
    }


}
