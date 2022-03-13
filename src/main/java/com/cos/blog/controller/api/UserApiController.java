package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;


//	@Autowired
//	private HttpSession session;

	@PostMapping("/auth/joinProc")
	public ResponseDTO<Integer> save(@RequestBody User user) { // username, password, email
		System.out.println("UserApiController : save 호출됨");
		userService.회원가입(user);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
	}

//	@PostMapping("/api/user/login")
//	public ResponseDTO<Integer> login (@RequestBody User user,  HttpSession session) {
//		System.out.println("UserApiController : login호출됨");
//		User principal = userService.로그인(user); // principal (접근주체)
//		
//		if (principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
//	}

//	@PutMapping("/user")
//	public ResponseDTO<Integer> update(@RequestBody User user, 
//			@AuthenticationPrincipal PrincipalDetail principal,
//			HttpSession session) { // requestbody 써야만 json 타입 받을 수 있다, 그외에는 key=value 형태만 받을 수 있다.
//		userService.회원수정(user);
//		// 여기서는 트랜잭션이 종료되어서 DB 값은 종료되었지만, 세션값은 변경되지 않은 상태
//		// 그래서 직접 세션값을 변경해야 함
//		Authentication authentication =
//				new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		securityContext.setAuthentication(authentication);
//		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
//		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
//	}
	@PutMapping("/user")
	public ResponseDTO<Integer> update(@RequestBody User user) { // requestbody 써야만 json 타입 받을 수 있다, 그외에는 key=value 형태만
																	// 받을 수 있다.
		userService.회원수정(user);
		
		// 여기서는 트랜잭션이 종료되어서 DB 값은 종료되었지만, 세션값은 변경되지 않은 상태
		// 그래서 직접 세션값을 변경해야 함
		// 세션 등록
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
}
