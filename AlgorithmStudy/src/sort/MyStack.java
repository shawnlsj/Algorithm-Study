package sort;

import java.util.Stack;
import java.util.TreeMap;
interface IStack {
	boolean isEmpty();

	boolean isFull();

	void push(int item);

	char pop();

	char peek();

	void clear();
}

class MyStack implements IStack {
	Stack s;
	private int top;
	private int stackSize;
	private int[] stackArr;
	private int topIndex;

	MyStack() {
		this(10);
	}

	MyStack(int size) {
		stackSize = size;
		stackArr = new int[size];
		topIndex = -1;
	}

	public void push(int item) {
		if (topIndex < stackSize) {
			stackArr[++topIndex] = item;			
		} else {
			System.out.println("용량이 가득 찼습니다");
		}
	}

	public char pop() {
		if (topIndex >= 0) {
			char pop = (char) stackArr[topIndex];
			topIndex--;
			return pop;
		} else {
			System.out.println("스택이 비어있습니다");
			return 'X';
		}
	}

	public char peek() {
		if (topIndex >= 0) {
			return (char) stackArr[topIndex];
		} else {System.out.println("스택이 비어있습니다");
			return 'X';
		}
		
	}
	public void clear() {
		topIndex = -1;
	}
	public boolean isEmpty() {
		return topIndex < 0 ? true : false;
	}

	public boolean isFull() {
		return (topIndex == stackSize - 1) ? true : false;
	}

}
