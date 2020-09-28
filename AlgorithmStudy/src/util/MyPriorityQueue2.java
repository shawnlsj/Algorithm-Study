package util;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MyPriorityQueue2 {
    private int[] arr = new int[50];
    private int size = 0;
    private int finalNodeNum = 0;
    private int finalNodeCursor = 0;
    private int rootNodeNum = 1;

    private boolean isDesc;

    MyPriorityQueue2() {
        isDesc = false;
    }
    MyPriorityQueue2(boolean isDesc) {
        this.isDesc = isDesc;
    }

    public static void main(String[] args) {
        MyPriorityQueue2 myQueue = new MyPriorityQueue2();
        PriorityQueue queue = new PriorityQueue();

        double start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            queue.offer(i);
        }
        for (int i = 0; i < 1000000; i++) {
            queue.poll();
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            myQueue.offer(i);
        }
        for (int i = 0; i < 1000000; i++) {
            myQueue.poll();
        }
        System.out.println(System.currentTimeMillis() - start);

    }

    void print() {
        System.out.println(Arrays.toString(arr));
    }

    int size() {
        return size;
    }

    int getValue(int nodeNum) {
        return arr[nodeNum - 1];
    }

    int setValue(int nodeNum, int value) {
        return arr[nodeNum - 1] = value;
    }

    void offer(int value) {
        size++;
        finalNodeNum++;
        if (arr.length < size) {
            arr = sizeUp();
        }
        setValue(finalNodeNum, value); // 신입 사원을 말단에 앉히고
        if (isDesc) {
            maxSiftUp(); // 상사와 비교하여 더 크다면 승진 시킨다
        } else {
            minSiftUp();
        }
    }

    int poll() {
        int result = getValue(rootNodeNum);
        setValue(rootNodeNum, getValue(finalNodeNum)); // 말단에 있던 사원을 사장자리에 앉힌다
        setValue(finalNodeNum, 0); // 마지막 자리를 없애버린다
        size--;
        finalNodeNum--;
        if (isDesc) {
            maxSiftDown(); // 부하와 비교하여 자신이 더 작다면 부하와 바꿔준다
        } else {
            minSiftDown();
        }
        return result;
    }
    void minSiftUp() {
        //자신이 부모노드보다 작다면 교환
        int childNodeNum = finalNodeNum;
        int parentNodeNum = finalNodeNum / 2;

        while (childNodeNum != rootNodeNum &&
                getValue(childNodeNum) < getValue(parentNodeNum)) {
            swap(childNodeNum, parentNodeNum);
            parentNodeNum = parentNodeNum / 2;
            childNodeNum = childNodeNum / 2;
        }
    }
    void minSiftDown() {
        int parentNodeNum = rootNodeNum;

        while (true) {
            int min = getValue(parentNodeNum);
            int childNodeNum = -1;

            // 부모 노드의 왼쪽 자식이 존재하고, 해당 노드가 부모 보다 작다면 수행
            if (parentNodeNum * 2 <= finalNodeNum
                    && getValue(parentNodeNum * 2) < getValue(parentNodeNum)) {
                min = getValue(parentNodeNum * 2);
                childNodeNum = parentNodeNum * 2;
            }
            if (parentNodeNum * 2 + 1 <= finalNodeNum
                    && getValue(parentNodeNum * 2 + 1) < min) {
                childNodeNum = parentNodeNum * 2 + 1;
            }

            // 부모보다 큰 자식 노드를 못 찾았으면 리턴
            if (childNodeNum == -1) return;

            // 찾았으면 부모와 자식노드를 바꾼다
            swap(parentNodeNum, childNodeNum);

            //자식노드를 부모로 삼고 반복문의 처음으로 돌아간다
            parentNodeNum = childNodeNum;
        }
    }
    void maxSiftUp() {
        //자신이 부모노드보다 크다면 교환
        int childNodeNum = finalNodeNum;
        int parentNodeNum = finalNodeNum / 2;

        while (childNodeNum != rootNodeNum &&
                getValue(childNodeNum) > getValue(parentNodeNum)) {
            swap(childNodeNum, parentNodeNum);
            parentNodeNum = parentNodeNum / 2;
            childNodeNum = childNodeNum / 2;
        }
    }

    void maxSiftDown() {
        int parentNodeNum = rootNodeNum;

        while (true) {
            int max = getValue(parentNodeNum);
            int childNodeNum = -1;

            // 부모 노드의 왼쪽 자식이 존재하고, 그 자식이 부모 보다 크다면 수행
            if (parentNodeNum * 2 <= finalNodeNum
                    && getValue(parentNodeNum * 2) > getValue(parentNodeNum)) {
                max = getValue(parentNodeNum * 2);
                childNodeNum = parentNodeNum * 2;
            }
            if (parentNodeNum * 2 + 1 <= finalNodeNum
                    && getValue(parentNodeNum * 2 + 1) > max) {
                childNodeNum = parentNodeNum * 2 + 1;
            }

            // 부모보다 큰 자식 노드를 못 찾았으면 리턴
            if (childNodeNum == -1) return;
            
            // 찾았으면 부모와 자식노드를 바꾼다
            swap(parentNodeNum, childNodeNum);

            //자식노드를 부모로 삼고 반복문의 처음으로 돌아간다
            parentNodeNum = childNodeNum;
        }
    }

    int[] sizeUp() {
        int[] newArr = new int[arr.length + 50];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    void heapSort() {
        finalNodeCursor = finalNodeNum;
        while (finalNodeCursor > 0) {
            buildMaxHeap();
            swap(0, finalNodeCursor);
            finalNodeCursor--;
        }
    }

    void swap(int nodeNumA, int nodeNumB) {
        int tmp = 0;
        tmp = getValue(nodeNumA);
        setValue(nodeNumA, getValue(nodeNumB));
        setValue(nodeNumB, tmp);
    }


    void buildMaxHeap() {
        for (int i = finalNodeCursor / 2; i >= 0; i--) {
            maxHeapify(i); //마지막 노드의 부모노드부터 heapify 진행
        }
    }

    void maxHeapify(int nodeNum) {
        int max = getValue(nodeNum);
        int maxNodeNum = 0;

        if (nodeNum * 2 <= finalNodeCursor && getValue(nodeNum * 2) > max) {
            maxNodeNum = nodeNum * 2;
            max = getValue(max);

        }
        if (nodeNum * 2 + 1 <= finalNodeCursor && getValue(nodeNum * 2 + 1) > max) {
            maxNodeNum = nodeNum * 2 + 1;
        }
        swap(nodeNum, maxNodeNum);
    }
}
