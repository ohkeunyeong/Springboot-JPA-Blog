package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data
@RestController
public class HttpController테스트 {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member(1, "ssar", "1234", "email");
//		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build(); //build 어노테이션 사용 시
		System.out.println(TAG + "getter: " + m.getId());
		m.setId(5000);
		System.out.println(TAG + "setter: " + m.getId());
		return "lombok test 완료";
	}
	
	// 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {	//id=1&username=ssar&password=1234&email=ssar@naver.com
//		System.out.println(TAG + "getter: " + m.getId());
//		m.setId(5000);
//		System.out.println(TAG + "setter: " + m.getId());
////		Member m1 = new Member(id, username, password, email); //member 클래스의 AllArgsConstructor 로 인해 모든 생성자가 만들어져서 에러남
//		Member m2 = new Member();  // NoArgsConstructor로 인해 가능
		return "get 요청: " + m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post") // text/plain, application/json
	public String postTest(@RequestBody Member m) { // MessageConverter (스프링부트) 가 일을해서 처리함
		return "post 요청: " + m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청: " + m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}

}
