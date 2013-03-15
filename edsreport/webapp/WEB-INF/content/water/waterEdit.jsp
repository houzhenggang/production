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
			url : '${ctx }/security/water/save.action',
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
			<input type=hidden name="id" value="${water.id }">
			<table style="width: 395px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">站点：</label></td>
					<td width="295px">
					<select name="stationId" class="easyui-combobox" style="width:200px;"
					data-options="editable:false,required:true,panelWidth:210">
						<c:forEach items="${stationList }" var="station">
						<c:choose>
							<c:when test="${station.stationId eq water.stationId }">
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
						value="<fmt:formatDate value='${water.operationTime}' pattern='yyyy-MM-dd'/>"
						onclick="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',errDealMode:2,autoUpdateOnChanged:true})">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">水体名称：</label></td>
					<td>
						<select name="waterName" class="easyui-combobox" style="width:200px;"
						data-options="required:true" id="waterName">
							<c:forEach items="${datadicts.waterNames }" var="waterName">
								<c:choose>
									<c:when test="${water.waterName eq waterName }">
										<option value="${waterName }" selected="selected">${waterName }</option>
									</c:when>
									<c:otherwise>
										<option value="${waterName }">${waterName }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">取样点经度：</label></td>
					<td>
						<select name="fetchLongitude" class="easyui-combobox" style="width:200px;"
						data-options="required:true" id="waterName">
							<c:forEach items="${datadicts.fetchLongitudes }" var="fetchLongitude">
								<c:choose>
									<c:when test="${water.fetchLongitude eq fetchLongitude }">
										<option value="${fetchLongitude }" selected="selected">${fetchLongitude }</option>
									</c:when>
									<c:otherwise>
										<option value="${fetchLongitude }">${fetchLongitude }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">取样点纬度：</label></td>
					<td>
						<select name="fetchLatitude" class="easyui-combobox" style="width:200px;"
						data-options="required:true" id="waterName">
							<c:forEach items="${datadicts.fetchLatitudes }" var="fetchLatitude">
								<c:choose>
									<c:when test="${water.fetchLatitude eq fetchLatitude }">
										<option value="${fetchLatitude }" selected="selected">${fetchLatitude }</option>
									</c:when>
									<c:otherwise>
										<option value="${fetchLatitude }">${fetchLatitude }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">拐点经度：</label></td>
					<td>
						<select name="turnLongitude" class="easyui-combobox" style="width:200px;" id="waterName">
							<c:forEach items="${datadicts.turnLongitudes }" var="turnLongitude">
								<c:choose>
									<c:when test="${water.turnLongitude eq turnLongitude }">
										<option value="${turnLongitude }" selected="selected">${turnLongitude }</option>
									</c:when>
									<c:otherwise>
										<option value="${turnLongitude }">${turnLongitude }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">拐点纬度：</label></td>
					<td>
						<select name="turnLatitude" class="easyui-combobox" style="width:200px;" id="waterName">
							<c:forEach items="${datadicts.turnLatitudes }" var="turnLatitude">
								<c:choose>
									<c:when test="${water.turnLatitude eq turnLatitude }">
										<option value="${turnLatitude }" selected="selected">${turnLatitude }</option>
									</c:when>
									<c:otherwise>
										<option value="${turnLatitude }">${turnLatitude }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">水体面积：</label></td>
					<td>
					<input name="waterArea" value="${water.waterArea }" class="x-form-text easyui-validatebox" required="true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">水位：</label></td>
					<td>
					<input name="waterLevel" value="${water.waterLevel }" class="x-form-text easyui-validatebox" required="true">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">透明度：</label></td>
					<td>
					<input name="waterOpacity" value="${water.waterOpacity }" class="x-form-text easyui-validatebox">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">水体颜色：</label></td>
					<td>
						<select name="waterColor" class="easyui-combobox" style="width:200px;"
						data-options="required:true" id="waterName">
							<c:forEach items="${datadicts.waterColors }" var="waterColor">
								<c:choose>
									<c:when test="${water.waterColor eq waterColor }">
										<option value="${waterColor }" selected="selected">${waterColor }</option>
									</c:when>
									<c:otherwise>
										<option value="${waterColor }">${waterColor }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">水体温度：</label></td>
					<td>
					<input name="waterTemperature" value="${water.waterTemperature }" class="x-form-text easyui-validatebox">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">水体PH值：</label></td>
					<td>
					<input name="waterPh" value="${water.waterPh }" class="x-form-text easyui-validatebox">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">全盐含量：</label></td>
					<td>
					<input name="waterTotalSalt" value="${water.waterTotalSalt }" class="x-form-text easyui-validatebox">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">氯化物含量：</label></td>
					<td>
					<input name="waterChlorine" value="${water.waterChlorine }" class="x-form-text easyui-validatebox">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">硫化物含量：</label></td>
					<td>
					<input name="waterSulfide" value="${water.waterSulfide }" class="x-form-text easyui-validatebox">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">备注：</label></td>
					<td>
					<textarea name="remarks" class="x-form-text easyui-validatebox" style="height: 50px">${water.remarks }</textarea>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>