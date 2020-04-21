package com.istio.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.istio.demo.model.AccessRight;
import com.istio.demo.model.Role;

@RestController
public class RoleController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/findRole")
	public List<Role> search(Long companyId) throws InterruptedException {
		Role role = new Role();
		role.setRoleId(1L);
		role.setRoleName("Admin");
		// AccessRights
		role.setAccessRights(Arrays.asList(AccessRight.ADMIN_EDIT, AccessRight.REQUEST_EDIT));
		List<Role> roleList = new ArrayList<Role>();
		roleList.add(role);
		return roleList;
	}

}
