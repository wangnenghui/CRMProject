<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String editUrl = basePath + "pages/back/member/MemberServletBack/editPass" ;
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp" />
<script type="text/javascript" src="<%=basePath%>/js/pages/back/member/member_edit_password_admin.js"></script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li><a href="#">修改登录密码</a></li>
		</ul>
	</div>

	<div class="formbody">


		<div id="usual1" class="usual">

			<div class="itab">
				<ul>
					<li><a href="#tab1" class="selected">修改“${param.mid}”登录密码</a></li>
				</ul>
			</div>
			<div id="tab1" class="tabson">
				<form action="<%=editUrl%>" method="post" id="editForm">  
				<ul class="forminfo">
					<li><label>新的密码<b>*</b></label><input name="member.password" id="member.password" type="password"
						class="dfinput" placeholder="新的密码" style="width:518px;" /></li>
					<li><label>&nbsp;</label>
						<input type="hidden" name="member.mid" id="member.mid" value="${param.mid}">
						<input type="submit" class="btn" value="更新密码" /></li>
				</ul>
				</form>
			</div>
		</div>
		<script type="text/javascript">
			$("#usual1 ul").idTabs();
		</script>
		<script type="text/javascript">
			$('.tablelist tbody tr:odd').addClass('odd');
		</script>

	</div>

</body>
</html>
