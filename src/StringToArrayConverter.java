import java.util.StringTokenizer;

public class StringToArrayConverter<V> {
    private final String stringArray;
    private int dimension;

    public StringToArrayConverter(String stringArray) {
        this.stringArray = stringArray;
        this.dimension = this.getDimension();
    }

    private int getDimension() {
        int count = 0;
        String squareBracket = "[";
        StringTokenizer st = new StringTokenizer(this.stringArray);

        while(st.hasMoreTokens()) {

            if (st.nextToken().equals(squareBracket)) {
                count++;
            } else {
                return count;
            }
        }

        return count;
    }

}
