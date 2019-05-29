package com.saeyan.javabean;

public class MemberBeans {
	private String name;
	private String userId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public MemberBeans() {}
	
	public MemberBeans(String name, String userId) {
		super();
		this.name = name;
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "MemberBeans [name=" + name + ", userId=" + userId + "]";
	}
}
