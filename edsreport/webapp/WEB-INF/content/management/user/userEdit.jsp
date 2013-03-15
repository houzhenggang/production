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
			url : '${ctx }/security/management/user/save.action',
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
	
	/*
	* 超级管理无需授予站点权限，默认对所有站点具有权限
	*/
	function disabledStationAuth(value){
		if(value == "super"){
			$("#station_auth").css("display", "none");
			$("[name=stations]").attr("disabled", true);
			$("#super_station").css("display", "");
		}else{
			$("#station_auth").css("display", "");
			$("[name=stations]").attr("disabled", false);
			$("#super_station").css("display", "none");
		}
	}
	
	$(document).ready(function(){
		$("[id^='role_']").click(function(){
			if(this.checked){
				$("[id^='child_"+ this.id +"_']").attr("checked", "checked");
			}else{
				$("[id^='child_"+ this.id +"_']").attr("checked", false);
			}
		});
		$("[id^='child_']").click(function(){
			var checked = false;
			$("[id^='child_']").each(function(){
				if(this.checked){
					checked = true;
				}
			});
			$("#"+this.lang).attr("checked", checked);
		});
	});
</script>
</head>
<body style="overflow-y: auto; overflow-x: hidden;padding: 0px">
	<form id="userForm" name="userForm" class="form" method="post" novalidate>
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">基本信息——<font class="tip-red">请仔细填写用户基本信息</font></legend>
			<input type="hidden" name="id" value="${user.id }" id="userId">
			<table style="width: 430px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="cn_fontright" width="120px"><label class="x-form-field">用户名：</label></td>
					<td>
					<input name="username" value="${user.username }" class="x-form-text easyui-validatebox" 
					validType="checkUsername('${ctx }')" required="true" 
					<c:if test="${not empty user.id }">readonly="readonly"</c:if>><c:if test="${not empty user.id }"><font class="tip-red">*不能修改用户名</font></c:if></td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">用户类型：</label></td>
					<td>
						<select name="userRole" class="easyui-combobox" style="width:200px;"
						data-options="editable:false,required:true, 
												onSelect: function(rec){
													disabledStationAuth(rec.value);
												}">
							<c:forEach items="${roleSelects }" var="item">
							<c:choose>
								<c:when test="${item.key eq user.userRole }">
									<option value="${item.key }" selected="selected">${item.value}</option>
								</c:when>
								<c:otherwise>
									<c:if test="${userRole eq 'super' }">
										<option value="${item.key }">${item.value}</option>
									</c:if>
								</c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">姓名：</label></td>
					<td><input name="nickname" value="${user.nickname }" class="x-form-text easyui-validatebox" required="true"></td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">联系电话：</label></td>
					<td><input name="telephone" value="${user.telephone }" class="x-form-text easyui-validatebox" required="true"></td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">邮箱：</label></td>
					<td><input name="email" value="${user.email }" class="x-form-text easyui-validatebox" validType="email"></td>
				</tr>
				<tr>
					<td class="cn_fontright"><label class="x-form-field">备注：</label></td>
					<td>
						<textarea name="remarks" class="x-form-text" style="height: 50px">${user.remarks }</textarea>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">菜单权限</legend>
			<input type="hidden" name="roles" checked="checked" value="110" >
			<table style="width: 430px" border="0" cellpadding="0" cellspacing="0">
				<c:forEach items="${roles }" var="role">
					<tr>
						<c:if test="${empty role.parentId }">
							<td class="cn_fontright" width="120px">
								<input type="checkbox" name="roles" value="${role.id }" id="role_${role.id }"
								<c:if test="${fn:contains(userRoles, role.id)}" >checked='checked'</c:if> >
							</td>
						</c:if>
						<c:if test="${!empty role.parentId }">
							<td class="cn_fontright" width="120px"></td>
							<td class="cn_fontright" width="40px">
								<input type="checkbox" name="roles" value="${role.id }" lang="role_${role.parentId }" id="child_role_${role.parentId}_${role.id }"
								<c:if test="${fn:contains(userRoles, role.id)}" >checked='checked'</c:if> >
							</td>
						</c:if>
						<td <c:if test="${empty role.parentId }">colspan='2'</c:if> style="border-bottom: 1px solid #B5B8C8">
						<a class="easyui-linkbutton" href="javascript:void(0)" data-options="plain:true,iconCls:'icon-ok'">
						${role.name }</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
		
		<fieldset class="x-fieldset" id="super_station" <c:if test="${user.userRole ne 'super' }">style="display:none"</c:if>>
			<label class="x-form-field"><font class="tip-red">*超级管理员默认对所有站点具有操作权限！</font></label>
		</fieldset>
		
		<fieldset class="x-fieldset" id="station_auth" <c:if test="${user.userRole eq 'super' }">style="display:none"</c:if>>
			<legend class="x-fieldset-header x-unselectable">站点权限</legend>
			<table style="width: 480px" border="0" cellpadding="0" cellspacing="0">
				<c:forEach items="${stations }" var="station" varStatus="status">
					<c:if test="${(status.index % 2) eq 0 }">
						<tr>
					</c:if>
						<td class="cn_fontright" width="25px">
							<input type="checkbox" name="stations" value="${station.stationId }"
							<c:if test="${fn:contains(userStations, station.stationId)}" >checked='checked'</c:if> >
						</td>
						<td width="180" style="border-bottom: 1px solid #B5B8C8">
						<a class="easyui-linkbutton" href="javascript:void(0)" data-options="plain:true,iconCls:'icon-ok'">
						${station.stationName }</a>
						</td>
					<c:if test="${(status.index % 2) eq 1 }">
						<tr>
					</c:if>
				</c:forEach>
			</table>
		</fieldset>
	</form>
</body>
</html>