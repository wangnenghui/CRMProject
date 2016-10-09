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
	src="<%=basePath%>js/pages/back/role/role_list.js"></script>
</head>
<body class="sarchbody">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li><a href="#">角色列表</a></li>
		</ul>
	</div>
	<div class="formtitle">
		<span>角色列表</span>
	</div>
	<div class="formbody">
		<table class="tablelist">
			<thead>
				<tr>
					<th width="4%"><input type="checkbox" id="selAll"></th>
					<th width="36%">名称</th>
					<th width="60%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${allRoles != null}">
					<c:forEach items="${allRoles}" var="role">
						<tr>
							<td><input type="checkbox" id="rid" name="rid" value="${role.rid}"></td>
							<td>${role.title}</td>
							<td>
								<c:if test="${allActions['34'] != null}">
									<a href="<%=basePath%>${allActions['34'].url}?rid=${role.rid}" class="tablelink">查看详情</a>
								</c:if>
								
								<c:if test="${allActions['21'] != null}">
									<a href="<%=basePath%>${allActions['21'].url}?rid=${role.rid}" class="tablelink">修改</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<c:if test="${allActions['22'] != null}">
			<div class="buttonform1">
				<li class="delbtn"><label>&nbsp;</label><input id="delClient"
					type="button" class="deltn" value="删除角色" /></li>
			</div>
		</c:if>
	</div>

</body>


</html>
