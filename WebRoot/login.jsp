<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String loginUrl = basePath + "LoginServletBack/login" ;
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp"/>
<script src="<%=basePath%>js/cloud.js" type="text/javascript"></script>
<script src="<%=basePath%>js/login.js" type="text/javascript"></script>
</head>
<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
	<div class="logintop">
		<span>欢迎登录CRM管理界面平台</span>
		<ul>
			<li><a href="#">回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>
	<div class="loginbody">
		<span class="systemlogo"></span>
		<div class="loginbox">
			<form action="<%=loginUrl%>" method="post" id="loginForm">
			<ul>
				<li><input name="member.mid" id="member.mid" type="text" class="loginuser"
					placeholder="输入登录用户名"/></li> 
				<li><input name="member.password" id="member.password" type="password" class="loginpwd"
					placeholder="请输入登录密码"/></li>
				<li><input type="submit" class="loginbtn" value="登录"/></li>
			</ul>
			</form>
		</div>
	</div>
	<div class="loginbm"></div>
</body>
</html>
