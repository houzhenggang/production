package com.rubik.eds.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbSoilErosionMonitor;
import com.rubik.eds.entity.TbSoilErosionReport;
import com.rubik.support.dao.CommonDao;

@Repository
public class TbSoilErosionMonitorDao extends CommonDao<TbSoilErosionMonitor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 766716445138935L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "soilErosion";
	}

	/**
	 * 分页查询土壤风蚀监测数据列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbSoilErosionMonitor> getSoilErosionByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", userIdMap, rowBounds);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public List<TbSoilErosionReport> getSoilErosionReportData(ReportHeader reportHeader){
		return getSqlSession().selectList(getNameSpace()+".findSoilErosionReportData", reportHeader);
	}
	
	/**
	 * 删除相同月份的监测报表数据
	 * @param soilErosionReport
	 * @return
	 */
	public int deleteByTime(TbSoilErosionMonitor soilErosionMonitor){
		return getSqlSession().delete(getNameSpace()+".deleteByTime", soilErosionMonitor);
	}
}
