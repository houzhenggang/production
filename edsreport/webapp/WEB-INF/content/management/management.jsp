<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	OpenTab("1、${tabName}", "${ctx}${tabUrl}");
});
</script>
</head>
<body>
	<div id="cc" class="easyui-layout" data-options="fit:true">
		<!-- 
	    <div data-options="region:'north',split:true" style="height:40px;">鄂尔多斯气象局农业气象数据观测系统</div>
	     --> 
	    <div data-options="region:'north',split:true" 
	    style="height:37px; padding-left: 2px; padding-top: 2px; background-color: #fafafa">
	    	<c:forEach items="${menuMap }" var="menu" varStatus="status">
	    	<c:choose>
	    		<c:when test="${empty  menuMap[status.index + 1]}">
	    			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" href="javascript:void(0)" 
					onclick="OpenTab('${status.index + 1 }、${menu.name}','${ctx}${menu.url }')">${status.index + 1 }、${menu.name}</a>
	    		</c:when>
	    		<c:otherwise>
	    			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" href="javascript:void(0)" 
					onclick="OpenTab('${status.index + 1 }、${menu.name}','${ctx}${menu.url }')">${status.index + 1 }、${menu.name}</a>
					<img src="${ctx }/images/split-y.gif" height="16" width="2"/>
	    		</c:otherwise>
	    	</c:choose>
			</c:forEach>
	    </div>
	    <div data-options="region:'center'">
	    <div id="tabs" class="easyui-tabs" data-options="fit:true">

	    </div>
	    </div>
	</div>
</body>
</html>