package ca.bytetube._17_sort._cmp;

public class Student implements Comparable<Student>{
    public int score;
    public int age;

    public Student(int score, int age) {
        this.score = score;
        this.age = age;
    }


    @Override
    public int compareTo(Student o) {
        return this.age - o.age;
    }
}
