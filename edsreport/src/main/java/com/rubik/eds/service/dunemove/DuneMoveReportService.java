package com.rubik.eds.service.dunemove;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.eds.dao.TbDuneMoveReportDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbDuneMoveMonitor;

@Service
@Transactional
public class DuneMoveReportService {
	@Autowired
	private TbDuneMoveReportDao duneMoveReportDao;
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbDuneMoveMonitor> findPageByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return duneMoveReportDao.getDuneMoveByRowBounds(userIdMap, rowBounds);
	}
	
	/**
	 * 查询报表数据
	 * @param reportHeader
	 * @return
	 */
	public List<TbDuneMoveMonitor> findReportData(ReportHeader reportHeader){
		return duneMoveReportDao.findReportData(reportHeader);
	}
	
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbDuneMoveMonitor findById(Integer id){
		return duneMoveReportDao.findById(id);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbDuneMoveMonitor user){
		return duneMoveReportDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return duneMoveReportDao.deleteById(id);
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return duneMoveReportDao.getTotalCount();
	}
	
	/**
	 * 删除相同月份的报表数据
	 * @param duneMoveMonitor
	 * @return
	 */
	public int deleteByTime(TbDuneMoveMonitor duneMoveMonitor){
		return duneMoveReportDao.deleteByTime(duneMoveMonitor);
	}
}
