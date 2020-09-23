package util;

import java.util.LinkedList;
import java.util.Queue;

public class MyPriorityQueue {

    CompleteBinaryTree binaryTree = new CompleteBinaryTree();

    void offer(int value) {
        binaryTree.add(value);
        heapSort(binaryTree);
    }
    int poll() {

        // 현재 루트 노드의 value 값을 리턴 값으로 잡는다
        int returnVal = binaryTree.rootNode.value;

        // 제일 끝에 있는 노드를 찾고, 루트노드와 끝 노드를 바꾸어 준다
        CompleteBinaryTree.Node finalNode = searchFinalNode(binaryTree);
        swapNode(binaryTree.rootNode, finalNode);

        //끝 노드의 부모 노드가 있다면, 부모 노드와 끝 노드의 연결을 끊어준다
        if (finalNode.parent != null) {
            if (finalNode.parent.leftChild == finalNode) {
                finalNode.parent.leftChild = null;
            } else {
                finalNode.parent.rightChild = null;
            }
            heapSort(binaryTree);
        } else {
            finalNode = null;  //끝 노드의 부모 노드가 없다면 자기 자신에 null을 대입한다
        }
        return returnVal;
    }


    static class CompleteBinaryTree {
        CompleteBinaryTree.Node rootNode;

        void clearVisitedStatus() {
            //bfs 로 모든 노드를 방문하여 방문 여부를 false로 초기화한다
            Queue<Node> q = new LinkedList<>();
            q.offer(this.rootNode);
            while (!q.isEmpty()) {
                Node node = q.poll();
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
            //bfs로 모든 노드를 방문하며 아래의 내용을 출력한다
            //node 현재 노드의 값
            //left child 왼쪽 자식 노드의 값
            //right child 오른쪽 자식 노드의 값
            //<left node> 현재 노드가 말단 노드임을 의미
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
            buildMaxHeap(binaryTree);

            // 2. 방문한 적이 없는 final 노드를 찾는다
            finalNode = searchFinalNode(binaryTree);

            // 3. root 노드와 final 노드를 바꾼다
            swapNode(binaryTree.rootNode, finalNode);

            // 4. final 노드가 있던 위치에 방문 처리를 한다
            finalNode.visited = true;

            // 위의 과정을 root 노드와 final 노드가 같아질 때 까지반복 한다
        } while (binaryTree.rootNode != finalNode);
        binaryTree.clearVisitedStatus();
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
