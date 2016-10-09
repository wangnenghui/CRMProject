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
			<li><a href="#">角色详情</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>角色详情</span>
		</div>
			<ul class="forminfo">
				<li><label>角色名称</label><i><label>${role.title}</label></i></li>
			</ul>
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
				<c:if test="${role.groups != null}">
					<c:forEach items="${role.groups}" var="gup">
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
