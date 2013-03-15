<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
		
	</style>
<script type="text/javascript">
	function submitForm(){
		$("#userForm").form('submit',{
			url : '${ctx }/security/plant/save.action',
			onSubmit: function(){
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
			<input type=hidden name="id" value="${speciesMonitor.id }">
			<table style="width: 395px" border="0" cellpadding="0" cellspacing="0">
				<tr height="35px">
					<td class="cn_fontright" width="80px"><label class="x-form-field">站点：</label></td>
					<td>
					<select name="stationId" id="stationId" class="easyui-combobox" style="width:200px;"
					data-options="editable:false,required:true,panelWidth:210,panelHeight:160">
						<c:forEach items="${stationList }" var="station">
							<c:choose>
								<c:when test="${station.stationId eq speciesMonitor.stationId }">
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
						validType="isExist('${ctx }-<fmt:formatDate value='${speciesMonitor.plantMonitorDate}' pattern='yyyy年MM月dd日'/>')" 
						value="<fmt:formatDate value='${speciesMonitor.operationTime}' pattern='yyyy-MM-01'/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-01',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<!-- 
				<tr height="35px">
					<td class="cn_fontright">
						<label class="x-form-field">监测年月：</label>
					</td>
					<td>
						<input name="plantMonitorDate" id="plantMonitorDate" class="x-form-text easyui-validatebox Wdate" 
						value="<fmt:formatDate value='${speciesMonitor.plantMonitorDate}' pattern='yyyy年MM月dd日'/>"
						required="required" 
						validType="isExist('${ctx }-<fmt:formatDate value='${speciesMonitor.plantMonitorDate}' pattern='yyyy年MM月dd日'/>')"
						onclick="WdatePicker({dateFmt:'yyyy年MM月01日',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				 -->
			</table>
		</fieldset>
	</form>
	<fieldset class="x-fieldset">
		<legend class="x-fieldset-header x-unselectable">操作提示信息</legend>
		<a href="#" class="easyui-linkbutton" iconCls="icon-help" plain="true" style="color: red;">
		新增数据后，在新增数据记录上逐一添加植物物种名称
		</a>
	</fieldset>
</body>
</html>