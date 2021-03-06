package com.yoan.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

import com.yoan.repository.EmployeeRepository;
import com.yoan.service.Login;

import com.yoan.model.Employee;

@Service
public class LoginImpl implements Login {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		int employeeId = Integer.parseInt(username);
		UserBuilder builder = null;		 
		try
		{
			Employee employee = employeeRepository.findById(employeeId).get();
			if (employee != null) 
			{
				builder = org.springframework.security.core.userdetails.User.withUsername(username);
				builder.password(employee.getPassword());
				builder.roles(employee.getDesignation());
			} 
			else 
			{
				throw new UsernameNotFoundException("User not found.");
			}	
		 }
		 catch(UsernameNotFoundException e)
		 {
			 System.out.println(e);
		 }
		 return builder.build();
	}

}
