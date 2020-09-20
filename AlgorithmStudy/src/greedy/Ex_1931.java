package greedy;

import java.util.*;
import java.io.*;
public class Ex_1931 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int meetingNum = Integer.parseInt(in.readLine());

		ArrayList<Integer[]> meetingPlan = new ArrayList<Integer[]>(meetingNum);
		ArrayList<Integer[]> schedule = new ArrayList<>();
		
		int a = 0;
		int b = 0;
		int cursor=0;
		
		for(int i=0;i<meetingNum;i++) {
			StringTokenizer stk = new StringTokenizer(in.readLine()," ");
			a= Integer.parseInt(stk.nextToken());
			b= Integer.parseInt(stk.nextToken());
			meetingPlan.add(new Integer[] {a,b});
		}
		meetingPlan.sort(new Comparator() {
			public int compare(Object o1, Object o2) {
				Integer[] a =(Integer[])o1;
				Integer[] b =(Integer[])o2;
				if(a[1]==b[1]) {
					return a[0]-b[0];
				}else {
					return a[1]-b[1];
				}
			}
		});
		System.out.println(Arrays.deepToString(meetingPlan.toArray()));
		schedule.add(meetingPlan.get(0));
		for(int i=1;i<meetingNum;i++) {
			if(schedule.get(cursor)[1]<=meetingPlan.get(i)[0]) {
				schedule.add(meetingPlan.get(i));
				cursor++;
			}   
		}
		System.out.println(Arrays.deepToString(schedule.toArray()));
		int result = schedule.size();
		System.out.print(result);
	}
}

//class MeetingComparator implements Comparator{
//
//	@Override
//	public int compare(Object o1, Object o2) {
//		Integer[] a =(Integer[])o1;
//		Integer[] b =(Integer[])o2;
//		return (a[0]+a[1])-(b[0]+b[1]);
//	}
//	
//}
