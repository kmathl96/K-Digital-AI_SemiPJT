package com.kd.classroom.dao;

import java.util.List;

import com.kd.classroom.bean.Comment;

public interface CommentDAO {
	public void insertComment(Comment com) throws Exception;
	public List<Comment> queryComments() throws Exception;
	public List<Comment> queryComments(int id) throws Exception;
	public List<Comment> queryComments(String w_id) throws Exception;
	public int findNewId() throws Exception;
}