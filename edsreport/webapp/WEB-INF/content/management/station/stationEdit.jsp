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
			url : '${ctx }/security/management/station/save.action',
			onSubmit: function(){
				return $(this).form('validate');
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
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">基本信息——<font class="tip-red">请认真填写(编辑站点时不能修改站号)</font></legend>
			<input type="hidden" id="isAdd" name="isAdd" value="${station.stationId }">
			<table style="width: 395px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="100px"><label class="x-form-field">站号：</label></td>
					<td width="295px">
					<input id="stationId" name="stationId" value="${station.stationId }" class="x-form-text easyui-validatebox" required="true"
					<c:if test="${!empty station.stationId }">readonly='readonly'</c:if> 
					validType="minlength(5),checkStationExist('${ctx }')">
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">站名：</label></td>
					<td><input name="stationName" value="${station.stationName }" class="x-form-text easyui-validatebox" required="true"></td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">联系电话：</label></td>
					<td><input name="stationTele" value="${station.stationTele }" class="x-form-text easyui-validatebox"></td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">所在地区：</label></td>
					<td><input name="stationArea" value="${station.stationArea }" class="x-form-text easyui-validatebox"></td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">备注：</label></td>
					<td>
						<textarea name="remarks" class="x-form-text" style="height: 50px">${station.remarks }</textarea>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>