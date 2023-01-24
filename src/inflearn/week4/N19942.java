package inflearn.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 다이어트
// https://www.acmicpc.net/problem/19942
public class N19942 {

    static List<Ingredient> INGREDIENTS;
    static boolean[] VISITED;
    static int MIN_PRICE;
    static List<Integer> CHOOSE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        INGREDIENTS = new ArrayList<>();
        VISITED = new boolean[n];
        MIN_PRICE = Integer.MAX_VALUE;
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Ingredient standard = new Ingredient(input[0], input[1], input[2], input[3], 0);

        for (int i = 0; i < n; i++) {
            input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Ingredient ingredient = new Ingredient(i + 1, input[0], input[1], input[2], input[3], input[4]);
            INGREDIENTS.add(ingredient);
        }

        chooseIngredient(0, standard);

        if (MIN_PRICE != Integer.MAX_VALUE) {
            System.out.println(MIN_PRICE);
            System.out.println(CHOOSE.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        } else {
            System.out.println(-1);
        }
    }

    private static void chooseIngredient(int here, Ingredient standard) {
        if (standard.isSatisfied()) {
            if (MIN_PRICE > standard.price) {
                MIN_PRICE = standard.price;
                CHOOSE = setChoose(VISITED);
            } else if (MIN_PRICE == standard.price) {
                CHOOSE = checkChoose(VISITED);
            }
            return;
        }

        if (here >= INGREDIENTS.size()) {
            return;
        }

        chooseIngredient(here + 1, standard);

        Ingredient ingredient = INGREDIENTS.get(here);
        VISITED[here] = true;
        chooseIngredient(here + 1, standard.add(ingredient));
        standard.remove(ingredient);
        VISITED[here] = false;
    }

    private static List<Integer> setChoose(boolean[] visited) {
        List<Integer> choose = new ArrayList<>();

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                choose.add(i + 1);
            }
        }

        return choose;
    }

    private static List<Integer> checkChoose(boolean[] visited) {
        List<Integer> choose = new ArrayList<>();

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                choose.add(i + 1);
            }
        }

        for (int i = 0; i < choose.size(); i++) {
            if (choose.get(i) < CHOOSE.get(i)) {
                return choose;
            } else if (choose.get(i) > CHOOSE.get(i)) {
                return CHOOSE;
            }
        }

        return choose;
    }

    static class Ingredient {
        int number;
        int protein;
        int fat;
        int sugar;
        int vitamin;
        int price;

        public Ingredient(int protein, int fat, int sugar, int vitamin, int price) {
            this.protein = protein;
            this.fat = fat;
            this.sugar = sugar;
            this.vitamin = vitamin;
            this.price = price;
        }

        public Ingredient(int number, int protein, int fat, int sugar, int vitamin, int price) {
            this.number = number;
            this.protein = protein;
            this.fat = fat;
            this.sugar = sugar;
            this.vitamin = vitamin;
            this.price = price;
        }

        public boolean isSatisfied() {
            return protein <= 0 && fat <= 0 && sugar <= 0 && vitamin <= 0;
        }

        public Ingredient add(Ingredient ingredient) {

            this.protein -= ingredient.protein;
            this.fat -= ingredient.fat;
            this.sugar -= ingredient.sugar;
            this.vitamin -= ingredient.vitamin;
            this.price += ingredient.price;

            return this;
        }

        public Ingredient remove(Ingredient ingredient) {
            this.protein += ingredient.protein;
            this.fat += ingredient.fat;
            this.sugar += ingredient.sugar;
            this.vitamin += ingredient.vitamin;
            this.price -= ingredient.price;

            return this;
        }
    }


}
