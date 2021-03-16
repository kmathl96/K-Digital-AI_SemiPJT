package com.kd.classroom.dao;

import java.util.List;

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
	}
	
	@Override
	public Question queryQuestion(int id) throws Exception {
		return sqlSession.selectOne("mapper.question.selectQuestion", id);
	}
	
	@Override
	public List<Question> queryQuestions() throws Exception {
		return sqlSession.selectList("mapper.question.selectAllQuestionList");
	}
	
	@Override
	public int updateQuestionHits(int id) throws Exception {
		return sqlSession.update("mapper.question.updateQuestionHits", id);
	}
	
	@Override
	public int findNewId() throws Exception {
		try {
			int max = sqlSession.selectOne("mapper.question.selectMaxId");
			return max+1;
		} catch (Exception e) {
			return 1;
		}
	}
}