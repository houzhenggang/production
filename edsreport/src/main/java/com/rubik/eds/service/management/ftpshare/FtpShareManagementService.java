package com.rubik.eds.service.management.ftpshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.eds.dao.FtpShareLocalDao;
import com.rubik.eds.entity.FtpShareLocalEntity;

@Service
@Transactional
public class FtpShareManagementService {
	
	@Autowired
	private FtpShareLocalDao ftpShareLocalDao;
	
	/**
	 * 更新一条记录
	 * @return
	 */
	public int update(FtpShareLocalEntity user){
		return ftpShareLocalDao.update(user);
	}
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insert(FtpShareLocalEntity user){
		return ftpShareLocalDao.insert(user);
	}
	/**
	 * 获取远程访问实体
	 * @return
	 */
	public FtpShareLocalEntity findUnique(){
		return ftpShareLocalDao.findUnique();
	}
}
