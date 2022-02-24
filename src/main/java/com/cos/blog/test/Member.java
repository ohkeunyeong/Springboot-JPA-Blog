package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@Data //getter, setter 합친거
//@AllArgsConstructor //모든필드를 쓰는 생성자
//@RequiredArgsConstructor //final 붙은것들에 대한 생성자를 만들어줌
@NoArgsConstructor //빈 생성자
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder //값을넣을때 생성자의 순서를 안지켜도 된다.
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
