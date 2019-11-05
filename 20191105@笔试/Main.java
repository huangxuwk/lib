package com.dl.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static int dealBottle(int num) {
		int result = 0;
		int temp = 0;
		int tmp = 0;
		
		while (num / 3 >= 1) {
			temp = (int) num / 3;
			tmp += temp;
			num -= 3 * temp;
			num += temp;
		}
		if (num == 2) {
			result++;
		}
		
		return result + tmp;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<Integer> numList = new ArrayList<>();
		int num = 0;
		
		if (in.hasNextInt()) {
			num = in.nextInt();
		}
		while (num != 0) {
			numList.add(num);
			if (in.hasNextInt()) {
				num = in.nextInt();
			}
		}
		for (int i = 0; i < numList.size(); i++) {
			System.out.println(Main.dealBottle(numList.get(i)));
		}
		
		in.close();
	}

}