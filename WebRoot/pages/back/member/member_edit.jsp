<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String addUrl = basePath + "pages/back/member/MemberServletBack/edit" ;
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp"/>
<script type="text/javascript" src="<%=basePath%>js/pages/back/member/member_edit.js"></script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="main.html" target="_top">首页</a></li>
    <li>编辑用户信息</li>
    </ul>
</div>

	<div class="formbody">
		<div class="formtitle">
			<span>编辑用户</span>
		</div>
		<img src="upload/member/${member.photo}" style="width:200px;">
		<form action="<%=addUrl%>" method="post" enctype="multipart/form-data">
			<ul class="forminfo">
				<li><label>用户名</label>
					<label>${member.mid}</label>
					<i></i></li>
				<li><label>联系电话</label>
					<input name="member.tel" id="member.tel" type="text" class="dfinput" value="${member.tel}" /></li>
				<li><label>用户角色</label> 
				<select id="member.role.rid" name="member.role.rid">
					<c:forEach items="${allRoles}" var="role">
						<option value="${role.rid}" ${member.role.rid==role.rid?"selected" : ""}>${role.title}</option>
					</c:forEach>
				</select>
				<li><label>照片</label>
					<input name="pic" id="pic" type="file" class="dfinput" />
				</li>
				<li><label>用户状态</label><label>
					<input name="member.locked" id="member.locked" type="radio" class="" value="0" ${member.locked == 0 ? "checked" : ""}/>活跃
					<input name="member.locked" id="member.locked" type="radio" class="" value="1" ${member.locked == 1 ? "checked" : ""}/>锁定
				</label></li>
				<li><label>&nbsp;</label>
					<input type="hidden" name="member.photo" id="member.photo" value="${member.photo}">
					<input type="hidden" name="member.mid" id="member.mid" value="${member.mid}">
					<input type="submit" class="btn" value="修改用户" /></li>
			</ul>
		</form>
	</div>
</body>
</html>
