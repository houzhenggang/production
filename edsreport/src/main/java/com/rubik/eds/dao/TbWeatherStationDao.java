package com.rubik.eds.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.support.dao.CommonDao;

@Repository
public class TbWeatherStationDao extends CommonDao<TbWeatherStation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -901223903056930393L;

	@Override
	protected String getNameSpace() {
		return "station";
	}

	/**
	 * 分页查询站点列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbWeatherStation> getStationByRowBounds(RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", null, rowBounds);
	}
	
	/**
	 * 查询用户具有的权限的站点List
	 * @param userId
	 * @return
	 */
	public List<TbWeatherStation> getStationListByUserId(Map<Object, Object> userIdMap){
		return getSqlSession().selectList(getNameSpace()+".findStationByUserId", userIdMap);
	}
}
