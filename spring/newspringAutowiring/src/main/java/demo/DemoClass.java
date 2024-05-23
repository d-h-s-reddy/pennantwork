package demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoClass {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring_application.xml");
		Users u = (Users) context.getBean("users");
		u.execute();

	}

}
