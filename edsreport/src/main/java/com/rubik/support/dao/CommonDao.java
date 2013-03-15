/**
 * 
 */
package com.rubik.support.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.rubik.support.security.SecurityUtils;

/**
 * @author Administrator
 *
 */
@Scope("prototype")
@Repository
public abstract class CommonDao<T> extends SqlSessionDaoSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -232049678326750233L;
	/**
	 * 数据库connection
	 */
	/**
	 * 获取myBatis会话
	 * @return
	 */
	protected abstract String getNameSpace();
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return getSqlSession().selectOne(this.getNameSpace()+".getTotalCount", SecurityUtils.getCurrentUserIdMap());
	}
	/**
	 * 获取所有记录
	 * @return
	 */
	public List<T> findAll(){
		return getSqlSession().selectList(this.getNameSpace()+".findAll", SecurityUtils.getCurrentUserIdMap());
	}
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public T findById(Object id){
		return getSqlSession().selectOne(this.getNameSpace()+".findById", id);
	}
	/**
	 * 根据Name查询记录
	 * @return
	 */
	public List<T> findByName(Object name){
		return getSqlSession().selectList(this.getNameSpace()+".findByName", name);
	}
	
	/**
	 * 根据ID删除记录
	 * @return
	 */
	public int deleteById(Object id){
		return getSqlSession().delete(this.getNameSpace()+".deleteById", id);
	}
	/**
	 * 根据Name删除记录
	 * @return
	 */
	public int deleteByName(Object name){
		return getSqlSession().delete(this.getNameSpace()+".deleteByName", name);
	}
	/**
	 * 添加记录
	 * @return
	 */
	public int insert(Object entity){
		return getSqlSession().insert(this.getNameSpace()+".insert", entity);
	}
	/**
	 * 修改记录
	 * @return
	 */
	public int update(Object entity){
		return getSqlSession().update(this.getNameSpace()+".update", entity);
	}
	
	/**
	 * 分页查询列表
	 * @param rowBounds
	 * @return
	 */
	public List<T> findPageByRowBounds(RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", null, rowBounds);
	}
}
