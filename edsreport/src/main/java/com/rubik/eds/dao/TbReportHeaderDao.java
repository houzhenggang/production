package com.rubik.eds.dao;


import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.ReportHeader;
import com.rubik.support.dao.CommonDao;

@Repository
public class TbReportHeaderDao extends CommonDao<ReportHeader> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8304607437350831504L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "reportHeader";
	}
}
