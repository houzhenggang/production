package com.rubik.eds.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbWaterMonitor;
import com.rubik.support.dao.CommonDao;

@Repository
public class TbWaterMonitorDao extends CommonDao<TbWaterMonitor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5457082986728957589L;

	@Override
	protected String getNameSpace() {
		return "water";
	}

	/**
	 * 根据用户ID查询所有记录
	 * @param userId
	 * @return
	 */
	public List<TbWaterMonitor> findAll(Map<Object, Object> useIdMap){
		return getSqlSession().selectList(getNameSpace()+".findAll", useIdMap);
	}
	
	/**
	 * 分页查询水体监测数据列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbWaterMonitor> getWaterByRowBounds(Map<Object, Object> useIdMap, RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", useIdMap, rowBounds);
	}
	/**
	 * 查询已登记的水体名称
	 * @param rowBounds
	 * @return
	 */
	public List<TbWaterMonitor> getWaterNames(Map<Object, Object> useIdMap){
		return getSqlSession().selectList(getNameSpace()+".findWaterName", useIdMap);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public List<TbWaterMonitor> getWaterReportData(ReportHeader reportHeader){
		return getSqlSession().selectList(getNameSpace()+".findWaterReportData", reportHeader);
	}
}
