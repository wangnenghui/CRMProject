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
<script type="text/javascript" 
	src="<%=basePath%>/js/pages/back/mclient/client_show.js"></script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li>查看客户详情</li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>查看客户详情</span>
		</div>
			<ul class="forminfo">
				<li><label>客户编号</label><i>${client.cid}</i></li>
				<li><label>客户名称</label><i>${client.name}</i></li>
				<li><label>客服</label><i>${client.member.mid}</i></li>
				<li><label>性别</label><i>${client.sex}</i></li>
				<li><label>电话</label><i>${client.tel}</i></li>
				<li><label>邮箱</label><i>${client.email}</i></li>
				<li><label>QQ</label><i>${client.qq}</i></li>
				<li><label>客户状态</label><i><c:if test="${client.type == 0}">
										初步咨询
									</c:if>
									<c:if test="${client.type == 1}">
										有意向
									</c:if>
									<c:if test="${client.type == 2}">
										无意向
									</c:if>
									<c:if test="${client.type == 3}">
										准备签约
									</c:if>
									<c:if test="${client.type == 4}">
										签约完毕
									</c:if></i></li>
				<li><label>备注</label> <textarea name="client.note"
						id="client.note" cols="" rows="" class="textinput">${client.note}</textarea></li>
				<li><label>&nbsp;</label>
			</ul>
	</div>

</body>
</html>
