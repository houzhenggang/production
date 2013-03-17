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
			url : '${ctx }/security/dust/save.action',
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
			<input type=hidden name="id" value="${dust.id }">
			<table style="width: 375px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">站点：</label></td>
					<td width="275px">
					<select name="stationId" class="easyui-combobox" style="width:200px;"
					data-options="editable:false,required:true,panelWidth:210">
						<c:forEach items="${stationList }" var="station">
						<c:choose>
							<c:when test="${station.stationId eq dust.stationId }">
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
					<td class="cn_fontright" width="100px">
						<label class="x-form-field">录入时间：</label>
					</td>
					<td>
						<input name="operationTime" id="operationTime" class="x-form-text easyui-validatebox Wdate" 
						required="true" 
						value="<fmt:formatDate value='${dust.operationTime}' pattern='yyyy-MM-01 00:00:00'/>"
						onclick="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-01 00:00:00',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">降尘干重：</label></td>
					<td>
					<input name="dustDryWeight" value="${dust.dustDryWeight }" 
					class="x-form-text easyui-numberbox"
					data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">平均降尘干重：</label></td>
					<td>
					<input name="dustAvgDryWeight" value="${dust.dustAvgDryWeight }" 
					class="x-form-text easyui-numberbox"
					data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">沙尘种类：</label></td>
					<td>
						<select name="dustType" class="easyui-combobox" style="width:200px" 
							data-options="editable:false, required:true, panelHeight:100" validType="changeView('dataPick')">
							<option value="无" <c:if test="${dust.dustType eq '无' }">selected="selected"</c:if>>无</option>
							<option value="扬沙" <c:if test="${dust.dustType eq '扬沙' }">selected="selected"</c:if>>扬沙</option>
							<option value="沙尘暴" <c:if test="${dust.dustType eq '沙尘暴' }">selected="selected"</c:if>>沙尘暴</option>
						</select>
					</td>
				</tr>
				<tr id="dataPick"><td colspan="2">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="cn_fontright" width="100px"><label class="x-form-field">开始时间：</label></td>
							<td width="275px">
							<input name="dustStartTime" id="dustStartTime" class="x-form-text easyui-validatebox Wdate" 
							value="<fmt:formatDate value='${dust.dustStartTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',errDealMode:2,autoUpdateOnChanged:true})">
							</td>
						</tr>
						<tr>
							<td class="cn_fontright"><label class="x-form-field">结束时间：</label></td>
							<td>
							<input name="dustEndTime" id="dustEndTime" 
							class="x-form-text easyui-validatebox Wdate" 
							value="<fmt:formatDate value='${dust.dustEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
							onclick="WdatePicker({minDate:'#F{$dp.$D(\'dustStartTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',errDealMode:2,autoUpdateOnChanged:true})">
							</td>
						</tr>
					</table>
				</td></tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">备注：</label></td>
					<td>
						<textarea name="remarks" class="x-form-text easyui-validatebox" style="height: 50px">${dust.remarks }</textarea>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>