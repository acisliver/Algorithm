public class StringToArrayConverter {
    public static void main(String[] args) {
        String array = "[[1, 4, 4], [1, 6, 1], [1, 7, 3], [2, 5, 2], [3, 7, 4], [5, 6, 6]]";
        String replace = array.replace("[", "{").replace("]", "}");
        System.out.println(replace);
    }
}
