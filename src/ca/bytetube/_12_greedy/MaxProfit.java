package ca.bytetube._12_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxProfit {
    public static void main(String[] args) {
        // maxProfit(int[] costs, int[] profits, int k, int m)
        int[] costs = {100, 150, 120, 200, 210, 140, 700, 300};
        int[] profits = {10, 15, 12, 20, 40, 5, 100, 50};
        int balance = maxProfit(costs, profits, 5, 130);
        System.out.println("your current balance:" + balance);
    }


    private static class Project {
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }


        @Override
        public String toString() {
            return "Project{" +
                    "cost=" + cost +
                    ", profit=" + profit +
                    '}';
        }
    }


    public static int maxProfit(int[] costs, int[] profits, int k, int m) {

        Project[] projects = new Project[costs.length];
        for (int i = 0; i < costs.length; i++) {
            projects[i] = new Project(costs[i], profits[i]);
        }

        //用cost划分项目，装进小根堆，看哪些项目小于初始资金的，将这些项目从堆中弹出
        // 装进以profit作为划分标准的大根堆中，取出profit最大项目去做
        PriorityQueue<Project> minCostQ = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

        minCostQ.addAll(Arrays.asList(projects));
        PriorityQueue<Project> maxProfitQ = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);

        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().cost <= m) {
                maxProfitQ.add(minCostQ.poll());
            }

            Project project = maxProfitQ.poll();


            if (project != null) {
                m += project.profit;
                System.out.println(project);
            }


        }


        return m;
    }

}
