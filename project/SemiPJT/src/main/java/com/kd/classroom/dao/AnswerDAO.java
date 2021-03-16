package com.kd.classroom.dao;

import java.util.List;

import com.kd.classroom.bean.Answer;

public interface AnswerDAO {
	public void insertAnswer(Answer ans) throws Exception;
	public Answer queryAnswer(int id) throws Exception;
	public List<Answer> queryAnswers() throws Exception;
	public int findNewId() throws Exception;
}