<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天然草场监测数据报表</title>

<script type="text/javascript">
	//新增修改
	function edit(title,id){
		OpenWin(title, 480, 560, "${ctx }/security/grass/edit.action?id="+id);
	}
	//Excel表格导出
	function exportExcel(title){
		OpenWin(title, 480, 350, "${ctx }/security/grass/reportHeader.action");
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
					url : '${ctx }/security/grass/delete.action?grassId='+id,
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
					window.location.href="${ctx }/security/grass/grass.action?pageNo="+pageNumber+"&pageSize="+pageSize;
				}
			});
			
			if('${fn:length(grasss) }' == '0' && '${user.userRole }' !='super' ){
				edit('添加天然草场监测数据','');
			}
	});
</script>
</head>
<body>
	<div id="toolbar" style="display: none">
	<c:if test="${user.userRole ne 'super' }">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:edit('添加天然草场监测数据','')">添加监测数据</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-xls" plain="true" onclick="javascript:exportExcel('导出excel表格参数')">导出Excel表格</a>
	</c:if>
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载天然草场监测数据...',
	pagination:true, title:'天然草场监测数据列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead>  
	        <tr>
	        	<th data-options="field:'operation', align:'center'">操作</th>
	        	<th data-options="field:'operationTime'">录入时间</th>
	            <th data-options="field:'stationId'">站号</th>
	            <th data-options="field:'stationName'">站名</th>
	            <th data-options="field:'monitorArea'" >监测地段</th>
	        	<th data-options="field:'grassName'" >牧草名称</th>
	        	<th data-options="field:'backDate'" >返青期</th>
	        	<th data-options="field:'flowerDate'" >开花期</th>
	        	<th data-options="field:'yellowDate'" >黄枯期</th>
	        	<th data-options="field:'absoluteHeigth'" >绝对高度</th>
	        	<th data-options="field:'grassHeigth'" >草层高度</th>
	        	<th data-options="field:'coverDegree'" >盖度</th>
	        	<th data-options="field:'earthBiomass'" >地上生物量</th>
	        	<th data-options="field:'betterGrassRate'" >优良牧草比例</th>
	        	<th data-options="field:'remarks'" >备注</th>
	        </tr>  
	    </thead>  
	    <tbody>
	    	<c:forEach items="${grasss }" var="grass">
		        <tr>
		        	<td>
			        	<c:if test="${user.userRole ne 'super' }">
			            	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit('编辑监测数据','${grass.id }')">编辑</a><a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${grass.id }')">删除</a>
			            </c:if>
		            </td>
		            <td><fmt:formatDate value='${grass.operationTime }' pattern='yyyy-MM-dd'/></td>
		            <td>${grass.stationId }</td>
		            <td>${grass.stationName }</td>
		            <td>${grass.monitorArea }</td>
		            <td>${grass.grassName }</td>
		            <td>${grass.backDate }</td>
		            <td>${grass.flowerDate }</td>
		            <td>${grass.yellowDate }</td>
		            <td>${grass.absoluteHeigth }</td>
		            <td>${grass.grassHeigth }</td>
		            <td>${grass.coverDegree }</td>
		            <td>${grass.earthBiomass }</td>
		            <td>${grass.betterGrassRate }</td>
		            <td>${grass.remarks }</td>
		        </tr>
	        </c:forEach>
	        <c:if test="${empty grasss }">
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