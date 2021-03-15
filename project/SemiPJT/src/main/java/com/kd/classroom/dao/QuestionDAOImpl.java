package com.kd.classroom.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.kd.classroom.bean.Question;

public class QuestionDAOImpl implements QuestionDAO {
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void insertQuestion(Question que) throws Exception {
		sqlSession.insert("mapper.question.insertQuestion", que);
		System.out.println("성공");
	}
	
	@Override
	public Question queryQuestion(int id) throws Exception {
		return sqlSession.selectOne("mapper.question.selectQuestion", id);
	}
}