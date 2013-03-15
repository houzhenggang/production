package com.rubik.eds.dao;

import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.FtpShareLocalEntity;
import com.rubik.support.dao.CommonDao;

@Repository
public class FtpShareLocalDao extends CommonDao<FtpShareLocalEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String getNameSpace() {
		return "ftpShareLocal";
	}
	
	/**
	 * 获取远程访问实体
	 * @return
	 */
	public FtpShareLocalEntity findUnique(){
		return getSqlSession().selectOne(this.getNameSpace()+".findUnique");
	}
}
