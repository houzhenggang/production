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
			url : '${ctx }/security/soil/save.action',
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
			<input type=hidden name="id" value="${soil.id }">
			<table style="width: 675px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="120px"><label class="x-form-field">站点：</label></td>
					<td width="200px">
					<select name="stationId" class="easyui-combobox" style="width:200px;"
					data-options="editable:false,required:true,panelWidth:210">
						<c:forEach items="${stationList }" var="station">
						<c:choose>
							<c:when test="${station.stationId eq soil.stationId }">
								<option value="${station.stationId }" selected="selected">${station.stationName}</option>
							</c:when>
							<c:otherwise>
								<option value="${station.stationId }">${station.stationName}</option>
							</c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
					</td>
					<td class="cn_fontright" width="120px">
						<label class="x-form-field">录入时间：</label>
					</td>
					<td>
						<input name="operationTime" id="operationTime" class="x-form-text easyui-validatebox Wdate" 
						required="true"
						value="<fmt:formatDate value='${soil.operationTime}' pattern='yyyy-MM-01'/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-01',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<c:if test="${empty stationList }">
					<tr>
					<td></td>
					<td colspan="3">
						<a href="#" class="easyui-linkbutton" iconCls="icon-help" plain="true" style="color: red; font-weight: bold">
						无可操作站点，请联系管理员</a>
					</td></tr>
				</c:if>
				<tr>
					
					<!-- 
					<td class="cn_fontright" width="120px">
						<label class="x-form-field">监测年月：</label>
					</td>
					<td>
						<input name="soilMonitorDate" id="soilMonitorDate" class="x-form-text easyui-validatebox Wdate" 
						required="true"
						value="<fmt:formatDate value='${soil.soilMonitorDate}' pattern='yyyy年MM月01日'/>"
						onclick="WdatePicker({dateFmt:'yyyy年MM月01日',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
					 -->
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">东西区域内1：</label></td>
					<td>
						<input name="innerSoilErosion1" value="${soil.innerSoilErosion1 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">东西区域外1：</label></td>
					<td>
						<input name="outSoilErosion1" value="${soil.outSoilErosion1 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">东西区域内2：</label></td>
					<td>
						<input name="innerSoilErosion2" value="${soil.innerSoilErosion2 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">东西区域外2：</label></td>
					<td>
						<input name="outSoilErosion2" value="${soil.outSoilErosion2 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">东西区域内3：</label></td>
					<td>
						<input name="innerSoilErosion3" value="${soil.innerSoilErosion3 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">东西区域外3：</label></td>
					<td>
						<input name="outSoilErosion3" value="${soil.outSoilErosion3 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">东西区域内4：</label></td>
					<td>
						<input name="innerSoilErosion4" value="${soil.innerSoilErosion4 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">东西区域外4：</label></td>
					<td>
						<input name="outSoilErosion4" value="${soil.outSoilErosion4 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">东西区域内5：</label></td>
					<td>
						<input name="innerSoilErosion5" value="${soil.innerSoilErosion5 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">东西区域外5：</label></td>
					<td>
						<input name="outSoilErosion5" value="${soil.outSoilErosion5 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">东西区域内6：</label></td>
					<td>
						<input name="innerSoilErosion6" value="${soil.innerSoilErosion6 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">东西区域外6：</label></td>
					<td>
						<input name="outSoilErosion6" value="${soil.outSoilErosion6 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">东西区域内7：</label></td>
					<td>
						<input name="innerSoilErosion7" value="${soil.innerSoilErosion7 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">东西区域外7：</label></td>
					<td>
						<input name="outSoilErosion7" value="${soil.outSoilErosion7 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">东西区域内8：</label></td>
					<td>
						<input name="innerSoilErosion8" value="${soil.innerSoilErosion8 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">东西区域外8：</label></td>
					<td>
						<input name="outSoilErosion8" value="${soil.outSoilErosion8 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">东西区域内9：</label></td>
					<td>
						<input name="innerSoilErosion9" value="${soil.innerSoilErosion9 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">东西区域外9：</label></td>
					<td>
						<input name="outSoilErosion9" value="${soil.outSoilErosion9 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">东西区域内10：</label></td>
					<td>
						<input name="innerSoilErosion10" value="${soil.innerSoilErosion10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">东西区域外10：</label></td>
					<td>
						<input name="outSoilErosion10" value="${soil.outSoilErosion10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">南北区域内1：</label></td>
					<td>
						<input name="innerSoilErosion11" value="${soil.innerSoilErosion11 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">南北区域外1：</label></td>
					<td>
						<input name="outSoilErosion11" value="${soil.outSoilErosion11 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">南北区域内2：</label></td>
					<td>
						<input name="innerSoilErosion12" value="${soil.innerSoilErosion12 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">南北区域外2：</label></td>
					<td>
						<input name="outSoilErosion12" value="${soil.outSoilErosion12 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">南北区域内3：</label></td>
					<td>
						<input name="innerSoilErosion13" value="${soil.innerSoilErosion13 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">南北区域外3：</label></td>
					<td>
						<input name="outSoilErosion13" value="${soil.outSoilErosion13 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">南北区域内4：</label></td>
					<td>
						<input name="innerSoilErosion14" value="${soil.innerSoilErosion14 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">南北区域外4：</label></td>
					<td>
						<input name="outSoilErosion14" value="${soil.outSoilErosion14 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">南北区域内5：</label></td>
					<td>
						<input name="innerSoilErosion15" value="${soil.innerSoilErosion15 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">南北区域外5：</label></td>
					<td>
						<input name="outSoilErosion15" value="${soil.outSoilErosion15 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">南北区域内6：</label></td>
					<td>
						<input name="innerSoilErosion16" value="${soil.innerSoilErosion16 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">南北区域外6：</label></td>
					<td>
						<input name="outSoilErosion16" value="${soil.outSoilErosion16 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">南北区域内7：</label></td>
					<td>
						<input name="innerSoilErosion17" value="${soil.innerSoilErosion17 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">南北区域外7：</label></td>
					<td>
						<input name="outSoilErosion17" value="${soil.outSoilErosion17 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">南北区域内8：</label></td>
					<td>
						<input name="innerSoilErosion18" value="${soil.innerSoilErosion18 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">南北区域外8：</label></td>
					<td>
						<input name="outSoilErosion18" value="${soil.outSoilErosion18 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">南北区域内9：</label></td>
					<td>
						<input name="innerSoilErosion19" value="${soil.innerSoilErosion19 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">南北区域外9：</label></td>
					<td>
						<input name="outSoilErosion19" value="${soil.outSoilErosion19 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">南北区域内10：</label></td>
					<td>
						<input name="innerSoilErosion20" value="${soil.innerSoilErosion20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
					<td class="cn_fontright"><label class="x-form-field">南北区域外10：</label></td>
					<td>
						<input name="outSoilErosion20" value="${soil.outSoilErosion20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:1">
					</td>
				</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>