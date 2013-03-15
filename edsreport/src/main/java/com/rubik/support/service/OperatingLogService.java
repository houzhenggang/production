package com.rubik.support.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.support.dao.TbGbOperatingLogDao;
import com.rubik.support.entity.TbGbOperatingLog;

@Service
@Transactional
public class OperatingLogService {

	@Autowired
	private TbGbOperatingLogDao tbGbOperatingLogDao;

	/**
	 * 分页查询操作日志
	 * @param rowBounds
	 * @return
	 */
	public List<TbGbOperatingLog> getOperatingLogByRowBounds(RowBounds rowBounds){
		return tbGbOperatingLogDao.getOperatingLogByRowBounds(rowBounds);
	}
	
	public TbGbOperatingLog findById(Integer id) {
		return tbGbOperatingLogDao.findById(id);
	}

	public void save(TbGbOperatingLog entity) {
		this.tbGbOperatingLogDao.insert(entity);
	}

	public void delete(List<String> ids) {
		this.tbGbOperatingLogDao.deleteByIds(ids);
	}
	/**
	 * 记录操作日志
	 * @param module
	 * @param comments
	 */
	public void save(String module, String comments) {
		//保存操作日志：
		this.tbGbOperatingLogDao.save(module, comments);;
	}
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return tbGbOperatingLogDao.getTotalCount();
	}
}