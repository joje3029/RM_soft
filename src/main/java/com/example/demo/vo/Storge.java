package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storge {
	private int storge_PK;
	private int company_ID;
	private String file_nm;
	private String file_save_nm;
	private String file_ext;
	private String file_path;
	private String file_size;
	private String first_reg_dt;
	private String update_reg_dt;
}
