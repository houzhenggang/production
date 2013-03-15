package com.rubik.support.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubik.support.dao.TbSystemDatadictDao;
import com.rubik.support.entity.TbSystemDatadict;

@Service
public class DatadictService {

	@Autowired
	private TbSystemDatadictDao datadictDao;
	
	/**
	 * 根据code获取value
	 * @param code
	 * @return
	 */
	public TbSystemDatadict getValueById(String code){
		return datadictDao.findById(code);
	}
}
