<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String editUrl = basePath
			+ "pages/back/client/ClientServletBack/edit";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp" />
<script type="text/javascript" 
	src="<%=basePath%>/js/pages/back/client/client_edit.js"></script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li>修改客户信息</li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>修改客户信息</span>
		</div>
		<form action="<%=editUrl%>" method="post" id="myform">
			<ul class="forminfo">
				<li><label>客户名称</label><input name="client.name"
					id="client.name" value="${client.name}" type="text" class="dfinput" /> <i>不能超过10个字符</i></li>
				<li><label>性别</label> <cite>
				<input name="client.sex"
						id="client.sex" type="radio" value="男" ${client.sex=="男"?"checked":""}/> 男
						&nbsp;&nbsp;&nbsp; <input name="client.sex" id="client.sex"
						type="radio" value="女" ${client.sex=="女"?"checked":""}/> 女</cite></li>
				<li><label>电话</label><input name="client.tel" id="client.tel"
					type="text" class="dfinput" value="${client.tel}" /></li>
				<li><label>邮箱</label><input name="client.email"
					id="client.email" type="text" class="dfinput" value="${client.email}" /></li>
				<li><label>QQ</label><input name="client.qq" id="client.qq"
					type="text" class="dfinput" value="${client.qq}" /></li>

				<li><label>客户状态</label> <select name="client.type"
					id="client.type">
						<option value="0" ${client.type == 0 ? "selected" : ""}>初步咨询</option>
						<option value="1" ${client.type == 1 ? "selected" : ""}>有意向</option>
						<option value="2" ${client.type == 2 ? "selected" : ""}>无意向</option>
						<option value="3" ${client.type == 3 ? "selected" : ""}>准备签约</option>
						<option value="4" ${client.type == 4 ? "selected" : ""}>签约完毕</option>
				</select></li>
				<li><label>备注</label> <textarea name="client.note"
						id="client.note" cols="" rows="" class="textinput">${client.note}</textarea></li>
				<li><label>&nbsp;</label>
					<input type="hidden" name="client.cid" id="client.cid" value="${client.cid}">
				<input name="" type="submit"
					class="btn" value="确认保存" /></li>
			</ul>
		</form>
	</div>

</body>
</html>
