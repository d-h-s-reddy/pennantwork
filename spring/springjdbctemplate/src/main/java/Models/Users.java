package Models;

import org.springframework.stereotype.Component;

@Component
public class Users {
	private String userName;
	private String passWord;

	// Getter for userName
	public String getUserName() {
		return userName;
	}

	// Setter for userName
	public void setUserName(String userName) {
		this.userName = userName;
	}

	// Getter for passWord
	public String getPassWord() {
		return passWord;
	}

	// Setter for passWord
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
