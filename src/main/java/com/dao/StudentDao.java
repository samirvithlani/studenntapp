package com.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.StudentBean;
import com.services.StudentRepository;
import com.services.StudentServices;

@Repository
public class StudentDao implements StudentServices {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public int addStudent(StudentBean studentBean) {

		int res = 0;
		try {
			studentRepository.save(studentBean);
			res = 1;
		} catch (Exception e) {

			res = -1;
		}

		return res;
	}

	@Override
	public List<StudentBean> getAllStudents() {

		return studentRepository.findAll();
	}

	@Override
	public int deleteStudent(int sId) {
		int res = 0;
		try {
			studentRepository.deleteById(sId);
			res = 1;
		} catch (Exception e) {
			res = -1;
		}

		return res;
	}

	@Override
	public Optional<StudentBean> getStudentById(int sId) {
		// TODO Auto-generated method stub
		return studentRepository.findById(sId);
	}

	@Override
	public int updateStudent(StudentBean studentBean, int sId) {

		//studentBean new Data
		int res = 0;
		// old will be there...
		try {
			//old record we have fetch from the database...
			//inside same object studentfromDb we are setting new values coming from studentBean
			StudentBean studentFromDb = studentRepository.getById(sId);
			//inside same object studentfromDb we are setting new values coming from studentBean
			studentFromDb.setsAge(studentBean.getsAge());
			studentFromDb.setsEmail(studentBean.getsEmail());
			studentFromDb.setsName(studentBean.getsName());
			studentFromDb.setsPassowrd(studentBean.getsPassowrd());
			studentFromDb.setsPhone(studentBean.getsPhone());
			studentRepository.save(studentFromDb);
			res = 0;
		} catch (Exception e) {
			System.out.println(".....");
			res = -1;
		}

		return res;

	}

}
