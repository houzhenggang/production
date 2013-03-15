<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>

<script type="text/javascript">
	function edit(id){
		OpenWin("编辑用户", 600, 550, "${ctx }/security/management/user/edit.action?id="+id);
	}
	function saveUser(){
		window.frames['myDialog'].submitForm();
	}
	/*
	*删除用户
	*/
	function deleteRecord(id){
		$.messager.confirm('用户确认', '<br>确定删除该用户？', function(r){
			if(r){
				$.ajax({
					type : 'post',
					url : '${ctx }/security/management/user/delete.action?id='+id,
					dataType : 'json',
					async : false,
					success : function(data){
						if(data){
							$.messager.alert("用户提示", "<br>删除成功", "info", function(){
								window.location.reload();
							});
						}else{
							$.messager.alert("用户提示", "<br>删除失败", "info");
						}
					}
				});
			}else{
				return;
			}
		});
	}
	
////////////////////////////////////////////////////页面分页控件////////////////////////////////////////////////////////////////
$(function(){
		var pager = $('#userInfo').datagrid('getPager');	// get the pager of datagrid
		pager.pagination({
			total:parseInt('${page.totalCount }'),
			pageList: [10,15,20],
			pageNumber:parseInt('${page.pageNo }'),
			pageSize:parseInt('${page.pageSize }'),
			onSelectPage : function(pageNumber, pageSize){
				window.location.href="${ctx }/security/management/user/user.action?pageNo="+pageNumber+"&pageSize="+pageSize;
			}
		});
});

</script>
</head>
<body>
	<div id="toolbar" style="display: none">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:edit('')">添加用户</a>
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载用户信息...',
	pagination:true, title:'用户列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead>  
	        <tr>
	        	<th data-options="field:'operation',align:'center'" width="15%">操作</th>
	            <th data-options="field:'username'" width="15%">用户名</th>  
	            <th data-options="field:'nickname'" width="18%">姓名</th>
	             <th data-options="field:'userRole'" width="10%">用户类型</th>
	            <th data-options="field:'telephone'" width="21%">联系电话</th>
	            <th data-options="field:'email'" width="21%">邮箱</th>
	        </tr>  
	    </thead>  
	    <tbody>
	    	<c:forEach items="${userList }" var="user">
	        <tr>
	        	 <td>
	            	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit('${user.id }')">编辑</a><a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${user.id }')">删除</a>
	            </td>
	            <td>${user.username }</td>
	            <td>${user.nickname }</td>
	            <td>${userRoles[user.userRole] }</td>
	            <td>${user.telephone }</td>
	            <td>${user.email }</td>
	        </tr>
	        </c:forEach>
	    </tbody>  
	</table>
	<div id="myWindows" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px; background-color: #fafafa" closed="true" buttons="#dlg-buttons">
	</div>
	<div id="dlg-buttons" style="display: none">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保  存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#myWindows').dialog('close')">取  消</a>
	</div>
</body>
</html>