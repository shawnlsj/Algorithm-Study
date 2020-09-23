package util;

import java.util.LinkedList;
import java.util.Queue;

class CompleteBinaryTree {
    public static void main(String[] args) {
        Queue<Node> q = new LinkedList<>();

        CompleteBinaryTree binaryTree = new CompleteBinaryTree();

        //완전 이진트리에 하나씩 노드를 붙인다
        for (int i = 1; i <= 100; i++) {
            binaryTree.add(i);
        }
        //완전 이진트리의 내용을 출력한다
        //val = 현재 노드의 값 , left = 왼쪽 자식 노드의 값, right = 오른쪽 자식 노드의 값
        //leaf node = 말단 노드
        binaryTree.print();
    }

    Node rootNode;

    void print() {
        Queue<Node> q = new LinkedList<>();
        q.add(rootNode);
        while (!q.isEmpty()) {
            Node node = q.poll();
            System.out.print("val = " + node.value);
            if(node.leftChild != null) {
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
        
        //루트 노드가 없다면 -> value 값을 가진 루트 노드를 생성한다
        //루트 노드가 있다면 -> 루트 노드 정보와 입력할 value 값을 가지고 bfs를 수행한다
        if (rootNode == null) {
            rootNode = new Node(value);
        } else {
            bfs(rootNode, value);
        }
    }

    private void bfs(Node rootNode, int value) {
        //큐를 생성하고 루트 노드를 넣는다
        Queue<Node> q = new LinkedList<>();
        q.add(rootNode);

        // 1. 왼쪽 자식이 비어있거나
        // 2. 왼쪽 자식은 있지만 오른쪽 자식이 비어있는 노드를 찾을 때까지 무한 반복한다
        while (true) {
            Node node = q.poll(); // 큐에서 노드를 꺼낸다
            // 왼쪽 자식이 비어있다면 -> 새로운 노드를 추가한 뒤 리턴
            // 오른쪽 자식이 비어있다면 -> 새로운 노드를 추가한 뒤 리턴
            // 둘 다 비어있지 않다면 -> 왼쪽, 오른쪽 자식 순서대로 큐에 추가
            if (node.leftChild == null) {
                node.leftChild = new Node(value);
                return;
            } else if (node.rightChild == null) {
                node.rightChild = new Node(value);
                return;
            } else {
                q.add(node.leftChild);
                q.add(node.rightChild);
            }
        }
    }
    static class Node {
        int value;
        Node leftChild;
        Node rightChild;

        Node(int value) {
            this.value = value;
        }
    }
}

