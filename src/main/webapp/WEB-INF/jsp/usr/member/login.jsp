<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="로그인" />

<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<script>
		const joinFormChk = function(form) {
			form.loginId.value = form.loginId.value.trim();
			form.loginPw.value = form.loginPw.value.trim();
			form.loginPwChk.value = form.loginPwChk.value.trim();
			form.name.value = form.name.value.trim();
			
			if (form.loginId.value.length == 0) {
			alert('아이디는 필수 입력 정보입니다');
			form.loginId.focus();
			
			
			return false;
			}

			if (form.loginPw.value.length == 0) {
			alert('비밀번호는 필수 입력 정보입니다');
			form.loginId.focus();
			return false;
			}

			if (form.name.value.length == 0) {
			alert('이름은 필수 입력 정보입니다');
			form.loginId.focus();
			return false;
			}

			if (form.loginPw.value != form.loginPwChk.value) {
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.value = '';
			form.loginPwChk.value = '';
			form.loginPw.focus();
			return false;
			}

		return true;
	}
	
		const loginIdDupChk = function(el){
			el.value = el.value.trim();
		}
			$ajax({
				url : '/user/member/loginIdDupChk',
			
				type : 'get',
				data : {
					loginId : "form.loginId.value"
				},
				dayaType : 'json',
				success : function(data){
					$('#loginIdDupChkMsg').html(`\${data}`);
						
					}	
				error: function(xhr, status, error){
					console.log(error);
				}
			})
			
		
</script>

<section class="mt-8">
	<div class="container mx-auto flex justify-center">
		<form action="doJoin" method="post"
			onsubmit="return joinFormChk(this);">
			<div class="table-box">
				<table class="w-50">
					<tr>
						<td class="bg-yellow-200 h-20 font-bold">회원 정보</td>
					</tr>
					<tr>
						<td><input class="border w-80 h-10" name="loginId"
							type="text" placeholder="아이디" onblur="loginIdDupChk(this)"/>
							<div id="loginIdDupChkMsg" class="mt-2 text-sm h-5 text-left"></div></td>

					</tr>
					<tr>
						<td><input class="border w-80 h-10" name="loginPw"
							type="password" placeholder="비밀번호 확인" /></td>
					</tr>
					<tr>
						<td><input class="border w-80 h-10" name="loginPwChk"
							type="password" placeholder="비밀번호 확인" /></td>
					</tr>
					<tr>
						<td><input class="border w-80 h-10" name="name" type="text"
							placeholder="이름" /></td>
					</tr>
					<td class="bg-white hover:bg-yellow-200"><button
							class="submitBtn w-32 h-15 font-bold">가입</button></td>
				</table>
			</div>
		</form>

	</div>
</section>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>