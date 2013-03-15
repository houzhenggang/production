/**
 * 
 */
package com.rubik.eds.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.TbPlantDetails;
import com.rubik.support.dao.CommonDao;

/**
 * @author Administrator
 *
 */
@Repository
public class TbPlantDetailsDao extends CommonDao<TbPlantDetails> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3445699624326862793L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "plantDetails";
	}

	/**
	 * 获取所有植物种类名称
	 * @return
	 */
	public List<String> findAllPlantNames(){
		//查询返回
		return getSqlSession().selectList(getNameSpace()+".findAllPlantNames");
	}
	
	/**
	 * 根据条件查询记录
	 * @param plantDetails
	 * @return
	 */
	public List<String> findByWhere(TbPlantDetails plantDetails){
		//查询返回
		return getSqlSession().selectList(getNameSpace()+".findByWhere", plantDetails);
	}
	
	/**
	 * 删除原有的记录
	 * @param plantDetails
	 * @return
	 */
	public int deleteByWhere(TbPlantDetails plantDetails){
		//删除原有的记录
		return getSqlSession().delete(getNameSpace()+".deleteByWhere", plantDetails);
	}
}
