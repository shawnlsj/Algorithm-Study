package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class asd {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (br.readLine().equals("D")) {
            System.out.println("ok");
        } else {
            System.out.println("false");
        }
    }
}
