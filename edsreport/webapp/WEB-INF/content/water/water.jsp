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
		OpenWin(title, 500, 600, "${ctx }/security/water/edit.action?id="+id);
	}
	//Excel表格导出
	function exportExcel(title){
		OpenWin(title, 500, 350, "${ctx }/security/water/reportHeader.action");
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
					url : '${ctx }/security/water/delete.action?waterId='+id,
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
					window.location.href="${ctx }/security/water/water.action?pageNo="+pageNumber+"&pageSize="+pageSize;
					//$(this).pagination('loading');
					//$(this).pagination('loaded');
				}
			});
			
			if('${fn:length(waters) }' == '0' && '${user.userRole }' !='super' ){
				edit('添加水体监测数据','');
			}
	});
</script>
</head>
<body>
	<div id="toolbar" style="display: none">
	<c:if test="${user.userRole ne 'super' }">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:edit('添加水体监测数据','')">添加监测数据</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-xls" plain="true" onclick="javascript:exportExcel('导出excel表格参数')">导出Excel表格</a>
	</c:if>
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载水体监测数据...',
	pagination:true, title:'水体监测数据列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead>  
	        <tr>
	        	<th data-options="field:'operation', align:'center'">操作</th>
	        	<th data-options="field:'operationTime'">录入时间</th>
	            <th data-options="field:'stationId'">站号</th>
	            <th data-options="field:'stationName'">站名</th>
	            <th data-options="field:'waterName'">水体名称</th>
	            <th data-options="field:'fetchLongitude'" >取样点经度</th>
	            <th data-options="field:'fetchLatitude'" >取样点纬度</th>
	            <th data-options="field:'turnLongitude'" >拐点经度</th>
	            <th data-options="field:'turnLatitude'" >拐点纬度</th>
	            <th data-options="field:'waterArea'" >水体面积</th>
	            <th data-options="field:'waterLevel'" >水位</th>
	            <th data-options="field:'waterOpacity'" >透明度</th>
	            <th data-options="field:'waterColor'" >水体颜色</th>
	            <th data-options="field:'waterTemperature'" >水体温度</th>
	            <th data-options="field:'waterPh'" >水体PH值</th>
	            <th data-options="field:'waterTotalSalt'" >全盐含量</th>
	            <th data-options="field:'waterChlorine'" >氯化物含量</th>
	            <th data-options="field:'waterSulfide'" >硫化物含量</th>
	        </tr>  
	    </thead>  
	    <tbody>
	    	<c:forEach items="${waters }" var="water">
		        <tr>
		        	<td>
		        		<c:if test="${user.userRole ne 'super' }">
		            		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit('编辑监测数据','${water.id }')">编辑</a><a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${water.id }')">删除</a>
		            	</c:if>
		            </td>
		            <td><fmt:formatDate value='${water.operationTime }' pattern='yyyy-MM-dd'/></td>
		            <td>${water.stationId }</td>
		            <td>${water.stationName }</td>
		            <td>${water.waterName }</td>
		            <td>${water.fetchLongitude }</td>
		            <td>${water.fetchLatitude }</td>
		            <td>${water.turnLongitude }</td>
		            <td>${water.turnLatitude }</td>
		            <td>${water.waterArea }</td>
		            <td>${water.waterLevel }</td>
		            <td>${water.waterOpacity }</td>
		            <td>${water.waterColor }</td>
		            <td>${water.waterTemperature }</td>
		            <td>${water.waterPh }</td>
		            <td>${water.waterTotalSalt }</td>
		            <td>${water.waterChlorine }</td>
		            <td>${water.waterSulfide }</td>
		        </tr>
	        </c:forEach>
	        <c:if test="${empty waters }">
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