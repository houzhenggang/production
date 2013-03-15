package com.rubik.support.interceptor;

public class MysqlDialect extends Dialect {

	/**
	 * @param sql
	 * @param skipResults 页码
	 * @param maxResults 每页记录数
	 * @return
	 */
	@Override
	public String getLimitString(String sql, int skipResults, int maxResults) {
		sql = sql.trim();
		StringBuffer pagingSelect = new StringBuffer(sql);
		pagingSelect.append(" LIMIT ");
		int begin = (skipResults-1) * maxResults;
		pagingSelect.append(begin);
		pagingSelect.append(",");
		pagingSelect.append(maxResults);
	    return pagingSelect.toString();
		
	}
}
