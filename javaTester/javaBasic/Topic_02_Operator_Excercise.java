package javaBasic;

import org.testng.annotations.Test;

@Test
public class Topic_02_Operator_Excercise {

	public void TC_01() {
		String name = "Thanh";
		int age = 20;

		System.out.println("After 15 years, age of " + name + " will be " + age);
	}

	public void TC_02() {
		int a = 3;
		int b = 4;

		a = a + b; // 7
		b = a - b; // 3
		a = a - b; // 4

		System.out.println("After swapping then a = " + a + " , b = " + b);

	}

	public void TC_03() {
		int a = 3;
		int b = 4;
		boolean c;

		c = (a > b) ? true : false;
		System.out.println(c);

	}
}
