package com.rubik.eds.service.management.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.eds.dao.TbWeatherStationDao;
import com.rubik.eds.entity.TbUserStation;
import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.support.dao.TbSystemRoleDao;
import com.rubik.support.dao.TbSystemUserDao;
import com.rubik.support.entity.TbSystemRole;
import com.rubik.support.entity.TbSystemUser;
import com.rubik.support.entity.TbUserRole;

@Service
@Transactional
public class UserManagementService {

	@Autowired
	private TbSystemUserDao systemUserDao;
	@Autowired
	private TbSystemRoleDao systemRoleDao;
	@Autowired
	private TbWeatherStationDao weatherStationDao;
	
	/**
	 * 分页查询用户列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbSystemUser> getUserByRowBounds(RowBounds rowBounds){
		return systemUserDao.getUserByRowBounds(rowBounds);
	}
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbSystemUser getUserById(Integer id){
		return systemUserDao.findById(id);
	}
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbSystemUser user){
		return systemUserDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbSystemUser user){
		return systemUserDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return systemUserDao.deleteById(id);
	}
	
	/**
	 * 保存用户角色
	 * @return
	 */
	public int saveUserRole(Map<String, Object> paramMap){
		return systemUserDao.saveUserRole(paramMap);
	}
	
	/**
	 * 根据用户ID删除权限
	 * @return
	 */
	public int deleteRoleById(Integer userId){
		return systemUserDao.deleteRoleById(userId);
	}
	
	/**
	 * 根据用户ID获取roles
	 * @param id
	 * @return
	 */
	public List<Integer> getRolesByUserId(Integer userId){
		List<TbUserRole> userRoles = systemUserDao.getRolesByUserId(userId);
		List<Integer> checkRoles = new ArrayList<Integer>();
		for(TbUserRole userRole : userRoles){
			checkRoles.add(userRole.getRoleId());
		}
		return checkRoles;
	}
	
	/**
	 * 获取所有可选权限列表
	 * @return
	 */
	public List<TbSystemRole> getAllOptionsRole(){
		return systemRoleDao.getAllOptionsRole();
	}
	
	/**
	 * 保存用户角色
	 * @return
	 */
	public int saveUserStation(Map<String, Object> paramMap){
		return systemUserDao.saveUserStation(paramMap);
	}
	
	/**
	 * 根据用户ID删除站点
	 * @return
	 */
	public int deleteStationById(Integer userId){
		return systemUserDao.deleteStationById(userId);
	}
	
	/**
	 * 根据用户ID获取stations
	 * @param id
	 * @return
	 */
	public List<String> getStationsByUserId(Integer userId){
		List<TbUserStation> userStationss = systemUserDao.getStationByUserId(userId);
		List<String> checkStations = new ArrayList<String>();
		for(TbUserStation userStation : userStationss){
			checkStations.add(userStation.getStationId());
		}
		return checkStations;
	}
	
	/**
	 * 获取所有可选站点列表
	 * @return
	 */
	public List<TbWeatherStation> getAllStations(){
		return weatherStationDao.findAll();
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return systemUserDao.getTotalCount();
	}
}
