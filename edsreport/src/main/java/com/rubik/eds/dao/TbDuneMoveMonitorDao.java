package com.rubik.eds.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbDuneMoveMonitor;
import com.rubik.support.dao.CommonDao;

@Repository
public class TbDuneMoveMonitorDao extends CommonDao<TbDuneMoveMonitor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8225634574074596293L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "dune";
	}
	/**
	 * 分页查询监测数据列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbDuneMoveMonitor> getDuneMoveByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", userIdMap, rowBounds);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public TbDuneMoveMonitor getDuneMoveReportData(ReportHeader reportHeader){
		return getSqlSession().selectOne(getNameSpace()+".findDuneMoveReportData", reportHeader);
	}
	/**
	 * 获取经度集合
	 * @param userId
	 * @return
	 */
	public List<TbDuneMoveMonitor> getLongtitudes(Map<Object, Object> userIdMap){
		return getSqlSession().selectList(getNameSpace()+".getLongtitudes", userIdMap);
	}
	/**
	 * 获取纬度集合
	 * @param userId
	 * @return
	 */
	public List<TbDuneMoveMonitor> getLatitudes(Map<Object, Object> userIdMap){
		return getSqlSession().selectList(getNameSpace()+".getLatitudes", userIdMap);
	}
	
	/**
	 * 获取原始报表数据
	 * @param reportHeader
	 * @return
	 */
	public List<TbDuneMoveMonitor> getRealDuneMoveReportData(ReportHeader reportHeader){
		return getSqlSession().selectList(getNameSpace()+".findReportData", reportHeader);
	}
}
