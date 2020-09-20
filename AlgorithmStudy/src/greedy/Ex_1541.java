package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

public class Ex_1541 {
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		String src = input.readLine();
		StringReader in = new StringReader(src);
		
		ArrayList<StringBuilder> list = new ArrayList<StringBuilder>();
		char c = ' ';
		boolean isMinus = false;
		int result= 0;
		
		
		outer: while (true) {
			StringBuilder str = new StringBuilder();
			if((c =(char)in.read()) == -1) {
				break outer;
			}
			if (isMinus) {
				str.append('-');
				
			} 
			while (((c == '-') || (c == '+')) != true) {
						str.append(c);
				if((c =(char)in.read()) == 65535) {
						list.add(str);
						break outer;
				}
			}
			list.add(str);
			if (c == '-') {
				isMinus = true;
			} else {
				continue;
			}
		}//�ٱ� while
		System.out.println("size:"+list.size());
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
				result += Integer.parseInt(list.get(i).toString());
		}
		System.out.println(result);
	}//main
}
