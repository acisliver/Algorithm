package leetcode;

// Time Needed to Buy Tickets
public class N2073 {

    public static void main(String[] args) {
        new N2073().timeRequiredToBuy(new int[]{5, 1, 1, 1}, 0);
    }

    public int timeRequiredToBuy(int[] tickets, int k) {
        int cycle = tickets[k];
        int time = cycle;
        for (int i = 0; i < k; i++) {
            int ticket = tickets[i];
            time += Math.min(cycle, ticket);
        }

        for (int i = k + 1; i < tickets.length; i++) {
            int ticket = tickets[i];
            time += Math.min(cycle - 1, ticket);
        }

        return time;
    }
}
