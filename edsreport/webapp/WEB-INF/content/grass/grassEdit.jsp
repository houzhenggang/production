<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function findLastedRecord(value){
		if(value == ""){
			return;
		}
		$.ajax({
			type : 'post',
			url : '${ctx}/security/grass/getLasredGrassRecord.action?stationId='+value,
			dataType : 'json',
			async : false,
			success : function(data){
				$("#monitorArea").combobox("setValue", data.monitorArea);
				$('#grassName').combobox('setValue', data.grassName);
				$('#backDate').datebox('setValue', data.backDate);
				$('#flowerDate').datebox('setValue', data.flowerDate);
				$('#yellowDate').datebox('setValue', data.yellowDate);
			}
		});
	}

	function submitForm(){
		$("#userForm").form('submit',{
			url : '${ctx }/security/grass/save.action',
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
	$(document).ready(function(){
		if('${grass.id }' == ''){
			var stationId = $('#stationId').combobox('getValue');
			findLastedRecord(stationId);
		}
	});
</script>
</head>
<body style="overflow-y: auto; overflow-x: hidden;padding: 0px; background-color: #fafafa">
	<form id="userForm" name="userForm" class="form" method="post" novalidate>
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">基本信息——<font class="tip-red">请认真填写(填写XXXXX表示无此监测项目)</font></legend>
			<input type=hidden name="id" value="${grass.id }">
			<table style="width: 375px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">站点：</label></td>
					<td>
					<select id="stationId" name="stationId" class="easyui-combobox" style="width:200px;"
					data-options="editable:false,required:true,panelWidth:210,onSelect: function(rec){
													findLastedRecord(rec.value);
												}">
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
					<td class="cn_fontright" width="100px">
						<label class="x-form-field">录入时间：</label>
					</td>
					<td>
						<input name="operationTime" id="operationTime" class="x-form-text easyui-validatebox Wdate" 
						required="true" 
						value="<fmt:formatDate value='${grass.operationTime}' pattern='yyyy-MM-dd'/>"
						onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">监测地段：</label></td>
					<td>
					<select name="monitorArea" id="monitorArea" class="easyui-combobox" style="width:200px"
					data-options="required:true">
							<option value="监测场" <c:if test="${grass.monitorArea eq '监测场' }">selected="selected"</c:if>>监测场</option>
							<option value="监测区" <c:if test="${grass.monitorArea eq '监测区' }">selected="selected"</c:if>>监测区</option>
							<c:if test="${grass.monitorArea ne '监测场' && grass.monitorArea ne '监测区' }">
								<option value="${grass.monitorArea }" selected="selected">${grass.monitorArea }</option>
							</c:if>
					</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">牧草名称：</label></td>
					<td>
					<select name="grassName" id="grassName" class="easyui-combobox" style="width:200px"
					data-options="required:true">
							<option value="牧草鲜重" <c:if test="${grass.grassName eq '牧草鲜重' }">selected="selected"</c:if>>牧草鲜重</option>
							<option value="混合草本" <c:if test="${grass.grassName eq '混合草本' }">selected="selected"</c:if>>混合草本</option>
							<c:forEach items="${grassNames }" var="grassName">
								<c:if test="${grassName ne '牧草鲜重' && grassName ne '混合草本' }">
									<c:choose>
										<c:when test="${grass.grassName eq grassName }">
											<option value="${grassName }" selected="selected">${grassName }</option>
										</c:when>
										<c:otherwise>
											<option value="${grassName }">${grassName }</option>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">返青期：</label></td>
					<td>
					<input name="backDate" id="backDate" 
					value="<c:choose><c:when test="${empty grass.backDate }">XXXXX</c:when><c:otherwise>${grass.backDate}</c:otherwise></c:choose>" 
					class="x-form-text easyui-validatebox easyui-datebox" style="width: 200px">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">开花期：</label></td>
					<td>
					<input name="flowerDate" id="flowerDate" 
					value="<c:choose><c:when test="${empty grass.flowerDate }">XXXXX</c:when><c:otherwise>${grass.flowerDate}</c:otherwise></c:choose>" 
					class="x-form-text easyui-validatebox easyui-datebox" style="width: 200px">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">黄枯期：</label></td>
					<td>
					<input name="yellowDate" id="yellowDate" 
					value="<c:choose><c:when test="${empty grass.yellowDate }">XXXXX</c:when><c:otherwise>${grass.yellowDate}</c:otherwise></c:choose>"
					class="x-form-text easyui-validatebox easyui-datebox" style="width: 200px">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">绝对高度：</label></td>
					<td>
					<input name="absoluteHeigth" id="absoluteHeigth" class="x-form-text easyui-validatebox" 
					value="<c:choose><c:when test="${empty grass.absoluteHeigth }">XXXXX</c:when><c:otherwise>${grass.absoluteHeigth}</c:otherwise></c:choose>">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">草层高度：</label></td>
					<td>
					<input name="grassHeigth" id="grassHeigth" class="x-form-text easyui-validatebox" 
					value="<c:choose><c:when test="${empty grass.grassHeigth }">XXXXX</c:when><c:otherwise>${grass.grassHeigth}</c:otherwise></c:choose>">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">盖度：</label></td>
					<td>
					<input name="coverDegree" id="coverDegree" class="x-form-text easyui-validatebox" 
					value="<c:choose><c:when test="${empty grass.coverDegree }">XXXXX</c:when><c:otherwise>${grass.coverDegree}</c:otherwise></c:choose>">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">地上生物量：</label></td>
					<td>
					<input name="earthBiomass" id="earthBiomass" class="x-form-text" 
					 value="<c:choose><c:when test="${empty grass.earthBiomass }">XXXXX</c:when><c:otherwise>${grass.earthBiomass}</c:otherwise></c:choose>">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">优良牧草比例：</label></td>
					<td>
					<input name="betterGrassRate" id="betterGrassRate" class="x-form-text easyui-validatebox" 
					 value="<c:choose><c:when test="${empty grass.betterGrassRate }">XXXXX</c:when><c:otherwise>${grass.betterGrassRate}</c:otherwise></c:choose>">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">备注：</label></td>
					<td>
					<textarea name="remarks" id="remarks" class="x-form-text easyui-validatebox"  style="height: 50px">${grass.remarks}</textarea>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>