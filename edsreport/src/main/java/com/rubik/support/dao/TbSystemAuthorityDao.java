/**
 * 
 */
package com.rubik.support.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rubik.support.entity.TbSystemAuthority;
import com.rubik.support.entity.TbSystemUser;

/**
 * @author Administrator
 *
 */
@Repository
public class TbSystemAuthorityDao extends CommonDao<TbSystemAuthority> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3026417420048460377L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "authority";
	}

	public List<TbSystemAuthority> getAuthoritiesByUser(TbSystemUser user){
		return getSqlSession().selectList(getNameSpace()+".getAuthorityByUser", user);
	}
}
