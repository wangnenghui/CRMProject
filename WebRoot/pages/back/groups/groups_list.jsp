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
	src="<%=basePath%>js/pages/back/groups/groups_list.js"></script>
</head>
<body class="sarchbody">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li><a href="#">权限组列表</a></li>
		</ul>
	</div>
	<div class="formtitle">
		<span>权限组列表</span>
	</div>
	<div class="formbody">
		<table class="tablelist">
			<thead>
				<tr>
					<th width="15%">LOGO</th>
					<th width="20%">组名称</th>
					<th width="50%">权限组类型</th>
					<th width="15%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${allGroupses != null}">
					<c:forEach items="${allGroupses}" var="gup">
						<tr>
							<td><img src="images/${gup.img}" /></td>
							<td>${gup.title}</td>
							<td>${gup.type}</td>
							<td>
								<c:if test="${allActions['24'] != null}">
									<a href="<%=basePath%>${allActions['24'].url}?gid=${gup.gid}" class="tablelink">查看详情</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>

</body>


</html>
