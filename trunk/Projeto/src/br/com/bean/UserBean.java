package br.com.bean;

public class UserBean {
	private String userName;
	private String name;
	private String lastName;
	private TestSpring testSpring;
	
	public TestSpring getTestSpring() {
		return testSpring;
	}
	public void setTestSpring(TestSpring testSpring) {
		this.testSpring = testSpring;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
