package com.kd.classroom.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kd.classroom.bean.Comment;
import com.kd.classroom.bean.Question;

public class CommentDAOImpl implements CommentDAO {
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void insertComment(Comment com) throws Exception {
		sqlSession.insert("mapper.comment.insertComment", com);
	}
	
	@Override
	public List<Comment> queryComments() throws Exception {
		return sqlSession.selectList("mapper.comment.selectAllCommentList");
	}
	
	@Override
	public List<Comment> queryComments(int id) throws Exception {
		return sqlSession.selectList("mapper.comment.selectCommentListById", id);
	}
}