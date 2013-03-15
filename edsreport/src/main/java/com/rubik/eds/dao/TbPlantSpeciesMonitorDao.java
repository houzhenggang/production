/**
 * 
 */
package com.rubik.eds.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbPlantSpeciesMonitor;
import com.rubik.support.dao.CommonDao;

/**
 * @author Administrator
 *
 */
@Repository
public class TbPlantSpeciesMonitorDao extends CommonDao<TbPlantSpeciesMonitor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4358038015491409340L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "plant";
	}

	/**
	 * 分页查询监测数据列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbPlantSpeciesMonitor> getPlantSepeciesByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", userIdMap, rowBounds);
	}
	
	/**
	 * 通过年月日期查询记录
	 * @param plantMonitorDate
	 * @return
	 */
	public int findByDate(TbPlantSpeciesMonitor plantSpeciesMonitor){
		return getSqlSession().selectOne(getNameSpace()+".findByDate", plantSpeciesMonitor);
	}
	
	/**
	 * 查询报表数据
	 * @param reportHeader
	 * @return
	 */
	public List<TbPlantSpeciesMonitor> getPlantSpeciesReportData(ReportHeader reportHeader){
		return getSqlSession().selectList(getNameSpace()+".findPlantReportData", reportHeader);
	}
}
