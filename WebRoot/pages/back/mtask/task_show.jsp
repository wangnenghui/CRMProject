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
<script type="text/javascript" src="<%=basePath%>js/pages/back/mtask/task_show.js"></script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li>查看任务详情</li>
		</ul>
	</div>

	<div class="formbody">
		<div class="formtitle">
			<span>查看任务详情</span>
		</div>


		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="welinfo">
			<tr>
				<td>任务编号：</td>
				<td>${task.tid}</td>
			</tr>
			<tr>
				<td width="9%">主题：</td>
				<td width="91%">${task.title}</td>
			</tr>
			<tr>
				<td>优先级：</td>
				<td>
						<c:if test="${task.level == 0}">
							最高
						</c:if>
						<c:if test="${task.level == 1}">
							中等
						</c:if>
						<c:if test="${task.level == 2}">
							最低
						</c:if>
				</td>
			</tr>
			<tr>
				<td>预计安排时间：</td>
				<td>${task.tdate}</td>
			</tr>
			<tr>
				<td>回访状态：</td>
				<td>
					<c:if test="${task.type == 0}">
						初步咨询
					</c:if>
					<c:if test="${task.type == 1}">
						有意向
					</c:if>
					<c:if test="${task.type == 2}">
						无意向
					</c:if>
					<c:if test="${task.type == 3}">
						准备签约
					</c:if>
					<c:if test="${task.type == 4}">
						签约完毕
					</c:if>
				</td>
			</tr>
			<tr>
				<td>回访方式：</td>
				<td>
					<c:if test="${task.visit == 0}">
						电话
					</c:if>
					<c:if test="${task.visit == 1}">
						上门
					</c:if>
					<c:if test="${task.visit == 2}">
						网络
					</c:if>
				</td>
			</tr>
			<tr>
				<td>任务状态：</td>
				<td>
					<c:if test="${task.status == 0}">
						关闭
					</c:if>
					<c:if test="${task.status == 1}">
						进行中
					</c:if>
					<c:if test="${task.status == 2}">
						完成
					</c:if>
				</td>
			</tr>
			<tr>
				<td>留言备注：</td>
				<td>${task.note}</td>
			</tr>
		</table>

	</div>
</body>
</html>
