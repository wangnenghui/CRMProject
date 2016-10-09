<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String searchUrl = basePath + "pages/back/mclient/ManagerClientServletBack/listSplit" ;
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
	src="<%=basePath%>js/pages/back/mclient/client_list.js"></script>
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
						<th width="18%">姓名</th>
						<th width="12%">客服</th>
						<th width="5%">性别</th>
						<th width="14%">邮箱</th>
						<th width="12%">电话</th>
						<th width="12%">QQ</th>
						<th width="8%">客户状态</th>
						<th width="12%">登记时间</th>
						<th width="7%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${allClients != null}">
						<c:forEach items="${allClients}" var="client">
							<tr> 
								<td><a href="<%=basePath%>${allActions['27'].url}?client.cid=${client.cid}">${client.name}</a></td>
								<td>${client.member.mid}</td>
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
									<c:if test="${allActions['29'] != null}">
										<a href="<%=basePath%>${allActions['29'].url}?cid=${client.cid}&name=${client.name}" class="tablelink">任务列表</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<jsp:include page="/pages/plugins/split_page_plugin_bar.jsp"/>

	</div>

</body>


</html>
