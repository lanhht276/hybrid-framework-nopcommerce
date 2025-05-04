package javaBasic;

import org.testng.annotations.Test;

@Test
public class Topic_01_Data_Type {

	public void TC_01() {
		int a = 6;
		int b = 2;

		System.out.println(a + b + "= Tong");
		System.out.println(a - b + "= Hieu");
		System.out.println(a * b + "= Tich");
		System.out.println(a / b + "= Thuong");
	}

	public void TC_02() {
		float CD = 7.5f;
		float CR = 3.8f;

		System.out.println("Area = " + CD * CR);

	}

	public void TC_03() {
		String name = "Automation Testing";
		System.out.println("Hello " + name);
	}
}
