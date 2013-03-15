<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>土壤风蚀监测数据报表</title>

<script type="text/javascript">
	/*
	*删除用户
	*/
	function deleteRecord(id){
		$.messager.confirm('用户确认', '<br>确定删除该数据？', function(r){
			if(r){
				$.ajax({
					type : 'post',
					url : '${ctx }/security/soil/deleteReport.action?soilReportId='+id,
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
					window.location.href="${ctx }/security/soil/soilReport.action?pageNo="+pageNumber+"&pageSize="+pageSize;
					//$(this).pagination('loading');
					//$(this).pagination('loaded');
				}
			});
	});
</script>
</head>
<body>
	<div id="toolbar" style="display: none">
		<a href="${ctx }/security/soil/soil.action" class="easyui-linkbutton" iconCls="icon-back" plain="true">返回土壤风蚀监测数据列表</a>
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载土壤风蚀历史报表数据列表...',
	pagination:true, title:'土壤风蚀历史报表数据列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead>  
	        <tr>
	        	<th data-options="field:'operation', align:'center'">操作</th>
	        	<th data-options="field:'operationTime'">报表生成时间</th>
				<th data-options="field:'stationId'" >站号</th>
				<th data-options="field:'stationName'" >站台名称</th>
				<!-- 
				<th data-options="field:'operationTime'" >监测时间</th>
				 -->
				<th data-options="field:'soilMonitorDate'" >监测时间</th>
				<th data-options="field:'avgInnerSoilProduct'" >区域内平均风积厚度</th>
				<th data-options="field:'avgInnerSoilLose'" >区域内平均风蚀厚度</th>
				<th data-options="field:'avgOutSoilProduct'" >区域外平均风积厚度</th>
				<th data-options="field:'avgOutSoilLose'" >区域外平均风蚀厚度</th>
	        </tr>  
	    </thead>  
	    <tbody>
	    	<c:forEach items="${soilReports }" var="soilReport">
		        <tr>
		        	<td>
		        		<c:if test="${user.userRole ne 'super' }">
		            		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${soilReport.id }')">删除</a>
		           		</c:if>
		            </td>
		            <td><fmt:formatDate value='${soilReport.operationTime}' pattern='yyyy-MM-dd'/></td>
		            <td>${soilReport.stationId }</td>
					<td>${soilReport.stationName }</td>
					<td><fmt:formatDate value='${soilReport.soilMonitorDate }' pattern='yyyy-MM-dd'/></td>
					<td>${soilReport.avgInnerSoilProduct }</td>
					<td>${soilReport.avgInnerSoilLose }</td>
					<td>${soilReport.avgOutSoilProduct }</td>
					<td>${soilReport.avgOutSoilLose }</td>
		        </tr>
	        </c:forEach>
	        <c:if test="${empty soilReports }">
	        	<tr>
		            <td>暂无记录</td>
		        </tr>
	        </c:if>
	    </tbody>  
	</table>
</body>
</html>