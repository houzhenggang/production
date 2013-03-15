package com.rubik.eds.service.management.station;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.eds.dao.TbWeatherStationDao;
import com.rubik.eds.entity.TbWeatherStation;

@Service
@Transactional
public class StationManagementService {
	
	@Autowired
	private TbWeatherStationDao weatherStationDao;
	
	/**
	 * 分页查询
	 * @param rowBounds
	 * @return
	 */
	public List<TbWeatherStation> findPageByRowBounds(RowBounds rowBounds){
		return weatherStationDao.findPageByRowBounds(rowBounds);
	}
	
	/**
	 * 根据ID查询记录
	 * @return
	 */
	public TbWeatherStation findById(String id){
		return weatherStationDao.findById(id);
	}
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(TbWeatherStation user){
		return weatherStationDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(TbWeatherStation user){
		return weatherStationDao.insert(user);
	}
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 */
	public int delete(String id){
		return weatherStationDao.deleteById(id);
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Integer getTotalCount(){
		return weatherStationDao.getTotalCount();
	}
}
