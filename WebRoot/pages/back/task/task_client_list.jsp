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
<body class="sarchbody">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li><a href="#">客户任务列表</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div id="usual1" class="usual">
			<div class="formtitle">
				<span>客户任务列表</span>
			</div>

			<table class="tablelist">
				<thead>
					<tr>
						<th width="35%">任务名称</th>
						<th width="15%">关联客户</th>
						<th width="7%">优先级</th>
						<th width="8%">回访方式</th>
						<th width="8%">回访状态</th>
						<th width="15%">预约日期</th>
						<th width="5%">完成状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allTasks}" var="task">
						<tr> 
							<td>
								<c:if test="${allActions['31'] != null}">
									<a href="<%=basePath%>${allActions['31'].url}?cid=${task.client.cid}&tid=${task.tid}">${task.title}</a>
								</c:if>
								<c:if test="${allActions['31'] == null}">
									${task.title}
								</c:if>
							</td>
							<td>${client.name}</td>
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
							<td>
								${task.tdate}
							</td>
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
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
