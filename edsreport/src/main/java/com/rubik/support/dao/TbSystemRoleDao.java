package com.rubik.support.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rubik.support.entity.TbSystemRole;

@Repository
public class TbSystemRoleDao extends CommonDao<TbSystemRole> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8342252836601065202L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "systemRole";
	}

	/**
	 * 获取所有可选权限列表
	 * @return
	 */
	public List<TbSystemRole> getAllOptionsRole(){
		return getSqlSession().selectList(getNameSpace()+".findAllRoles");
	}
	
}
