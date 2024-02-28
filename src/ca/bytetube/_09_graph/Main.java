package ca.bytetube._09_graph;

import org.junit.Test;

public class Main {
    public static void main(String[] args) {

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

    public static Graph<Object, Double> unDirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
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
        Graph<Object, Double> graph = new ListGraph<>();
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
