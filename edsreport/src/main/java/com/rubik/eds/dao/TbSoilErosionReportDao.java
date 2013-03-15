package com.rubik.eds.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbSoilErosionReport;
import com.rubik.support.dao.CommonDao;

@Repository
public class TbSoilErosionReportDao extends CommonDao<TbSoilErosionReport> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4697804346180168754L;

	@Override
	protected String getNameSpace() {
		return "soilReport";
	}

	/**
	 * 分页查询土壤风蚀历史报表监测数据列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbSoilErosionReport> getSoilErosionReportsByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", userIdMap, rowBounds);
	}
	
	/**
	 * 删除相同月份的土壤监测报表数据
	 * @param soilErosionReport
	 * @return
	 */
	public int deleteByTime(TbSoilErosionReport soilErosionReport){
		return getSqlSession().delete(getNameSpace()+".deleteByTime", soilErosionReport);
	}
	
	/**
	 * 查询报表数据5个月
	 * @param reportHeader
	 * @return
	 */
	public List<TbSoilErosionReport> getSoilReports(ReportHeader reportHeader){
		return getSqlSession().selectList(getNameSpace()+".findReportData", reportHeader);
	}
}
