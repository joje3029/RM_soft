package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UsrStorageService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrStorageController {
	
	private UsrStorageService usrStorageService;
	private HttpSession session;
	
	UsrStorageController(UsrStorageService usrStorageService, HttpSession session){
		this.usrStorageService =usrStorageService;
		this.session =session;
	}
	
	Map<String, Object> data = new HashMap<>();
	
	//서비스 구독신청
	@RequestMapping("/usr/Storage/applySub")
	@ResponseBody
	public Map<String, Object> doApplySub(String useMemberNum, String serviceType, String serviceDate) {
		// 필수정보 : 사용인원, 서비스형태(Basic=1, Standard=2,Premium=3),구독기간
		// 부가정보 :  어느놈이 신청했나 => 로그인한 세션의 번호가 들어가면 되겠다.
		
		// 세션에 있는지 확인. 있으면 진행 없으면 로그인하고 오라고 해야함.
		if(session.getAttribute("loginedMemberId")==null) {
			data.put("F-5", "로그인 후 이용해주세요.");
			return data;
		}
		
		//1. 필수정보는 없으면 없다고 해야함
		if(useMemberNum==null) {
			data.put("F-1", "사용인원이 없습니다. 사용인원을 입력해주세요.");
		}else if(serviceType == null) {
			data.put("F-2", "서비스 타입이 없습니다. 서비스타입을 선택해주세요().");
		}else if(serviceDate == null) {
			data.put("F-3", "서비스 종료일이 없습니다. 서비스 종료일을 설정해주세요.");
		}else { 
			// 위에서 null인곳이 없음을 확인(필수정보)
			// 저장을 일단 시도-> 성공하면 세션에 로그인정보 넣는거까지 하기
			
			// 스토리지 타입 문자에서 구분 번호로 변경
			int storageTypeNum = 0;
			if(serviceType.equals("Basic")) {
				storageTypeNum=1;
			}else if(serviceType.equals("Standard")) {
				storageTypeNum=2;
			}else if(serviceType.equals("Premium")) {
				storageTypeNum=3;
			}else {
				storageTypeNum=-1;
			}
			
			
			if(storageTypeNum==-1) {
				data.put("F-4", "알맞은 서비스 종류가 아닙니다. Basic, Standard, Premium 중 알 맞은 것으로 넣어주세요.");
			}else {
				//2. 있으면 서비스에 테이블에 넣기 -> 사용인원, 서비스형태(번호로 서비스에서변형), 서비스 신청일자(지금 날짜), 서비스 종료일자 -> 기간기간으로 자동 +계산, 사용가능 스토리지
				usrStorageService.doServiceSub(useMemberNum, serviceType, serviceDate, storageTypeNum, (int)session.getAttribute("loginedMemberId"));
				
				data.put("result", "구독신청되었습니다.");
			}
			
		}
		
		
		return data; // json으로 보내야함. -> 데이터는 json으로 오가니까.
		
	}
	
	
}
