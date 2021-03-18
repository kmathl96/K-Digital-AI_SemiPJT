package com.kd.classroom.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kd.classroom.bean.Scrap;

public class ScrapDAOImpl implements ScrapDAO {
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void insertScrap(Scrap scr) throws Exception {
		sqlSession.insert("mapper.scrap.insertScrap", scr);
	}
	
	@Override
	public void deleteScrap(Scrap scr) throws Exception {
		sqlSession.delete("mapper.scrap.deleteScrap", scr);
	}
	
	@Override
	public Scrap queryScrap(Scrap scr) throws Exception {
		return sqlSession.selectOne("mapper.scrap.selectScrap", scr);
	}
	
	@Override
	public List<Scrap> queryScraps(String id) throws Exception {
		return sqlSession.selectList("mapper.scrap.selectScrapListById", id);
	}
}