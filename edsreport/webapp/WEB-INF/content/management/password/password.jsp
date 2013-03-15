<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码管理</title>
<script type="text/javascript">
function submitForm(){
	if($("#loginForm").form('validate')){
		$.ajax({
			type : 'post',
			url : '${ctx }/security/management/password/change.action?newPassword='+$("#newPassword").val()
					+"&oldPassword="+$("#oldPassword").val(),
			dataType : 'json',
			async : false,
			success : function(data){
				if(data == "1"){
					$.messager.alert("用户提示", "<br>修改成功", "info",function(){
						window.location.reload();
					});
				}else{
					$.messager.alert("用户提示", "<br>修改失败", "info");
				}
			}
		});
	}
}
</script>
</head>
<body style="overflow: hidden;padding: 0px;background-color: #fafafa">
	<div class="easyui-window" data-options="title:'密码修改',
	draggable:false,resizable:false,modal:false,collapsible:false,
	minimizable:false,maximizable:false,closable:false,iconCls:'icon-edit'"
	style="width:400px;height:250px;padding:0px">
	<table border="0" style="width: 100%; height: 100%; background-color: #fafafa"><tr>
	<td valign="middle" width="100%">
		<form action="" method="post" name="loginForm" id="loginForm" novalidate>
			<table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
				<tr height="40px">
					<td width="110px" align="right">
						<a class="easyui-linkbutton" data-options="plain:true" style="font-size: 12px">原密码：</a></td>
					<td>
						<input type="password" name="oldPassword" id="oldPassword" class="easyui-validatebox" style="width: 180px" 
						validType="safepass,eaqualToOld('${ctx }')" required="true">
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						<a class="easyui-linkbutton" data-options="plain:true" style="font-size: 12px">新密码：</a>
					</td>
					<td><input type="password" name="newPassword" id="newPassword" class="easyui-validatebox" style="width: 180px" 
					validType="safepass,equalTo('newPassword')" required="true">
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						<a class="easyui-linkbutton" data-options="plain:true" style="font-size: 12px">确认密码：</a>
					</td>
					<td>
					<input type="password" name="confirmPassword" id="confirmPassword" class="easyui-validatebox" style="width: 180px" 
					validType="safepass,equalTo('newPassword')" required="true">
					</td>
				</tr>
				<tr height="40px">
					<td align="center" colspan="2">
						<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="font-weight: bold" 
						href="javascript:submitForm()">确 定</a>
					</td>
				</tr>
			</table>
		</form>
	</td></tr></table>
	</div>
</body>
</html>