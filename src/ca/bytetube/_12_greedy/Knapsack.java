package ca.bytetube._12_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Knapsack {
    public static void main(String[] args) {
        Article[] articles = new Article[]{new Article(35, 10),
                new Article(30, 40),
                new Article(60, 30),
                new Article(50, 50), new Article(40, 35),
                new Article(10, 40), new Article(25, 30)};
//        int val1 = select(150, articles, new Comparator<Article>() {
//            @Override
//            public int compare(Article o1, Article o2) {
//                return o2.value - o1.value;
//            }
//        });
//
//        System.out.println(val1);

//        int val2 = select(150, articles, new Comparator<Article>() {
//            @Override
//            public int compare(Article o1, Article o2) {
//                return o1.weight - o2.weight;
//            }
//        });
//
//        System.out.println(val2);

        int val3 = select(150, articles, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return Double.compare(o2.density, o1.density);

            }
        });

        System.out.println(val3);

    }


    public static int select(int W, Article[] articles, Comparator<Article> comparator) {
        Arrays.sort(articles, comparator);
        int value = 0;
        int weight = 0;
        List<Article> list = new ArrayList<>();
        for (int i = 0; i < articles.length && weight < W; i++) {
            int newWeight = articles[i].weight + weight;
            if (newWeight <= W) {
                weight = newWeight;
                value += articles[i].value;
                list.add(articles[i]);
            }
        }

        System.out.println(list);

        return value;


    }


}
