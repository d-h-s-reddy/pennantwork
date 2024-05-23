package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Users {

	private String username;

	Users() {
		System.out.println("This is users class");
	}

	@Autowired
	private Student s;

	public void execute() {
		s.demo();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
