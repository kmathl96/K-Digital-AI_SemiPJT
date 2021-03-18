package com.kd.classroom.dao;

import java.util.List;

import com.kd.classroom.bean.Scrap;

public interface ScrapDAO {
	public void insertScrap(Scrap scr) throws Exception;
	public void deleteScrap(Scrap scr) throws Exception;
	public Scrap queryScrap(Scrap scr) throws Exception;
	public List<Scrap> queryScraps(String id) throws Exception;
}