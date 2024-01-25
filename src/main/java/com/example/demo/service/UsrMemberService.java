package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UsrMemberDao;
import com.example.demo.vo.Member;

@Service
public class UsrMemberService {
	
	private UsrMemberDao usrMemberDao;

	UsrMemberService(UsrMemberDao usrMemberDao){
		this.usrMemberDao = usrMemberDao;
	}
	
	public void doJoin(String companyNm, String tel, String email, String address, String pw) {
		usrMemberDao.doJoin(companyNm, tel, email, address, pw);
	}

	public Member getMember(String email, String pw) {
		return usrMemberDao.getMember(email,pw);
	}

	

}
