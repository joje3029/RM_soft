package com.example.demo.dao;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Storage;

@Mapper
public interface UsrStorageDao {
	
	@Insert("""
			INSERT INTO `Service`
				SET member_num = #{useMemberNum}
					, company_ID = #{loginedMemberId}
					, service_type = #{storageTypeNum}
					, start_datetime =#{serviceStartTime}
					, end_datetime =#{serviceEndTime}
			""")
	public void doServiceSub(String useMemberNum,  LocalDateTime serviceStartTime,
			LocalDateTime serviceEndTime, int storageTypeNum, int loginedMemberId);
	
	@Update("""
			UPDATE Service
			SET end_datetime = (SELECT DATE_ADD(end_datetime, INTERVAL #{extenstionDate} MONTH)
			                    FROM Service
			                    WHERE company_ID = #{loginedMemberId})
			WHERE company_ID = #{loginedMemberId};
			""")
	public void doExtenstionDate(int loginedMemberId, String extenstionDate);
	
	@Select("""
			SELECT * 
				FROM Service 
				WHERE company_ID = #{loginedMemberId};
			""")
	public Storage getLastUpdate(int loginedMemberId);
	
	
	
}