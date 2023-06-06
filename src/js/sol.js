const [input_n, input_S, ...ARR] = require('fs')
    .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
    .toString()
    .trim()
    .split('\n');

class Node {
    constructor(val, priority) {
        this.val = val;
        this.priority = priority;
    }
}

class PriorityQueue {
    constructor() {
        this.arr = [];
    }
    enqueue(val, priority) {
        const newNode = new Node(val, priority);
        this.arr = [...this.arr, newNode];
        this.bubbleUp();
    }

    bubbleUp() {
        let index = this.arr.length - 1;
        const elem = this.arr[index];

        while (index > 0) {
            let parentIndex = Math.floor((index - 1) / 2);
            let parent = this.arr[parentIndex];

            if (elem.priority <= parent.priority) break;

            this.arr[parentIndex] = elem;
            this.arr[index] = parent;
            index = parentIndex;
        }
    }
    dequeue() {
        const max = this.arr[0];
        const end = this.arr.pop();

        if (this.arr.length > 0) {
            this.arr[0] = end;
            this.sinkDown();
        }

        return max;
    }

    sinkDown() {
        let parentIndex = 0;
        const { length } = this.arr;
        const parent = this.arr[parentIndex];

        while (true) {
            let leftChildIndex = 2 * parentIndex + 1;
            let rightChildIndex = 2 * parentIndex + 2;
            let leftChild = null;
            let rightChild = null;
            let swap = null;

            if (leftChildIndex < length) {
                leftChild = this.arr[leftChildIndex];
                if (leftChild.priority > parent.priority) {
                    swap = leftChildIndex;
                }
            }

            if (rightChildIndex < length) {
                rightChild = this.arr[rightChildIndex];
                if (
                    (swap === null && rightChild.priority > parent.priority) ||
                    (swap !== null && rightChild.priority > leftChild.priority)
                ) {
                    swap = rightChildIndex;
                }
            }

            if (swap === null) break;

            this.arr[parentIndex] = this.arr[swap];
            this.arr[swap] = parent;
            parentIndex = swap;
        }
    }
    isEmpty() {
        return this.arr.length === 0;
    }
}

// 서로 다른 두 정점 사에 간선이 한 개 아닌 두 개 일 수 있음
const [V, E] = input_n.split(' ').map(Number);
const start = Number(input_S);
const rel = ARR.map((v) => v.split(' ').map(Number));

const pq = new PriorityQueue();
const arr = Array.from(Array(V + 1), () => []);
const visited = Array.from(Array(V + 1), () => false);
const answer = Array.from(Array(V + 1), () => Infinity);

rel.forEach(([from, to, weight]) => {
    arr[from].push([to, weight]);
});

answer[start] = 0;
pq.enqueue(new Node(start, 0));

while (!pq.isEmpty()) {
    const curNode = pq.dequeue();

    if (visited[curNode.val]) continue;

    visited[curNode.val] = true;

    for (const [nextN, weight] of arr[curNode.val]) {
        if (answer[nextN] > answer[curNode.val] + weight) {
            answer[nextN] = answer[curNode.val] + weight;
            pq.enqueue(new Node(nextN, answer[nextN]));
        }
    }
}

console.log(
    answer
        .map((v) => (v === Infinity ? 'INF' : v))
        .slice(1)
        .join('\n')
);
