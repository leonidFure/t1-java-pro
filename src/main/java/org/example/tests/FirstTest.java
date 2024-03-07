package org.example.tests;

import org.example.annotations.*;

public class FirstTest {

	@BeforeSuite
	static void beforeAll() {
		System.out.println("start ...");
	}

	@AfterSuite
	static void afterAll() {
		System.out.println("end ...");
	}

	@BeforeTest
	static void beforeEach() {
		System.out.println("before test");
	}

	@AfterTest
	static void afterEach() {
		System.out.println("after test");
	}

	@Test(priority = 1)
	void test1() {
		System.out.println("test 1");
	}

	@Test
	void test2() {
		System.out.println("test 2");
	}

	@Test(priority = 1)
	void test3() {
		System.out.println("test 3");
	}

	@Test
	@CsvSource(params = "1, true, hello world")
	void megaTest(int a, boolean b, String c) {
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("c = " + c);
	}

	@Test
	@CsvSource(params = "1.2, с")
	void megaTest2(double a, char b) {
		System.out.println("a = " + a);
		System.out.println("b = " + b);
	}
}
