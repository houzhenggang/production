<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FTP、共享配置参数管理</title>
<script type="text/javascript">
function submitForm(){
	$("#loginForm").form('submit',{
		url : '${ctx }/security/management/ftpshare/save.action',
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(map){
			map = eval("("+ map +")");
			if(map.result){
				$.messager.alert('系统提示', '<br>操作成功', "info", function(){
					window.location.reload();
				});
			}else{
				$.messager.alert("错误提示", "<br>操作失败，错误："+map.msg, "error");
			}
		}
	});
}
</script>
</head>
<body style="overflow: hidden;padding: 0px;background-color: #fafafa">
	<div class="easyui-window" data-options="title:'FTP共享配置参数管理',
	draggable:false,resizable:false,modal:false,collapsible:false,
	minimizable:false,maximizable:false,closable:false,iconCls:'icon-edit'"
	style="width:500px;height:380px;padding:0px">
	<table border="0" style="width: 100%; height: 100%; background-color: #fafafa"><tr>
	<td valign="middle" width="100%">
		<form action="" method="post" name="loginForm" id="loginForm" novalidate>
			<input type=hidden name="id" value="${shareLocal.id }">
			<table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
				<tr height="5px"><td colspan="3"></td></tr>
				<tr height="35px">
					<td width="130px" align="right">
						<label class="x-form-field">FTP服务器地址：</label></td>
					<td width="180px">
						<input type="text" name="ftpAddress" id="ftpAddress" class="easyui-validatebox" 
						style="width: 180px" required="true" value="${shareLocal.ftpAddress }">
					</td>
					<td>
					<font class="tip-red">如：127.0.0.1</font></td>
				</tr>
				<tr height="35px">
					<td align="right">
						<label class="x-form-field">FTP端口：</label></td>
					<td>
						<input type="text" name="ftpPort" id="ftpPort" class="easyui-validatebox easyui-numberbox" 
						style="width: 180px" required="true" value="${shareLocal.ftpPort }" 
						data-options="min:0,precision:0">
					</td>
					<td>
					<font class="tip-red">如：21</font></td>
				</tr>
				<tr height="35px">
					<td align="right">
						<label class="x-form-field">FTP用户名：</label></td>
					<td>
						<input type="text" name="ftpUsername" id="ftpUsername" class="easyui-validatebox" style="width: 180px" 
						required="true" value="${shareLocal.ftpUsername }">
					</td>
					<td>
					<font class="tip-red">如：administrator</font></td>
				</tr>
				<tr height="35px">
					<td align="right">
						<label class="x-form-field">FTP用户密码：</label></td>
					<td>
						<input type="text" name="ftpPassword" id="ftpPassword" class="easyui-validatebox" style="width: 180px" 
						required="true" value="${shareLocal.ftpPassword }">
					</td>
					<td>
					<font class="tip-red">如：admin</font></td>
				</tr>
				<tr height="35px">
					<td align="right">
						<label class="x-form-field">FTP上传目录：</label></td>
					<td>
						<input type="text" name="ftpBasePath" id="ftpBasePath" class="easyui-validatebox" style="width: 180px" 
						required="true" value="${shareLocal.ftpBasePath }">
					</td>
					<td>
					<font class="tip-red">如（main/eds）</font></td>
				</tr>
				<tr height="35px">
					<td align="right">
						<label class="x-form-field">本地保存目录：</label></td>
					<td>
						<input type="text" name="shareBasePath" id="shareBasePath" class="easyui-validatebox" style="width: 180px" 
						required="true" value="${shareLocal.shareBasePath }">
					</td>
					<td>
					<font class="tip-red">如（main/eds）</font></td>
				</tr>
				<tr height="35px">
					<td align="right"><input type="checkbox" name="checkFtp" value="1" checked="checked"></td>
					<td colspan="2">
						<label class="x-form-field">校验FTP的有效性</label>
					</td>
				</tr>
				<tr height="20px">
					<td align="center" colspan="3">
						<font class="tip-red">*注意：目录分隔符为'/'或'\\'，只输入'/'或'\\'表示空</font>
					</td>
				</tr>
				<tr height="50px">
					<td align="center" colspan="3">
						<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="font-weight: bold" href="javascript:submitForm()">确 定</a>
					</td>
				</tr>
			</table>
		</form>
	</td></tr></table>
	</div>
</body>
</html>