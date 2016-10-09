<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String addUrl = basePath + "pages/back/member/MemberServletBack/add" ;
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp"/>
<script type="text/javascript" src="<%=basePath%>js/pages/back/member/member_add.js"></script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="main.html" target="_top">首页</a></li>
    <li>快速添加用户</li>
    </ul>
</div>

	<div class="formbody">
		<div class="formtitle">
			<span>增加新用户</span>
		</div>
		<form action="<%=addUrl%>" method="post" enctype="multipart/form-data">
			<ul class="forminfo">
				<li><label>用户名</label>
					<input name="member.mid" id="member.mid" type="text" class="dfinput" />
					<i></i></li>
				<li><label>密码</label>
					<input name="member.password" id="member.password" type="password" class="dfinput" /></li>
				<li><label>联系电话</label>
					<input name="member.tel" id="member.tel" type="text" class="dfinput" /></li>
				<li><label>用户角色</label> 
				<select id="member.role.rid" name="member.role.rid">
					<c:forEach items="${allRoles}" var="role">
						<option value="${role.rid}">${role.title}</option>
					</c:forEach>
				</select>
				<li><label>照片</label>
					<input name="photo" id="photo" type="file" class="dfinput" />
				</li>
				<li><label>&nbsp;</label>
					<input type="submit" class="btn" value="创建用户" /></li>
			</ul>
		</form>
	</div>
</body>
</html>
