package demo;

import org.springframework.stereotype.Component;

@Component
public class Student {
	Student() {
		System.out.println("this is student class");
	}

	public void demo() {
		System.out.println("i am a student");
	}
}
