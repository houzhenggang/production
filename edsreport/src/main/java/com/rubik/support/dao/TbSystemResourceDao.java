/**
 * 
 */
package com.rubik.support.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rubik.support.entity.TbSystemResource;

/**
 * @author Administrator
 *
 */
@Repository
public class TbSystemResourceDao extends CommonDao<TbSystemResource> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8222187068963035415L;
	public static final String URL_TYPE = "url";
	public static final String MENU_TYPE = "menu";
	public static final String MENU1_TYPE = "menu1";
	public static final String MENU2_TYPE = "menu2";
	public static final String MENU3_TYPE = "menu3";
	public static final String MENU4_TYPE = "menu4";
	public static final String MENU5_TYPE = "menu5";
	
	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "resource";
	}

	/**
	 * 返回所有的权限与资源的对应关系
	 * @return
	 */
	public List<TbSystemResource> getResourceUrlAuthority(){
		return getSqlSession().selectList(getNameSpace()+".findResourceUrlAuthority");
	}
	
	/**
	 * 根据当前用户ID及资源类型查询资源URL
	 * @param map
	 * @return
	 */
	public List<TbSystemResource> getResourceUrlByUserId(Map<Object, Object> map){
		return getSqlSession().selectList(getNameSpace()+".findResourceUrlByUserId", map);
	}
}
