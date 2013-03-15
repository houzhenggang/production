<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="overflow-y: auto; overflow-x: hidden;padding: 0px; background-color: #fafafa">
	<form id="userForm" name="userForm" class="form" method="post" novalidate>
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">基本信息查看</legend>
			<table style="width: 375px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="140px"><label class="x-form-field">站号：</label></td>
					<td>
					<input value="${duneMove.stationId }" readonly="readonly" class="x-form-text easyui-validatebox" >
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="140px"><label class="x-form-field">站名：</label></td>
					<td>
					<input value="${duneMove.stationName }" readonly="readonly" class="x-form-text easyui-validatebox" >
					</td>
				</tr>
				<tr height="35px">
					<td class="cn_fontright">
						<label class="x-form-field">录入时间：</label>
					</td>
					<td>
						<input class="x-form-text" 
						value="<fmt:formatDate value='${duneMove.operationTime}' pattern='yyyy-MM-dd'/>" readonly="readonly">
					</td>
				</tr>
				<!-- 
				<tr>
					<td class="cn_fontright">
						<label class="x-form-field">测定日期：</label>
					</td>
					<td>
						<input class="x-form-text"  
						value="<fmt:formatDate value='${duneMove.duneMoveMonitorDate}' pattern='yyyy-MM-dd'/>" readonly="readonly">
					</td>
				</tr>
				 -->
				<tr>
					<td class="cn_fontright">
						<label class="x-form-field">被监测沙丘经度：</label>
					</td>
					<td>
						<input class="x-form-text" style="width:200px;" readonly="readonly"  value="${duneMove.duneMoveLongtitude }">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright">
						<label class="x-form-field">被监测沙丘纬度：</label>
					</td>
					<td>
						<input class="x-form-text"  style="width:200px;" readonly="readonly"  value="${duneMove.duneMoveLatitude }">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘海拔高度：</label></td>
					<td>
						<input value="${duneMove.duneAltitude }" class="x-form-text" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘高度变化：</label></td>
					<td>
						<input value="${duneMove.duneHeight }" class="x-form-text" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘迎风坡脚移动距离：</label></td>
					<td>
						<input value="${duneMove.duneWindwardSlope }" class="x-form-text" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘丘顶移动距离：</label></td>
					<td>
						<input value="${duneMove.duneHilltop }" class="x-form-text" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘背风坡脚移动距离：</label></td>
					<td>
						<input value="${duneMove.duneLeewardSlope }" class="x-form-text" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘迎风坡脚方位角：</label></td>
					<td>
						<input value="${duneMove.duneWindPosition }" class="x-form-text" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘丘顶方位角：</label></td>
					<td>
						<input value="${duneMove.hilltopPosition }" class="x-form-text" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘背风坡脚方位角：</label></td>
					<td>
						<input value="${duneMove.leewardPosition }" class="x-form-text" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">主风向：</label></td>
					<td>
						<input class="x-form-text" style="width:200px;" value="${duneMove.windDirection }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">风速：</label></td>
					<td>
						<input value="${duneMove.windSpeed }" class="x-form-text" readonly="readonly">
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>