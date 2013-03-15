package com.rubik.eds.service.soilanalysis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.eds.dao.SoilMonitorConstantDao;
import com.rubik.eds.entity.SoilMonitorConstant;

@Service
@Transactional
public class SoilConstantService {
	@Autowired
	private SoilMonitorConstantDao soilMonitorConstantDao;
	
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<SoilMonitorConstant> getSoilConstantByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return soilMonitorConstantDao.getSoilConstantByRowBounds(userIdMap, rowBounds);
	}
	
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public SoilMonitorConstant findById(String id){
		return soilMonitorConstantDao.findById(id);
	}
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(SoilMonitorConstant user){
		return soilMonitorConstantDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(SoilMonitorConstant user){
		return soilMonitorConstantDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(String id){
		return soilMonitorConstantDao.deleteById(id);
	}
	
	/**
	 * 查询用户具有的权限的站点List
	 * @param userId
	 * @return
	 */
	public List<SoilMonitorConstant> getSoilConstantListByUserId(Map<Object, Object> userIdMap){
		return soilMonitorConstantDao.getSoilConstantListByUserId(userIdMap);
	}
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return soilMonitorConstantDao.getTotalCount();
	}
}
