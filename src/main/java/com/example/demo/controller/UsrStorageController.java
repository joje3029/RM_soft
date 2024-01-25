package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UsrStorageService;

@Controller
public class UsrStorageController {
	
	private UsrStorageService usrStorageService;
	
	UsrStorageController(UsrStorageService usrStorageService){
		this.usrStorageService =usrStorageService;
	}
	
	Map<String, Object> data = new HashMap<>();
	
	//서비스 구독신청
	@RequestMapping("/usr/Storage/applySub")
	@ResponseBody
	public Map<String, Object> doApplySub(String useMemberNum, String serviceType, String serviceDate) {
		// 필수정보 : 사용인원, 서비스형태(Basic=1, Standard=2,Premium=3),구독기간
		// 부가정보 :  어느놈이 신청했나 => 로그인한 세션의 번호가 들어가면 되겠다.
		
		//1. 필수정보는 없으면 없다고 해야함
		
		
		//2. 있으면 서비스에 테이블에 넣기 -> 사용인원, 서비스형태(번호로 서비스에서변형), 서비스 신청일자(지금 날짜), 서비스 종료일자 -> 기간기간으로 자동 +계산, 사용가능 스토리지
		
		
		
		
		return data; // json으로 보내야함. -> 데이터는 json으로 오가니까.
		
	}
	
	
}
