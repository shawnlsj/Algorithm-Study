package etc.prime_number;

public class PrimeEx1 {
	public static void main(String[] args) {
		int k=2, j;
		while(true) {
			j=2;
			while(k%j!=0) {
				j++;
			}
			if(k==j) {
			System.out.printf("%d",k);
			System.out.println();
			}
			if(k<17)k++;
			else break;
		}
	}
}
