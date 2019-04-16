package jspbook.ch03;

import java.util.Objects;

public class MemberBean {

	private int id;
	private String name;
	private String email;
	private String phone;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getId() {
		if(phone != null) {
			id = Objects.hash(name, email, phone);
			System.out.println(toString());
			return id;
		} return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	@Override
	public String toString() {
		return "MemberBean [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
}
