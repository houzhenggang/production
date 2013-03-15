/**
 * 
 */
package com.rubik.support.security.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.support.dao.TbSystemAuthorityDao;
import com.rubik.support.dao.TbSystemResourceDao;
import com.rubik.support.dao.TbSystemUserDao;
import com.rubik.support.entity.TbSystemAuthority;
import com.rubik.support.entity.TbSystemResource;
import com.rubik.support.entity.TbSystemUser;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class SecurityService {

	@Autowired
	private TbSystemUserDao systemUserDao;
	@Autowired
	private TbSystemAuthorityDao systemAuthorityDao;
	@Autowired
	private TbSystemResourceDao resourceDao;
	
	public TbSystemUser getUserByLoginName(String loginName){
		return systemUserDao.getUserByLoginName(loginName);
	}
	
	public List<TbSystemAuthority> getAuthoritiesByUser(TbSystemUser user){
		return systemAuthorityDao.getAuthoritiesByUser(user);
	}
	
	/**
	 * 根据当前用户ID及资源类型查询资源URL
	 * @param map
	 * @return
	 */
	public List<TbSystemResource> getResourceUrlByUserId(Map<Object, Object> map){
		return resourceDao.getResourceUrlByUserId(map);
	}
}
