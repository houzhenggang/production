/**
 * 
 */
package com.rubik.eds.service.plant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.eds.dao.TbPlantDetailsDao;
import com.rubik.eds.entity.TbPlantDetails;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class PlantDetailsService {

	@Autowired
	private TbPlantDetailsDao plantDetailsDao;
	
	/**
	 * 获取所有植物种类名称
	 * @return
	 */
	public List<String> findAllPlantNames(){
		//查询返回
		return plantDetailsDao.findAllPlantNames();
	}
	
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbPlantDetails findById(Integer id){
		return plantDetailsDao.findById(id);
	}
	
	/**
	 * 根据条件查询记录
	 * @param plantDetails
	 * @return
	 */
	public List<String> findByWhere(TbPlantDetails plantDetails){
		//查询返回
		return plantDetailsDao.findByWhere(plantDetails);
	}
	
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbPlantDetails user){
		return plantDetailsDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbPlantDetails user){
		return plantDetailsDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		return plantDetailsDao.deleteById(id);
	}
	/**
	 * 删除原有的记录
	 * @param plantDetails
	 * @return
	 */
	public int deleteByWhere(TbPlantDetails plantDetails){
		//删除原有的记录
		return plantDetailsDao.deleteByWhere(plantDetails);
	}
}
