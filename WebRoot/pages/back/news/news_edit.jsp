<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String editUrl = basePath + "pages/back/news/NewsServletBack/edit" ; 
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp"/>
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/editor/kindeditor.js"></script>
<script type="text/javascript" src="<%=basePath%>js/pages/back/news/news_edit.js"></script>
<script type="text/javascript">
	KE.show({
		id : 'news.note'
	});
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li><a href="#">系统设置</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div id="usual1" class="usual">
			<div class="itab">
				<ul>
					<li><a href="#tab1" class="selected">修改公告通知</a></li>
				</ul>
			</div>
			<div id="tab1" class="tabson">
				<div class="formtext">
					Hi，<b>${mid}</b>，欢迎您试用信息发布功能！
				</div>
				<form action="<%=editUrl%>" method="post" id="myform">
				<ul class="forminfo">
					<li><label>公告标题<b>*</b></label>
					<input id="news.title" name="news.title" value="${news.title}" type="text" class="dfinput" placeholder="请输入公告标题" style="width:518px;" /></li>
					<li><label>公告分类<b>*</b></label>
						<div class="vocation">
							<select class="select1" id="news.type" name="news.type">
								<option value="0" ${news.type == 0 ? "selected" : ""}>普通通知</option>
								<option value="1" ${news.type == 1 ? "selected" : ""}>重要通知</option>
							</select>
						</div></li>
					<li><label>通知内容<b>*</b></label> 
						<textarea name="news.note" id="news.note" style="width:700px;height:250px;visibility:hidden;">${news.note}</textarea>
					</li>
					<li><label>&nbsp;
					</label>
					<input type="hidden" name="news.nid" id="news.nid" value="${news.nid}">
					<input name="" type="submit" class="btn" value="马上发布" /></li>
				</ul>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
