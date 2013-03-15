<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>农作物产量监测数据报表</title>

<script type="text/javascript">
	//新增修改
	function edit(title,id){
		OpenWin(title, 480, 520, "${ctx }/security/cropyield/edit.action?id="+id);
	}
	//Excel表格导出
	function exportExcel(title){
		OpenWin(title, 480, 330, "${ctx }/security/cropyield/reportHeader.action");
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
					url : '${ctx }/security/cropyield/delete.action?cropYieldId='+id,
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
					window.location.href="${ctx }/security/cropyield/yield.action?pageNo="+pageNumber+"&pageSize="+pageSize;
					//$(this).pagination('loading');
					//$(this).pagination('loaded');
				}
			});
			
			if('${fn:length(cropYields) }' == '0' && '${user.userRole }' !='super' ){
				edit('添加农作物产量监测数据','');
			}
	});
</script>
</head>
<body>
	<div id="toolbar" style="display: none">
	<c:if test="${user.userRole ne 'super' }">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:edit('添加农作物产量监测数据','')">添加监测数据</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-xls" plain="true" onclick="javascript:exportExcel('导出excel表格参数')">导出Excel表格</a>
	</c:if>
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载农作物监测数据...',
	pagination:true, title:'农作物产量监测数据列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead>  
	        <tr>
	        	<th data-options="field:'operation', align:'center'">操作</th>
	        	<th data-options="field:'operationTime'">录入时间</th>
	            <th data-options="field:'stationId'">站号</th>
	            <th data-options="field:'stationName'">站名</th>
	            <th data-options="field:'cropName'">农作物名称</th>
	            <th data-options="field:'wheatLength'" >小麦穗长</th>
	            <th data-options="field:'wheatCount'" >小穗数</th>
	            <th data-options="field:'infertilityWheatCount'" >不孕小穗数</th>
	            <th data-options="field:'solidCount'" >结实粒数</th>
	            <th data-options="field:'stemWidth'" >茎粗</th>
	            <th data-options="field:'cropEarLength'" >果穗长</th>
	            <th data-options="field:'cropEarWidth'" >果穗粗</th>
	            <th data-options="field:'doubleEarCount'" >双穗率</th>
	            <th data-options="field:'cropYield'" >农作物产量</th>
	        </tr>  
	    </thead>  
	    <tbody>
	    	<c:forEach items="${cropYields }" var="cropYield">
		        <tr>
		        	<td>
			        	<c:if test="${user.userRole ne 'super' }">
			            	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit('编辑监测数据','${cropYield.id }')">编辑</a><a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${cropYield.id }')">删除</a>
			            </c:if>
		            </td>
		            <td><fmt:formatDate value='${cropYield.operationTime }' pattern='yyyy-MM-dd'/></td>
		            <td>${cropYield.stationId }</td>
		            <td>${cropYield.stationName }</td>
		            <td>${cropYield.cropName }</td>
					<td>${cropYield.wheatLength }</td>
					<td>${cropYield.wheatCount }</td>
					<td>${cropYield.infertilityWheatCount }</td>
					<td>${cropYield.solidCount }</td>
					<td>${cropYield.stemWidth }</td>
					<td>${cropYield.cropEarLength }</td>
					<td>${cropYield.cropEarWidth }</td>
					<td>${cropYield.doubleEarCount }</td>
					<td>${cropYield.cropYield }</td>
		        </tr>
	        </c:forEach>
	        <c:if test="${empty cropYields }">
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