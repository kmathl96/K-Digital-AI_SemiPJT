package com.kd.classroom.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kd.classroom.bean.Answer;

public class AnswerDAOImpl implements AnswerDAO {
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void insertAnswer(Answer ans) throws Exception {
		sqlSession.insert("mapper.answer.insertAnswer", ans);
	}
	
	@Override
	public Answer queryAnswer(int id) throws Exception {
		return sqlSession.selectOne("mapper.answer.selectAnswer", id);
	}
	
	@Override
	public List<Answer> queryAnswers() throws Exception {
		return sqlSession.selectList("mapper.answer.selectAllAnswerList");
	}
}