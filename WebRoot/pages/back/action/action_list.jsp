<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/pages/back/action/action_list.js"></script>
</head>
<body class="sarchbody">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li><a href="#">权限列表</a></li>
		</ul>
	</div>
	<div class="formtitle">
		<span>权限列表</span>
	</div>
	<div class="formbody">
		<table class="tablelist">
			<thead>
				<tr>
					<th width="15%">名称</th>
					<th width="15%">权限类型</th>
					<th width="70%">路径</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${allActions != null}">
					<c:forEach items="${allActions}" var="action">
						<tr>
							<td>${action.title}</td>
							<td>${action.menu == 0 ? "页面权限" : "菜单与页面权限"}</td>
							<td>${action.url}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>

</body>


</html>
