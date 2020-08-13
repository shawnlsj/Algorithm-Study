package greedy_study;

import java.util.Arrays;
import java.util.stream.*;

public class asd {
	public static void main(String[] args) {
		Box2.print((BoxInterface)new Box2());
	}
}

interface BoxInterface{
	
}

class Box{

	
}

class Box2 extends Box implements BoxInterface{
	
	public static void print(Object o) {
		System.out.println("obj print");
	}
	
	static void print(Box b) {
		System.out.println("box print");
	}
	
	static void print(BoxInterface b) {
		System.out.println("boxInter print");
	}
}
