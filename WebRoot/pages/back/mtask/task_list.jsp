<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String formUrl = basePath + "pages/back/mtask/ManagerTaskServletBack/listSplit" ;
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>/js/pages/back/task/task_list.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/select-ui.min.js"></script>
</head>
<body>
<body class="sarchbody">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li><a href="#">任务列表</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div id="usual1" class="usual">
		<form action="<%=formUrl%>" method="post">
			<ul class="seachform1">
				<li><label>客户姓名</label><input name="kw" id="kw" type="text"
					class="scinput1" value="${keyWord}"/></li>
				<li><label>回访方式</label>
					<div class="vocation">
						<select class="select3" id="visit" name="visit">
							<option value="-1" ${visit == -1 ? "selected" : ""}>不限</option>
							<option value="0" ${visit == 0 ? "selected" : ""}>电话回访</option>
							<option value="1" ${visit == 1 ? "selected" : ""}>上门回访</option>
							<option value="2" ${visit == 2 ? "selected" : ""}>网络咨询</option>
						</select>
					</div></li>
				<li><label>回访状态</label>
					<div class="vocation">
						<select class="select3" id="type" name="type">
							<option value="-1" ${type == -1 ? "selected" : ""}>不限</option>
							<option value="0" ${type == 0 ? "selected" : ""}>初步咨询</option>
							<option value="1" ${type == 1 ? "selected" : ""}>有意向</option>
							<option value="2" ${type == 2 ? "selected" : ""}>无意向</option>
							<option value="3" ${type == 3 ? "selected" : ""}>准备签约</option>
							<option value="4" ${type == 4 ? "selected" : ""}>签约完毕</option>
						</select>
					</div></li>
			</ul>
			<ul class="seachform1">
				<li class="sarchbtn"><label>&nbsp;</label>
				<input type="submit" class="scbtn" value="查询" />
			</ul>
			</form>
			<div class="formtitle">
				<span>任务列表</span>
			</div>

			<table class="tablelist">
				<thead>
					<tr>
						<th width="35%">任务名称</th>
						<th width="7%">关联客户</th>
						<th width="6%">优先级</th>
						<th width="7%">回访方式</th>
						<th width="7%">回访状态</th>
						<th width="11%">预约日期</th>
						<th width="5%">完成状态</th>
						<th width="15%">所属用户</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allTasks}" var="task">
						<tr>
							<td><c:if test="${allActions['32'] != null}">
									<a href="<%=basePath%>${allActions['32'].url}?cid=${task.client.cid}&tid=${task.tid}">${task.title}</a>
								</c:if> <c:if test="${allActions['32'] == null}">
									${task.title}
								</c:if></td>
							<td>
								<c:if test="${allActions['27'] != null}"> 
									<a href="<%=basePath%>${allActions['27'].url}?client.cid=${task.client.cid}" class="tablelink">${allClients[task.client.cid]}</a>
								</c:if> 
								<c:if test="${allActions['27'] == null}">
									${allClients[task.client.cid]}
								</c:if>
							</td>
							<td><c:if test="${task.level == 0}">
									最高
								</c:if> <c:if test="${task.level == 1}">
									中等
								</c:if> <c:if test="${task.level == 2}">
									最低
								</c:if></td>
							<td><c:if test="${task.visit == 0}">
									电话
								</c:if> <c:if test="${task.visit == 1}">
									上门
								</c:if> <c:if test="${task.visit == 2}">
									网络
								</c:if></td>
							<td><c:if test="${task.type == 0}">
									初步咨询
								</c:if> <c:if test="${task.type == 1}">
									有意向
								</c:if> <c:if test="${task.type == 2}">
									无意向
								</c:if> <c:if test="${task.type == 3}">
									准备签约
								</c:if> <c:if test="${task.type == 4}">
									签约完毕
								</c:if></td>
							<td>${task.tdate}</td>
							<td><c:if test="${task.status == 0}">
									关闭
								</c:if> <c:if test="${task.status == 1}">
									进行中
								</c:if> <c:if test="${task.status == 2}">
									完成
								</c:if></td>
							<td>
								${task.member.mid}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<jsp:include page="/pages/plugins/split_page_plugin_bar.jsp"/>
	</div>
</body>
</body>

</html>
