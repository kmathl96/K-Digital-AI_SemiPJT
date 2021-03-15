package com.kd.classroom.dao;

import com.kd.classroom.bean.Student;

public interface StudentDAO {
	public void insertUser(Student stu) throws Exception;
	public Student queryUser(String id) throws Exception;
}