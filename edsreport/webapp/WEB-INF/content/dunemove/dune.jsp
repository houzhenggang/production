<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>沙丘移动监测数据报表</title>

<script type="text/javascript">
	//新增修改
	function edit(title,id){
		OpenWin(title, 520, 580, "${ctx }/security/dunemove/edit.action?id="+id);
	}
	//Excel表格导出
	function exportExcel(title){
		OpenWin(title, 480, 310, "${ctx }/security/dunemove/reportHeader.action");
	}
	//保存
	function saveUser(){
		window.frames['myDialog'].submitForm();
	}
	/*
	*删除用户
	*/
	function deleteRecord(id){
		$.messager.confirm('用户确认', '<br>确定删除该数据？', function(r){
			if(r){
				$.ajax({
					type : 'post',
					url : '${ctx }/security/dunemove/delete.action?duneMoveId='+id,
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
					window.location.href="${ctx }/security/dunemove/dune.action?pageNo="+pageNumber+"&pageSize="+pageSize;
					//$(this).pagination('loading');
					//$(this).pagination('loaded');
				}
			});
			
			if('${fn:length(duneMoves) }' == '0' && '${user.userRole }' !='super' ){
				edit('添加沙丘移动监测数据','');
			}
	});
</script>
</head>
<body>
	<div id="toolbar" style="display: none">
	<c:if test="${user.userRole ne 'super' }">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:edit('添加沙丘移动监测数据','')">添加监测数据</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-xls" plain="true" onclick="javascript:exportExcel('导出excel表格参数')">导出Excel表格</a>
		</c:if>
		<!-- 
		<a href="${ctx }/security/dunemove/duneMoveReport.action" class="easyui-linkbutton" iconCls="icon-save" plain="true">查看历史沙丘移动报表数据</a>
		 -->
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载沙丘移动监测数据...',
	pagination:true, title:'沙丘移动监测数据列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead>  
	        <tr>
	        	<th data-options="field:'operation', align:'center'">操作</th>
	        	<th data-options="field:'operationTime'">录入时间</th>
	            <th data-options="field:'stationId'" >站号</th>
				<th data-options="field:'stationName'" >站名</th>
				<!-- 
				<th data-options="field:'duneMoveMonitorDate'" >测定日期</th>
				 -->
				<th data-options="field:'duneMoveLongtitude'" >被监测沙丘经度</th>
				<th data-options="field:'duneMoveLatitude'" >被监测沙丘纬度</th>
				<th data-options="field:'duneAltitude'" >海拔高度</th>
				<th data-options="field:'duneHeight'" >沙丘高度变化</th>
				<th data-options="field:'duneWindwardSlope'" >迎风坡脚移动距离</th>
				<th data-options="field:'duneHilltop'" >丘顶移动距离</th>
				<th data-options="field:'duneLeewardSlope'" >背风坡脚移动距离</th>
				<th data-options="field:'windDirection'" >主风向</th>
				<th data-options="field:'windSpeed'" >风速</th>
				<th data-options="field:'duneWindPosition'" >迎风坡脚方位角</th>
				<th data-options="field:'hilltopPosition'" >丘顶方位角</th>
				<th data-options="field:'leewardPosition'" >背风坡脚方位角</th>
	        </tr>  
	    </thead>  
	    <tbody>
	    	<c:forEach items="${duneMoves }" var="duneMove">
		        <tr>
		        	<td>
		        		<c:if test="${user.userRole ne 'super' }">
		            		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit('编辑监测数据','${duneMove.id }')">编辑</a><a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${duneMove.id }')">删除</a>
		            	</c:if>
		            </td>
		            <td><fmt:formatDate value='${duneMove.operationTime}' pattern='yyyy-MM-dd'/></td>
		            <td>${duneMove.stationId }</td>
					<td>${duneMove.stationName }</td>
					<!-- 
					<td><fmt:formatDate value='${duneMove.duneMoveMonitorDate }' pattern='yyyy-MM-dd'/></td>
					 -->
					<td>${duneMove.duneMoveLongtitude }</td>
					<td>${duneMove.duneMoveLatitude }</td>
					<td>${duneMove.duneAltitude }</td>
					<td>${duneMove.duneHeight }</td>
					<td>${duneMove.duneWindwardSlope }</td>
					<td>${duneMove.duneHilltop }</td>
					<td>${duneMove.duneLeewardSlope }</td>
					<td>${duneMove.windDirection }</td>
					<td>${duneMove.windSpeed }</td>
					<td>${duneMove.duneWindPosition }</td>
					<td>${duneMove.hilltopPosition }</td>
					<td>${duneMove.leewardPosition }</td>
		        </tr>
	        </c:forEach>
	        <c:if test="${empty duneMoves }">
	        	<tr>
		            <td>暂无记录</td>
		        </tr>
	        </c:if>
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