package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Ex_1931Test {
	public static void main(String[] args) {
		while (true) {
			int m = (int)(Math.random()*11+1);
			LinkedList<Integer[]> list = getList(m);
			if (answer(m,list) != rightAnswer(m,list)) {
				System.out.println("no");
				break;
			}
		}
	}

	static int answer(int meetingNum, LinkedList<Integer[]> list) {

		ArrayList<Integer[]> meetingPlan = new ArrayList<Integer[]>(meetingNum);
		ArrayList<Integer[]> schedule = new ArrayList<>();

		
		for(int i=0; i<list.size();i++) {
			meetingPlan.add(list.get(i));
		}
		meetingPlan.sort(new Comparator() {
			public int compare(Object o1, Object o2) {
				Integer[] a = (Integer[]) o1;
				Integer[] b = (Integer[]) o2;
				return (a[0] + a[1]) - (b[0] + b[1]);
			}
		});
		System.out.println("meetingPlan :"+Arrays.deepToString(meetingPlan.toArray()));

		int cursor = 0;
		schedule.add(meetingPlan.get(0));
		for (int i = 1; i < meetingNum; i++) {
			if (schedule.get(cursor)[1] <= meetingPlan.get(i)[0]) {
				schedule.add(meetingPlan.get(i));
				cursor++;
			}
		}
	//	System.out.println(Arrays.deepToString(schedule.toArray()));
		int result = schedule.size();
		System.out.print("result :" +result +"!=");
		return result;
	}

	static int rightAnswer(int n, LinkedList<Integer[]> list) {
		int[][] times = new int[n][2]; // 0 : start 1 : finish

		int min = -1;
		for (int i = 0; i < n; i++) {
			times[i][0] = list.get(i)[0];
			times[i][1] = list.get(i)[1];
		}

		
		Arrays.sort(times, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (times[i][0] >= min) {
				min = times[i][1];
				cnt++;
			}
		}
		
		System.out.println("cnt : " +cnt);
		System.out.println("cnt :"+Arrays.deepToString(times));
		return cnt;
	}
	
	static LinkedList<Integer[]> getList(int m){
		LinkedList<Integer[]> list = new LinkedList<Integer[]>();
		
		for(int i=0;i<m;i++) {
			int a = (int)(Math.random()*(Math.pow(2, 3)-1));
			int b = (int)(Math.random()*(Math.pow(2, 3)-1)); 
			if(a>b) {
			i--;
			continue;
			}
			list.add(new Integer[] {a,b});
		}
		return list;
				
	}
}
