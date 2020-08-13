package greedy_study;

import java.util.LinkedHashSet;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		Set s = new LinkedHashSet();
		while(s.size()!=6) {
			int num = (int)(Math.random()*45+1);
			s.add(num);
		}
		System.out.println(s);
	}
}

class Person{
	String name;
	int age;
	
	public boolean equals(Object o, Object o2) {
		if(o instanceof Person && o2 instanceof Person) {
			Person p = (Person) o;
			Person p2 = (Person) o2;
			if((p.name==p2.name) && (p.age==p2.age)) {
				return true;
			} 
		}  
		return false;
		
	}
}
