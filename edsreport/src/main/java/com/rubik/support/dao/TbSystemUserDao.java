/**
 * 
 */
package com.rubik.support.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.TbUserStation;
import com.rubik.support.entity.TbSystemUser;
import com.rubik.support.entity.TbUserRole;
import com.rubik.support.security.SecurityUtils;


/**
 * @author Administrator
 *
 */
@Repository
public class TbSystemUserDao extends CommonDao<TbSystemUser> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -569571838104123624L;

	@Override
	protected String getNameSpace() {
		return "user";
	}
	
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	public TbSystemUser getUserByLoginName(String username){
		return getSqlSession().selectOne(getNameSpace()+".findByName", username);
	}
	
	/**
	 * 分页查询用户列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbSystemUser> getUserByRowBounds(RowBounds rowBounds){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userRole", SecurityUtils.getCurrentUser().getUserRole());
		
		return getSqlSession().selectList(getNameSpace()+".findByRowBound", paramMap, rowBounds);
	}
	
	/**
	 * 保存用户角色
	 * @return
	 */
	public int saveUserRole(Map<String, Object> paramMap){
		return getSqlSession().insert(getNameSpace()+".userRoleInsert", paramMap);
	}
	
	/**
	 * 根据用户ID删除权限
	 * @return
	 */
	public int deleteRoleById(Integer userId){
		return getSqlSession().delete(getNameSpace()+".deleteRoleById", userId);
	}
	
	/**
	 * 根据用户ID获取roles
	 * @param id
	 * @return
	 */
	public List<TbUserRole> getRolesByUserId(Integer userId){
		 return getSqlSession().selectList(getNameSpace()+".findRolesByUserId", userId);
	}
	
	/**
	 * 保存用户角色
	 * @return
	 */
	public int saveUserStation(Map<String, Object> paramMap){
		return getSqlSession().insert(getNameSpace()+".userStationInsert", paramMap);
	}
	
	/**
	 * 根据用户ID删除权限
	 * @return
	 */
	public int deleteStationById(Integer userId){
		return getSqlSession().delete(getNameSpace()+".deleteStationById", userId);
	}
	
	/**
	 * 根据用户ID获取roles
	 * @param id
	 * @return
	 */
	public List<TbUserStation> getStationByUserId(Integer userId){
		 return getSqlSession().selectList(getNameSpace()+".findStationsByUserId", userId);
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	@Override
	public Integer getTotalCount(){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userRole", SecurityUtils.getCurrentUser().getUserRole());
		
		return getSqlSession().selectOne(this.getNameSpace()+".getTotalCount", paramMap);
	}
}
