package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrMemberController {
	Map<String, Object> data = new HashMap<>();
	//회원가입
	@RequestMapping("/usr/member/join")
	@ResponseBody
	public Map<String, Object> doJoin(String companyNm, String tel, String email, String address, String pw, String pwcheck ) { //key로 들어올 내용 : 회사명(companyNm), 전화번호(tel), 이메일(email), 주소(address), 비밀번호(pw), 비밀번호 확인(pwcheck)
		
		if(companyNm==null) {
			data.put("F-1", "회사명이 없습니다. 회원가입을 할 수 없습니다.");
		}else if(tel == null) {
			data.put("F-2", "전화번호가 없습니다. 회원가입을 할 수 없습니다.");
		}else if(email == null) {
			data.put("F-3", "이메일이 없습니다. 회원가입을 할 수 없습니다.");
		}else if(address == null) {
			data.put("F-4", "주소가 없습니다. 회원가입을 할 수 없습니다.");
		}else if(pw == null) {
			data.put("F-5", "비밀번호가 없습니다. 회원가입을 할 수 없습니다.");
		}else if(pwcheck == null) {
			data.put("F-6", "비밀번호 확인이 없습니다. 회원가입을 할 수 없습니다.");
		}else {
			data.put("result", "회원가입되었습니다.");
		}
		
		
		return data; // json으로 보내야함. -> 데이터는 json으로 오가니까.
		
	}
	
	//로그인
	@RequestMapping("/usr/member/loin")
	@ResponseBody
	public Map<String, Object> doLogin(String email, String pw) { //이메일(email) , 비밀번호(pw)
		
		if(email==null) {
			data.put("F-1", "이메일주소가 없습니다. 로그인 할 수 없습니다.");
		}else if(pw==null) {
			data.put("F-2", "비밀번호가 없습니다. 로그인 할 수 없습니다.");
		}else { // 여기는 키들이 null이 아닐 때
			
			// 여기서는 DB 가서 있는지 확인하고 데려와야지
			
			
			data.put("result", "로그인되었습니다.");
		}
		
		System.out.println("emial : "+email);
		System.out.println("pw : "+pw);
		
		
		return data ; // json으로 보내야함. -> 데이터는 json으로 오가니까.
		
	}
}
