package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.StudentBean;
import com.services.StudentServices;
import com.util.JwtRequest;
import com.util.ResponseManager;

@RestController
@CrossOrigin
public class StudentController {

	@Autowired
	StudentServices studentServices;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(value = "/student")
	public ResponseEntity<?> addStudent(@RequestBody StudentBean studentBean) {

		ResponseManager responseManager = new ResponseManager();

		String encodedPassword = passwordEncoder.encode(studentBean.getPassword());
		System.out.println(encodedPassword);
		studentBean.setPassword(encodedPassword);

		int res = studentServices.addStudent(studentBean);
		if (res > 0) {

			responseManager.setStatus("success");
			responseManager.setCode(HttpStatus.CREATED);
			responseManager.setObject(null);
			return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.CREATED);
		}

		responseManager.setStatus("failed");
		responseManager.setCode(HttpStatus.EXPECTATION_FAILED);
		responseManager.setObject(null);
		return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping(value = "/student")
	public ResponseEntity<?> listStudents() {

		ResponseManager responseManager = new ResponseManager();
		List<StudentBean> students = studentServices.getAllStudents();
		if (students.size() > 0) {

			responseManager.setStatus("success");
			responseManager.setCode(HttpStatus.OK);
			responseManager.setObject(students);
			return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.OK);
		}

		responseManager.setStatus("failed");
		responseManager.setCode(HttpStatus.EXPECTATION_FAILED);
		responseManager.setObject(null);
		return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.EXPECTATION_FAILED);

	}

	@DeleteMapping(value = "/student/{sId}")
	public ResponseEntity<?> deleteStudent(@PathVariable("sId") int sId) {
		ResponseManager responseManager = new ResponseManager();
		int res = studentServices.deleteStudent(sId);
		if (res > 0) {

			responseManager.setStatus("success");
			responseManager.setCode(HttpStatus.OK);
			responseManager.setObject(null);
			return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.OK);
		}

		responseManager.setStatus("failed");
		responseManager.setCode(HttpStatus.EXPECTATION_FAILED);
		responseManager.setObject(null);
		return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.EXPECTATION_FAILED);

	}

	@GetMapping(value = "/student/{id}")
	public ResponseEntity<?> getStudentByid(@PathVariable("id") int sId) {

		ResponseManager responseManager = new ResponseManager();

		Optional<StudentBean> stu = studentServices.getStudentById(sId);
		if (stu.isPresent()) {
			StudentBean students = stu.get();
			students.setId(students.getId());
			students.setName(students.getName());
			students.setsEmail(students.getsEmail());
			students.setsPhone(students.getsPhone());
			students.setsAge(students.getsAge());
			students.setPassword(students.getPassword());

			responseManager.setCode(HttpStatus.OK);
			responseManager.setStatus("success");
			responseManager.setObject(students);
			return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.OK);

		}

		responseManager.setCode(HttpStatus.EXPECTATION_FAILED);
		responseManager.setStatus("failed");
		responseManager.setObject(null);
		return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.EXPECTATION_FAILED);

	}

	@PutMapping(value = "/student/{sid}")
	public ResponseEntity<?> updateStudentById(@PathVariable("sid") int sid, @RequestBody StudentBean studentBean) {

		ResponseManager responseManager = new ResponseManager();
		int res = studentServices.updateStudent(studentBean, sid);
		System.out.println(res);
		if (res != -1) {

			responseManager.setStatus("success");
			responseManager.setCode(HttpStatus.CREATED);
			responseManager.setObject(null);
			return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.CREATED);
		}

		responseManager.setStatus("failed");
		responseManager.setCode(HttpStatus.EXPECTATION_FAILED);
		responseManager.setObject(null);
		return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.EXPECTATION_FAILED);

	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> loginStudents(@RequestBody JwtRequest jwtRequest) {

		StudentBean studentBean = studentServices.loginStudent(jwtRequest);
		ResponseManager responseManager = new ResponseManager();
		if (studentBean != null) {

			responseManager.setCode(HttpStatus.OK);
			responseManager.setObject(studentBean);
			responseManager.setStatus("user found !");

			return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.OK);
		}
		responseManager.setCode(HttpStatus.EXPECTATION_FAILED);
		responseManager.setObject(null);
		responseManager.setStatus("user not found !");

		return new ResponseEntity<ResponseManager>(responseManager, HttpStatus.EXPECTATION_FAILED);

	}

}
