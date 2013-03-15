<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>农作物产量监测报表头</title>
<script type="text/javascript">
var confirm = true;
function submitForm(){
	if($("#userForm").form('validate')){
		if(confirm){
			confirm = false;
			$dp.hide();
			$.messager.confirm('用户确认', '<br>确定导出数据到Excel表格？', function(r){
				if(r){
					$("#reportStationName").val($('#reportStationId').combobox('getText'));
					userForm.submit();
					parent.CloseWin("myWindows");
				}else{
					confirm = true;
					return;
				}
			});
		}
	};
}
</script>
</head>
<body style="overflow-y: auto; overflow-x: hidden;padding: 0px; background-color: #fafafa">
	<form id="userForm" name="userForm" class="form" method="post" novalidate
	action="${ctx }/security/grass/createReport.action">
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">导出Excel参数——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 375px" border="0" cellpadding="0" cellspacing="0">
				<tr height="30px">
					<td class="cn_fontright" width="100px"><label class="x-form-field">站点：</label></td>
					<td width="275px">
					<select name="reportStationId" class="easyui-combobox" style="width:200px;"
					data-options="editable:false,required:true,panelHeight:160,panelWidth:210" id="reportStationId">
						<c:forEach items="${stationList }" var="station">
							<option value="${station.stationId }">${station.stationName}</option>
						</c:forEach>
					</select>
					<input type="hidden" name="reportStationName" id="reportStationName">
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
				<tr>
					<td class="cn_fontright"><label class="x-form-field">监测地段：</label></td>
					<td>
					<select name="reportMonitorArea" class="easyui-combobox" style="width:200px;" 
						data-options="editable:false,required:true,panelHeight:120">
							<option value="监测场" selected="selected">监测场</option>
							<option value="监测区">监测区</option>
					</select>
					</td>
				</tr>
				<tr height="30px">
					<td class="cn_fontright" width="100px"><label class="x-form-field">填表人：</label></td>
					<td width="295px">
						<select name="reporterName" class="easyui-combobox" style="width:200px;"
						data-options="required:true,panelHeight:100" id="reporterName">
							<c:forEach items="${reporters }" var="reporter">
								<option value="${reporter.reporterName }">${reporter.reporterName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr height="30px">
					<td class="cn_fontright" width="100px"><label class="x-form-field">录入时间：</label></td>
					<td width="295px">
						<input name="reportStartDate" required="true" id="reportStartDate"
						class="x-form-text easyui-validatebox Wdate" 
						value="<fmt:formatDate value='${reportHeader.reportDate }' pattern='yyyy-MM-dd'/>" 
						onclick="WdatePicker({maxDate:'#F{$dp.$D(\'reportDate\')}',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<!-- 
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">监测结束时间：</label></td>
					<td width="295px">
						<input name="reportEndDate" required="true" id="reportEndDate"
						class="x-form-text easyui-validatebox Wdate"
						onclick="WdatePicker({minDate:'#F{$dp.$D(\'reportStartDate\')}',dateFmt:'yyyy-MM-dd 23:59:59',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				 -->
				<tr height="30px">
					<td class="cn_fontright" width="100px"><label class="x-form-field">上报时间：</label></td>
					<td width="295px">
						<input name="reportDate" required="true" id="reportDate"
						class="x-form-text easyui-validatebox Wdate" 
						value="<fmt:formatDate value='${reportHeader.reportDate }' pattern='yyyy-MM-21'/>" 
						onclick="WdatePicker({minDate:'#F{$dp.$D(\'reportStartDate\')}',dateFmt:'yyyy-MM-21',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr height="30px">
					<td class="cn_fontright"><label class="x-form-field">保存方式：</label></td>
					<td>
						<select name="howSaveFile" class="easyui-combobox" style="width:200px;"
						data-options="editable:false,required:true,panelHeight:100" id="howSaveFile">
								<option value="0">只导出到本地</option>
								<option value="1">保存到共享和本地</option>
						</select>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>