package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Member;

@Mapper
public interface UsrMemberDao {
	
	@Insert("""
			INSERT INTO `Member`
				SET company_name = #{companyNm}
					, tel = #{tel}
					, email =#{email}
					, address =#{address}
					, pw=#{pw}
			""")
	public void doJoin(String companyNm, String tel, String email, String address, String pw);
	
	@Select("""
			SELECT * 
				FROM `Member`
				WHERE email = #{email}
				AND pw =#{pw}
			""")
	public  Member getMember(String email, String pw);
	
}