<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>土壤风蚀监测数据报表</title>

<script type="text/javascript">
	//新增修改
	function edit(title,id){
		OpenWin(title, 820, 560, "${ctx }/security/soil/edit.action?id="+id);
	}
	//Excel表格导出
	function exportExcel(title){
		OpenWin(title, 480, 310, "${ctx }/security/soil/reportHeader.action");
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
					url : '${ctx }/security/soil/delete.action?soilId='+id,
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
					window.location.href="${ctx }/security/soil/soil.action?pageNo="+pageNumber+"&pageSize="+pageSize;
					//$(this).pagination('loading');
					//$(this).pagination('loaded');
				}
			});
			
			if('${fn:length(soils) }' == '0' && '${user.userRole }' !='super' ){
				edit('添加土壤风蚀监测数据','');
			}
	});
</script>
</head>
<body>
	<div id="toolbar" style="display: none">
	<c:if test="${user.userRole ne 'super' }">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:edit('添加土壤风蚀监测数据','')">添加监测数据</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-xls" plain="true" onclick="javascript:exportExcel('导出excel表格参数')">导出Excel表格</a>
	</c:if>
		<a href="${ctx }/security/soil/soilReport.action" class="easyui-linkbutton" iconCls="icon-save" plain="true">查看历史土壤风蚀报表数据</a>
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载土壤风蚀监测数据...',
	pagination:true, title:'土壤风蚀监测数据列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead>  
	        <tr>
	        	<th data-options="field:'operation', align:'center'">操作</th>
	        	<th data-options="field:'operationTime'">录入时间</th>
	            <th data-options="field:'stationId'">站号</th>
	            <th data-options="field:'stationName'">站名</th>
	            <!-- 
	            <th data-options="field:'soilMonitorDate'" >监测年月</th>
	             -->
	        	<th data-options="field:'innerSoilErosion1'" >东西区域内1</th>
				<th data-options="field:'innerSoilErosion2'" >东西区域内2</th>
				<th data-options="field:'innerSoilErosion3'" >东西区域内3</th>
				<th data-options="field:'innerSoilErosion4'" >东西区域内4</th>
				<th data-options="field:'innerSoilErosion5'" >东西区域内5</th>
				<th data-options="field:'innerSoilErosion6'" >东西区域内6</th>
				<th data-options="field:'innerSoilErosion7'" >东西区域内7</th>
				<th data-options="field:'innerSoilErosion8'" >东西区域内8</th>
				<th data-options="field:'innerSoilErosion9'" >东西区域内9</th>
				<th data-options="field:'innerSoilErosion10'" >东西区域内10</th>
				<th data-options="field:'innerSoilErosion11'" >南北区域内1</th>
				<th data-options="field:'innerSoilErosion12'" >南北区域内2</th>
				<th data-options="field:'innerSoilErosion13'" >南北区域内3</th>
				<th data-options="field:'innerSoilErosion14'" >南北区域内4</th>
				<th data-options="field:'innerSoilErosion15'" >南北区域内5</th>
				<th data-options="field:'innerSoilErosion16'" >南北区域内6</th>
				<th data-options="field:'innerSoilErosion17'" >南北区域内7</th>
				<th data-options="field:'innerSoilErosion18'" >南北区域内8</th>
				<th data-options="field:'innerSoilErosion19'" >南北区域内9</th>
				<th data-options="field:'innerSoilErosion20'" >南北区域内10</th>
				<th data-options="field:'outSoilErosion1'" >东西区域外1</th>
				<th data-options="field:'outSoilErosion2'" >东西区域外2</th>
				<th data-options="field:'outSoilErosion3'" >东西区域外3</th>
				<th data-options="field:'outSoilErosion4'" >东西区域外4</th>
				<th data-options="field:'outSoilErosion5'" >东西区域外5</th>
				<th data-options="field:'outSoilErosion6'" >东西区域外6</th>
				<th data-options="field:'outSoilErosion7'" >东西区域外7</th>
				<th data-options="field:'outSoilErosion8'" >东西区域外8</th>
				<th data-options="field:'outSoilErosion9'" >东西区域外9</th>
				<th data-options="field:'outSoilErosion10'" >东西区域外10</th>
				<th data-options="field:'outSoilErosion11'" >南北区域外11</th>
				<th data-options="field:'outSoilErosion12'" >南北区域外12</th>
				<th data-options="field:'outSoilErosion13'" >南北区域外13</th>
				<th data-options="field:'outSoilErosion14'" >南北区域外14</th>
				<th data-options="field:'outSoilErosion15'" >南北区域外15</th>
				<th data-options="field:'outSoilErosion16'" >南北区域外16</th>
				<th data-options="field:'outSoilErosion17'" >南北区域外17</th>
				<th data-options="field:'outSoilErosion18'" >南北区域外18</th>
				<th data-options="field:'outSoilErosion19'" >南北区域外19</th>
				<th data-options="field:'outSoilErosion20'" >南北区域外20</th>
	        </tr>  
	    </thead>  
	    <tbody>
	    	<c:forEach items="${soils }" var="soil">
		        <tr>
		        	<td>
		        		<c:if test="${user.userRole ne 'super' }">
		            		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit('编辑监测数据','${soil.id }')">编辑</a><a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${soil.id }')">删除</a>
		            	</c:if>
		            </td>
		            <td><fmt:formatDate value='${soil.operationTime}' pattern='yyyy-MM-dd'/></td>
					<td>${soil.stationId }</td>
					<td>${soil.stationName }</td>
					<!-- 
					<td><fmt:formatDate value='${soil.soilMonitorDate }' pattern='yyyy年MM月dd日'/></td>
					 -->
					<td>${soil.innerSoilErosion1 }</td>
					<td>${soil.innerSoilErosion2 }</td>
					<td>${soil.innerSoilErosion3 }</td>
					<td>${soil.innerSoilErosion4 }</td>
					<td>${soil.innerSoilErosion5 }</td>
					<td>${soil.innerSoilErosion6 }</td>
					<td>${soil.innerSoilErosion7 }</td>
					<td>${soil.innerSoilErosion8 }</td>
					<td>${soil.innerSoilErosion9 }</td>
					<td>${soil.innerSoilErosion10 }</td>
					<td>${soil.innerSoilErosion11 }</td>
					<td>${soil.innerSoilErosion12 }</td>
					<td>${soil.innerSoilErosion13 }</td>
					<td>${soil.innerSoilErosion14 }</td>
					<td>${soil.innerSoilErosion15 }</td>
					<td>${soil.innerSoilErosion16 }</td>
					<td>${soil.innerSoilErosion17 }</td>
					<td>${soil.innerSoilErosion18 }</td>
					<td>${soil.innerSoilErosion19 }</td>
					<td>${soil.innerSoilErosion20 }</td>
					<td>${soil.outSoilErosion1 }</td>
					<td>${soil.outSoilErosion2 }</td>
					<td>${soil.outSoilErosion3 }</td>
					<td>${soil.outSoilErosion4 }</td>
					<td>${soil.outSoilErosion5 }</td>
					<td>${soil.outSoilErosion6 }</td>
					<td>${soil.outSoilErosion7 }</td>
					<td>${soil.outSoilErosion8 }</td>
					<td>${soil.outSoilErosion9 }</td>
					<td>${soil.outSoilErosion10 }</td>
					<td>${soil.outSoilErosion11 }</td>
					<td>${soil.outSoilErosion12 }</td>
					<td>${soil.outSoilErosion13 }</td>
					<td>${soil.outSoilErosion14 }</td>
					<td>${soil.outSoilErosion15 }</td>
					<td>${soil.outSoilErosion16 }</td>
					<td>${soil.outSoilErosion17 }</td>
					<td>${soil.outSoilErosion18 }</td>
					<td>${soil.outSoilErosion19 }</td>
					<td>${soil.outSoilErosion20 }</td>
		        </tr>
	        </c:forEach>
	        <c:if test="${empty soils }">
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