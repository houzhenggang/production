/**
 * Copyright (c) 2012 rubik All Rights Reserved.
 */
package com.rubik.support.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.support.entity.TbGbOperatingLog;
import com.rubik.support.security.SecurityUtils;

/**
 * @author wyfhrubik@sina.com
 * @version 1.0
 */
@Repository
public class TbGbOperatingLogDao extends CommonDao<TbGbOperatingLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "operatingLog";
	}
	/**
	 * 分页查询登陆日志
	 * @param rowBounds
	 * @return
	 */
	public List<TbGbOperatingLog> getOperatingLogByRowBounds(RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", null, rowBounds);
	}
	
	/**
	 * 根据ID集合删除记录
	 * @param ids
	 */
	public Integer deleteByIds(List<String> ids) {
		return getSqlSession().delete(getNameSpace()+".deleteByIds", ids);
	}

	/**
	 * 记录操作日志
	 * @param module
	 * @param comments
	 */
	public void save(String module, String comments) {
		//定义并初始化
		TbGbOperatingLog operLog = new TbGbOperatingLog();
		//设置操作者：
		operLog.setUserId(SecurityUtils.getCurrentUser().getId());
		//设置登录名称：
		operLog.setLoginName(SecurityUtils.getCurrentUserName());
		//设置操作者名称：
		operLog.setNickname(SecurityUtils.getCurrentUserNickname());
		//设置操作时间：
		operLog.setHappenTime(new Date());
		//设置操作模块名称：
		operLog.setModule(module);
		//设置备注：
		operLog.setComments(comments);
		//保存操作日志：
		super.insert(operLog);
	}
}
