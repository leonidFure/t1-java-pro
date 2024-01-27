package org.example;

import org.example.runner.TestRunner;
import org.example.tests.FirstTest;

public class Main {
	public static void main(String[] args) {
		TestRunner.runTest(FirstTest.class);
	}
}