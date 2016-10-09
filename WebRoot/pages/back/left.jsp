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
<jsp:include page="/pages/plugins/import_file.jsp"/>
<script type="text/javascript" src="<%=basePath%>js/pages/back/left.js"></script>
</head>
<body style="background:#fff3e1;">
	<c:if test="${groups != null}">    
    <dl class="leftmenu">
    	<c:forEach items="${groups}" var="gup">
			<dd>
				<div class="title">
					<span><img src="images/${gup.img}" /></span>${gup.title}
				</div>
				<ul class="menuson">
					<c:forEach items="${gup.action}" var="act">
						<c:if test="${act.menu == 1}">
							<li><cite></cite><a href="<%=basePath%>${act.url}"
								target="rightFrame">${act.title}</a><i></i></li>
						</c:if>
					</c:forEach>
				</ul>
			</dd>
		</c:forEach>
	</dl>
	</c:if>

</body>
</html>
