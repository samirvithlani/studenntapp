package com.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bean.StudentBean;

@Service
public class JwtUserDetailService implements UserDetailsService {

	@Autowired
	StudentServices studentServices;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		StudentBean studentBean = studentServices.getStudentDetailByEmail(email);
		if (studentBean != null) {

			if (studentBean.getsEmail().equals(email)) {
				return new User(studentBean.getsEmail(), studentBean.getPassword(), new ArrayList<>());
			} else {
				throw new UsernameNotFoundException("User not found with username: " + email);
			}

		}
		throw new UsernameNotFoundException("User not found with username: " + email);
	}

}
