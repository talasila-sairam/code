package com.apps.samples;

import java.util.HashSet;
import java.util.Set;

public class Student {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public static void main(String[] args) {
		Student std=new Student();
		Set<Student> set=new HashSet();
		Set<Student> set1=new HashSet();
		set.add(std);
		System.out.println(set.contains(std));
		std.setName("chandu");
		set.add(std);
		System.out.println(set.contains(std));
	}
}
