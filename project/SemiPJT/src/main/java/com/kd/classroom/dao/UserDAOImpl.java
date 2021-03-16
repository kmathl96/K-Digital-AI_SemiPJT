package com.kd.classroom.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kd.classroom.bean.User;

public class UserDAOImpl implements UserDAO {
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public User queryUser(String id) throws Exception {
		return sqlSession.selectOne("mapper.user.selectUser", id);
	}
	
	@Override
	public void insertStudent(User stu) throws Exception {
		sqlSession.insert("mapper.user.insertStudent", stu);
	}
	
	@Override
	public User queryStudent(String id) throws Exception {
		return sqlSession.selectOne("mapper.user.selectStudent", id);
	}
	
	@Override
	public void insertTeacher(User tch) throws Exception {
		sqlSession.insert("mapper.user.insertTeacher", tch);
	}
	
	@Override
	public User queryTeacher(String id) throws Exception {
		return sqlSession.selectOne("mapper.user.selectTeacher", id);
	}
	
	@Override
	public void changeProfileImg(User user) throws Exception {
		sqlSession.update("mapper.user.updateProfileImage",user);
	}
}