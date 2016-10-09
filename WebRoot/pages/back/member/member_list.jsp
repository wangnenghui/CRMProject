<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
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
<jsp:include page="/pages/plugins/import_file.jsp"/>
<script type="text/javascript" src="<%=basePath%>js/pages/back/member/member_list.js"></script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li><a href="#">用户列表</a></li>
		</ul>
	</div>

	<div class="formbody">
		<div id="usual1" class="usual">
			<div class="formtitle">
				<span>用户列表</span>
			</div>
			<jsp:include page="/pages/plugins/split_page_plugin_search.jsp"/>
			<table class="tablelist">
				<thead>
					<tr>
						<th width="5%"><input type="checkbox" id="selAll"></th>
						<th width="10%">照片</th>
						<th width="20%">用户名</th>
						<th width="15%">电话</th>
						<th width="25%">最后登录日期</th>
						<th width="10%">用户状态</th>
						<th width="20%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allMembers}" var="member">
					<tr>
						<td>
							<c:if test="${member.flag == 0}">
								<input type="checkbox" id="mid" value="${member.mid}:${member.photo}">
							</c:if>
						</td>
						<td><img src="upload/member/${member.photo}" class="member_photo"></td>
						<td>${member.mid}</td>
						<td>${member.tel}</td>
						<td>${member.lastdate}</td>
						<td>${member.locked == 0 ? "活跃" : "已锁定"}</td>
						<td>
							<c:if test="${member.flag == 0}">
								<c:if test="${allActions['17'] != null}">
									<a href="<%=basePath%>${allActions['17'].url}?mid=${member.mid}" class="tablelink">修改</a>
									<a href="<%=basePath%>pages/back/member/MemberServletBack/editPassPre?mid=${member.mid}" class="tablelink">密码重置</a>
								</c:if>
								<c:if test="${allActions['17'] == null}">
									修改
								</c:if>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="buttonform1">
			<label>&nbsp;</label><input name="delBtn" id="delBtn" type="button" class="deltn"
				value="删除用户" />
		</div>
		<jsp:include page="/pages/plugins/split_page_plugin_bar.jsp"/>
	</div>

</body>
</html>
