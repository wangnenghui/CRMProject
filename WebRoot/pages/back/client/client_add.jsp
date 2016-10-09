<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String addUrl = basePath
			+ "pages/back/client/ClientServletBack/add";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp" />
<script type="text/javascript" 
	src="<%=basePath%>/js/pages/back/client/client_add.js"></script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li>快速添加客户</li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>快速添加客户</span>
		</div>
		<form action="<%=addUrl%>" method="post" id="myform">
			<ul class="forminfo">
				<li><label>客户名称</label><input name="client.name"
					id="client.name" type="text" class="dfinput" /> <i>不能超过10个字符</i></li>
				<li><label>性别</label> <cite><input name="client.sex"
						id="client.sex" type="radio" value="男" checked="checked" /> 男
						&nbsp;&nbsp;&nbsp; <input name="client.sex" id="client.sex"
						type="radio" value="女" /> 女</cite></li>
				<li><label>电话</label><input name="client.tel" id="client.tel"
					type="text" class="dfinput" /></li>
				<li><label>邮箱</label><input name="client.email"
					id="client.email" type="text" class="dfinput" /></li>
				<li><label>QQ</label><input name="client.qq" id="client.qq"
					type="text" class="dfinput" /></li>

				<li><label>客户状态</label> <select name="client.type"
					id="client.type">
						<option value="0" selected="selected">初步咨询</option>
						<option value="1">有意向</option>
						<option value="2">无意向</option>
						<option value="3">准备签约</option>
						<option value="4">签约完毕</option>
				</select></li>
				<li><label>备注</label> <textarea name="client.note"
						id="client.note" cols="" rows="" class="textinput"></textarea></li>
				<li><label>&nbsp;</label><input name="" type="submit"
					class="btn" value="确认保存" /></li>
			</ul>
		</form>
	</div>

</body>
</html>
