<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String logoutUrl = basePath + "LoginServletBack/logout" ;
	String editPasswordUrl = basePath + "pages/back/member/MemberServletBack/editPasswordPre" ;
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp" />
<script type="text/javascript" src="<%=basePath%>js/pages/back/top.js"></script>
</head>
<body style="background:url(images/topbg.gif) repeat-x;">
	<div class="topleft">
		<a href="<%=basePath%>pages/back/index.jsp" target="_parent"></a> <a href="<%=basePath%>pages/back/DefaultServletBack/show"
			target="rightFrame"><img src="images/logo.png" border="0"></a>
	</div>

	<ul class="nav">
		<c:if test="${allActions['1'] != null}">
			<li><a href="<%=basePath%>${allActions['1'].url}" target="rightFrame"
				class="selected"><img src="images/icon01.png" border="0"
					title="工作台" />
					<h2>添加客户</h2> </a></li>
		</c:if>
		<c:if test="${allActions['5'] != null}">
		<li><a href="<%=basePath%>${allActions['5'].url}" target="rightFrame"><img
				src="images/icon02.png" border="0" title="模型管理" />
				<h2>添加任务</h2> </a></li>
		</c:if>
		<c:if test="${allActions['11'] != null}">
		<li><a href="<%=basePath%>${allActions['11'].url}" target="rightFrame"> <img
				src="images/icon03.png" title="模块设计" />
				<h2>公告管理</h2></a></li>
		</c:if>
	</ul>
 
	<div class="topright">
		<ul>
			<li><span><img src="images/help.png" title="帮助"
					class="helpimg" /></span>帮助</li>
			<li><a href="<%=editPasswordUrl%>" target="rightFrame">修改密码</a></li> 
			<li><a href="<%=logoutUrl%>" target="_parent">退出</a></li> 
		</ul>

		<div class="user">
			<span>${mid}</span>
			 <c:if test="${allActions['11'] != null}">
				<a href="<%=basePath%>${allActions['11'].url}" target="rightFrame"><i>消息</i> <b>${unread}</b></a>
			</c:if>
			
		</div>

	</div>

</body>
</html>
