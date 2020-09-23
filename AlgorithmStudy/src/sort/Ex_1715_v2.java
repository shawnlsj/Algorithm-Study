package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// 직접 구현한 우선순위 큐 버전
public class Ex_1715_v2 {
    static int n;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 카드 뭉치 수

        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            q.add(x);
        }

        for (int i = 0; i < n - 1; i++) {
            int merge = 0;
            merge += q.poll();
            merge += q.poll();
            q.add(merge);
            answer += merge;
        }
        System.out.println(answer);
    }
}

class MyPriorityQueue {

    CompleteBinaryTree binaryTree = new CompleteBinaryTree();

    void offer(int value) {
        binaryTree.add(value);
        binaryTree.clearVisitedStatus();
        heapSort(binaryTree);
    }

    int poll() {
        int returnVal = binaryTree.rootNode.value;
        binaryTree.clearVisitedStatus();
        CompleteBinaryTree.Node finalNode = searchFinalNode(binaryTree);
        swapNode(binaryTree.rootNode, finalNode);

        //부모 노드가 있다면, 현재 노드가 왼쪽 자식인지 오른쪽 자식인지 판별 후 null 대입
        if (finalNode.parent != null) {
            if (finalNode.parent.leftChild == finalNode) {
                finalNode.parent.leftChild = null;
            } else {
                finalNode.parent.rightChild = null;
            }
            heapSort(binaryTree);
        } else {
            finalNode = null;  //부모 노드가 없다면 자기 자신을 null로 초기화
        }
        return returnVal;
    }


    static class CompleteBinaryTree {
        CompleteBinaryTree.Node rootNode;

        void clearVisitedStatus() {
            Queue<CompleteBinaryTree.Node> q = new LinkedList<>();
            q.offer(this.rootNode);
            while (!q.isEmpty()) {
                CompleteBinaryTree.Node node = q.poll();
                node.visited = false;
                if (node.leftChild != null) {
                    q.offer(node.leftChild);
                }
                if (node.rightChild != null) {
                    q.offer(node.rightChild);
                }
            }
        }

        static class Node {
            int value;
            CompleteBinaryTree.Node leftChild;
            CompleteBinaryTree.Node rightChild;
            CompleteBinaryTree.Node parent;
            boolean visited = false;

            Node(int value, CompleteBinaryTree.Node parent) {
                this.value = value;
                this.parent = parent;
            }
        }

        void print() {
            java.util.Queue<CompleteBinaryTree.Node> q = new LinkedList<>();
            q.offer(rootNode);
            while (!q.isEmpty()) {
                CompleteBinaryTree.Node node = q.poll();
                System.out.print("node = " + node.value);
                if (node.leftChild != null) {
                    System.out.print(" left child = " + node.leftChild.value);
                    q.offer(node.leftChild);
                }
                if (node.rightChild != null) {
                    System.out.print(" right child= " + node.rightChild.value);
                    q.offer(node.rightChild);
                }
                if ((node.leftChild == null) && (node.rightChild == null)) {
                    System.out.print(" <leaf node> ");
                }
                System.out.println();
            }

        }

        void add(int value) {
            if (rootNode == null) {
                rootNode = new CompleteBinaryTree.Node(value, null);
            } else {
                bfs(rootNode, value);
            }
        }

        private void bfs(CompleteBinaryTree.Node rootNode, int value) {
            Queue<CompleteBinaryTree.Node> q = new LinkedList<>();
            q.offer(rootNode);
            while (true) {
                CompleteBinaryTree.Node node = q.poll();
                if (node.leftChild == null) {
                    node.leftChild = new CompleteBinaryTree.Node(value, node);
                    return;
                } else if (node.rightChild == null) {
                    node.rightChild = new CompleteBinaryTree.Node(value, node);
                    return;
                } else {
                    q.offer(node.leftChild);
                    q.offer(node.rightChild);
                }
            }
        }
    }

    static void buildMaxHeap(CompleteBinaryTree binaryTree) {
        java.util.Queue<CompleteBinaryTree.Node> q = new LinkedList<>();
        q.offer(binaryTree.rootNode);

        // 모든 노드에 대하여 heapify 를 수행
        while (!q.isEmpty()) {
            CompleteBinaryTree.Node node = q.poll();
            if (node.visited) continue;
            maxHeapify(node);
            if (node.leftChild != null && (node.visited == false)) {
                q.offer(node.leftChild);
            }
            if (node.rightChild != null && (node.visited == false)) {
                q.offer(node.rightChild);
            }
        }
    }


    static void heapSort(CompleteBinaryTree binaryTree) {
        CompleteBinaryTree.Node finalNode;
        do {
            // 1. 최대 힙 구조를 만든다
            // 2. 방문한 적이 없는 final 노드를 찾는다
            // 3. root 노드와 final 노드를 바꾼다
            // 4. final 노드가 있던 위치에 방문 처리를 한다
            // 위의 과정을 root 노드와 final 노드가 같아질 때 까지반복 한다
            buildMaxHeap(binaryTree);
            finalNode = searchFinalNode(binaryTree);
            swapNode(binaryTree.rootNode, finalNode);
        } while (binaryTree.rootNode != finalNode);
    }


    static CompleteBinaryTree.Node searchFinalNode(CompleteBinaryTree binaryTree) {
        java.util.Queue<CompleteBinaryTree.Node> q = new LinkedList<>();
        q.offer(binaryTree.rootNode);
        CompleteBinaryTree.Node finalNode = null;

        while (!q.isEmpty()) {
            CompleteBinaryTree.Node node = q.poll();
            finalNode = node;
            if ((node.leftChild != null) && (node.leftChild.visited == false)) {
                q.offer(node.leftChild);
            }
            if ((node.rightChild != null) && (node.rightChild.visited == false)) {
                q.offer(node.rightChild);
            }
        }
        finalNode.visited = true;
        return finalNode;
    }

    private static void maxHeapify(CompleteBinaryTree.Node node) {
        // 왼쪽 자식과 먼저 비교하고 오른쪽 자식과도 비교한다
        if ((node.leftChild != null) && (node.value < node.leftChild.value) && (node.leftChild.visited == false)) {
            swapNode(node, node.leftChild);
        }
        if ((node.rightChild != null) && (node.value < node.rightChild.value) && (node.rightChild.visited == false)) {
            swapNode(node, node.rightChild);
        }

        // 부모 노드와 비교하여 자리바꿈이 이동하면
        // 또, 그의 부모와 비교한다
        if (node.parent != null) {
            CompleteBinaryTree.Node parent = node.parent;
            do {
                if (node.value > parent.value) {
                    swapNode(node, parent);
                } else {
                    return; // 부모와 자리바꿈이 일어나지 않았다면 해당 노드에 대한 heapfy 를 종료한다
                }
                parent = parent.parent;
            } while (parent != null); // 자리 바꿈이 일어났고, 부모 노드가 존재한다면 반복문을 이어서 수행한다
        }
    }

    static void swapNode(CompleteBinaryTree.Node node1, CompleteBinaryTree.Node node2) {
        int tmp = node1.value;
        node1.value = node2.value;
        node2.value = tmp;
    }
}
