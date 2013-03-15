package com.rubik.eds.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbDustMonitor;
import com.rubik.support.dao.CommonDao;

@Repository
public class TbDustMonitorDao extends CommonDao<TbDustMonitor> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8304607437350831504L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "dust";
	}

	/**
	 * 分页查询沙尘监测数据列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbDustMonitor> getDustByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", userIdMap, rowBounds);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public List<TbDustMonitor> getDustReportData(ReportHeader reportHeader){
		return getSqlSession().selectList(getNameSpace()+".findDustReportData", reportHeader);
	}
	
	
}
