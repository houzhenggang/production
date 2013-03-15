<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>土壤水分监测数据报表</title>

<script type="text/javascript">
	//新增修改
	function edit(title,id){
		OpenWin(title, 540, 600, "${ctx }/security/soilanalysis/constantEdit.action?stationId="+id);
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
					url : '${ctx }/security/soilanalysis/constantDelete.action?soilConstantId='+id,
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
					window.location.href="${ctx }/security/soilanalysis/constant.action?pageNo="+pageNumber+"&pageSize="+pageSize;
				}
			});
			
			if('${fn:length(soilConstants) }' == '0' && '${user.userRole }' !='super' ){
				edit('添加站点盒重数据','');
			}
	});
</script>
</head>
<body>
	<div id="toolbar" style="display: none">
		<a href="${ctx }/security/soilanalysis/analysis.action" class="easyui-linkbutton" iconCls="icon-back" plain="true">返回土壤水分观测数据列表</a>
		<c:if test="${user.userRole ne 'super' }">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:edit('添加站点盒重数据','')">添加盒重数据</a>
		</c:if>
	</div>
	<table id="userInfo" class="easyui-datagrid" data-options="fit:true,fitColumns:true,singleSelect:true,loadMsg:'加载站点盒重数据...',
	pagination:true, title:'站点盒重数据列表', rownumbers:true, toolbar:'#toolbar', iconCls:'icon-save',scrollbarSize:0" style="display: none">  
	    <thead frozen="true">
	    	<tr>
	    		<th data-options="field:'operation', align:'center'">操作</th>
	            <th data-options="field:'stationId', align:'center'">站号</th>
	            <th data-options="field:'stationName', align:'center'">站名</th>
	    	</tr>
	    </thead>
	    <thead>
	    	<tr>
	    		 <th data-options="field:'reportSuffix', align:'center'" >报文后缀名</th>
				<th data-options="field:'fieldCapacity10', align:'center'" >田间持水量10CM（%）</th>
				<th data-options="field:'fieldCapacity20', align:'center'" >田间持水量20CM（%）</th>
				<th data-options="field:'fieldCapacity30', align:'center'" >田间持水量30CM（%）</th>
				<th data-options="field:'fieldCapacity40', align:'center'" >田间持水量40CM（%）</th>
				<th data-options="field:'fieldCapacity50', align:'center'" >田间持水量50CM（%）</th>
				<th data-options="field:'wiltingMoisture10', align:'center'" >凋萎湿度10CM（%）</th>
				<th data-options="field:'wiltingMoisture20', align:'center'" >凋萎湿度20CM（%）</th>
				<th data-options="field:'wiltingMoisture30', align:'center'" >凋萎湿度30CM（%）</th>
				<th data-options="field:'wiltingMoisture40', align:'center'" >凋萎湿度40CM（%）</th>
				<th data-options="field:'wiltingMoisture50', align:'center'" >凋萎湿度50CM（%）</th>
				<th data-options="field:'soilDensity10', align:'center'" >土壤容重10CM（克/立方厘米）</th>
				<th data-options="field:'soilDensity20', align:'center'" >土壤容重20CM（克/立方厘米）</th>
				<th data-options="field:'soilDensity30', align:'center'" >土壤容重30CM（克/立方厘米）</th>
				<th data-options="field:'soilDensity40', align:'center'" >土壤容重40CM（克/立方厘米）</th>
				<th data-options="field:'soilDensity50', align:'center'" >土壤容重50CM（克/立方厘米）</th>

	            <th data-options="field:'boxWeight1_10', align:'center'" >盒重0001（g）</th>
				<th data-options="field:'boxWeight1_20', align:'center'" >盒重0002（g）</th>
				<th data-options="field:'boxWeight1_30', align:'center'" >盒重0003（g）</th>
				<th data-options="field:'boxWeight1_40', align:'center'" >盒重0004（g）</th>
				<th data-options="field:'boxWeight1_50', align:'center'" >盒重0005（g）</th>
				
				<th data-options="field:'boxWeight2_10', align:'center'" >盒重0006（g）</th>
				<th data-options="field:'boxWeight2_20', align:'center'" >盒重0007（g）</th>
				<th data-options="field:'boxWeight2_30', align:'center'" >盒重0008（g）</th>
				<th data-options="field:'boxWeight2_40', align:'center'" >盒重0009（g）</th>
				<th data-options="field:'boxWeight2_50', align:'center'" >盒重0010（g）</th>
				
				<th data-options="field:'boxWeight3_10', align:'center'" >盒重0011（g）</th>
				<th data-options="field:'boxWeight3_20', align:'center'" >盒重0012（g）</th>
				<th data-options="field:'boxWeight3_30', align:'center'" >盒重0013（g）</th>
				<th data-options="field:'boxWeight3_40', align:'center'" >盒重0014（g）</th>
				<th data-options="field:'boxWeight3_50', align:'center'" >盒重0015（g）</th>
				
				<th data-options="field:'boxWeight4_10', align:'center'" >盒重0016（g）</th>
				<th data-options="field:'boxWeight4_20', align:'center'" >盒重0017（g）</th>
				<th data-options="field:'boxWeight4_30', align:'center'" >盒重0018（g）</th>
				<th data-options="field:'boxWeight4_40', align:'center'" >盒重0019（g）</th>
				<th data-options="field:'boxWeight4_50', align:'center'" >盒重0020（g）</th>
	        </tr>
	    </thead>
	    <tbody>
	    	<c:forEach items="${soilConstants }" var="soilConstant">
		        <tr>
		        	<td>
		        		<c:if test="${user.userRole ne 'super' }">
		            		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit('编辑监测数据','${soilConstant.stationId }')">编辑</a><a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:deleteRecord('${soilConstant.stationId }')">删除</a>
		            	</c:if>
		            </td>
					<td>${soilConstant.stationId }</td>
					<td>${soilConstant.stationName }</td>
					<td>${soilConstant.reportSuffix }</td>
					<td>${soilConstant.fieldCapacity10 }</td>
					<td>${soilConstant.fieldCapacity20 }</td>
					<td>${soilConstant.fieldCapacity30 }</td>
					<td>${soilConstant.fieldCapacity40 }</td>
					<td>${soilConstant.fieldCapacity50 }</td>
					<td>${soilConstant.wiltingMoisture10 }</td>
					<td>${soilConstant.wiltingMoisture20 }</td>
					<td>${soilConstant.wiltingMoisture30 }</td>
					<td>${soilConstant.wiltingMoisture40 }</td>
					<td>${soilConstant.wiltingMoisture50 }</td>
					<td>${soilConstant.soilDensity10 }</td>
					<td>${soilConstant.soilDensity20 }</td>
					<td>${soilConstant.soilDensity30 }</td>
					<td>${soilConstant.soilDensity40 }</td>
					<td>${soilConstant.soilDensity50 }</td>
					<td>${soilConstant.boxWeight1_10 }</td>
					<td>${soilConstant.boxWeight1_20 }</td>
					<td>${soilConstant.boxWeight1_30 }</td>
					<td>${soilConstant.boxWeight1_40 }</td>
					<td>${soilConstant.boxWeight1_50 }</td>
					<td>${soilConstant.boxWeight2_10 }</td>
					<td>${soilConstant.boxWeight2_20 }</td>
					<td>${soilConstant.boxWeight2_30 }</td>
					<td>${soilConstant.boxWeight2_40 }</td>
					<td>${soilConstant.boxWeight2_50 }</td>
					<td>${soilConstant.boxWeight3_10 }</td>
					<td>${soilConstant.boxWeight3_20 }</td>
					<td>${soilConstant.boxWeight3_30 }</td>
					<td>${soilConstant.boxWeight3_40 }</td>
					<td>${soilConstant.boxWeight3_50 }</td>
					<td>${soilConstant.boxWeight4_10 }</td>
					<td>${soilConstant.boxWeight4_20 }</td>
					<td>${soilConstant.boxWeight4_30 }</td>
					<td>${soilConstant.boxWeight4_40 }</td>
					<td>${soilConstant.boxWeight4_50 }</td>
		        </tr>
	        </c:forEach>
	         <c:if test="${empty soilConstants }">
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