package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
	private int company_ID;
	private String company_name;
	private String tel;
	private String email;
	private String address;
	private String pw;
}
