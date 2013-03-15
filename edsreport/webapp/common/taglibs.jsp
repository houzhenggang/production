<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="func" uri="/WEB-INF/tld/func.tld" %>

<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<c:set var="user" value="${func:getCurrentUser()}"/>

<link rel="stylesheet" type="text/css" href="${ctx }/css/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx }/css/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="${ctx }/css/form.css"/>
<script type="text/javascript" src="${ctx }/script/jquery-1.7.1.js"></script>
<script type="text/javascript" src="${ctx }/script/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/script/common.js"></script>
<script type="text/javascript" src="${ctx }/script/validator.js"></script>
<script type="text/javascript" src="${ctx }/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/script/DateUtils.js"></script>
