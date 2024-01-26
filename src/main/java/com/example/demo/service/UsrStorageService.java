package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UsrStorageDao;

@Service
public class UsrStorageService {
	
	private UsrStorageDao usrStorageDao;

	UsrStorageService(UsrStorageDao usrStorageDao){
		this.usrStorageDao = usrStorageDao;
	}

	public void doServiceSub(String useMemberNum, String serviceType, String serviceDate, int storageTypeNum, int loginedMemberId) {
		
		int serviceMonth =Integer.parseInt(serviceDate);
		
		// 사용기간: 현재 날짜와 시간 + 개월 수()
		LocalDateTime ServiceStartTime = LocalDateTime.now(); // 현재 날짜와 시간
		
		// 종료일자 : currentTime + serviceMonth
		LocalDateTime serviceEndTime = ServiceStartTime.plus(serviceMonth, ChronoUnit.MONTHS);
		
		
		//Dao에 보내기
		usrStorageDao.doServiceSub(useMemberNum, ServiceStartTime, serviceEndTime, storageTypeNum, loginedMemberId);
	}

	
	

	

}
