<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function submitForm(){
		$("#userForm").form('submit',{
			url : '${ctx }/security/dunemove/save.action',
			onSubmit: function(){
				$dp.hide();
				return $(this).form('validate');
			},
			success: function(result){
				$dp.hide();
				if(result == "1"){
					$.messager.defaults={ok:"返 回",cancel:"导出数据", add:"继续添加"};
					$.messager.confirms('用户确认', '<br>操作成功', function(r){
						parent.CloseWin("myWindows");
						if(r){
							parent.location.reload();
						}else{
							parent.exportExcel('导出excel表格参数');
						}
					}, function(r){
						//parent.CloseWin("myWindows");
					});
				}else{
					$.messager.alert("系统提示", "<br>操作失败，请重试！", "info");
				}
			}
		});
	}
</script>
</head>
<body style="overflow-y: auto; overflow-x: hidden;padding: 0px; background-color: #fafafa">
	<form id="userForm" name="userForm" class="form" method="post" novalidate>
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">基本信息——<font class="tip-red">请认真填写</font></legend>
			<input type="hidden" name="id" value="${duneMove.id }">
			<table style="width: 375px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="140px"><label class="x-form-field">站点：</label></td>
					<td>
					<select name="stationId" class="easyui-combobox" style="width:200px;"
					data-options="editable:false,required:true,panelWidth:210">
						<c:forEach items="${stationList }" var="station">
						<c:choose>
							<c:when test="${station.stationId eq grass.stationId }">
								<option value="${station.stationId }" selected="selected">${station.stationName}</option>
							</c:when>
							<c:otherwise>
								<option value="${station.stationId }">${station.stationName}</option>
							</c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
					</td>
				</tr>
				<c:if test="${empty stationList }">
					<tr>
					<td></td>
					<td>
						<a href="#" class="easyui-linkbutton" iconCls="icon-help" plain="true" style="color: red; font-weight: bold">
						无可操作站点，请联系管理员</a>
					</td></tr>
				</c:if>
				<tr height="35px">
					<td class="cn_fontright">
						<label class="x-form-field">录入时间：</label>
					</td>
					<td>
						<input name="operationTime" id="operationTime" class="x-form-text easyui-validatebox Wdate" 
						required="true" 
						value="<fmt:formatDate value='${duneMove.operationTime}' pattern='yyyy-MM-dd'/>"
						onclick="WdatePicker({startDate:'%y-05-08',dateFmt:'yyyy-MM-08',minDate:'%y-03-08',maxDate:'%y-06-08',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<!-- 
				<tr>
					<td class="cn_fontright">
						<label class="x-form-field">测定日期：</label>
					</td>
					<td>
						<input name="duneMoveMonitorDate" id="duneMoveMonitorDate" class="x-form-text easyui-validatebox Wdate" 
						required="true"
						value="<fmt:formatDate value='${duneMove.duneMoveMonitorDate}' pattern='yyyy-MM-dd'/>"
						onclick="WdatePicker({startDate:'%y-05-08',dateFmt:'yyyy-MM-08',minDate:'%y-03-08',maxDate:'%y-06-08',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				 -->
				<tr>
					<td class="cn_fontright">
						<label class="x-form-field">被监测沙丘经度：</label>
					</td>
					<td>
						<select name="duneMoveLongtitude" class="easyui-combobox" style="width:200px;"
						data-options="required:true">
							<c:forEach items="${longtitudes }" var="longtitude">
							<c:choose>
								<c:when test="${longtitude.duneMoveLongtitude eq duneMove.duneMoveLongtitude }">
									<option value="${longtitude.duneMoveLongtitude }" selected="selected">${longtitude.duneMoveLongtitude}</option>
								</c:when>
								<c:otherwise>
									<option value="${longtitude.duneMoveLongtitude }">${longtitude.duneMoveLongtitude}</option>
								</c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright">
						<label class="x-form-field">被监测沙丘纬度：</label>
					</td>
					<td>
						<select name="duneMoveLatitude" class="easyui-combobox" style="width:200px;"
						data-options="required:true">
							<c:forEach items="${latitudes }" var="latitude">
							<c:choose>
								<c:when test="${latitude.duneMoveLatitude eq duneMove.duneMoveLatitude }">
									<option value="${latitude.duneMoveLatitude }" selected="selected">${latitude.duneMoveLatitude}</option>
								</c:when>
								<c:otherwise>
									<option value="${latitude.duneMoveLatitude }">${latitude.duneMoveLatitude}</option>
								</c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘海拔高度：</label></td>
					<td>
						<input name="duneAltitude" value="${duneMove.duneAltitude }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘高度变化：</label></td>
					<td>
						<input name="duneHeight" value="${duneMove.duneHeight }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘迎风坡脚移动距离：</label></td>
					<td>
						<input name="duneWindwardSlope" value="${duneMove.duneWindwardSlope }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘丘顶移动距离：</label></td>
					<td>
						<input name="duneHilltop" value="${duneMove.duneHilltop }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘背风坡脚移动距离：</label></td>
					<td>
						<input name="duneLeewardSlope" value="${duneMove.duneLeewardSlope }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘迎风坡脚方位角：</label></td>
					<td>
						<input name="duneWindPosition" value="${duneMove.duneWindPosition }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘丘顶方位角：</label></td>
					<td>
						<input name="hilltopPosition" value="${duneMove.hilltopPosition }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙丘背风坡脚方位角：</label></td>
					<td>
						<input name="leewardPosition" value="${duneMove.leewardPosition }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">主风向：</label></td>
					<td>
						<select name="windDirection" id="windDirection" class="easyui-combobox" style="width:200px;"
						data-options="required:true">
							<c:forEach items="${windDirections }" var="direction">
								<c:choose>
									<c:when test="${direction.key eq duneMove.windDirection }">
										<option value="${direction.key }" selected="selected">${direction.value }</option>
									</c:when>
									<c:otherwise>
										<option value="${direction.key }">${direction.value }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">风速：</label></td>
					<td>
						<input name="windSpeed" value="${duneMove.windSpeed }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2">
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>