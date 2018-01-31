package com.apps.samples;

import javax.annotation.PostConstruct;

public class AnnotationsTest {

	public static void main(String[] args) {
		Test test = new Test();
	}

}

class Test {
	private int value;

	public Test() {
		System.out.println("chandu u got it.");
	}

	@PostConstruct
	public void test() {
		System.out.println("chandu u got it.");
	}

}
