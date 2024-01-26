package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RmService {
	private int Service_Pk;
	private int company_ID;
	private int member_num;
	private int service_type;
	private String start_datetime;
	private String end_datetime;
}
