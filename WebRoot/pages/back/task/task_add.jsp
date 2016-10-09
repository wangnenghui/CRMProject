<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String addUrl = basePath + "pages/back/task/TaskServletBack/add" ;
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp"/>
<script type="text/javascript" src="<%=basePath%>js/pages/back/task/task_add.js"></script>
<script type="text/javascript" src="<%=basePath%>js/date/WdatePicker.js"></script>

</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li>添加回访任务</li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>添加回访任务 </span>
		</div>
		<form action="<%=addUrl%>" method="post" id="myform">
			<ul class="forminfo">
				<li><label>主题</label>
					<input name="task.title" placeholder="任务的标题" id="task.title" type="text" class="dfinput" />
					<i>不能超过10个字符</i></li>
				<c:if test="${param.cid == null}">
					<li><label>客户编号</label><input name="task.client.cid" id="task.client.cid" type="text"
						class="dfinput" placeholder="请输入客户编号" /></li>
				</c:if>
				<c:if test="${param.cid != null}">
					<li><label>客户姓名</label><label>${param.name}</label>
					<input name="task.client.cid" id="task.client.cid" type="hidden"
						value="${param.cid}"/></li>
				</c:if>
				<li><label>优先级</label> <cite>
					<input id="task.level" name="task.level" type="radio" value="0" checked="checked" /> 高 &nbsp; 
					<input id="task.level" name="task.level" type="radio" value="1" /> 中  &nbsp; 
					<input id="task.level" name="task.level" type="radio" value="2" /> 低 &nbsp; 
					</cite></li>
				<li><label>预计安排时间</label>
					<input id="task.tdate" name="task.tdate" type="text" class="dfinput" placeholder="选择任务的完成日期" onClick="WdatePicker()"/>
				<li><label>回访状态</label> <select id="task.type" name="task.type">
						<option value="0">初步咨询</option>
						<option value="1" selected="selected">有意向</option>
						<option value="2">无意向</option>
						<option value="3">准备签约</option>
						<option value="4">签约完毕</option>
				</select></li>
				<li><label>回访方式</label> 
					<select id="task.visit" name="task.visit">
						<option value="0">电话回访</option>
						<option value="1">上门回访</option>
						<option value="2" selected="selected">网络咨询</option>
				</select></li>
				<li><label>备注</label>
				<textarea name="task.note" id="task.note" cols="" rows="" class="textinput"></textarea></li>
				<li><label>&nbsp;</label>
				<input type="submit" class="btn" value="确认保存" /></li>
			</ul>
		</form>
	</div>


</body>
</html>
