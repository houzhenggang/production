<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>农作物生长发育状况监测数据报表</title>

<script type="text/javascript">
	//新增修改
	function edit(title,id){
		OpenWin(title, 480, 600, "${ctx }/security/cropgrowth/edit.action?id="+id);
	}
	//Excel表格导出
	function exportExcel(title){
		OpenWin(title, 480, 370, "${ctx }/security/cropgrowth/reportHeader.action");
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
					url : '${ctx }/security/cropgrowth/delete.action?cropGrowthId='+id,
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
					window.location.href="${ctx }/security/cropgrowth/growth.action?pageNo="+pageNumber+"&pageSize="+pageSize;
					//$(this).pagination('loading');
					//$(this).pagination('loaded');
				}
			});
			
			if('${fn:length(cropGrowths) }' == '0' && '${user.userRole }' !='super' ){
				edit('添加农作物监测数据','');
			}
	});
</script>
</head>
<body>
	<div id="toolbar" style="display: none">
	<c:if test="${user.userRole ne 'super' }">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:edit('添加农作物监测数据','')">添加监测数据</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-xls" plain="true" onclick="javascript:exportExcel('导出excel表格参数')">导出Excel表格</a>
	</c:if>
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载农作物监测数据...',
	pagination:true, title:'农作物生长监测数据列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead>  
	        <tr>
	        	<th data-options="field:'operation', align:'center'">操作</th>
	        	<th data-options="field:'operationTime'">录入时间</th>
	            <th data-options="field:'stationId'">站号</th>
	            <th data-options="field:'stationName'">站名</th>
	            <th data-options="field:'cropName'">农作物名称</th>
	            <th data-options="field:'sowingDate'" >播种期</th>
	            <th data-options="field:'seedlingDate'" >出苗期</th>
	            <th data-options="field:'trefoilDate'" >三叶期</th>
	            <th data-options="field:'jointingDate'" >拔节期</th>
	            <th data-options="field:'headingDate'" >抽穗期</th>
	            <th data-options="field:'tasselingDate'" >抽雄期</th>
	            <th data-options="field:'floweringDate'" >开花期</th>
	            <th data-options="field:'silkingDate'" >吐丝期</th>
	            <th data-options="field:'milkyDate'" >乳熟期</th>
	            <th data-options="field:'maturityDate'" >成熟期</th>
	            <th data-options="field:'growthHeight'" >生长高度</th>
	            <th data-options="field:'growthCondition'" >生长状况</th>
	        </tr>  
	    </thead>  
	    <tbody>
	    	<c:forEach items="${cropGrowths }" var="cropGrowth">
		        <tr>
		        	<td>
			        	<c:if test="${user.userRole ne 'super' }">
			            	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit('编辑监测数据','${cropGrowth.id }')">编辑</a><a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${cropGrowth.id }')">删除</a>
			            </c:if>
		            </td>
		            <td><fmt:formatDate value='${cropGrowth.operationTime}' pattern='yyyy-MM-dd'/></td>
		            <td>${cropGrowth.stationId }</td>
		            <td>${cropGrowth.stationName }</td>
		             <td>${cropGrowth.cropName }</td>
					<td><fmt:formatDate value='${cropGrowth.sowingDate }' pattern='yyyy-M-d'/></td>
					<td><fmt:formatDate value='${cropGrowth.seedlingDate }' pattern='yyyy-M-d'/></td>
					<td><fmt:formatDate value='${cropGrowth.trefoilDate }' pattern='yyyy-M-d'/></td>
					<td><fmt:formatDate value='${cropGrowth.jointingDate }' pattern='yyyy-M-d'/></td>
					<td><fmt:formatDate value='${cropGrowth.headingDate }' pattern='yyyy-M-d'/></td>
					<td><fmt:formatDate value='${cropGrowth.tasselingDate }' pattern='yyyy-M-d'/></td>
					<td><fmt:formatDate value='${cropGrowth.floweringDate }' pattern='yyyy-M-d'/></td>
					<td><fmt:formatDate value='${cropGrowth.silkingDate }' pattern='yyyy-M-d'/></td>
					<td><fmt:formatDate value='${cropGrowth.milkyDate }' pattern='yyyy-M-d'/></td>
					<td><fmt:formatDate value='${cropGrowth.maturityDate }' pattern='yyyy-M-d'/></td>
					<td>${cropGrowth.growthHeight }</td>
					<td>${cropGrowth.growthCondition }</td>
		        </tr>
	        </c:forEach>
	        <c:if test="${empty cropGrowths }">
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