package leetcode;

// Find the Pivot Integer
public class N2485 {
    public int pivotInteger(int n) {
        int sum = n * (n + 1) / 2;
        double a = Math.sqrt(sum);

        if (a - Math.ceil(a) == 0) {
            return (int) a;
        }

        return -1;
    }
}
