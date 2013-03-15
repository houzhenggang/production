<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>气象站点管理</title>

<script type="text/javascript">
	function edit(title,id){
		OpenWin(title, 500, 350, "${ctx }/security/management/station/edit.action?stationId="+id);
	}
	function saveUser(){
		window.frames['myDialog'].submitForm();
	}
	/*
	*删除用户
	*/
	function deleteRecord(id){
		$.messager.confirm('用户确认', '<br>确定删除该站点？', function(r){
			if(r){
				$.ajax({
					type : 'post',
					url : '${ctx }/security/management/station/delete.action?stationId='+id,
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
					window.location.href="${ctx }/security/management/station/station.action?pageNo="+pageNumber+"&pageSize="+pageSize;
					//$(this).pagination('loading');
					//$(this).pagination('loaded');
				}
			});
			if('${fn:length(stationList) }' == '0'){
				edit('添加站点','');
			}
	});
</script>
</head>
<body>
	<div id="toolbar" style="display: none">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:edit('添加站点','')">添加站点</a>
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载用户信息...',
	pagination:true, title:'站点列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead>  
	        <tr>
	        	<th data-options="field:'operation',align:'center'" width="17%">操作</th>
	            <th data-options="field:'stationId'" width="20%">站号</th>  
	            <th data-options="field:'stationName'" width="21%">站名</th>  
	            <th data-options="field:'stationTele'" width="21%">联系电话</th>
	            <th data-options="field:'stationArea'" width="21%">所在地区</th>
	        </tr>  
	    </thead>  
	    <tbody>
	    	<c:forEach items="${stationList }" var="station">
	        <tr>
	        	 <td>
	            	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit('编辑站点','${station.stationId }')">编辑</a><a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${station.stationId }')">删除</a>
	            </td>
	            <td>${station.stationId }</td>
	            <td>${station.stationName }</td>
	            <td>${station.stationTele }</td>
	            <td>${station.stationArea }</td>
	        </tr>
	        </c:forEach>
	    </tbody>  
	</table>
	<div id="myWindows" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px;background-color: #fafafa" closed="true" buttons="#dlg-buttons">
	</div>
	<div id="dlg-buttons" style="display: none">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保  存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#myWindows').dialog('close')">取  消</a>
	</div>
</body>
</html>