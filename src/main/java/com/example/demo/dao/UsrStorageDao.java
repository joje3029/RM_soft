package com.example.demo.dao;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsrStorageDao {
	
	@Insert("""
			INSERT INTO `Service`
				SET member_num = #{useMemberNum}
					, service_type = #{storageTypeNum}
					, start_datetime =#{serviceStartTime}
					, end_datetime =#{serviceEndTime}
			""")
	public void doServiceSub(String useMemberNum,  LocalDateTime serviceStartTime,
			LocalDateTime serviceEndTime, int storageTypeNum);
	
	
	
}