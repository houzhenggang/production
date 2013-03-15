<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>鄂尔多斯市生态观测业务系统</title>
<script type="text/javascript">
function exit(){
	$.messager.confirm('用户确认', '<br>确定退出系统，返回登录页面吗？', function(r){
		if(r){
			parent.window.location.href = "${ctx }/j_spring_security_logout";
		}
	});
}
$(document).ready(function(){
	/*
	if('${menuMap[0] }' != ''){
		OpenTab('1、${menuMap[0].name}','${ctx}${menuMap[0].url }');
	}
	*/
});
</script>
</head>
<body style="overflow: hidden;background-color: #fafafa">
	<div id="cc" class="easyui-layout" data-options="fit:true">
		<!-- 
	    <div data-options="region:'north',split:true" style="height:40px;">鄂尔多斯气象局农业气象数据观测系统</div>
	     --> 
	    <div data-options="region:'west',title:'功能菜单',split:true" 
	    style="width:240px;">
	    		<hr width="99%" style="border: 1px thin ;"/>
	    		<c:forEach items="${menuMap }" var="menu" varStatus="status">
		    		<div>
		    			<a class="easyui-linkbutton" href="javascript:void(0)" data-options="plain:true,iconCls:'icon-ok'"
		    			onclick="OpenTab('${status.index+1 }、${menu.name}','${ctx}${menu.url }')">${status.index+1 }、${menu.name}</a>
		    		</div>
		    		<hr width="99%" style="border: 1px thin ;"/>
				</c:forEach>
				<div>
	    			<a class="easyui-linkbutton" href="javascript:exit()" data-options="plain:true,iconCls:'icon-cancel'">0、退出系统</a>
	    		</div>
	    		<hr width="99%" style="border: 1px thin ;"/>
	    </div>
	    <div data-options="region:'center'">
	    <div id="tabs" class="easyui-tabs" data-options="fit:true">
	    	<div title="系统首页" style="padding:20px; background-color: #fafafa; padding: 0px; text-align: center" data-options="fit:true">  
				<table style="width: 100%; height: 100%" border="0" cellpadding="10" cellspacing="10" >
					<tr><td valign="bottom" align="center">
						<a class="easyui-linkbutton" href="javascript:void(0)" data-options="plain:true,iconCls:'icon-tip'" style="font-size: 20px; font-weight: bold">
							欢迎使用鄂尔多斯市生态生态观测业务系统
						</a>
					</td></tr>
					<tr><td valign="top" align="center">
						<a class="easyui-linkbutton" href="javascript:void(0)" data-options="iconCls:'icon-ok'" style="font-size: 14px; font-weight: bold">
							当前用户[ ${user.nickname } ]
						</a>
					</td></tr>
				</table>
		    </div> 
	    </div>
	    </div>
	</div>
</body>
</html>