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
		OpenWin(title, 810, 560, "${ctx }/security/soilanalysis/edit.action?id="+id);
	}
	//Excel表格导出
	function exportExcel(title){
		OpenWin(title, 480, 270, "${ctx }/security/soilanalysis/reportHeader.action");
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
					url : '${ctx }/security/soilanalysis/delete.action?soilAnalysisId='+id,
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
					window.location.href="${ctx }/security/soilanalysis/analysis.action?pageNo="+pageNumber+"&pageSize="+pageSize;
				}
			});
			if('${fn:length(soilAnalysiss) }' == '0' && '${user.userRole }' !='super' ){
				edit('添加土壤风蚀监测数据','');
			}
	});
</script>
</head>
<body>
	<div id="toolbar" style="display: none">
		<c:if test="${user.userRole ne 'super' }">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:edit('添加土壤风蚀监测数据','')">添加监测数据</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-xls" plain="true" onclick="javascript:exportExcel('导出excel表格参数')">生成数据报文</a>
		</c:if>
		<a href="${ctx }/security/soilanalysis/constant.action" class="easyui-linkbutton" iconCls="icon-save" plain="true">站点盒重水文常量数据</a>
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载土壤水分观测监测数据...',
	pagination:true, title:'土壤水分观测数据列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead>  
	        <tr>
	        	<th data-options="field:'operation', align:'center'">操作</th>
	        	<th data-options="field:'operationTime'">录入时间</th>
	            <th data-options="field:'stationId', align:'center'">站号</th>
	            <th data-options="field:'stationName', align:'center'">站名</th>
	            <!-- 
	            <th data-options="field:'soilMonitorDate'" >监测年月</th>
	             -->
	            <th data-options="field:'drySoilHeight',align:'center'" >干土层厚度（cm）</th>
	        	<th data-options="field:'wetSoilboxWeight1_10', align:'center', align:'center'">样点1-10CM（湿重/干重：g）</th>
				<th data-options="field:'wetSoilboxWeight1_20', align:'center', align:'center'">样点1-20CM（湿重/干重：g）</th>
				<th data-options="field:'wetSoilboxWeight1_30', align:'center', align:'center'">样点1-30CM（湿重/干重：g）</th>
				<th data-options="field:'wetSoilboxWeight1_40', align:'center', align:'center'">样点1-40CM（湿重/干重：g）</th>
				<th data-options="field:'wetSoilboxWeight1_50', align:'center', align:'center'">样点1-50CM（湿重/干重：g）</th>
				
				<th data-options="field:'drySoilboxWeight2_10', align:'center', align:'center'">样点2-10CM（湿重/干重：g）</th>
				<th data-options="field:'drySoilboxWeight2_20', align:'center', align:'center'">样点2-20CM（湿重/干重：g）</th>
				<th data-options="field:'drySoilboxWeight2_30', align:'center', align:'center'">样点2-30CM（湿重/干重：g）</th>
				<th data-options="field:'drySoilboxWeight2_40', align:'center', align:'center'">样点2-40CM（湿重/干重：g）</th>
				<th data-options="field:'drySoilboxWeight2_50', align:'center', align:'center'">样点2-50CM（湿重/干重：g）</th>
				
				<th data-options="field:'wetSoilboxWeight3_10', align:'center', align:'center'">样点3-10CM（湿重/干重：g）</th>
				<th data-options="field:'wetSoilboxWeight3_20', align:'center', align:'center'">样点3-20CM（湿重/干重：g）</th>
				<th data-options="field:'wetSoilboxWeight3_30', align:'center', align:'center'">样点3-30CM（湿重/干重：g）</th>
				<th data-options="field:'wetSoilboxWeight3_40', align:'center', align:'center'">样点3-40CM（湿重/干重：g）</th>
				<th data-options="field:'wetSoilboxWeight3_50', align:'center', align:'center'">样点3-50CM（湿重/干重：g）</th>
				
				<th data-options="field:'drySoilboxWeight4_10', align:'center', align:'center'">样点4-10CM（湿重/干重：g）</th>
				<th data-options="field:'drySoilboxWeight4_20', align:'center', align:'center'">样点4-20CM（湿重/干重：g）</th>
				<th data-options="field:'drySoilboxWeight4_30', align:'center', align:'center'">样点4-30CM（湿重/干重：g）</th>
				<th data-options="field:'drySoilboxWeight4_40', align:'center', align:'center'">样点4-40CM（湿重/干重：g）</th>
				<th data-options="field:'drySoilboxWeight4_50', align:'center', align:'center'">样点4-50CM（湿重/干重：g）</th>
	        </tr>  
	    </thead>  
	    <tbody>
	    	<c:forEach items="${soilAnalysiss }" var="soilAnalysis">
		        <tr>
		        	<td>
		        		<c:if test="${user.userRole ne 'super' }">
		            		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit('编辑监测数据','${soilAnalysis.id }')">编辑</a><a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${soilAnalysis.id }')">删除</a>
		            	</c:if>
		            </td>
		            <td><fmt:formatDate value='${soilAnalysis.operationTime }' pattern='yyyy年MM月dd日'/></td>
					<td>${soilAnalysis.stationId }</td>
					<td>${soilAnalysis.stationName }</td>
					<!-- 
					<td><fmt:formatDate value='${soilAnalysis.soilAnalysisDate }' pattern='yyyy年MM月dd日'/></td>
					 -->
					<td>${(empty soilAnalysis.drySoilHeight)?'无':soilAnalysis.drySoilHeight }</td>
					
					<td>${soilAnalysis.wetSoilboxWeight1_10 }/${soilAnalysis.drySoilboxWeight1_10 }</td>
					<td>${soilAnalysis.wetSoilboxWeight1_20 }/${soilAnalysis.drySoilboxWeight1_20 }</td>
					<td>${soilAnalysis.wetSoilboxWeight1_30 }/${soilAnalysis.drySoilboxWeight1_30 }</td>
					<td>${soilAnalysis.wetSoilboxWeight1_40 }/${soilAnalysis.drySoilboxWeight1_40 }</td>
					<td>${soilAnalysis.wetSoilboxWeight1_50 }//${soilAnalysis.drySoilboxWeight1_50 }</td>
					
					<td>${soilAnalysis.wetSoilboxWeight2_10 }/${soilAnalysis.drySoilboxWeight2_10 }</td>
					<td>${soilAnalysis.wetSoilboxWeight2_20 }/${soilAnalysis.drySoilboxWeight2_20 }</td>
					<td>${soilAnalysis.wetSoilboxWeight2_30 }/${soilAnalysis.drySoilboxWeight2_30 }</td>
					<td>${soilAnalysis.wetSoilboxWeight2_40 }/${soilAnalysis.drySoilboxWeight2_40 }</td>
					<td>${soilAnalysis.wetSoilboxWeight2_50 }//${soilAnalysis.drySoilboxWeight2_50 }</td>
					
					<td>${soilAnalysis.wetSoilboxWeight3_10 }/${soilAnalysis.drySoilboxWeight3_10 }</td>
					<td>${soilAnalysis.wetSoilboxWeight3_20 }/${soilAnalysis.drySoilboxWeight3_20 }</td>
					<td>${soilAnalysis.wetSoilboxWeight3_30 }/${soilAnalysis.drySoilboxWeight3_30 }</td>
					<td>${soilAnalysis.wetSoilboxWeight3_40 }/${soilAnalysis.drySoilboxWeight3_40 }</td>
					<td>${soilAnalysis.wetSoilboxWeight3_50 }//${soilAnalysis.drySoilboxWeight3_50 }</td>
					
					<td>${soilAnalysis.wetSoilboxWeight4_10 }/${soilAnalysis.drySoilboxWeight4_10 }</td>
					<td>${soilAnalysis.wetSoilboxWeight4_20 }/${soilAnalysis.drySoilboxWeight4_20 }</td>
					<td>${soilAnalysis.wetSoilboxWeight4_30 }/${soilAnalysis.drySoilboxWeight4_30 }</td>
					<td>${soilAnalysis.wetSoilboxWeight4_40 }/${soilAnalysis.drySoilboxWeight4_40 }</td>
					<td>${soilAnalysis.wetSoilboxWeight4_50 }//${soilAnalysis.drySoilboxWeight4_50 }</td>
		        </tr>
	        </c:forEach>
	        <c:if test="${empty soilAnalysiss }">
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