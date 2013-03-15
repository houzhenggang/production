<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>水体监测数据报表</title>

<script type="text/javascript">
	//新增修改
	function edit(title,id){
		OpenWin(title,500, 320, "${ctx }/security/plant/add.action?id="+id);
	}
	//Excel表格导出
	function exportExcel(title){
		OpenWin(title, 480, 350, "${ctx }/security/plant/reportHeader.action");
	}
	//添加具体的植物物种名称
	function editPlantDetails(title, id, type){
		OpenWin(title, 480, 400, "${ctx }/security/plant/plantDetails.action?plantSpeciesId="+id
				+"&plantMonitorArea="+type);
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
					url : '${ctx }/security/plant/delete.action?plantId='+id,
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
					window.location.href="${ctx }/security/plant/plant.action?pageNo="+pageNumber+"&pageSize="+pageSize;
					//$(this).pagination('loading');
					//$(this).pagination('loaded');
				}
			});
			
			if('${fn:length(plants) }' == '0' && '${user.userRole }' !='super' ){
				edit('添加植物物种监测数据','');
			}
	});
</script>
</head>
<body>
	<div id="toolbar" style="display: none">
	<c:if test="${user.userRole ne 'super' }">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:edit('添加植物物种监测数据','')">添加监测数据</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-xls" plain="true" onclick="javascript:exportExcel('导出excel表格参数')">导出Excel表格</a>
	</c:if>
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载植物物种监测数据...',
	pagination:true, title:'植物物种监测数据列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead>  
	        <tr>
	        	<th data-options="field:'operation', align:'center'">操作</th>
	        	<th data-options="field:'operationTime'">录入时间</th>
	            <th data-options="field:'stationId'">站号</th>
	            <th data-options="field:'stationName'">站名</th>
	            <!-- 
	            <th data-options="field:'plantMonitorDate'">监测月份</th>
	             -->
	            <th data-options="field:'inner1'" >围栏内1</th>
	            <th data-options="field:'inner2'" >围栏内2</th>
	            <th data-options="field:'inner3'" >围栏内3</th>
	            <th data-options="field:'inner4'" >围栏内4</th>
	            <th data-options="field:'out1'" >围栏外1</th>
	            <th data-options="field:'out2'" >围栏外2</th>
	            <th data-options="field:'out3'" >围栏外3</th>
	            <th data-options="field:'out4'" >围栏外4</th>
	        </tr>  
	    </thead>  
	    <tbody>
	    	<c:forEach items="${plants }" var="plant">
		        <tr>
		        	<td>
		        		<c:if test="${user.userRole ne 'super' }">
		            		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit('编辑监测数据','${plant.id }')">编辑</a><a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${plant.id }')">删除</a>
		            	</c:if>
		            </td>
		            <td><fmt:formatDate value='${plant.operationTime }' pattern='yyyy-MM-dd'/></td>
		            <td>${plant.stationId }</td>
		            <td>${plant.stationName }</td>
		            <!-- 
		            <td><fmt:formatDate value='${plant.plantMonitorDate }' pattern='yyyy年MM月01日'/></td>
		             -->
		            <td>
		            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
		            onclick="javascript:editPlantDetails('围栏内1植物种类','${plant.id }','inner1')">围栏内1植物</a>
		            </td>
		            <td>
		            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
		            onclick="javascript:editPlantDetails('围栏内2植物种类','${plant.id }','inner2')">围栏内2植物</a>
		            </td>
		            <td>
		            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
		            onclick="javascript:editPlantDetails('围栏内3植物种类','${plant.id }','inner3')">围栏内3植物</a>
		            </td>
		            <td>
		            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
		            onclick="javascript:editPlantDetails('围栏内4植物种类','${plant.id }','inner4')">围栏内4植物</a>
		            </td>
		            <td>
		            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
		            onclick="javascript:editPlantDetails('围栏外1植物种类','${plant.id }','out1')">围栏外1植物</a>
		            </td>
		            <td>
		            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
		            onclick="javascript:editPlantDetails('围栏外2植物种类','${plant.id }','out2')">围栏外2植物</a>
		            </td>
		            <td>
		            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
		            onclick="javascript:editPlantDetails('围栏外3植物种类','${plant.id }','out3')">围栏外3植物</a>
		            </td>
		            <td>
		            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
		            onclick="javascript:editPlantDetails('围栏外4植物种类','${plant.id }','out4')">围栏外4植物</a>
		            </td>
		        </tr>
	        </c:forEach>
	        <c:if test="${empty plants }">
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