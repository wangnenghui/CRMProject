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
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li><a href="#">系统设置</a></li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>查看公告详情</span>
		</div>

		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="welinfo">
			<tr>
				<td width="11%">公告编号：</td>
				<td width="89%">${news.nid}</td>
			</tr>
			<tr>
				<td>公告标题：</td>
				<td>${news.title}</td>
			</tr>
			<tr>
				<td>公告分类：</td>
				<td>${news.type == 0 ? "普通通知" : "重要通知"}</td>
			</tr>

			<tr>
				<td>公告内容：</td>
				<td>${news.note}</td>
			</tr>
			<tr>
				<td>发布时间：</td>
				<td>${news.pubdate}</td>
			</tr>
		</table>
		<p>&nbsp;</p>
	</div>

</body>
</html>
