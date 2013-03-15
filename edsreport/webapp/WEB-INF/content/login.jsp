<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>鄂尔多斯市生态观测业务系统</title>
<script type="text/javascript">
/**
 * 键盘监听事件
 */
 document.onkeydown=function(){
	if(window.event.keyCode == 13){
		submitForm();
	}
};

$(document).ready(function(){
	if("<%=request.getParameter("error")%>" == "true"){
		$.messager.alert("系统提示","<br>用户名密码错误，请确认后重试！","error",function(){
		});
	}
	document.getElementById("j_username").focus();	
});
function submitForm(){
	if($("#loginForm").form('validate')){
		loginForm.submit();
	}
}
</script>
</head>
<body style="overflow: hidden;padding: 0px;background-color: #fafafa">
	<div class="easyui-window" data-options="title:'用户登录',
	draggable:false,resizable:false,modal:false,collapsible:true,
	minimizable:false,maximizable:false,closable:false"
	style="width:550px;height:180px;padding:0px; overflow: hidden">
	<form action="${ctx }/j_spring_security_check" method="post" name="loginForm" id="loginForm" novalidate>
	<table border="0" style="width: 100%; height: 92%; background-color: #fafafa"><tr height="90%"><td valign="middle" align="center">
			<table border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td width="418px" align="center">
					<a class="easyui-linkbutton" data-options="plain:true" style="font-weight: bold; font-size: 12px">用户名:</a><input type="text" name="j_username" id="j_username" class="easyui-validatebox" style="width: 140px" 
					required="true">
					<a class="easyui-linkbutton" data-options="plain:true" style="font-weight: bold; font-size: 12px">密   码:</a><input type="password" name="j_password" id="j_password" class="easyui-validatebox" style="width: 140px" 
					required="true">
				</td>
				<td align="center" width="100px">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="font-weight: bold" 
					href="javascript:submitForm()">登 录</a>
				</td>
			</tr>
			</table>
	</td></tr>
	<tr height="10%">
		<td align="right" >
			<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" style="font-weight: bold; font-size: 12px"
			href="${ctx  }/download/chrome_installer.exe">高速浏览器下载</a>
		</td>
	</tr>
	</table>
	</form>
	</div>
</body>
</html>