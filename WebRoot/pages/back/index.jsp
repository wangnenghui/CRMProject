<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
</head> 
<frameset rows="88,*,31" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="<%=basePath%>pages/back/top.jsp" name="topFrame" scrolling="No"
		noresize="noresize" id="topFrame" title="topFrame" />
	<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
		<frame src="<%=basePath%>pages/back/left.jsp" name="leftFrame" scrolling="No"
			noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="<%=basePath%>pages/back/DefaultServletBack/show" name="rightFrame" id="rightFrame"
			title="rightFrame" />
	</frameset>
	<frame src="<%=basePath%>pages/back/footer.jsp" name="bottomFrame" scrolling="No"
		noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
<noframes>
</html>
