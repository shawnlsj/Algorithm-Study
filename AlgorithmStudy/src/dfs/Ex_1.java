package dfs;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Ex_1 {
    public static void main(String[] args) {

        Graph graph = new Graph();

        graph.add(1, 2);
        graph.add(1, 3);
        graph.add(1, 8);
        graph.add(3, 4);
        graph.add(3, 5);
        graph.add(4, 5);
        graph.add(2, 7);
        graph.add(7, 8);
        graph.add(7, 6);

        dfsTest(graph, 1);

    }

    static void dfsTest(Graph graph, int a) {
        Node rootNode = graph.nodeMap.get(a);

        dfs(rootNode);

    }

    static void dfs(Node node) {
        if (node.isVisited == true) {
            return;
        }

        System.out.println(node.getValue());
        node.isVisited = true;


        node.nodeList.sort(new Comparator<Node>() {
            public int compare(Node node, Node node2) {
                return node.getValue() - node2.getValue();
            }
        });

        for (int i = 0; i < node.nodeList.size(); i++) {
            dfs(node.nodeList.get(i));
        }

    }

}

class Graph {

    HashMap<Integer, Node> nodeMap;

    Graph() {
        nodeMap = new HashMap<Integer, Node>();
    }

    public void add(int a, int b) {

        if (nodeMap.get(a) == null) {
            Node node = new Node(a);
            nodeMap.put(a, node);
        }

        if (nodeMap.get(b) == null) {
            Node node2 = new Node(b);
            nodeMap.put(b, node2);
        }

        Node nodeA = nodeMap.get(a);

        Node nodeB = nodeMap.get(b);

        nodeA.add(nodeB);
        nodeB.add(nodeA);

    }
}

class Node {

    private int value;
    boolean isVisited = false;

    LinkedList<Node> nodeList;

    Node(int value) {
        nodeList = new LinkedList<Node>();
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node add(Node node) {
        nodeList.add(node);
        return this;
    }

}