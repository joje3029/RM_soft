package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dashbord {
	private String company_name; // 회사명 - Member
	private String tel; // 전화번호 - Member
	private String email; // 이메일 - Member
	private String address; // 주소 - Member
	private int member_num; // 사용인원 - Service
	private int storage_size; // 신청한 스토리지 크기 - Service
	private long service_type; // 구독한 서비스 형태 - Service
	private int price; // 현재 요금 - Service : service_type 보고 알려줘야함.
	private int Service_endDay ; // 사용신청 마감기간(D-day로 알려주기)  - Service
	private int stroage_user;// 스토리지 사용량  - Service + Storage 계산해서 보여줘야함.
	
	// 메소드로 계산해서 때려야지
	
}
