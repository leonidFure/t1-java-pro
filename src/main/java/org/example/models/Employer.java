package org.example.models;

public record Employer(String name, int age, Position position) {
	public enum Position {
		PROGRAMMER,
		ENGINEER,
		STUDENT
	}
}
