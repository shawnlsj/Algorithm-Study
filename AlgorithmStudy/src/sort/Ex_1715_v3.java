package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 직접 구현한 우선순위 큐 버전
public class Ex_1715_v3 {
    static int n;
    static int answer;
    static int merge;

    static MyPriorityQueue1715 q = new MyPriorityQueue1715();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 카드 뭉치 수

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            q.offer(x);
        }

        answerMethod();

        System.out.println(answer);
    }
    static void answerMethod() {

        int start1 = 0;
        int start2 = 15000;
        int start3 = 30000;

        int start4 = 45000;
        int start5 = 60000;
        int start6 = 75000;

        int start7 = 90000;

        for (int i = start1; i < start2; i++) {
            if(i+1 == n) return;
            merge += q.poll();
            merge += q.poll();
            q.offer(merge);
            answer += merge;
        }

        for (int i = start2; i < start3; i++) {
            if(i+1 == n) return;
            merge += q.poll();
            merge += q.poll();
            q.offer(merge);
            answer += merge;
        }
        for (int i = start3; i < start4; i++) {
            if(i+1 == n) return;
            merge += q.poll();
            merge += q.poll();
            q.offer(merge);
            answer += merge;
        }
        for (int i = start4; i < start5; i++) {
            if(i+1 == n) return;
            merge += q.poll();
            merge += q.poll();
            q.offer(merge);
            answer += merge;
        }
        for (int i = start5; i < start6; i++) {
            if(i+1 == n) return;
            merge += q.poll();
            merge += q.poll();
            q.offer(merge);
            answer += merge;
        }
        for (int i = start6; i < start7; i++) {
            if(i+1 == n) return;
            merge += q.poll();
            merge += q.poll();
            q.offer(merge);
            answer += merge;
        }
        for (int i = start7; i < 100001; i++) {
            if(i+1 == n) return;
            merge += q.poll();
            merge += q.poll();
            q.offer(merge);
            answer += merge;
        }


    }
}


class MyPriorityQueue1715 {
    private int[] arr = new int[50];
    private int size = 0;
    private int finalNodeNum = 0;
    private int rootNodeNum = 1;

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

        minSiftUp();
    }

    int poll() {
        int result = getValue(rootNodeNum);
        setValue(rootNodeNum, getValue(finalNodeNum)); // 말단에 있던 사원을 사장자리에 앉힌다
        setValue(finalNodeNum, 0); // 마지막 자리를 없애버린다

        size--;
        finalNodeNum--;

        minSiftDown();
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

    int[] sizeUp() {
        int[] newArr = new int[arr.length + 50];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    void swap(int nodeNumA, int nodeNumB) {
        int tmp = 0;
        tmp = getValue(nodeNumA);
        setValue(nodeNumA, getValue(nodeNumB));
        setValue(nodeNumB, tmp);
    }
}


