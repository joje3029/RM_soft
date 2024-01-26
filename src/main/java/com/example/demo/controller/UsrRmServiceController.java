package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UsrRmServiceService;
import com.example.demo.vo.Dashbord;
import com.example.demo.vo.RmService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrRmServiceController {
	
	private UsrRmServiceService usrRmServiceService;
	private HttpSession session;
	
	UsrRmServiceController(UsrRmServiceService usrRmServiceService, HttpSession session){
		this.usrRmServiceService =usrRmServiceService;
		this.session =session;
	}
	
	Map<String, Object> data = new HashMap<>();
	
	//서비스 구독신청
	@RequestMapping("/usr/RmService/applySub")
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
				usrRmServiceService.doServiceSub(useMemberNum, serviceType, serviceDate, storageTypeNum, (int)session.getAttribute("loginedMemberId"));
				
				data.put("result", "구독신청되었습니다.");
			}
			
		}
		
		return data; // json으로 보내야함. -> 데이터는 json으로 오가니까.
	}
	
	//서비스 사용현황(대시보드)
		@RequestMapping("/usr/RmService/showDashbord")
		@ResponseBody
		public Map<String, Object> showDashbord() {
			// 여기도 우선 로그인이 되어있어야함 -> 그래야 그 정보로 가져오지
			if(session.getAttribute("loginedMemberId")==null) {
				data.put("F-5", "로그인 후 이용해주세요.");
				return data;
			}
			
			//1. DB 가서 일단 지금꺼 가져오기
			Dashbord dashbord = usrRmServiceService.getDashbord((int)session.getAttribute("loginedMemberId"));
			
//			//2. 1. 성공하면 스토리지를 사용했다는 전제하에 얼마나 남았는지가 서비스에서 계산되서 나와야함.
			
			data.put("result", "대시보드 오케?");
			
			return data;
			
		}
	
	
	
	//서비스 기간연장 요청
		@RequestMapping("/usr/RmService/extenstionDate")
		@ResponseBody
		public Map<String, Object> doExtenstionDate(String extenstionDate) {
			// 여기도 우선 로그인이 되어있어야함 -> 그래야 그 정보로 가져오지
			if(session.getAttribute("loginedMemberId")==null) {
				data.put("F-5", "로그인 후 이용해주세요.");
				return data;
			}
			
			System.out.println("extenstionDate : "+extenstionDate);

			if(extenstionDate==null) {
				data.put("F-1", "연장 기간을 입력해주세요.");
			}else {
				// 기간이 입력됨. -> 이걸 들고 Db 가야지 -> 서비스에서 계산해서 Db에 업데이트하게 하면되것다.
				
				RmService rmService  = usrRmServiceService.doExtenstionDate((int)session.getAttribute("loginedMemberId") ,extenstionDate);
				
				 System.out.println("RmService : "+rmService.getEnd_datetime());
				 
				data.put("result", rmService.getEnd_datetime()+"까지 연장되었습니다.");
			}
			
			// 1. 받아야하는 키. 연장얼마나 할껀지 
			
			// 리턴 내용 : 서비스 연장이 되었다. 언제까지 연장되었는지 날짜. 토해내기
			return data;
		
		}
}
