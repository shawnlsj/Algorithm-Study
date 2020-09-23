package sort;

import java.util.LinkedList;
import java.util.Queue;

public class HeapSort {
    public static void main(String[] args) {
        CompleteBinaryTree binaryTree = new CompleteBinaryTree();

        for (int i = 0; i < 6; i++) {
            int x = (int) (Math.random() * 15);
            System.out.println("add : " + x);
            binaryTree.add(x);
        }
        System.out.println("====현재 트리 상태======");
        binaryTree.print();

        System.out.println("===최대 힙 수행 후===");
        buildMaxHeap(binaryTree); // 완전 이진트리를 최대 힙 구조로 만든다
        binaryTree.print();

        System.out.println("===오름차순 힙 정렬 후===");
        heapSort(binaryTree);
        binaryTree.print();
    }

    static void buildMaxHeap(CompleteBinaryTree binaryTree) {
        Queue<CompleteBinaryTree.Node> q = new LinkedList<>();
        q.add(binaryTree.rootNode);

        // 모든 노드에 대하여 heapify 를 수행
        while (!q.isEmpty()) {
            CompleteBinaryTree.Node node = q.poll();
            maxHeapify(node);
            if (node.leftChild != null) {
                q.add(node.leftChild);
            }
            if (node.rightChild != null) {
                q.add(node.rightChild);
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
        Queue<CompleteBinaryTree.Node> q = new LinkedList<>();
        q.offer(binaryTree.rootNode);
        CompleteBinaryTree.Node finalNode = null;

        while (!q.isEmpty()) {
            CompleteBinaryTree.Node node = q.poll();
            finalNode = node;
            if ((node.leftChild != null) && (node.leftChild.visited && false)) {
                q.add(node.leftChild);
            }
            if ((node.rightChild != null) && (node.rightChild.visited && false)) {
                q.add(node.rightChild);
            }
        }
        finalNode.visited = true;
        return finalNode;
    }

    private static void maxHeapify(CompleteBinaryTree.Node node) {
        // 왼쪽 자식과 먼저 비교하고 오른쪽 자식과도 비교한다
        if ((node.leftChild != null) && (node.value < node.leftChild.value)) {
            swapNode(node, node.leftChild);
        }
        if ((node.rightChild != null) && (node.value < node.rightChild.value)) {
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

    static class CompleteBinaryTree {
        Node rootNode;

        static class Node {
            int value;
            Node leftChild;
            Node rightChild;
            Node parent;
            boolean visited = false;

            Node(int value, Node parent) {
                this.value = value;
                this.parent = parent;
            }
        }

        void print() {
            Queue<Node> q = new LinkedList<>();
            q.add(rootNode);
            while (!q.isEmpty()) {
                Node node = q.poll();
                System.out.print("val = " + node.value);
                if (node.leftChild != null) {
                    System.out.print(" left = " + node.leftChild.value);
                    q.add(node.leftChild);
                }
                if (node.rightChild != null) {
                    System.out.print(" right = " + node.rightChild.value);
                    q.add(node.rightChild);
                }
                if ((node.leftChild == null) && (node.rightChild == null)) {
                    System.out.print(" <leaf node> ");
                }
                System.out.println();
            }

        }

        void add(int value) {
            if (rootNode == null) {
                rootNode = new Node(value, null);
            } else {
                bfs(rootNode, value);
            }
        }

        private void bfs(Node rootNode, int value) {
            Queue<Node> q = new LinkedList<>();
            q.add(rootNode);
            while (true) {
                Node node = q.poll();
                if (node.leftChild == null) {
                    node.leftChild = new Node(value, node);
                    return;
                } else if (node.rightChild == null) {
                    node.rightChild = new Node(value, node);
                    return;
                } else {
                    q.add(node.leftChild);
                    q.add(node.rightChild);
                }
            }
        }

    }




}
