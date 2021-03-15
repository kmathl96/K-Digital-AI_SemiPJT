package com.kd.classroom.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kd.classroom.bean.Student;

public class TeacherDAOImpl implements StudentDAO {
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void insertUser(Student stu) throws Exception {
		sqlSession.insert("mapper.student.insertUser", stu);
	}
	
	@Override
	public Student queryUser(String id) throws Exception {
		return sqlSession.selectOne("mapper.student.selectUser", id);
	}
}