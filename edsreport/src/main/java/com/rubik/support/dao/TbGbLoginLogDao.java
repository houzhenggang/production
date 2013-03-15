package com.rubik.support.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.support.entity.TbGbLoginLog;

@Repository
public class TbGbLoginLogDao extends CommonDao<TbGbLoginLog>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "loginLog";
	}
	/**
	 * 分页查询登陆日志
	 * @param rowBounds
	 * @return
	 */
	public List<TbGbLoginLog> getLoginLogByRowBounds(RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll",null, rowBounds);
	}
	
	/**
	 * 根据ID集合删除记录
	 * @param ids
	 */
	public Integer deleteByIds(List<String> ids) {
		return getSqlSession().delete(getNameSpace()+".deleteByIds", ids);
	}
}