package com.kd.classroom.dao;

import com.kd.classroom.bean.User;

public interface UserDAO {
	public User queryUser(String id) throws Exception;
	public void insertStudent(User stu) throws Exception;
	public User queryStudent(String id) throws Exception;
	public void insertTeacher(User tch) throws Exception;
	public User queryTeacher(String id) throws Exception;
	public void changeProfileImg(User user) throws Exception;
}