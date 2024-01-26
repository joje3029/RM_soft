package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Storge;

@Mapper
public interface UsrStorageDao {
	
	@Select("""
			SELECT * 
				FROM Storge
				WHERE company_ID =#{loginedMemberId}
			""")
	public List<Storge> getStorageinfo(int loginedMemberId);
	
}