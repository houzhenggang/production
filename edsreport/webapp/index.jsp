<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统首页</title>
<script type="text/javascript">
$(document).ready(function(){
	parent.parent.window.location.href = "${ctx }/login.action";
});
</script>
</head>
<body></body>
</html>