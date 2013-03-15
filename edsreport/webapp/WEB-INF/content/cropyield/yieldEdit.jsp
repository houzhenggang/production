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
			url : '${ctx }/security/cropyield/save.action',
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
			<input type=hidden name="id" value="${cropYield.id }">
			<table style="width: 375px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">站点：</label></td>
					<td>
					<select name="stationId" class="easyui-combobox" style="width:200px;"
					data-options="editable:false,required:true,panelWidth:210">
						<c:forEach items="${stationList }" var="station">
						<c:choose>
							<c:when test="${station.stationId eq cropYield.stationId }">
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
						value="<fmt:formatDate value='${cropYield.operationTime}' pattern='yyyy-MM-dd'/>"
						onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">农作物名称：</label></td>
					<td>
						<select name="cropName" class="easyui-combobox" style="width:200px;"
						data-options="required:true" id="cropName">
							<c:forEach items="${cropNames }" var="cropName">
								<c:choose>
									<c:when test="${cropYield.cropName eq cropName }">
										<option value="${cropName }" selected="selected">${cropName }</option>
									</c:when>
									<c:otherwise>
										<option value="${cropName }">${cropName }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">小麦穗长：</label></td>
					<td>
					<input name="wheatLength" id="wheatLength" class="x-form-text easyui-numberbox" 
					value="${cropYield.wheatLength}" data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">小穗数：</label></td>
					<td>
					<input name="wheatCount" id="wheatCount" class="x-form-text easyui-numberbox" 
					value="${cropYield.wheatCount}" data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">不孕小穗数：</label></td>
					<td>
					<input name="infertilityWheatCount" id="infertilityWheatCount" class="x-form-text easyui-numberbox" 
					value="${cropYield.infertilityWheatCount}" data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">结实粒数：</label></td>
					<td>
					<input name="solidCount" id="solidCount" class="x-form-text easyui-numberbox" 
					value="${cropYield.solidCount}" data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">茎粗：</label></td>
					<td>
					<input name="stemWidth" id="stemWidth" class="x-form-text easyui-numberbox" 
					value="${cropYield.stemWidth}" data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">果穗长：</label></td>
					<td>
					<input name="cropEarLength" id="cropEarLength" class="x-form-text easyui-numberbox" 
					value="${cropYield.cropEarLength}" data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">果穗粗：</label></td>
					<td>
					<input name="cropEarWidth" id="cropEarWidth" class="x-form-text easyui-numberbox" 
					value="${cropYield.cropEarWidth}" data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">双穗率：</label></td>
					<td>
					<input name="doubleEarCount" id="doubleEarCount" class="x-form-text easyui-validatebox"
					value="${cropYield.doubleEarCount}">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">农作物产量：</label></td>
					<td>
					<input name="cropYield" id="cropYield" class="x-form-text easyui-numberbox" 
					 value="${cropYield.cropYield}" data-options="min:0.0,precision:2">
					 <label class="x-form-field">公斤/公顷</label>
					</td>
				</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>