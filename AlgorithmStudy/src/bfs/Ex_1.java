package bfs;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import util.Queue;
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

		bfsTest(graph, 1);

	}

	static void bfsTest(Graph graph, Integer i) {
		Node rootNode = graph.nodeMap.get(i);
		bfs(rootNode);
	}

	static void bfs(Node rootNode) {
		Queue<Node> queue = new Queue<Node>();

		queue.add(rootNode);
		rootNode.isVisited = true;
		
		while(true) {
			if(queue.isEmpty()) return;

			Node removedNode = queue.remove();
			System.out.println(removedNode.getValue());
			
			// 빼낸 노드의 자식 노드 리스트를 오름차순 정렬
			removedNode.nodeList.sort(new Comparator<Node>() {
				public int compare(Node node, Node node2) {
					return node.getValue() - node2.getValue();
				}
			});

			// 빼낸 노드의 자식 노드를 전부 큐에 추가
			for (int i = 0; i < removedNode.nodeList.size(); i++) {
				Node childNode = removedNode.nodeList.get(i);
				if (!childNode.isVisited) {
					queue.add(childNode);
					childNode.isVisited = true;
				}
			}
			
		}//while
	} //bfs()
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