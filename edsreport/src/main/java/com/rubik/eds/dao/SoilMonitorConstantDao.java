package com.rubik.eds.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.SoilMonitorConstant;
import com.rubik.support.dao.CommonDao;

@Repository
public class SoilMonitorConstantDao extends CommonDao<SoilMonitorConstant> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3012465032349689601L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "soilConstant";
	}

	/**
	 * 分页查询站点列表
	 * @param rowBounds
	 * @return
	 */
	public List<SoilMonitorConstant> getSoilConstantByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", userIdMap, rowBounds);
	}
	
	/**
	 * 查询用户具有的权限的站点List
	 * @param userId
	 * @return
	 */
	public List<SoilMonitorConstant> getSoilConstantListByUserId(Map<Object, Object> userIdMap){
		return getSqlSession().selectList(getNameSpace()+".findSoilConstantByUserId", userIdMap);
	}
}
