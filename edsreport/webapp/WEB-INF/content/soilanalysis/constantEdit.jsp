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
			url : '${ctx }/security/soilanalysis/constantSave.action',
			onSubmit: function(){
				$('#stationId').combo("enable");
				var result = $(this).form('validate');
				if(!result){
					$('#stationId').combo("disable");
				}
				return result;
			},
			success: function(result){
				if(result == "1"){
					$.messager.alert('系统提示', '<br>操作成功', "info", function(){
						parent.CloseWin("myWindows");
						parent.location.reload();
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
		<input type="hidden" name="changeFlag" id="changeFlag" value="${soilConstant.stationId }">
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">基本信息——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 425px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="120px"><label class="x-form-field">站点：</label></td>
					<td colspan="2">
					<select id="stationId" name="stationId" class="easyui-combobox" style="width:200px;"
					data-options="editable:false,required:true, panelWidth:210 ${(empty soilConstant.stationId) ? '':',disabled:true' }"
					validType="constantIsExist['${ctx }']">
						<c:forEach items="${stationList }" var="station">
						<c:choose>
							<c:when test="${station.stationId eq soilConstant.stationId }">
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
					<td colspan="2">
						<a href="#" class="easyui-linkbutton" iconCls="icon-help" plain="true" style="color: red; font-weight: bold">
						无可操作站点，请联系管理员</a>
					</td></tr>
				</c:if>
				<tr>
					<td class="cn_fontright" width="120px"><label class="x-form-field">报文后缀名：</label></td>
					<td width="205px">
						<input name="reportSuffix" value="${soilConstant.reportSuffix }" 
						class="x-form-text easyui-validatebox"
						data-options="required:true">
					</td>
					<td><font class="tip-red">*不包括点（.）</font></td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">田间持水量(%)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 370px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="120px"><label class="x-form-field">0-10CM：</label></td>
					<td>
						<input name="fieldCapacity10" value="${soilConstant.fieldCapacity10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">10-20CM：</label></td>
					<td>
						<input name="fieldCapacity20" value="${soilConstant.fieldCapacity20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">20-30CM：</label></td>
					<td>
						<input name="fieldCapacity30" value="${soilConstant.fieldCapacity30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">30-40CM：</label></td>
					<td>
						<input name="fieldCapacity40" value="${soilConstant.fieldCapacity40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">40-50CM：</label></td>
					<td>
						<input name="fieldCapacity50" value="${soilConstant.fieldCapacity50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">凋萎湿度(%)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 370px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="120px"><label class="x-form-field">0-10CM：</label></td>
					<td>
						<input name="wiltingMoisture10" value="${soilConstant.wiltingMoisture10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">10-20CM：</label></td>
					<td>
						<input name="wiltingMoisture20" value="${soilConstant.wiltingMoisture20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">20-30CM：</label></td>
					<td>
						<input name="wiltingMoisture30" value="${soilConstant.wiltingMoisture30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">30-40CM：</label></td>
					<td>
						<input name="wiltingMoisture40" value="${soilConstant.wiltingMoisture40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">40-50CM：</label></td>
					<td>
						<input name="wiltingMoisture50" value="${soilConstant.wiltingMoisture50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">土壤容重(克/立方厘米)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 370px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="120px"><label class="x-form-field">0-10CM：</label></td>
					<td>
						<input name="soilDensity10" value="${soilConstant.soilDensity10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">10-20CM：</label></td>
					<td>
						<input name="soilDensity20" value="${soilConstant.soilDensity20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">20-30CM：</label></td>
					<td>
						<input name="soilDensity30" value="${soilConstant.soilDensity30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">30-40CM：</label></td>
					<td>
						<input name="soilDensity40" value="${soilConstant.soilDensity40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">40-50CM：</label></td>
					<td>
						<input name="soilDensity50" value="${soilConstant.soilDensity50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">样点1各层盒重数据(单位：克)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 370px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="120px"><label class="x-form-field">10CM盒重(0001)：</label></td>
					<td>
						<input name="boxWeight1_10" value="${soilConstant.boxWeight1_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">20CM盒重(0002)：</label></td>
					<td>
						<input name="boxWeight1_20" value="${soilConstant.boxWeight1_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">30CM盒重(0003)：</label></td>
					<td>
						<input name="boxWeight1_30" value="${soilConstant.boxWeight1_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">40CM盒重(0004)：</label></td>
					<td>
						<input name="boxWeight1_40" value="${soilConstant.boxWeight1_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">50CM盒重(0005)：</label></td>
					<td>
						<input name="boxWeight1_50" value="${soilConstant.boxWeight1_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">样点2各层盒重数据(单位：克)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 370px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="120px"><label class="x-form-field">10CM盒重(0006)：</label></td>
					<td>
						<input name="boxWeight2_10" value="${soilConstant.boxWeight2_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">20CM盒重(0007)：</label></td>
					<td>
						<input name="boxWeight2_20" value="${soilConstant.boxWeight2_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">30CM盒重(0008)：</label></td>
					<td>
						<input name="boxWeight2_30" value="${soilConstant.boxWeight2_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">40CM盒重(0009)：</label></td>
					<td>
						<input name="boxWeight2_40" value="${soilConstant.boxWeight2_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">50CM盒重(0010)：</label></td>
					<td>
						<input name="boxWeight2_50" value="${soilConstant.boxWeight2_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">样点3各层盒重数据(单位：克)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 370px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="120px"><label class="x-form-field">10CM盒重(0011)：</label></td>
					<td>
						<input name="boxWeight3_10" value="${soilConstant.boxWeight3_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">20CM盒重(0012)：</label></td>
					<td>
						<input name="boxWeight3_20" value="${soilConstant.boxWeight3_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">30CM盒重(0013)：</label></td>
					<td>
						<input name="boxWeight3_30" value="${soilConstant.boxWeight3_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">40CM盒重(0014)：</label></td>
					<td>
						<input name="boxWeight3_40" value="${soilConstant.boxWeight3_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">50CM盒重(0015)：</label></td>
					<td>
						<input name="boxWeight3_50" value="${soilConstant.boxWeight3_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">样点4各层盒重数据(单位：克)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 370px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="120px"><label class="x-form-field">10CM盒重(0016)：</label></td>
					<td>
						<input name="boxWeight4_10" value="${soilConstant.boxWeight4_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">20CM盒重(0017)：</label></td>
					<td>
						<input name="boxWeight4_20" value="${soilConstant.boxWeight4_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">30CM盒重(0018)：</label></td>
					<td>
						<input name="boxWeight4_30" value="${soilConstant.boxWeight4_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">40CM盒重(0019)：</label></td>
					<td>
						<input name="boxWeight4_40" value="${soilConstant.boxWeight4_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">50CM盒重(0020)：</label></td>
					<td>
						<input name="boxWeight4_50" value="${soilConstant.boxWeight4_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>