package com.kd.classroom.dao;

import com.kd.classroom.bean.Question;

public interface QuestionDAO {
	public void insertQuestion(Question que) throws Exception;
	public Question queryQuestion(int id) throws Exception;
}