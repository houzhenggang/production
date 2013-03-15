package com.rubik.support.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.support.dao.TbGbLoginLogDao;
import com.rubik.support.entity.TbGbLoginLog;

@Service
@Transactional
public class LoginLogService {

	@Autowired
	private TbGbLoginLogDao tbGbLoginLogDao;

	/**
	 * 分页查询登陆日志
	 * @param rowBounds
	 * @return
	 */
	public List<TbGbLoginLog> getLoginLogByRowBounds(RowBounds rowBounds){
		return tbGbLoginLogDao.getLoginLogByRowBounds(rowBounds);
	}
	
	public TbGbLoginLog findById(Integer id) {
		return tbGbLoginLogDao.findById(id);
	}

	public void save(TbGbLoginLog entity) {
		this.tbGbLoginLogDao.insert(entity);
	}

	public void delete(List<String> ids) {
		this.tbGbLoginLogDao.deleteByIds(ids);
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return tbGbLoginLogDao.getTotalCount();
	}
}