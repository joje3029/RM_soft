package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UsrMemberService;
import com.example.demo.vo.Member;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {
	
	private UsrMemberService usrMemberService;
	private HttpSession session;
	
	UsrMemberController(UsrMemberService usrMemberService, HttpSession session){
		this.usrMemberService =usrMemberService;
		this.session =session;
	}
	
	Map<String, Object> data = new HashMap<>();
	//회원가입
	@RequestMapping("/usr/member/join")
	@ResponseBody
	public Map<String, Object> doJoin(String companyNm, String tel, String email, String address, String pw, String pwcheck ) { //key로 들어올 내용 : 회사명(companyNm), 전화번호(tel), 이메일(email), 주소(address), 비밀번호(pw), 비밀번호 확인(pwcheck)
		
//		data.isEmpty();
		
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
			// 위에서 null인곳이 없음을 확인하고 나면
			// 비밀번호와 비밀번호 확인 일치확인
			if(!pw.equals(pwcheck)) {
				data.put("F-7", "비밀번호가 일치하지 않습니다. 회원가입을 할 수 없습니다.");
			}else {
				//비밀번호 일치 시
				usrMemberService.doJoin(companyNm, tel, email, address, pw);
				
				data.put("result", "회원가입되었습니다.");
			}
		}
		
		return data; // json으로 보내야함. -> 데이터는 json으로 오가니까.
		
	}
	
	//로그인
	@RequestMapping("/usr/member/login")
	@ResponseBody
	public Map<String, Object> doLogin(String email, String pw) { //이메일(email) , 비밀번호(pw)
		
		if(email==null) {
			data.put("F-1", "이메일주소가 없습니다. 로그인 할 수 없습니다.");
		}else if(pw==null) {
			data.put("F-2", "비밀번호가 없습니다. 로그인 할 수 없습니다.");
		}else { // 여기는 키들이 null이 아닐 때
			
			// 여기서는 DB 가서 있는지 확인하고 데려와야지
			Member member = usrMemberService.getMember(email, pw);
			
			if(member==null) {
				data.put("F-3", "찾을수 없는 회원입니다. 회원가입해주세요.");
			}else {
				// 여기서 세션에 넣어야 그래야 로그인 안되면 서비스 이용 불가하게 가능하지.
				session.setAttribute("loginedMemberId", member.getCompany_ID());
				data.put("result", "로그인되었습니다.");				
			}
			
		}
		
		return data ; // json으로 보내야함. -> 데이터는 json으로 오가니까.
		
	}
	
	//임으로 쓰려고 만든 로그아웃
	@RequestMapping("/usr/member/logout")
	@ResponseBody
	public Map<String, Object> doLogOut() { //이메일(email) , 비밀번호(pw)
		
		session.removeAttribute("loginedMemberId");
		
		data.put("result", "로그아웃되었습니다.");				
		
		return data ; // json으로 보내야함. -> 데이터는 json으로 오가니까.
		
	}
}
