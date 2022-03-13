let index = {
	init: function() {
		$("#btn-save").on("click", () => { // function(){} 대신에 () =>{} 사용 이유는 this를 바인딩하기 위해서 사용하는 것
			this.save();
		});
		/*		$("#btn-login").on("click", () => { // function(){} 대신에 () =>{} 사용 이유는 this를 바인딩하기 위해서 사용하는 것
					this.login();
				}); */
		$("#btn-update").on("click", () => { // function(){} 대신에 () =>{} 사용 이유는 this를 바인딩하기 위해서 사용하는 것
			this.update();
		});
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		//console.log(data);

		// ajax 호출시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		// ajax 가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // 자바스크립트 오브젝트를 JSON 문자열로 만들어주기 위해 // http body데이터
			contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json 이라면) => javascript오브젝트로 변경
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	/* 	login: function() {
			//alert('user의 save함수 호출됨');
			let data = {
				username: $("#username").val(),
				password: $("#password").val()
			};
	
			$.ajax({
				// 회원가입 수행 요청
				type: "POST",
				url: "api/user/login",
				data: JSON.stringify(data), // 자바스크립트 오브젝트를 JSON 문자열로 만들어주기 위해 // http body데이터
				contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
				dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json 이라면) => javascript오브젝트로 변경
			}).done(function(resp) {
				alert("로그인이 완료되었습니다.");
				console.log(resp);
				location.href = "/";
			}).fail(function(error) {
				alert(JSON.stringify(error));
			});
		} */

	update: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		$.ajax({
			// 회원가입 수행 요청
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // 자바스크립트 오브젝트를 JSON 문자열로 만들어주기 위해 // http body데이터
			contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json 이라면) => javascript오브젝트로 변경
		}).done(function(resp) {
			alert("회원수정이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init();