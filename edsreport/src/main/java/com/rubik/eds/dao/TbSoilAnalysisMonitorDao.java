package com.rubik.eds.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbSoilAnalysisMonitor;
import com.rubik.support.dao.CommonDao;

@Repository
public class TbSoilAnalysisMonitorDao extends CommonDao<TbSoilAnalysisMonitor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 678882256578227414L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "soilAnalysis";
	}

	/**
	 * 分页查询监测数据列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbSoilAnalysisMonitor> getSoilAnalysisByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", userIdMap, rowBounds);
	}
	
	/**
	 * 根据报文头查询报文数据
	 * @param reportHeader
	 * @return
	 */
	public TbSoilAnalysisMonitor getReportDataByDate(ReportHeader reportHeader){
		return getSqlSession().selectOne(getNameSpace()+".findReportDataByDate", reportHeader);
	}
}
