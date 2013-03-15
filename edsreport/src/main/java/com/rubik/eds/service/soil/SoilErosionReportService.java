package com.rubik.eds.service.soil;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.eds.dao.TbSoilErosionReportDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbSoilErosionReport;

@Service
@Transactional
public class SoilErosionReportService {

	@Autowired
	private TbSoilErosionReportDao soilErosionReportDao;
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbSoilErosionReport> findPageByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return soilErosionReportDao.getSoilErosionReportsByRowBounds(userIdMap, rowBounds);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbSoilErosionReport user){
		return soilErosionReportDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return soilErosionReportDao.deleteById(id);
	}
	
	/**
	 * 删除相同月份的土壤监测报表数据
	 * @param soilErosionReport
	 * @return
	 */
	public int deleteByTime(TbSoilErosionReport soilErosionReport){
		return soilErosionReportDao.deleteByTime(soilErosionReport);
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return soilErosionReportDao.getTotalCount();
	}
	
	/**
	 * 查询报表数据5个月
	 * @param reportHeader
	 * @return
	 */
	public List<TbSoilErosionReport> getSoilReports(ReportHeader reportHeader){
		return soilErosionReportDao.getSoilReports(reportHeader);
	}
}
