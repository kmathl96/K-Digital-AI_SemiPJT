package com.kd.classroom.dao;

import java.util.List;

import com.kd.classroom.bean.Question;

public interface QuestionDAO {
	public void insertQuestion(Question que) throws Exception;
	public Question queryQuestion(int id) throws Exception;
	public List<Question> queryQuestions() throws Exception;
	public int updateQuestionHits(int id) throws Exception;
}