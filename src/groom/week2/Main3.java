package groom.week2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int t = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        List<Person> list = new ArrayList<>();

        for (int i = 0; i < t; i++) {
            String[] person = br.readLine().split(" ");
            String name = person[0];
            double height = Double.parseDouble(person[1]);
            list.add(new Person(name, height));
        }

        list.sort(Person::compareTo);

        System.out.println(list.get(k - 1));
    }

    static class Person implements Comparable<Person>{
        public String name;
        public double height;

        public Person (String name, double height) {
            this.name = name;
            this.height = height;
        }

        @Override
        public int compareTo(Person o) {
            int nameCompare = this.name.compareTo(o.name);
            if (nameCompare == 0) return Double.compare(this.height, o.height);
            return nameCompare;
        }

        @Override
        public String toString() {
            return String.format("%s %3.2f", this.name, this.height);
        }
    }
}
