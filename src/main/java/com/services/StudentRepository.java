package com.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.StudentBean;

//we need to autowired in dao....
@Repository
public interface StudentRepository extends JpaRepository<StudentBean, Integer> {

}
