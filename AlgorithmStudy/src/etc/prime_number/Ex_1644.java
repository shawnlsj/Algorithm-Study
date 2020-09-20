package etc.prime_number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Ex_1644 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int max = Integer.parseInt(br.readLine())+1;
		
		
		LinkedList<Integer> list = getPrimeNumList(max);
		double start = System.currentTimeMillis();
		
		Integer[] arr = new Integer[list.size()];
		int k=0;
		for(Integer a :list) {
			arr[k] = a;
			k++;
		}
		int count = 0;
		for(int i=0;i<list.size();i++) {
			int targetNum = max-1;
			for(int j=i;j>=0;j--) {
				targetNum -= arr[j];
				if(targetNum==0) {
					count++;
				}
				if(targetNum<0) {
					break;
				}
			}
		}
		
		//
		
//		int count = 0;
//		for(int i=0;i<list.size();i++) {
//			int targetNum = max-1;
//			for(int j=i;j>=0;j--) {
//				targetNum -= list.get(j);
//				if(targetNum==0) {
//					count++;
//				}
//				if(targetNum<0) {
//					break;
//				}
//			}
//		}
		System.out.println((System.currentTimeMillis()-start));
		System.out.print(count);
		
	}
	
	public static LinkedList<Integer> getPrimeNumList(int max) {
		
		LinkedList<Integer> list = new LinkedList<>();
		boolean[] a = new boolean[max];
		for(int i=2 ;i<=Math.sqrt(max);i++) {
			for(int j=i;j<a.length;j+=i) {
				if(a[i]==true) {
					break;
				}
				if(j==i) {
					continue;
				}
				if(j%i==0) {
					a[j] = true;
				}
			}
			
		}
		for(int i=2 ;i<a.length;i++) {
			if(a[i]!=true) {
				list.add(i);
			}
		}
		return list;
	}
	
public static LinkedList<Integer> getPrimeNumList2(int max) {
		
		LinkedList<Integer> list = new LinkedList<>();
		boolean[] a = new boolean[max];
		for(int i=2 ;i<=Math.sqrt(max);i++) {
			for(int j=i*i;j<a.length;j+=i) {
				
				
//				if(a[i]==true) {
//					System.out.println("i : "+i);
//					break;
//				}
//				if(j==i) {
//					continue;
//				}
//				if(j%i==0) {
//					a[j] = true;
//				}
			}
			
		}
		for(int i=2 ;i<a.length;i++) {
			if(a[i]!=true) {
				list.add(i);
			}
		}
		return list;
	}

	public static LinkedList<Integer> getPrimeNumList3(int num){
		LinkedList<Integer> list = new LinkedList<>();
        boolean[] arr = new boolean[num+1];    //true 이면 해당 인덱스 소수.
        arr[0] = arr[1] = false;
        for(int i=2; i<=num; i+=1) {
            arr[i] = true;
        }
      
        //2 부터 숫자를 키워가며 배수들을 제외(false 할당)
        for(int i=2; i*i<=num; i+=1) {
            for(int j=i*i; j<=num; j+=i) {
                arr[j] = false;        //2를 제외한 2의 배수 false
            }
        }
        for(int i=0; i<=num; i+=1) {
            if(true == arr[i]) {
            	list.add(i);
            }
        }
        return list;
    }
}
