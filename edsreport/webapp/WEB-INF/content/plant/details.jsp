<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function addValidate(){
		var result = true;
		$("[name='plantNames']",document.forms[0]).each(function(){
			if(this.value == ""){
				$.messager.alert("系统提示" , "<br>植物名称不能为空！");
				result = false;
			}
		});
		return result;
	}

	function submitForm(){
		$("#userForm").form('submit',{
			url : '${ctx }/security/plant/saveDetails.action',
			onSubmit: function(){
				var result = $(this).form('validate');
				//if(result){
					//result = addValidate();
				//}
				return result;
			},
			success: function(result){
				$dp.hide();
				if(result == "1"){
					$.messager.alert('系统提示', '<br>操作成功', "info", function(){
						parent.CloseWin("myWindows");
					});
				}else{
					$.messager.alert("系统提示", "<br>操作失败，请重试！", "info");
				}
			}
		});
	}
	var html = '<tr height="35px"><td class="cn_fontright" width="100px">'+
	'<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="javascript:deletePlantDetails(this)">'+
	'植物名称</a></td><td><select name="plantNames" class="easyui-combobox" style="width:200px" data-options="required:true">'+
	'<option value="" ></option>'+
	'<c:forEach items="${plantSelects }" var="plantSelect">'+
	'<option value="${plantSelect }">${plantSelect }</option>'+
	'</c:forEach></select></td></tr>';
	
	//增加植物物种输入域
	function addPlantDetails(){
		$("#detailsTable").append(html);
		var size = $("#detailsTable").find("tr").size();
		$.parser.parse($("#detailsTable").find("tr")[size-1]);
	}
	
	//删除植物物种输入域
	function deletePlantDetails(obj){
		$(obj).parents("tr").each(function(){
			$(this).empty();
			$(this).css("display","none");
		});
	}
</script>
</head>
<body style="overflow-y: auto; overflow-x: hidden;padding: 0px; background-color: #fafafa">
	<form id="userForm" name="userForm" class="form" method="post" novalidate>
		<fieldset class="x-fieldset">
			<legend class="x-fieldset-header x-unselectable">基本信息——<font class="tip-red">请认真填写</font></legend>
			<input type="hidden" value="${plantDetails.plantSpeciesId }" name="plantSpeciesId">
			<input type="hidden" value="${plantDetails.plantMonitorArea }" name="plantMonitorArea">
			<table id="detailsTable" style="width: 375px" border="0" cellpadding="0" cellspacing="0">
				<c:forEach items="${plantDetails.plantNames }" var="plantName" varStatus="status">
					<tr height="35px">
						<td class="cn_fontright" width="100px">
							<c:choose>
								<c:when test="${status.index eq 0 }">
									<a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true">植物名称</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" 
			            				<c:if test="${user.userRole ne 'super' }">onclick="javascript:deletePlantDetails(this)"</c:if>>植物名称</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
						<c:if test="${user.userRole ne 'super' }">
							<select name="plantNames" class="easyui-combobox" style="width:200px;"
								data-options="required:true">
								<option value="" ></option>
								<c:forEach items="${plantSelects }" var="plantSelect">
									<c:choose>
										<c:when test="${plantSelect eq plantName }">
											<option value="${plantSelect }" selected="selected">${plantSelect }</option>
										</c:when>
										<c:otherwise>
											<option value="${plantSelect }">${plantSelect }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${user.userRole eq 'super' }">
							<input name="plantNames" value="${plantName }" class="x-form-text easyui-validatebox" readonly="readonly">
						</c:if>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty plantDetails.plantNames }">
					<tr height="35px">
						<td class="cn_fontright" width="100px">
							<a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true">植物名称</a>
						</td>
						<td>
							<select name="plantNames" class="easyui-combobox" style="width:200px;"
									data-options="required:true">
								<option value="" ></option>
								<c:forEach items="${plantSelects }" var="plantSelect">
										<option value="${plantSelect }">${plantSelect }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</c:if>
			</table>
			<table style="width: 375px" border="0" cellpadding="0" cellspacing="0">
				<tr height="35px">
					<td class="cn_fontright" width="100px"></td>
					<td>
						<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
			            <c:if test="${user.userRole ne 'super' }">onclick="javascript:addPlantDetails()"</c:if>>增加植物物种</a>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>