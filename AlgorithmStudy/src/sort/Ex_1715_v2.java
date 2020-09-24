package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

// 직접 구현한 우선순위 큐 버전
public class Ex_1715_v2 {
    static int n;
    static int answer;

    public static void main(String[] args) throws Exception {

        int[] arr = new int[99999];
        int[] arr2;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 123456);
        }
        arr2 = Arrays.copyOf(arr, arr.length);

        MyPriorityQueue2 q = new MyPriorityQueue2();
        q.arr = arr;
        q.size = arr.length;
        q.cursor = arr.length - 1;

        double start = System.currentTimeMillis();
        q.heapSort();
        System.out.println(System.currentTimeMillis() - start);

        MyPriorityQueue3 q3 = new MyPriorityQueue3();
        q3.arr = arr2;
        q3.size = arr2.length;
        q3.cursor = arr2.length;

        start = System.currentTimeMillis();
        q3.heapSort();
        System.out.println(System.currentTimeMillis() - start);

        for (int i = 0; i < q.arr.length; i++) {
            if (q.arr[i] != q3.arr[i]) {
                System.out.println("실패");
            }
        }
        System.out.println("종료");
    }
}

class MyPriorityQueue2 {
    int[] arr = {4, 7, 15, 0, 5, 8, 1, 12, 2, 3};
    int size = 10;
    int cursor = 9;
    private int finalNodeIndex = -1;

    int size() {
        return size;
    }

    void offer(int i) {
        size++;
        cursor++;
        if (arr.length < size) {
            arr = sizeUp();
        }
        arr[cursor] = i;
        heapSort();
    }

    int[] sizeUp() {
        int[] newArr = new int[arr.length + 50];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    int poll() {
        int result = arr[0];
        arr[0] = arr[cursor];
        arr[cursor] = 0;

        size--;
        cursor--;
        heapSort();
        return result;
    }

    void heapSort() {
        finalNodeIndex = cursor;
        while (finalNodeIndex > 0) {
            buildMaxHeap();
            //    System.out.println("--- 힙 구조 완성 ---");
            //     System.out.println(Arrays.toString(arr));
            swap(0, finalNodeIndex);
            //   System.out.println("인덱스 0과" + finalNodeIndex+"를 바꿔준다");
            finalNodeIndex--;
        }
    }

    void swap(int indexA, int indexB) {
        int tmp = 0;
        tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    void buildMaxHeap() {
        //   System.out.println("finalNodeIndex/2 = " + finalNodeIndex/ 2);
        for (int i = finalNodeIndex / 2; i >= 0; i--) {
            maxHeapify(i); //마지막 노드의 부모노드부터 heapify 진행
        }
    }

    void maxHeapify(int index) {
        int max = arr[index];
        int maxIndex = 0;

        //   System.out.println("------");
        //   System.out.println(Arrays.toString(arr));
        //   System.out.println("heapyfy 진행할 index = " + index);

        //   System.out.println("finalNodeIndex = " + finalNodeIndex);

        if (index * 2 <= finalNodeIndex && arr[index * 2] > max) {
            //     System.out.println(" 왼쪽이 자식이 더 큼 index * 2  > max");
            maxIndex = index * 2;
            max = arr[index * 2];
        }
        if (index * 2 + 1 <= finalNodeIndex && arr[index * 2 + 1] > max) {
            //     System.out.println(" 오른쪽 자식이 더 큼 index * 2 + 1  > max");
            maxIndex = index * 2 + 1;
        }
        // System.out.println("maxIndex = " + maxIndex);
        //  System.out.println("인덱스"+ index + " 와 " + maxIndex + "를 스왑한다");
        swap(index, maxIndex);
    }
}

class MyPriorityQueue3 {
    int[] arr = {4, 7, 15, 0, 5, 8, 1, 12, 2, 3};
    int size = 10;
    int cursor = 10;
    int rootIndex = 1;
    private int finalNodeIndex = -1;

    void heapSort() {
        finalNodeIndex = cursor;
        while (finalNodeIndex > 1) {
            buildMaxHeap();
      //      System.out.println("--- 힙 구조 완성 ---");
      //      System.out.println(Arrays.toString(arr));
            swap(rootIndex, finalNodeIndex);
      //      System.out.println("루트와" + finalNodeIndex + "를 바꿔준다");
            finalNodeIndex--;
        }
    }

    void swap(int indexA, int indexB) {
        int tmp = 0;
        indexA -= 1;
        indexB -= 1;
        tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    void buildMaxHeap() {
        //   System.out.println("finalNodeIndex/2 = " + finalNodeIndex/ 2);
        for (int i = finalNodeIndex / 2; i >= 1; i--) {
            maxHeapify(i); //마지막 노드의 부모노드부터 heapify 진행
        }
    }

    void maxHeapify(int index) {

        int max = arr[index - 1];
        int maxIndex = -1;

//        System.out.println("------");
//        System.out.println(Arrays.toString(arr));
//        System.out.println("heapyfy 진행할 index = " + index);
//
//        System.out.println("finalNodeIndex = " + finalNodeIndex);

        if (index * 2 <= finalNodeIndex && arr[(index * 2) - 1] > max) {
    //        System.out.println(" 왼쪽이 자식이 더 큼 index * 2  > max");
            maxIndex = index * 2;
            max = arr[index * 2 - 1];
        }
        if (index * 2 + 1 <= finalNodeIndex && arr[(index * 2 + 1) - 1] > max) {
     //       System.out.println(" 오른쪽 자식이 더 큼 index * 2 + 1  > max");
            maxIndex = index * 2 + 1;
        }
     //   System.out.println("maxIndex = " + maxIndex);
        if (maxIndex == -1) {
     //       System.out.println("패스");
            return;
        }

      //  System.out.println("인덱스" + index + " 와 " + maxIndex + "를 스왑한다");
        swap(index, maxIndex);
        maxHeapify(maxIndex);
    }
}