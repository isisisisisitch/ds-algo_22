package ca.bytetube._12_greedy;

public class Article {
    public int weight;
    public int value;

    public double density;

    public Article(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.density = value * 1.0 / weight;
    }


    @Override
    public String toString() {
        return "{weight=" + weight + ", value=" + value + ", density=" + density + '}';
    }
}
