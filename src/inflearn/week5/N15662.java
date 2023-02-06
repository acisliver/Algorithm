package inflearn.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// 톱니바퀴 (2)
// https://www.acmicpc.net/problem/15662
/*
    1. 맞물린 톱니바퀴들을 움직인다.
    1.1 움직이려는 톱니바퀴와 맞물린 톱니도 함께 움직인다.
    2. 위가 S인 톱니바퀴의 개수를 센다.
 */
public class N15662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Gear> bunchOfGear = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Pole> poles = Arrays.stream(br.readLine().split(""))
                    .map(Integer::valueOf)
                    .map(Pole::numberToPole)
                    .collect(Collectors.toList());
            Gear gear = new Gear(i, poles);
            bunchOfGear.add(gear);
        }

        Gears gears = new Gears(bunchOfGear);

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int number = input[0] - 1;
            int side = input[1];
            gears.move(number, Gears.Side.numberToSide(side));
        }

        long southAtTopCount = gears.countSouthAtTop();
        System.out.println(southAtTopCount);
    }

    static class Gears {
        private final List<Gear> gears;

        Gears(List<Gear> gears) {
            this.gears = gears;
        }

        public void move(int number, Side side) {
            if (number < 0 || number >= gears.size()) {
                return;
            }

            int leftNumber = number - 1;
            int rightNumber = number + 1;
            Gear currentGear = gears.get(number);
            boolean[] visited = new boolean[gears.size()];
            visited[number] = true;

            if (leftNumber >= 0) {
                Gear leftGear = gears.get(leftNumber);
                visited[leftNumber] = true;
                if (leftGear.isIntermeshedWith(currentGear)) {
                    move(leftNumber, side.getOpposite(), visited);
                }
            }

            if (rightNumber < gears.size()) {
                Gear rightGear = gears.get(rightNumber);
                visited[rightNumber] = true;
                if (rightGear.isIntermeshedWith(currentGear)) {
                    move(rightNumber, side.getOpposite(), visited);
                }
            }

            if (side == Side.CLOCKWISE) {
                moveClockwise(number);
                return;
            }

            moveCounterClockwise(number);
        }

        private void move(int number, Side side, boolean[] visited) {
            if (number < 0 || number >= gears.size()) {
                return;
            }

            int leftNumber = number - 1;
            int rightNumber = number + 1;
            Gear currentGear = gears.get(number);

            if (leftNumber >= 0 && !visited[leftNumber]) {
                Gear leftGear = gears.get(leftNumber);
                visited[leftNumber] = true;
                if (leftGear.isIntermeshedWith(currentGear)) {
                    move(leftNumber, side.getOpposite(), visited);
                }
            }

            if (rightNumber < gears.size() && !visited[rightNumber]) {
                Gear rightGear = gears.get(rightNumber);
                visited[rightNumber] = true;
                if (rightGear.isIntermeshedWith(currentGear)) {
                    move(rightNumber, side.getOpposite(), visited);
                }
            }

            if (side == Side.CLOCKWISE) {
                moveClockwise(number);
                return;
            }

            moveCounterClockwise(number);
        }

        private void moveClockwise(int number) {
            gears.get(number).moveClockwise();
        }

        private void moveCounterClockwise(int number) {
            gears.get(number).moveCounterClockwise();
        }

        public long countSouthAtTop() {
            return gears.stream()
                    .filter(Gear::isTopSouth)
                    .count();
        }

        enum Side {
            CLOCKWISE(1), COUNTERCLOCKWISE(-1);

            private final int number;

            Side(int number) {
                this.number = number;
            }

            public Side getOpposite() {
                if (this == Side.CLOCKWISE) {
                    return Side.COUNTERCLOCKWISE;
                }

                return Side.CLOCKWISE;
            }

            public static Side numberToSide(int number) {
                return Arrays.stream(Side.values())
                        .filter(side -> side.number == number)
                        .findAny()
                        .orElseThrow(IllegalArgumentException::new);
            }
        }
    }

    static class Gear {

        private static final int NUMBER_OF_TEETH = 8;

        private final int number;
        private final List<Pole> poles;
        private int top = 0;

        public Gear(int number, List<Pole> poles) {
            this.number = number;
            this.poles = Collections.unmodifiableList(poles);
        }

        public boolean isIntermeshedWith(Gear other) {
            if (this.number > other.number) {
                return isIntermeshedWithLeft(other);
            }

            if (this.number < other.number) {
                return isIntermeshedWithRight(other);
            }

            return false;
        }

        private boolean isIntermeshedWithLeft(Gear other) {
            return this.getLeft() != other.getRight();
        }

        private boolean isIntermeshedWithRight(Gear other) {
            return this.getRight() != other.getLeft();
        }

        private Pole getLeft() {
            int left = top - 2;
            if (left < 0) {
                left += NUMBER_OF_TEETH;
            }
            return this.poles.get(left);
        }

        private Pole getRight() {
            int right = top + 2;
            if (right >= NUMBER_OF_TEETH) {
                right -= NUMBER_OF_TEETH;
            }

            return this.poles.get(right);
        }

        public void moveClockwise() {
            this.top -= 1;
            while (this.top < 0) {
                this.top += NUMBER_OF_TEETH;
            }
        }

        public void moveCounterClockwise() {
            this.top += 1;
            while (this.top >= NUMBER_OF_TEETH) {
                this.top -= NUMBER_OF_TEETH;
            }
        }

        public boolean isTopSouth() {
            return poles.get(top) == Pole.SOUTH;
        }
    }

    enum Pole {
        NORTH(0), SOUTH(1);

        private final int number;

        Pole(int number) {
            this.number = number;
        }

        public static Pole numberToPole(int number) {
            return Arrays.stream(Pole.values())
                    .filter(pole -> pole.number == number)
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
}
