package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UsrRmServiceDao;
import com.example.demo.vo.Member;
import com.example.demo.vo.RmService;

@Service
public class UsrRmServiceService {
	
	private UsrRmServiceDao usrRmServiceDao;

	UsrRmServiceService(UsrRmServiceDao usrRmServiceDao){
		this.usrRmServiceDao = usrRmServiceDao;
	}

	public void doServiceSub(String useMemberNum, String serviceType, String serviceDate, int storageTypeNum, int loginedMemberId) {
		
		int serviceMonth =Integer.parseInt(serviceDate);
		
		// 사용기간: 현재 날짜와 시간 + 개월 수()
		LocalDateTime ServiceStartTime = LocalDateTime.now(); // 현재 날짜와 시간
		
		// 종료일자 : currentTime + serviceMonth
		LocalDateTime serviceEndTime = ServiceStartTime.plus(serviceMonth, ChronoUnit.MONTHS);
		
		
		//Dao에 보내기
		usrRmServiceDao.doServiceSub(useMemberNum, ServiceStartTime, serviceEndTime, storageTypeNum, loginedMemberId);
	}

	public RmService doExtenstionDate(int loginedMemberId, String extenstionDate) {
		// 1. loginedMemberId 가지고 Db가서 Service 테이블의 기간 update
		usrRmServiceDao.doExtenstionDate(loginedMemberId, extenstionDate);
		
		// 2. 그후 select로 update한 내용을 가져옴. -> 그래야 어디까지 연장되었는지 나오지. 
		return usrRmServiceDao.getLastUpdate(loginedMemberId);
		
	}

	
	

	

}
