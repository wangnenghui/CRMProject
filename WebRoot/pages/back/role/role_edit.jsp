<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String addUrl = basePath
			+ "pages/back/role/RoleServletBack/edit";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp" />
<script type="text/javascript" 
	src="<%=basePath%>/js/pages/back/role/role_edit.js"></script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li>编辑角色</li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>编辑角色</span>
		</div>
		<form action="<%=addUrl%>" method="post" id="myform">
			<ul class="forminfo">
				<li><label>角色名称</label>
				<input name="role.title" id="role.title" type="text" class="dfinput" value="${role.title}"/> <i>不能为空！</i></li>
				<li><label>包含权限组</label><label><span id="gidSpan"></span></label></li>
				<c:forEach items="${allGroupses}" var="entry">
					<li><label>${entry.key}</label><ul>
					<c:forEach items="${entry.value}" var="gup">
						<li><input name="gid" id="gid" type="checkbox" value="${gup.gid}" ${roleGroups[gup.gid] ? "checked" : ""}/> <i>${gup.title}</i></li>
					</c:forEach></ul>
				</c:forEach>
				
				<li><label>&nbsp;</label>
				<input type="hidden" name="role.rid" id="role.rid" value="${role.rid}">
				<input name="" type="submit" class="btn" value="确认保存" /></li>
			</ul>
		</form>
	</div>

</body>
</html>
