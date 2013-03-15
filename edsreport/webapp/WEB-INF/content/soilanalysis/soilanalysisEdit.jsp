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
			url : '${ctx }/security/soilanalysis/save.action',
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
			<input type=hidden name="id" id="id" value="${soilAnalysis.id }">
			<table style="width: 670px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="110px"><label class="x-form-field">站点：</label></td>
					<td width="205px">
					<select name="stationId" id="stationId" class="easyui-combobox" style="width:200px;"
					data-options="editable:false,required:true,panelWidth:210">
						<c:forEach items="${stationList }" var="station">
						<c:choose>
							<c:when test="${station.stationId eq soilAnalysis.stationId }">
								<option value="${station.stationId }" selected="selected">${station.stationName}</option>
							</c:when>
							<c:otherwise>
								<option value="${station.stationId }">${station.stationName}</option>
							</c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
					<input type="hidden" value="${soilAnalysis.stationId }" id="reportStationId">
					</td>
					<td class="cn_fontright" width="110px">
						<label class="x-form-field">录入时间：</label>
					</td>
					<td>
						<input name="operationTime" id="operationTime" class="x-form-text easyui-validatebox Wdate" 
						required="true"  validType="checkReportIsExist('${ctx }')" 
						value="<fmt:formatDate value='${soilAnalysis.operationTime}' pattern='yyyy-MM-dd'/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',opposite:true,disabledDates:['6$'],errDealMode:2,autoUpdateOnChanged:true})">
						<input type="hidden" value="<fmt:formatDate value='${soilAnalysis.operationTime}' pattern='yyyy-MM-dd'/>" id="reportStartDate">
					</td>
				</tr>
				<c:if test="${empty stationList }">
					<tr>
					<td></td>
					<td colspan="3">
						<a href="javascript:parent.window.location.href='${ctx }/security/soilanalysis/constant.action'" class="easyui-linkbutton" iconCls="icon-help" plain="true" style="color: red; font-weight: bold">
						缺少已初始化站点水文常量的监测站点</a>
					</td></tr>
				</c:if>
				<!-- 
				<tr>
					<td class="cn_fontright" width="110px">
						<label class="x-form-field">监测年月：</label>
					</td>
					<td colspan="3">
						<input name="soilAnalysisDate" id="soilAnalysisDate" class="x-form-text easyui-validatebox Wdate" 
						required="required"
						value="<fmt:formatDate value='${soilAnalysis.soilAnalysisDate}' pattern='yyyy年MM月dd日'/>"
						onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',opposite:true,disabledDates:['6$'],errDealMode:2,autoUpdateOnChanged:true})" 
						validType="checkReportIsExist('${ctx }')">
						<input type="hidden" value="<fmt:formatDate value='${soilAnalysis.soilAnalysisDate}' pattern='yyyy年MM月dd日'/>" id="reportStartDate">
					</td>
				</tr>
				 -->
			</table>
		</fieldset>
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">干土层厚度(单位：CM)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 670px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="110px"><label class="x-form-field">干土层厚度：</label></td>
					<td width="205px" >
						<input name="drySoilHeight" value="${soilAnalysis.drySoilHeight }" 
						class="x-form-text easyui-numberbox"
						data-options="min:1,max:9999,precision:0">
					</td>
					<td><font class="tip-red">请填写整数(如：100)</font></td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">样点1各层数据(单位：克)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 670px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="110px"><label class="x-form-field">10CM湿重(0001)：</label></td>
					<td width="205px">
						<input name="wetSoilboxWeight1_10" value="${soilAnalysis.wetSoilboxWeight1_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright" width="110px"><label class="x-form-field">10CM干重(0001)：</label></td>
					<td>
						<input name="drySoilboxWeight1_10" value="${soilAnalysis.drySoilboxWeight1_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">20CM湿重(0002)：</label></td>
					<td>
						<input name="wetSoilboxWeight1_20" value="${soilAnalysis.wetSoilboxWeight1_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">20CM干重(0002)：</label></td>
					<td>
						<input name="drySoilboxWeight1_20" value="${soilAnalysis.drySoilboxWeight1_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">30CM湿重(0003)：</label></td>
					<td>
						<input name="wetSoilboxWeight1_30" value="${soilAnalysis.wetSoilboxWeight1_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">30CM干重(0003)：</label></td>
					<td>
						<input name="drySoilboxWeight1_30" value="${soilAnalysis.drySoilboxWeight1_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">40CM湿重(0004)：</label></td>
					<td>
						<input name="wetSoilboxWeight1_40" value="${soilAnalysis.wetSoilboxWeight1_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">40CM干重(0004)：</label></td>
					<td>
						<input name="drySoilboxWeight1_40" value="${soilAnalysis.drySoilboxWeight1_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">50CM湿重(0005)：</label></td>
					<td>
						<input name="wetSoilboxWeight1_50" value="${soilAnalysis.wetSoilboxWeight1_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">50CM干重(0005)：</label></td>
					<td>
						<input name="drySoilboxWeight1_50" value="${soilAnalysis.drySoilboxWeight1_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">样点2各层数据(单位：克)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 670px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="110px"><label class="x-form-field">10CM湿重(0006)：</label></td>
					<td width="205px">
						<input name="wetSoilboxWeight2_10" value="${soilAnalysis.wetSoilboxWeight2_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright" width="110px"><label class="x-form-field">10CM干重(0006)：</label></td>
					<td>
						<input name="drySoilboxWeight2_10" value="${soilAnalysis.drySoilboxWeight2_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">20CM湿重(0007)：</label></td>
					<td>
						<input name="wetSoilboxWeight2_20" value="${soilAnalysis.wetSoilboxWeight2_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">20CM干重(0007)：</label></td>
					<td>
						<input name="drySoilboxWeight2_20" value="${soilAnalysis.drySoilboxWeight2_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">30CM湿重(0008)：</label></td>
					<td>
						<input name="wetSoilboxWeight2_30" value="${soilAnalysis.wetSoilboxWeight2_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">30CM干重(0008)：</label></td>
					<td>
						<input name="drySoilboxWeight2_30" value="${soilAnalysis.drySoilboxWeight2_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">40CM湿重(0009)：</label></td>
					<td>
						<input name="wetSoilboxWeight2_40" value="${soilAnalysis.wetSoilboxWeight2_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">40CM干重(0009)：</label></td>
					<td>
						<input name="drySoilboxWeight2_40" value="${soilAnalysis.drySoilboxWeight2_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">50CM湿重(0010)：</label></td>
					<td>
						<input name="wetSoilboxWeight2_50" value="${soilAnalysis.wetSoilboxWeight2_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">50CM干重(0010)：</label></td>
					<td>
						<input name="drySoilboxWeight2_50" value="${soilAnalysis.drySoilboxWeight2_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">样点3各层数据(单位：克)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 670px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="110px"><label class="x-form-field">10CM湿重(0011)：</label></td>
					<td width="205px">
						<input name="wetSoilboxWeight3_10" value="${soilAnalysis.wetSoilboxWeight3_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright" width="110px"><label class="x-form-field">10CM干重(0011)：</label></td>
					<td>
						<input name="drySoilboxWeight3_10" value="${soilAnalysis.drySoilboxWeight3_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">20CM湿重(0012)：</label></td>
					<td>
						<input name="wetSoilboxWeight3_20" value="${soilAnalysis.wetSoilboxWeight3_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">20CM干重(0012)：</label></td>
					<td>
						<input name="drySoilboxWeight3_20" value="${soilAnalysis.drySoilboxWeight3_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">30CM湿重(0013)：</label></td>
					<td>
						<input name="wetSoilboxWeight3_30" value="${soilAnalysis.wetSoilboxWeight3_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">30CM干重(0013)：</label></td>
					<td>
						<input name="drySoilboxWeight3_30" value="${soilAnalysis.drySoilboxWeight3_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">40CM湿重(0014)：</label></td>
					<td>
						<input name="wetSoilboxWeight3_40" value="${soilAnalysis.wetSoilboxWeight3_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">40CM干重(0014)：</label></td>
					<td>
						<input name="drySoilboxWeight3_40" value="${soilAnalysis.drySoilboxWeight3_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">50CM湿重(0015)：</label></td>
					<td>
						<input name="wetSoilboxWeight3_50" value="${soilAnalysis.wetSoilboxWeight3_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">50CM干重(0015)：</label></td>
					<td>
						<input name="drySoilboxWeight3_50" value="${soilAnalysis.drySoilboxWeight3_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">样点4各层数据(单位：克)——<font class="tip-red">请认真填写</font></legend>
			<table style="width: 670px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="110px"><label class="x-form-field">10CM湿重(0016)：</label></td>
					<td width="205px">
						<input name="wetSoilboxWeight4_10" value="${soilAnalysis.wetSoilboxWeight4_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright" width="110px"><label class="x-form-field">10CM干重(0016)：</label></td>
					<td>
						<input name="drySoilboxWeight4_10" value="${soilAnalysis.drySoilboxWeight4_10 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">20CM湿重(0017)：</label></td>
					<td>
						<input name="wetSoilboxWeight4_20" value="${soilAnalysis.wetSoilboxWeight4_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">20CM干重(0017)：</label></td>
					<td>
						<input name="drySoilboxWeight4_20" value="${soilAnalysis.drySoilboxWeight4_20 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">30CM湿重(0018)：</label></td>
					<td>
						<input name="wetSoilboxWeight4_30" value="${soilAnalysis.wetSoilboxWeight4_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">30CM干重(0018)：</label></td>
					<td>
						<input name="drySoilboxWeight4_30" value="${soilAnalysis.drySoilboxWeight4_30 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">40CM湿重(0019)：</label></td>
					<td>
						<input name="wetSoilboxWeight4_40" value="${soilAnalysis.wetSoilboxWeight4_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">40CM干重(0019)：</label></td>
					<td>
						<input name="drySoilboxWeight4_40" value="${soilAnalysis.drySoilboxWeight4_40 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">50CM湿重(0020)：</label></td>
					<td>
						<input name="wetSoilboxWeight4_50" value="${soilAnalysis.wetSoilboxWeight4_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
					<td class="cn_fontright"><label class="x-form-field">50CM干重(0020)：</label></td>
					<td>
						<input name="drySoilboxWeight4_50" value="${soilAnalysis.drySoilboxWeight4_50 }" 
						class="x-form-text easyui-numberbox"
						data-options="min:0.0,precision:2,required:true">
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>