package com.istio.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class User {

	Long userId;

	String userName;
	
	Long companyId;

	List<Role> roles;

	Tentant tentant;

}
