package com.istio.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class Role {

	private String roleName;
	
	private Long roleId;
	
	private List<AccessControl> accessControls;
	
	private List<AccessRight> accessRights;
	
}
