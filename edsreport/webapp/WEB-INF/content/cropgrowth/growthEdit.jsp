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
			url : '${ctx }/security/cropgrowth/save.action',
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
			<input type=hidden name="id" value="${cropGrowth.id }">
			<table style="width: 375px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">站点：</label></td>
					<td width="275px">
					<select name="stationId" class="easyui-combobox" style="width:200px;"
					data-options="editable:false,required:true,panelWidth:210">
						<c:forEach items="${stationList }" var="station">
						<c:choose>
							<c:when test="${station.stationId eq cropGrowth.stationId }">
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
						value="<fmt:formatDate value='${cropGrowth.operationTime}' pattern='yyyy-MM-dd'/>"
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
									<c:when test="${cropGrowth.cropName eq cropName }">
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
					<td class="cn_fontright"><label class="x-form-field">播种期：</label></td>
					<td>
					<input name="sowingDate" id="sowingDate" class="x-form-text easyui-validatebox Wdate" 
					value="<fmt:formatDate value='${cropGrowth.sowingDate}' pattern='yyyy-MM-dd' />"
					onclick="WdatePicker({maxDate:'#F{$dp.$D(\'seedlingDate\')}',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">出苗期：</label></td>
					<td>
					<input name="seedlingDate" id="seedlingDate" class="x-form-text easyui-validatebox Wdate" 
					value="<fmt:formatDate value='${cropGrowth.seedlingDate}' pattern='yyyy-MM-dd'/>"
					onclick="WdatePicker({minDate:'#F{$dp.$D(\'sowingDate\')}',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">三叶期：</label></td>
					<td>
					<input name="trefoilDate" id="trefoilDate" class="x-form-text easyui-validatebox Wdate" 
					value="<fmt:formatDate value='${cropGrowth.trefoilDate}' pattern='yyyy-MM-dd'/>"
					onclick="WdatePicker({minDate:'#F{$dp.$D(\'seedlingDate\')}',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">拔节期：</label></td>
					<td>
					<input name="jointingDate" id="jointingDate" class="x-form-text easyui-validatebox Wdate" 
					value="<fmt:formatDate value='${cropGrowth.jointingDate}' pattern='yyyy-MM-dd'/>"
					onclick="WdatePicker({minDate:'#F{$dp.$D(\'trefoilDate\')}',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">抽穗期：</label></td>
					<td>
					<input name="headingDate" id="headingDate" class="x-form-text easyui-validatebox Wdate" 
					value="<fmt:formatDate value='${cropGrowth.headingDate}' pattern='yyyy-MM-dd'/>"
					onclick="WdatePicker({minDate:'#F{$dp.$D(\'jointingDate\')}',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">抽雄期：</label></td>
					<td>
					<input name="tasselingDate" id="tasselingDate" class="x-form-text easyui-validatebox Wdate" 
					value="<fmt:formatDate value='${cropGrowth.tasselingDate}' pattern='yyyy-MM-dd'/>"
					onclick="WdatePicker({minDate:'#F{$dp.$D(\'headingDate\')}',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">开花期：</label></td>
					<td>
					<input name="floweringDate" id="floweringDate" class="x-form-text easyui-validatebox Wdate" 
					value="<fmt:formatDate value='${cropGrowth.floweringDate}' pattern='yyyy-MM-dd'/>"
					onclick="WdatePicker({minDate:'#F{$dp.$D(\'tasselingDate\')}',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">吐丝期：</label></td>
					<td>
					<input name="silkingDate" id="silkingDate" class="x-form-text easyui-validatebox Wdate" 
					value="<fmt:formatDate value='${cropGrowth.silkingDate}' pattern='yyyy-MM-dd'/>"
					onclick="WdatePicker({minDate:'#F{$dp.$D(\'floweringDate\')}',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">乳熟期：</label></td>
					<td>
					<input name="milkyDate" id="milkyDate" class="x-form-text easyui-validatebox Wdate" 
					value="<fmt:formatDate value='${cropGrowth.milkyDate}' pattern='yyyy-MM-dd'/>"
					onclick="WdatePicker({minDate:'#F{$dp.$D(\'silkingDate\')}',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">成熟期：</label></td>
					<td>
					<input name="maturityDate" id="maturityDate" class="x-form-text easyui-validatebox Wdate"  
					value="<fmt:formatDate value='${cropGrowth.maturityDate}' pattern='yyyy-MM-dd'/>"
					onclick="WdatePicker({minDate:'#F{$dp.$D(\'milkyDate\')}',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">生长高度：</label></td>
					<td>
					<input name="growthHeight" id="growthHeight" class="x-form-text easyui-numberbox" 
					value="${cropGrowth.growthHeight}" data-options="min:0.0,precision:2">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">生长状况：</label></td>
					<td>
					<input name="growthCondition" id="growthCondition" class="x-form-text easyui-validatebox" 
					 value="${cropGrowth.growthCondition}">
					</td>
				</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>