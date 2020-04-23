package com.istio.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class Tentant {

	Long companyId;

	String companyName;
	
	List<User> users;

}
