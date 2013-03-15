package com.rubik.eds.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbDuneMoveMonitor;
import com.rubik.support.dao.CommonDao;

@Repository
public class TbDuneMoveReportDao extends CommonDao<TbDuneMoveMonitor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8225634574074596293L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "duneReport";
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
	 * 删除相同月份的报表数据
	 * @param duneMoveMonitor
	 * @return
	 */
	public int deleteByTime(TbDuneMoveMonitor duneMoveMonitor){
		return getSqlSession().delete(getNameSpace()+".deleteByTime", duneMoveMonitor);
	}
	
	/**
	 * 查询报表数据
	 * @param reportHeader
	 * @return
	 */
	public List<TbDuneMoveMonitor> findReportData(ReportHeader reportHeader){
		return getSqlSession().selectList(getNameSpace()+".findReportData", reportHeader);
	}
}
