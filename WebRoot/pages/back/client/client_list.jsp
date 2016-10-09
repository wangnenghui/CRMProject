<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String searchUrl = basePath + "pages/back/client/ClientServletBack/listSplit" ;
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
	src="<%=basePath%>js/pages/back/client/client_list.js"></script>
</head>
<body>
<body class="sarchbody">

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li><a href="#">客户列表</a></li>
		</ul>
	</div>

	<div class="formbody">


		<div id="usual1" class="usual">

		<form action="<%=searchUrl%>" method="post">

			<ul class="seachform1">

				<li><label>客户名称</label><input name="kw" id="kw" type="text"
					class="scinput1" value="${keyWord}"/></li>


				<li><label>客户状态</label>
					<div class="vocation">
						<select class="select3" name="type">
							<option value="-1" ${type == -1 ? "selected" : ""}>全部客户</option>
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
				<input type="hidden" name="col" value="name">
				<input type="submit" class="scbtn" value="查询" />
			</ul>

	</form>

			<div class="formtitle">
				<span>列表</span>
			</div>
			<table class="tablelist">
				<thead>
					<tr>
						<th width="5%"><input type="checkbox" id="selAll"></th>
						<th width="6%">编号</th>
						<th width="15%">姓名</th>
						<th width="5%">性别</th>
						<th width="9%">邮箱</th>
						<th width="12%">电话</th>
						<th width="12%">QQ</th>
						<th width="8%">客户状态</th>
						<th width="12%">登记时间</th>
						<th width="16%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${allClients != null}">
						<c:forEach items="${allClients}" var="client">
							<tr>
								<td><input type="checkbox" id="cid" name="cid" value="${client.cid}"></td>
								<td>${client.cid}</td>
								<td>${client.name}</td>
								<td>${client.sex}</td>
								<td>${client.email}</td>
								<td>${client.tel}</td>
								<td>${client.qq}</td>
								<td>
									<c:if test="${client.type == 0}">
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
									</c:if> 
								</td>
								<td>${client.reg}</td>
								<td> 
									<c:if test="${allActions['3'] != null}">
										<a href="<%=basePath%>${allActions['3'].url}?client.cid=${client.cid}" class="tablelink">修改</a>
									</c:if>
									<c:if test="${allActions['5'] != null}">
										<a href="<%=basePath%>${allActions['5'].url}?cid=${client.cid}&name=${client.name}" class="tablelink">添加任务</a>
									</c:if>
									<c:if test="${allActions['30'] != null}">
										<a href="<%=basePath%>${allActions['30'].url}?cid=${client.cid}&name=${client.name}" class="tablelink">任务列表</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<c:if test="${allActions['4'] != null}">
			<div class="buttonform1">
				<li class="delbtn"><label>&nbsp;</label><input id="delClient"
					type="button" class="deltn" value="删除客户" />
			</div>
		</c:if>
		<jsp:include page="/pages/plugins/split_page_plugin_bar.jsp"/>

	</div>

</body>


</html>
