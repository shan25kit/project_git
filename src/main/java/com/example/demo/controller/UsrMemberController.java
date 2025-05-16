package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import com.example.demo.util.Util;

@Controller
public class UsrMemberController {
	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/usr/member/join")
	public String join() {
		return "usr/member/join";
	}
	@PostMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name) {
		this.memberService.doJoin(loginId, loginPw, name);
		
		return Util.jsReplace("가입이 완료되었습니다.","/");
	}
	
	@PostMapping("/user/member/loginIdDupChk")
	public String loginIdDupChk(String loginId) {
		Member member = this.memberService.getMemberByLoginId(loginId);
		
		return loginId;
	}
}
