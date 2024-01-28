package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UsrRmServiceDao;
import com.example.demo.dao.UsrStorageDao;
import com.example.demo.vo.Dashbord;
import com.example.demo.vo.RmService;
import com.example.demo.vo.Storge;

@Service
public class UsrRmServiceService {
	
	private UsrRmServiceDao usrRmServiceDao;
	private UsrStorageDao usrStorageDao;

	UsrRmServiceService(UsrRmServiceDao usrRmServiceDao, UsrStorageDao usrStorageDao){
		this.usrRmServiceDao = usrRmServiceDao;
		this.usrStorageDao = usrStorageDao;
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

	public Dashbord getDashbord(int loginedMemberId) {
		
		//1. 현재 로그인한 사람의 Service 테이블에서 정보 가져오기
		RmService rmService = usrRmServiceDao.getServiceinfo(loginedMemberId);
		
		//2. 현재 로그인한 사람의 Storage 테이블에서 정보 다 가져오기 -> 파일 크기 다 더해서 서비스 종류마다 모수에서 빼기 / 파일 리스트 다 보여줄수 있고 / 다 더해서 얼마만큼 총합 보여줄수 있음
		List<Storge> storages = usrStorageDao.getStorageinfo(loginedMemberId);
		
		// file_size합산
		long sumFileSize = 0;
		
		for(Storge storage : storages) {
			
			// file_size들 숫자로 환산.
			String fileSize =storage.getFile_size();
			String fileUnit = fileSize.substring(fileSize.length()-2, fileSize.length()); // 단위 확인
			String fileStrNum = fileSize.substring(0,fileSize.length()-2); // 단위 앞 숫자 str
			
			int fileNum=Integer.parseInt(fileStrNum); // 숫자 str -> int
			
			System.out.println("fileNum : "+fileNum);
			
			// 단위 변환 : KB 기준으로 하기
			if(fileUnit.equals("KB")) {
				sumFileSize += fileNum;
			}else if(fileUnit.equals("MB")) {
				// 1MB = 1024KB
				fileNum =fileNum*1024;
				sumFileSize += fileNum;
			}else if(fileUnit.equals("GB")) {
				// 1GB = 1024KB(1KB*1024) *1024
				fileNum =fileNum*1024*1024;
				sumFileSize += fileNum;
				
			}else if(fileUnit.equals("TB")) {
				// 1TB = 1024GB * 1024
				fileNum =fileNum*1024*1024*1024;
				sumFileSize += fileNum;
			}
			
			System.out.println("sumFileSize : "+sumFileSize);
		}
		
		System.out.println("sumFileSize 최종 : "+sumFileSize);
		
		
		//3. Dashbord 객체 형태에 맞춰서 조립해서 return
		
		
		return null;
	}

	
	

	

}
