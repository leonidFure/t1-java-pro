package org.example.tests;

import org.example.annotations.*;

public class SecondTest {
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
		System.out.println("test 2.1");
	}
}
