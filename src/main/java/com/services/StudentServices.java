package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bean.StudentBean;
import com.util.JwtRequest;

@Service
public interface StudentServices {

	public int addStudent(StudentBean studentBean);

	public List<StudentBean> getAllStudents();

	public int deleteStudent(int sId);

	public Optional<StudentBean> getStudentById(int sId);

	public int updateStudent(StudentBean studentBean,int sId);
	
	public StudentBean loginStudent(JwtRequest jwtRequest);
	
	public StudentBean getStudentDetailByEmail(String email);
}
