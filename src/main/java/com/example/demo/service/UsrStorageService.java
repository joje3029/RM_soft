package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UsrStorageDao;

@Service
public class UsrStorageService {
	
	private UsrStorageDao usrStorageDao;

	UsrStorageService(UsrStorageDao usrStorageDao){
		this.usrStorageDao = usrStorageDao;
	}
	

	

}
