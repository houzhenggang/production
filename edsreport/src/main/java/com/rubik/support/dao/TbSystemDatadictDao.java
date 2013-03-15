package com.rubik.support.dao;

import org.springframework.stereotype.Repository;

import com.rubik.support.entity.TbSystemDatadict;

@Repository
public class TbSystemDatadictDao extends CommonDao<TbSystemDatadict> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9129587369478020494L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "datadict";
	}

	
	
}
