package com.haowu.test.customtag;

import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author 20190023
 * @Date 2020/3/24 19:43
 * @Version 1.0
 **/
@Data
public class User {
	private String id;
	private String userName;
	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
